<%@page import="com.fort.util.JSPTableConfig"%>
<%@page import="java.util.List"%>
<%@page import="com.fort.util.JSPUtils"%>
<%@page import="com.fort.consts.JSPConstants"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<%@page import="com.fort.util.DateUtils"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.fort.bean.DashBoardOutPatBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%
	String today = request.getAttribute("date") == null ? new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()): DateUtils.dateToStringConvert((Date)request.getAttribute("date"));
%>

<div class="row">
	<div class="pull-right">
			<button class="btn btn-small btn-primary" onclick="areq_int(null,'addappmainpage','body-id')">Add New Appointment</button>
	</div>
</div>
<div class="alert alert-info">
	<center>
		<div class="btn-group" data-toggle="buttons-radio">
			<button class="btn active"	onclick="document.getElementById('min').value=0;document.getElementById('max').value=24;$('#min').trigger('change')">All Day [<%=today%>]</button>
			<button class="btn"	onclick="document.getElementById('min').value=0;document.getElementById('max').value=12;$('#min').trigger('change')">Morning</button>
			<button class="btn"	onclick="document.getElementById('min').value=11;document.getElementById('max').value=24;$('#min').trigger('change')">Afternoon</button>
		</div>
		<div class="input-prepend date pull-right" id="appDatePicker"
			data-date=<%=today%> data-date-format="yyyy-MM-dd">
			<span class="add-on"><i class="icon-calendar"></i></span> <input
				class="span2" size="16" type="text" id="appointmentDate" value=<%=today%> readonly="">
		</div>
	</center>

	<input type="hidden" id="min" name="min"><input type="hidden" id="max" name="max">
</div>

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {	
	<%
		String tName = JSPConstants.TAB_DASHBOARD_APPOINTMENTS;
		String[] colsInfo = JSPTableConfig.getTableColumnNames(tName);
		String tabHtml = JSPUtils.getTableHTML(tName, colsInfo, false, true);
	%>
		document.getElementById('<%=tName+"_div"%>').innerHTML='<%=tabHtml%>';
		storeTabMetaData('<%=tName%>', <%=JSPTableConfig.getTableConfig(tName).getCfg()%>);
		initTableD('<%=tName%>',$('#<%=tName%>'),[], '<%=request.getContextPath()%>',$('#appointmentDate').val());
		$('#appDatePicker').datepicker().on('changeDate', function(ev) {
			$('#appDatePicker').datepicker('hide');
			areqF_int(ev, 'dashboard', 'body-id', { 'date': getDateAsString( ev.date ), 'actionType':'refresh'});
		});
		/*$.fn.dataTableExt.afnFiltering.push(function(oSettings, aData, iDataIndex) {
			var iMin = document.getElementById('min').value * 1;
			var iMax = document.getElementById('max').value * 1;
			var t = aData[1].split(":")[0];
			var iVersion = t == "-" ? 0 : t * 1;
			if (iMin == "" && iMax == "") {
				return true;
			} else if (iMin == "" && iVersion < iMax) {
				return true;
			} else if (iMin < iVersion && "" == iMax) {
				return true;
			} else if (iMin < iVersion
					&& iVersion < iMax) {
				return true;
			}
			return false;
		});*/
	});
</script>

<div id="DashboardAppointmentsTable_div"></div>
