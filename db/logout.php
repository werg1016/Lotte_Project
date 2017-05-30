<?
    header("Content-Type:text/html;charset=euc-kr");

    session_start();

    session_unset(); 

    session_destroy(); 
   echo "<script>
           
           
           alert('성공적으로 로그아웃 되었습니다.');
	  location.href='login.html';
          </script>";
    exit;
  


?>
