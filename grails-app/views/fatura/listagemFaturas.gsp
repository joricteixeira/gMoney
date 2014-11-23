<head>
    <meta name="layout" content="main" />
</head>

<body>

<div class="container">
    <div class="well well-lg">
        <g:form controller="fatura" action="listarFaturas">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Data inicial</label>
                        <input type="date" name="dataInicial" class="form-control"/>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Data final</label>
                        <input type="date" name="dataFinal" class="form-control"/>
                    </div>
                </div>
                <div class="col-md-2"></div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Instituições</label>
                        <g:select name="instituicao"
                                  from="${instituicoes}"
                                  noSelection="['':'Todas']"
                                  optionKey="id"
                                  optionValue="nome"
                                  class="form-control"
                                  value="${filtros?.instituicao}"
                        />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-11"></div>
                <div class="col-md-1">
                    <button class="btn bnt-default">Filtrar</button>
                </div>
            </div>

        </g:form>
    </div>

    <div class="list-group">
        <g:each in="${faturas}" var="fatura">
            <a class="list-group-item">
                <h4 class="list-group-item-heading">
                    <g:formatNumber number="${fatura.itensFatura*.valor.sum{it}}" type="currency" />
                </h4>
                <p class="list-group-item-text">
                    <g:formatDate date="${fatura.dataVencimento}" format="dd/MM/yyyy" /> -
                    ${fatura.instituicao.nome}
                </p>
            </a>
        </g:each>
    </div>
</div>

</body>