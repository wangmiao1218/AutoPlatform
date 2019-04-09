<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RWS测试_引用类型</title>
<style type="text/css">
#box {
	position: absolute;
	left: 1000px;
	top: 150px;
	padding: 5px;
	background: #f0f3f9;
	font-size: 12px;
	-moz-box-shadow: 2px 2px 4px #666666;
	-webkit-box-shadow: 2px 2px 4px #666666;
}
#box2 {
	position: absolute;
	left: 1000px;
	top: 550px;
	padding: 5px;
	background: #f0f3f9;
	font-size: 12px;
	-moz-box-shadow: 2px 2px 4px #666666;
	-webkit-box-shadow: 2px 2px 4px #666666;
}

#main {
	border: 1px solid #a0b3d6;
	background: white;
}

#bar {
	line-height: 24px;
	background: #beceeb;
	border-bottom: 1px solid #a0b3d6;
	padding-left: 5px;
	cursor: move;
}

#content {
	width: 300px;
	height: 300px;
	padding: 10px 5px;
}

#content2 {
	width: 300px;
	height: 280px;
	padding: 10px 5px;
}
</style>
<%
	String rootPath = request.getContextPath();
%>

<body>
	<h4>RWS测试_引用比较_事件</h4>
	<h5>引用类型——测试数据：pat_testrws002.txt(pat_testrws002_1.txt)</h5>
	<p />
	<a href="<%=rootPath%>/test/testRWS_1.jsp">RWS测试_直接比较_事件</a>
	<p />


	<div id="box">
		<div id="main">
			<div id="bar">所引用的指标：数值字段</div>
			<div id="content">
				<span>1.自定义数值指标(数据为166，2014年)(新增一次检验，时间改为2015年，值800)</span><p/>
				<input type="text" placeholder="第一次检验全血的血小板总数" /><p/>
				指标类型：
				<select>
				    <option value="">默认类型</option>  
				    <option value="">枚举类型</option>  
				</select><p/> 
				检索结果：
				<select>
				    <option value="">就诊.检验报告.检验子项.检验子项结果数值</option>  
				</select><p/>
				结果处理函数： 
				<select>
					<option value="">第一个</option>  
					<option value="">最后一个</option>  
					<option value="">全部</option>  
					<option value="">第N个</option>  
				</select><p/>
				检索条件：<p/>
				<select>
					<option value="">就诊.检验报告.检验子项.检验子项中文名</option>
				</select> <p/>
				<select>
					<option value="">包含</option>
				</select><p/> 
				<input type="text"placeholder="血小板总数" /><p />
			</div>
		</div>
	</div>

 	<div id="box2">
		<div id="main">
			<div id="bar">所引用的指标:日期字段</div>
			<div id="content2">
				<span>1.自定义日期指标（2014-11-19，第二次改的为2015）</span><p/>
				<input type="text" placeholder="首次心脏病手术开始时间" /><p/>
				指标类型：
				<select>
				    <option value="">默认类型</option>  
				    <option value="">枚举类型</option>  
				</select><p/> 
				检索结果：
				<select>
				    <option value="">就诊.手术信息.手术开始时间</option>  
				</select><p/>
				结果处理函数： 
				<select>
					<option value="">第一个</option>  
					<option value="">全部</option>  
					<option value="">最后一个</option>  
					<option value="">第N个</option>  
				</select><p/>
				检索条件：<p/>
				<select>
					<option value="">就诊.手术信息.术后诊断</option>
				</select> <p/>
				<select>
					<option value="">包含</option>
				</select><p/> 
				<input type="text"placeholder="先天性心脏病" /><p />
			</div>
		</div>
	</div>
 
	<div>
		<span>一、定义事件</span><br /> <input type="text" placeholder="事件名称" />
		检索结果： <select>
			<option value="">就诊.检验报告.检验子项</option>
		</select> 
		结果处理函数：
		 <select>
			<option value="">全部</option>
			<option value="">第一个</option>
			<option value="">最后一个</option>
			<option value="">第N个</option>
		</select>
	</div>
	<p />
	<div>
		<span>二、定义指标</span><br /> <input type="text" placeholder="指标名称" />
		指标类型： <select>
			<option value="">默认类型</option>
			<option value="">枚举类型</option>
		</select> 检索结果： <select>
			<option value="">就诊.检验报告.检验子项</option>
		</select> 结果处理函数： <select>
			<option value="">全部</option>
			<option value="">第一个</option>
			<option value="">最后一个</option>
			<option value="">第N个</option>
		</select>
	</div>
	<p />

	<div>
		<span>1.数值类型:大于指标值（第一次检验全血的血小板总数：166）（平均血红蛋白浓度：328）</span><br /> 
		<select>
		    <option value="">就诊.检验报告.检验子项.检验子项结果数值</option>  
		</select> 
		<select>
			<option value="">大于指标值</option>
		</select>
		<select>
			<option value="">第一次检验全血的血小板总数</option>
		</select> 
		<input type="text" placeholder="100" /> 至
		<input type="text" placeholder="200" />之间
		<p />

		<span>2.数值类型:大于等于指标值（第一次检验全血的血小板总数：166;2-1:16）（平均血红蛋白浓度：328）</span><br /> 
		<select>
			<option value="">就诊.检验报告.检验子项.检验子项结果数值</option>
		</select> 
		<select>
			<option value="">大于等于指标值</option>
		</select> 
		<select>
			<option value="">第一次检验全血的血小板总数</option>
		</select> 
		<input type="text" placeholder="162" /> 至
		<input type="text" placeholder="162" />之间
		<p/>

		<span>3.数值类型:小于指标值（第一次检验全血的血小板总数：166）（实际住院天数：26）</span><br /> 
		<select>
		    <option value="">就诊.病案首页.实际住院天数</option>  
		</select> 
		<select>
			<option value="">小于指标值</option>
		</select> 
		<select>
			<option value="">第一次检验全血的血小板总数</option>
		</select> 	
		<input type="text" placeholder="150" /> 至
		<input type="text" placeholder="120" />之间
		<p />


		<span>4.数值类型:小于等于指标值（第一次检验全血的血小板总数：166）（就诊次数：数据1是1，数据1_1中改是5）</span><br /> 
		<select>
			<option value="">就诊.病案首页.就诊次数</option>
		</select> 
		<select>
			<option value="">小于等于指标值</option>
		</select> 
		<select>
			<option value="">第一次检验全血的血小板总数</option>
		</select> 	
		<input type="text" placeholder="166" /> 至
		<input type="text" placeholder="165" />之间
		<p />

		<span>5.数值类型:大于指标百分比</span><br /> 
		<select>
			<option value=""></option>
		</select> 
		<select>
			<option value="">大于指标百分比</option>
		</select> 
		<select>
			<option value="">第一次检验全血的血小板总数</option>
		</select> 	
		<input type="text" placeholder="" /> 至
		<input type="text" placeholder="" />之间（%）
		<p />


		<span>6.数值类型:大于等于指标百分比</span><br /> 
		<select>
			<option value=""></option>
		</select> 
		<select>
			<option value="">大于等于指标百分比</option>
		</select> 
		<select>
			<option value="">第一次检验全血的血小板总数</option>
		</select> 
		<input type="text" placeholder="" /> 至
		<input type="text" placeholder="" />之间（%）
		<p />


		<span>7.数值类型:小于指标百分比</span><br /> 
		<select>
			<option value=""></option>
		</select> 
		<select>
			<option value="">小于指标百分比</option>
		</select> 
		<select>
			<option value="">第一次检验全血的血小板总数</option>
		</select> 	
		<input type="text" placeholder="" /> 至
		<input type="text" placeholder="" />之间（%）
		<p />


		<span>8.数值类型:小于等于指标百分比</span><br /> 
		<select>
			<option value=""></option>
		</select> 
		<select>
			<option value="">大于指标值</option>
		</select> 
		<select>
			<option value="">第一次检验全血的血小板总数</option>
		</select> 	
		<input type="text" placeholder="" /> 至
		<input type="text" placeholder="" />之间（%）
		<p />


		<span>9.日期类型:早于指标日期（数据为2014-11-05）(指标上面选：就诊.检验报告)(首次心脏病手术（2014-11-19 ）)</span><br /> 
		<select>
			<option value="">就诊.检验报告.报告时间</option>
		</select> 
		<select>
			<option value="">早于指标日期</option>
		</select> 
		<select>
			<option value="">首次心脏病手术开始时间</option>
		</select> 
		<input type="text" placeholder="20" />至
		<input type="text"placeholder="15" />天 之间
		<p />


		<span>10.日期类型:晚于指标日期（数据为2014-11-20）(指标上面选：就诊.X线影像诊断报告)(首次心脏病手术（2014-11-19 ）)</span><br /> 
		<select>
			<option value="">就诊.X线影像诊断报告.检查日期</option>
		</select> 
		<select>
			<option value="">晚于指标日期</option>
		</select> 
		<select>
			<option value="">首次心脏病手术开始时间</option>
		</select> 
		<input type="text" placeholder="1" /> 至
		<input type="text" placeholder="2" />天 之间
		<p />

	</div>





</body>
</html>
