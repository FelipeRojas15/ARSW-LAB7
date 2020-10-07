var app = (function () {
    var cinemaSeleccionado = "";
    var fecha = "";
    var fechaCompleta = "";
    var listados = [];
    var seatslocal = [];
    var archivo = apiclient;
    var nombrePelicula = "";

    setCinema = function(){
      cinemaSeleccionado = $("#name").val();
      $("#name").val("");
    }
    setFecha = function(){
      fecha = $("#Fecha").val();
      $("#Fecha").val("");
    }

    actulizarFunciones = function(){
      archivo.getFunctionsByCinemaAndDate(cinemaSeleccionado, fecha, mapObjetos);
      $("#cineSelect").text("Cinema selected: "+cinemaSeleccionado);
    }
    
    var mapObjetos = function (listados){
      $("#cuerpo").html("");
      var nombre = listados.map(function(listado){
          $("#tabla > tbody").append(
            `<tr>
                    <td>${listado.movie.name}</td>
                    <td>${listado.movie.genre}</td>
                    <td>${listado.date}</td>
                    <td><button type="button" onclick="app.getSeats('${listado.movie.name}','${listado.date}')" class="btn btn-primary">Open Seats</button></td>"
            </tr>`
          );
      })

    }

    functionseats = function(movie, fechaFuncion){
      nombrePelicula = movie;
      fechaCompleta = fechaFuncion;
      Listafecha = fechaCompleta.split(" ");
      fecha = Listafecha[0];
      archivo.getFunctionByNameAndDate(cinemaSeleccionado, fechaCompleta, movie, drawSeats);
    }
    drawSeats = function(datos){
      seatslocal= []
      seatslocal = datos.seats;
      var c = document.getElementById("myCanvas");
      var lapiz = c.getContext("2d");
      lapiz.fillStyle = "#0531ae";
      lapiz.fillRect(20, 20 , 820, 20);
      lapiz.beginPath();
      for (var i = 0; i < seatslocal[0].length; i++) {
        for (var j = 0; j < seatslocal.length; j++) {
          lapiz.fillStyle = "#000000";  
          if(seatslocal[j][i]){
            lapiz.fillStyle = "#999999"
          }
          lapiz.fillRect(i*70+25, j*70+120, 40, 40);
        }
      }
    }
    setFunction = function (){
      var newHora= $("#newHour").val();
      console.log($("#newHour").val());
      if($("#newHour").val() === ""){
        if($("#Pelicula").val() !== "" || $("#genero").val() !== "" || $("#hour").val() !== ""){
          apiclient.newFunction(cinemaSeleccionado, $("#Pelicula").val(), $("#genero").val(), fecha+" "+$("#hour").val(), actulizarFunciones);
        }
      }else{
        archivo.setFunctionByNameAndDate(cinemaSeleccionado, nombrePelicula, fecha+" "+newHora, fechaCompleta, actulizarFunciones)
      }
    }
    formulario = function (){
      document.getElementById("formulario").style.display ="block";
      var c = document.getElementById("myCanvas");
      var lapiz = c.getContext("2d");
      lapiz.clearRect(0, 0, c.width, c.height);
    },
    deleteFunction = function(){
      apiclient.deleteFunction(cinemaSeleccionado, nombrePelicula, fechaCompleta, actulizarFunciones);
    }
  return {
    setCinema: setCinema,
    setFecha: setFecha,
    actulizarFunciones: actulizarFunciones,
    cambiarFuncion: setFunction,
    crearFormulario: formulario,
    eliminarFuncion: deleteFunction,
    getSeats :function(movie, fechaFuncion){
      functionseats (movie,fechaFuncion);
    }
  }
})();