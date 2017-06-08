<%@page import="src.modelo.Estacao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%ArrayList<Estacao> list = (ArrayList<Estacao>) request.getAttribute("estacao");%>
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
            dayName = new Array("Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado");
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
                <section class="wrapper site-min-height">
                    <div class="row mt">
                        <div class="col-lg-12">
                            <div class="form-panel">
                                <h3><i class="fa fa-angle-double-right"></i> SISTEMA DE MARÉ</h3>
                                <br><br>
                                <form class="form-horizontal style-form" action="pdf2.jsp" method="post">
                                    <div class="form-group">
                                        <label class="col-sm-1 col-sm-2 control-label">Estação: </label>
                                        <div class="col-sm-2">
                                            <select class="form-control" id="esta" size="1" name="esta" required>
                                                <option value="">Selecione!</option>
                                            </select>
                                        </div>
                                        <label class="col-sm-1 col-sm-2 control-label">Data: </label>
                                        <div class="col-sm-2">
                                            <input name="campo" id="campo" type="text" class="form-control">
                                        </div>
                                        <label class="col-sm-1 col-sm-2 control-label">Periodo: </label>
                                        <div class="col-sm-2">
                                            <select class="form-control" id="teste" size="1" name="tempo" required>
                                                <option value="">Selecione!</option>
                                                <option value="1">Diario</option>
                                                <option value="2">Semanal</option>
                                                <option value="3">Quinzenal</option>
                                                <option value="4">Mensal</option>
                                            </select>
                                        </div>
                                        <label class="col-sm-1 col-sm-1 control-label"></label>
                                        <div class="col-sm-1">
                                            <input class="form-control" id="enviar" name="enviar" target="_blank" type="submit" value="PDF" style="background-color: #006400; color: #FFF">
                                        </div>
                                    </div>
                                </form>
                                <div class="row mt">
                                    <!-- page start-->
                                    <div id="morris">
                                        <div class="col-lg-12">
                                            <div class="content-panel">
                                                <div class="panel-body">
                                                    <div id="hero-graph2" class="graph"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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
                            $(function () {
                                $("#campo").datepicker({
                                    changeMonth: true,
                                    changeYear: true,
                                    dateFormat: 'dd/mm/yy',
                                    dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado', 'Domingo'],
                                    dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D'],
                                    dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb', 'Dom'],
                                    monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
                                    monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez']
                                });
                            });
        </script>
        <script>
            $(document).ready(function (e) {
                document.getElementById('enviar').disabled = true; // desabilitar
                var json_data1 = (function () {
                    var json3;
                    $.ajax({
                        type: 'GET',
                        url: '/chm/Mares.jsp',
                        async: false,
                        global: false,
                        success: function (data) {
                            $.each(data, function () {
                                $("#esta").append('<option value="' + this.id_estacao + '">' + this.estacao + '</option>')
                            })
                        },
                        error: function () {
                            alert("Ocorreu um erro ao acessar a base de dados 1");
                        }
                    });
                    return json3;
                })();
                $("body").delegate("#teste", "change", function (data) {
                    var teste = $(this).val();
                    var teste2 = $("#campo").val();
                    var teste3 = $("#esta").val();
                    var json_data2 = (function () {
                        var json3;
                        $.ajax({
                            type: 'GET',
                            url: '/chm/Mare.jsp',
                            data: {type: teste, day: teste2, estacao: teste3},
                            async: false,
                            global: false,
                            beforeSend: function () {
                                $("#hero-graph2").html("");
                            },
                            success: function (data) {
                                json3 = data;
                                document.getElementById("enviar").disabled = false; //habilitar
                            },
                            error: function () {
                                alert("Ocorreu um erro ao desenhar o gráfico");
                                $("#hero-graph2").html("");
                            }
                        });
                        return json3;
                    })();
                    var json_date = (function () {
                        var json2;
                        $.ajax({
                            type: 'GET',
                            url: '/chm/Tempo.jsp',
                            async: false,
                            global: false,
                            success: function (data1) {
                                json2 = data1;
                            },
                            error: function () {
                                alert("Ocorreu um erro ao desenhar o gráfico");
                            }
                        });
                        return json2;
                    })();
                    new Morris.Line({
                        element: 'hero-graph2',
                        data: json_data2,
                        parseTime: false,
                        xkey: 'period',
                        ykeys: ['previsto', 'real'],
                        postUnits: ' cm',
                        goalLineColors: ['#FF0000'],
                        labels: json_date,
                        lineColors: ['#0000FF', '#FF0000']

                    });
                });
            });
        </script>
    </body>
</html>
