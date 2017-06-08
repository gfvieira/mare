<%@page import="src.modelo.Estacao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String temp = (String) request.getAttribute("mensagem");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Dashboard">
        <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

        <title>-= CHM =-</title>
        <link rel="shortcut icon" href="assets/images/gt_favicon.png">
        <!-- Bootstrap core CSS -->
        <link href="assets2/css/bootstrap.css" rel="stylesheet">
        <!--external css-->
        <link href="assets2/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link rel="stylesheet" href="assets2/css/morris-0.4.3.min.css">  
        <link rel="stylesheet" href="assets2/jquery-ui/jquery-ui.css">  

        <!-- Custom styles for this template -->
        <link href="assets2/css/style.css" rel="stylesheet">
        <link href="assets2/css/style-responsive.css" rel="stylesheet">

        <script language=javascript type="text/javascript">
            dayName = new Array("Domingo", "Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado");
            monName = new Array("janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro");
            now = new Date;
        </script>
        <script type="text/javascript">
            function time()
            {
                today = new Date();
                h = today.getHours();
                m = today.getMinutes();
                s = today.getSeconds();
                document.getElementById('txt').innerHTML = h + ":" + m + ":" + s;
                setTimeout('time()', 500);
            }
        </script>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body onload="time()">

        <section id="container" >

            <!-- **********************************************************************************************************************************************************
            TOP BAR CONTENT & NOTIFICATIONS
            *********************************************************************************************************************************************************** -->
            <!--header start-->
            <header class="header black-bg">
                <!--                <div class="sidebar-toggle-box">
                                    <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
                                </div>-->
                <!--logo start-->
                <a href="#" class="logo"><b>CHM || 
                        <script language=javascript type="text/javascript">
                            document.write(dayName[now.getDay() ] + ", " + now.getDate() + " de " + monName [now.getMonth() ] + " de " + now.getFullYear());
                        </script>
                    </b></a>
                <!--logo end-->
                <div class="top-menu">
                    <ul class="nav pull-right top-menu">

                        <li>
                        </li>
                    </ul>
                </div>

            </header>
            <!--header end-->

            <!-- **********************************************************************************************************************************************************
            MAIN SIDEBAR MENU
            *********************************************************************************************************************************************************** -->
            <!--sidebar start-->
            <!--            <aside>
                            <div id="sidebar"  class="nav-collapse ">
                                <ul class="sidebar-menu" id="nav-accordion">
                                    <h5 class="centered">CHM</h5>
                                    <h5 class="centered"> 
                                        <script language=javascript type="text/javascript">
                                            document.write(dayName[now.getDay() ] + ", " + now.getDate() + " de " + monName [now.getMonth() ] + " de " + now.getFullYear());
                                        </script>
                                    </h5>
                                    <h5 class="centered"><div id="txt"></div></h5>
                                    <li class="mt">
                                        <a class="active" href="#">
                                            <i class="fa fa-dashboard"></i>
                                            <span>Principal </span>
                                        </a>
                                    </li>
                                    <li class="sub-menu">
                                        <a href="javascript:;" >
                                            <i class=" fa fa-bar-chart-o"></i>
                                            <span>Estação</span>
                                        </a>
                                        <ul class="sub">
                                            <li>
                                                <select>
                                                    <option value="">Selecione!</option>
                                                    <option value="1">Diario</option>
                                                    <option value="2">Uma Semana</option>
                                                    <option value="3">Mensal</option>
                                                </select>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </aside>-->

            <!-- **********************************************************************************************************************************************************
            MAIN CONTENT
            *********************************************************************************************************************************************************** -->
            <!--main content start-->
            <section id="main-content">
                <section class="wrapper">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-panel">
                                <%
                                            if (temp != null) {
                                                if (temp.equals("NO")) {
                                        %>
                                <div class="alert alert-danger centered"><b>erro!</b> Dados não cadastrado, ou já consta em nosso sistema!</div>
                                <%
                                } else {
                                %>
                                <div class="alert alert-success" centered><b>Sucesso!</b> Dados Cadastrado!</div>      				
                                <%
                                        }
                                    }
                                %>
                                <h4><i class="fa fa-angle-double-right"></i> SISTEMA DE MARÉ</h4>
                                <h6> Selecione a estação e envie o arquivo</h6>
                                <form class="form-horizontal style-form">
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Estação</label>
                                        <div class="col-sm-3">
                                            <select class="form-control" size="1" name="estacao" id="estacao">
                                                <option value="">Selecione!</option>
                                            </select>
                                            <a href="javascript:void(0)" id="teste" data-toggle="modal" data-target="#myModalEstacao" style="font-size: 12px; color: red;"><i class="fa fa-plus" style="font-size: 12px; color: black"></i> Cadastrar nova estação</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-panel">
                                <h5><i class="fa fa-angle-double-down"></i> ENVIO DE ARQUIVO MARÉ OBSERVADA</h5>
                                <form class="form-horizontal style-form" action="Upload.jsp" method="post" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label class="col-sm-1 col-sm-2 control-label">Arquivo:</label>
                                        <div class="col-sm-4">
                                            <input type="file" class="" id="file" name="file" required>
                                        </div>
                                        <input type="hidden" class="form-control" id="type" name="type" value="1">
                                        <input type="hidden" class="form-control" id="idEstacao1" name="idEstacao" required>
                                        <button type="submit" class="btn btn-theme">Enviar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-panel">
                                <h5><i class="fa fa-angle-double-down"></i> ENVIO DE ARQUIVO MARÉ PREVISTA-PRETERITO</h5>
                                <form class="form-horizontal style-form" action="Upload.jsp" method="post" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label class="col-sm-1 col-sm-2 control-label">Arquivo:</label>
                                        <div class="col-sm-4">
                                            <input type="file" class="" id="file" name="file" required>
                                        </div>
                                        <input type="hidden" class="form-control" id="type" name="type" value="2">
                                        <input type="hidden" class="form-control" id="idEstacao2" name="idEstacao" required>
                                        <button type="submit" class="btn btn-theme">Enviar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-panel">
                                <h5><i class="fa fa-angle-double-down"></i> ENVIO DE ARQUIVO MARÉ PREVISTA-FUTURO</h5>
                                <form class="form-horizontal style-form" action="Upload.jsp" method="post" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label class="col-sm-1 col-sm-2 control-label">Arquivo:</label>
                                        <div class="col-sm-4">
                                            <input type="file" class="" id="file" name="file" required>
                                        </div>
                                        <input type="hidden" class="form-control" id="type" name="type" value="3">
                                        <input type="hidden" class="form-control" id="idEstacao3" name="idEstacao" required>
                                        <button type="submit" class="btn btn-theme">Enviar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="modal fade bs-example-modal-sm" id="myModalEstacao" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Cadastro de Estação</h4>
                                </div>
                                <div class="modal-body">
                                    <form  class="form login" name="form1">
                                        <div class="login-wrap">
                                            Entre com o nome da estação
                                            <input type="text" class="form-control" id="estacaoModal" name="estacaoModal" required>
                                            <hr>
                                            <input class="form-control" type="button" value="cadastrar" id="ButtonEstacao" style="background-color: #32CD32; border-color: #228B22; color: #FFF"> 
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </section><!-- /MAIN CONTENT -->

            <!--main content end-->
            <!--footer start-->
            <!--footer end-->
        </section>

        <!-- js placed at the end of the document so the pages load faster -->
        <script src="assets2/js/jquery-3.1.1.min.js"></script>
        <script src="assets2/js/bootstrap.min.js"></script>
        <script class="include" type="text/javascript" src="assets2/js/jquery.dcjqaccordion.2.7.js"></script>
        <script src="assets2/js/jquery.scrollTo.min.js"></script>
        <script src="assets2/js/jquery.nicescroll.js" type="text/javascript"></script>
        <!--common script for all pages-->
        <script src="assets2/js/chm/raphael-min.js"></script>
        <script src="assets2/js/chm/morris-0.4.3.min.js"></script>
        <script src="assets2/js/common-scripts.js"></script>
        <script type="text/javascript" src="assets2/jquery-ui/jquery-ui.js"></script>
        <script>
                            $(document).ready(function (e) {
                                var json_data1 = (function () {
                                    var json3;
                                    $.ajax({
                                        type: 'GET',
                                        url: '/chm/Mares.jsp',
                                        async: false,
                                        global: false,
                                        success: function (data) {
                                            $.each(data, function () {
                                                $("#estacao").append('<option value="' + this.id_estacao + '">' + this.estacao + '</option>')
                                            })
                                        },
                                        error: function () {
                                            alert("Ocorreu um erro ao acessar a base de dados!!!");
                                        }
                                    });
                                    return json3;
                                })();
                                $("body").delegate("#estacao", "change", function (data) {
                                    var teste1 = $("#estacao").val();
                                    document.getElementById("idEstacao1").value = teste1;
                                    document.getElementById("idEstacao2").value = teste1;
                                    document.getElementById("idEstacao3").value = teste1;
                                });
                                $('#ButtonEstacao').click(function () {
                                    var teste2 = $("#estacaoModal").val();
                                    $.ajax({
                                        type: 'GET',
                                        url: '/chm/CadEstacao.jsp',
                                        data: {estacao: teste2},
                                        async: false,
                                        global: false,
                                        beforeSend: function () {
                                            document.getElementById("estacao").innerHTML = "";
                                            $('#myModalEstacao').modal('hide');
                                            $("#estacao").append('<option value="">Selecione!</option>');
                                        },
                                        success: function (data) {
                                            //limpar o campo da modal
                                            $.each(data, function () {
                                                $("#estacao").append('<option value="' + this.id_estacao + '">' + this.estacao + '</option>')
                                            });
                                        },
                                        error: function () {
                                            alert("Ocorreu um erro ao acessar a base de dados 3");
                                        }
                                    });
                                });
                            });
        </script>
    </body>
</html>
