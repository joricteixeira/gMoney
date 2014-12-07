<head>
   <meta name="layout" content="main" />
</head>

<body>
    <div class="container">



        <div class="row">

            <div class="col-lg-2 col-md-3 col-sm-3 col-xs-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Vencimento</h3>
                    </div>
                    <div class="panel-body">
                        <g:formatDate format="dd/MM/yyyy" value="${fatura.dataVencimento}" />
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
                        <g:formatNumber number="${fatura.itensFatura*.valor.sum{it}}" type="currency"/>
                    </div>
                </div>
            </div>
        </div>



        <table class="table table-bordered">
            <tr>
                <th>Data</th>
                <th>Descri&ccedil;&atilde;o</th>
                <th>Parcelas</th>
                <th>R$</th>
            </tr>
            <g:each in="${fatura.itensFatura}" var="item">
                <tr>
                    <td><g:formatDate date="${item.dataOrigemCompra}" format="dd/MM/yyyy"/></td>
                    <td>${item.descricao}</td>
                    <td>${item.numeroParcela} / ${item.quantidadeParcelas}</td>
                    <td><g:formatNumber number="${item.valor}" type="currency" currencyCode="BRL" currencySymbol=""/></td>
                </tr>
            </g:each>
        </table>
    </div>
</body>