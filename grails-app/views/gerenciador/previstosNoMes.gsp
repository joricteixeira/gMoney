<head>
    <meta name="layout" content="main" />
</head>

<body>
<div class="container">
    <div class="well well-lg">
        LISTAR

    </div>
    <h3>Previsto para ${mes}/${ano}: <strong><g:formatNumber type="currency" currencyCode="BRL" number="${itensFatura*.valor.sum{it}}" /> | <g:formatNumber type="currency" currencyCode="BRL" number="${valorRenda}" /></strong></h3>
    <g:render template="/gerenciador/barraPressao" />
    <table class="table table-bordered">
        <tr>
            <th>#</th>
            <th>Valor</th>
            <th>Instituição</th>
            <th>Fatura</th>
            <th>Terceiro</th>
            <th>Valor terceiro</th>
            <th>Descrição</th>
            <th>Consolidado</th>
        </tr>
        <g:each in="${itensFatura}" var="item">
            <tr
                <g:if test="${item.id}"> style="color:#3c763d; background-color: #dff0d8;"  </g:if>
                <g:else> style="color:#a94442; background-color: #f2dede;" </g:else>
            >
                <td>${item?.id}</td>
                <td><g:formatNumber number="${item.valor}" type="currency" currencyCode="BRL"/></td>
                <td>${item?.fatura?.instituicao?.nome}</td>
                <td><g:formatDate date="${item?.fatura?.dataVencimento}" format="dd/MM/yyyy"/></td>
                <td>${item?.terceiro?.nome ?: '-'}</td>
                <td>
                    <g:if test="${item.valorTerceiro != null && item.valorTerceiro != 0 }">
                        <g:formatNumber number="${item.valorTerceiro}" type="currency" currencyCode="BRL"/>
                    </g:if>
                    <g:else>
                        -
                    </g:else>
                </td>
                <td>${item.descricao}</td>
                <td>
                    <g:if test="${item.id}"> Sim </g:if>
                    <g:else> Não </g:else>
                </td>
            </tr>
        </g:each>
    </table>
</div>
</body>