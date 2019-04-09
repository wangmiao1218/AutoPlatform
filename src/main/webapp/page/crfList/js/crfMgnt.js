Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux', rootPath+'/res/extjs/ux/');
//动态模块的引入
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
	//悬浮
    Ext.QuickTips.init();
    //自适应宽高
    Ext.EventManager.onWindowResize(function(){ 
        queryGrid.getView().refresh() ;
    });
    //初始化列表
    initGrid();
    //初始化下拉框
    initCombo();
});
 
/*日期组件*/
function initDateTime() {
	// 开始时间
	$("#timeStartBox").live("click", function() {
		WdatePicker({
			el : "timeStart",
			dateFmt : "yyyy-MM-dd HH:mm:ss"
		});
	});
	// 结束时间
	$("#timeEndBox").live("click", function() {
		WdatePicker({
			el : "timeEnd",
			dateFmt : "yyyy-MM-dd HH:mm:ss"
		});
	});
}

// 初始化下拉框
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
	        url : rootPath + '/common/getCodeValue'
	    }
        
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'simpleCombo',
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
        	}
        }
    });
}

// 初始化列表
function initGrid(){

    // 创建列表需要的数据源 store
    store = Ext.create('Ext.data.Store', {
    	// 自动加载
    	autoLoad: true,
        fields: [
           {name: 'id', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'baseName', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'chineseName', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'englishName', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'dataType', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'variableType', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'rangeData', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'input', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'xpath', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'output', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'result', type: 'auto', convert: null, defaultValue: undefined}
        ],  
        remoteSort: true,
        // 设置单页显示数量(每页显示10个)
        pageSize: 10,
        proxy: {
            type: 'ajax',
            //传统分页
            //url: rootPath + '/user/selectUserList',
            //使用PageHelper分页
            url: rootPath + '/crfTemplateController/getCrfTemplateList',
            data: data,
            actionMethods:{
            	read:"POST"
            },
            reader: {
                type: 'json',
                // 指定根节点使用的数据
                // root: 'rows',
                root: 'list', // PageHelper 
                // 指定总记录数
	            totalProperty: 'total'
            }
        }
    });
 // width确定的宽度
    columns = [
               {
                   text : 'id',
                   /*flex : 1,*/
                   width : 45,
                   sortable : false,
                   dataIndex: 'id',
                   renderer : qtips
               },
               {
                   text : '基本信息',
                   width : 85,
                   sortable : true,
                   dataIndex: 'baseName'
               },
               {
                   text : '中文名',
                   width : 95,
                   sortable : true,
                   dataIndex: 'chineseName'
               },
               {
                   text : '英文名',
                   width : 104,
                   sortable : true,
                   dataIndex: 'englishName'
               },
               {
                   text : '变量类型',
                   width : 110,
                   sortable : true,
                   dataIndex: 'dataType',
                   // 对齐
                   align : 'center'
               },
               /*{
                   text : '变量类型',
                   width : 110,
                   sortable : true,
                   dataIndex: 'variableType'
               },*/
               {
                   text : '取值范围',
                   width : 110,
                   sortable : true,
                   dataIndex: 'rangeData'
               },
               {
                   text : '输入',
                   width : 110,
                   sortable : true,
                   dataIndex: 'input'
               },
               {
                   text : 'xpath',
                   width : 110,
                   sortable : true,
                   dataIndex: 'xpath'
               },
               {
                   text : '输出',
                   width : 85,
                   sortable : true,
                   dataIndex: 'output'
               },
               {
                   text : '结果',
                   width : 85,
                   sortable : true,
                   dataIndex: 'result'
               },
               {
                   text: '操作',
                   menuDisabled: true,
                   sortable: false,
                   width: 75,
                   renderer: buttonRender,
                   align : 'center'
               }
        ];
    // 操作区域
    var dockedItems = [{
            xtype: 'toolbar',
            items: [{
                text:'',
                tooltip:'新建',
                //tooltip:'验证下拉框',
                minWidth: 30,
                minHeight:30,
                iconCls:'new-ico',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							//window.location.href = rootPath + "/infoSelectController/verifySelect";
							window.location.href = rootPath + "/crfTemplateController/addUserPage?type=add";
						}
					}
				}
            }]
        }];
    
    // 多选
    var selModel = Ext.create('Ext.selection.CheckboxModel', {
        listeners: {
            selectionchange: function(sm, selections) {
            }
        }
    });
    //pager
    pager = Ext.create('Ext.PagingToolbar', {
        store: store,
        displayInfo: true,
        displayMsg : '显示第 {0} 条到 {1} 条记录,一共 {2} 条'
    });
    // create the Grid
    queryGrid = Ext.create('Ext.grid.Panel', {
        store: store,
        stateful: true,
        collapsible: false,
        multiSelect: true,
        stateId: 'stateGrid',
        columns: columns,
        selModel: selModel,
        dockedItems: dockedItems,
        autoHeight: true,
        autoWidth: true,
        renderTo: 'queryGrid',
        /*resizable: {
          handles: 's',
          minHeight: 100
        },*/
        bbar: pager,
        viewConfig: {
            stripeRows: true,
            enableTextSelection: true,
            deferRowRender : false,
            forceFit : true,
            emptyText : "<font class='emptyText'>没有符合条件的记录</font>",
            autoScroll:true,
            scrollOffset:-10
        }
    });
    store.load();
   
}
/*
* 操作按钮
*/
function buttonRender(value, meta, record, rowIndex, colIndex, store) {
    var returnValue = "";
    var state = record.data.state;
    var opId = record.data.opId;
   // returnValue += '<em class="modify-ico" title="修改" onclick="modify('+opId+')"></em>'+
    returnValue += '<em class="modify-ico" title="验证下拉框" onclick="modify()"></em>'+
                    '<em class="del-ico" title="删除" onclick="deleteSysOp('+opId+')"></em>';
    return returnValue;
}


