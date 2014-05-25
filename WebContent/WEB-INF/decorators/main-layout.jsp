<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Hospital Management System</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css" rel="stylesheet"  type="text/css" />
  <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet"  type="text/css" />
</head>
<body>
<div id="wrap">
    <c:import url="/WEB-INF/views/tags/navbar.jsp"/>
    <div class="container">
    	<div class="col-md-10">
        	<div class="row">
            	<div class="col-lg-12">
                	<div class="container">
                    	<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="<c:url value="/resources/js/widgets.js"/>";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
                 	</div>
                    <hr class="soften">
					<div class="pageBody"><decorator:body/></div>
              	</div>
           	</div>
      	</div>
   	</div>
</div>
<hr class="soften">
<c:import url="/WEB-INF/views/tags/footer.jsp"/>
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/widgets.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/demo.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/fort-pagination.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/json2.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/date.format.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/fort-tab-1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/fort-aja.js" />"></script>
</body>
</html>