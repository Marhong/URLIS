/**
  * @Description: 所有页面都能够通用的js函数
  * @Time: 2018年8月28日 下午17:11:55
  * @author: wangbin
*/
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/jquery.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/jquery.sorted.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/bootstrap.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/ckform.js'></script>");
document.write("<script language='javascript' src='"+getRootPath()+"/static/js/common.js'></script>");


//获取工程绝对路径
function getRootPath(){
	   var currentPagepath=location.href;
	   var pathName = window.document.location.pathname;
	   var pos = currentPagepath.indexOf(pathName);
	   var localhostPath = currentPagepath.substring(0,pos);
	   var projectName = pathName.substring(0,pathName.substr(1).indexOf("/")+1);
	   return localhostPath+projectName;
	   
}
function init(){
	//init
	$(function(){
		var totalPage = $('#totalPage').val();
		var totalRecords = $('#totalRecords').val();
		var pageNo = getParameter('pno');
		if(!pageNo){
			pageNo = 1;
		}
		//生成分页
		//有些参数是可选的，比如lang，若不传有默认值
		kkpager.generPageHtml({
			pno : pageNo,
			//总页码
			total : totalPage,
			//总数据条数
			totalRecords : totalRecords,
			//链接前部
			hrefFormer : 'index',
			//链接尾部
			hrefLatter : '',
			getLink : function(n){	
				return this.hrefFormer + this.hrefLatter + "?pno="+n;
			},		

		});
	});
}
function getParameter(name) { 
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return unescape(r[2]); return null;
}

