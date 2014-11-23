package br.com.gmoney

class SubGrupo {

    static hasMany = [itemFaturas: ItemFatura]

    static constraints = {
    }

    static mapping = {
        version false

        table 'TB_SUB_GRUPO'
        id column: 'ID'
    }
}
