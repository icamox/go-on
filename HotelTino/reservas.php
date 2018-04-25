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

    <title>Realizar Reserva</title>

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
            <h1 class="h2">Reservar</h1>
          </div>
            <div class="col-md-8 order-md-1">


              <form action="reservar.php" method="POST" class="needs-validation" name="form1">

                <div class="col-md-6 mb-3">
                  <label for="cedula">Cedula</label>
                  <div class="input-group">
                    <input type="number" class="form-control" id="cedula" name="cedula" placeholder="Cedula del cliente" required>
                    <div class="invalid-feedback" style="width: 100%;">
                      Your ID is required.
                    </div>
                  </div>
                </div>

                <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="cper">Cantidad de personas</label>
                  <div class="input-group">
                    <input type="number" class="form-control" id="cper" name="cper" placeholder="Cantidad de personas" required>
                    <div class="invalid-feedback" style="width: 100%;">
                      This information is required.
                    </div>
                  </div>
                </div>


                <div class="col-md-6 mb-3" id="numhab">
                  <label for="numb">Habitaciones disponibles</label>
                  <select class="custom-select d-block w-100" id="numb" name="numb" required>
                    <?php include ('NumeroDeHabitacion.php'); ?>
                    </select>
                </div>
            </div>


                <div class="row">
                  <div class="col-md-6 mb-3">
                    <label for="fecini">Fecha de inicio</label>
                    <input type="date" class="form-control" id="fecini" name="fecini">
                  </div>

                  <div class="col-md-6 mb-3">
                    <label for="fecfin">Fecha de fin</label>
                    <input type="date" class="form-control" id="fecfin" name="fecfin">
                  </div>
                </div>


            </div>
            <br>

                <button class="col-md-8 btn btn-primary btn-lg btn-block" type="submit">Registrar</button>
                </div>

              </form>
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
