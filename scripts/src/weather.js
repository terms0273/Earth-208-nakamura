export default class Weather {
    constructor() {
        this.city = "tokyo";
    }

    print(json) {
        this.city = json.name;
        $('#city-name').text(this.city);
        $('#weather').text(json.weather[0].description);
        $('#weather-icon').html("<img src='http://openweathermap.org/img/w/" + json.weather[0].icon +".png'></img>");
    }

    send(cityName) {
        let url = 'http://api.openweathermap.org/data/2.5/weather?q=' +
        cityName +
        '&appid=8ee47547bd73be2f27e5afb68162ecd8';

        $.ajax({
            url: url
        }).then((json) => {
            this.print(json);
            console.log(json);
        }, (err) => {
            console.log(err);
        });
    }
}
