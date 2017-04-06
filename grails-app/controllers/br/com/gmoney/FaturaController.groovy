package br.com.gmoney

import jline.internal.Log

class FaturaController {
    FaturaService faturaService
    GerenciadorService gerenciadorService

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
        def fatura = Fatura.findById(new Long(params.faturaId ?: 0))

        if(fatura != null) {
            instituicao = fatura.instituicao
        }

        if(instituicao == null){
            redirect action: index()
        }

        render view: "/fatura/formularioAdicao",
               model: [instituicao: instituicao,
                       terceiro: Terceiro.list(),
                       itemRecorrente: itemRecorrente,
                       fatura: fatura]
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
            redirect action: listarFaturas(new FiltrosFaturaCommand())
        }

        List<ItemFaturaRecorrente> recorrentes = gerenciadorService.listarItemRecorrenteNaoLancadosNaFatura(fatura)

        render(view: '/fatura/detalheFatura', model:[fatura:fatura, recorrentes: recorrentes])
    }

    def calcularDataVencimento(){

        def data = params.dataObtida
        def instituicaoId = params.instituicaoObtida

        Log.info("Data Obtida: "+ data)
        Log.info("ID Instituição: "+ instituicaoId)

        def dataVencimento

        if((data != "") && (instituicaoId.isNumber())){
            def instituicao = Instituicao.findById(new Long(instituicaoId))

            dataVencimento = faturaService.calcularDataVencimento(1, data as String,instituicao)
            render(view:'/fatura/dataVencimentoCalculada', model: [dataVencimento: dataVencimento] )
        }

        render(view:'/fatura/dataVencimentoCalculada', model: [dataVencimento: dataVencimento] )





    }
}