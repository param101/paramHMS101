function areq(ev,act,div){
	$('#loading-div').show();
	$.ajax({
		type: 'POST',
		url: act,
		success: function(data,textStatus,request){pAreqSuccess(div,data,textStatus,request)},
        error: function(result,status,xhr ){aerrfunc(result,status,xhr,div)}
	});
}
function areq_signal(act){
	$.ajax({
		type: 'POST',
		url: act,
		success: function(data,textStatus,request){},
        error: function(result,status,xhr ){}
	});
}
function areql(act,div,data){
	$('#'+div).slideUp()
	$('#loading-div').show();
	$.ajax({
		type: 'POST',
		url: act,
		data: data,
        success: function(data, textStatus, request) {pAreqSuccess(div,data,textStatus,request)},
        error: function(result,status,xhr ){aerrfunc(result,status,xhr,div)}
	});
}
function areqF(ev,act,div,data){
	$('#loading-div').show();
	$.ajax({
		type: 'POST',
		url: act,
		data: data,
		success: function(data, textStatus, request) {pAreqSuccess(div,data,textStatus,request)},
		error: function(result,status,xhr ){aerrfunc(result,status,xhr,div)}
	});
}
pAreqSuccess=function(div,data,textStatus,request){
	$('#'+div).slideDown();
	$('#'+div).html(data);  // replace
    var f = request.getResponseHeader("currentFunc");
    var m = $('.menu-begin li');
    m.removeClass('active');
    m.each(function(){
 	   if(this.textContent===f){$(this).addClass('active');}
    });
    $('#loading-div').hide();//slow, fast
}
function aerrfunc(r, s, x, d){
	var r = r.responseText; 
	$('#'+d).html(r);  // replace
	$('#loading-div').hide();//slow, fast
}