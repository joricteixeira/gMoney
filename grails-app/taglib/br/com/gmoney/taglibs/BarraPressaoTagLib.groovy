package br.com.gmoney.taglibs

import br.com.gmoney.GerenciadorService
import br.com.gmoney.ItemFatura

class BarraPressaoTagLib {
    GerenciadorService gerenciadorService

    static defaultEncodeAs = 'none'
    //static encodeAsForTags = [tagName: 'raw']

    def barraPressao = {attrs, body ->

        /*def despesa = new BigDecimal(attrs.valorDespesaTotal)
        def receita = new BigDecimal(attrs.valorReceita)
        def despesaTerceiro = new BigDecimal(attrs.valorTerceiro)*/

        if(receita >= despesa){
            def gasto = ((despesa - despesaTerceiro)*100)/receita
            def gastoTerceiro = ((despesaTerceiro)*100)/receita
            def renda = 100 - gasto - gastoTerceiro

            out << render(template: '/taglibs/barraPressaoSobrando', model: [gasto: gasto, gastoTerceiro: gastoTerceiro, renda:renda])
        }else{
            def renda = (receita*100)/despesa
            def gastoTerceiro = (despesaTerceiro*100)/despesa
            def gasto = 100 - renda - gastoTerceiro

            out << render(template: '/taglibs/barraPressaoDevendo', model: [gasto: gasto, gastoTerceiro: gastoTerceiro, renda:renda])
        }
    }

    def barraPressaoPorMes = {attrs, body ->

        def mes = attrs.mes
        def ano = attrs.ano

        def itens = ItemFatura.executeQuery("SELECT I FROM ItemFatura AS I WHERE I.fatura.id in (SELECT F FROM Fatura AS F WHERE MONTH(F.dataVencimento) = :pMes AND YEAR(F.dataVencimento) = :pAno)", ["pMes":mes, "pAno": ano])
        def itensNaoLancados = gerenciadorService.listarItemRecorrenteNaoLancadosNoMesConvertidos(mes, ano)

        def receita = new BigDecimal(4000)
        def despesa = itens*.valor.sum{it} + itensNaoLancados*.valor.sum{it}
        def despesaTerceiro = itens*.valorTerceiro.sum{it}



        if(receita >= despesa){
            def gasto = ((despesa - despesaTerceiro)*100)/receita
            def gastoTerceiro = ((despesaTerceiro)*100)/receita
            def renda = 100 - gasto - gastoTerceiro

            out << render(template: '/taglibs/barraPressaoSobrando', model: [gasto: gasto, gastoTerceiro: gastoTerceiro, renda:renda])
        }else{
            def renda = (receita*100)/despesa
            def gastoTerceiro = (despesaTerceiro*100)/despesa
            def gasto = 100 - renda - gastoTerceiro

            out << render(template: '/taglibs/barraPressaoDevendo', model: [gasto: gasto, gastoTerceiro: gastoTerceiro, renda:renda])
        }
    }
}
