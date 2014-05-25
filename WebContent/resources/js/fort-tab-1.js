var g_tab_metadata = {};
var _localcache={};
var counter=0;
var g_contextPath;
var editcode='<a class="delete" href=""><i class="icon-trash"></i>&nbsp;</a>&nbsp;'+
'<a class="edit" href=""><i class="icon-pencil"></i>&nbsp;</a>'+
'<a class="save" href=""><i class="icon-ok"></i>&nbsp;</a>';
function storeTabMetaData(tabName,str){
	g_tab_metadata[tabName]=str;
}

function initTable(name,tab,aDataSet,contextPath,patId,allowEdit){
	aoColData = [];
	g_contextPath=contextPath;
	aoColData.push({"mDataProp":"row"});
	for(var i=0;i<g_tab_metadata[name].columns.length;i++){
		aoColData.push({"mDataProp":g_tab_metadata[name].columns[i].id});
	}
	if(allowEdit)
		aoColData.push({"mDataProp":"edit"});
	var oTable = tab.dataTable( {
		"bPaginate": g_tab_metadata[name].paginate,
		"sPaginationType" : "bootstrap",
		"bInfo": false,
		"aaSortingFixed": [[0,'asc']],
		"bFilter": g_tab_metadata[name].searchable,
		"iTabIndex": 1,
        "aoColumnDefs": [
         	            { "bSortable": false, "aTargets": [ 0 ] },
         	        ],
        "aaSorting": [[ 0, 'asc' ]],
        //"aaData": aDataSet,
		"sAjaxSource":contextPath+"/a/"+g_tab_metadata[name].action+".doo?actionType=refresh&encounterId="+getEncounterDate(),
				//patId="+patId,
		"aoColumns": aoColData,
        'fnServerData':function(sSource, aoData, fnCallback) {
        	 var thistable = this;
        	 $.ajax({
                 'dataType':'json',
                 'type':'GET',
                 'url':sSource,
                 'data':aoData,
                 'success':function(json) {
                	 if(allowEdit)
                		 for(var i = 0; i < json.aaData.length; i++)
                			 json.aaData[i].edit = editcode;
                    fnCallback(json);
                    if(allowEdit){
                    	//bind delete event for existing rows in the table
                    	bindDeleteToExistingRows(thistable);
                    	//bind edit event for existing rows in the table
                  		bindEditToExistingRows(thistable);
                  		//bind save event for existing rows in the table
                  		bindSaveToExistingRows(thistable);
                    }
                 }
             });
         },
        "fnDrawCallback": function ( oSettings ) {
			var that = this;
            if ( oSettings.bSorted || oSettings.bFiltered )
            {
                this.$('td:first-child', {"filter":"applied"}).each( function (i) {
                    that.fnUpdate( i+1, this.parentNode, 0, false, false );
                } );
            }
        },
	});
	//bind delete event for existing rows in the table
	bindDeleteToExistingRows(oTable);
	
	$('#'+name+'_arow').click( function (e) {
		bindAddRowToTable('#'+name, e);
	} );
}

