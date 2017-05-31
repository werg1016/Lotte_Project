<?
// Set up basic connection
$ftp_server = "127.0.0.1";
$user = "root";
$pass = "123456";
$conn_id = ftp_connect($ftp_server);

$login_result = ftp_login($conn_id, $user, $pass);

// Check connection
if((!$conn_id) || (!$login_result))
{
	echo "Ftp connection has filed!";
	echo "Attempted to connect to $ftp_server for uesr $user";
	die;
}

if( isset($_POST["submit"])) 
{
	if(!empty($_FILES["file"]["name"]))
	{
		$server_file = "lotteimg/" . $_FILES["file"]["name"];
		$file = $_FILES["file"]["tmp_name"];
		$upload = ftp_put($conn_id, $server_file, $file, FTP_BINARY);
		if(!$upload)
			echo "UploadFail!";
		else
		{			
			echo "UploadSuccess!<br>";
			$conn = mysqli_connect("127.0.0.1", "root", "123456", "all_lotte");
			$select_query = "select * from member where recive = 'on'";
			$result = mysqli_query($conn, $select_query);
			$Email="";
			$j = 0;
			while ($row = mysqli_fetch_assoc($result)){
				$Email = $Email ."". $row['Email'] .",";
				$j++;
			}
			$server_file = "C:/" .$server_file;
			$rs = "java -cp mail.jar; mailsend $Email:Parsing:[LOTTE]:Parsing:$server_file";
			exec($rs);
			echo $rs;
		}
	}
}

ftp_quit($conn_id);
?>