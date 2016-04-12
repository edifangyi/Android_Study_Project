<?php

/*
 * 获得JSON数据
 * 返回值：title desc time content_url pic_url
 */
 
 require 'mysql_connect.php';
 
 $n = 0;
 $result = mysql_query("select * from news");
 while ($row = mysql_fetch_array($result)){
 	$arr[$n++] = array("title" => $row['title'],
 						"desc" => $row['desc'],
 						"time" => $row['time'],
 						"content_url" => $row['content_url'],
 						"pic_url" => $row['pic_url']
					);
 }
 
 //数组转换为JSON字符串
 echo json_encode($arr);

?>