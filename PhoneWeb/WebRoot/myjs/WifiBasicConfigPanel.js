WifiBasicConfigPanel = Ext.extend(Ext.panel.Panel,{
	myid : 'defaultId',
	//无线基本设置面板
	wifiBasicConfigForm : false,
	//下拉菜单Store
	channelComboboxStore : false,
	modeComboboxStore : false,
	bandwidthComboboxStore : false,
	constructor : function(_config){
		if(_config == null){
			_config = {};
		}//将config附加到当前对象
	Ext.apply(this, _config);
	//实例化本地数据源Store
	channelComboboxStore = new Ext.data.Store({
		fields : ['channel'],
		data : [
		        {channel:'自动'},
		        {channel:'1'},
		        {channel:'2'},
		        {channel:'3'},
		        {channel:'4'},
		        {channel:'5'},
		        {channel:'6'},
		        {channel:'7'},
		        {channel:'8'},
		        {channel:'9'},
		        {channel:'10'},
		        {channel:'11'},
		        {channel:'12'},
		        {channel:'13'}]
	});
	modeComboboxStore = new Ext.data.Store({
		fields : ['mode'],
		data : [
		        {mode:'11a'},
		        {mode:'11b'},
		        {mode:'11g'}]
	});
//	bandwidthComboboxStore = new Ext.data.Store({
//		fields : ['bandwidth'],
//		data : [
//		        {bandwidth:'自动'},
//		        {bandwidth:'20MHz'},
//		        {bandwidth:'40MHz'}]
//	});
	//实例化无线基本设置面板
	wifiBasicConfigForm = new Ext.form.Panel({
		region : 'center',
		frame : false,
		layout : 'border',
		viewConfig : {
			forceFit : true
		},
		items : [
		         {
		        	 region : 'north',
		        	 xtype : 'label',
		        	 html : '本页面设置路由器无线网络的基本参数。'
		         },
		         {
		        	 region : 'center',
		        	 xtype : 'form',
		        	 //title : '基本设置',
		        	 //html : '在此查看和修改基本设置'
		        	 frame : true,
		        	 autoScroll : true,
		        	 fieldDefaults : {
		        	 	labelWidth : 80
		         	 },
		        	 items : [
		        	          {
		        	        	  xtype : 'textfield',
		        	        	  fieldLabel : 'SSID',
		        	        	  name : 'ssid'
		        	          },
		        	          {
		        	        	  xtype : 'combo',
		        	        	  name : 'channel',
		        	        	  fieldLabel : '信道',
		        	        	  width : 150,
		        	        	  listConfig : {
		        	        	  		maxWidth : 60
		        	          	  },
		        	        	  triggerAction : 'all',
		        	        	  store : channelComboboxStore,
		        	        	  displayField : 'channel',
		        	        	  queryMode : 'local',
		        	        	  forceSelection : true,
		        	        	  typeAhead : true
		        	        	  //value : '自动'
		        	          },
		        	          {
		        	        	  xtype : 'combo',
		        	        	  name : 'mode',
		        	        	  fieldLabel : '模式',
		        	        	  width : 150,
		        	        	  listConfig : {
		        	        	  		maxWidth : 60
		        	          	  },
		        	        	  triggerAction : 'all',
		        	        	  store : modeComboboxStore,
		        	        	  displayField : 'mode',
		        	        	  queryMode : 'local',
		        	        	  forceSelection : true,
		        	        	  typeAhead : true
		        	        	  //value : '11g'
		        	          },
//		        	          {
//		        	        	  xtype : 'combo',
//		        	        	  id : 'bandwidth',
//		        	        	  fieldLabel : '频段带宽',
//		        	        	  width : 150,
//		        	        	  listConfig : {
//		        	        	  		maxWidth : 60
//		        	          	  },
//		        	        	  triggerAction : 'all',
//		        	        	  store : bandwidthComboboxStore,
//		        	        	  displayField : 'bandwidth',
//		        	        	  queryMode : 'local',
//		        	        	  forceSelection : true,
//		        	        	  typeAhead : true,
//		        	        	  value : '自动'
//		        	          },
//		        	          {
//		        	        	  xtype : 'checkboxfield',
//		        	        	  name : 'openwifi',
//		        	        	  boxLabel : '开启无线功能',
//		        	        	  inputValue : 'true'
//		        	          },
		        	          {
		        	        	  xtype : 'textfield',
		        	        	  name : 'wifipw',
		        	        	  fieldLabel : '无线密码'
		        	          },
		        	          {
		        	        	  xtype : 'checkboxfield',
		        	        	  name : 'broadcast',
		        	        	  boxLabel : '开启SSID广播',
		        	        	  inputValue : 'true'
		        	          }],
		        	 buttonAlign : 'center',
		        	 buttons : [{
		        		  			text : '保存',
		        		  			height : 30,
		        		  			width : 80,
		        		  			handler : this.submitForm
		        	  			}]
		         }]
	});
	/*将父类的构造拷贝过来*/
	IndexPage.superclass.constructor.call(this, {
		id : this.myid, // 唯一id
		iconCls : "houfei-treeNodeLeafIcon",
		layout : "border", // 指定布局为border布局
		items : [ wifiBasicConfigForm]
	})
	},
	submitForm : function(){
//			Ext.Ajax.request({
//			url : 'setWifiBasicConfig.action',
//			params : {
//				ssid : Ext.getCmp('ssid').getValue(),
//				channel : Ext.getCmp('channel').getValue(),
//				mode : Ext.getCmp('mode').getValue(),
//				bandwidth : Ext.getCmp('bandwidth').getValue(),
//				openwifi : Ext.getCmp('openwifi').getValue(),
//				broadcast : Ext.getCmp('broadcast').getValue()
//			},
//			success : function(response){
//				Ext.Msg.show( {
//					title : '提示',
//					msg : '保存成功！',
//					buttons : Ext.Msg.YES
//				});
//			},
//			failure : function(response, opts){
//				Ext.Msg.show( {
//					title : '提示',
//					msg : '保存失败！',
//					buttons : Ext.Msg.YES
//				});
//			}
//		})
		wifiBasicConfigForm.form.submit({
			clientValidation : true,
			waitMsg : '正在保存请稍后...',
			waitTitle : '提示',
			url : 'setWifiBasicConfig.action',
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
		})
	},
	loadForm : function(){
		wifiBasicConfigForm.form.load({
			waitMsg : '正在加载数据请稍后...',
			waitTitle : '提示',
			url : 'showWifiBasicConfig.action',
			method : 'GET',
			failure:function(form,action){
				Ext.Msg.alert('提示','数据加载失败');
			}
		});
	}
})