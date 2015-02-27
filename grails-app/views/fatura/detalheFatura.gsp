<head>
   <meta name="layout" content="main" />
</head>

<body>
    <div class="container">

        <h1>${fatura.instituicao.nome}</h1>

        <div class="row">

            <div class="col-lg-2 col-md-3 col-sm-3 col-xs-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Vencimento</h3>
                    </div>
                    <div class="panel-body">
                        <g:formatDate format="dd/MM/yyyy" date ="${fatura.dataVencimento}" />
                    </div>
                </div>
            </div>

            <div class="col-lg-2 col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Total desta Fatura
                        </h3>
                    </div>
                    <div class="panel-body">
                        <g:formatNumber number="${(fatura.itensFatura*.valor.sum{it} ?: 0) + (fatura.encargos*.valor.sum{it} ?: 0)}" type="currency"/>
                    </div>
                </div>
            </div>
        </div>

        <g:if test="${!fatura.encargos.isEmpty()}">
        <table class="table table-bordered">
            <tr>
                <th>Descri&ccedil;&atilde;o</th>
                <th>R$</th>
            </tr>
            <g:each in="${fatura.encargos}" var="item">
                <tr>
                    <td>${item.descricao}</td>
                    <td><g:formatNumber number="${item.valor}" type="currency" currencyCode="BRL" currencySymbol=""/></td>
                </tr>
            </g:each>
        </table>
        </g:if>

        <table class="table table-bordered">
            <tr>
                <th>#</th>
                <th>Data</th>
                <th>Descri&ccedil;&atilde;o</th>
                <th>Parcelas</th>
                <th>R$</th>
                <th>Terceiro</th>
                <th>Valor Terceiro</th>
            </tr>
            <g:each in="${fatura.itensFatura.sort{a,b -> a.dataOrigemCompra < b.dataOrigemCompra ? -1 : 1}}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td><g:formatDate date="${item.dataOrigemCompra}" format="dd/MM/yyyy"/></td>
                    <td>${item.descricao}</td>
                    <td>${item.numeroParcela} / ${item.quantidadeParcelas}</td>
                    <td><g:formatNumber number="${item.valor}" type="currency" currencyCode="BRL" currencySymbol=""/></td>
                    <td>${item.terceiro?.nome}</td>
                    <td><g:formatNumber number="${item.valorTerceiro}" type="currency" currencyCode="BRL" currencySymbol=""/></td>
                </tr>
            </g:each>
        </table>
    </div>
</body>