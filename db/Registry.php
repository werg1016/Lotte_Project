<?

   $conn = mysqli_connect("127.0.0.1", "root", "123456", "all_lotte");
   $NAME = $_POST['NAME'];
   $ID = $_POST['ID'];
   $PW = $_POST['PW'];

   $P_NUMBER = $_POST['P_NUMBER'];
   $ID_CARD = $_POST['IDCARD'];
   $Email = $_POST['EMAIL'];
   $Address = $_POST['ADDRESS'];
   $Recive = $_POST['RECIVE'];
   $time = date("Y-m-d-H-i-s");
    
   
   
   $select_query = "select * from member where ID='" .$ID . "'";
   $result_set = mysqli_query($conn, $select_query);
   $row = mysqli_fetch_assoc($result_set);

  if($row['ID'] == $ID){
     
    echo "<script>
           alert('해당 ID는 이미 존재합니다.');
 	  history.back();
          </script>";
    exit;
   
   }
   else{
   	$insert_query = "INSERT INTO member VALUES('" .$ID . "',HEX(AES_ENCRYPT('" . $PW . "', 'rlagmlwls')),'" . $NAME . "','" . $P_NUMBER . "','" . $ID_CARD . "','" . $Email . "','" . $Address  . "','" . $Recive . "','" . $time . "');";
   	$result = mysqli_query($conn, $insert_query);
    $rs = "java -cp mail.jar; mailsender $Email:Parsing:[LOTTE　AIO　회원가입안내]:Parsing:.$NAME.　회원가입을　진심으로　축하드립니다.";
    $rs = iconv("UTF-8","EUC-KR",$rs);
	exec($rs);
    echo $rs;
   }	


   

   mysqli_close($conn);

?>
