<label>Fatura para Pagar</label>
<input type="hidden" value="${fatura.id}" name="faturaPagamento"/>
<input type="text" class="form-control" readonly="readonly" name="faturaLabel" value="${fatura.id} - ${fatura.instituicao.nome} <g:formatDate date="${fatura.dataVencimento}" format="dd/MM/yyyy" />" />