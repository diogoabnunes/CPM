class WeatherInfo {
  WeatherInfo({this.weatherID, this.weatherMain, this.weatherDescription, this.weatherIcon, this.mainTemp, this.mainFeelsLike, this.mainPressure, this.mainHumidity, this.mainTempMin, this.mainTempMax, this.wind, this.countryCode, this.cityName, this.sunriseTime, this.sunsetTime, this.timezone});

  final int? weatherID; // weather.id
  final String? weatherMain; // weather.main
  final String? weatherDescription; // weather.description
  final String? weatherIcon; // weather.icon

  final double? mainTemp; // main.temp
  final double? mainFeelsLike; // main.feels_like
  final int? mainPressure; // main.pressure
  final int? mainHumidity; // main-humidity
  final double? mainTempMin; // main.temp_min
  final double? mainTempMax; // main.temp_max

  final double? wind; // wind.speed

  final String? countryCode; // sys.country
  final String? cityName; // name
  final String? sunriseTime; // sys.sunrise
  final String? sunsetTime; // sys.sunset
  final String? timezone; // timezone

  factory WeatherInfo.fromJson(dynamic json) {
    if (json == null) {
      return WeatherInfo();
    }

    return WeatherInfo(
      weatherID: 1,
      weatherMain: "a",
      weatherDescription: "b",
      weatherIcon: "c",

      mainTemp: json['main']['temp'],
      mainFeelsLike: json['main']['feels_like'],
      mainPressure: json['main']['pressure'],
      mainHumidity: json['main']['humidity'],
      mainTempMin: json['main']['temp_min'],
      mainTempMax: json['main']['temp_max'],

      wind: json['wind']['speed'],

      countryCode: json['sys']['country'],
      cityName: json['name'],
      sunriseTime: json['sys']['sunrise'],
      sunsetTime: json['sys']['sunset'],
      timezone: json['timezone']
    );
  }

  @override
  String toString() {
    return 'WeatherInfo{cityName: $cityName}';
  }
}