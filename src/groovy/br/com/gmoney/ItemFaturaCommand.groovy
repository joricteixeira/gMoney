package br.com.gmoney

import grails.validation.Validateable

/**
 * Created by T-850 on 01/11/2014.
 */
@Validateable
class ItemFaturaCommand {
    String instituicaoId
    String faturaId
    String descricao
    String numeroParcela
    String quantidadeParcelas
    String valor
    String terceiroId
    String valorTerceiro
    String dataOrigemCompra

    static constraint = {
        descricao(blank: false, nullable: false)
        numeroParcela(blank: false, nullable: false, validator:{val ->
            if(val.isNumber()){
                return true
            }else{
                return false
            }
        })

    }

    String getValor(){
        this.valor.replace('.','').replace(',','.')
    }

    String getValorTerceiro(){
        this.valorTerceiro.replace('.','').replace(',','.')
    }
}
