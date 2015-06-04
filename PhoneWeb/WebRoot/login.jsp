<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>手机安全-登陆</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />	
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">   	
        <link href="css/houfei-login.css" rel="stylesheet" type="text/css" />
    </head>

	<body>
  <center>
      <form action="login.action" method="post">
      <div class="loginDiv" style="">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="17%" height="106">&nbsp;</td>
            <td width="16%">&nbsp;</td>
            <td width="47%">&nbsp;</td>
            <td width="1%">&nbsp;</td>
            <td width="19%">&nbsp;</td>
          </tr>
          <tr>
            <td height="32">&nbsp;</td>
            <td><span class="myFont">用户名：</span></td>
            <td><input name="userName" type="text" class="loginTextField" id="textfield"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><span class="myFont">密　码：</span></td>
            <td><input name="password" type="password" class="loginTextField" id="textfield2"/></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr> 
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>
              <input name="button" type="submit" style="widows: 100px;" class="loginBtn" id="button" value="提交" />
              <input name="button3" type="reset"  class="loginBtn" id="button3" value="重置">
              <html:errors property="validCodeError" />
              <html:errors property="pwdError"/>
              <html:errors property="userNameError"/>
            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
      </div>
      </form>
  </center>  

</body>
</html>