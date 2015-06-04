IndexPage = Ext.extend(Ext.Viewport, {
	/**
	 * 中间区域
	 */
	center : new Ext.TabPanel({
				id : "MainTab",
				region : "center",
				activeTab : 0,
				autoScroll : true, // 自动添加滚动条
				height : 80,
				split : true, // 添加分割线
				// plugins : new Ext.ux.TabCloseMenu(),
				items : {
					closeable : true,
					iconCls : "houfei-homeTabIcon", // tab页图片样式
					title : "首页"
					//html:'<FONT size=6><MARQUEE width=100% align="middle">啦啦啦，我会移动耶！</MARQUEE></FONT></P>'
				}
			}),
	/**
	 * 构造方法
	 */
	constructor : function(userName) {
		
		/**
		 * 将父类的构造拷贝过来
		 */
		IndexPage.superclass.constructor.call(this, {
			layout : "border", // 指定布局为border布局
			items : [{
						region : "north", // 北部布局
						xtype : "panel", // 指定容器为panel
						border : false, // 不要边框
						height : 80, // 高度
						html : "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" ><tr class=\"navigator\"><br><center><font size = 6>流量监测系统</font></center><tr><tr class=\"navigator\"><td width=\"85%\"></td><td> &nbsp;[<A href=\"logout.action\"  target=\"_top\"   style=\"color:#993300;\">退出</A>]&nbsp;&nbsp;</td></tr></table>",
						baseCls : '/FBS_02/images/houfei-top-font.png'
					}, 
					{
						region : "west",
						xtype : "panel",
						layout : "accordion", // 手风琴布局
						title :
							"流量查看",
						split : true, // 添加分割线
						collapsible : true, // 自动伸缩
						width : 200, // 宽度
						layoutConfig : {
							// 展开折叠是否有动画效果
							animate : true
						},
						// 事件监听
						listeners : {
							'expand' : {
								fn : this.onExpandPanel,
								scope : this
							},
							'collapse' : {
								fn : this.onCollapsePanel,
								scope : this
							}
						},
						items : [{
									title : "功能面板", // 标题
									iconCls : "houfei-checkIcon",
									xtype : "treepanel", // 指定类型为树面板
									autoScroll : true, // 自动添加滚动条
									border : false, // 不要边框
									id : "mytree",
									tools : [{
												id : 'refresh', // 刷新按钮
												handler : this.onRefreshTreeNodes,
												scope : this
											}],
									store:new Ext.data.TreeStore({
									    fields:['id','leaf','linkUrl','text','parentId'],
									    nodeParam:'id',
									    proxy:{
									    	type:'ajax',
									    	url:'showNode.action',
									    	reader:'json'
									    },
									    autoLoad:true,
									    root:{
										text : '功能菜单',
										iconCls : 'houfei-treeRootIcon',
										id : '-1'
										}
									}),
									
									// 监听事件
									listeners : {
										/*click : {
											fn : this.onTreeNodeClick,
											scope : this
										}*/
										itemclick : this.onTreeNodeClick,
										scope : this
									}
								}]
					}, this.center, {
						region : "south", // 南方布局
						//split : true, // 添加分割线
						xtype : "panel", // 用panel作为容器
						border : false, // 不要边框
						frame : true, // 强制背景色
						height : 25,// 高度
						resizable: false,
						html : "<div align=\"right\"><font color=\"#15428b\">本系统建议使用火狐浏览器</font></div>"
					}]
		})

	},

	/**
	 * 动态添加标签页
	 * 
	 * @param {}
	 *            _name 节点的text值
	 * @param {}
	 *            _id 节点id
	 * @param {}
	 *            _css css
	 * @param {}
	 *            _link 节点的链接
	 */
	addTab : function(_name, _id, _css, _link) {
		// 动态创建tab标签的id
		var tabId = "tab_" + _id;
		// 动态创建tab标签的标题
		var tabTitle = _name;
		// 动态获取tree的某个节点的链接
		var tabLink = _link;
		// 获取主tab组件
		var centerPanel = Ext.getCmp("MainTab");
		// 根据id获取centerPanel组件的直接子组件的引用
		var tab = centerPanel.getComponent(tabId);
		// 这个要传到load页去，很关键，以后的布局要靠它
		var subMainId = 'tab_' + _id + '_main';
		/**
		 * 如果可以获取到tab页,就不用再次添加tab页了 (获取不到时tab=undefined)
		 * (也就是说如果当前tab页已经渲染就不用再次添加该tab了)
		 */

		if (!tab) {
			// 动态创建tab
			centerPanel.remove(centerPanel.getActiveTab());
				var newTab = centerPanel.add(new Ext.Panel({
							id : tabId, // tab的唯一id
							title : tabTitle, // tab的标题
							iconCls : "houfei-treeNodeLeafIcon", // 图片
							layout : 'fit', // 填充布局,它不会让load进来的东西改变大小
							border : false, // 无边框
							closable : true, // 有关闭选项卡按钮
							loader: { 
									url:tabLink, 
									loadMask: 'loading...', 
									autoLoad: true,
									params : {
										// 这里是关键的一个参数，传给load页，布局的关键
										subMainId : subMainId,
										text:_id
									},
									scripts: true },
							listeners : {
								// 侦听tab页被激活里触发的动作
								activate : this.onActiveTabSize,
								scope : this
							}
			}));
			// 激活加入的tab页(将新创建的tab页获取焦点)
			centerPanel.setActiveTab(newTab);
			/*newTab.load({
				url : tabLink, // 请求服务器的地址
				method : "post", // post请求方式
				params : {
					// 这里是关键的一个参数，传给load页，布局的关键
					subMainId : subMainId
				},
				scope : this, // 范围
				discardUrl : true, // 丢弃url
				nocache : true, // 不缓存
				text : "加载中,请稍候……",
				timeout : 3000, // 延时3秒
				scripts : true
					// 允许执行script
				});*/
		} else {
			// 激活已存在的tab页
			centerPanel.setActiveTab(tab);
		}
	},

	/**
	 * 激活TAB页时改变内部容器的大小
	 */
	onActiveTabSize : function() {
		// 获取当前活动的tab页的body元素的宽度 (不含任何框架元素)
		var w = Ext.getCmp('MainTab').getActiveTab().getWidth();
		// 获取当前活动的tab页的body元素的高度 (不含任何框架元素)
		var h = Ext.getCmp('MainTab').getActiveTab().getHeight();
		// 获取当活动的tab页的id
		var Atab = Ext.getCmp('MainTab').getActiveTab().id;

		// 获取组件
		var submain = Ext.getCmp(Atab + "_main");

		if (submain) {
			submain.setWidth(w);
			submain.setHeight(h);
		}
	},

	/**
	 * treePanel的刷新按钮事件
	 * 
	 * @param {}
	 *            _treePanel
	 */
	onRefreshTreeNodes : function() {
		Ext.getCmp("mytree").root.reload();
	},

	/**
	 * 树的节点单击事件
	 * 
	 * @param {}
	 *            _node 节点
	 * @param {}
	 *            _e
	 */
	onTreeNodeClick : function(view, rec, item, index, e) {
		// 如果当前节点为叶子节点
		if (rec.get('leaf')) {
			// 获取当前节点的值
			var _nodeText = rec.get('text');
			// 获取当前节点的连接
			var _nodeLink = rec.get('linkUrl');
			// 获取当前节点的id
			var _nodeId = rec.get('id');
			// 调用动态添加Tab页方法
			this.addTab(_nodeText, _nodeId, '_css', _nodeLink);

		}
	},

	/**
	 * 面板展开事件
	 * 
	 * @param {}
	 *            _nowCmp
	 */
	onExpandPanel : function(_nowCmp) {
		// alert('展开之后触发');
		this.onActiveTabSize();
	},
	/**
	 * 面板闭合事件
	 * 
	 * @param {}
	 *            _nowCmp
	 */
	onCollapsePanel : function(_nowCmp) {
		// alert('面板关闭后触发');
		this.onActiveTabSize();
	}

});