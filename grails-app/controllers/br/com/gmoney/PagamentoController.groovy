package br.com.gmoney

class PagamentoController {

    def pagamentoService

    static defaultAction = "listarPagamentos"

    def listarPagamentos() {
        def pagamentos = Pagamento.list()

        render(view: '/pagamento/listagemPagamentos', model: [pagamentos: pagamentos])
    }

    def lancarPagamento(){
        def instituicoes = Instituicao.list()
        render(view: '/pagamento/formularioPagamento',
               model: [instituicoes: instituicoes])
    }

    def processarAdicao(){

        def faturaId = Integer.parseInt(params.faturaPagamento)
        def fatura = Fatura.findById(faturaId)
        def valor = new BigDecimal((params.valorPagamento).replace('.','')?.replace(',','.'))
        def data = new Date().parse("yyyy-M-d", params.dataPagamento)

        pagamentoService.processarAdicao(fatura, data, valor)

        redirect action: 'listarPagamentos'
    }

    def encontrarFatura(int instituicaoSelecionada){

        def instituicao = Instituicao.findById(instituicaoSelecionada)
        Date dataVencimento = new Date().parse("yyyy-M-d", params.dataSelecionada)

        def fatura = Fatura.findByInstituicaoAndDataVencimento(instituicao, dataVencimento)

        render(view: '/pagamento/faturaEncontrada', model: [fatura: fatura])
    }
}
