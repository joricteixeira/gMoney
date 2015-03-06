package br.com.gmoney

import jline.internal.Log

class FaturaController {
    FaturaService faturaService

    def index() {
        def cliente = Cliente.findById(1)
        def instituicoes = Instituicao.findAllByCliente(cliente)

        log.info("Abriu")

        render view: "/fatura/selecionarInstituicao",
               model:[instituicoes: instituicoes]

    }

    def formularioAdicao(){

        Long itemRecorrenteId = (params.itemRecorrente && params.itemRecorrente.isNumber()) ? new Long(params.itemRecorrente) : 0
        def itemRecorrente = ItemFaturaRecorrente.findById(itemRecorrenteId)

        def instituicao = itemRecorrente ? itemRecorrente.instituicao : Instituicao.findById(new Long(params.instituicao ?: 0))
        if(instituicao == null){
            redirect action: index()
        }


        render view: "/fatura/formularioAdicao",
               model: [instituicao: instituicao, terceiro: Terceiro.list(), itemRecorrente: itemRecorrente]
    }

    def processarAdicao(ItemFaturaCommand itemFaturaCommand){

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

    def detalhesFatura(){
        def idFatura = new Long(params.id ?: 0)

        def fatura = Fatura.findById(idFatura)

        if(fatura == null){
            redirect action: listarFaturas()
        }

        render(view: '/fatura/detalheFatura', model:[fatura:fatura])
    }

    def calcularDataVencimento(){

        def data = params.dataObtida
        def instituicaoId = params.instituicaoObtida

        Log.info("Data Obtida: "+ data)
        Log.info("ID Instituição: "+ instituicaoId)

        if((data != "") && (instituicaoId.isNumber())){
            def instituicao = Instituicao.findById(new Long(instituicaoId))

            def dataVencimento = faturaService.calcularDataVencimento(1, data as String,instituicao)
            render(view:'/fatura/dataVencimentoCalculada', model: [dataVencimento: dataVencimento] )
        }else{
            render 'Não foi possível calcular a data'
        }





    }
}