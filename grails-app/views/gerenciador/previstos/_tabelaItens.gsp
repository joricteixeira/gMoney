<table class="table table-bordered">
    <tr>
        <th>#</th>
        <th>Valor</th>
        <th>Instituição</th>
        <th>Nº Parcela</th>
        <th>Qt. Parcelas</th>
        <th>Fatura</th>
        <th>Terceiro</th>
        <th>Valor terceiro</th>
        <th>Descrição</th>
        <th>SubGrupo</th>
        <th>Consolidado</th>
    </tr>
    <g:each in="${itensFatura}" var="item">
        <tr
            <g:if test="${item.id}"> style="color:#3c763d; background-color: #dff0d8;"  </g:if>
            <g:else> style="color:#a94442; background-color: #f2dede;" </g:else>
        >
            <td>${item?.id}</td>
            <td><g:formatNumber number="${item.valor}" type="currency" currencyCode="BRL"/></td>
            <td>${item?.fatura?.instituicao?.nome}</td>
            <td>${item.numeroParcela}</td>
            <td>${item.quantidadeParcelas}</td>
            <td><g:formatDate date="${item?.fatura?.dataVencimento}" format="dd/MM/yyyy"/></td>
            <td>${item?.terceiro?.nome ?: '-'}</td>
            <td>
                <g:if test="${item.valorTerceiro != null && item.valorTerceiro != 0 }">
                    <g:formatNumber number="${item.valorTerceiro}" type="currency" currencyCode="BRL"/>
                </g:if>
                <g:else>
                    -
                </g:else>
            </td>
            <td>${item.descricao}</td>
            <td>${item.subGrupo?.id}</td>
            <td style="text-align: center;">
                <g:if test="${item.id}"> - </g:if>
                <g:else>
                    <g:link controller="fatura" action="formularioAdicao" params="[itemRecorrente:item.itemFaturaRecorrente.id]">
                        Lançar
                    </g:link>
                </g:else>
            </td>
        </tr>
    </g:each>
</table>