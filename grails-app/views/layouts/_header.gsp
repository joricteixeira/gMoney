<nav class="navbar navbar-inverse" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Lançamentos<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <g:link controller="fatura" action="formularioAdicao">Novo lançamento</g:link>
                        </li>
                        <li>
                            <g:link controller="fatura" action="listarItensFatura">Todos os lançamentos</g:link>
                        </li>
                    </ul>
                </li>
                <li>
                    <g:link controller="fatura" action="listarFaturas">Consultar Faturas</g:link>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Controle de Receita</a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="">Gastos por Institui&ccedil;&atilde;o</a>
                        </li>
                        <li>
                            <a href="">Gastos No M&ecirc;s</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Configura&ccedil;&otilde;es <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="">Institui&ccedil;&atilde;o</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>