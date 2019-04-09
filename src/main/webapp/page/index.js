Ext.onReady(function(){
	Ext.QuickTips.init();
	// 先获取菜单
    getMenu();
    // 再通过jquery操作节点
    menuOpera();
    initLogout();
});


function initLogout(){
	$(".logout").click(function(){
		window.location.href = rootPath + "/loginController/logout";
	});
	
}

/**
 * 得到所有菜单
 */
function getMenu() {
	var homePage = null;
	var menuData ="";
	
	$.ajax({
	 // 注意请求地址也要变为动态(报错中，还没联调)
	 url : rootPath + '/loginController/getMenu',
   	 // 同步：false,默认是true
   	 async : false,
   	 dataType: 'json',
   	 type:'GET',
   	 data:{
   		 
   	 },
   	 success : function(data){
   		var menuData = data;
   		
   		var firstCount = 0; 
   		for(var i = 0; i < menuData.length; i++) {
   			var menu = menuData[i];
   			var menuHtml="";
   			var secondMenuHtml="";
   			var oper = ""; //点击动作字符串
   			var css = ""; //css
   			if (menu != null && menu != ""  &&  menu.funcLevel == "1") {
   				if(menu.funcUrl != null && menu.funcUrl != "" && menu.funcUrl != "*.jsp") {
   					oper = 'onclick=\'$("#indexiframe").attr("src","page/' + menu.funcUrl + '");$("#cnt-title").html("'+menu.funcName+'");\'';
   					if(i==0)homePage = "" + menu.funcUrl;
   				}
   				//根据code判断菜单小图标
   				if (menu.funcCode == "LIVING_HALL") {
   					css += "acc-ico";
   				} else if (menu.funcCode == "ABILITY_CUBE") {
   					css += "acc-ico";
   				} else if (menu.funcCode == "STRATEGY_CENTER") {
   					css += "acc-ico";
   				} else if (menu.funcCode == "ELEMENT_MGNT") {
   					css += "acc-ico";
   				} else if (menu.funcCode == "SYS_MGNT") {
   					css += "acc-ico";
   				} else if (menu.funcCode == "MAINTENANCE_MENT") {
   					css +="acc-ico";
   				}else if (menu.funcCode == "CRFLURUZIDONGHUA") {//sys_func表的func_Code字段，对应前台显示的左侧列图标
   					css +="goods-ico";
   				}else if (menu.funcCode == "GAOJISOUSUOZIDONGHUA") {
   					css +="busi-ico";
   				}else if (menu.funcCode == "EMPIZIDONGHUA") {
   					css +="acc-ico";
   				}else {
   					css +="acc-ico";
   				}
   				css =" class=\""+css+"\" ";
   				if(menu.children != null && menu.children.length > 0) {
   					secondMenuHtml="<ul class=\"second-menu\">";
   					for (var y = 0; y < menu.children.length; y++) {
   						var secondMenu = menu.children[y];
   						var secondeOper = "";
   						if (secondMenu.funcUrl != null && secondMenu.funcUrl != "" && secondMenu.funcUrl != "*.jsp") {
   							secondeOper = 'onclick=\'$("#indexiframe").attr("src","page/' + secondMenu.funcUrl + '");$("#cnt-title").html("'+secondMenu.funcName+'");\'';
   							if(y==0){
   								secondMenuHtml+="<li id=\"defaultPage"+firstCount+"\" idsrc=\"page/"+secondMenu.funcUrl+"\" "+secondeOper+">"+secondMenu.funcName+"</li>"
   								firstCount++;
   							}else{
   								secondMenuHtml+="<li "+secondeOper+">"+secondMenu.funcName+"</li>"
   							}
   						}
   					}
   					secondMenuHtml+="</ul>";
   					menuHtml = "<li class=\"frt hasMenu\"><span "+oper+" ><i id=\""+menu.funcId+"\"" +css+"></i>"+menu.funcName+"</span><em class=\"m-arrow\">></em>"+secondMenuHtml+"</li>";
   				} else {
   					menuHtml = "<li id=\"defaultPage"+firstCount+"\" idsrc=\"page/+"+menu.funcUrl+"\" class=\"frt\"><span "+oper+" ><i id=\""+menu.funcId+"\"" +css+"></i>"+menu.funcName+"</span></li>";
   					firstCount++;
   				}
   				$("#menu").append(menuHtml);
   				
   				setDefaultPage();
   			}
   		}
   	 },
   	 error : function(data){
   	 	 alert("请求失败");
   	 }
   });

};

