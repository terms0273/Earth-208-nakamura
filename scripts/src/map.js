import L from 'leaflet';

export default class Map {
    constructor() {
        this.map = {};
        this.init();
    }

    init() {
        // map要素が無い場合は地図画面ではない
        if (!$("#map")) {
            console.log("this page is not map page");
            return;
        }

        let std = L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
        });

        let chiriin = L.tileLayer('https://cyberjapandata.gsi.go.jp/xyz/std/{z}/{x}/{y}.png', {
            attribution: "<a href='http://portal.cyberjapan.jp/help/termsofuse.html' target='_blank'>国土地理院</a>"
        });

        let clouds = L.tileLayer('http://tile.openweathermap.org/map/clouds_new/{z}/{x}/{y}.png?appid=8ee47547bd73be2f27e5afb68162ecd8', {
            attribution: "<a></a>"
        });

        let precipitation = L.tileLayer('http://tile.openweathermap.org/map/precipitation_new/{z}/{x}/{y}.png?appid=8ee47547bd73be2f27e5afb68162ecd8', {
            attribution: "<a></a>"
        });

        let pressure = L.tileLayer('http://tile.openweathermap.org/map/pressure_new/{z}/{x}/{y}.png?appid=8ee47547bd73be2f27e5afb68162ecd8', {
            attribution: "<a></a>"
        });

        let wind = L.tileLayer('http://tile.openweathermap.org/map/wind_new/{z}/{x}/{y}.png?appid=8ee47547bd73be2f27e5afb68162ecd8', {
            attribution: "<a></a>"
        });

        let temp = L.tileLayer('http://tile.openweathermap.org/map/temp_new/{z}/{x}/{y}.png?appid=8ee47547bd73be2f27e5afb68162ecd8', {
            attribution: "<a></a>"
        });

        this.map = L.map("map", {
            center: [35.69, 139.69],
            zoom: 16,
            layers: [std]
        });

        let baseMaps = {
            "Mapbox(osm)": std,
            "Mapbox(chiriin)": chiriin
        };

        var overlayMaps = {
            "clouds": clouds,
            "precipitation": precipitation,
            "pressure": pressure,
            "wind": wind,
            "temp": temp
        };

        L.control.layers(baseMaps, overlayMaps).addTo(this.map);

        L.control.scale().addTo(this.map);

        this.map.on('resize', () => {
            this.map.invalidateSize();
        });
    }

    changeLocation(lat, lon) {
        this.map.setView(new L.latLng(lat,lon), 16);
    }
}
