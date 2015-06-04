RunStatePanel=Ext.extend(Ext.panel.Panel,{	
	/* 各个运行状态信息所在的面板 */
	myStatePanel:false,
	constructor : function(_config) {
		if (_config == null) {
			_config = {};
		}// 将_config附加到当前对象
		
		Ext.apply(this, _config);
		// 各个运行状态面板
		this.myStatePanel=new Ext.form.Panel({
			region:'center',
			layout:'accordion',
			autoScroll:true,		
			fieldDefaults:{// 统一设置表单字段默认属性
				labelSeparator :'：',// 分隔符
				labelWidth : 160,// 标签宽度
				msgTarget : 'side',
				width : '100%',	
				height: '50%'
			},
			
			items:[
			       {
			    	    title:'版本信息',
						bodyPadding: 5,
						autoScroll:true,
						frame:true,
						renderTo: Ext.getBody(),
						layout:{
							type:'table',// 表格布局
							columns:2// 设置表格布局默认列数为2列
						},
						items:[{
							xtype:'label',
							width : 300,
							id:'softVersionLabel',
							text:'当前软件版本信息：',
							margin:'0 0 0 0'
						},{
							xtype:'displayfield',
							width : 300,
							id:'softInfo',
							text : 'PhoneWeb 版本1.0',
							margin:'0 0 0 0'
						},
						
						{
							xtype:'label',
							width : 300,
							id:'systemVersionLabel',
							text:'当前系统版本信息：',
							margin:'0 0 0 0'
						},{
							xtype:'displayfield',
							width : 300,
							id:'systemVersionInfo',
							text : 'Windows7 32位旗舰版',
							margin:'0 0 0 0'
						},
						]
			       },
//			       {
//			    	    title:'LAN口状态',
//						bodyPadding: 5,
//						autoScroll:true,
//						frame:true,
//						renderTo: Ext.getBody(),
//						layout:{
//							type:'table',// 表格布局
//							columns:2// 设置表格布局默认列数为2列
//						},
//						items:[{
//							xtype:'label',
//							width : 300,
//							id:'macLanLabel',
//							text:'MAC地址：',
//							margin:'0 0 0 0'
//						},{
//							xtype:'displayfield',
//							width : 300,
//							id:'macLanInfo',
//							text : '6C-E8-73-D6-A4-E3',
//							margin:'0 0 0 0'
//						},
//						{
//							xtype:'label',
//							width : 300,
//							id:'ipLanLabel',
//							text:'IP地址：',
//							margin:'0 0 0 0'
//						},{
//							xtype:'displayfield',
//							width : 300,
//							id:'ipLanInfo',
//							text : '192.168.1.1',
//							margin:'0 0 0 0'
//						},
//						{
//							xtype:'label',
//							width : 300,
//							id:'zwymLanLabel',
//							text:'子网掩码：',
//							margin:'0 0 0 0'
//						},{
//							xtype:'displayfield',
//							width : 300,
//							id:'zwymLanInfo',
//							text : '255.255.255.0',
//							margin:'0 0 0 0'
//						},
//						]},
						{
							title:'WAN口状态',
							bodyPadding: 5,
							autoScroll:true,
							frame:true,
							renderTo: Ext.getBody(),
							layout:{
								type:'table',// 表格布局
								columns:2// 设置表格布局默认列数为2列
							},
							items:[{
								xtype:'label',
								width : 300,
								id:'macWanLabel',
								text:'MAC地址：',
								margin:'0 0 0 0'
							},{
								xtype:'displayfield',
								width : 300,
								id:'macWanInfo',
								text : '3B-E8-73-D6-A4-E3',
								margin:'0 0 0 0'
							},
							{
								xtype:'label',
								width : 300,
								id:'IPWanLabel',
								text:'IP地址：',
								margin:'0 0 0 0'
							},{
								xtype:'displayfield',
								width : 300,
								id:'IPWanInfo',
								text : '192.168.1.1',
								margin:'0 0 0 0'
							},
							{
								xtype:'label',
								width : 300,
								id:'zwymWanLabel',
								text:'子网掩码：',
								margin:'0 0 0 0'
							},{
								xtype:'displayfield',
								width : 300,
								id:'zwymWanInfo',
								text : '255.255.255.0',
								margin:'0 0 0 0'
							},
							{
								xtype:'label',
								width : 300,
								id:'wgWanLabel',
								text:'网关：',
								margin:'0 0 0 0'
							},{
								xtype:'displayfield',
								width : 300,
								id:'wgWanInfo',
								text : '192.168.1.1',
								margin:'0 0 0 0'
							},
							{
								xtype:'label',
								width : 300,
								id:'dnsWanLabel',
								text:'DNS服务器：',
								margin:'0 0 0 0'
							},{
								xtype:'displayfield',
								width : 300,
								id:'dnsWanInfo',
								text : '202.4.130.100,202.4.130.101',
								margin:'0 0 0 0'
							},
							]
						},
						{
							title:'无线状态',
							bodyPadding: 5,
							autoScroll:true,
							frame:true,
							renderTo: Ext.getBody(),
							layout:{
								type:'table',// 表格布局
								columns:2// 设置表格布局默认列数为2列
							},
							items:[{
								xtype:'label',
								width : 300,
								id:'wirelessLabel',
								text:'无线功能：',
								margin:'0 0 0 0'
							},{
								xtype:'displayfield',
								width : 300,
								id:'wirelessInfo',
								text : '启用',
								margin:'0 0 0 0'
							},
							{
								xtype:'label',
								width : 300,
								id:'ssidLabel',
								text:'SSID号：',
								margin:'0 0 0 0'
							},{
								xtype:'displayfield',
								width : 300,
								id:'ssidInfo',
								text : 'TP601',
								margin:'0 0 0 0'
							},
							{
								xtype:'label',
								width : 300,
								id:'xdLabel',
								text:'信道：',
								margin:'0 0 0 0'
							},{
								xtype:'displayfield',
								width : 300,
								id:'xdInfo',
								text : '自动（当前信道6）',
								margin:'0 0 0 0'
							},
//							{
//								xtype:'label',
//								width : 300,
//								id:'msLabel',
//								text:'模式：',
//								margin:'0 0 0 0'
//							},{
//								xtype:'displayfield',
//								width : 300,
//								id:'msInfo',
//								text : '11bgn mixed',
//								margin:'0 0 0 0'
//							},
//							{
//								xtype:'label',
//								width : 300,
//								id:'pddkLabel',
//								text:'频道带宽：',
//								margin:'0 0 0 0'
//							},{
//								xtype:'displayfield',
//								width : 300,
//								id:'pddkInfo',
//								text : '自动',
//								margin:'0 0 0 0'
//							},
//							{
//								xtype:'label',
//								width : 300,
//								id:'wdsLabel',
//								text:'WDS状态：',
//								margin:'0 0 0 0'
//							},{
//								xtype:'displayfield',
//								width : 300,
//								id:'wdsInfo',
//								text : '未开启',
//								margin:'0 0 0 0'
//							},
							]
						}
			       ]
		}),
		
		/* 将父类的构造拷贝过来 */
		IndexPage.superclass.constructor.call(this, {
					id : this.myid, // 唯一id
					iconCls : "houfei-treeNodeLeafIcon",
					layout : "border", // 指定布局为border布局
					items : [this.myStatePanel]
		})
	},
	
	loadForm:function(){
		// 加载表单数据
		this.myStatePanel.form.load({
			waitMsg : '正在加载运行状态数据请稍后',// 提示信息
			waitTitle : '提示',// 标题
			url :'GetWifiConfig.action',// 请求的url地址
			method:'GET',// 请求方式
			// 加载成功
			success:function(form,action){
				var responseText = Ext.decode(action.result.message);
				var softInfoText = responseText.softInfo;
				var systemVersionInfoText = responseText.systemVersionInfo;
				var macLanInfoText = responseText.macLanInfo;
				var ipLanInfoText = responseText.ipLanInfo;
				var zwymLanInfoText = responseText.zwymLanInfo;
				var macWanInfoText = responseText.macWanInfo;
				var IPWanInfoText = responseText.IPWanInfo;
				var zwymWanInfoText = responseText.zwymWanInfo;
				var wgWanInfoText = responseText.wgWanInfo;
				var dnsWanInfoText = responseText.dnsWanInfo;
				var wirelessInfoText = responseText.wirelessInfo;
				var ssidInfoText = responseText.ssidInfo;
				var xdInfoText = responseText.xdInfo;
				var msInfoText = responseText.msInfo;
				var pddkInfoText = responseText.pddkInfo;
				var wdsInfoText = responseText.wdsInfo;
				// 调用文本域的方法，来设置对应的值
				if(setValue!=null){
					Ext.getCmp('softInfo').setValue(softInfoText);
				}
				if(systemVersionInfoText!=null){
					Ext.getCmp('systemVersionInfo').setValue(systemVersionInfoText);
				}
//				if(macLanInfoText!=null){
//				Ext.getCmp('macLanInfo').setValue(macLanInfoText);
//				}
//				if(ipLanInfoText!=null){
//				Ext.getCmp('ipLanInfo').setValue(ipLanInfoText);
//				}
//				if(zwymLanInfoText!=null){
//				Ext.getCmp('zwymLanInfo').setValue(zwymLanInfoText);
//				}
				if(macWanInfoText!=null){
				Ext.getCmp('macWanInfo').setValue(macWanInfoText);
				}
				if(IPWanInfoText!=null){
				Ext.getCmp('IPWanInfo').setValue(IPWanInfoText);
				}
				if(zwymWanInfoText!=null){
				Ext.getCmp('zwymWanInfo').setValue(zwymWanInfoText);
				}
				if(wgWanInfoText!=null){
				Ext.getCmp('wgWanInfo').setValue(wgWanInfoText);
				}
				if(dnsWanInfoText!=null){
				Ext.getCmp('dnsWanInfo').setValue(dnsWanInfoText);
				}
				if(wirelessInfoText!=null){
				Ext.getCmp('wirelessInfo').setValue(wirelessInfoText);
				}
				if(ssidInfoText!=null){
				Ext.getCmp('ssidInfo').setValue(ssidInfoText);
				}
				if(xdInfoText!=null){
				Ext.getCmp('xdInfo').setValue(xdInfoText);
				}
//				if(msInfoText!=null){
//				Ext.getCmp('msInfo').setValue(msInfoText);
//				}
//				if(pddkInfoText!=null){
//				Ext.getCmp('pddkInfo').setValue(pddkInfoText);
//				}
//				if(wdsInfoText!=null){
//				Ext.getCmp('wdsInfo').setValue(wdsInfoText);
//				}
			},
			failure:function(form, action){
                if (action.failureType === Ext.form.action.Action.CONNECT_FAILURE) {
                    Ext.Msg.alert('Error',
                        'Status:'+action.response.status+': '+
                        action.response.statusText);
                }
                if (action.failureType === Ext.form.action.Action.SERVER_INVALID){
                    // server responded with success = false
                    Ext.Msg.alert('Invalid', action.result.errormsg);
                }
			}				
// function(form,action){//加载失败的处理函数
// Ext.Msg.alert('提示','数据加载失败');
// }
		});
	}
})
