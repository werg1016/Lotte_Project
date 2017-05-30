<?
   header("Content-Type:text/html;charset=euc-kr");
   $conn = mysqli_connect("127.0.0.1", "root", "123456", "all_lotte");
   $NAME = $_POST['NAME'];
   $ID = $_POST['ID'];
   $PW = $_POST['PW'];

   $P_NUMBER = $_POST['P_NUMBER'];
   $ID_CARD = $_POST['ID_CARD'];
   $Email = $_POST['EMAIL'];

   $select_query = "select * from member where ID='" .$ID . "'";
   $result_set = mysqli_query($conn, $select_query);
   $row = mysqli_fetch_assoc($result_set);

  if($row['ID'] == $ID){
     
    echo "<script>
           alert('�ش� ID�� �����մϴ�');
 	  history.back();
          </script>";
    exit;
   
   }
   else{
	$FileName = "../images/Profile.png";
	$handle = fopen($FileName,"rb");
	$content = addslashes(fread($handle,filesize($FileName)));
	fclose($handle);
          $insert_query = "INSERT INTO member VALUES('" .$ID . "','" . $PW . "','" . $NAME . "','" .$Email . "','" . $P_NUMBER . "','" . $ID_CARD . "','" . $content  . "');";
           $result = mysqli_query($conn, $insert_query);

   echo "<script>
           
           
           alert('ȸ�����Կ� �����ϼ̽��ϴ�.');
	  location.href='../moris.html';
          </script>";
    exit;
   }	


   

   mysqli_close($conn);

?>
