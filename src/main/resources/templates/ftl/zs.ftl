<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width,height=device-height, initial-scale=1.0">
		<title> 指数综合 </title>
		<style>
		.font{ font-family:"Microsoft YaHei Mono";}
		</style>
	</head>
	<body class = "font" style = "background-color:#222222;color:#bfbfbf;text-align:center;">
		<div style = "border-style:solid;border-width:1px;width:240px;position:relative;margin:auto;margin-top:5%;border-radius:16px;">
			<div>

			<#list resultList as lsit>

			<p>=======   ${lsit.name}   =======</p>
			<#if lsit.percent?contains("-") >
			<h3  style = "color:green;">${lsit.now}&nbsp&nbsp&nbsp&nbsp${lsit.percent} %</h3>
			<#else>
			<h3  style = "color:red;">${lsit.now}&nbsp&nbsp&nbsp&nbsp${lsit.percent} %</h3>
			</#if>
			<h3>${lsit.time}</h3>

			</#list>
			
			</div>
		</div>
	</body>
</html>