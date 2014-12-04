package br.com.gmoney

class GerenciadorController {
    GerenciadorService gerenciadorService

    def index() {
        Calendar atual = Calendar.getInstance()
        def mes = params.mes && params.mes.isNumber() ? Integer.parseInt(params.mes) : atual.cdate.month
        def ano = params.ano && params.ano.isNumber() ? Integer.parseInt(params.ano) : atual.cdate.year

        def listaGastos = gerenciadorService.listaGastosNoMes(mes, ano)

        render(view: '/gerenciador/previstosNoMes' ,
               model:[itensFatura:listaGastos,
                      mes:mes, ano:ano,
                      valorRenda: 4000,
                      valorGasto: listaGastos*.valor.sum{it}
               ]
        )
    }
}
