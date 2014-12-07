<table class="table table-bordered table-striped">
    <tr>
        <th>#</th>
        <th>Valor</th>
        <th>Instituição</th>
        <th>Data de Vencimento</th>
        <th></th>
    </tr>
    <g:each in="${faturas}" var="fatura">
    <tr>
        <td>${fatura.id}</td>
        <td><g:formatNumber number="${fatura.itensFatura*.valor.sum{it}}" type="currency" currencyCode="BRL" /> </td>
        <td>${fatura.instituicao.nome}</td>
        <td><g:formatDate format="dd/MM/yyyy" date="${fatura.dataVencimento}" /></td>
        <td>
            <g:link controller="fatura" action="detalhesFatura" params="[id:fatura.id]">Abrir fatura</g:link>
        </td>
    </tr>
    </g:each>
</table>