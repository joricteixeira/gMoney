package br.com.gmoney

class GraficoController {
    GraficoService graficoService
    def instituicaoService
    def gerenciadorService

    def gastosNoMes() {

        Calendar atual = Calendar.getInstance()
        def mes = params.mes && params.mes.isNumber() ? Integer.parseInt(params.mes) : atual.cdate.month
        def ano = params.ano && params.ano.isNumber() ? Integer.parseInt(params.ano) : atual.cdate.year

        def gastosPorInstituicao = instituicaoService.gastosNoMesPosInstituicao(mes,ano)
        def graficoTO = graficoService.gerarGrafico(gastosPorInstituicao, 'pie')
        def instituicoesSemGastos = instituicaoService.listarInstituicoesRestantes(gastosPorInstituicao)

        def instituicoes = Instituicao.findAll()

        def mapaMeses = gerenciadorService.calcularMeses(mes, ano)

        render(view: '/grafico/gastoNoMes',
               model:[grafico: graficoTO,
                      gastosPorInstituicao: gastosPorInstituicao,
                      mapaMeses: mapaMeses,
                      instituicoesSemGastos: instituicoesSemGastos,
                      data: [mes: mes, ano: ano]
                     ]
              )
    }

    def balancoInstituicao(int id){

        if(id == 0) id = 1
        def instituicao = Instituicao.findById(id)
        def faturas = Fatura.findAllByInstituicao(instituicao)


        def graficoTO = graficoService.gerarGraficoBalanco(faturas, 'area')

        render(view: '/gerenciador/instituicao/balanco', model: [grafico: graficoTO, instituicao: instituicao, instituicoes: Instituicao.list()])
    }
}
