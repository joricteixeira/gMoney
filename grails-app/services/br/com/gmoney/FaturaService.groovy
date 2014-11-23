package br.com.gmoney

import grails.transaction.Transactional

@Transactional
class FaturaService {

    def processarAdicao(ItemFaturaCommand itemFaturaCommand){
        def listaFaturas = obterFaturas(itemFaturaCommand)

        def subgrupo = new SubGrupo()
        subgrupo.save()
        listaFaturas.eachWithIndex { fatura, index ->
            itemFaturaCommand.numeroParcela = index + Integer.parseInt(itemFaturaCommand.numeroParcela)
            def item = new ItemFatura(itemFaturaCommand, fatura, subgrupo)
            item.save()

        }
    }

    def List<Fatura> obterFaturas(ItemFaturaCommand itemFaturaCommand){
        List<Fatura> faturas = new ArrayList<Fatura>()

        log.info("Item possui faturaId: [${itemFaturaCommand.faturaId}]")
        println("Item possui faturaId: [${itemFaturaCommand.faturaId}]")
        if(itemFaturaCommand.faturaId == null) {
            log.info("Criar ou instanciar faturas")
            println("Criar ou instanciar faturas")
            def instituicao = Instituicao.findById(new Long(itemFaturaCommand.instituicaoId ?: 0))


            for (int i = Integer.parseInt(itemFaturaCommand.numeroParcela); i <= Integer.parseInt(itemFaturaCommand.quantidadeParcelas); i++) {
                Date dataVencimento = calcularDataVencimento(i, itemFaturaCommand.dataOrigemCompra, instituicao)
                println("Abrindo fatura de data vencimento: " + dataVencimento)
                def fatura = Fatura.findOrCreateByInstituicaoAndDataVencimento(instituicao, dataVencimento)
                fatura.save()
                faturas.add(fatura)
            }

            return faturas
        }else{
            log.info("Adicionando item a fatura existente")
            println("Adicionando item a fatura existente")
            faturas.add(Fatura.findById(new Long(itemFaturaCommand.faturaId)))
        }
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
        def faturas

        if(filtrosFaturaCommand.instituicao != null){
            faturas = Fatura.findAllByInstituicao(Instituicao.findById(new Long(filtrosFaturaCommand.instituicao)))
        }else{
            faturas = Fatura.list()
        }

        return faturas
    }






}