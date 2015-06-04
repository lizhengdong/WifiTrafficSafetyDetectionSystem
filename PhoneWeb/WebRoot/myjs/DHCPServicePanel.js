DHCPServicePanel = Ext.extend(Ext.panel.Panel,{
	myid : 'defaultId',
	//DHCP面板容器
	dhcpServiceForm : false,
	//客户端Store
	clientStore : false,
	constructor : function(_config) {
		if (_config == null) {
			_config = {};
		}// 将_config附加到当前对象
	Ext.apply(this, _config);
	//实例化Store
	clientStore = new Ext.data.Store({
		fields : ['clientId', 'clientName','clientMac','clientIp','validTime'],
		proxy : {
			type : 'ajax',
			url : 'null.json',
			reader : {
				type : 'json'
			}
		},
		autoLoad : {
			start : 0,
			limit : 20
		},
		pageSize : 20
	});
	//实例化DHCP面板容器
	dhcpServiceForm = new Ext.form.Panel({
		region : 'center',
		layout : 'border',
		frame : false,
		viewConfig : {
			forceFit : true
		},
		items : [
		         {
		        	 region : 'center',
		        	 xtype : 'form',
		        	 frame : true,
		        	 title : 'DHCP配置',
		        	 autoScroll : true,
		        	 height : 320,
		        	 //html : '在这里配置DHCP参数'
		        	 fieldDefaults : {
		        	 	labelWidth : 150,
		        	 	labelAlign : 'right'
		         		},
		        	 items : [
//		        	          {
//		        	        	xtype : 'radiogroup',
//		        	        	fieldLabel : 'DHCP服务器',
//		        	        	width : 300,
//		        	        	columns : 2,
//		        	        	name : 'dhcpService',
//		        	        	id : 'dhcpRadio', 
//		        	        	items : [
//		        	        	         {
//		        	        	        	 boxLabel : '启用',
//		        	        	        	 name : 'dhcpService',
//		        	        	        	 width : 150,
//		        	        	        	 inputValue : 'Y',
//		        	        	        	 id : 'dhcpRadio1',
//		        	        	        	 checked : true
//		        	        	         },
//		        	        	         {
//		        	        	        	 boxLabel : '不启用',
//		        	        	        	 name : 'dhcpService',
//		        	        	        	 width : 150,
//		        	        	        	 inputValue : 'N',
//		        	        	        	 id : 'dhcpRadio2',
//		        	        	        	 checked : false
//		        	        	         }]
//		        	          },
		        	          {
		        	        	  xtype : 'textfield',
		        	        	  fieldLabel : '地址池开始地址',
		        	        	  name : 'startIp'
		        	          },
		        	          {
		        	        	  xtype : 'textfield',
		        	        	  fieldLabel : '地址池结束地址',
		        	        	  name : 'endIp'
		        	          },
		        	          {
		        	        	  xtype : 'textfield',
		        	        	  fieldLabel : '默认地址租期(秒)',
		        	        	  name : 'defaultValidTime'
		        	          },
		        	          {
		        	        	  xtype : 'textfield',
		        	        	  fieldLabel : '最大地址租期(秒)',
		        	        	  name : 'maxValidTime'
		        	          },
		        	          {
		        	        	  xtype : 'textfield',
		        	        	  fieldLabel : '网关(可选)',
		        	        	  name : 'gateWay'
		        	          },
		        	          {
		        	        	  xtype : 'textfield',
		        	        	  fieldLabel : '缺省域名(可选)',
		        	        	  name : 'defaultHost'
		        	          },
		        	          {
		        	        	  xtype : 'textfield',
		        	        	  fieldLabel : 'DNS服务器(可选)',
		        	        	  name : 'dnsServer'
		        	          },
		        	          {
		        	        	  xtype : 'label',
		        	        	  html : '(可输入多个DNS地址，用逗号隔开)',
		        	        	  style : {
		        	        	  	marginLeft : '150px'
		        	          	  }
		        	          }],
		        	  buttonAlign : 'center',
		        	  buttons : [
		        	             {
		        	            	 text : '保存',
		        	            	 height : 30,
		        	            	 width : 80,
		        	            	 handler :  this.submitForm
		        	             }]
		         }
//		         {
//		        	 region : 'center',
//		        	 xtype : 'grid',
//		        	 title : '客户端列表',
//		        	 //html : '在这里显示客户端列表'
//		        	 autoScroll : true,
//		        	 viewConfig : {
//		        	 	forceFit : true,
//		        	 	stripeRows : true
//		         	 },
//		         	 bbar : new Ext.PagingToolbar({
//		         		 store : clientStore,
//		         		 displayInfo : true
//		         	 }),
//		         	 store : clientStore,
//		         	 columns : [
//		         	            {
//		         	            	header : 'ID',
//		         	            	width : 50,
//		         	            	dataIndex : 'clientId'
//		         	            },
//		         	            {
//		         	            	header : '客户端名',
//		         	            	width : 150,
//		         	            	dataIndex : 'clientName'
//		         	            },
//		         	            {
//		         	            	header : 'MAC地址',
//		         	            	width : 350,
//		         	            	dataIndex : 'clientMac'
//		         	            },
//		         	            {
//		         	            	header : 'IP地址',
//		         	            	width : 200,
//		         	            	dataIndex : 'clientIp'
//		         	            },
//		         	            {
//		         	            	header : '有效时间',
//		         	            	width : 100,
//		         	            	dataindex : 'validTime'
//		         	            }]
//		         	 
//		         }
		         ]
	});
	/*将父类的构造拷贝过来*/
	IndexPage.superclass.constructor.call(this, {
		id : this.myid, // 唯一id
		iconCls : "houfei-treeNodeLeafIcon",
		layout : "border", // 指定布局为border布局
		items : [ dhcpServiceForm]
	})
	},
	submitForm : function(){
//			Ext.Ajax.request({
// 				waitMsg : '正在保存请稍后...',
// 				waitTitle : '提示',
// 				url : 'setDhcp.action',
// 				params : {
// 					dhcpService : Ext.getCmp('dhcpRadio').getValue(),
// 					sIp : Ext.getCmp('sIp').getValue(),
// 					eIp : Ext.getCmp('eIp').getValue(),
// 					defaultValidTime : Ext.getCmp('defaultValidTime').getValue(),
// 					maxValidTime : Ext.getCmp('maxValidTime').getValue(),
// 					gateWay : Ext.getCmp('gateWay').getValue(),
// 					defaultHost : Ext.getCmp('defaultHost').getValue(),
// 					dnsServer : Ext.getCmp('dnsServer').getValue()
// 				},
// 				success : function(response){
// 					Ext.Msg.show( {
// 						title : '提示',
// 						msg : '保存成功！',
// 						buttons : Ext.Msg.YES
// 					});
// 				},
// 				failure : function(response, opts){
// 					Ext.Msg.show( {
// 						title : '提示',
// 						msg : '保存失败！',
// 						buttons : Ext.Msg.YES
// 					});
// 				}
// 			})
 			dhcpServiceForm.form.submit({
 				waitMsg : '正在保存请稍后...',
 				waitTitle : '提示',
 				url : 'setDhcp.action',
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
     		dhcpServiceForm.form.load({
     			waitMsg : '正在加载请稍后...',
 				waitTitle : '提示',
 				url : 'showDhcpConfig.action',
 				method : 'GET',
 				failure:function(form,action){
     				Ext.Msg.alert('提示','数据加载失败');
     			}
     		});
		}
})