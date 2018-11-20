<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width,height=device-height, initial-scale=1.0">
		<title>${hs300percent} %</title>
		<style>
		.font{ font-family:"Microsoft YaHei Mono";}
		</style>
	</head>
	<body class = "font" style = "background-color:#222222;color:#bfbfbf;text-align:center;">
		<div style = "border-style:solid;border-width:1px;width:240px;position:relative;margin:auto;margin-top:5%;border-radius:16px;">
			<div>
			<h3>${time}</h3>
			<p>=======   sh001   =======</p>
			
			<#if sh001percent?contains("-") >
			<h3 style = "color:green;">${sh001}&nbsp&nbsp&nbsp&nbsp${sh001percent} %</h3>
			<#else>
			<h3 style = "color:red;">${sh001}&nbsp&nbsp&nbsp&nbsp${sh001percent} %</h3>
			</#if>
			
			<p>=======   hs300   =======</p>
			<#if sh001percent?contains("-") >
			<h3 style = "color:green;">${hs300}&nbsp&nbsp&nbsp&nbsp${hs300percent} %</h3>
			<#else>
			<h3 style = "color:red;">${hs300}&nbsp&nbsp&nbsp&nbsp${hs300percent} %</h3>
			</#if>
			</div>
		</div>
	</body>
</html>