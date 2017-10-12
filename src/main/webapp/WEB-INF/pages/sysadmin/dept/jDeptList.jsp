<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>部门列表</title>
	<script>
		function getChecked(){
			var checkBox = document.getElementsByName("deptId");
			var checked = new Array();
			for(var i = 0;i<checkBox.length;i++){
				if(checkBox[i].checked){
					checked.push(checkBox[i]);
				}
			}
			return checked;
		}
		
		function toView(){
			var checked = getChecked();
			if(checked.length == 1){
				formSubmit('toview','_self');
			}else{
				alert("请选择一个部门进行查看！");
				return fasle;
			}
		}
		function toUpdate(){
			var checked = getChecked();
			if(checked.length == 1){
				formSubmit('toupdate','_self');
			}else{
				alert("请选中一个部门！");
				return fasle;
			}
		}
		function toDelete(){
			var checked = getChecked();
			if(checked.length > 0){
				if(confirm("你确定删除该用户？")){
				formSubmit('delete','_self');
				}else{
					return false;
				}
			}else{
				alert("请勾选删除的部门");
				return false;
			}
		}
		function toStart(){
			var checked = getChecked();
			if(checked.length > 0){
				formSubmit('start','_self');
			}else{
				alert("请选至少一个启用的部门！");
				return false;
			}
			
		}
		function toStop(){
			var checked = getChecked();
			if(checked.length > 0){
				formSubmit('stop','_self');
			}else{
				alert("请选至少一个停用的部门！");
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
	<li id="new"><a href="#" onclick="toStart();this.blur();">启用</a></li>
	<li id="new"><a href="#" onclick="toStop();this.blur();">停用</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    部门列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('deptId',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">编号</td>
		<td class="tableHeader">上级</td>
		<td class="tableHeader">名称</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<c:forEach items="${deptList}" var="d" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
		<td><input type="checkbox" name="deptId" value="${d.deptId}"/></td>
		<td>${status.index+1}</td>
		<td>${d.deptId}</td>
		<td>${d.parentDept.deptName}</td>
		<td><a href="dept/toview?id=${d.deptId}">${d.deptName}</a></td>
		<td>
			<c:if test="${d.state==1}"><a href="stop?deptId=${d.deptId}"><font color="green">启用</font></a></c:if>
			<c:if test="${d.state==0}"><a href="start?deptId=${d.deptId}"><font color="red">停用</font></a></c:if>
		</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

