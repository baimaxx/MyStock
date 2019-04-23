<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width,height=device-height, initial-scale=1.0">
		<title> (ง •̀灬•́)ง </title>
		<style>
		.font{ font-family:"Microsoft YaHei Mono";background-color:#ffffff;color:#515151;text-align:center;line-height:15px}
		</style>
	</head>
	<body class = "font" >
		<div style = "border-style:solid;border-width:1px;width:300px;position:relative;margin:auto;margin-top:5%;border-radius:16px;">
			<div>

			<#list resultList as lsit>

			<p>=======   ${lsit.name}   =======</p>
			<#if lsit.percent?contains("-") >
			<h3  style = "color:green;">${lsit.now}&nbsp&nbsp&nbsp&nbsp${lsit.percent} %</h3>
			<#else>
			<h3  style = "color:red;">${lsit.now}&nbsp&nbsp&nbsp&nbsp${lsit.percent} %</h3>
			</#if>
			<p>${lsit.time}</p>

			</#list>
			
			</div>
		</div>
	</body>
</html>