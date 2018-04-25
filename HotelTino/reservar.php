<?php
	include ('Conexion.php');

		$cedula=$_POST['cedula'];
		$personas=$_POST['cper'];
		$dias=$_POST['cdias'];
		$numhab=$_POST['numb'];
		$fecin=$_POST['fecini'];
		$fecfin=$_POST['fecfin'];


				$query="SELECT per_id,per_cedula FROM persona WHERE per_cedula='$cedula'";
				$result=mysqli_query($conexion,$query);
				$query2="SELECT hab_id,hab_numero FROM habitacion WHERE hab_numero='$numhab'";
				$result2=mysqli_query($conexion,$query2);

				if($fila=mysqli_fetch_array($result)){
					if($fila2=mysqli_fetch_array($result2)){
								$sql="INSERT INTO reserva VALUES (".$fila2['hab_id'].",".$fila['per_id'].",'$fecin','$fecfin','$personas')";
								$sql2="UPDATE habitacion set hab_estado= 'Ocupado' WHERE hab_id=".$fila2['hab_id'];
								$res1=$conexion->query($sql);
								$res2=$conexion->query($sql2);
								echo "<script language='javascript'>alert('Registro Exitoso');</script>";
							}
							else{
								echo "<script>alert('Error al ingresar los datos')</script>".mysqli_connect_error();
							}
					}else{
						echo "<script>alert('Error al ingresar los datos')</script>".mysqli_connect_error();
					}

   header('location:reservas.php');

?>
