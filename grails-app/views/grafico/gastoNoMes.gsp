<head>
    <meta name="layout" content="main" />

    <script type="text/javascript" src="<g:resource dir="js" file="jquery.canvasjs.min.js" />"></script>
    ${grafico}
</head>

<body>
    <div class="container">
        <h1 style="text-align: center;">Gastos de <g:formatNumber number="${data.mes}" minIntegerDigits="2"/>/${data.ano}</h1>
        <div class="row">
            <div class="col-md-1">
                <g:link controller="grafico" action="gastosNoMes" params="[mes:mapaMeses.mesPrev,ano:mapaMeses.anoPrev]">Anterior</g:link>
            </div>
            <div class="col-md-10"></div>
            <div class="col-md-1">
                <g:link controller="grafico" action="gastosNoMes" params="[mes:mapaMeses.mesNext,ano:mapaMeses.anoNext]">Próximo</g:link>
            </div>
        </div>
        <div class="row">
                <div class="col-md-4">
                    <table class="table table-bordered">
                        <tr>
                            <td>Instituição</td>
                            <td>Gastos</td>
                        </tr>
                        <g:each in="${gastosPorInstituicao}" var="it">
                        <tr>
                            <td>${it[1]}</td>
                            <td>
                                <g:formatNumber number="${it[0]}" currencyCode="BRL" type="currency" />
                            </td>
                        </tr>
                        </g:each>
                        <g:each in="${instituicoesSemGastos}" var="instituicao">
                        <tr class="danger">
                            <td>${instituicao.nome}</td>
                            <td>
                                <g:formatNumber number="${0}" currencyCode="BRL" type="currency" />
                            </td>
                        </tr>
                        </g:each>

                    </table>
                </div>
                <div class="col-md-8">
                    <div id="chartContainer" style="height: 300px; width: 100%;"></div>
                </div>
        </div>
    </div>
</body>