<html>
	<head>
		<title>FANGYIQRLogin</title>
		<meta charset="utf-8" />
	</head>
	<body>
		<?php 
			require 'mysql_connect.php';
			$randnumber = "";
			for	($i=0; $i<8; $i++)
				$randnumber .= rand(0, 9);
			mysql_query("insert into login_record (randnumber) values ('$randnumber')");
			echo $randnumber;
		?>
		<img src="http://qr.topscan.com/api.php?text= <?php echo $randnumber; ?>" width="300px"/>
		
		<input hidden="hidden" type="text" name="randnumber" id="randnumber" value="<?php echo $randnumber;?>"/>
	</body>
	
	<script>
		function polling() {
			//执行轮询操作
			/*
			 * 根据当前二维码的随机数信息，去数据库查询是否有用户执行了扫码登陆操作
			 * 有因为执行轮询操作的过程中，页面不能有任何的刷新操作，让用户感觉不到页面刷新
			 * 使用：异步加载
			 */
			var xmlHttp;
			//判断浏览器的类型，生成不同XMLhttp的对象
			if (window.XMLHttpRequest) {
				xmlHttp = new XMLHttpRequest();
			} else {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			xmlHttp.onreadystatechange = function() {
				if (xmlHttp.status == 200 && xmlHttp.readyState == 4) {
					result = xmlHttp.responseText;
					if (result == 'true') {
						window.location.href = 'welcome.php';
					} 
				}
			}

			//javascript获取input标签中的随机数值
			randnumber = document.getElementById('randnumber').value;
			xmlHttp.open("GET", "polling.php?randnumber=" + randnumber, true);
			xmlHttp.send();	
		}
		//第一个参数：间隔多久时间执行哪个函数，第二个参数：间隔多久0
		setInterval("polling()", 1000);

	</script>
</html>