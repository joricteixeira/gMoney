<h3>Previsto para ${mes}/${ano}:
    <strong>
        <g:formatNumber type="currency" currencyCode="BRL" number="${valorDespesa - valorTerceiro}" />
        + (<g:formatNumber type="currency" currencyCode="BRL" number="${valorTerceiro}" />)
        |
        <g:formatNumber type="currency" currencyCode="BRL" number="${valorReceita}" />

    </strong>
</h3>