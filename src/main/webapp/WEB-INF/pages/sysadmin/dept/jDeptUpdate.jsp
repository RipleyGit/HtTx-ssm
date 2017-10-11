<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>部门修改</title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
	<li id="update"><a href="#" onclick="formSubmit('update','_self');this.blur();">修改</a></li>
	<li id="back"><a href="#" onclick="window.history.go(-1);this.blur();">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    部门修改
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<tr class="odd">
		<td>部门编号：</td>
		<td><input name="deptId" type="text" value="${dept.deptId}"/></td>
	</tr>
	<tr class="odd">
		<td>部门名称：</td>
		<td><input name="deptName" type="text" value="${dept.deptName}"/></td>
	</tr>
	<tr class="odd">
		<td>上级部门：</td>
		<td>
			<!--下拉列表的回显思路：
				1.获取当前下拉列表中的deptId
				2.获取修改数据的上级部门信息 parentDept.deptId
				3.比较二者是否相同，如果相同则添加属性selected="selected"
				ognl表达式  几乎没有人使用了，EasyUI框架  实现数据的自动回显
			 -->
			<select name="parentDept.deptId" style="width:121px">
				<option value="0">---请选择/无上级---</option>
				<c:forEach items="${parentList}" var="p">
					<option value="${p.deptId}" <c:if test="${p.deptId == dept.parentDept.deptId}">selected="selected"</c:if>>${p.deptName}</option>
				</c:forEach>	
			</select>
		</td>
	</tr>
	<tr class="odd">
		<td>部门状态：</td>
		<td>
			<input name="state" type="radio" value="0" <c:if test="${dept.state ==0}">checked="checked"</c:if>/>停用
			<input name="state" type="radio" value="1" <c:if test="${dept.state ==1}">checked="checked"</c:if>/>启用
		</td>
	</tr>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

