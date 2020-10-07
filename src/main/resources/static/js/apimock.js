apimock = (function () {

    var seats = [[false, false, false, true, true, true, true, true, true, true, true, false], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true]];
    var mockdata = [];
    var function1Y = {"movie": {"name": "SuperHeroesMovie", "genre": "Action"}, "seats": seats, "date": "2018-12-19 17:00"};
    var function2Y = {"movie": {"name": "TheNight", "genre": "Horror"}, "seats": seats, "date": "2018-12-19 19:40"};
    var function3Y = {"movie": {"name": "SuperHeroesMovie", "genre": "Action"}, "seats": seats, "date": "2018-12-19 14:30"};
    var function4Y = {"movie": {"name": "TheEnigma", "genre": "Drama"}, "seats": seats, "date": "2018-12-20 17:00"};

    mockdata["cinemaX"] = {"name": "cinemaX", "functions": [{"movie": {"name": "TheEnigma", "genre": "Drama"}, "seats": seats, "date": "2018-12-18 15:30"}, {"movie": {"name": "TheEnigma2", "genre": "Drama"}, "seats": seats, "date": "2018-12-18 15:30"}]};
    mockdata["cinemaY"] = {"name": "cinemaY", "functions": [function1Y, function2Y, function3Y, function4Y]};

    return {
        getFunctionsByCinema: function (cinema_name, callback) {
            callback(mockdata[cinema_name]);
        },
        getFunctionsByCinemaAndDate: function (cinema_name, fdate, callback) {
            callback(
                    mockdata[cinema_name].functions.filter(
                    funct => funct.date.includes(fdate))
            );
        },
        getFunctionByNameAndDate: function (cinema_name, fdate, movie_name, callback) {
            console.log(cinema_name);
            var data = mockdata[cinema_name].functions.filter(
                funct => funct.date.includes(fdate));
            var funcion = data.find(element => element.movie.name == movie_name);
            callback(funcion);
        }
    }

})();
