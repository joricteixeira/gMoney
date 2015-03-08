<g:if test="${dataVencimento}">
    <input type="text" readonly="readonly" class="form-control" value="<g:formatDate date="${dataVencimento}" format="dd/MM/yyyy" />" />
</g:if>
<g:else>
    <input type="text" readonly="readonly" class="form-control" value="Não foi possível prever a data de vencimento deste lançamento" />
</g:else>