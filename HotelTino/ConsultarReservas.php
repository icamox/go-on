<?php
session_start();
 ?>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Consultar Reserva</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="dashboard.css" rel="stylesheet">


  </head>

  <body>

    <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="">Hotel...</a>
    </nav>
    <div class="container-fluid">

      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
              <hr>
              <li class="nav-item">
                <a class="nav-link" href="ConsultarReservas.php">
                  Consultar Reservas
                </a>
              </li>
              <hr>
              <li class="nav-item">
                <a class="nav-link" href="Reservas.php">
                  Realizar Reserva
                </a>
              </li>


            </ul>
          </div>
        </nav>
      </div>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
          <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3">
            <h1 class="h2">Consultar reservas</h1>
          </div>
            <div class="col-md-8 order-md-1">


                <div class="table-responsive">
                  <table class="table table-striped table-sm">

                    <?php

                        include ('ListarReservas.php');

                     ?>

                  </table>
                </div>

            </div>
            <br>

                </div>


              <hr class="mb-4">
            </div>
            </main>
          </div>
    </div>

    <style media="screen">
      main{
        margin-top: -8em;
      }
    </style>


  </body>
</html>
