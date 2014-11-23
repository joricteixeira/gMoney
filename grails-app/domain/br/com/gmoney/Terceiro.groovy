package br.com.gmoney

class Terceiro {

    String nome

    static constraints = {
    }

    static mapping = {
        table 'TB_TERCEIRO'
        version false
        id column: 'ID'
        nome column: 'NOME'
    }
}
