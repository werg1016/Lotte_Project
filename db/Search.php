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
while ($row = mysqli_fetch_assoc($result)){
	$i = $i+1;
	$data[$i] = iconv("UTF-8","EUC-KR",$row['morpheme']);
	
}

$f = 0;
for($j=0; $j<=$i; $j++){
	$f = $j + 1;
	exec("python similarity/OtoO_Similarity.py " . $filename . " " . $data[$j], $output3, $return_var3);
	$insert_query = "INSERT INTO resulut VALUES(" .$f . "," . $output3[$j] . ");";
	$result_query = mysqli_query($conn, $insert_query);
}

$select_query = "SELECT resulut.index,data FROM `resulut`,`searchengine` where result > 15 and searchengine.index = resulut.index order by result desc;";
$result_query = mysqli_query($conn, $select_query);

while($row = mysqli_fetch_assoc($result_query)){
	echo iconv("UTF-8","EUC-KR",$row['data']);
	echo '<br>';
}


$trun_query = "truncate table resulut";
$result_query = mysqli_query($conn, $trun_query);
mysqli_close($conn);

?>