/*
 * 执行修改操作
 */
/*function modify(opId){
	window.location.href = rootPath + "/user/addUserPage?type=modify&opId="+opId;
}*/
function modify(){
	//window.location.href = rootPath + "/infoSelectController/verifySelect";
	window.location.href = rootPath + "/infoSelectController/verifySelect";
}

/*
 * 执行删除操作
 */
function deleteSysOp(opId){
	if(!confirm("您确定要删除吗？")){
		return false;
	}
	
	// 获取store中的  所有记录数、单页展示记录数、当前页码
	var totalCount = store.getTotalCount();
	var pageSize = store.pageSize;
	var curPage = store.currentPage;
	
	// 找出记录号和记录条数
	// 当前页展示的起始记录号
	var fromRecord = ((curPage - 1) * pageSize) + 1;
    // 当前页展示的结尾记录号
    var toRecord = Math.min(curPage * pageSize, totalCount);
    // 当前页展示的记录条数
    var totalOnCurPage = toRecord - fromRecord + 1;
    // 总的页数
    var totalPage = Math.ceil(totalCount / pageSize);
    
    // 若当前页是最后一页，且不是仅有的一页，且删除的记录数是当前页上的最后一条（因为我们是单条删除）
    if (curPage === totalPage && totalPage != 1 && totalOnCurPage == 1){
    	// 更改store的当前页为前一页
    	store.currentPage = store.currentPage-1;
    }
	
	var url = rootPath + "/user/delete";
	var data = {opId:opId};
	var callback = function(result){
		alert(result.msg);
		// 重新加载数据
		store.load();
	};
	var type = "json";
	$.post(url, data, callback, type);	
}


/*
 * 是否锁定判断
 */
function ifLockRender(value){
	if(value == '1'){
		return '未锁定';
	}else{
		return '已锁定';
	}
}

/*
 * 用户类型判断
 */
function typeRender(value){
	if(value == '1'){
		return '超级管理员';
	}else if(value == '2'){
		return '管理员';
	}else{
		return '普通用户';
	}
}

/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}

/*
 * 按条件查询
 */
function query(){
	store.proxy.extraParams = {
		'opKind':$("#opKind").val(),
		'opName':$("#opName").val(),
	},
	
	// 加载store数据
	store.load();
}

/*
 * 重置查询
 */
function reset(){
	window.location.href = rootPath + "/page/crfList/crfMgnt.jsp";
}


