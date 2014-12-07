package br.com.gmoney

class Instituicao {
    String nome
    int diaVencimento
    int diaCorte
    BigDecimal valorSugerido
    Date dataFimVigencia

    static belongsTo = [cliente: Cliente]

    static hasMany = [saldos: Saldo, faturas: Fatura]

        static constraints = {
    }

    static mapping = {
        table 'TB_INSTITUICAO'
        version false

        id column: 'ID'
        nome column: 'NOME'
        diaVencimento column: 'DIA_VENCIMENTO'
        diaCorte column: 'DIA_CORTE'
        valorSugerido column: 'VALOR_SUGERIDO'
        dataFimVigencia column: 'DATA_FIM_VIGENCIA'
        cliente column: 'CLIENTE_ID'
    }

    String toString(){
        this.id+ " - "+ this.nome
    }
}
