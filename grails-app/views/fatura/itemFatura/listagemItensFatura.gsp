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
                <th>Terceiro</th>
                <th>Valor terceiro</th>
                <th>Descrição</th>
            </tr>
            <g:each in="${itensFatura}" var="item">
            <tr>
                <td>${item.id}</td>
                <td><g:formatNumber number="${item.valor}" type="currency" currencyCode="BRL"/></td>
                <td>${item.fatura.instituicao.nome}</td>
                <td><g:formatDate date="${item.fatura.dataVencimento}" format="dd/MM/yyyy"/></td>
                <td>${item.terceiro?.nome ?: '-'}</td>
                <td>
                    <g:if test="${item.valorTerceiro != null && item.valorTerceiro != 0 }">
                        <g:formatNumber number="${item.valorTerceiro}" type="currency" currencyCode="BRL"/>
                    </g:if>
                    <g:else>
                        -
                    </g:else>
                </td>
                <td>${item.descricao}</td>
            </tr>
            </g:each>
        </table>
    </div>
</body>