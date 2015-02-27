package br.com.gmoney

import br.com.gmoney.grafico.DataPointTO
import br.com.gmoney.grafico.DataTO
import br.com.gmoney.grafico.GraficoTO
import grails.transaction.Transactional

@Transactional
class GraficoService {
    def insituicaoService

    def gastoNoMesPorInstituicao(List<ItemFatura> resultados) {

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
        data.type = 'pie'

        def datas = [data]

        def grafico = new GraficoTO()
        grafico.datas = datas
        grafico.titulo = ""//"Gastos em mm/aaaa"
        grafico.nomeDiv = 'chartContainer'

        return grafico
    }
}