ClientListPanel = Ext.extend(Ext.panel.Panel,{
	myid : "defaultId",
	//客户端Store
	clientListStore : false,
	//中间Grid
	clientListGrid : false,
	constructor : function(_config){
		if(_config == null){
			_config = {};
		}// 将_config附加到当前对象
		Ext.apply(this, _config);
		//实例化客户端Store
		clientListStore = new Ext.data.Store({
			fields : ['cid', 'cmac', 'showpkgs'],
			proxy : {
				type : 'ajax',
				url : 'getClientList.action',
				reader : {
					type : 'json',
					totalProperty : 'totalCount',
					idProperty : 'cid',
					root : 'rows'
				}
			},
			autoLoad : {
				start : 0,
				limit : 20,
				callback : function(records,options,success){
					Ext.TaskManager.start({
						run : function(){
							//alert("in");
							clientListStore.load();
						},
						interval : 60000
					});
				}
			},
			pageSize : 20
		});
		//实例化中间Grid
		clientListGrid = new Ext.grid.Panel({
			region : 'center',
			autoScroll : true,
			frame : false,
			viewConfig : {
				forceFit : false,
				stripeRows : true
			},
			selType : 'rowmodel',
			store : clientListStore,
			listeners : {
				dblclick : {
					element : 'body',
					fn : function(){
						var selection = clientListGrid.getSelectionModel().getSelection()[0];
						var status = selection.get('showpkgs');
						//alert(status);
						Ext.Ajax.request({
							url : 'changePkgMode.action',
							params : {
								cid : selection.get('cid'),
								cmac : selection.get('cmac'),
								showpkgs : !status
							},
							success : function(response){
								var res = Ext.decode(response.responseText);
								if(res.success == true){
									clientListStore.load();
								}
								else {
									Ext.Msg.show( {
										title : '操作提示',
										msg : '此Mac当前未连接！',
										buttons : Ext.Msg.YES
									});
								}
							},
							failure : function(response,opts){
								Ext.Msg.show( {
									title : '操作提示',
									msg : '修改显示状态失败！',
									buttons : Ext.Msg.YES
								});
							}
						});
					},
					scope : this
				}
			},
			columns : [
			           {
			        	  text : 'ID',
			        	  width : 50,
			        	  hidable : true,
			        	  hidden : true,
			        	  sortable : true,
			        	  dataIndex : 'cid'
			           },
			           {
			        	   text : '客户端Mac地址',
			        	   width : 300,
			        	   hideable : false,
			        	   sortable : false,
			        	   dataIndex : 'cmac'
			           },
			           {
			        	   text : '流量包状态',
			        	   width : 100,
			        	   sortable : true,
			        	   xtype : 'booleancolumn',
			        	   dataIndex : 'showpkgs',
			        	   trueText : '显示',
			        	   falseText : '不显示'
			           }]
		});
		/*将父类的构造拷贝过来*/
		IndexPage.superclass.constructor.call(this, {
			id : this.myid, // 唯一id
			iconCls : "houfei-treeNodeLeafIcon",
			layout : "border", // 指定布局为border布局
			items : [ clientListGrid, {
				region : 'south',//指定子面板所在区域为south
				height : 25,
				layout : 'fit',
				frame : true,
				html : '在此查看客户端列表以及修改客户端流量包的显示情况,双击某个Mac地址来切换该地址流量包的显示状态'
			} ]
		});
	}
});