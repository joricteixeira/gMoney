package br.com.gmoney

import grails.transaction.Transactional

@Transactional
class GerenciadorService {

    def listaGastosNoMes(int mes, int ano) {
        def itens = []

        def itensRecorrentesNaoLancados = listarItemRecorrenteNaoLancadosNoMes(mes, ano)
        def itensConvertidos = converterItemRecorrenteParaItemFatura(itensRecorrentesNaoLancados)
        itens.addAll(itensConvertidos)

        def itensLancados = ItemFatura.executeQuery("SELECT I FROM ItemFatura AS I WHERE MONTH(I.fatura.dataVencimento) = :pMes AND YEAR(I.fatura.dataVencimento) = :pAno ", ["pMes":mes,"pAno":ano])
        itens.addAll(itensLancados)

        return itens
    }

    def calcularMeses(int mes, int ano){
        Calendar c = Calendar.getInstance()
        c.set(Calendar.MONTH, mes-1)
        c.set(Calendar.YEAR, ano)

        c.add(Calendar.MONTH, -1)
        def mesPrev = c.get(Calendar.MONTH) + 1
        def anoPrev = c.get(Calendar.YEAR)
        c.add(Calendar.MONTH, 2)
        def mesNext = c.get(Calendar.MONTH) + 1
        def anoNext = c.get(Calendar.YEAR)

        return [mesPrev: mesPrev, anoPrev: anoPrev, mesNext: mesNext, anoNext: anoNext, mes: mes, ano: ano]
    }

    def listarItemRecorrenteNaoLancadosNoMes(int mes, int ano){
        def lista = ItemFaturaRecorrente.executeQuery("SELECT R FROM ItemFaturaRecorrente AS R WHERE R.id not in (SELECT distinct(I.itemFaturaRecorrente.id) FROM ItemFatura AS I WHERE I.fatura.id in (SELECT F.id FROM Fatura AS F WHERE MONTH(F.dataVencimento) = :pMes AND YEAR(F.dataVencimento) = :pAno) AND I.itemFaturaRecorrente != null)",["pMes":mes,"pAno":ano])

        return lista
    }

    def listarItemRecorrenteNaoLancadosNoMesConvertidos(int mes, ano){
        def lista = listarItemRecorrenteNaoLancadosNoMes(mes, ano)

        return converterItemRecorrenteParaItemFatura(lista)
    }

    def converterItemRecorrenteParaItemFatura(List<ItemFaturaRecorrente> itensRecorrentes){
        def itens = []

        itensRecorrentes.each{ it ->
            def item = new ItemFatura()
            item.descricao = it.descricao
            item.valor = it.valor
            item.fatura = new Fatura()
            item.fatura.instituicao = it.instituicao
            item.itemFaturaRecorrente = it

            itens.add(item)
        }

        return itens
    }

}
