PhoneSafetyPanel = Ext.extend(Ext.panel.Panel, {
	myid : "defaultId",
	macStore : false, 
	macGrid : false,
	constructor : function(_config) {
		if (_config == null) {
			_config = {};
		}
		// 将_config附加到当前对象
		Ext.apply(this, _config);
		this.macStore = new Ext.data.Store({
			fields : ['nativeMac', 'maxUpDown', 'type','status'],// 定义字段
			proxy : {
				type : 'ajax',
				url : 'showMac.action',
				reader : {
					type : 'json',
					totalProperty : 'totalCount',
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
		this.macStore.on('beforeload', function(gridStore, options) {
			var new_params = {
				phoneid : temp
			};
				//alert(temp);
			Ext.apply(gridStore.proxy.extraParams, new_params);
		});
		this.macGrid= new Ext.grid.Panel({
			region: "center",
			autoScroll : true,
			frame : false,
			store : this.macStore,
			viewConfig: {
				forceFit: true,
				stripeRows: true
			},
			columns : [// 配置表格列
				{
					header : "源Mac地址",
					width : 20,
					dataIndex : 'nativeMac',
					flex: 2,
					sortable : true
				},/*{
					header :"安全评分",
					width :20,
					dataIndex:'maxUpDown',
					flex:1,
					sortable:true
				},*/
				{
					header: "安全状态",
					width: 20,
					dataIndex: 'maxUpDown',
					flex: 1,
					sortable: false,
					renderer: function chooseIcon(value,metadata){
					if(value >= 0.80){
						metadata.style = 'background:url(images/icons/status-good.png) 20px no-repeat !important';
					}
					else if(value >=0.60 && value < 0.80){
						metadata.style = 'background:url(images/icons/status-fine.png) 20px no-repeat !important';
					}
					else {
						metadata.style = 'background:url(images/icons/status-bad.png) 20px no-repeat !important';
					}
					return '';
				}
				}
			]
		}),
		IndexPage.superclass.constructor.call(this, {
			id : this.myid, // 唯一id
			iconCls : "houfei-treeNodeLeafIcon",
			layout: 'border',
			items :[this.macGrid,
			        {
				region: 'south',//指定子面板所在区域为south
				height: 25,
		        //layout: 'fit',
		        frame: true,
		        html: '<div>状态图示：<img src="images/icons/status-good.png" align=top></img>状态良好  <img src="images/icons/status-fine.png" align=top></img>建议优化  <img src="images/icons/status-bad.png"  align=top></img>急需优化</div>'
				}]
		})
	}
})
