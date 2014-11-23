package br.com.gmoney

class Cliente {
    String nome
    String login
    String password
    Date dataInicial


    static hasMany = [instituicoes: Instituicao]

    static constraints = {
    }

    static mapping = {
        table 'TB_CLIENTE'
        version false

        id column: 'ID'
        nome column: 'NOME'
        login column: 'LOGIN'
        password column: 'PASSWORD'
        dataInicial colum: 'DATA_INICIAL'
    }
}
