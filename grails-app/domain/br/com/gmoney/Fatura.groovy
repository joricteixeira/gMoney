package br.com.gmoney

class Fatura {
    Date dataVencimento

    static belongsTo = [instituicao: Instituicao]

    static hasMany = [itensFatura: ItemFatura]

    static constraints = {
    }

    static mapping = {
        table 'TB_FATURA'
        version false
        
        id column: 'ID'
        instituicao column: 'INSTITUICAO_ID'
        dataVencimento column: 'DATA_VENCIMENTO'
    }
}

