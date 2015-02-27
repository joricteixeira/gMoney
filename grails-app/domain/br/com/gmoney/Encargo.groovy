package br.com.gmoney

class Encargo {

    BigDecimal valor
    String descricao

    static belongsTo = [fatura: Fatura]

    static constraints = {
    }

    static mapping = {
        version false
        table "TB_ENCARGO"
        id column: 'ID'
        valor column: "VALOR"
        descricao column:"DESCRICAO"
        fatura column:"FATURA_ID"
    }
}
