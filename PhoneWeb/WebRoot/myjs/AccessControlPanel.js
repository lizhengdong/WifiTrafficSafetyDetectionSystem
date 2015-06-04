AccessControlPanel = Ext.extend(Ext.panel.Panel, {
	myid : "defaultId",
	/*内容Store*/
	accessControlStore : false,
	/*中间Grid*/
	accessControlGrid : false,
	/*列编辑插件*/
	rowEditing : false,
	/*下拉列表Store*/
	typeComboStore : false,
	constructor : function(_config) {
		if (_config == null) {
			_config = {};
		}// 将_config附加到当前对象
	Ext.apply(this, _config);
	Ext.QuickTips.init();
	/*示例化下拉列表Store*/
	typeComboStore = new Ext.data.Store({
		fields : ['displayType','typeValue'],
		data : [
		        {displayType : '警告', typeValue : 1},
		        {displayType : '不警告', typeValue : 0}]
	}),
	/*实例化内容Store*/
	accessControlStore = new Ext.data.Store( {
		fields : [ 'aid', 'ip', 'port', 'accessType' ],
		proxy : {
			type : 'ajax',
			url : 'getAccess.action',
			reader : {
				type : 'json',
				totalProperty : 'totalCount',
				idProperty : 'aid',
				root : 'rows'
			}
		},
		autoLoad : {
			start : 0,
			limit : 20
		},
		pageSize : 20
	}),
	/*实例化中间Grid*/
	accessControlGrid = new Ext.grid.Panel( {
		region : "center",
		autoScroll : true,
		frame : false,
		viewConfig : {
			forceFit : false,
			stripeRows : true,
			getRowClass : function(record) {
				if (record.get('type') == 1) {
					return 'erro-row';
				} else if (record.get('type') == 2) {
					return 'access-row';
				}
			}
		},
		selType : 'rowmodel',
		plugins : [/*创建列编辑插件*/
		Ext.create('Ext.grid.plugin.RowEditing', {
			pluginid : 'rowEditing',
			saveBtnText : '保存',
			cancelBtnText : '取消',
			autoCancel : false,
			clicksToEdit : 2,
			listeners : {
				edit : function(editor, e) {
					var myMask = new Ext.LoadMask(Ext.getBody(), {
						msg : '正在修改，请稍后...',
						removeMask : true
					});
					myMask.show();
					Ext.Ajax.request( {
						url : 'updateAccess.action',
						params : {
							aid : e.record.get('aid'),
							ip : e.record.get('ip'),
							port : e.record.get('port'),
							accessType : e.record.get('accessType')
						},
						success : function(response) {

							myMask.hide();
							Ext.Msg.show( {
								title : '操作提示',
								msg : '操作成功！',
								buttons : Ext.Msg.YES
							});
							e.record.commit();
					},
					failure : function(response, opts) {
						myMask.hide();
						Ext.Msg.show( {
							title : '操作提示',
							msg : '操作失败！',
							buttons : Ext.Msg.YES
						});
					}
					})
				}
			}
		}) ],
		tbar : [ {
			id : 'btn_new',
			text : '添加',
			icon : 'images/icons/add.png'
		}, {
			text : '删除',
			icon : 'images/icons/delete.png',
			/*删除一行的实现*/
			 handler: function() {
			var record = accessControlGrid.getSelectionModel();
				if (record.hasSelection()) {
					Ext.Msg.confirm("警告", "确定要删除吗？", function(button) {
						if (button == "yes") {
							var selection = record.getSelection()[0];
							Ext.Ajax.request( {
								url : 'deleteAccess.action',
								params : {
									aid : selection.get('aid')
									/*ip : selection.get('ip'),
									port : selection.get('port'),
									accessType : selection.get('accessType')*/
								},
								success : function(response) {
									Ext.Msg.show( {
										title : '操作提示',
										msg : '操作成功！',
										buttons : Ext.Msg.YES
									});
									accessControlStore.load({
										params: {
										start: 0,
										limit: 20
									}
									});
									accessControlGrid.view.refresh();
								},
								failure : function(response, opts) {
									Ext.Msg.show( {
										title : '操作提示',
										msg : '操作失败！',
										buttons : Ext.Msg.YES
									})
								}
							})
						}
					})
				}
			}
		} ],
		store : accessControlStore,
		columns : [ {
			text : 'ID',
			width : 50,
			hideable : true,
			hidden: true,
			sortable : true,
			dataIndex : 'aid'
		}, {
			text : 'IP地址',
			width : 300,
			hideable : false,
			sortable : true,
			dataIndex : 'ip',
			editor : {
				xtype : 'textfield',
				allowBlank : false
			}

		}, {
			text : '端口',
			width : 50,
			hideable : false,
			sortable : true,
			dataIndex : 'port',
			editor : {
				xtype : 'textfield',
				allowBlank : false
			}
		}, {
			text : '是否警告',
			xtype : 'booleancolumn',
			width : 100,
			hideable : true,
			sortable : false,
			dataIndex : 'accessType',
			trueText : '警告',
			falseText : '不警告',
			editor : {
				xtype : 'combo',
				name : 'accessType',
				triggerAction : 'all',
				store : typeComboStore,
				displayField : 'displayType',
				valueField : 'typeValue',
				forceSelection : true,
				queryMode : 'local',
				editable : false
			}
		} ]
	}),
	/*点击添加按钮弹出新窗口*/
	Ext.getCmp('btn_new').on(
			'click',
			function() {
				var form = Ext.create('Ext.form.Panel', {
					bodyStyle : 'padding: 5px 5px 0',
					layout : 'form',
					frame : true,
					fieldDefaults : {
						labelWidth : 100,
						labelAlign : 'right'
					},
					items : [ {
						xtype : 'textfield',
						name : 'ip',
						fieldLabel : 'IP地址',
						fieldStyle : {
							margin : 1,
							width : 200
						},
						labelStyle : 'margin-Top: 1px',
						allowBlank : false,
						autoFitErrors : true,
						blankText : '不能为空'
					}, {
						xtype : 'textfield',
						name : 'port',
						fieldLabel : '端口',
						fieldStyle : {
							margin : 1,
							width : 200
						},
						labelStyle : 'margin-Top: 1px',
						allowBlank : false,
						autoFitErrors : true,
						blankText : '不能为空'
					}, {
						xtype : 'combo',
						name : 'accessType',
						fieldLabel : '是否警告',
						width : 170,
						fieldStyle : {
							margin : 1
						},
						listConfig : {
							maxWidth : 80
						},
						triggerAction : 'all',
						store : typeComboStore,
						displayField : 'displayType',
						valueField : 'typeValue',
						labelStyle : 'margin-Top: 1px',
						forceSelection : true,
						queryMode : 'local'
					} ],
					buttonAlign : 'center',
					buttons : [
							{
								text : '添加',
								width : 80,
								height : 30,
								/*添加按钮的实现*/
								handler : function() {
									Ext.Ajax.request( {
										url : 'addAccess.action',
										params : {
											ip : form.getForm().findField('ip')
													.getValue(),
											port : form.getForm().findField(
													'port').getValue(),
											accessType : form.getForm()
													.findField('accessType')
													.getValue()
										},
										success : function(response) {
											Ext.Msg.show( {
												title : '操作提示',
												msg : '操作成功！',
												buttons : Ext.Msg.YES
											});
											addWindow.close();
											accessControlStore.load({
												params: {
												start: 0,
												limit: 20
											}
											});
											accessControlGrid.view.refresh();
									},
									failure : function(response, opts) {
										Ext.Msg.show( {
											title : '操作提示',
											msg : '操作失败！',
											buttons : Ext.Msg.YES
										});
									}
									})
								}
							}, {
								text : '重置',
								width : 80,
								height : 30,
								/*重置按钮的实现*/
								handler : function() {
									form.getForm().reset();
								}
							} ]
				});
				var addWindow = Ext.create('Ext.window.Window', {
					title : '添加黑名单',
					closeAction : 'hide',
					draggable : false,
					height : 200,
					width : 400,
					layout : 'fit',
					modal : true,
					plain : true,
					//frame: true,
					resizable : false,
					items : [ form ]
				});
				addWindow.show();
			});
	/*将父类的构造拷贝过来*/
	IndexPage.superclass.constructor.call(this, {
		id : this.myid, // 唯一id
		iconCls : "houfei-treeNodeLeafIcon",
		layout : "border", // 指定布局为border布局
		items : [ accessControlGrid, {
			region : 'south',//指定子面板所在区域为south
			height : 25,
			layout : 'fit',
			frame : true,
			html : '双击一行来进行编辑,设置为警告的IP地址在查看其流量包时会以红色显示'
		} ]
	})
}
});