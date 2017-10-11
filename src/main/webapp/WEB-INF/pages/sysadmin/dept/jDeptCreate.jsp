<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>部门列表</title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
	<li id="save"><a href="#" onclick="formSubmit('save','_self');this.blur();">保存</a></li>
	<li id="back"><a href="#" onclick="window.history.go(-1);this.blur();">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    部门新增
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<tr class="odd">
		<td>部门编号：</td>
		<td><input name="deptId" type="text"/></td>
	</tr>
	<tr class="odd">
		<td>部门名称：</td>
		<td><input name="deptName" type="text"/></td>
	</tr>
	<tr class="odd">
		<td>上级部门：</td>
		<td>
			<select name="parentDept.deptId" style="width:121px">
				<option value="0">---请选择/无上级---</option>
				<c:forEach items="${parentDeptList}" var="p">
					<option value="${p.deptId}">${p.deptName}</option>
				</c:forEach>	
			</select>
		</td>
	</tr>
	<tr class="odd">
		<td>部门状态：</td>
		<td>
			<input name="state" type="radio" value="0"/>停用
			<input name="state" type="radio" value="1"/>启用
		</td>
	</tr>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

