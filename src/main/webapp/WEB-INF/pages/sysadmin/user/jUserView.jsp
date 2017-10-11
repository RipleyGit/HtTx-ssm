<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户新增</title>
</head>

<body>
<form name="icform" method="post">


   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    用户查看
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<tr class="odd" hidden="hidden">
		<td><input name="userId" type="text" value="${ user.userId }"/></td>
	</tr>
	<tr class="odd">
		<td>用户名：</td>
		<td>${ user.username }</td>
		<td>密码：</td>
		<td>*******</td>
	</tr>
	<tr class="odd">
		<td>所属部门：</td>
		<td>
			${ user.dept.deptName } 
		</td>
		<td>真实姓名：</td>
		<td>${ user.userInfo.name }</td>
	</tr>
	<tr class="odd">
		<td>身份证号：</td>
		<td>${ user.userInfo.cardNo }<input name="userInfo.cardNo" type="text"/></td>
		<td>直属领导：</td>
		<td>
			<c:if test="${user.userInfo.manager != null}">${ user.userInfo.manager.name }</c:if>
			<c:if test="${user.userInfo.manager == null}">无上级</c:if>
		</td>
	</tr>
	<tr class="odd">
		<td>入职时间：</td>
		<td>${ user.userInfo.joinDate }</td>
		<td>薪水：</td>
		<td>${ user.userInfo.salary }</td>
	</tr>
	<tr class="odd">
		<td>生日：</td>
		<td>${ user.userInfo.birthday }</td>
		<td>性别：</td>
		<td>${ user.userInfo.gender }</td>
	</tr>
		<tr class="odd">
		<td>岗位：</td>
		<td>${ user.userInfo.station }</td>
		<td>电话：</td>
		<td>${ user.userInfo.telephone }</td>
	</tr>
	</tr>
		<tr class="odd">
		<td>等级：</td>
		<td>
			<c:if test="user.userInfo.userLevel == 1">总经理</c:if>
			<c:if test="user.userInfo.userLevel == 2">>副总</c:if>
			<c:if test="user.userInfo.userLevel == 3">部门经理</c:if>
			<c:if test="user.userInfo.userLevel == 4">普通用户</c:if>
		</td>
		<td>排序号：</td>
		<td>${ user.userInfo.orderNo }</td>
	</tr>
	<tr class="odd">
		<td>状态：</td>
		<td>
			<c:if test="${dept.state ==0}">停用</c:if>
			<c:if test="${dept.state ==1}">启用</c:if>
		</td>
	</tr>
	<tr class="odd">
		<td>备注信息：</td>
		<td colspan="3">
			<textarea style="width:100%;height:80px" name="userInfo.remark" readonly></textarea>
		</td>
	</tr>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

