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
    <title>添加用户</title>
    <!-- 日期控件CSS -->
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/skinex/skinex.css" />
	<!-- 引入jQueryValidation -->
	<script type="text/javascript" src="<%=rootPath %>/res/jquery/plugins/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/jquery/plugins/jquery.validate.message_cn.js"></script>
	<script type="text/javascript">
		$.validator.setDefaults({
			// 当表单提交时，验证通过，重新绑定提交方法
			submitHandler:function(form){
				// 提交表单
				//form.submit();
			}
		});
		
		// 表单验证
		$().ready(function(){
			$("#userMesForm").validate({
				rules:{
					opName:{
						required:true,
						minlength:2
					},
					opCode:{
						required:true
					},
					loginCode:{
						required:true,
						minlength:5
					},
					mobileNo:{
						required:true,
						rangelength:[11,11]
					},
					emailAdress:{
						required:true,
						email:true
					}
				},
				messages:{
					opName:{
						required:"请输入操作员名称",
						minlength:"操作员名称长度需大于2"
					},
					opCode:{
						required:"请输入操作员编码"
					},
					loginCode:{
						required:"请输入登录名",
						minlength:"登录名必须大于5位"
					},
					mobileNo:{
						required:"请输入手机号",
						rangelength:"手机号格式不正确"
					},
					emailAdress:{
						required:"请输入邮箱地址",
						email:"邮箱地址格式不正确"
					}
				}
			});	
		});
	</script>
</head>
<body>
    <div class="asideR-cont">
        <div class="add-cnt">
       	 <form action="" id="userMesForm" method="post">
       	 <!-- 放置类型和id的隐藏域 -->
       	 <input type="hidden" id="type" name="type" value="${type }" >
       	 <input type="hidden" id="opId" name="opId" value="${sysOp.opId }" >
       	 <!-- 操作员类型 -->
       	 <input type="hidden" id="opKind" name="opKind" value="${sysOp.opKind }" >
       	 
            <ul class="add-lst">
            	 <li>
                    <label class="lbl-txt">操作员类型：</label>
                    <span class="query-item">
                        <div class="combo" id="opKindCombo"></div>
                    </span>
                    <span class="require">*</span>
                </li>
                <li>
                    <label class="lbl-txt">操作员名称：</label>
                    <input type="text" name="opName" value="${sysOp.opName }" class="input-text" />
                </li>
                <li>
                    <label class="lbl-txt">操作员编码：</label>
                    <input type="text" name="opCode" value="${sysOp.opCode }" class="input-text" />
                </li>
                <li>
                    <label class="lbl-txt">登录名：</label>
                    <input type="text" name="loginCode" value="${sysOp.loginCode }" class="input-text" />
                </li>
                <li>
                    <label class="lbl-txt">手机号码：</label>
                    <input type="text" name="mobileNo" value="${sysOp.mobileNo }" class="input-text" />
                </li>
                <li>
                    <label class="lbl-txt">邮箱地址：</label>
                    <input type="text" name="emailAdress" value="${sysOp.emailAdress }" class="input-text" />
                </li>
            </ul>
            <ul class="add-lst">
              <li>
                  <label class="lbl-txt">用户头像：</label>
                  <div class="upload-box">
                      <input type="text" class="input-text"  name="path" id="path" />
                      <input type="file" id="uploadFile" name="uploadFile" class="file-upload" onchange="uploadPath()"/>
                      <span class="require">*</span>
                      <button class="browse-btn">浏览</button>
                  </div>  
                   <input type="button" class="upload-btn" name="uploadImg" id="uploadImg" value="上传"> 
                   <input type="hidden" id="opPic" name="opPic" value="${sysOp.opPic }"> 
                   <p style="color:red;margin-left:120px">建议上传图片选取400×300像素图片，大小在300K以下，以达到最佳显示效果。</p>
              </li>
            </ul>
              </form>
            <div class="form-aciton" style="width:300px;margin-left:auto;margin-right:auto;">
                <button class="submit-btn" onclick="saveUser()">确定</button>
                <button class="quit-btn">取消</button>
            </div>
        </div>
    </div>
   	<!-- 日期控件JS  -->
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/js/dateField.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/jquery/plugins/jquery.ajaxfileupload.js"></script>
    <script src="<%=rootPath %>/page/user/js/userAdd.js"></script>
</body>

</html>