<?
   session_start();
 
   header("Content-Type:text/html;charset=euc-kr");
   $conn = mysqli_connect("127.0.0.1", "root", "123456", "all_lotte");
 
   $Search = $_POST['search'];
 
   $output;
   $return_var;
   $output2;
   $return_var2;
 
   $path_dir = "../Server/";
   if(!is_dir($path_dir)){
   	mkdir($path_dir, 0777);
   }
   

exec("java -cp arirang-morph-1.0.3.jar; morpheme.StoSMain .  $Search ." ,$output, $return_var);
 
$filename = $path_dir . "Search.txt";
$fp = fopen($filename,"w");
fwrite($fp, $output[0]);
fclose($fp);


$select_query = "SELECT * FROM morpheme";
$result = mysqli_query($conn, $select_query);

$i=-1;
$data = "";
while ($row = mysqli_fetch_assoc($result)){
	$i = $i+1;
	$data = $data ."". iconv("UTF-8","EUC-KR",$row['morpheme']) ."////";
}


$filename2 = $path_dir . "Target.txt";
$fp2 = fopen($filename2,"w");
fwrite($fp2, $data);
fclose($fp2);

$f = 0;

	exec("python similarity/Test_Similarity.py " . $filename . " " . $filename2, $output3, $return_var3);

for($i=0; $i<count($output3); $i++){
	echo $output3[$i];
	echo '<br>';
}



?>