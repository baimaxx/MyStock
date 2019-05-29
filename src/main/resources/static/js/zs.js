function autoRefresh() {
    if ($.cookie('refresh-time') != null) {
        console.log("刷新间隔" + $.cookie('refresh-time'))
        setTimeout("window.location.reload();", $.cookie('refresh-time'));
    }
}

var last;
$(document).ready(function () {
    autoRefresh();

    // 搜索框输入事件，延迟查询
    $(".search-field").on("input", function (event) {

            var key = $(".search-field").val();
            if (key.length == 0) {
                return;
            }

            last = event.timeStamp;
            setTimeout(function () {     //设时延迟0.5s执行
                if (last - event.timeStamp == 0)
                //如果时间差为0（也就是你停止输入0.5s之内都没有其它的事件发生）则做你想要做的事
                {
                    doSearch(key)
                }
            }, 300);
        }
    )

    $(".search-field").on("focus", function () {
        var key = $(".search-field").val();
        if (key.length == 0) {
            return;
        }
        $("#suggest-form").show()
    })

    $(".search-field").on("blur", function () {
        setTimeout("$(\"#suggest-form\").hide();", 150);

    })

    $(".del").on("click", function (event) {
        var innercode = $(this).parents('tr').children("td").get(0).innerText.trim();
        var url = window.location.href;
        var pathname = window.location.pathname;
        if (pathname == "/") {
            return;
        } else {
            url = url.replace(innercode, "").replace(",,", ",").replace("/,", "/");
            if (url.endsWith(",")) {
                url = url.substring(0, url.length - 1);
            }
            location.replace(url);
        }

    })

    $("#refresh").on("click", function () {
        if ($.cookie('refresh-time') == null) {
            $.cookie('refresh-time', "10000", {expires: 10, path: '/'});
            console.log("自动刷新(10s)")
            showTips("自动刷新(10s)");
            setTimeout("window.location.reload();", 2000);
        } else {
            $.cookie("refresh-time", "", {expires: -1});
            console.log("自动刷新已停止")
            showTips("自动刷新已停止");
            setTimeout("window.location.reload();", 2000);
        }
    })
});

function showTips(text) {
    $(".tips").text(text);
    $(".tips-div").fadeIn(300).delay(1000).fadeOut(400);
}

function doSearch(key) {
    var searchUrl = '/api/search/';
    $.ajax({
        url: searchUrl + key,
        param: {},
        success: function (data) {
            $(".suggest-form-tbody").empty();
            for (var i = 0; i < data.length; i++) {
                var table = $("<tr id = " + data[i].innercode + " class = 'suggestLine' ></tr>");
                table.append("<td class = 'type suggest-td' onclick=\"addStock(event)\">" + data[i].type + "</td>");
                table.append("<td class = 'name suggest-td' onclick=\"addStock(event)\">" + data[i].name + "</td>");
                table.append("<td class = 'code suggest-td' onclick=\"addStock(event)\">" + data[i].code + "</td>");
                table.append("<td class = 'innercode suggest-td' onclick=\"addStock(event)\">" + data[i].innercode + "</td>");
                table.appendTo(".suggest-form-tbody");
            }
            $("#suggest-form").show();
        }
    });
}

function addStock(event) {
    var innercode = event.path[1].cells[3].innerText;
    var type = event.path[1].cells[0].innerText;
    if (type == "港股") {
        innercode = "hk" + innercode;
    }
    var url = window.location.href;
    if (window.location.pathname == "/") {
        url += innercode;
    } else {
        url += "," + innercode;
    }
    location.replace(url);
}

function sleep(n) {
    var start = new Date().getTime();
    while (true) if (new Date().getTime() - start > n) break;
}