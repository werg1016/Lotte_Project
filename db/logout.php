<?
    header("Content-Type:text/html;charset=euc-kr");

    session_start();

    session_unset(); 

    session_destroy(); 
   echo "<script>
           
           
           alert('���������� �α׾ƿ� �Ǿ����ϴ�.');
	  location.href='login.html';
          </script>";
    exit;
  


?>
