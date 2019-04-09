Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux', rootPath + '/res/extjs/ux/');
Ext.require([
    'Ext.data.*',
    'Ext.grid.*',
    'Ext.util.*',
    'Ext.form.field.ComboBox',
    'Ext.form.FieldSet',
    'Ext.tip.QuickTipManager',
    'Ext.ux.data.PagingMemoryProxy'
    
]);

var data, store, columns, queryGrid,pager;
Ext.onReady(function(){
    initCombo();
    onfocus();
    initButton();
    //initCombo("simpleCombo");
    //initCombo("simpleCombo2");
});

/*
 * ajax 文件上传
 */
function initButton(){
	
	// 取消操作
	$("#cancle").click(function(){
		window.location.href = rootPath + "/page/user/userMgnt.jsp";
	});
	
	$("#uploadImg").click(function(){
		// 验证图片格式
		var ext = '.jpg.jpeg.gif.bmp.png.';
		var f = $("#uploadFile").val();
		if (f == "") {
			alert("请选择文件！");
			return false;
		}
		f = f.substr(f.lastIndexOf('.') + 1).toLowerCase();
		if (ext.indexOf('.' + f + '.') == -1) {
			alert("图片格式不正确！");
			return false;
		}
		
		 $.ajaxFileUpload({
			 url:rootPath + "/user/uploadFile",
		 	 dataType:"json",
		 	 fileElementId:"uploadFile",
		 	 success:function(result){
		 		 // 接收图片路径，给隐藏域赋值
		 		 alert(result.message);
		 		 $("#opPic").val(result.fileName);
		 		 alert(result.fileName);
		 	 },
		 	 error:function(result){
		 		alert("图片上传失败，请联系管理员");
		 	 }
		 });
	});
}


/*
 * 点击保存按钮
 */
function saveUser(){
	
	// 判断  新增 / 修改
	var type = $("#type").val();
	// 声明要提交的url
	var url = "";
	
	if(type == "add"){
		url = rootPath + "/user/add";
	}else{
		url = rootPath + "/user/modify";
	}
	
	// ajax 方式提交表单
	//1、获取表单参数
	var data = $("#userMesForm").serialize();
	var type = "json";
	$.post(url,data,function(result){
		alert("保存成功");
		//手动跳转页面
		window.location.href = rootPath + "/page/user/userMgnt.jsp";
	},type);
	
	alert("保存成功");
	//手动跳转页面
	window.location.href = rootPath + "/page/user/userMgnt.jsp";
	
	//$("#userMesForm").submit();
}


//初始化下拉框
function initCombo(){
	// 生成下拉框数据源
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 指定下拉框属性
        fields: ['codeValue', 'codeName'],
        // 给请求附加参数
        // 利用ajax请求获取数据
        proxy: {
        	extraParams:{
            	codeType : '1003'
            },
	        type: 'ajax',
	        url : rootPath + '/codeValue/getCodeValue'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'opKindCombo',
        // 下拉框显示的值
        displayField: 'codeName',
        // 下拉框提交的值
        valueField: 'codeValue',
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        // 监听下拉框选中事件
        listeners:{
        	'select':function(value){
        		// 给opKind赋值
        		$("#opKind").val(this.getValue());
        	},
        	// 当页面重新渲染时，设置下拉框选中回显
    		render:function(value){
    			//重新加载数据
    			store.load();
    			//回显下拉框
    			this.setValue($("#opKind").val());
    		}
        }
        
    });
}

/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}
/* upload */
//图片路径赋值 
function uploadPath(){
	 var path = $("#uploadFile").val();
     $("#path").val(path);
     $("#img").attr('src',path);
     $("#img").next().css('display','none'); 
}


// 图片上传 及数据保存
function uploadImage() {
	$("#uploadImg").click(function() {
		var ext = '.jpg.jpeg.gif.bmp.png.';
		var f = $("#uploadFile").val();
		if (f == "") {// 先判断是否已选择了文件
			alert("请选择文件！");
			return false;
		}
		f = f.substr(f.lastIndexOf('.') + 1).toLowerCase();
		if (ext.indexOf('.' + f + '.') == -1) {
			alert("图片格式不正确！");
			return false;
		}
		
		$.ajaxFileUpload({
			url :  "/upload",
			secureuri : false,
			fileElementId : 'uploadFile',
			dataType : 'json',
			success : function(data) {
				if (data.status == "0"){
					fileName = data.fileName;
					$("#advPic").val(fileName);
					alert("上传图片成功");
				} else {
					alert(data.message);
				}
			},
			error : function(data, status, e) {
				alert("文件上传失败，请联系系统管理员");
			}
		});
	});
}
/*=======================*/
function onfocus(){
    $("input[type='text']").focus(function(){
        $(this).addClass("blur");
    })
     $("input[type='text']").blur(function(){
        $(this).removeClass("blur");
    })
}