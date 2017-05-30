<?
   session_start();
   $conn = mysqli_connect("127.0.0.1", "root", "123456", "all_lotte");
   $ID = $_POST['ID'];
   $PW = $_POST['PW'];
	
   $select_query = "SELECT cast(AES_DECRYPT(UNHEX(Password), 'rlagmlwls')as char) Password FROM member where id='".$ID."'";
   $result_set = mysqli_query($conn, $select_query);
   $row = mysqli_fetch_assoc($result_set);
   echo $select_query;
  if($row['Password'] == $PW){
    
    $_SESSION["ID"] = $row['ID'];
    $_SESSION["NAME"] = $row['Name'];
 
    echo "<script>
           alert('로그인에 성공하셨습니다.');
			location.href='../base.php';
          </script>";
    exit;
   
   }
   else{
   echo "<script>
           alert('아이디/비밀번호 오류');
           history.back();
          </script>";
    exit;
   }	


   

   mysqli_close($conn);

?>