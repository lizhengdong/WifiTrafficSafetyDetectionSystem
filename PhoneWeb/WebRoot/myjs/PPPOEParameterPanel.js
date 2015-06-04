PPPOEParameterPanel = Ext.extend(Ext.panel.Panel,{
	myid : "defauleId",
	//配置文件表单
	pppoeParameterForm : false,
	constructor : function(_config){
		if(_config == null){
			_config = {};
		}//将config附加到当前对象
	Ext.apply(this, _config);
	//实例化表单
	pppoeParameterForm = new Ext.form.Panel({
		region : 'center',
		frame : true,
		autoScroll : true,
		viewConfig : {
			forceFit : true
		},
		items : [
		         {
		        	 xtype : 'radiogroup',
		        	 fieldLabel : '开机自动拨号',
		        	 //name : 'onboot',
		        	 id : 'onbootradio',
		        	 width : 300,
		        	 columns : 2,
		        	 items : [
		        	          {
		        	        	  boxLabel : '是',
		        	        	  name : 'onboot',
		        	        	  width : 150,
		        	        	  inputValue : 'yes'
		        	          },
		        	          {
		        	        	  boxLabel : '否',
		        	        	  name : 'onboot',
		        	        	  width : 150,
		        	        	  inputValue : 'no'
		        	          }]
		         },
//		         {
//		        	 xtype : 'textfield',
//		        	 fieldLabel : '设备名',
//		        	 name : 'device'
//		         },
		         {
		        	 xtype : 'textfield',
		        	 fieldLabel : '用户名',
		        	 name : 'username'
		         },
		         {
		        	 xtype : 'textfield',
		        	 fieldLabel : '登录密码',
		        	 name : 'password'
		         }],
		 buttonAlign : 'center',
		 buttons : [
		            {
		            	text : '保存设置',
		            	height : 40,
		            	width : 150,
		            	handler : this.submitForm
		            },
		            {
		            	text : '拨号',
		            	height : 40,
		            	width : 150
		            },
		            {
		            	text : '断开',
		            	height : 40,
		            	width : 150
		            }]
	});
	/*将父类的构造拷贝过来*/
	IndexPage.superclass.constructor.call(this, {
		id : this.myid, // 唯一id
		iconCls : "houfei-treeNodeLeafIcon",
		layout : "border", // 指定布局为border布局
		items : [pppoeParameterForm]
	});
	},
	submitForm : function(){
		pppoeParameterForm.form.submit({
			clientValidation : true,
			waitMsg : '正在保存请稍后...',
			waitTitle : '提示',
			url : 'setPPPOEParameter.action',
			method : 'POST',
			success : function(response){
				Ext.Msg.show( {
					title : '提示',
					msg : '保存成功！',
					buttons : Ext.Msg.YES
				});
			},
			failure : function(response, opts){
				Ext.Msg.show( {
					title : '提示',
					msg : '保存失败！',
					buttons : Ext.Msg.YES
				});
			}
		});
	},
	loadForm : function(){
		pppoeParameterForm.form.load({
			waitMsg : '正在加载数据请稍后...',
			waitTitle : '提示',
			url : 'showPPPOEParameter.action',
			method : 'GET',
			failure : function(form,action){
				Ext.Msg.alert('提示','数据加载失败!');
			}
		});
	}
});