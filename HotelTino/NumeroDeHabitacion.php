
  <!--<option>Choose...</option>-->
    <?php
      include ('Conexion.php');
      $id=$_POST['id_estado'];

      print_r($id);
      $query="SELECT * FROM habitacion";// INNER JOIN reserva WHERE res_estado!='Ocupado' AND reserva.hab_id=habitacion.hab_id";
      echo($query);
      $result=mysqli_query($conexion,$query);
      if($result)
        while($fila=mysqli_fetch_array($result)){
            $valor2=$fila['hab_numero'];
            echo "<option value=".$valor2.">".$valor2."</option>";
        }
     ?>
