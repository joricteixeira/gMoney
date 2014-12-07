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
            <g:link controller="gerenciador" action="itensNoMes" params="[mes:mapaMeses.mesPrev,ano:mapaMeses.anoPrev]">Anterior</g:link>
        </div>
        <div class="col-md-10"></div>
        <div class="col-md-1">
            <g:link controller="gerenciador" action="itensNoMes" params="[mes:mapaMeses.mesNext,ano:mapaMeses.anoNext]">Próximo</g:link>
        </div>
    </div>
    <h3>Previsto para ${mes}/${ano}: <strong><g:formatNumber type="currency" currencyCode="BRL" number="${itensFatura*.valor.sum{it}}" /> | <g:formatNumber type="currency" currencyCode="BRL" number="${valorRenda}" /></strong></h3>
    <g:barraPressaoPorMes mes="${mapaMeses.mes}" ano="${mapaMeses.ano}" />

    <g:render template="previstos/tabelaItens" />
</div>
</body>