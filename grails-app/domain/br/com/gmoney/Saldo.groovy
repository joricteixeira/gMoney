package br.com.gmoney

class Saldo {
    Date data
    BigDecimal saldo

    static belongsTo = [instituicao: Instituicao]

    static constraints = {
    }

    static mapping = {
        table 'TB_SALDO'
        version false
        
        id column: 'ID'
        instituicao column: 'INSTITUICAO_ID'
        data column: 'DATA'
        saldo column: 'SALDO'
    }
}
