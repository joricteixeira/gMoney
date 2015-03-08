<head>
    <meta name="layout" content="main">
</head>
<body>
    <div class="container">
        <div class="well">
        <div class="row">
            <g:formRemote name="encontrarFatura" url="[controller:'pagamento',action:'encontrarFatura']" update="faturaSelecionada">
            <div class="col-lg-8">
                <g:select class="form-control" name="instituicaoSelecionada" id="instituicaoSelecionada" from="${instituicoes}" optionKey="id" optionValue="nome" noSelection="['0':'Selecione']"/>
            </div>
            <div class="col-lg-2">
                <input type="date" class="form-control" name="dataSelecionada" id="dataSelecionada">
            </div>
            <div class="col-lg-2">
                <input type="submit" name="submit" class="btn btn-default" value="Encontrar Fatura">
            </div>
            </g:formRemote>
        </div>
        </div>

        <g:form controller="pagamento" action="processarAdicao">
            <div class="form-group" id="faturaSelecionada">
                <label>Fatura para Pagar</label>
                <input type="text" class="form-control" readonly="readonly" name="faturaLabel" placeholder="Selecione uma Fatura para Pagar" />
            </div>

            <div class="form-group">
                <label>Data do pagamento</label>
                <input type="date" id="dataPagamento" name="dataPagamento" class="form-control"/>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon">R$</div>
                    <g:textField name="valorPagamento" class="form-control" placeholder="Valor do Pagamento"/>
                </div>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-default" style="float:right;">Salvar</button>
            </div>
        </g:form>
    </div>
</body>