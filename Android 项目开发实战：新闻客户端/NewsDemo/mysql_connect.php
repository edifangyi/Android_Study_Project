<?php

	/**
	 * 连接数据库
	 */
	  
	$con = mysql_connect("127.0.0.1:3366", "root", "root");//服务器地址， 服务器用户名， 密码
	//设置字符集utf8
	mysql_query("SET NAMES 'utf8'");
	mysql_query("SET CHARACTER SET 'utf8'");
	mysql_query("SET CHARACTER_SET_RESULT = utf8");

	if (!$con) {
		die(mysql_error());
	}
	mysql_select_db("newsdemo", $con);

?>