<head>
   <meta name="layout" content="main" />
</head>

<body>
    <div class="container">

        <h1>${fatura.instituicao.nome}
            <g:link class="btn btn-success"  controller="fatura" action="formularioAdicao" params="[faturaId:fatura.id]">
                + adicionar item
            </g:link>
        </h1>





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
                        <g:formatNumber number="${fatura.valor}" type="currency"/>
                    </div>
                </div>
            </div>

            <div class="col-lg-2 col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Total de terceiros
                        </h3>
                    </div>
                    <div class="panel-body">
                        <g:formatNumber number="${fatura.valorTerceiro}" type="currency"/>
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
                <th>Ações</th>
            </tr>

            <g:each in="${recorrentes}" var="item">
                <tr class="alert-danger">
                    <td></td>
                    <td></td>
                    <td>${item.descricao}</td>
                    <td>1 / 1</td>
                    <td>${item.valor}</td>
                    <td></td>
                    <td></td>
                    <td>
                        <g:link class="btn btn-sm btn-success"  controller="fatura" action="formularioAdicao" params="[itemRecorrente:item.id, faturaId:fatura.id]">
                            Lançar
                        </g:link>
                    </td>
                </tr>
            </g:each>

            <g:each in="${fatura.itensFatura.sort{a,b -> a.dataOrigemCompra < b.dataOrigemCompra ? -1 : 1}}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td><g:formatDate date="${item.dataOrigemCompra}" format="dd/MM/yyyy"/></td>
                    <td>${item.descricao}</td>
                    <td>${item.numeroParcela} / ${item.quantidadeParcelas}</td>
                    <td><g:formatNumber number="${item.valor}" type="currency" currencyCode="BRL" currencySymbol=""/></td>
                    <td>${item.terceiro?.nome}</td>
                    <td><g:formatNumber number="${item.valorTerceiro}" type="currency" currencyCode="BRL" currencySymbol=""/></td>
                    <td>
                        <button class="btn btn-sm btn-warning">Editar</button>
                        <button class="btn btn-sm btn-danger">Remover</button>
                    </td>
                </tr>
            </g:each>
        </table>
    </div>
</body>