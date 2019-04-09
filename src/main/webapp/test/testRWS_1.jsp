<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RWS测试_非引用类型</title>

<%
	String rootPath = request.getContextPath();
%>


<!-- 测试日期范围开始结束 -->
<script language="javascript" type="text/javascript"
	src="<%=rootPath%>/res/My97DatePicker/WdatePicker.js"></script>
<body>
	<h4>RWS测试_直接比较_事件</h4>
	<h5>直接比较——测试数据：pat_testrws001.txt(pat_testrws001_1.txt)</h5>
	<p/>
	<a href="<%=rootPath %>/test/testRWS_2.jsp">RWS测试_引用比较_事件</a>
	<p/>
	<div>
		<span>一、定义事件</span><br/>
		<input type="text" placeholder="事件名称" />
		检索结果：
		<select>
		    <option value="">就诊.病案首页</option>  
		</select> 
		结果处理函数：
		<select>
			<option value="">第一个</option>  
			<option value="">全部</option>  
			<option value="">最后一个</option>  
			<option value="">第N个</option>   
		</select> 
	</div>
	<p/>
	<div>
		<span>二、定义指标</span><br/>
		<input type="text" placeholder="指标名称" />
		指标类型：
		<select>
		    <option value="">默认类型</option>  
		    <option value="">枚举类型</option>  
		</select> 
		检索结果：
		<select>
		    <option value="">就诊.病案首页</option>  
		</select>
		结果处理函数： 
		<select>
			<option value="">第一个</option>  
			<option value="">全部</option>  
			<option value="">最后一个</option>  
			<option value="">第N个</option>  
		</select> 
	</div>
	<p/>
	
	<div>
		<span>1.字符类型:精确匹配（完整词）</span><br/>
		<select>
		    <option value="">就诊.诊断.诊断名称</option>  
		</select> 
		<select>
			<option value="">精确匹配（完整词）</option>  
		</select> 
		<input type="text" placeholder="先天性心脏病" />
		
		<p/>
		<span>2.字符类型:完全不包含</span><br/>
		<select>
		    <option value="">就诊.诊断.诊断名称</option>  
		</select> 
		<select>
			<option value="">不包含</option>  
		</select> 
		<input type="text" placeholder="高血压I" />
		
		<p/>
		<span>3.日期类型:早于（日期为数据中的日期）</span><br/>
		<select>
		    <option value="">就诊.病案首页.入院日期</option>  
		</select> 
		<select>
			<option value="">早于</option>  
		</select> 
		<input type="text" placeholder="2014-11-04 08:48:00" />
		
		<p/>
		<span>4.日期类型:晚于（日期为数据中的日期）</span><br/>
		<select>
		    <option value="">就诊.病案首页.出院日期</option>  
		</select> 
		<select>
			<option value="">晚于</option>  
		</select> 
		<input type="text" placeholder="2014-11-30 08:11:00" />
		
		<p/>
		<span>5.日期类型:在…之间（日期为数据中的日期）</span><br/>
		<select>
		    <option value="">就诊.病案首页.手术.手术及操作日期</option>  
		</select> 
		<select>
			<option value="">在…之间</option>  
		</select> 
		<input type="text" placeholder="2014-11-19 00:00:00" />
		<input type="text" placeholder="2014-11-19 00:00:00" />
		
		<p/>
		<span>6.枚举类型:包含</span><br/>
		<select>
		    <option value="">就诊.病案首页.婚姻</option>  
		</select> 
		<select>
			<option value="">包含</option>  
		</select> 
		<input type="text" placeholder="已婚" />
		
		<p/>
		<span>7.枚举类型:不包含（数据中是门诊，数据中1_1是急诊。其他：急诊；门诊；其他医疗机构转入；其他）</span><br/>
		<select>
		    <option value="">就诊.病案首页.入院途径</option>  
		</select> 
		<select>
			<option value="">不包含</option>  
		</select> 
		<input type="text" placeholder="急诊" />
		
		<p/>
		<span>8.数值类型:大于(数据1中改的是9999)</span><br/>
		<select>
		    <option value="">就诊.检验报告.检验子项.检验子项结果数值</option>  
		</select> 
		<select>
			<option value="">大于</option>  
		</select> 
		<input type="text" placeholder="9998" />
		
		<p/>
		<span>9.数值类型:大于等于(数据1是26，数据1_1中改的是20)</span><br/>
		<select>
		    <option value="">就诊.病案首页.实际住院天数</option>  
		</select> 
		<select>
			<option value="">大于等于</option>  
		</select> 
		<input type="text" placeholder="26" />
		
		<p/>
		<span>10.数值类型:小于(数据1是1，数据1_1中改是5)</span><br/>
		<select>
		    <option value="">就诊.病案首页.就诊次数</option>  
		</select> 
		<select>
			<option value="">小于</option>  
		</select> 
		<input type="text" placeholder="5" />
		
		<p/>
		<span>11.数值类型:小于等于(数据1是0，数据1_1中改是5)</span><br/>
		<select>
		    <option value="">就诊.病案首页.抢救次数</option>  
		</select> 
		<select>
			<option value="">小于等于</option>  
		</select> 
		<input type="text" placeholder="2" />
		
		<p/>
		<span>12.数值类型:等于(数据1是265，数据1_1中改是2650)</span><br/>
		<select>
		    <option value="">就诊.手术信息.手术持续时间（分钟）</option>  
		</select> 
		<select>
			<option value="">等于</option>  
		</select> 
		<input type="text" placeholder="265" />
		<p/>
		
		
		
	</div>	
		

	


</body>
</html>
