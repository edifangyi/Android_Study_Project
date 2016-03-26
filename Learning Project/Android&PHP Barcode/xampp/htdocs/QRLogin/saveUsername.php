<?php
/*
 * 自定义API用于Android客户端扫码后访问，将指定的username保存至相应的位置
 * 接收参数 randnumber、username
 * 无返回值
 */
 $randnumber = $_GET['randnumber'];
 $username = $_GET['username'];
 
 require 'mysql_connect.php';
 mysql_query("update login_record set username = '$username' where randnumber = '$randnumber'");
?>