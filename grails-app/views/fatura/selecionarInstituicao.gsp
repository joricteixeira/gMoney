<head>
    <meta name="layout" content="main" />
</head>

<body>
    <div class="container">
        <h1>Selecionar Instituição</h1>
        <div class="well">
            <g:form controller="fatura" action="formularioAdicao">
                <div class="row">
                    <div class="col-md-9">
                        <g:select
                            name="instituicao"
                            from="${instituicoes}"
                            optionKey="id"
                            optionValue="nome"
                            class="form-control"
                        />
                    </div>
                    <div class="col-md-1"></div>
                    <div class="col-md-2">
                        <button class="btn btn-default form-control">Avançar</button>
                    </div>
                </div>
            </g:form>
        </div>
    </div>
</body>