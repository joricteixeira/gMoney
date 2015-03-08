<head>
    <meta name="layout" content="main" />
</head>
<body>
<div class="container">
    <div class="well well-lg">
        filtros
    </div>
    <table class="table table-bordered table-striped">
        <tr>
            <th>#</th>
            <th>Valor</th>
            <th>Instituição</th>
            <th>Fatura</th>
            <th>Vencimento da Fatura</th>
            <th>Data Pagamento</th>
        </tr>
        <g:each in="${pagamentos}" var="item">
            <tr>
                <td>${item.id}</td>
                <td><g:formatNumber number="${item.valor}" type="currency" currencyCode="BRL"/></td>
                <td>${item.fatura.instituicao.nome}</td>
                <td>${item.fatura.id}</td>
                <td><g:formatDate date="${item.fatura.dataVencimento}" format="dd/MM/yyyy"/></td>
                <td><g:formatDate date="${item.data}" format="dd/MM/yyyy"/></td>
            </tr>
        </g:each>
        <g:if test="${pagamentos.isEmpty()}">
            <tr>
                <td colspan="6">Não existem pagamentos registrados</td>
            </tr>
        </g:if>
    </table>
</div>
</body>