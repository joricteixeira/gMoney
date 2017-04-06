package br.com.gmoney

import grails.transaction.Transactional

@Transactional
class FaturaService {

    def processarAdicao(ItemFaturaCommand itemFaturaCommand){
        def faturas = obterFaturas(itemFaturaCommand)

        def subgrupo = new SubGrupo()
        subgrupo.save()

        faturas.eachWithIndex { fatura, index ->
            itemFaturaCommand.numeroParcela = index + 1
            def item = new ItemFatura(itemFaturaCommand, fatura, subgrupo)
            item.save()
        }
    }

    private List<Fatura> obterFaturas(ItemFaturaCommand itemFaturaCommand){
        List<Fatura> faturas = new ArrayList<Fatura>()

        log.info("Item possui faturaId: [${itemFaturaCommand.faturaId}]")

        if(itemFaturaCommand.faturaId == null) {
            log.info("Criar ou instanciar faturas")
            println("Criar ou instanciar faturas")
            def instituicao = Instituicao.findById(new Long(itemFaturaCommand.instituicaoId ?: 0))

            for (int i = Integer.parseInt(itemFaturaCommand.numeroParcela); i <= Integer.parseInt(itemFaturaCommand.quantidadeParcelas); i++) {
                Date dataVencimento = calcularDataVencimento(i, itemFaturaCommand.dataOrigemCompra, instituicao)
                println("Abrindo fatura de data vencimento: " + dataVencimento)
                dataVencimento.setHours(0)
                dataVencimento.setMinutes(0)
                dataVencimento.setSeconds(0)
                def fatura = Fatura.findOrCreateByInstituicaoAndDataVencimento(instituicao, dataVencimento)
                fatura.save()
                faturas.add(fatura)
            }

        }else{
            log.info("Adicionando item a fatura existente")
            faturas.add(Fatura.findById(new Long(itemFaturaCommand.faturaId)))
        }

        return faturas
    }

    def Date calcularDataVencimento(int numeroParcela, String dataOrigemCompra, Instituicao instituicao){
        long diaEmMillis = 24 * 60 * 60 * 1000

        Date dataCompra = new Date().parse("yyyy-M-d", dataOrigemCompra)
        Date proximoVencimento = dataCompra.clone()

        while(proximoVencimento.date != instituicao.diaVencimento){
            proximoVencimento = new Date(proximoVencimento.time + diaEmMillis)
        }

        Date dataCorte = new Date(proximoVencimento.time - (diaEmMillis * instituicao.diaCorte))
        while((dataCorte.day == 6) || (dataCorte.day == 6)){
            dataCorte = new Date(dataCorte.time + diaEmMillis)
        }

        Date vencimentoCalculado
        if(dataCompra < dataCorte){
            vencimentoCalculado = proximoVencimento
        }else{
            Calendar cal = Calendar.getInstance();
            cal.setTime(proximoVencimento);
            cal.add(Calendar.MONTH, 1);
            vencimentoCalculado = cal.getTime()
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(vencimentoCalculado);
        cal.add(Calendar.MONTH, numeroParcela-1);
        vencimentoCalculado = cal.getTime()

        return vencimentoCalculado
    }

    def listaDeFaturas(FiltrosFaturaCommand filtrosFaturaCommand){

        StringBuilder query = new StringBuilder()
        Map params = new HashMap()

        query.append("SELECT F FROM Fatura AS F WHERE 1 = 1")

        if(filtrosFaturaCommand.instituicao != null){
            query.append(" AND F.instituicao = :pInstituicao")
            params.put("pInstituicao", Instituicao.findById(new Long(filtrosFaturaCommand.instituicao)))
            //faturas = Fatura.findAllByInstituicao(Instituicao.findById(new Long(filtrosFaturaCommand.instituicao)))
        }

        if(filtrosFaturaCommand.dataInicial != null){
            query.append(" AND F.dataVencimento >= :pDataInicial")
            params.put("pDataInicial", Date.parse("yyyy-M-d",filtrosFaturaCommand.dataInicial))
        }

        if(filtrosFaturaCommand.dataFinal != null){
            query.append(" AND F.dataVencimento <= :pDataFinal")
            params.put("pDataFinal", Date.parse("yyyy-M-d",filtrosFaturaCommand.dataFinal))
        }

        if((filtrosFaturaCommand.mes != 0) && (filtrosFaturaCommand.ano != 0)){
            query.append(" AND MONTH(F.dataVencimento) = :pMes AND YEAR(F.dataVencimento) = :pAno")
            params.put("pMes",filtrosFaturaCommand.mes)
            params.put("pAno", filtrosFaturaCommand.ano)
        }

        def faturas = Fatura.executeQuery(query.toString(),params)

        return faturas
    }






}