<?php
    session_start();
    include 'Conexion.php';

    $contra = $_POST['contra'];
    $usuario = $_POST['usuario'];
    $trab=0;
    $cliente=1;

    $sql = "SELECT * FROM persona WHERE per_cedula ='$usuario' AND per_contra = '$contra' AND per_rol = '$trab'";
    $result = mysqli_query($conexion, $sql);
    $filas = mysqli_num_rows($result);
    //************************************************************************
    //Cliente
    //************************************************************************
    $sql2 = "SELECT * FROM persona WHERE per_cedula ='$usuario' AND per_contra = '$contra' AND per_rol = '$cliente'";
    $result2 = mysqli_query($conexion, $sql2);
    $filas2 = mysqli_num_rows($result2);
    //************************************************************************
    if($filas){ //Si la el resultado de filas es igual al id, contra y rol 0 entonces es trabajador y envia a admin.php
          $_SESSION['usuario']=$filas['per_cedula'];
          $_SESSION['contra']=$filas['per_contra'];
            echo "<script>alert('Inicio valido')</script>";
            echo "<script>location.href='admin.php'</script>";
        }else if($filas2){//Si la el resultado de filas es igual al id, contra y rol 1 entonces es trabajador y envia a reservas.php
              $_SESSION['usuario']=$filas2['per_cedula'];
              $_SESSION['contra']=$filas2['per_contra'];
                echo "<script>alert('Inicio valido')</script>";
                echo "<script>location.href='reservas.php'</script>";
            }else{//Si la consulta es error ya sea al ingresar usuaio o contraseña, enviara este mensaje y de nuevo a la misma pagina]
                echo "<script>alert('Error: Verificar datos ingresados')</script>";
                echo "<script>location.href='log_in.php'</script>";
        }

      isset($_SESSION['usuario']);
 ?>
