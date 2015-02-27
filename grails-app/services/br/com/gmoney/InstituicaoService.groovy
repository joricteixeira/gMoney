package br.com.gmoney

import grails.transaction.Transactional

@Transactional
class InstituicaoService {

    def gastosNoMesPosInstituicao(int mes, int ano) {
        String query = "SELECT SUM(I.valor), I.fatura.instituicao.nome FROM ItemFatura as I LEFT JOIN I.fatura WHERE MONTH(I.fatura.dataVencimento) = :pMes AND YEAR(I.fatura.dataVencimento) = :pAno GROUP BY I.fatura.instituicao"
        return ItemFatura.executeQuery(query,["pMes": mes, "pAno": ano])
    }

    def listarInstituicoesRestantes(def gastosPorInstituicao){

        String query = "SELECT I FROM Instituicao AS I WHERE I.nome not in (:pNomes)"

        List<String> listaNomes = []

        for(Object obj : gastosPorInstituicao){
            listaNomes.add(obj[1])
        }

        return Instituicao.executeQuery(query, ["pNomes":listaNomes])
    }
}
