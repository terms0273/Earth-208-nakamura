import Weather from "./weather";
import Map from "./map";

$(function() {
    let weather = new Weather();
    let map = new Map();
    //イベント設定 検索
    $('#search-city').click(() => {
        let newCity = $('#input-city').val();
        weather.city = newCity;
        weather.send(newCity);
    });

    $("#input-city").keydown(function(e){
        if(e.keyCode === 13){
            let newCity = $('#input-city').val();
            weather.city = newCity;
            weather.send(newCity);
        }
    });

    $("#city-name").text(weather.city);
});
