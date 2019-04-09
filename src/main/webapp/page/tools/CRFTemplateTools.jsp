<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keyword" content="">
    <title>CRF模板工具-2.0</title>
    <!-- 日期控件CSS -->
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/skinex/skinex.css" />
	<!-- 引入jQueryValidation -->
	<script type="text/javascript" src="<%=rootPath %>/res/jquery/plugins/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/jquery/plugins/jquery.validate.message_cn.js"></script>
	<script type="text/javascript">
	</script>
</head>
<body>
	<!-- 遮蔽罩-->
	<div id="myShow" style="position:absolute; top:2%; left:2%; display: none;" >  
       <img style="width:310px; height:310px;" src="<%=rootPath %>/theme/gray/images/loading.gif"/>
    </div> 
    <div class="asideR-cont">
        <div class="add-cnt">
	        <form action="" method="post" enctype="multipart/form-data">  
	    		<ul class="add-lst">
	     			<li>
				        <label class="lbl-txt">模板结构(Excel):</label>
				    	<input type="file" id="uploadFile" name="files"> 
				    	<br><br>
				    	<label class="lbl-txt">单病种模板(Excel):</label>
				    	<input type="file" id="uploadFile2" name="files"> 
				    	<br><br>
				    	<p style="color:red;margin-left:20px">注意：若传错，则重新选择新文件，点击上传。</p>
				    	<p style="color:red;margin-left:20px">下载成功后，则删除服务器上文件。</p>
			   		</li>
	        	</ul>
	    	</form> 
	        <div class="form-aciton">
	        	<button class="upload-btn" id="upload-btn" onclick="upload()" >上传文件</button>
	            <button class="submit-btn" style="display: none" id="dealEnNamesButton" onclick="translateToEnglish()">配置英文名称</button>
	            <button class="submit-btn" style="display: none" id="dealPathButton" onclick="configuredLinkagePath()">配置联动路径</button>
	            <a id="downloadButton" style="display: none" href="<%=rootPath %>/CRFTemplateToolsController/downloadFile">下载文件</a>
	        </div>
        </div>
    </div>
	<!--上传  -->
    <script type="text/javascript" src="<%=rootPath %>/res/jquery/plugins/jquery.ajaxfileupload.js"></script>
	<script src="<%=rootPath %>/page/tools/js/CRFTemplateTools.js"></script>
</body>

</html>