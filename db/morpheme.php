<?
   session_start();
 
   $conn = mysqli_connect("127.0.0.1", "root", "123456", "all_lotte");
 
   $Search = $_POST['search'];
 
   $output;
   $return_var;
   $output2;
   $return_var2=0;

$select_query = "SELECT * FROM SearchEngine";
$result = mysqli_query($conn, $select_query);

$i=-1;
$f = 0;

while ($row = mysqli_fetch_assoc($result)){
$i = $i+1;
$data[$i] = iconv("UTF-8","EUC-KR",$row['data']);
}

for($j=0; $j <= $i; $j++){
	exec("java -cp arirang-morph-1.0.3.jar; morpheme.StoSMain .  $data[$j] ." ,$output2, $return_var2);
	$data[$j] = iconv("EUC-KR","UTF-8",$output2[$j]);
}


for($j=0; $j <= $i; $j++){
	$f=$j+1;
	$insert_query = "INSERT INTO morpheme VALUES(" .$f . ",'" . $data[$j] . "');";
	$result_query = mysqli_query($conn, $insert_query);
}

mysqli_close($conn);

print_r($data);
print_r($i);
?>