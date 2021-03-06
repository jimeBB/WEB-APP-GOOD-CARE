<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="content-type" content="text/html" charset="UTF-8">      

        <link href="https://fonts.googleapis.com/css?family=Raleway:300,700" rel="stylesheet">
        <link rel="stylesheet" href="css/bootstrap.min.css">    
        <link rel="stylesheet" href="css/all.css">        
        <link href="css/formulario.css" rel="stylesheet">

        <title>Nuevo diseño</title>        
    </head>
    <style>
        #pestana, #name, #cuenta, #nombre{
            display: none;
        }
        body{
            background-color: #698ed6;
        }

        #mensajeT{
            color: #000000;
        }
    </style>
    <body>

        <form action ="./Session" method= "post">
            <input type="text" id="cuenta" name="cuenta" class="input" value="${requestScope.response2}"> 
            <input type="text" id="pestana" name="pestana" class="input" value="${requestScope.response3}"> 
            <input type="text" id="name" name="name" class="input" value="${requestScope.response}"> 

            <input class="proyecto_buton fas fa-arrow-circle-left" type="submit"  value="Atrás" id="boton_pro">
        </form>  

        <h4 class="text-center mt-2 font-weight-bold">Registrar rutina</h4>

        <form action="./RegistarRutina" method="post" class="mt-5">
            <div id="registro" class="row justify-content-center mx-0">
                <div class="section d-flex flex-column">
                    <input type="text" id="video" name="video" class="input" required>            
                    <span class="user_label">video</span>
                </div>
                <div class="section d-flex flex-column">
                    <input type="text" id="texto" name="texto" class="input" required>            
                    <span class="user_label">texto</span>
                </div>
                <div class="section d-flex flex-column">
                    <input type="text" id="imagen" name="imagen" class="input" required>            
                    <span class="user_label">imagen</span>
                </div>
            </div>
           
            <input type="text" id="cuenta" name="cuenta" class="input" value="${requestScope.response2}"> 
            <input type="text" id="pestana" name="pestana" class="input" value="2"> 
            <input type="text" id="nombre" name="nombre" class="input" value="${requestScope.response}"> 
            <input type="text" id="nombre" name="nombre" class="input" value="${requestScope.mensaje}">
                   
            <input class="login_button" type="submit"  value="GUARDAR">
        </form>

        <p id="MensajeExito" name="mensaje" type="mensajeE"><c:out value="${requestScope.mensaje}"/></p>
        
    </body>
</html>
