IndexPage2 = Ext.extend(Ext.Viewport, {
	// 中间区域
	center : new Ext.TabPanel( {
		id : 'MainTab',
		region : 'center',
		activeTab : 0,
		autoScroll : true,
		split : true,
		items : {
			closeable : true,
			iconCls : "houfei-homeTabIcon", // tab页图片样式
			title : "首页"
		}
	}),
	constructor : function(userName) {
		/**
		 * 将父类的构造拷贝过来
		 */
		IndexPage2.superclass.constructor.call(this, {
			layout : 'border',
			items : [ {
				region : 'south',
				xtype : 'panel',
				border : false,
				height : 60,
				layout : {
					type : 'hbox',
					align : 'middle ',
					pack : 'center'
				},
				items : [ {
					xtype : 'button',
					text : '主页',
					height : 60,
					width : 60,
					listeners : {
						click : this.mainButtonClick
					}
				} ]
			} ]
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
	addTheTab : function(_name, _id, _link) {
		alert("添加面板函数");
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
		var newTab = centerPanel.add(new Ext.Panel( {
			id : tabId, // tab的唯一id
			title : tabTitle, // tab的标题
			iconCls : "houfei-treeNodeLeafIcon", // 图片
			layout : 'fit', // 填充布局,它不会让load进来的东西改变大小
			border : false, // 无边框
			closable : true, // 有关闭选项卡按钮
			loader : {
				url : tabLink,
				loadMask : 'loading...',
				autoLoad : true,
				params : {
					// 这里是关键的一个参数，传给load页，布局的关键
					subMainId : subMainId,
					text : _id
				},
				scripts : true
			},
			listeners : {
				// 侦听tab页被激活里触发的动作
				activate : this.onActiveTabSize,
				scope : this
			}
		}));
		// 激活加入的tab页(将新创建的tab页获取焦点)
		centerPanel.setActiveTab(newTab);

	} else {
		// 激活已存在的tab页
		centerPanel.setActiveTab(tab);
	}
},
mainButtonClick : function() {

	// 获取当前节点的值
	var _name = "功能面板";
	// 获取当前节点的连接
	var _link = "/PhoneWeb/jsp/nineSquare.jsp";
	// 获取当前节点的id
	var _id = 1;
	// 调用动态添加Tab页方法
	alert("添加面板函数");
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
		var newTab = centerPanel.add(new Ext.Panel( {
			id : tabId, // tab的唯一id
			title : tabTitle, // tab的标题
			iconCls : "houfei-treeNodeLeafIcon", // 图片
			layout : 'fit', // 填充布局,它不会让load进来的东西改变大小
			border : false, // 无边框
			closable : true, // 有关闭选项卡按钮
			loader : {
				url : tabLink,
				loadMask : 'loading...',
				autoLoad : true,
				params : {
					// 这里是关键的一个参数，传给load页，布局的关键
					subMainId : subMainId,
					text : _id
				},
				scripts : true
			},
			listeners : {
				// 侦听tab页被激活里触发的动作
				activate : this.onActiveTabSize,
				scope : this
			}
		}));
		// 激活加入的tab页(将新创建的tab页获取焦点)
		alert("jh1");
		centerPanel.setActiveTab(newTab);
		alert("jh2");

	} else {
		// 激活已存在的tab页
		centerPanel.setActiveTab(tab);
	}
},
/**
 * 激活TAB页时改变内部容器的大小
 */
onActiveTabSize : function() {
	alert("fd1");
	// 获取当前活动的tab页的body元素的宽度 (不含任何框架元素)
	var w = Ext.getCmp('MainTab').getActiveTab().getWidth();
	// 获取当前活动的tab页的body元素的高度 (不含任何框架元素)
	var h = Ext.getCmp('MainTab').getActiveTab().getHeight();
	// 获取当活动的tab页的id
	var Atab = Ext.getCmp('MainTab').getActiveTab().id;
	alert("fd2");
	// 获取组件
	var submain = Ext.getCmp(Atab + "_main");

	if (submain) {
		submain.setWidth(w);
		submain.setHeight(h);
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