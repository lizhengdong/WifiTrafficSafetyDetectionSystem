NetworkParameterPanel = Ext.extend(Ext.panel.Panel,{
	myid : 'defaultId',
	//网络参数面板容器
	networkParameterPanel : false,
	//下拉菜单Store
	subnetMaskComboboxStore : false,
	wanTypeComboboxStore : false,
	constructor : function(_config) {
		if (_config == null) {
			_config = {};
		}// 将_config附加到当前对象
	Ext.apply(this, _config);
	//下拉菜单Store
	subnetMaskComboboxStore = new Ext.data.Store({
		fields : ['subnetMask'],
		data : [{subnetMask:'255.0.0.0'},
		        {subnetMask:'255.255.0.0'},
		        {subnetMask:'255.255.255.0'}]
	});
	wanTypeComboboxStore = new Ext.data.Store({
		fields : ['wanType'],
		data : [{wanType:'动态IP'},
		        {wanType:'静态IP'},
		        {wanType:'PPPoE'},
		        {wanType:'L2TP'},
		        {wanType:'PPTP'}]
	});
	//实例化网络参数面板容器
	networkParameterPanel = new Ext.panel.Panel({
		id : 'npPanel',
		region : 'center',
		layout : 'border',
		frame : false,
		viewConfig : {
			forceFit : true
		},
		items : [
		         {
		        	 region : 'north',
		        	 xtype : 'form',
		        	 title : 'LAN口设置',
		        	 height : 150,
		        	 //html : '在此设置LAN的基本参数'
		        	 fieldDefaults : {
	        	 	  labelWidth : 80
	         	 	 },
	         	 	 frame : true,
	         	 	 items : [
	         	 	          {
	         	 	        	  xtype : 'displayfield',
	         	 	        	  fieldLabel : 'MAC地址',
	         	 	        	  value : '6C-E8-73-BB-6C-FC'
	         	 	          },
	         	 	          {
	         	 	        	  xtype : 'textfield',
	         	 	        	  fieldLabel : 'IP地址',
	         	 	        	  id : 'lanIpAddress'
	         	 	          },
	         	 	          {
	         	 	        	  xtype : 'combo',
	         	 	        	  fieldLabel : '子网掩码',
	         	 	        	  width : 220,
	         	 	        	  listConfig : {
	        	        	  			maxWidth : 120
	        	          	  	  },
	        	          	  	  triggerAction : 'all',
	        	          	  	  store : subnetMaskComboboxStore,
	        	          	  	  displayField : 'subnetMask',
	        	          	  	  queryMode : 'local',
	        	          	  	  forceSelection : false,
	        	          	  	  typeAhead :true
	        	          }],
	        	      buttonAlign : 'center',
	        	      buttons : [
	        	                 {
	        	                    text : '保存',
	        	                    width : 80,
	        	                    height : 30
	        	                  }]
	        	 
		         },
		         {
		        	 region : 'center',
		        	 xtype : 'form',
		        	 title : 'WAN口设置',
		        	 frame : true,
		        	 items : [
		        	          {
		        	        	  id : 'typeCombo',
		        	        	  xtype : 'combo',
		        	        	  fieldLabel : 'WAN口连接类型',
		        	        	  width : 200,
		        	        	  listConfig : {
		        	        	  	maxWidth : 120
		        	          	  },
		        	          	  triggerAction : 'all',
		        	          	  store : wanTypeComboboxStore,
		        	          	  displayField : 'wanType',
		        	          	  queryMode : 'local',
		        	          	  forceSelection : true,
		        	          	  typeAhead : true,
		        	          	  listeners : {
		        	          		  select : function(){
		        	          		  	var sv = Ext.getCmp('typeCombo').getValue();
		        	          		  	if(sv == '动态IP'){
		        	          		  		defaultPanel.hide();
		        	          		  		staticIpPanel.hide();
		        	          		  	    dynamicIpPanel.show();
		        	          		  	}
		        	          		  	if(sv == '静态IP'){
		        	          		  		defaultPanel.hide();
		        	          		  		staticIpPanel.show();
		        	          		  		dynamicIpPanel.hide();
		        	          		  	}
		        	          	  	  }
		        	          	  }
		        	          }]
		        	 
		         },
		         {
		        	 region : 'south',
		        	 id : 'southPanel',
		        	 xtype : 'panel',
		        	 frame : true,
		        	 height : 320,
		        	 autoScroll : true,
		        	 listeners : {
		        	 	'render' : function(){
		        	 		//var np = Ext.getCmp('npPanel');
		        	 		var nps = Ext.getCmp('southPanel');
		        	 		//nps.add(defaultPanel);
		        	 		//alert(nps);
		        	 		nps.add(defaultPanel);
		        	 		nps.add(dynamicIpPanel);
		        	 		nps.add(staticIpPanel);
		        	 		dynamicIpPanel.hide();
		        	 		staticIpPanel.hide();
		         		}
		         	 },
		        	 items : [defaultPanel]
		         }]
	});
	var defaultPanel = new Ext.panel.Panel({
		//title : 'test',
		html : '未选择时的空面板',
		height : 300,
		//layout : 'fit',
		frame : true
	});
	var dynamicIpPanel = new Ext.form.Panel({
		//layout : 'fit',
		height : 300,
		frame : true,
		autoScroll : true,
		fieldDefaults : {
			labelWidth : 140
		},
		items : [
		         {
		        	 xtype : 'displayfield',
		        	 fieldLabel : 'IP地址',
		        	 value : '0.0.0.0'
		         },
		         {
		        	 xtype : 'displayfield',
		        	 fieldLabel : '子网掩码',
		        	 value : '0.0.0.0'
		         },
		         {
		        	 xtype : 'displayfield',
		        	 fieldLabel : '网关',
		        	 value : '0.0.0.0'
		         },
		         {
		        	 xtype : 'textfield',
		        	 fieldLabel : '数据包MTU(字节)',
		        	 text : 1500
		         },
		         {
	   	        	 xtype : 'label',
	   	        	 html : '（默认是1500，如非必要，请勿修改）',
	   	        	 style : {
	   	        	  	marginLeft : '140px'
	   	          	 }
	   	         },
		         {
		        	 xtype : 'checkboxfield',
		        	 boxLabel : '手动设置DNS服务器',
		        	 name : 'manualSet'
		         },
		         {
		        	 xtype : 'textfield',
		        	 fieldLabel : 'DNS服务器'
		         },
		         {
		        	 xtype : 'textfield',
		        	 fieldLabel : '备用DNS服务器(可选)'
		         },
		         {
		        	 xtype : 'textfield',
		        	 fieldLabel : '主机名'
		         },
		         {
		        	 xtype : 'checkboxfield',
		        	 boxLabel : '单播方式获取IP(一般情况下请勿选择)',
		        	 name : 'singleMode'
		         }],
		buttonAlign : 'center',
		buttons : [
		           {
		        	   text : '刷新状态',
		        	   width : 80,
		        	   height : 30
		           },
		           {
		        	   text : '保存',
		        	   width : 80,
		        	   height : 30
		           }]
	});
	var staticIpPanel = new Ext.form.Panel({
		height : 300,
		frame : true,
		autoScroll : true,
		fieldDefaults : {
			labelWidth : 140
		},
		items : [
		         {
		        	 xtype : 'textfield',
		        	 fieldLabel : 'IP地址'
		         },
		         {
		        	 xtype : 'textfield',
		        	 fieldLabel : '子网掩码'
		         },
		         {
		        	 xtype : 'textfield',
		        	 fieldLabel : '网关'
		         },
		         {
		        	 xtype : 'textfield',
		        	 fieldLabel : '数据包MTU(字节)',
		        	 text : 1500
		         },
		         {
	   	        	 xtype : 'label',
	   	        	 html : '（默认是1500，如非必要，请勿修改）',
	   	        	 style : {
	   	        	  	marginLeft : '140px'
	   	          	 }
	   	         },
	   	         {
	   	        	 xtype : 'textfield',
	   	        	 fieldLabel : 'DNS服务器(可选)'
	   	         },
	   	         {
	   	        	 xtype : 'textfield',
	   	        	 fieldLabel : '备用DNS服务器(可选)'
	   	         }],
		buttonAlign : 'center',
		buttons : [
		           {
		        	   text : '保存',
		        	   width : 80,
		        	   height : 30
		           }]
	});
	/*将父类的构造拷贝过来*/
	IndexPage.superclass.constructor.call(this, {
		id : this.myid, // 唯一id
		iconCls : "houfei-treeNodeLeafIcon",
		layout : "border", // 指定布局为border布局
		items : [ networkParameterPanel]
	})
}
})