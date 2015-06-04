SystemToolsPanel = Ext.extend(Ext.panel.Panel,{
	myid : "defaultId",
	//系统工具面板容器
	systemToolsPanel : false,
	constructor : function(_config) {
		if (_config == null) {
			_config = {};
		}// 将_config附加到当前对象
	Ext.apply(this, _config);
	//实例化系统工具面板容器
	systemToolsPanel = new Ext.panel.Panel({
		region : "center",
		layout : 'border',
		frame : false,
		viewConfig : {
			forceFit : false
		},
		listeners : {
			'render' : function(){
				setButtonText();
			}
		},
		items : [
		         {
		        	 region : 'north',
		        	 xtype : 'panel',
		        	 title : '系统时间',
		        	 frame : true,
		        	 //html : '在这里设置时间'
		        	 items : [
		        	          {
		        	        	  height : 30,
		        	        	  xtype : 'label',
		        	        	  id : 'sTime',
		        	        	  listeners : {
		        	        	  	'render' : function(){
		        	        	  		getTime();
		        	          		}
		        	          	}
		        	          }]
		         },
		         {
		        	 region : 'center',
		        	 xtype : 'form',
		        	 frame : true,
		        	 autoScroll : true,
		        	 title : '修改管理员密码',
		        	 items : [
		        	          {
		        	        	  xtype : 'textfield',
		        	        	  id : 'oldPw',
		        	        	  inputType : 'password',
		        	        	  fieldLabel : '输入旧密码'
		        	          },
		        	          {
		        	        	  xtype : 'textfield',
		        	        	  id : 'newPw',
		        	        	  inputType : 'password',
		        	        	  fieldLabel : '输入新密码'
		        	          },
		        	          {
		        	        	  xtype : 'textfield',
		        	        	  id : 'newPwAg',
		        	        	  inputType : 'password',
		        	        	  fieldLabel : '确认新密码'
		        	          }],
		        	  buttonAlign : 'center',
		        	  buttons : [
		        	             {
		        	            	 text : '修改',
		        	            	 width : 80,
		        	            	 height : 30,
		        	            	 //点击修改密码
		        	            	 handler : function(){
		        	            	 	Ext.Ajax.request({
		        	            	 		url : 'identifyPassword.action',
		        	            	 		params : {
		        	            	 			loginPw : Ext.getCmp('oldPw').getValue()
		        	            	 		},
		        	            	 		success : function(response){
		        	            	 			var res =Ext.decode(response.responseText);
		        	            	 			if(res.success == true){
		        	            	 				var newPw1 = Ext.getCmp('newPw').getValue();	
			        	            	 			var newPw2 = Ext.getCmp('newPwAg').getValue();
			        	            	 			if(newPw1 == newPw2 && newPw1 != ''){
			        	            	 				Ext.Ajax.request({
			        	            	 					url : 'changePassword.action',
			        	            	 					params : {
			        	            	 						loginPw : Ext.getCmp('newPw').getValue()
			        	            	 					},
			        	            	 					success : function(response){
			        	            	 						Ext.Msg.show( {
			        												title : '操作提示',
			        												msg : '修改成功！',
			        												buttons : Ext.Msg.YES
			        											});
			        	            	 						Ext.getCmp('oldPw').reset();
			        			        	            	 	Ext.getCmp('newPw').reset();
			        			        	            	 	Ext.getCmp('newPwAg').reset();
			        	            	 					},
			        	            	 					failure : function(response, opts){
			        	            	 						Ext.Msg.show( {
			    													title : '操作提示',
			    													msg : '修改失败！',
			    													buttons : Ext.Msg.YES
			    												});
			        	            	 					}
			        	            	 				});
			        	            	 			}
			        	            	 			else {
			        	            	 				Ext.Msg.show({
			        	            	 					title : '操作提示',
															msg : '两次输入的新密码不一致或新密码为空！',
															buttons : Ext.Msg.YES
			        	            	 				});
			        	            	 			}
		        	            	 			}
		        	            	 			else {
		        	            	 				Ext.Msg.show( {
														title : '操作提示',
														msg : '原密码错误！',
														buttons : Ext.Msg.YES
													});
		        	            	 			}
		        	            	 		},
		        	            	 		failure : function(response, opts) {
												Ext.Msg.show( {
													title : '操作提示',
													msg : '操作失败！',
													buttons : Ext.Msg.YES
												});
											}
		        	            	 	});
		        	            	 	//Ext.getCmp('oldPw').reset();
		        	            	 	//Ext.getCmp('newPw').reset();
		        	            	 	//Ext.getCmp('newPwAg').reset();
		        	             }
		        	             }]
		         },
		         {
		        	 region : 'south',
		        	 xtype : 'panel',
		        	 title : '设备控制',
		        	 autoScroll : true,
		        	 frame : true,
		        	 height : 100,
		        	 buttonAlign : 'left',
		        	 buttons : [
		        	            {
		        	            	text : '重启设备',
		        	            	height : 50,
		        	            	width : 160,
		        	            	handler : this.restartDevice
		        	            },
		        	            {
		        	            	text : '关闭设备',
		        	            	height : 50,
		        	            	width : 160,
		        	            	handler : this.shutdownDevice
		        	            },
		        	            {
		        	            	text : '恢复初始设置',
		        	            	height : 50,
		        	            	width : 160,
		        	            	handler : this.resetAll
		        	            }]
//		        	 items : [
//		        	          	 {
//		        	          		 xtype : 'button',
//		        	          		 text : '重启设备',
//		        	          		 height : 50,
//		        	          		 width : 160,
//		        	          		 style : 'margin-Top : 5px',
//		        	          		 handler : this.restartDevice
//		        	          	 },
//		        	          	 {
//		        	          		 xtype : 'button',
//		        	          		 text : '关闭设备',
//		        	          		 height : 50,
//		        	          		 width : 160,
//		        	          		 style : 'margin-Top : 15px',
//		        	          		 handler : this.shutdownDevice
//		        	          	 },
//		        	          	 {
//		        	          		 xtype : 'button',
//		        	          		 text : '恢复出厂设置',
//		        	          		 height : 50,
//		        	          		 width : 160,
//		        	          		 style : 'margin-Top : 15px',
//		        	          		 handler : this.resetAll
//		        	          	 }]
		         }]
	});
	//获取时间
	function getTime(){
		Ext.TaskManager.start({
			run : function(){
				Ext.getCmp("sTime").setText('当前时间：'+Ext.Date.format(new Date(), 'Y-m-d,g:i:s A'));
			},
			interval : 1000
		});
	};
	function setButtonText(){
		Ext.MessageBox.buttonText = {
				ok : '好',
				cancel : '取消',
				yes : '确定',
				no : '取消'
		};
	}
	/*将父类的构造拷贝过来*/
	IndexPage.superclass.constructor.call(this, {
		id : this.myid, // 唯一id
		iconCls : "houfei-treeNodeLeafIcon",
		layout : "border", // 指定布局为border布局
		items : [ systemToolsPanel]
	});
	},
	restartDevice : function(){
		Ext.Msg.confirm('警告','确定要重新启动设备吗？',function(button){
			if(button == 'yes'){
				Ext.Ajax.request({
					url : 'restartDevice.action',
					success : function(response){
						Ext.Msg.show({
							title : '操作提示',
							msg : '设备即将重启，请稍后...',
							buttons : Ext.Msg.YES
						});
					},
					failure : function(response, opts){
						Ext.Msg.show({
							title : '操作提示',
							msg : '操作失败！',
							buttons : Ext.Msg.YES
						});
					}
				});
			}
		});
	},
	shutdownDevice : function(){
		Ext.Msg.confirm('警告','确定要关闭设备吗？',function(button){
			if(button == 'yes'){
				Ext.Ajax.request({
					url : 'shutDownDevice.action',
					success : function(response){
						Ext.Msg.show({
							title : '操作提示',
							msg : '设备即将关闭，请稍后...',
							buttons : Ext.Msg.YES
						});
					},
					failure : function(response, opts){
						Ext.Msg.show({
							title : '操作提示',
							msg : '操作失败！',
							buttons : Ext.Msg.YES
						});
					}
				});
			}
		});
	},
	resetAll : function(){
		Ext.Msg.confirm('警告','确定要恢复初始设置吗？',function(button){
			if(button == 'yes'){
				//alert('恢复了！');
				Ext.Ajax.request({
					url : 'resetAll.action',
					success : function(response){
						var res = Ext.decode(response.responseText);
						if(res.success == true){
							Ext.Msg.show({
								title : '操作提示',
								msg : '您的设备已经恢复到初始设置！',
								buttons : Ext.Msg.YES
							});
						}
						else {
							Ext.Msg.show({
								title : '操作提示',
								msg : '操作失败！',
								buttons : Ext.Msg.YES
							});
						}
					},
					failure : function(response, opts){
						Ext.Msg.show({
							title : '操作提示',
							msg : '操作失败！',
							buttons : Ext.Msg.YES
						});
					}
				});
			}
		});
	}
})