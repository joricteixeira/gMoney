package br.com.gmoney

class ItemFaturaRecorrente {

    BigDecimal valor
    String descricao
    Instituicao instituicao

    Date dataInicial
    Date dataFinal

    static constraints = {
        instituicao(nullable: true)
        dataFinal(nullable: true)
    }

    static mapping = {
        table 'TB_ITEM_FATURA_RECORRENTE'

        version false
        id column:'ID'
        valor column: 'VALOR'
        descricao column: 'DESCRICAO'
        instituicao column: 'INSITUICAO_ID'
        dataInicial column: 'DATA_INICIAL'
        dataFinal column: 'DATA_FINAL'
    }
}
