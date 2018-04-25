<?php
				include ("Conexion.php");

        $sql=("SELECT per_nombre,per_apellido,hab_numero,res_persona,res_fecin,res_fecfin,per_cedula FROM persona INNER JOIN reserva INNER JOIN habitacion WHERE persona.per_id=reserva.per_id AND reserva.hab_id=habitacion.hab_id ORDER BY res_fecin");

				$result = mysqli_query($conexion,$sql);

				echo "<table style='color:#000;' border='3';  class='table table-hover';>";
					echo "<tr class='warning' style='color:#000;'>";
						echo "<td><strong>Nombre</strong></td>";
            echo "<td><strong>Apellido</strong></td>";
            echo "<td ><strong>N° Habitacion</strong></td>";
            echo "<td ><strong>Cantidad Personas</strong></td>";
						echo "<td><strong>Fecha Inicio</strong></td>";
						echo "<td><strong>Fecha Fin</strong></td>";
					echo "</tr>";

          while($resultado=mysqli_fetch_array($result)){
				  	echo "<tr class='success' style='color:#000;'>";
				    	echo "<td>$resultado[0]</td>";
				    	echo "<td>$resultado[1]</td>";
				    	echo "<td>$resultado[2]</td>";
				    	echo "<td>$resultado[3]</td>";
				    	echo "<td>$resultado[4]</td>";
              echo "<td>$resultado[5]</td>";
				}
				echo "</table>";

?>
