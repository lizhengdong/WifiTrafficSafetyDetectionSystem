nineSquarePanel = Ext.extend(Ext.panel.Panel, {
	buttonPanel : false,
	constructor : function(_config) {
//		if (_config == null) {
//			_config = {};
//		}// 将_config附加到当前对象
//	Ext.apply(this, _config);
	// 实例化按钮面板
	this.buttonPanel = new Ext.panel.Panel( {
		layout : 'border',// 表格布局
		width : '100%',
		height : '100%',
		flex : 1,
		items : [ {
			xtype : 'panel',
			fullscreen : true,
			layout : 'vbox',
			width : '100%',
			height : '100%',
			defaults : {
				width : '100%',
				height : '100%'
			},
			items : [

			{
				xtype : 'panel',
				layout : 'hbox',
				defaults : {},
				items : [ {
					xtype : 'button',
					text : '按钮1'
				}, {
					xtype : 'button',
					text : '按钮2'
				}, {
					xtype : 'button',
					text : '按钮3'
				} ]
			}, {
				xtype : 'panel',
				layout : 'hbox',

				items : [ {
					xtype : 'button',
					text : '按钮4'
				}, {
					xtype : 'button',
					text : '按钮5'
				}, {
					xtype : 'button',
					text : '按钮6'
				} ]
			}, {
				xtype : 'panel',
				layout : 'hbox',
				defaults : {

				},
				items : [ {
					xtype : 'button',
					text : '按钮7'
				}, {
					xtype : 'button',
					text : '按钮8'
				}, {
					xtype : 'button',
					text : '按钮9'
				} ]
			} ]
		} ]
	}),
	/*将父类的构造拷贝过来*/
	IndexPage.superclass.constructor.call(this, {
				id : this.myid, // 唯一id
				iconCls : "houfei-treeNodeLeafIcon",
				layout : "border", // 指定布局为border布局
				items : [this.buttonPanel]
	})
}
})