function initTableD(name,tab,aDataSet,contextPath,date){
	aoColData = [];
	aoColData.push({"mDataProp":"row"});
	for(var i=0;i<g_tab_metadata[name].columns.length;i++){
		aoColData.push({"mDataProp":g_tab_metadata[name].columns[i].id});
	}
	aoColData.push({"mDataProp":"edit"});
	var oTable = tab.dataTable( {
		"bPaginate": g_tab_metadata[name].paginate,
		"sPaginationType" : "bootstrap",
		"bInfo": false,
		"aaSortingFixed": [[0,'asc']],
		"bFilter": g_tab_metadata[name].searchable,
		"iTabIndex": 1,
        "aoColumnDefs": [
         	            { "bSortable": false, "aTargets": [ 0 ] },
         	        ],
		"sAjaxSource":contextPath+"/a/"+g_tab_metadata[name].action+".doo?actionType=refresh&date="+getDateAsString(new Date(date)),
		"aoColumns": aoColData,
        'fnServerData':function(sSource, aoData, fnCallback) {
        	 var thistable = this;
        	 $.ajax({
                 'dataType':'json',
                 'type':'GET',
                 'url':sSource,
                 'data':aoData,
                 'success':function(json) {
                	 for(var i = 0; i < json.aaData.length; i++)
                		 json.aaData[i].edit = editcode;
                      fnCallback(json);
                    //bind delete event for existing rows in the table
                  	bindDeleteToExistingRows(thistable);
                  	
                  	//bind edit event for existing rows in the table
                  	bindEditToExistingRows(thistable);
                  	
                  	//bind save event for existing rows in the table
                  	bindSaveToExistingRows(thistable);
                 }
             });
         },
         "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				$(nRow).dblclick(function (ev) {
			    	areql(contextPath+"/a/patientcenter.doo","body-id", {patId:aData.patId,nav:1});	// NAV_FROM_DASHBOARD
			    });
				$(nRow).hover(function(){$(this).css("cursor","pointer")},function(){$(this).css("cursor","auto")});
         }
	});
	bindDeleteToExistingRows(oTable);
}
function editRow ( oTable, nRow ){
	var aData = oTable.fnGetData(nRow);
	var jqTds = $('>td', nRow);
	var l = 1;
	mapsize = 0;
	$.map( g_tab_metadata[oTable.selector.replace('#','')].columns, function(x,v){mapsize++});
	for(var k in g_tab_metadata[oTable.selector.replace('#','')].columns ){
		var columns = g_tab_metadata[oTable.selector.replace('#','')].columns;
		colId=columns[ k ].id;
		colType=columns[ k ].colType;
		if(columns[ k ].editable){
			if(colType==201){//201:COL_SINGLE_SELECT
				data=columns[ k ].data;
				if(columns[k].remoteLookAhead){
					jqTds[l].innerHTML='<input type="hidden" class="bigdrop" style="width:80%" id="'+colId+'" name="'+colId+'" value="'+aData[colId]+'"/>';
					fireLookAhead("#"+colId,columns[k].typeOfData,false,"Search..");
				} else {
					jqTds[l].innerHTML='<input type="text" style="width:80%" name="'+colId+'"value="'+aData[colId]+'" data-provide="typeahead" data-source=\'['+data+']\'>';
				}
			} else if(colType==202){//202:COL_DATE_CAL
				jqTds[l].innerHTML='<input type="text" name="'+colId+'" style="width:90px;" placeholder="select date" data-date-format="mm/dd/yyyy" value="'+aData[colId]+'" class="fort-date"></div>';
			}else {
				jqTds[l].innerHTML='<input type="text" style="width:80%" name="'+colId+'"value="'+aData[colId]+'">';	
			}
		}else{
			jqTds[l].innerHTML='<input readonly type="text" style="width:80%" name="'+colId+'"value="'+aData[colId]+'">';
		}
		l++;
	}
	setupDatePicker( '.fort-date');
	$(jqTds).find('a.edit').css({"visibility":"hidden"});
	$(jqTds).find('a.save').css({"visibility":"visible"});
}
function getPatientId(){
	return $('#patientId').text()
}

function getDateAsString( date ){
	m = date.getMonth()+1 > 9?""+(date.getMonth()+1):"0"+(date.getMonth()+1);
	d = date.getDate()>9?""+date.getDate():"0"+date.getDate();
	return( ""+m+"/"+d+"/"+(date.getYear()+1900) );
}
function getEncounterDate(){
	return $('#encounterId option:selected').attr('id');
}
function getEncounterDateAsString(){
	var ed = new Date(getEncounterDate());
    return (ed.getMonth() + 1)+"/"+ed.getDate()+"/"+(ed.getYear()+1900); 
}
function saveRow ( oTable, nRow ){
	var output = {};
	output.row=$(nRow).find('.sorting_2').text();
    $(oTable.selector+ " input").each(function(){
        output[$(this).attr('name')]=$(this).val();
    });
    output.actionType="save";
    output.patId=getPatientId();
    output.encounterDate = getEncounterDateAsString(); 
    var jqInputs = $('input', nRow);
	for(var i=1,j=1;i<=jqInputs.length;i++){
		if(jqInputs[i-1].name.length){
			if(jqInputs[i-1].classList.contains('bigdrop')){
				var type=g_tab_metadata[oTable.selector.replace('#','')].columns[j-1].typeOfData;
				oTable.fnUpdate(_localcache[type][jqInputs[i-1].value], nRow, j++, false);
			}else{
				oTable.fnUpdate(jqInputs[i-1].value, nRow, j++, false);
			}
		}
	}
	
	var jqTds = $('>td', nRow);
	$(jqTds).find('a.save').css({"visibility":"hidden"});
	$(jqTds).find('a.edit').css({"visibility":"visible"});
	oTable.fnDraw();
	//alert(JSON.stringify(output));
	//alert( output );
	areqF_int(null, getActionNameByTableName(oTable),'message',output);
	return false;
}
function getActionNameByTableName(bTable){
	return g_tab_metadata[bTable.selector.replace('#','')].action;
}
function bindDeleteToExistingRows( tab ){
	var that = tab;
	$( that.selector +' a.delete').bind('click', function (e) {
		e.preventDefault();
		var nRow = $(this).parents('tr')[0];
		deleteRow(that, nRow);
		that.fnDeleteRow( nRow );
		that.fnDraw();
	} );
}
function deleteRow ( oTable, nRow ){
	var output = {};
	output.row=$(nRow).find('.sorting_2').text();
    output.actionType="delete";
    output.patId=getPatientId();
    output.encounterDate=getEncounterDateAsString();
    //alert(JSON.stringify(output));
	areqF_int(null, getActionNameByTableName(oTable),'message',output);
	return false;
}

