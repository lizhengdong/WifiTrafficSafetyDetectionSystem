ClientStatePanel = Ext.extend(Ext.panel.Panel,{
	myid : 'defaultId',
	//主机状态面板
	clientStateGrid : false,
	//状态数据Store
	clientStateStore : false,
	constructor : function(_config){
		if(_config == null){
			_config = {};
		}
	Ext.apply(this, _config);
	//实例化状态Store
	clientStateStore = new Ext.data.Store({
		fields : ['clientId','clientMac','receivedPkg','sentPkg'],
		proxy : {
			type : 'ajax',
			url : 'null.json',
			reader : {
				type : 'json',
				
			}
		},
		autoload : {
			start : 0,
			limit : 20
		},
		pageSize : 20
	});
	//实例化主机状态面板
	clientStateGrid = new Ext.grid.Panel({
		region : 'center',
		autoScroll : true,
		frame : false,
		viewConfig : {
			forceFit : true,
			stripeRows : true
		},
		bbar : new Ext.PagingToolbar({
			store : clientStateStore,
			displayInfo : true
		}),
		store : clientStateStore,
		columns : [
		           {
		        	   header : 'ID',
		        	   width : 50,
		        	   dataIndex : 'clientId'
		           },
		           {
		        	   header : 'Mac地址',
		        	   width : 350,
		        	   dataIndex : 'clientMac'
		           },
		           {
		        	   header : '接收数据包',
		        	   width : 150,
		        	   dataIndex : 'receivedPkg',
		        	   sortable : true
		           },
		           {
		        	   header : '发送数据包',
		        	   width : 150,
		        	   dataIndex : 'sentPkg',
		        	   sortable : true
		           }]
	});
	/*将父类的构造拷贝过来*/
	IndexPage.superclass.constructor.call(this, {
		id : this.myid, // 唯一id
		iconCls : "houfei-treeNodeLeafIcon",
		layout : "border", // 指定布局为border布局
		items : [ clientStateGrid, {
			region : 'south',//指定子面板所在区域为south
			height : 25,
			layout : 'fit',
			frame : true,
			html : '这里显示主机状态'
		} ]
	})
}
})