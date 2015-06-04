/**
 * @author fjm
 * @date 2013.4.8.
 * @extends Ext.grid.Panel
 * @description aDyFlowPanel
 */
var temp;
var rMenu;
var accesses = new Array();
DayFlowPanel = Ext.extend(Ext.panel.Panel,{
	myid : "defaultId",
	/**
	* 表格store
	*/
	uriStore : false,
	/**
	* 内容store
	*/
	pkgStore : false,
	/**
	* 自定义模版
	*/
	contentTpl : false,
	/**
	* 中间区域grid
	*/
	pkgGrid : false,
	/**
	* 左边区域grid
	*/
	uriGrid : false,
	/**
	* 南边区域view
	*/
	view : false,
	/**
	* 东边区域from
	*/
	from : false,
	/**
	* 新南边区域
	*/
	newSouthView : false,
	/**
	* 新东边区域
	*/
	/**
	* 表格1
	*/
	cakeChart1dataStore : false,
	cakeChart1 : false,
	cakeChart2dataStore:false,
	cakeChart2:false,
	lineChart1dataStore:false,
	lineChart1:false,
	newSouthEastView : false,

	pakGrid : false,
	type : '',
	constructor : function(_config) {
		if (_config == null) {
			_config = {};
		}
		// 将_config附加到当前对象
		Ext.apply(this, _config);
		/**
		 * 手机表格store
		*/
		this.uriStore = new Ext.data.Store({
			fields : [ 'uriId', 'host', 'path' ],// 定义字段
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

		pkgStore = new Ext.data.Store({
			fields : [ 'packageId', 'nativeMac', 'remoteIp','remotePort', 'protocolType','flowDirectioin', 'flowAmount','dateVisit', 'type' ],// 定义字段
			proxy : {
				type : 'ajax',
				url : 'showPkg.action',
				// timeout : myBigTimeout,
				reader : {
					type : 'json',
					totalProperty : 'totalCount',
					idProperty : 'pkgId',
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
		pkgStore.on('beforeload', function(gridStore,options) {
			var new_params = {
				phoneid : temp
			};
			// alert(temp);
			Ext.apply(gridStore.proxy.extraParams, new_params);
		});
		/**
		* 内容store
		*/
		this.contentStore = new Ext.data.Store({
			fields : [ 'title', 'meta', 'country', 'province','city', 'district', 'isp' ],// 定义字段
			autoLoad : true,
			data : [],
			proxy : {
				type : 'ajax',
				url : 'extractPageContent.action',
				reader : {
					type : 'json',
					totalRecords : 'totalCount',
					root : 'rows'
				}
			}
		}),
		/**
		* 饼状图的相关数据
		* 
		*/
		this.cakeChart1dataStore = Ext.create('Ext.data.JsonStore', {
			fields : [ 'name', 'data' ],
			proxy : {
				type : 'ajax',
				url : 'UpDownstreamTraffic.action',
				reader : 'json'
			},
			autoLoad : {
				start : 0,
				limit : 10
			}
		});
		this.cakeChart2dataStore = Ext.create('Ext.data.JsonStore', {
			fields : [ 'name', 'data','showName'],
			proxy : {
				type : 'ajax',
				url : 'AllTrafficPropotion.action',
				reader : 'json'
			},
			autoLoad : {
				start : 0,
				limit : 10
			}
		});
		this.lineChart1dataStore = Ext.create('Ext.data.JsonStore', {
			fields: ['name', 'data'],
			proxy : {
				type : 'ajax',
				url : 'RecentDaysTraffic.action',
				reader : 'json'
			},
			autoLoad : {
				start : 0,
				limit : 10
			}
		});
		this.cakeChart1 = new Ext.chart.Chart({
//			title : '上下行流量比例',
			store : this.cakeChart1dataStore,
			width : 150,
			height : 135,
			animate : true,
			theme : 'Base:gradients',
			series : [{
				type : 'pie',
				angleField : 'data',
				showInLegend : true,
				tips : {
					trackMouse : true,
					width : 140,
					height : 28,
					renderer : function(storeItem, item) {
						this.setTitle(storeItem.get('name')+ ': '+ storeItem.get('data') + '%');
					}
				},
				highlight : {
					segment : {
						margin : 20
					}
				},
				label : {
					field : 'name',
					display : 'rotate',
					contrast : true,
					font : '18px Arial'
				}
			}]
		});
		this.cakeChart2 = new Ext.chart.Chart({
//			title : '所占总流量比例',
			store : this.cakeChart2dataStore,
			width : 150,
			height : 135,
			animate : true,
			theme : 'Base:gradients',
			series : [{
				type : 'pie',
				angleField : 'data',
				showInLegend : true,
				donut:20,//麦圈风格
				tips : {
					trackMouse : true,
					width : 140,
					height : 28,
					renderer : function(storeItem, item) {
						this.setTitle(storeItem.get('name')+ ': '+ storeItem.get('data') + '字节');
					}
				},
				highlight : {
					segment : {
					margin : 20
					}
				},
				label : {
					field : 'showName',
					display : 'rotate',
					contrast : true,
					font : '18px Arial'
				}
			}]
		});
		this.lineChart1 = new Ext.chart.Chart({
			width : 150,
			height : 135,
			animate: true,
			store: this.lineChart1dataStore,
			axes: [{
				type: 'Numeric',
				position: 'left',
				fields: ['data'],
				label: {
					renderer: Ext.util.Format.numberRenderer('0,0')
				},
				title: '流量',
				grid: true,
				minimum: 0
			},
			{
				type: 'Category',
				position: 'bottom',
				fields: ['name'],
				title: '近五天流量趋势'
			}],
			series: [{
				type: 'line',
				highlight: {
					size: 7,
					radius: 7
				},
				axis: 'left',
				fill: true,
				xField: 'name',
				yField: 'data',
				markerConfig: {
					type: 'circle',
					size: 4,
					radius: 4,
					'stroke-width': 0
				 }
			}]
		});
		/**
		* 自定义模版
		*/
		this.contentTpl = new Ext.XTemplate(
			'<table border=0 cellspacing=0 cellpadding=0 width=100%>',
			'<tpl for=".">',
			'<td valign="top" class="line" height="100%" style="padding-top: 10px;">',
			'<a name="pid5379392" href="misc.php?action=viewratings&amp;tid=410826&amp;pid=5379392" title="評分 0"></a>',
			'<div>',
			'<div style="font-size: 21px">',
			'<div align="center">{title}</div>',
			'</div>',
			'<div style="font-size: 18px">',
			'<div align="center">{country}&nbsp;&nbsp;{province}&nbsp;&nbsp;{city}&nbsp;&nbsp;&nbsp;{isp}</div>',
			'</div>',
			'</div>',
			'<br><br>',
			'<div style="font-size: 18px">{meta}</div>',
			'</td>', '</tpl>', '</table>'),

		/**
		* 中间区域grid
		*/
		pkgGrid = new Ext.grid.Panel({
			region : "center",
			// renderTo : Ext.getBody(),
			autoScroll : true,
			frame : false,
			viewConfig : {
				forceFit : true,
				stripeRows : false,
				getRowClass : function(record) {
					if (record.get('type') == 1) {
						return 'erro-row';
					} else if (record.get('type') == 2) {
						return 'access-row';
					}
				}							
			},
			selType : 'rowmodel',// 设置为单元格选择模式Ext.selection.RowModel
			bbar : new Ext.PagingToolbar({
				store : pkgStore,
				displayInfo : true
			}),
			listeners : { // 监听双击事件
				dblclick : {
					element : 'body',
					fn : function() {
						Ext.regModel('Address',{
							fields : [{
										name : 'country',
										type : 'String'
									},
									{
										name : 'province',
										type : 'String'
									},
									{
										name : 'city',
										type : 'String'
									},
									{
										name : 'district',
										type : 'String'
									},
									{
										name : 'isp',
										type : 'String'
							}],
							proxy : {
								type : 'ajax',
								url : 'getAddress.action'
							}
						});
						var address = Ext.ModelManager.getModel('Address');
						var rows = pkgGrid.getSelectionModel().getSelection();
						var num = rows.length;
						if (num > 1) {
							Ext.MessageBox.alert("提示","只能选一项");
						} else if (num == 1) {
							var msgTip = Ext.MessageBox.show({
								title : '提示',
								width : 250,
								msg : '正在获取信息请稍后......'
							});
						}
						temp = rows[0].get('packageId');
						address.load(1,{
							params : {
								pkgId : rows[0].get('remoteIp')
							},
							success : function(rec){
								Ext.getCmp('addressLabel').setText(rec.get('country')+ rec.get('province')+ rec.get('city')+ rec.get('district')+rec.get('isp'));
								msgTip.hide();
							}
						});
						Ext.get("frame1").dom.src = "http://"+rows[0].get('remoteIp');
						this.uriStore.load({
							params : {
								pkgId : rows[0].get('packageId')
							}
						});
					},
					scope : this
				},
				itemmouseenter : function(view,record, item) {
					if (record.get('type') == 1) {
						Ext.fly(item).set({
							'data-qtip' : '流量异常'
						});
					} else if (record.get('type') == 2) {
						Ext.fly(item).set({
							'data-qtip' : '黑名单'
						});
					} else if (record.get('type') == 3) {
						Ext.fly(item).set({
							'data-qtip' : '被信任'
						});
					} else {
						Ext.fly(item).set({
							'data-qtip' : '正常'
						});
					}
				},
				//右键菜单
				itemcontextmenu : function(view, record, item, index,e, eOpts){
					e.preventDefault();
					if(!rMenu){
						rMenu =new Ext.menu.Menu({
							floating : true,
							items: [{
								id: 'rMenu_add',
								text : '添加到黑名单',
								handler : function(){
								  var rec = pkgGrid.getSelectionModel();
								  if(rec.hasSelection()){
								      Ext.Msg.confirm("警告","确定要加入黑名单吗？",function(button){
								    	  	if(button == "yes"){
								         var sel = rec.getSelection()[0];
								         Ext.Ajax.request({
								        	 	url : 'addAccess.action',
								            params : {
								              //aid : sel.get('aid'),
								              ip : sel.get('remoteIp'),
								              port : sel.get('remotePort'),
								              accessType : '1'
								                },
								            success : function(response){
								              Ext.Msg.show({
								                 title : '操作提示',
								                 msg : '操作成功！',
								                 buttons : Ext.Msg.YES
								                   });
								              pkgStore.load({
								                 params : {
								                    start : 0,
								                    limit : 20
								                    	}
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
								  }
								}
							}]
						});
					}
					rMenu.showAt(e.getXY());
				}
				},
				store : pkgStore,
				columns : [// 配置表格列
					{
						header : "ID",
						width : 5,
						dataIndex : 'packageId',
						flex : 1,
						hidden : true
					}, 
					{
						header : "源Mac地址",
						width : 20,
						dataIndex : 'nativeMac',
						flex : 1,
						sortable : true
					}, 
					{
						header : "目标IP",
						width : 10,
						dataIndex : 'remoteIp',
						flex : 1,
						sortable : true
					}, 
					{
						header : "端口",
						width : 10,
						dataIndex : 'remotePort',
						flex : 1,
						sortable : true
					}, 
					{
						header : "协议",
						width : 10,
						dataIndex : 'protocolType',
						flex : 1,
						sortable : true
					}, 
					{
						header : "流量方向",
						width : 10,
						dataIndex : 'flowDirectioin',
						xtype : 'booleancolumn',
						trueText : '下行',
						falseText : '上行',
						flex : 1,
						sortable : true
					}, 
					{
						header : "总流量",
						width : 15,
						dataIndex : 'flowAmount',
						flex : 1,
						/*
						* renderer:function(value,metadata) {
						* if(value>3000){
						* metadata.style='background-color:#CCFFFF;' }
						* return value; },
						*/
						sortable : true
					}, 
					{
						header : "日期",
						width : 20,
						dataIndex : 'dateVisit',
						xtype : 'datecolumn',
						format : 'Y年m月d日',
						flex : 1,
						sortable : true
					}, 
					{
						dataIndex : 'type',
						hidden : true
					}]
		}),
		/**
		* 新南边区域
		*/
		this.newSouthView = new Ext.panel.Panel({
//			height : 220,
			height:400,
			autoScroll : true,
			region : "south",
			layout: 'border',
			id : 'newSouthView',
			// title:'新南边区域',
			defaults : {
				frame : false
			},
			items : [{	 
					xtype:'form',
					region:'center',
					flex:1,
					id:'panel2',
					height: 320,
					autoScroll:true,
					fitToFrame: true,
					tbar: [{id:'addressLabel',xtype: 'label', text:'',height:22 }],
					html: '<iframe id="frame1" frameborder="0" width="100%"height="100%"></iframe>'
				},
				{
					flex:1,
					xtype : 'panel',
//					html : 'zuo',
					region:'east',
					width:350,
					columnWidth : .5,
					height : 320,
					layout : 'accordion',
					items : [{
						title : '上行下行流量比例',
						id : 'cakeChartPanel1',
						xtype : 'panel',
						layout : 'fit',
						items : [ this.cakeChart1 ]
						}, 
						{
							title : '各设备流量比例',
							id : 'cakeChartPanel2',
							xtype : 'panel',
							layout : 'fit',
							items : [ this.cakeChart2 ]
						},
						{
							title : '流量使用趋势图',
							id : 'cakeChartPanel3',
							xtype : 'panel',
							layout : 'fit',
							items : [this.lineChart1]	
						}]
				}]
		}),

		/**
		* 南边区域view
		*/

		this.view = new Ext.view.View({
			columnWidth : .5,
			height : 220,
			autoScroll : true,
			store : this.contentStore,
			// region : "south",
			id : 'contentview',
			tpl : this.contentTpl,
			deferEmptyText : false,
			bodyStyle : 'font-size:17px',
			itemSelector : 'div.thumb-wrap',
			emptyText : '请选择内容'
		}),
		/**
		* 东边区域from
		*/
		this.uriGrid = new Ext.grid.Panel({
			region : "east",
			width : 300,
			autoScroll : true,
			frame : false,
			viewConfig : {
				forceFit : true,
				stripeRows : true
				// 在表格中显示斑马线
			},
			selType : 'rowmodel',// 设置为单元格选择模式Ext.selection.RowModel
			/*
			* bbar : new Ext.PagingToolbar({
			* store : this.phoneStore,
			* displayInfo : true }),
			*/
			listeners : { // 监听双击事件
				dblclick : {
					element : 'body',
					fn : function() {
						var rows = this.uriGrid.getSelectionModel().getSelection();
						var num = rows.length;
						if (num > 1) {
							Ext.MessageBox.alert("提示","只能选一项");
						} else if (num == 1) {
							var msgTip = Ext.MessageBox.show({
								title : '提示',
								width : 250,
								msg : '正在获取信息请稍后......'
							});
						}
						// test="http://"+rows[0].get('uriRoot')+rows[0].get('uriPath');
						Ext.get("frame1").dom.src = "http://"+ rows[0].get('host').replace(/\s+/g,"")
																+ rows[0].get('path').replace(/\s+/g,"");
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
					hidden : true
				}, 
				{
					header : "网站",
					width : 120,
					dataIndex : 'host',
					sortable : true
				}, 
				{
					header : "路径",
					width : 150,
					dataIndex : 'path',
					sortable : true
				}]
		 }),
		/**
		* 将父类的构造拷贝过来
		*/
		IndexPage.superclass.constructor.call(this, {
			id : this.myid, // 唯一id
			iconCls : "houfei-treeNodeLeafIcon",
			layout : "border", // 指定布局为border布局
			items : [ pkgGrid, this.uriGrid,
//									 {
//									 //title: '内容',
//									 region: 'south',//指定子面板所在区域为south
//									 height: 220,
//									 layout: 'fit',
//									 autoScroll:true,
//									 tbar: [
//									 { id:'addressLabel',xtype: 'label', text:
//									 '',height:22 }
//									 ],
//									 items:new Ext.form.Panel({
//									 id: "panel2",
//									 fitToFrame: true,
//									 html: '<iframe id="frame1" frameborder="0" width="100%" height="100%"></iframe>'
//									 })
//									 },
									this.newSouthView 
									]
		});

	}
});
