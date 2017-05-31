<?
   session_start();
 
   header("Content-Type:text/html;charset=euc-kr");
   $conn = mysqli_connect("127.0.0.1", "root", "123456", "all_lotte");
 
   $Search = $_POST['search'];
 
   $output;
   $return_var;
   $output2;
   $return_var2;
 
 
 
exec("java -cp arirang-morph-1.0.3.jar; morpheme.StoSMain .  $Search ." ,$output, $return_var);
 
$select_query = "SELECT * FROM SearchEngine";
$result = mysqli_query($conn, $select_query);

$row = mysqli_fetch_assoc($result);
mysqli_close($conn);
 
print_r($output);
 
 
?>