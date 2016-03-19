<?php
	require 'mysql_connect.php';
	$randnumber = $_GET['randnumber'];
	//查询数据库中的usemame是否为空
	$result = mysql_query("select * from login_record where randnumber='$randnumber'");
	$row = mysql_fetch_array($result);
	if ($row['username'] != "")
	//不为空，说明用户执行了扫码操作
		echo "true";
	else
		echo "false";
?>