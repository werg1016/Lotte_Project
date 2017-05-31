<?
header("Content-Type:text/html;charset=euc-kr");
?>
<html>

	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR"> 
	<title>LOTTE</title></head>
	

	
	<link rel="stylesheet" type="text/css" href="style-layout.css" />
	<body>
		<nav>
		
		</nav>

		<header>
			<center>
				<img src="img/LOTTE.png" alt="LOTTE">
			
			<br>
			Search Engine
			<br>
			<br>
			</center>
		</header>

		<article>
			<center>
				<form method="post" action="./db/Search.php">
					Content : <input type="text" id="search" name="search" size="40"/>
					<br><br>
					<input type="submit" name="submit" value="Search" />
				</form>
			</center>
		</article>

		<footer>
			<hr>
			<center>
			<small>
		
			</small>
			</center>
		</footer>

	</body>
</html>
