var temp;
 var accesses=new Array();
TimesFlowPanel = Ext.extend(Ext.panel.Panel, {
	myid : "defaultId",
	/**
	 * 表格store
	 */
	uriStore : false, 
	/**
	 * 内容store
	 */
	tPkgStore : false,
	/**
	 * 中间区域grid
	 */
	tPkgGrid : false,
	/**
	 * 左边区域grid
	 */
	uriGrid : false,
	/**
	 * 东边区域from
	 */
	 from:false,
	 /**
	  * 新窗口store
	  */
	 macPkgStore : false,
	 /**
	  * 下拉列表store
	  */
	 macComboStore : false,
	 //task
	 task : false,
	 runner : false,
	 constructor : function(_config) {
	 	if (_config == null) {
			_config = {};
		}
		// 将_config附加到当前对象
		Ext.apply(this, _config);
		//下拉列表Store
		macComboStore = new Ext.data.Store({
			fields : ['tnativeMac'],
			proxy : {
				type : 'ajax',
				url : 'getTPkgMac.action',
				reader : {
					type : 'json',
					//totalProperty : 'totalCount',
					//idProperty : 'tpackageId',
					root : 'tnativeMac'
			},
			autoLoad : {
				start : 0,
				limit : 1000
			}	
			}
		}),
		//新窗口Store
		macPkgStore = new Ext.data.Store({
			fields : ['tpackageId','tnativeMac', 'tremoteIp', 'tremotePort', 'tprotocolType','tflowDirection','tflowAmount','type'],// 定义字段
			proxy : {
				type : 'ajax',
				url : 'showPkgByMac.action',
				// timeout : myBigTimeout,
				reader : {
					type : 'json',
					totalProperty : 'totalCount',
					idProperty : 'tpackageId',
					root : 'rows'
				}
			},
			pageSize : 50
				// 设置分页大小
			}),
		/**
		 * 手机表格store
		 */
		this.uriStore = new Ext.data.Store({
			fields : ['uriId','host','path'],// 定义字段
			proxy : {
				type : 'ajax',
				url : 'listUri.action',
				// timeout : myBigTimeout,
				reader : {
					type : 'json',
					totalProperty : 'totalCount',
					idProperty : 'uriId',
					root : 'rows'
				}
			},
			autoLoad : {
				start : 0,
				limit : 10
			},
			pageSize : 10
				// 设置分页大小
			}),
			/**
		 * 流量包表格store
		 */

		this.tPkgStore = new Ext.data.Store({
			fields : ['tpackageId','tnativeMac', 'tremoteIp', 'tremotePort', 'tprotocolType','tflowDirection','tflowAmount','type'],// 定义字段
			proxy : {
				type : 'ajax',
				url : 'showTPkg.action',
				// timeout : myBigTimeout,
				reader : {
					type : 'json',
					totalProperty : 'totalCount',
					idProperty : 'tpackageId',
					root : 'rows'
				}
			},
			autoLoad : {
				start : 0,
				limit : 20
			},
			pageSize : 20
				// 设置分页大小
			}),
		this.tPkgStore.on('beforeload', function(gridStore, options) {
			var new_params = {
				phoneid : temp
			};
				//alert(temp);
				Ext.apply(gridStore.proxy.extraParams, new_params);
		});
		/**
		 * 顶部工具栏
		 */
		this.toolbar= new Ext.toolbar.Toolbar({
			height: 30,
			items: [
			{id:'st',height:25,text: '开始监测',icon: 'images/icons/start.png',handler:this.startT,scope:this},
			{id:'et',height:25,	text: '结束监测',icon: 'images/icons/stop.png',handler:this.endT,scope:this},
			{id:'showPkgWin',height:25,text: '按Mac查看',icon: 'images/icons/check.png',handler:this.showPkgWindow,scope: this},
			'->',
			{id:'tbtext',xtype: 'tbtext',text: '当前状态：不在监测'}
		]}),
		/**
		 * 中间区域grid
		 */
		this.tPkgGrid = new Ext.grid.Panel({
			region : "center",
			//renderTo : Ext.getBody(),
			autoScroll : true,
			frame : false,
			viewConfig : {
				forceFit : true,
				stripeRows : false,
				getRowClass: function(record) { 
		            //return record.get('Sex') == 1 ? 'boy-row' : 'gril-row';
					if(record.get('type')==1){
						return 'erro-row';
					}else if(record.get('type')==2){
						return 'access-row';
					}
		        }
				// 在表格中显示斑马线
			},
			selType : 'rowmodel',// 设置为单元格选择模式Ext.selection.RowModel
			tbar:this.toolbar,
			bbar : new Ext.PagingToolbar({
						store : this.tPkgStore,
						displayInfo : true
					}),
			listeners : { // 监听双击事件
				dblclick : {
					element : 'body',
					fn : function() {
						Ext.regModel('Address',{
						    fields:[{name:'country',type:'String'},
						    		{name:'province',type:'String'},
						    		{name:'city',type:'String'},
						    		{name:'district',type:'String'},
						    		{name:'isp',type:'String'}
						    ],
						    proxy:{
						    	type : 'ajax',
								url : 'getAddress.action'/*,
								reader : {
									type : 'json'
								}*/
						    }
						});
						var address=Ext.ModelManager.getModel('Address');
						
						var rows = this.tPkgGrid.getSelectionModel().getSelection();
						var num = rows.length;
						if (num > 1) {
							Ext.MessageBox.alert("提示", "只能选一项");
						} else if (num == 1) {
							var msgTip = Ext.MessageBox.show({
										title : '提示',
										width : 250,
										msg : '正在获取信息请稍后......'
									});
						}
						temp=rows[0].get('tpackageId');
						address.load(1,{
							params : {
										pkgId : rows[0].get('tremoteIp')
							},
							success:function(rec){
								Ext.getCmp('addressLabel').setText(rec.get('country')+rec.get('province')+rec.get('city')+rec.get('district')+rec.get('isp'));
								msgTip.hide();
							}
						});
						Ext.get("frame1").dom.src = "http://"+rows[0].get('tremoteIp');
						this.uriStore.load({
									params : {
										pkgId : rows[0].get('tpackageId')
									}
						});
						
					},
					scope : this
				},
				 itemmouseenter: function (view, record, item) {
				 	if(record.get('type')==1){
				 		Ext.fly(item).set({ 'data-qtip': '流量异常' });
				 	}else if(record.get('type')==2){
				 		Ext.fly(item).set({ 'data-qtip': '黑名单' });
				 	}else if(record.get('type')==3){
				 		Ext.fly(item).set({ 'data-qtip': '被信任' });
				 	}
				 	else{
				 		Ext.fly(item).set({ 'data-qtip': '正常' });
				 	}
                 }
			},
			store : this.tPkgStore,
			/*features : [{
						ftype : 'summary'
					}],*/
			columns : [// 配置表格列
					// {header: "TID", width: 80, dataIndex:
					// 'fileTid', sortable: true},
					{
						header : "ID",
						width : 5,
						dataIndex : 'tpackageId',
						flex: 1,
						hidden:true
					},{
						header : "源Mac地址",
						width : 20,
						dataIndex : 'tnativeMac',
						flex: 1,
						sortable : true
					}, {
						header : "目标IP",
						width : 10,
						dataIndex : 'tremoteIp',
						flex: 1,
						sortable : true
					}, {
						header : "端口",
						width : 10,
						dataIndex : 'tremotePort',
						flex: 1,
						sortable : true
					}, {
						header : "协议",
						width : 10,
						dataIndex : 'tprotocolType',
						flex: 1,
						sortable : true 
					}, {
						header : "流量方向",
						width : 10,
						dataIndex : 'tflowDirection',
						xtype:'booleancolumn',
						trueText:'下行',
						falseText:'上行',
						flex: 1,
						sortable : true 
					}, {
						header : "总流量",
						width : 15,
						dataIndex : 'tflowAmount',
						flex: 1,
						/*renderer:function(value,metadata) {
							if(value>3000){
								metadata.style='background-color:#CCFFFF;'
							}
							return value;
						},*/
						sortable : true 
					},{
						dataIndex : 'type',
						hidden:true
					}]
		}), 
		/**
		 * 东边区域from
		 */
		this.uriGrid = new Ext.grid.Panel({
		    //renderTo : Ext.getBody(),
		    region : "east",
		    width:300,
			autoScroll : true,
			frame : false,
			viewConfig : {
				forceFit : true,
				stripeRows : true
				// 在表格中显示斑马线
			},
			selType : 'rowmodel',// 设置为单元格选择模式Ext.selection.RowModel
			/*bbar : new Ext.PagingToolbar({
						store : this.phoneStore,
						displayInfo : true
					}),*/
			listeners : { // 监听双击事件
				dblclick : {
					element : 'body',
					fn : function() {
						var rows = this.uriGrid.getSelectionModel().getSelection();
						var num = rows.length;
						if (num > 1) {
							Ext.MessageBox.alert("提示", "只能选一项");
						} else if (num == 1) {
							var msgTip = Ext.MessageBox.show({
										title : '提示',
										width : 250,
										msg : '正在获取信息请稍后......'
									});
						}
						//test="http://"+rows[0].get('uriRoot')+rows[0].get('uriPath');
						Ext.get("frame1").dom.src = "http://"+rows[0].get('host').replace(/\s+/g,"")+rows[0].get('path').replace(/\s+/g,"");
						msgTip.hide();
					},
					scope : this
				}
			},
			store : this.uriStore,
			features : [{
						ftype : 'summary'
					}],
			columns : [// 配置表格列
			{
						header : "ID",
						width : 25,
						dataIndex : 'uriId',
						sortable : true,
						hidden:true
					},{
						header : "网站",
						width : 120,
						dataIndex : 'host',
						sortable : true
					},{
						header : "路径",
						width : 150,
						dataIndex : 'path',
						sortable : true
					}]
		}),
		task = new Ext.util.TaskRunner.Task({
			run : function(){
				this.tPkgStore.load();
			},
			interval : 15000,
			scope : this
		}),
		runner = new Ext.util.TaskRunner();
		/**
		 * 将父类的构造拷贝过来
		 */
		IndexPage.superclass.constructor.call(this, {
					id : this.myid, // 唯一id
					iconCls : "houfei-treeNodeLeafIcon",
					layout : "border", // 指定布局为border布局
					items : [this.tPkgGrid, this.uriGrid, 
						{
							//title: '内容',
							region: 'south',//指定子面板所在区域为south
							height: 220,
				            layout: 'fit',
				            autoScroll:true,
				            tbar: [
  									{ id:'addressLabel',xtype: 'label', text: '',height:22 }
							],
				            items:new Ext.form.Panel({
				                id:  "panel2",  
					            fitToFrame: true,                  
					            html: '<iframe id="frame1" frameborder="0" width="100%" height="100%"></iframe>'
				            })           
						}]
		})
	 },
	 /**
	 * 显示新建用户窗口
	 */
	startT : function() {
		Ext.MessageBox.show({
			title: '启动监测',
			msg: '正在处理，请稍后...',
			width: 250,
			wait: true,
			waitConfig: {interval: 100}
		}); 
		setTimeout(function(){
			Ext.MessageBox.hide();
			Ext.getCmp('tbtext').setText('当前状态：监测中');
		},1000);
		Ext.Ajax.request({ 
				url:'startTPkg.action',
				scope:this,
				success: function(response, options){
					//timename=window.setInterval(this.tPkgStore.load(),5000);
					Ext.getCmp("st").disabled=true;
					Ext.getCmp("et").disabled=false;
					runner.start(task);
				}
		});
		
	},
	endT:function(){
		Ext.MessageBox.show({
			title: '结束监测',
			msg: '关闭中...',
			width: 250,
			wait: true,
			waitConfig: {interval: 100}
		});
		setTimeout(function(){
			Ext.MessageBox.hide();
			Ext.getCmp('tbtext').setText('当前状态：监测结束');	
		},1000);
		Ext.Ajax.request({ 
				url:'endTPkg.action',
				scope:this,
				success: function(response, options){
					//timename=window.setInterval(this.tPkgStore.load(),5000);
					var task = {
						run: function(){
							this.tPkgStore.load();
						},
						interval: 15000 //15秒
					}
					Ext.TaskManager.stop(task);
					this.tPkgStore.load();
					Ext.getCmp("st").disabled=false;
					Ext.getCmp("et").disabled=true;
					runner.stopAll();
				}
		});	
	},
	showPkgWindow : function(){
		var showPkgPanel = Ext.create('Ext.panel.Panel', {
			//bodyStyle : 'padding: 5px 5px 0',
			layout : 'border',
			frame : true,
			items : [ {
				region: 'north',
				xtype : 'form',
				title : 'Mac列表',
				frame : true,
				items : [
				         {
				        	 id : 'tPkgMacCombo',
				        	 xtype : 'combo',
				        	 fieldLabel : '请选择要查看的Mac地址',
				        	 labelWidth : 150,
				 			 labelAlign : 'left',
				        	 width : 300,
	        	        	  listConfig : {
	        	        	  	maxWidth : 220
	        	          	  },
				        	 triggerAction : 'all',
	        	          	 store : macComboStore,
	        	          	 displayField : 'tnativeMac',
	        	          	 queryMode : 'remote',
	        	          	 forceSelection : true,
	        	          	 typeAhead : true,
	        	          	 listeners : {
	        	          		  select : function(){
	        	          		  		var mac = Ext.getCmp('tPkgMacCombo').getValue();
	        	          		  		//alert(mac);
	        	          		  		macPkgStore.on('beforeload',function(){
	        	          		  			Ext.apply(macPkgStore.proxy.extraParams, {tnativeMac : mac});
	        	          		  		});
	        	          		  		macPkgStore.load({
	        	          		  			params : {
	        	          		  				tnativeMac : mac,
	        	          		  				start : 0,
	        	          		  				limit : 50,
	        	          		  				pageSize : 50
	        	          		  			}
	        	          		  		});
	        	          		  		Ext.getCmp('wbbar').moveFirst();
	        	          	  	  }
	        	          	 }
				         }]
			}, 
			{
				region : 'center',
				xtype : 'grid',
				title : '流量包详情',
				autoScroll : true,
				frame : false,
				viewConfig : {
        	 		forceFit : true,
        	 		stripeRows : true
				},
				bbar : new Ext.PagingToolbar({
					id : 'wbbar',
					store : macPkgStore,
					displayInfo : true
				}),
				store : macPkgStore,
				columns : [
							{
								header : "ID",
								width : 5,
								dataIndex : 'tpackageId',
								flex: 1,
								hidden:true
							},{
								header : "源Mac地址",
								width : 20,
								dataIndex : 'tnativeMac',
								flex: 1,
								sortable : true
							}, {
								header : "目标IP",
								width : 10,
								dataIndex : 'tremoteIp',
								flex: 1,
								sortable : true
							}, {
								header : "端口",
								width : 10,
								dataIndex : 'tremotePort',
								flex: 1,
								sortable : true
							}, {
								header : "协议",
								width : 10,
								dataIndex : 'tprotocolType',
								flex: 1,
								sortable : true 
							}, {
								header : "流量方向",
								width : 10,
								dataIndex : 'tflowDirection',
								xtype:'booleancolumn',
								trueText:'下行',
								falseText:'上行',
								flex: 1,
								sortable : true 
							}, {
								header : "总流量",
								width : 15,
								dataIndex : 'tflowAmount',
								flex: 1,
								sortable : true 
							},{
								dataIndex : 'type',
								hidden:true
							}]
			}],
			buttonAlign : 'center',
			buttons : [
					{
						text : '生成表格',
						width : 150,
						height : 30,
						//点击按钮打开一个新的页面
						handler:function(item){
							var linkAddress="DownXlsPage.jsp?macAdd="+Ext.getCmp('tPkgMacCombo').getValue();
							 window.open(linkAddress, 'mywindow1', 'width=300, height=300, menubar=no, toolbar=no, scrollbars=yes');  
						}
					}]
		});
		var addWindow = Ext.create('Ext.window.Window', {
			title : '流量包详情-按Mac查看',
//			closeAction : 'hide',
			draggable : true,
			height : 600,
			width : 800,
			layout : 'fit',
			modal : true,
			plain : true,
			//frame: true,
			resizable : false,
			items : [ showPkgPanel ]
		});
		if(!addWindow.isVisible()){
			addWindow.show();
		}
	}
});