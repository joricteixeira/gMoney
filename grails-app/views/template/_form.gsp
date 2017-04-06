<head>
    <meta name="layout" content="main" />
</head>
<body>
    <g:if test="fatura">
        <h3><g:formatDate date="${fatura?.dataVencimento}" format="dd/MM/yyyy"/> </h3>
    </g:if>
    <g:form controller="fatura" action="processarAdicao" >
        <g:hiddenField name="itemRecorrente" value="${itemRecorrente?.id}"/>

        <div class="form-group">
            <g:hiddenField name="instituicaoId" id="instituicaoId" value="${instituicao?.id}" />
            <g:hiddenField name="faturaId" id="faturaId" value="${fatura?.id}" />
        </div>

        <div class="form-group">
            <label>Descrição da transação</label>
            <g:textField name="descricao" value="${itemRecorrente?.descricao ?: (itemFatura?.descricao ?: '')}" class="form-control"/>
        </div>

        <div class="form-group">
        <select name="numeroParcela" class="btn btn-default btn-sm dropdown-toggle" required>
            <option value="1">1&ordf; Parcela</option>
            %{--<option value="2">2&ordf; Parcela</option>
            <option value="3">3&ordf; Parcela</option>
            <option value="4">4&ordf; Parcela</option>
            <option value="5">5&ordf; Parcela</option>
            <option value="6">6&ordf; Parcela</option>
            <option value="7">7&ordf; Parcela</option>
            <option value="8">8&ordf; Parcela</option>
            <option value="9">9&ordf; Parcela</option>
            <option value="10">10&ordf; Parcela</option>
            <option value="11">11&ordf; Parcela</option>
            <option value="12">12&ordf; Parcela</option>--}%
        </select>
        de
        <select name="quantidadeParcelas" class="btn btn-default btn-sm dropdown-toggle" required>
            <option value="1">1 parcela</option>
            <option value="2">2 parcelas</option>
            <option value="3">3 parcelas</option>
            <option value="4">4 parcelas</option>
            <option value="5">5 parcelas</option>
            <option value="6">6 parcelas</option>
            <option value="7">7 parcelas</option>
            <option value="8">8 parcelas</option>
            <option value="9">9 parcelas</option>
            <option value="10">10 parcelas</option>
            <option value="11">11 parcelas</option>
            <option value="12">12 parcelas</option>
        </select>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">R$</div>
                <g:textField name="valor" value="${itemRecorrente?.valor ? itemRecorrente.valor.toString().replace(".",",") : (itemFatura?.valor?.replace(".",",") ?: '')}" class="form-control" placeholder="Valor da transação"/>
            </div>
        </div>

        <div class="form-group">
            <label>Data da transação</label>
            <input type="date" id="dataOrigemCompra" name="dataOrigemCompra" class="form-control"/>
        </div>

        <g:if test="${!terceiro.isEmpty()}">
        <div class="form-group">
            <label>Terceiro</label>
            <g:select name="terceiroId"
                      from="${terceiro}"
                      optionValue="nome"
                      optionKey="id"
                      noSelection="['':'Selecione']"
                      class="form-control"
            />
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">R$</div>
                <g:textField name="valorTerceiro" value="${itemFatura?.valorTerceiro?: ''}" class="form-control" placeholder="Valor do terceiro"/>
            </div>
        </div>
        </g:if>

        <div class="form-group">
        <button type="submit" class="btn btn-default" style="float:right;">Salvar</button>
        </div>

    </g:form>
</body>