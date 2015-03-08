package br.com.gmoney

class Pagamento {

    Date data
    BigDecimal valor

    static belongsTo = [fatura: Fatura]

    static constraints = {
    }

    static mapping = {
        version false

        table 'TB_PAGAMENTO'

        id column: 'ID'
        valor column: 'VALOR_PAGAMENTO  '
        data column: 'DATA_PAGAMENTO'
        fatura column: 'FATURA_ID'
    }
}
