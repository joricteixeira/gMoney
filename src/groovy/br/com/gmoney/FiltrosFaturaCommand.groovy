package br.com.gmoney

import grails.validation.Validateable

/**
 * Created by T-850 on 08/11/2014.
 */
@Validateable
class FiltrosFaturaCommand {
    String instituicao
    int mes
    int ano
}
