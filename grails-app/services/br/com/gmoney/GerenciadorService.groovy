package br.com.gmoney

import grails.transaction.Transactional

@Transactional
class GerenciadorService {

    def listaGastosNoMes(int mes, int ano) {
        def itens = ItemFatura.executeQuery("SELECT I FROM ItemFatura AS I WHERE MONTH(I.fatura.dataVencimento) = :pMes AND YEAR(I.fatura.dataVencimento) = :pAno ", ["pMes":mes,"pAno":ano])

        def itensRecorrentesLancados = itens*.itemFaturaRecorrente
        def itensRecorrentesNaoLancados = listarItensRecorrentesDivergentes(itensRecorrentesLancados)

        itensRecorrentesNaoLancados.each{ it ->
            def item = new ItemFatura()
            item.descricao = it.descricao
            item.valor = it.valor
            item.fatura = new Fatura()
            item.fatura.instituicao = it.instituicao

            itens.add(item)
        }

        println(itensRecorrentesNaoLancados)


        return itens
    }

    private def listarItensRecorrentesDivergentes(List lista){
        if((lista - null).size() > 0){
            return ItemFaturaRecorrente.findAllByIdNotInList((lista - null)*.id)
        }else{
            return ItemFaturaRecorrente.list()
        }
    }
}
