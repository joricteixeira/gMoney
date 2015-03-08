package br.com.gmoney

class GerenciadorController {
    GerenciadorService gerenciadorService
    FaturaService faturaService

    def itensNoMes() {
        Calendar atual = Calendar.getInstance()
        def mes = params.mes && params.mes.isNumber() ? Integer.parseInt(params.mes) : atual.cdate.month
        def ano = params.ano && params.ano.isNumber() ? Integer.parseInt(params.ano) : atual.cdate.year

        def mapaMeses = gerenciadorService.calcularMeses(mes, ano)

        def listaGastos = gerenciadorService.listaGastosNoMes(mes, ano)

        render(view: '/gerenciador/previstos/porItens' ,
               model:[itensFatura:listaGastos,
                      mes:mes, ano:ano,
                      valorRenda: 4000,
                      valorGasto: listaGastos*.valor.sum{it},
                      mapaMeses: mapaMeses
               ]
        )
    }

    def faturasNoMes(){
        Calendar atual = Calendar.getInstance()
        def mes = params.mes && params.mes.isNumber() ? Integer.parseInt(params.mes) : atual.cdate.month
        def ano = params.ano && params.ano.isNumber() ? Integer.parseInt(params.ano) : atual.cdate.year

        def mapaMeses = gerenciadorService.calcularMeses(mes, ano)
        def itensRecorrentesNaoLancados = gerenciadorService.listarItemRecorrenteNaoLancadosNoMes(mes, ano)
        def itensConvertidos = gerenciadorService.converterItemRecorrenteParaItemFatura(itensRecorrentesNaoLancados)

        def filtros = new FiltrosFaturaCommand()
        filtros.mes = mes
        filtros.ano = ano
        def faturas = faturaService.listaDeFaturas(filtros)


        render(view: '/gerenciador/previstos/porFaturas' ,
                model:[faturas:faturas,
                       itensFaturasConvertidos: itensConvertidos,
                       mes:mes, ano:ano,
                       valorRenda: 4000,
                       valorGasto: faturas*.valor.sum{it},
                       mapaMeses: mapaMeses
                ]
        )
    }
}
