<!DOCTYPEhtml>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>${percent} %</title>
	</head>
	<body style = "background-color:#222222;color:#bfbfbf;text-align: center;">
		<div style = "border-style:solid;border-width:1px;width:300px;position:relative;margin:0 auto;border-radius:20px;">
			<div>
			<p>=======   ${name}   =======</p>
			<#if percent?contains("-") >
			<h3  style = "color:green;">${now}&nbsp&nbsp&nbsp&nbsp${percent} %</h3>
			<#else>
			<h3  style = "color:red;">${now}&nbsp&nbsp&nbsp&nbsp${percent} %</h3>
			</#if>
			<h3>${time}</h3>
			</div>
		</div>
	</body>
</html>