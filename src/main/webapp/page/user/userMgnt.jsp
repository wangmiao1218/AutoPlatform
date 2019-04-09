<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keyword" content="">
    <title>列表</title>
    <!-- 日期控件CSS -->
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/skinex/skinex.css" />
</head>

<body>
    <div class="asideR-cont">
        <div class="query-head clearfix">
            <span class="query-item mr20">
                <label class="query-txt">用户类型</label>
                <div class="combo" id="simpleCombo"></div>
                <input type="hidden" id="opKind" name="opKind" >
            </span>
            <span class="query-item">
                <input class="query-input" id="opName" placeholder="用户名称">
            </span>
            <!-- <span class="query-item mr10 posR">
                   <input class="query-input" id="timeStart" placeholder="时间起" />
                   <i class="cal-ico" id="timeStartBox"></i>
            </span>
            <span class="query-item mr10 posR">
                   <input class="query-input" id="timeEnd" placeholder="时间止" />
                   <i class="cal-ico" id="timeEndBox"></i>
            </span> -->
            <span class="query-btns">
                 <button type="submit" class="btn-search" title="查询" onclick="query()"></button>
                 <button type="submit" class="btn-reset" onclick="reset()" title="重置"></button>
             </span>
        </div>
        <div class="grid-com com-line" id="queryGrid">
        </div>
    </div>
    
    <!-- 日期控件JS  -->
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/js/dateField.js"></script>
    <script src="<%=rootPath %>/page/user/js/userMgnt.js"></script>
</body>

</html>