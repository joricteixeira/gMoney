package br.com.gmoney

class GraficoController {
    GraficoService graficoService

    def gastosNoMes() {

        Calendar atual = Calendar.getInstance()
        def mes = params.mes && params.mes.isNumber() ? Integer.parseInt(params.mes) : atual.cdate.month
        def ano = params.ano && params.ano.isNumber() ? Integer.parseInt(params.ano) : atual.cdate.year

        def graficoTO = graficoService.gastoNoMes(mes, ano)
        render(view: '/grafico/gastoNoMes', model:[grafico: graficoTO])
    }
}
