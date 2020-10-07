var apiclient = (function () {
    let url = "http://localhost:8080/cinema/"

    return {
        getFunctionsByCinema: function (name,callback) {
            $.getJSON(url+name,(data)=>{
                callback(data);
            },null)
        },
        getFunctionsByCinemaAndDate: function (name,date,callback) {
            $.getJSON(url+name+"/"+date,(data)=>{
                callback(data);
            },null)
        },
        getFunctionByNameAndDate: function(name, date, movie,callback){
            $.getJSON(url+name+"/"+date+"/"+movie,(data)=>{
                console.log(data)
                callback(data);
            },null)
        },
        setFunctionByNameAndDate: function(nombreCinema, movie, newfecha, oldFecha, callback){
            var promise = $.ajax({
                url: "/cinema/"+nombreCinema,
                type: 'PUT',
                data: JSON.stringify([movie, newfecha, oldFecha]),
                contentType: "application/json"
            });
            promise.then(
                function () {
                    console.info("OK");
                    callback();
                },
                function () {
                    console.info("ERROR");
            });
        },
        newFunction: function(nombreCinema, newmovie, genero, newfecha, callback){
            var promise = $.ajax({
                url: "/cinema/"+nombreCinema,
                type: 'POST',
                data: JSON.stringify([newmovie, genero, newfecha]),
                contentType: "application/json"
            });
            promise.then(
                function () {
                    console.info("OK");
                    callback();
                },
                function () {
                    console.info("ERROR");
            });
        },
        deleteFunction : function (nombreCinema, movie, fecha, callback){
            var promise = $.ajax({
                url: "/cinema/Eliminar/"+nombreCinema,
                type: 'DELETE',
                data: JSON.stringify([movie, fecha]),
                contentType: "application/json"
            });
            promise.then(
                function () {
                    console.info("OK");
                    callback();
                },
                function () {
                    console.info("ERROR");
            });
        }

    }

})();