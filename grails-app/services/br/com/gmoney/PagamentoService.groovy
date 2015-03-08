package br.com.gmoney

import grails.transaction.Transactional

@Transactional
class PagamentoService {

    def processarAdicao(Fatura fatura, Date dataPagamento, BigDecimal valorPagamento) {
        def pagamento = new Pagamento()
        pagamento.fatura = fatura
        pagamento.data = dataPagamento
        pagamento.valor = valorPagamento

        pagamento.save()
    }
}
