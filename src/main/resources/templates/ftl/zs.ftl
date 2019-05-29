<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>(ง •̀灬•́)ง</title>
    <link rel="stylesheet" type="text/css" href="css/zs.css"/>
    <link rel="stylesheet" type="text/css" href="css/mui.min.css"/>
    <script src="js/mui.min.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.cookie.js" type="text/javascript"></script>
    <script src="js/zs.js"></script>
</head>

<body class="body">

<div id="search-div" class="mui-container">
    <div class="mui-textfield mui-textfield--float-label">
        <input class="search-field" type="text">
        <label>全拼/首字母/代码/名称</label>
    </div>
</div>

<div class="mui-container">
    <table id="suggest-form" class="mui-table mui-table--bordered">
        <thead>
        <th style="text-align:center;">类别</th>
        <th style="text-align:center;">名称</th>
        <th style="text-align:center;">代码</th>
        </thead>
        <tbody class="suggest-form-tbody">

        </tbody>
    </table>
</div>

<div class="mui-container">
    <table class="mui-table mui-table--bordered">
        <thead>
        <tr>
            <th style="text-align:center;">名称</th>
            <th style="text-align:center;">最新</th>
            <th style="text-align:center;">涨跌</th>
        </tr>
        <#list resultList as lsit>
        <tbody>
        <tr>
            <td class="td innercode">
                ${lsit.code}
            </td>
            <td class="td">
                ${lsit.name}
            </td>
            <#if lsit.percent?contains("-") >
                <td class="td green">
                    ${lsit.now}
                </td>
                <td class="td green">
                    ${lsit.percent} %
                </td>
            <#else>
                <td class="td red">
                    ${lsit.now}
                </td>
                <td class="td red">
                    ${lsit.percent} %
                </td>
            </#if>
            <td class="del td mui-btn mui-btn--mini mui-btn--fab">
                -
            </td>

        </tr>
        </#list>
        </tbody>
        </thead>
    </table>
</div>
<div class="refresh-div">
    <div id="refresh" class="mui-btn mui-btn--primary mui-btn--fab">
        <img class="refresh-img" alt="刷新"
             src="data:image/png;base64,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">
    </div>
</div>

<div class="tips-div" >
    <div class="tips" >
tetsta
    </div>
</div>

</body>
</html>