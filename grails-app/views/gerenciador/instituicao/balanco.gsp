<head>
    <meta name="layout" content="main" />

    <script type="text/javascript" src="<g:resource dir="js" file="jquery.canvasjs.min.js" />"></script>
    ${grafico}
</head>

<body>
<div class="container">
    <h1 style="text-align: center;">Balanço de ${instituicao.nome}</h1>
    <div class="well">
        <div class="row">
            <g:form controller="grafico" action="balancoInstituicao">
                <div class="col-lg-10">
                    <g:select class="form-control" name="id" from="${instituicoes}" optionKey="id" optionValue="nome" noSelection="['':'Selecione']"/>
                </div>
                <div class="col-lg-2">
                    <button class="btn btn-default">Pesquisar</button>
                </div>
            </g:form>
        </div>

    </div>
    <div class="row">
        <div class="col-md-12">
            <div id="${grafico.nomeDiv}" style="height: 300px; width: 100%;"></div>
        </div>
    </div>
</div>
</body>