function bindEditToExistingRows( tab ){
	$( tab.selector +' a.edit').bind('click', function (e) {
		e.preventDefault();
		var nRow = $(this).parents('tr')[0];
		editRow( tab, nRow );
	} );
}
function bindSaveToExistingRows( tab ){
	$( tab.selector +' a.save').bind('click', function (e) {
		e.preventDefault();
		var nRow = $(this).parents('tr')[0];
		saveRow( tab, nRow );
	} );
	$( tab.selector +' a.save').css({"visibility":"hidden"});
}
function bindAddRowToTable( tabName, e ) {
	tab=$(tabName).dataTable();
	var cols = tab.fnSettings().aoColumns;
	var rowNum = tab.fnSettings().fnRecordsTotal()+1;
	var newRow = {};
	for(var i=1;i<cols.length-1;i++){ // skip the first (row number) and the last one (edit fields)
		newRow[cols[i].mData]='';
	}
	newRow['row']=rowNum;
	newRow['edit']=editcode;
	var aiNew = tab.fnAddData( newRow );
	var nRow = tab.fnGetNodes( aiNew[0] );
	var local = tab;
	$(nRow).find('.delete').bind('click', function(e){
		e.preventDefault();
		var nRow = $(this).parents('tr')[0];
		deleteRow(local, nRow);
		local.fnDeleteRow( nRow );
		local.fnDraw();
	});
	
	$(nRow).find('.save').bind('click', function(e){
		e.preventDefault();
		saveRow( local, nRow );
	});
	$(nRow).find('.edit').bind('click', function(e){
		e.preventDefault();
		editRow( local, nRow );
	});
	editRow( local, nRow );
}

function setupDatePicker(id){
	(function(j){setTimeout(function(){
		//$(j).datepicker({ dateFormat: 'dd-MM-yy' });
		//$(j).mask('99/99/9999');
		$(j).datepicker()
		.on('changeDate', function(ev){
			$(this).datepicker('hide');
		});
	}, 50);}(id));//pass id as '#dob'
}	


function selectAppDate(id){
	(function(j){setTimeout(function(){
		//$(j).datepicker({ dateFormat: 'dd-MM-yy' });
		//$(j).mask('99/99/9999');
		var nowTemp = new Date();
		var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

		$(j).datepicker({startDate:now})
		.on('changeDate', function(ev){
			$(this).datepicker('hide');
		});
	}, 50);}(id));//pass id as '#dob'
}

function fireLookAhead(selector, type, searchByIndex, placeHolder ){
	if(_localcache[type]===undefined)
		_localcache[type]={};
    $(selector).select2({
        placeholder: placeHolder,
        minimumInputLength: 3,
        ajax: {
            url: g_contextPath+"/a/"+"lookahead.doo",
            dataType: 'json',
            data: function (term, page) {
                return {t: type, q: term, idx: searchByIndex };
            },
            results: function (data, page) {
                var res = [];
                ij = 0;
                $.each(data.aaData[0], function(i, item){
                	res[ij++]={"id":i,"title":item};
                	_localcache[type][i]=item;
                });
                
                return {results: res};
            }
        },
        initSelection: function(element, callback) {
            // the input tag has a value attribute preloaded that points to a preselected movie's id
            // this function resolves that id attribute to an object that select2 can render
            // using its formatResult renderer - that way the movie name is shown preselected
        	var id=$(element).val();
            if (id!=="") {
                $.ajax(g_contextPath+"/a/"+"lookahead.doo?q="+id+"&t="+type+"&idx="+searchByIndex, {
                    dataType: "jsonp"
                }).done(function(data) { 
                	callback(data); });
            }
        },
        formatResult: function(formRes){return "<table><tr><td><div>" + formRes.title + "</div></td></tr></table>";},
        formatSelection: function(formSel){return formSel.title;},
        dropdownCssClass: "bigdrop",
        escapeMarkup: function (m) { return m; }
    });
};