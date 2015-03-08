package br.com.gmoney

import java.text.SimpleDateFormat

class Fatura {
    Date dataVencimento

    static belongsTo = [instituicao: Instituicao]

    static hasMany = [itensFatura: ItemFatura, encargos: Encargo, pagamentos: Pagamento]

    static constraints = {
    }

    static mapping = {
        table 'TB_FATURA'
        version false
        
        id column: 'ID'
        instituicao column: 'INSTITUICAO_ID'
        dataVencimento column: 'DATA_VENCIMENTO'
    }

    def getAt(int indice){
        if(indice == 0){
            return this.itensFatura*.valor.sum{it}
        }
        if(indice == 1){
            return this.dataVencimento
        }
    }

    BigDecimal getValor(){
        return this.itensFatura*.valor.sum{it}
    }

    BigDecimal getValorTerceiro(){
        return this.itensFatura*.valorTerceiro.sum{it}
    }

    BigDecimal getValorPagamentos(){
        if(!this.pagamentos.isEmpty()){
            return this.pagamentos*.valor.sum{it}
        }else {
            return null
        }

    }

    String getDataVencimentoFormatada(){
        String newstring = new SimpleDateFormat("dd/MM/yyyy").format(this.dataVencimento);

        return newstring
    }

}