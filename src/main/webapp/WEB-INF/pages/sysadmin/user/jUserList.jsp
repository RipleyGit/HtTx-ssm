<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户列表</title>
	<script>
		function getChecked(){
			var checkBox = document.getElementsByName("userId");
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
				alert("请选择一个用户进行查看！");
				return fasle;
			}
		}
		function toUpdate(){
			var checked = getChecked();
			if(checked.length == 1){
				formSubmit('toupdate','_self');
			}else{
				alert("请选中一个用户！");
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
				alert("请勾选删除的用户");
				return false;
			}
		}
		function toStart(){
			var checked = getChecked();
			if(checked.length > 0){
				formSubmit('start','_self');
			}else{
				alert("请选至少一个启用的用户！");
				return false;
			}
			
		}
		function toStop(){
			var checked = getChecked();
			if(checked.length > 0){
				formSubmit('stop','_self');
			}else{
				alert("请选至少一个停用的用户！");
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
    用户列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('userId',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">用户名</td>
		<td class="tableHeader">真实姓名</td>
		<td class="tableHeader">直属领导</td>
		<td class="tableHeader">所属部门</td>
		<td class="tableHeader">入职日期</td>
		<td class="tableHeader">薪水</td>
		<td class="tableHeader">岗位</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<c:forEach items="${userList}" var="u" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
		<td><input type="checkbox" name="userId" value="${u.userId}"/></td>
		<td>${status.index+1}</td>
		<td>${u.username}</td>
		<td>${u.userInfo.name}</td>
		<td>${u.userInfo.manager.name}</td>
		<td>${u.dept.deptName}</td>
		<td> <fmt:formatDate value="${u.userInfo.joinDate}" pattern="yyyy-MM-dd"/></td>
		<td>${u.userInfo.salary}</td>
		<td>${u.userInfo.station}</td>
		<td>
			<c:if test="${u.state==1}"><a href="stop?userId=${u.userId}"><font color="green">启用</font></a></c:if>
			<c:if test="${u.state==0}"><a href="start?userId=${u.userId}"><font color="red">停用</font></a></c:if>
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

