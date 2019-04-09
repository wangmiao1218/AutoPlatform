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

// 定义zTree容器
var treeObj = null;

var data, store, columns, roleId, queryGrid,pager;
Ext.onReady(function(){
    Ext.QuickTips.init();
    Ext.EventManager.onWindowResize(function(){ 
        queryGrid.getView().refresh() ;
    });
    initGrid();
});

/**
 * 初始化供应商列表
 */
function initGrid(){

    // create the data store
    store = Ext.create('Ext.data.Store', {
        fields: [
           {name: 'roleName', type: 'auto', convert: null, defaultValue: undefined},
           {name: 'roleId', type: 'auto', convert: null, defaultValue: undefined},
        ],  
        remoteSort: true,
        pageSize: 10,
        proxy: {
            type: 'ajax',
            url: rootPath + '/sysRoleController/getRoleList',
            data: data,
            reader: {
                type: 'json',
                root: 'list',// PageHelper 
                totalProperty: 'total'
            }
            
        }
    });
    // width确定的宽度
    columns = [
            {
                text     : '角色名称',
                flex     : 1,
                sortable : false,
                dataIndex: 'roleName',
                renderer : qtips
            },
            {
                text     : '角色id',
                width    : 0,
                sortable : true,
                dataIndex: 'roleId'
            }
        ];
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
        autoHeight: true,
        autoWidth: true,
        renderTo: 'queryGrid',
        listeners:{
        	'select':function(value, meta, record, rowIndex, colIndex, store){
        		roleId = value.lastSelected.raw.roleId;
        		
        		// **** 加载树所需的数据
        		$.ajax({
        		    type: 'get',
        		    dataType :'json',
        		    url: rootPath + '/sysRoleController/getRolFuncTree',
        		    data :{
        		      roleId : roleId
        		    },
        		    success:function(data){
        		      if(treeObj != null){
        		        treeObj.destroy();
        		      }
        		      //初始化树，按配置进行渲染
        		      $.fn.zTree.init($("#treeDemo"), setting, data);
        		      treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        		      var nodes = treeObj.getCheckedNodes(true);
        		      for (var i = 0; i < nodes.length; i++) {
        		        treeObj.expandNode(nodes[i], true, true, true);
        		      }
        		    },
        		    error:function(data){
        		      
        		    }
        		 })
        		 // **** 加载树所需的数据
        	}
        },
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
 * 查询
 */
function queryBtn(){
	store.proxy.extraParams = {
		roleName:$("#roleName").val()
	},
	// 加载store数据
	store.load();
}

/*
 * 清空查询条件 
 */
function resetBtn(){
	window.location.href = rootPath + '/page/role/rolefunc.jsp';
}

/*
 * 点击保存修改
 */
function save(){
	var funcIdArr = new Array();
	var nodes = treeObj.getCheckedNodes(true);
	 for (var i = 0; i < nodes.length; i++) {
		 funcIdArr[i] = nodes[i].funcId;
	 }
	var data = {
		funcIdArr : funcIdArr.join(),
		roleId:roleId
	}
	var type = 'json';
	var url = rootPath + '/role/updateRoleFunc';
	var callback = function(result){
		alert(result.msg);
	};
	$.post(url,data,callback,type);
}

/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}

//设置zTree
var setting = {
	check: {
		enable: true,
		chkboxType: { "Y": "p", "N": "s" }
	},
	data: {
		simpleData: {
			enable: true,
			idKey:'funcId',
			pIdKey:'supFuncId'
		}
	}
};
	

