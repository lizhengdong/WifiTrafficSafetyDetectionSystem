WifiSafetyConfigPanel = Ext
		.extend(
				Ext.panel.Panel,
				{
					myid : 'defaultId',
					// 无线安全设置面板
					wifiSafetyConfigPanel : false,
					// 下拉列表数据源Store
					certificateStore : false,
					// certificateStoreWpa : false,
					encryptionStore : false,

					constructor : function(_config) {
						if (_config == null) {
							_config = {};
						}// 将_config附加到当前对象
						Ext.apply(this, _config);
								// 预定义本地下拉列表各项数据源
								certificateStore = new Ext.data.Store({
									fields : [ 'certificateTypePsk',
											'certificateTypeWpa' ],
									data : [ {
										certificateTypePsk : '自动',
										certificateTypeWpa : '自动'
									}, {
										certificateTypePsk : 'WPA-PSK',
										certificateTypeWpa : 'WPA'
									}, {
										certificateTypePsk : 'WPA2-PSK',
										certificateTypeWpa : 'WPA2'
									} ]
								}),
								/*
								 * certificateStoreWpa = new Ext.data.Store({
								 * fields : ['certificateTypeWpa'], data : [
								 * {certificateType:'自动'},
								 * {certificateType:'WPA'},
								 * {certificateType:'WPA2'} ] }),
								 */
								encryptionStore = new Ext.data.Store({
									fields : [ 'encryptionType' ],
									data : [ {
										encryptionType : '自动'
									}, {
										encryptionType : 'TKIP'
									}, {
										encryptionType : 'AES'
									} ]
								}),
								// 实例化无线安全设置面板
								this.wifiSafetyConfigPanel = new Ext.form.Panel(
										{
											region : 'center',
											frame : false,
											layout : 'border',
											viewConfig : {
												forceFit : true
											},
											items : [
													{
														region : 'north',
														xtype : 'panel',
														frame : true,
														items : [
																{
																	xtype : 'label',
																	html : '本页面设置路由器无线网络的安全认证选项。'
																},
																{
																	xtype : 'label',
																	html : '(安全提示：为保障网络安全，强烈推荐开启安全设置，并使用WPA-PSK/WPA2-PSK AES加密方法。)',
																	style : {
																		color : 'red'
																	}
																} ]
													},
													{
														region : 'center',
														xtype : 'form',
														frame : true,
														// title : '安全设置',
														autoScroll : true,
														// html : '在此查看和修改安全设置'
														fieldDefaults : {
															labelWidth : 120
														},
														items : [
																{
																	xtype : 'radio',
																	name : 'safetyConfig',
																	inputValue : 'radio1',
																	boxLabel : '不开启无线安全(不推荐)'
																},
																{
																	xtype : 'radio',
																	name : 'safetyConfig',
																	inputValue : 'radio2',
																	boxLabel : 'WPA-PSK/WPA2-PSK'
																},
																{
																	xtype : 'combo',
																	fieldLabel : '认证类型',
																	name : 'rzType1',
																	width : 200,
																	listConfig : {
																		maxWidth : 80
																	},
																	triggerAction : 'all',
																	store : certificateStore,
																	displayField : 'certificateTypePsk',
																	queryMode : 'local',
																	forceSelection : true,
																	typeAhead : true
																},
																{
																	xtype : 'combo',
																	fieldLabel : '加密算法',
																	name : 'jmsf1',
																	width : 200,
																	listConfig : {
																		maxWidth : 80
																	},
																	triggerAction : 'all',
																	store : encryptionStore,
																	displayField : 'encryptionType',
																	queryMode : 'local',
																	forceSelection : true,
																	typeAhead : true
																},
																{
																	xtype : 'textfield',
																	fieldLabel : 'PSK密码',
																	name : 'pskPwd',
																	id : 'pskPw',
																},
																{
																	xtype : 'label',
																	html : '（8-63个ASCII码字符或8-64个十六进制字符）',
																	style : {
																		marginLeft : '120px'
																	}
																},
														/*
														 * { xtype :
														 * 'textfield',
														 * fieldLabel :
														 * '组密钥更新周期',
														 * name:'zmygxzq1', id :
														 * 'udpateTime' }, {
														 * xtype : 'label', html :
														 * '（单位为秒，最小值为30，不更新则为0） ',
														 * style : { marginLeft :
														 * '120px' } }, { xtype :
														 * 'radio', name :
														 * 'safetyConfig',
														 * inputValue:'radio3',
														 * boxLabel : 'WPA/WPA2' }, {
														 * xtype : 'combo',
														 * fieldLabel : '认证类型',
														 * name:'rzlx2', width :
														 * 200, listConfig : {
														 * maxWidth : 80 },
														 * triggerAction :
														 * 'all', store :
														 * certificateStore,
														 * displayField :
														 * 'certificateTypeWpa',
														 * queryMode : 'local',
														 * forceSelection :
														 * true, typeAhead :
														 * true }, { xtype :
														 * 'combo', fieldLabel :
														 * '加密算法', name:'jmsf2',
														 * width : 200,
														 * listConfig : {
														 * maxWidth : 80 },
														 * triggerAction :
														 * 'all', store :
														 * encryptionStore,
														 * displayField :
														 * 'encryptionType',
														 * queryMode : 'local',
														 * forceSelection :
														 * true, typeAhead :
														 * true }, { xtype :
														 * 'textfield',
														 * fieldLabel :
														 * 'Radius服务器IP',
														 * name:'radiusServerIP',
														 * id : 'radiusServerIp' }, {
														 * xtype : 'textfield',
														 * fieldLabel :
														 * 'Radius端口',
														 * name:'radiusPort', id :
														 * 'radiusPort' }, {
														 * xtype : 'label', html :
														 * '（1-65535，0表示默认端口：1812） ',
														 * style : { marginLeft :
														 * '120px' } }, { xtype :
														 * 'textfield',
														 * fieldLabel :
														 * 'Radius密码',
														 * name:'radiusPwd', id :
														 * 'radiusPw' }, { xtype :
														 * 'textfield',
														 * fieldLabel :
														 * '组密钥更新周期',
														 * name:'zmygxzq2', id :
														 * 'udpateTime2' }, {
														 * xtype : 'label', html :
														 * '（单位为秒，最小值为30，不更新则为0） ',
														 * style : { marginLeft :
														 * '120px' } }
														 */],
														buttonAlign : 'center',
														buttons : [ {
															text : '保存',
															height : 30,
															width : 80,
															scope : this,
															handler : this.submitForm
														} ]

													} ]
										}),
								/* 将父类的构造拷贝过来 */
								IndexPage.superclass.constructor.call(this, {
									id : this.myid, // 唯一id
									iconCls : "houfei-treeNodeLeafIcon",
									layout : "border", // 指定布局为border布局
									items : [ this.wifiSafetyConfigPanel ]
								})
					},
					submitForm : function() {

						this.wifiSafetyConfigPanel.form.submit({
							clientValidation : true,// 进行客户端验证
							waitMsg : '正在提交数据并重启wifi',// 提示信息
							waitTitle : '提示',// 标题
							url : 'wifiSafetyConfig.action',// 请求的url地址
							method : 'POST',// 请求方式
							success : function(form, action) {// 加载成功的处理函数
								Ext.Msg.show({
									title : '操作提示',
									msg : '修改成功！',
									buttons : Ext.Msg.YES
								});
							},
							failure : function(form, action) {// 加载失败的处理函数
								Ext.Msg.show({
									title : '操作提示',
									msg : '保存失败！',
									buttons : Ext.Msg.YES
								});
							}
						})
					},
				})