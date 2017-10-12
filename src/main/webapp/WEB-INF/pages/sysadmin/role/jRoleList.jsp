<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>角色列表</title>
	<script>
		function getChecked(){
			var checkBox = document.getElementsByName("roleId");
			var checked = new Array();
			for(var i = 0; i<checkBox.length;i++){
				if(checkBox[i].checked){
					checked.push(checkedBox[i]);
				}
			}
			return checked;
		}
		function toView(){
			var checked = getChecked();
			if(checked.length == 1){
				formSubmit('toview','_self');
			}else{
				alert("请选择一条数据进行查看");
				return false;
			}
		}
		function toUpdate(){
			var checked = getChecked();
			if(checked.length == 1){
				formSubmit('toupdate','_self');
			}else{
				alert("请选择一条数据进行修改");
				return false;
			}
		}
		function toDelete(){
			var checked = getChecked();
			if(checked.length > 0){
				if(confirm("你确定删除这些数据？")){
					formSubmit('delete','_self');
				}
			}else{
				alert("请选中删除的数据！");
				return false;
			}
		}
	</script>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
	<li id="view"><a href="#" onclick="toView();this.blur();">查看</a></li>
	<li id="new"><a href="#" onclick="formSubmit('tocreate','_self');this.blur();">新增</a></li>
	<li id="update"><a href="#" onclick="toUpdate();this.blur();">修改</a></li>
	<li id="delete"><a href="#" onclick="toDelete();this.blur();">删除</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    角色列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('roleId',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">角色名称</td>
		<td class="tableHeader">备注信息</td>
		<td class="tableHeader">排序号</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<c:forEach items="${roleList}" var="r" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
		<td><input type="checkbox" name="roleId" value="${r.roleId}"/></td>
		<td>${status.index+1}</td>
		<td>${r.name}</td>
		<td>${r.remarks}</td>
		<td>${r.orderNo}</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

