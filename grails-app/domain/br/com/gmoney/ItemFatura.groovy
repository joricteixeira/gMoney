package br.com.gmoney

import com.lowagie.text.pdf.AcroFields

class ItemFatura {

    Date dataOrigemCompra
    int numeroParcela
    int quantidadeParcelas
    BigDecimal valor
    String descricao
    Terceiro terceiro
    BigDecimal valorTerceiro

    ItemFaturaRecorrente itemFaturaRecorrente

    static belongsTo = [fatura: Fatura, subGrupo: SubGrupo]

    ItemFatura(ItemFaturaCommand itemFaturaCommand, Fatura faturaSelecionada, SubGrupo subGrupoSelecionado){
        this.dataOrigemCompra = new Date().parse("yyyy-M-d", itemFaturaCommand.dataOrigemCompra)
        this.numeroParcela = Integer.parseInt(itemFaturaCommand.numeroParcela)
        this.quantidadeParcelas = Integer.parseInt(itemFaturaCommand.quantidadeParcelas)
        this.valor = new BigDecimal(itemFaturaCommand.valor ?: 0)
        this.descricao = itemFaturaCommand.descricao
        this.terceiro = Terceiro.findById(new Long(itemFaturaCommand.terceiroId ?: 0))
        this.valorTerceiro = new BigDecimal(itemFaturaCommand.valorTerceiro ?: 0)
        this.fatura = faturaSelecionada
        this.subGrupo = subGrupoSelecionado
        this.itemFaturaRecorrente = ItemFaturaRecorrente.findById(new Long(itemFaturaCommand.itemRecorrente ?: 0))
    }


    static constraints = {
        numeroParcela(min: 1)
        quantidadeParcelas(min: 1)
        descricao(nullable: false, blank: false)
        terceiro(nullable: true)
        itemFaturaRecorrente(nullable: true)
    }

    static mapping = {
        version false
        table 'TB_ITEM_FATURA'

        id column: 'ID'
        dataOrigemCompra colum: 'DATA_ORIGEM_COMPRA'
        numeroParcela column: 'NUMERO_PARCELA'
        quantidadeParcelas column: 'QUANTIDADE_PARCELAS'
        valor column: 'VALOR'
        descricao column: 'DESCRICAO'
        terceiro column: 'TERCEIRO_ID'
        valorTerceiro colum: 'VALOR_TERCEIRO'
        fatura column: 'FATURA_ID'
        subGrupo column: 'SUB_GRUPO_ID'
        itemFaturaRecorrente column: 'ITEM_FATURA_RECORRENTE_ID'

    }
}
