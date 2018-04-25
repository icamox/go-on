<?php

  $conexion = new mysqli("localhost","root","","hotel");
  if(mysqli_connect_error()){
    echo "Error: ".mysqli_connect_error();
    exit();
  }

 ?>
