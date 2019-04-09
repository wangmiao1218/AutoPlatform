Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux', rootPath +'/res/extjs/ux/');
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
	//initCombo();
    onfocus();
    //initButton();
    //initCombo("simpleCombo");
    //initCombo("simpleCombo2");
});

 //配置联动路径
function configuredLinkagePath(){
	url = rootPath + "/CRFTemplateToolsController/configuredLinkagePath";
	$.ajax({
        type : "get",
        url : url,
        dataType : 'JSON',
        beforeSend: function () {
        	$("#myShow").show();
        },
        
		success : function(data) {
			alert(data.msg);
			//成功后才跳转，请求controller，跳转到文件列表页面，以便下载
			//window.location.href = rootPath+"/crfLinkagePathController/showFilesList";
			$("#downloadButton").show();
		},
		complete: function () {
			$("#myShow").hide();
        },
		error : function(data) {
			alert("处理失败！");
		}
    });

}

//配置英文名称
function translateToEnglish(){
	url = rootPath + "/CRFTemplateToolsController/translateToEnglish";
	$.ajax({
		type : "get",
		url : url,
		dataType : 'JSON',
		beforeSend: function () {
			$("#myShow").show();
		},
		success : function(data) {
			alert(data.msg);
			//成功后才跳转，请求controller，跳转到文件列表页面，以便下载
			//window.location.href = rootPath+"/crfLinkagePathController/showFilesList";
			$("#downloadButton").show();
		},
		complete: function () {
			$("#myShow").hide();
		},
		error : function(data) {
			alert("处理失败！");
		}
	});
	
}

//下载：暂不使用此方法！
/*
function download(){
	url = rootPath + "/crfLinkagePathController/downloadFile";
	$.ajax({
        type : "get",// 请求方式
        url : url,// 发送请求地址
        dataType : 'JSON',
		success : function(data) {
			alert(data.msg);
			$("#downloadButton").hide();
		},
		error : function(data) {
			alert("下载失败");
		}
    });
}
*/

/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}

/**
 * 文件上传
 * @returns {Boolean}
 */
function upload() {
	var f = $("#uploadFile").val();
	if (f == "") {
		alert("请选择模板结构(Excel)文件！");
		return false;
	}
	var f2 = $("#uploadFile2").val();
	if (f2 == "") {
		alert("请选择单病种模板(Excel)文件！");
		return false;
	}
	
    var fileslist = $("input[name^=files]");  
    var filesId = [];  
    for (var i=0; i< fileslist.length; i++){  
	    if(fileslist[i].value){  
	    	filesId[i] = fileslist[i].id;  
	    }  
    }
    //var filesId=['uploadFile','uploadFile2'];
    
    //!!!!!!又是一坑：ajaxFileUpload是不解析json的,需要用：
    //data = $.parseJSON(data.replace(/<.*?>/ig,""));
	$.ajaxFileUpload({
		url : rootPath + '/CRFTemplateToolsController/uploadFiles',
		type : 'post',
		dataType : 'JSON',
		fileElementId : filesId,
		data : {
			
		},
		success : function(data) {
			data = $.parseJSON(data.replace(/<.*?>/ig,""));
			alert(data.msg);
			$("#dealEnNamesButton").show();
			$("#dealPathButton").show();
		},
		error : function(data) {
			alert("上传失败");
		}
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