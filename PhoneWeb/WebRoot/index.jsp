<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String ctxpath = request.getContextPath();
	String Login = (String)session.getAttribute("isLogin");
	if (Login != null && Login.equals("Y")) 
	{
	}   
	else 
	{  
		response.setHeader("Refresh","0;URL=login.jsp");    
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>流量包监测系统</title>
		<%-- 自定义CSS样式 --%>
		<link href="css/gridcss.css" rel="stylesheet" type="text/css" />
		<link href="css/MainContainer.css" rel="stylesheet" type="text/css" />
		<link href="css/metaData.css" rel="stylesheet" type="text/css">
		<link href="css/icon.css" rel="stylesheet" type="text/css" />
		<link href="css/loading.css" rel="stylesheet" type="text/css" />
		<link href="css/login.css" rel="stylesheet" type="text/css" />
		<%-- Ext必需的js/css文件 --%>
		<link rel="stylesheet" type="text/css"
			href="ext4/resources/css/ext-all.css" />
		<script type="text/javascript" src="ext4/bootstrap.js"></script>
		<script type="text/javascript" src="ext4/locale/ext-lang-en.js"></script>
		<%-- 主页 --%>
		<script type="text/javascript" src="myjs/IndexPage.js"></script>
		<script type="text/javascript" src="myjs/DayFlowPanel.js"></script>
		<script type="text/javascript" src="myjs/TimesFlowPanel.js"></script>
		<script type="text/javascript" src="myjs/PhoneSafetyPanel.js"></script>
		<script type="text/javascript" src="myjs/AccessControlPanel.js"></script>
		<script type="text/javascript" src="myjs/SystemConfigPanel.js"></script>
		<script type="text/javascript" src="myjs/RunStatePanel.js"></script>
		<script type="text/javascript" src="myjs/SystemToolsPanel.js"></script>
		<script type="text/javascript" src="myjs/DHCPServicePanel.js"></script>
		<script type="text/javascript" src="myjs/NetworkParameterPanel.js"></script>
		<script type="text/javascript" src="myjs/WifiBasicConfigPanel.js"></script>
		<script type="text/javascript" src="myjs/WifiSafetyConfigPanel.js"></script>
		<script type="text/javascript" src="myjs/ClientStatePanel.js"></script>
		<script type="text/javascript" src="myjs/ClientListPanel.js"></script>
		<script type="text/javascript" src="myjs/PPPOEParameterPanel.js"></script>
	</head>

	<script type="text/javascript"><!--
	  Ext.Loader.setConfig({ enabled: true });
	  Ext.Loader.setPath('Ext.ux', '/ext4/ux');
	  Ext.onReady(function(){
           <%-- 加载效果 --%>
			setTimeout(function() {
				Ext.get('loading').remove();
				Ext.get('loading-mask').fadeOut({remove:true});
			}, 1000); 
	       <%-- 开启提示 --%>
	        Ext.QuickTips.init() ;
			Ext.form.Field.prototype.msgTarget = "side" ;
			<%-- 实例化主页 --%>
		    var _index= new IndexPage();
		    <%-- 窗体大小改变延时事件 --%>
	        window.onresize=function(){
	        <%-- 过100毫秒在执行  --%>
	        setTimeout(_index.onActiveTabSize,100);
	        };
	  });
	--></script>
	</head>

	<body>
		<div id="loading">
			<div class="loading-indicator"> 
				c&quot;<img src="images/extanim32.gif" alt="" width="32" height="32"
					style="margin-right: 8px;" align="absmiddle" />
				正在加载,请稍候......
			</div>
		</div>
		<div id="loading-mask">
		</div>
	</body>
</html>
