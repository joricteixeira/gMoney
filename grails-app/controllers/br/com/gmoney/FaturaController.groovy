package br.com.gmoney

class FaturaController {
    FaturaService faturaService

    def index() {
        def cliente = Cliente.findById(1)
        def instituicoes = Instituicao.findAllByCliente(cliente)

        render view: "/fatura/selecionarInstituicao",
               model:[instituicoes: instituicoes]

    }

    def formularioAdicao(){

        def instituicao = Instituicao.findById(new Long(params.instituicao ?: 0))
        if(instituicao == null){
            redirect action: index()
        }

        render view: "/fatura/formularioAdicao",
               model: [instituicao: instituicao, terceiro: Terceiro.list()]
    }

    def processarAdicao(ItemFaturaCommand itemFaturaCommand){
        println("> a >"+itemFaturaCommand.dataOrigemCompra)

        if(itemFaturaCommand.validate()){
            faturaService.processarAdicao(itemFaturaCommand)

            render 'ok'
        }else{

            render itemFaturaCommand.errors.allErrors
        }
    }

    def listarFaturas(FiltrosFaturaCommand filtrosFaturaCommand){

        def faturas = faturaService.listaDeFaturas(filtrosFaturaCommand)
        def instituicoes = Instituicao.list()

        render view:'/fatura/listagemFaturas',
               model: [faturas: faturas,
                       instituicoes: instituicoes,
                       filtros: filtrosFaturaCommand
               ]
    }

    def listarItensFatura(){

        def itensFatura = ItemFatura.list()

        render view: '/fatura/itemFatura/listagemItensFatura',
               model: [itensFatura: itensFatura]
    }
}