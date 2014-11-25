package br.com.gmoney

import grails.transaction.Transactional

@Transactional
class GerenciadorService {

    def listaGastosNoMes(int mes, int ano) {
        def itens = ItemFatura.executeQuery("SELECT I FROM ItemFatura AS I WHERE MONTH(I.fatura.dataVencimento) = :pMes AND YEAR(I.fatura.dataVencimento) = :pAno ", ["pMes":mes,"pAno":ano])
        ItemFatura item = new ItemFatura()
        item.dataOrigemCompra = new Date()
        item.descricao = 'Teste dirty'
        item.valor = new BigDecimal('45')

        itens.add(item)
        itens.add(item)

        return itens
    }
}
