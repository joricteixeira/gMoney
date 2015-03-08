<%--
  Created by IntelliJ IDEA.
  User: T-1000
  Date: 15/09/2014
  Time: 22:07
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
    <div class="container">
        <h1>${instituicao.nome}</h1>
        <g:render template="/template/form" />
        <div class="row">
            <div class="col-lg-9" id="dataCalculada">
                <input type="text" readonly="readonly" class="form-control" value="" />
            </div>
            <div class="col-lg-1">
                <g:formRemote name="calcularVencimento" url="[controller:'fatura',action:'calcularDataVencimento']"
                              update="dataCalculada"
                              before="jQuery('#dataObtida').val(jQuery('#dataOrigemCompra').val()); jQuery('#instituicaoObtida').val(jQuery('#instituicaoId').val())">
                    <input type="hidden" id="instituicaoObtida" name="instituicaoObtida">
                    <input type="hidden" id="dataObtida" name="dataObtida">

                    <input type="submit" class="btn btn-default" name="submit" value="Estimar Data">
                </g:formRemote>
            </div>
        </div>
    </div>
</body>
</html>