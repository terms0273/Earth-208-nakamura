export default class Weather {
    constructor() {
        this.city = "tokyo";
    }

    print(json) {
        this.city = json.name;
        $('#city-name').text(this.city);
        $('#weather').text(json.weather[0].description);
        $('#weather-icon').html("<img src='http://openweathermap.org/img/w/" + json.weather[0].icon +".png'></img>");
        $('#temperature').text(Math.round(json.main.temp - 273.15) + "℃");
        $('#wind').text(json.wind.speed + "m/s");
        $('#cloud').text(json.clouds.all + "%");
        $('#pressure').text(json.main.pressure + "hPa");
        $('#humidity').text(json.main.humidity + "%");
        $('#sunset').text(new Date(json.sys.sunset * 1000));
        $('#sunrise').text(new Date(json.sys.sunrise * 1000));
        $('#latlon').text("lat:" + json.coord.lat + "  lon:" + json.coord.lon);
    }

    send(cityName, map) {
        let url = 'http://api.openweathermap.org/data/2.5/weather?q=' +
        cityName +
        '&appid=8ee47547bd73be2f27e5afb68162ecd8';

        $.ajax({
            url: url
        }).then((json) => {
            this.print(json);
            console.log(json);
            map.changeLocation(json.coord.lat, json.coord.lon);
        }, (err) => {
            console.log(err);
        });
    }
}
