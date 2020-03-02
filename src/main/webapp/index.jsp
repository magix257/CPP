  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset=UTF-8>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
</head>
<body>
<div class="jumbotron text-center" style="margin-bottom:0">
<img src="logoetiko.svg" width="447.029" height="95.04" />
</div>

 <div class="container-fluid">
<div class="input-group mb-3">
<div class="input-group-prepend">
<form action="prepare">
 <input type="text" class="form-control" placeholder="NUMER KLIENTA" id="clientNumber" name="clientNumber" required>
 <input type="text" class="form-control" placeholder="NUMER ETYKIETY" id="labelNumber" name="labelNumber" required>
<button class="btn btn-info" type="submit">PRZYGOTUJ PLIKI</button>
</form>
</div>
</div>




</div>
</body>
</html>		