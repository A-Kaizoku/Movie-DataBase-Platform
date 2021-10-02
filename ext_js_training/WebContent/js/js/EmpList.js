 Ext.onReady(function(){
	 
		 var data='';
	Ext.define('movieModel', {
	    extend: 'Ext.data.Model',
	    fields: [
			    	{name:'title',type:'string'},
					{name:'description',type:'string'},
					{name:'year',type:'string'},
					{name:'languageId',type:'string'},
					{name:'director',type:'string'},
					{name:'rating',type:'string'},
					{name:'specialFeatures',type:'string'},
					{name:'filmId',type:'string'}
				]
	});
	
	var rating=Ext.create('Ext.data.Store',{
		fields:['name'],
		data: [
				{"name":"G"},
				{"name":"PG"},
				{"name":"PG-13"},
				{"name":"R"},
				{"name":"NC-17"}
			]
	});
	
	var movieStore = Ext.create('Ext.data.Store', {
		storeId: 'movieStore',
		model:  'movieModel',
		pageSize: 10,
		autoLoad: true,
		proxy: {
	        type: 'ajax',
			url: '/ext_js_training/fetchDataAction',
			appendId: true,
			noCache: false,
			enablePaging: true,
	        reader: {
	            type: 'json',
				rootProperty: 'FilmData',
				totalProperty: 'total',
	        },
			success: function(response){
				Ext.Msg.alert('Status','Request Passed.');
				consol.log(response);
			},
			failure: function(response){
				Ext.Msg.alert('Status','Request Failed.');
			},
		}
	});
	
	movieStore.load({
		params: {
			start: 0,
			limit: 10,
			query: 'select'
		}
	});
	
	/*Ext.define('languageModel', {
	    extend: 'Ext.data.Model',
	    fields: [{name: 'languageSelection', type: 'string'}]
	});*/
	
	var langStore = Ext.create('Ext.data.Store', {
		storeId: 'langStore',
	    //model:  'languageModel',
	    data: [
//	        {'num' : "English"},
	    	{"num":"1", "name":"English"},
	        {"num":"2", "name":"Italian"},
	        {"num":"3", "name":"Japanese"},
	        {"num":"4", "name":"Mandarin"},
	        {"num":"5", "name":"French"},
	        {"num":"6", "name":"German"},
	        {"num":"7", "name":"Mongolian"},
	    ],
	});
	
	Ext.define('featuresModel', {
	    extend: 'Ext.data.Model',
	    fields: [{name: 'languageSelection', type: 'string'}]
	});
	
	var featureStore = Ext.create('Ext.data.Store', {
		storeId: 'featuresStore',
	    model:  'featuresModel',
	    data: [
	        {'featureSelect' : "Trailers"},
	        {'featureSelect' : "Commentaries"},
			{'featureSelect' : "Deleted Scenes"},
			{'featureSelect' : "Behind the Scenes"}
	    ],
	});
	
	//-------------------------------add form window----------------------------------
	
	var addForm = Ext.create('Ext.window.Window', {
		title: 'Add Film',
		id: 'addFormId',
		width: 500,
		height: 700,
		closeAction: 'close',
		layout: 'fit',
		items: {
			xtype: 'form',
			bodyPadding: 10,
			layout: 'anchor',
		    defaults: {
		        anchor: '100%'
		    },
			items: [{
				xtype: 'textfield',
				fieldLabel: 'Title',
				id: 'addTitle',
				name: 'title',
				allowBlank: false
			}, {
				fieldLabel: 'Release Year',
				id: 'addYear',
				name: 'releaseYear',
				xtype: 'numberfield',
				minValue:2000,
		    	maxValue:2021,
				allowBlank: false
			}, {
				fieldLabel: 'Special Features',
				id: 'addFeatures',
				name: 'specialFeatures',
				store: featureStore,
				displayField: 'featureSelect',
				forceSelection: 'true',
				allowBlank: false,
				xtype: 'combobox',
				multiSelect:true,
	          	queryMode: 'local',
			}, {
				fieldLabel: 'Rating',
				id: 'addRating',
				name: 'rating',
				xtype:'combobox',
				forceSelection: 'true',
		        store:rating,
		        queryMode: 'local',
		        displayField: 'name',
		        allowBlank: false,
			}, {
				fieldLabel: 'Language',
				id: 'addLang',
				name: 'language',
				store: langStore,
				forceSelection: 'true',
				xtype: 'combobox',
				displayField: 'name',
				queryMode: 'local',
				valueField:'num',
				allowBlank: false
			}, {
				xtype: 'textfield',
				id: 'addDirector',
				fieldLabel: 'Director Name',
				name: 'director',
				allowBlank: false
			}, {
				xtype: 'textfield',
				id: 'addDesc',
				fieldLabel: 'Description',
				name: 'description',
			}],
			buttons: [{
	            text: 'Save',
				handler: function(){
	                var form = this.up('form').getForm()
	                var addFormValues = form.getValues()
	                if (form.isValid()) {
	                    Ext.Ajax.request({
	                        url: '/ext_js_training/addAction',
	                        params: addFormValues,
	                        
	                        success: function (response) {
								console.log('Request Passed')
								console.log(addFormValues)
	                            movieStore.load({
	                                params: {
	                                    start: 0,
	                                    limit: 7,
	                                }
	                            });
								console.log(form.getValues());
								form.reset();
	                            addForm.close();
	                        },
	                        failure: function (response) {
	                            Ext.Msg.alert('Request Failed', 'Please Try Again!');
	                        }
	                        
	                    });
	                    //console.log(form.getValues());
	                }
	            }
	        }, {
	            text: 'Cancel',
	            handler: function(){
	                addForm.close();
	            }
	        }],
	        buttonAlign: 'center',
		}
	});
	
	//-------------------------------edit form window----------------------------------
	
	var editForm = Ext.create('Ext.window.Window', {
	    title: 'Edit',
		width: 500,
		closeAction: 'close',
	    items: [{ 
	        xtype: 'form',
			id: 'editFormId',
			bodyPadding: 10,
			layout: 'anchor',
		    defaults: {
		        anchor: '100%'
		    },
	        items: [{
	            xtype : 'textfield',
	            fieldLabel: 'Film ID',
	            name: 'filmId',
	            id: 'editFilmId',
				hidden: true,
	        }, {
	            xtype : 'textfield',
	            fieldLabel: 'Title',
	            name: 'title',
	            id: 'editTitle'
	        }, {
	            xtype : 'textfield',
	            fieldLabel: 'Description',
	            name: 'description',
	            id: 'editDescription'
	        }, {
	            xtype : 'textfield',
	            fieldLabel: 'Release Year',
	            name: 'releaseYear',
	            id: 'editReleaseYear'
	        }, {
	            xtype: 'combobox',
	            fieldLabel: 'Language',
	            store: langStore,
	            queryMode: 'local',
	            displayField: 'name',
	            valueField:'num',
	            name: 'language',
	            id: 'editLanguage'
	        }, {
	            xtype : 'textfield',
	            fieldLabel: 'Director',
	            name: 'director',
	            id: 'editDirector'
	        }, {
	        	fieldLabel: 'Rating',
				name: 'rating',
				xtype:'combobox',
		        store:rating,
		        queryMode: 'local',
		        displayField: 'name',
		        allowBlank: false,
	            id: 'editFilmRating'
	        }, {
	            xtype : 'textfield',
	            fieldLabel: 'Special Features',
	            name: 'specialFeatures',
	            id: 'editSpecialFeatures'
	        }],
	        
	        buttons: [{
	            text: 'Edit',
				handler: function(){
	                var form = this.up('form').getForm()
	                var editFormValues = form.getValues()
	                if (form.isValid()) {
	                    Ext.Ajax.request({
	                        url: '/ext_js_training/editAction',
	                        params: editFormValues,
	                        success: function (response) {
								console.log("Request Passed")
	                            movieStore.load({
	                                params: {
	                                    start: 0,
	                                    limit: 7,
	                                }
	                            });
								form.reset();
	                            editForm.close();
	                        },
	                        failure: function (response) {
								console.log("Request Failed")
	                            Ext.Msg.alert('Request Failed', 'Please Try Again!');
	                        }
	                    });
	                    console.log(form.getValues());
	                }
	            }
	        }, {
	            text: 'Cancel',
	            handler: function(){
	                editForm.hide();
	            }
	        }],
	        buttonAlign: 'center',
	    }]
	});
	
	//-------------------------------delete form----------------------------------
	
	var deleteForm = Ext.create('Ext.window.Window', {
	    title: 'Delete',
	    width: 400,
	    html: "<p>Are you sure you want to Delete!?</p>",
	    layout: 'fit',
	    closeAction: 'close',
	    items: [{ 
	        xtype: 'form',
	        bodyPadding: 10,
	        id: 'deleteFormId',
			html: "Are you sure you want to delete the selected records ?",
	        items: [{
	            xtype : 'textfield',
	            fieldLabel: 'Film ID',
	            name: 'filmId',
	            id: 'deleteFilmId',
				hidden: true,
	        }],
	        
	        buttons: [{
	            text: 'Delete',
	            iconCls: 'x-fa fa-trash',
	            handler: function(){
	                Ext.getCmp('deleteFilmId').enable();
	                var form = this.up('form').getForm();
	                var deleteFormValues = form.getValues()
	                if (form.isValid()) {
	                    Ext.Ajax.request({
	                        url: '/ext_js_training/deleteAction',
	                        params: deleteFormValues,
	                        success: function (response) {
	                            movieStore.load({
	                                params: {
	                                    start: 0,
	                                    limit: 10,
	                                }
	                            });
								form.reset();
	                            deleteForm.close();
	                        },
	                        failure: function (response) {
	                            Ext.Msg.alert('Request Failed', 'Oops, Please Try Again!');
	                        }
	                    });
	                }
	            }
	        }, {
	            text: 'Cancel',
	            iconCls: 'x-fa fa-times',
	            handler: function(){
	                deleteForm.hide();
	            }
	        }],
	        buttonAlign: 'center',
	    }]
	});
	
	var movieAdvanceSearch = Ext.create('Ext.form.Panel', {
		renderTo: document.body,
		title: 'Movie Advance Search',
		width: "100%",
		height: 250,
		layout: {
	        type: 'vbox',
	        align: 'center',
	        pack: 'center'
	    },
		buttonAlign: 'center',
		items: [{
				xtype: 'container',
				layout: 'hbox',
				defaults: {
			        xtype: 'textfield',
			        padding: '10px 100px',
			        //width: '35%',
			    },
				items: [{
						xtype: 'textfield',
						id: 'movieNameId',
						fieldLabel: 'Movie Name',
						name: 'movieName',
						//flex: 1,
						//margin: '40 0 0 210'
					},
					{
						xtype: 'textfield',
						id: 'directorNameId',
						fieldLabel: 'Director Name',
						name: 'dirName',
						//flex: 1,
						//margin: '40 0 0 310'
					}
				]
			},
			{
				xtype: 'container',
				layout: 'hbox',
				defaults: {
			        xtype: 'textfield',
			        padding: '10px 100px',
			        //width: '35%',
			    },
				items: [{
						xtype: 'datefield',
						format: 'Y',
						id: 'releaseYearId',
						fieldLabel: 'Release Year',
						name: 'releaseDate',
						//margin: '15 0 0 210'
					},
					{
						xtype: 'combobox',
						id: 'languageId',
						fieldLabel: 'Language',
						store: langStore,
						displayField: 'name',
						name: 'lang',
						//margin: '15 0 0 310',
						queryMode: 'local',
						forceSelection: 'true',
					}
				]
			}
		],
		fbar: [{
				type: 'button',
				text: 'Search',
				handler: function () {
					Ext.ComponentQuery.query("#movieGrid")[0].store.proxy.setExtraParam("title",this.up('form').getForm().findField("movieName").getValue());
					Ext.ComponentQuery.query("#movieGrid")[0].store.proxy.setExtraParam("director",this.up('form').getForm().findField("dirName").getValue());
					Ext.ComponentQuery.query("#movieGrid")[0].store.proxy.setExtraParam("year",this.up('form').getForm().findField("releaseDate").getValue());
					Ext.ComponentQuery.query("#movieGrid")[0].store.proxy.setExtraParam("languageId",this.up('form').getForm().findField("lang").getValue());
					Ext.ComponentQuery.query("#movieGrid")[0].store.reload();
				}
			},
			{
				type: 'button',
				text: 'Reset',
				handler: function () {
	            	movieStore.clearFilter();
	                movieStore.load({
	                	params: {
	                    	start: 0,
	                        limit: 7
	                    }
	                });
	                this.up('form').getForm().reset();
	            }
			}
		],
	});
	
	Ext.define('MyGrid', {
		extend: 'Ext.app.ViewModel',
		alias: 'viewmodel.test'
	});
	
	var movieGrid = Ext.create('Ext.grid.Panel', {
		title: 'Movie Grid',
		store: movieStore,
		width: "100%",
	    height:500,
		id: 'movieGrid',
		reference: 'mygrid',
		viewModel: {
			type: 'test'
		},
		columns: [
					{text: 'FilmID', dataIndex: 'filmId',hidden:true},
					{text: 'Title', dataIndex: 'title'},
					{text: 'Description', dataIndex: 'description', flex: 1},
					{text: 'Release Year', dataIndex: 'year', renderer: Ext.util.Format.dateRenderer('Y')},
					{text: 'Language', dataIndex: 'languageId'},
					{text: 'Director', dataIndex: 'director'},
					{text: 'Rating', dataIndex: 'rating'},
					{text: 'Special Features', dataIndex: 'specialFeatures', flex: 1}
		],
		renderTo: document.body,
		selModel: {
			selType: 'checkboxmodel'
		},
		listeners: {
	        selectionChange: function(){
	        	//edit button + Auto form fillup
				if(Ext.ComponentQuery.query('#movieGrid')[0].getSelectionModel().getSelection().length==1){
					Ext.ComponentQuery.query('#editButtonId')[0].enable();
					Ext.ComponentQuery.query('#deleteButtonId')[0].enable();
					var gridData = {}
					var selected = Ext.ComponentQuery.query('#movieGrid')[0].getSelectionModel().getSelection();
					gridData = selected[0].data;
	                Ext.getCmp('editFormId').getForm().setValues(gridData);
					Ext.getCmp('deleteFilmId').setValue(gridData.filmId);
				}
				// delete button (list of all selected filmIds)
				else if(Ext.ComponentQuery.query('#movieGrid')[0].getSelectionModel().getSelection().length>1){
					Ext.ComponentQuery.query('#editButtonId')[0].disable(true);
					Ext.ComponentQuery.query('#deleteButtonId')[0].enable();
					var selected = Ext.ComponentQuery.query('#movieGrid')[0].getSelectionModel().getSelection();
					gridFilmList = [];
	                for(let i = 0; i < selected.length; i++) {
	                	gridFilmList.push(selected[i].data.filmId)
	                }
					console.log(gridFilmList);
					Ext.getCmp('deleteFilmId').setValue(gridFilmList);
	                //Ext.getCmp('deleteFilmId').disable();
				}
				else{
					Ext.ComponentQuery.query('#editButtonId')[0].disable(true);
					Ext.ComponentQuery.query('#deleteButtonId')[0].disable(true);
				}
			},
	    },
		dockedItems: [{
			xtype: 'pagingtoolbar',
			store: movieStore,
			dock: 'top',
			displayInfo: true,
			inputItemWidth: 45,
			items: [
				'|', {
					xtype: 'button',
					text: 'Add',
					id: 'addButtonId',
					iconCls: 'fa fa-plus-circle',
					handler: function () {
						addForm.show();
					},
				},
				'|', {
					xtype: 'button',
					text: 'Edit',
					id: 'editButtonId',
					iconCls: 'fa fa-edit',
					disabled: true,
					handler: function () {
						editForm.show();
					},
				},
				'|', {
					xtype: 'button',
					text: 'Delete',
					id: 'deleteButtonId',
					iconCls: 'fa fa-trash',
					disabled: true,
					handler: function () {
						deleteForm.show();
					}
				}, '|'
			]
		}]
	});
	
	Ext.application({
		name: 'HighRadiusMovieDatabase',
		launch: function () {
			Ext.create('Ext.container.Viewport', {
				items: [movieAdvanceSearch, movieGrid]
			});
		}
	});
 });