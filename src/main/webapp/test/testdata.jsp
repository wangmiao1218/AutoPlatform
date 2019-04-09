<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日期插件</title>

<%
	String rootPath = request.getContextPath();
%>



<!-- 测试日期范围开始结束 -->
<script language="javascript" type="text/javascript"
	src="<%=rootPath%>/res/My97DatePicker/WdatePicker.js"></script>

<script language="javascript" type="text/javascript">
	//禁止用F5键
	/* document.onkeydown = function(e) {
		e = window.event || e;
		var keycode = e.keyCode || e.which;
		if (keycode = 116) {
			if (window.event) {// ie
				try {
					e.keyCode = 0;
				} catch (e) {
				}
				e.returnValue = false;
			} else {// ff
				e.preventDefault();
			}
		}
	}
 */ 
 	//禁止f5刷新
	/* document.onkeydown = function(e) {
		var ev = window.event || e;
		var code = ev.keyCode || ev.which;
		if (code == 116) {
			ev.keyCode ? ev.keyCode = 0 : ev.which = 0;
			cancelBubble = true;
			return false;
		}
	};
	//禁止右键刷新 
	document.oncontextmenu = function() {
		return false;
	}; */
</script>
<body>

	<div>
		<h5>测试日期</h5>
		<input class="Wdate" id="d1" onclick="WdatePicker()" />
	</div>

	<div>
		<h5>日期联动（前面大于今天，后面大于前面）</h5>
		<input id="d5221" type="text"
			onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'d5222\',{M:-3,d:-1})}'})" />

		- <input id="d5222" type="text"
			onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\',{M:0,d:1})}'})" />
	</div>

	<!-- M:0 日期往后推迟几个月 
	为空时,表示直接取值,不做差量(示例4-3-1中的参数就是空的)
	{M:5,d:7} 表示 五个月零7天
	{y:1,d:-3} 表示 1年少3天
	{d:1,H:1} 表示一天多1小时
	
	-->


	<!-- 
	<div>
		<h5>显示日期范围（今天到今天往后七天）</h5>
		<input type="text"
			onfocus="WdatePicker({skin:'default',minDate:'%y-%M-%d',maxDate:'%y-%M-{%d+7}'})" />
	</div>
	<div>
		<h5>只能选择今天以前的日期</h5>
		<input type="text"
			onfocus="WdatePicker({skin:'default',maxDate:'%y-%M-%d'})" />
	</div>
	<div>
		<h5>只能选择今天以后的日期</h5>
		<input type="text"
			onfocus="WdatePicker({skin:'default',minDate:'%y-%M-{%d+1}'})" />
	</div>
	<div>
		<h5>只能选择本月日期</h5>
		<input type="text"
			onfocus="WdatePicker({minDate:'%y-%M-01',maxDate:'%y-%M-%ld'})" />
	</div>
	<div>
		<h5>只能选择今天01:00:00到12:00:00的日期</h5>
		<input type="text"
			onfocus="WdatePicker({dateFmt:'yyyy-M-d H:mm:ss',minDate:'%y-%M-%d 01:00',maxDate:'%y-%M-%d 12:00:00'})" />
	</div>
	<div>
		<h5>只能选择一小时前到一小时后的日期</h5>
		<input type="text"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-%d {%H-1}:%m:%s',maxDate:'%y-%M-%d {%H+1}:%m:%s'})" />
	</div>
	<div>
		<h5>选择日期（前面大于今天且小于2020年1月1号，后面大于前面且小于2020年1月1号）</h5>
		<input type="text" id="d4331"
			onFocus="WdatePicker({minDate:'%y-%M-{%d+1}',maxDate:'#F{$dp.$D(\'d4332\',{M:-3,d:-2})||$dp.$DV(\'2020-1-1\',{M:-3,d:-2})}'})" />
		- <input type="text" id="d4332"
			onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d4331\',{M:3,d:2});}',maxDate:'2020-1-1'})" />
	</div>
	<div>
		<h5>禁用 周六 周日 所对应的日期</h5>
		<input type="text" onFocus="WdatePicker({disabledDays:[0,6]})" />
	</div> -->


	<div style="position:absolute; height:400px; overflow:auto">
	dsdasdasdasdasdsadsa
	dasdsadsadsada
	dssa
	</div>
	<DIV style="PADDING-RIGHT:10px;OVERFLOW-Y:auto;PADDING-LEFT:10px;SCROLLBAR-FACE-COLOR:#ffffff;FONT-SIZE:11pt;PADDING-BOTTOM:0px;SCROLLBAR-HIGHLIGHT-COLOR:#ffffff;OVERFLOW:auto;WIDTH:510px;SCROLLBAR-SHADOW-COLOR:#919192;COLOR:blue;SCROLLBAR-3DLIGHT-COLOR:#ffffff;LINE-HEIGHT:100%;SCROLLBAR-ARROW-COLOR:#919192;PADDING-TOP:0px;SCROLLBAR-TRACK-COLOR:#ffffff;FONT-FAMILY:宋体;SCROLLBAR-DARKSHADOW-COLOR:#ffffff;LETTER-SPACING:1pt;HEIGHT:200px;TEXT-ALIGN:left">
</div> 

</body>
</html>
