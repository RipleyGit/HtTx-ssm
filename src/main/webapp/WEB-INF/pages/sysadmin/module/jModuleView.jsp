<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>模块新增</title>
	<script>
		function textdown(e){  
			textevent = e ;   
			if(textevent.keyCode == 8){   
				return;   
			}   
			if(document.getElementById('text').value.length >= 100){   
				alert("最多输入100个字")    
					if(!document.all){   
					textevent.preventDefault();   
					}else{   
						textevent.returnValue = false;   
					}   
				}   
		}   
		function textup(){   
			var s = document.getElementById('text').value;   
			//判断ID为text的文本区域字数是否超过1000个   
			if(s.length > 100){   
				document.getElementById('text').value=s.substring(0,100);   
			}   
		}
		function ctypeChange(ctype){
			alert(ctype);
			var parentSelect = document.getElementById("parentMod");
			
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
	<li id="save"><a href="#" onclick="formSubmit('save','_self');this.blur();">保存</a></li>
	<li id="new"><a href="#" onclick="window.history.go(-1)">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    模块新增
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<tr class="odd" hidden="hidden">
		<td><input name="moduleId" type="text" value="${modu.moduleId}" readonly="readonly"/></td>
	</tr>
	<tr class="odd">
		<td>模块名称:</td>
		<td><input name="name" type="text" value="${ modu.name }" readonly="readonly"/></td>
	</tr>
	<tr class="odd">
		<td>模块类型:</td>
		<td>
			<select name="ctype" style="width:121px" onchange="ctypeChange(this.value)" disabled>
				<option >---请选择---</option>
				<option value="1" <c:if test="${ modu.ctype ==1 }">selected="selected"</c:if>>主菜单</option>
				<option value="2" <c:if test="${ modu.ctype ==2 }">selected="selected"</c:if>>左侧菜单</option>
				<option value="3" <c:if test="${ modu.ctype ==3 }">selected="selected"</c:if>>按钮</option>
			</select>
			
		</td>
	</tr>
	<tr class="odd">
		<td>上级模块:</td>
		<td>
			<select name="parentModule.moduleId" id="parentMod" style="width:121px" disabled>
				<option value="">---请选择/无上级---</option>
				<c:forEach items="${moduleList}" var="m">
					<option <c:if test="${ modu.moduleId == m.moduleId }">selected="selected"</c:if>>${m.name}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr class="odd">
		<td>排序号:</td>
		<td><input name="orderNo" type="text" value="${modu.orderNo}" readonly="readonly"/></td>
	</tr>
	<tr class="odd">
		<td>状态:</td>
		<td>
			<input name="state" type="radio" value="1" <c:if test="${ modu.state == 1 }">checked="checked"</c:if> readonly="readonly"/>启用
			<input name="state" type="radio" value="0" <c:if test="${ modu.state == 0 }">checked="checked"</c:if> readonly="readonly"/>停用
		</td>
	</tr>
	<tr class="odd">
		<td>备注信息:</td>
		<td>
			<textarea id="text" style="width:100%;height:120px" name="remark" onKeyDown="textdown(event)" onKeyUp="textup()" readonly="readonly">${ modu.remark }</textarea>
		</td>
	</tr>
	
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

