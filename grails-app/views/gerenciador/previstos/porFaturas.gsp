<head>
    <meta name="layout" content="main" />
</head>

<body>
<div class="container">
    <div class="well well-lg">
        LISTAR

    </div>
    <div class="row">
        <div class="col-md-1">
            <g:link controller="gerenciador" action="faturasNoMes" params="[mes:mapaMeses.mesPrev,ano:mapaMeses.anoPrev]">Anterior</g:link>
        </div>
        <div class="col-md-10"></div>
        <div class="col-md-1">
            <g:link controller="gerenciador" action="faturasNoMes" params="[mes:mapaMeses.mesNext,ano:mapaMeses.anoNext]">Próximo</g:link>
        </div>
    </div>

    <g:render template="cabecalhoPrevisao" model="[valorReceita: 4000, valorDespesa:(faturas*.itensFatura*.valor.sum{it}.sum{it} + itensFatura*.valor.sum{it})]"/>
    <g:barraPressaoPorMes mes="${mapaMeses.mes}" ano="${mapaMeses.ano}" />

    <g:render template="previstos/tabelaItens"/>
    <g:render template="previstos/tabelaFaturas" />
</div>
</body>