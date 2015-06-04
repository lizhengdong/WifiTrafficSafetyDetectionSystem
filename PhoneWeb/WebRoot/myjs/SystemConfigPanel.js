SystemConfigPanel=Ext.extend(Ext.panel.Panel,{
	configForm:false,
	constructor : function(_config) {
		if (_config == null) {
			_config = {};
		}// 将_config附加到当前对象
		Ext.apply(this, _config);
		
		this.configForm=new Ext.form.Panel({
			fieldDefaults:{//统一设置表单字段默认属性
				labelSeparator :'：',//分隔符
				labelWidth : 80,//标签宽度
				msgTarget : 'side',
				width : 300
			},
			bodyPadding: 5,
			autoScroll:true,
			frame:true,
			renderTo: Ext.getBody(),
			items:[{
				xtype:'textfield',
				name : 'filePath',
				width : 300,
				fieldLabel:'文件路径'
			},{
				xtype:'textfield',
				width : 300,
				name : 'copyPath',
				fieldLabel:'备份路径'
			},{
	            xtype      : 'fieldcontainer',
	            width : 300,
	            fieldLabel : '是否抓取',
	            defaultType: 'radiofield',
	            defaults: {
	                flex: 1
	            },
	            layout: 'hbox',
	            items: [
	                {
	                    boxLabel  : '是',
	                    name      : 'isContinue',
	                    inputValue: 'Y',
	                    id        : 'radio1',
	                    checked:true
	                }, {
	                    boxLabel  : '否',
	                    name      : 'isContinue',
	                    inputValue: 'N',
	                    id        : 'radio2'
	                }
	            ]
	        },{
	        	xtype:'textfield',
	        	width : 300,
				name : 'nativeMac',
				fieldLabel:'apMac'
	        },{
	        	xtype:'textfield',
	        	width : 300,
				name : 'wfpW',
				fieldLabel:'wifi密码'
	        }],
	        buttons:[{
				text : '提交',
				scope:this,
				handler :this.submitForm
			},'->']
		}),
		
		/*this.submitForm=function (){
			this.configForm.form.submit({
				clientValidation:true,//进行客户端验证
				waitMsg : '正在提交数据请稍后',//提示信息
				waitTitle : '提示',//标题
				url : 'editConfig.action',//请求的url地址
				method:'POST',//请求方式
				success:function(form,action){//加载成功的处理函数
					win.hide();
					updateBookGrid(action.result.bookId);
					Ext.Msg.alert('提示','新增书籍成功');
				},
				failure:function(form,action){//加载失败的处理函数
					Ext.Msg.alert('提示','新增书籍失败');
				}
			})
		},*/
		/*将父类的构造拷贝过来*/
		IndexPage.superclass.constructor.call(this, {
					id : this.myid, // 唯一id
					iconCls : "houfei-treeNodeLeafIcon",
					layout : "border", // 指定布局为border布局
					items : [this.configForm]
		})
	},
	submitForm : function (){
		this.configForm.form.submit({
			clientValidation:true,//进行客户端验证
			waitMsg : '正在提交数据请稍后',//提示信息
			waitTitle : '提示',//标题
			url : 'editConfig.action',//请求的url地址
			method:'POST',//请求方式
			success:function(form,action){//加载成功的处理函数
			},
			failure:function(form,action){//加载失败的处理函数
			}
		})
	},
	loadForm:function(){
		//加载表单数据
		this.configForm.form.load({
			waitMsg : '正在加载数据请稍后',//提示信息
			waitTitle : '提示',//标题
			url :'showConfig.action',//请求的url地址
			method:'GET',//请求方式
			failure:function(form,action){//加载失败的处理函数
				Ext.Msg.alert('提示','数据加载失败');
			}
		});
	}
})
