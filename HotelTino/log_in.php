<?php
session_start();
?>

<!DOCTYPE html>
<html lang="es">
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <link href="bootstrap.min.css" rel="stylesheet">
      <link href="floating-labels.css" rel="stylesheet">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <title>Inicio De Sesion</title>
    </head>

    <body>

      <form class="form-signin" method="POST" action="login.php" enctype="application/x-www-form-urlencoded">
          <center><h1>Inicio de sesión</h1></center>

        <div class="form-label-group">
          <input type="text" id="usuario" name="usuario" value="1234" class="form-control">
          <label for="usuario">Usuario</label>
        </div>

        <div class="form-label-group">
          <input type="password" id="contra" name="contra" value="123" class="form-control">
          <label for="usuario">Contraseña</label>
        </div>


        <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar Sesion</button>
      </form>

      <style media="screen">
        body{
          background: #e9dbbe;
        }
        h1{
          color: #ff2000;
          margin-bottom: 15%;
        }
      </style>

    </body>

</html>
