<#macro html ueditor="false">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>api监控</title>
    <script src="${ctx!}/static/js/vue/vue.min.js"></script>
    <script src="${ctx!}/static/js/jquery/jquery.min.js"></script>
    <script src="${ctx!}/static/js/third/jquery-1.10.2.js"></script>
    <script src="${ctx!}/static/js/element-ui/element-ui.js"></script>
    <script src="${ctx!}/static/js/jsonView/jquery.jsonview.js"></script>
    <link rel="stylesheet" href="${ctx!}/static/css/element-ui/element-ui.css">
    <link rel="stylesheet" href="${ctx!}/static/css/jsonView/jquery.jsonview.css">
</head>
<style type="text/css">
    [v-cloak] {
        display: none;
    }
    body {
        margin: 0 auto;
    }
    .base_container {
        height:calc(100vh - 40px);
    }
    ::-webkit-scrollbar {width: 5px;height: 10px;}
    ::-webkit-scrollbar-thumb {background-color: #DDDEE0;border-radius: 3px;}
</style>
<script>
    function addNoCache(url){
        let timestamp = (new Date()).valueOf();
        if (url.indexOf("?") >= 0) {
            url = url + "&_tsp_=" + timestamp;
        } else {
            url = url + "?_tsp_=" + timestamp;
        }
        return url;
    }
</script>
<body>
<div id="app">
    <#nested/>
</div>
</body>
</html>
</#macro>