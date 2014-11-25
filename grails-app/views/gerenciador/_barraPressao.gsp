<g:if test="${valorGasto < valorRenda}">
    <div class="progress">
        <div class="progress-bar progress-bar-info progress-bar-striped" style="width: ${((valorGasto*100)/valorRenda)}%">
            <span class="sr-only">%</span>
        </div>
        <div class="progress-bar progress-bar-success" style="width: ${100 - ((valorGasto*100)/valorRenda)}%">
            <span class="sr-only">%</span>
        </div>
    </div>
</g:if>
<g:else>
    <div class="progress">
        <div class="progress-bar progress-bar-info progress-bar-striped" style="width: ${((valorRenda*100)/valorGasto)}%">
            <span class="sr-only">35%</span>
        </div>
        <div class="progress-bar progress-bar-danger" style="width: ${100 - ((valorRenda*100)/valorGasto)}%">
            <span class="sr-only">20%</span>
        </div>
    </div>
</g:else>