package br.com.gmoney

import br.com.gmoney.grafico.DataPointTO
import br.com.gmoney.grafico.DataTO
import br.com.gmoney.grafico.GraficoTO
import grails.transaction.Transactional

@Transactional
class GraficoService {
    def insituicaoService

    def gerarGrafico(def resultados, String tipoGrafico) {

        List<DataPointTO> dataPoints = new ArrayList<DataPointTO>()
        resultados.each { it ->
            def dataPoint = new DataPointTO()
            dataPoint.y = it[0]
            dataPoint.label = it[1]

            dataPoints.add(dataPoint)
        }

        def data = new DataTO()
        data.dataPoints = dataPoints
        data.legenda = 'legenda teste'
        data.name = 'nome do data'
        data.type = tipoGrafico

        def datas = [data]

        def grafico = new GraficoTO()
        grafico.datas = datas
        grafico.titulo = ""//"Gastos em mm/aaaa"
        grafico.nomeDiv = 'chartContainer'

        return grafico
    }

    def gerarGraficoBalanco(List<Fatura> faturas, String tipoGrafico) {

        List<DataPointTO> dataPointsGastos = new ArrayList<DataPointTO>()
        List<DataPointTO> dataPointsPagamentos = new ArrayList<DataPointTO>()
        faturas.each { it ->
            def dataPoint = new DataPointTO()
            dataPoint.y = it.valor
            dataPoint.label = it.dataVencimentoFormatada

            def dataPointPagamento = new DataPointTO()
            dataPointPagamento.y = it.valorPagamentos
            dataPointPagamento.label = it.dataVencimentoFormatada

            dataPointsGastos.add(dataPoint)
            dataPointsPagamentos.add(dataPointPagamento)
        }

        List<DataTO> datas = new ArrayList<DataTO>()

        def data = new DataTO()
        data.dataPoints = dataPointsGastos
        data.legenda = 'legenda teste'
        data.name = 'nome do data'
        data.type = tipoGrafico

        datas.add(data)

        def dataPagamento = new DataTO()
        dataPagamento.dataPoints = dataPointsPagamentos
        dataPagamento.legenda = 'Pagamentos'
        dataPagamento.name = 'Pagamentos'
        dataPagamento.type = tipoGrafico

        datas.add(dataPagamento)

        def grafico = new GraficoTO()
        grafico.datas = datas
        grafico.titulo = ""//"Gastos em mm/aaaa"
        grafico.nomeDiv = 'chartContainer'

        return grafico
    }
}