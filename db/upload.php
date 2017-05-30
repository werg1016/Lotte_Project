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

			#exec("python androguard\androapp.py -r \"C:/APM_Setup/ftp/apk/" . $_FILES["file"]["name"] . "\"", $output, $return_var);
		
		}
	}
}

ftp_quit($conn_id);
?>