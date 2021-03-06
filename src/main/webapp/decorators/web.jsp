<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<!-- Site meta -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Free Bootstrap 4 Ecommerce Template</title>
<!-- CSS -->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="//fonts.googleapis.com/css?family=Open+Sans:400,300,600"
	rel="stylesheet" type="text/css">
<link href="<c:url value="/templates/style.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
	<!-- BEGIN HEADER -->
	<%@ include file="/common/web/header.jsp"%>
	<!-- END HEADER -->

	<dec:body />

	<!-- BEGIN FOOTER -->
	<%@ include file="/common/admin/footer.jsp"%>
	<!-- END FOOTER -->

	<script src="//code.jquery.com/jquery-3.2.1.slim.min.js"
		type="text/javascript"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		type="text/javascript"></script>
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script>
		
		function tanggiam (i){
			if (i == 1){ 
				var giatri = Number($("#quantity").val()) + 1;
				document.getElementById('quantity').value = giatri;
			} else {
				var giatri = Number($("#quantity").val()) - 1;
				document.getElementById('quantity').value = giatri;
			}
			
		}
	</script>

</body>
</html>