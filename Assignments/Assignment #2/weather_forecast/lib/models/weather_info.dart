class WeatherInfo {
  WeatherInfo(
      {this.weatherId,
      this.weatherMain,
      this.weatherDescription,
      this.weatherIcon,
      this.mainTemp,
      this.mainFeelsLike,
      this.mainPressure,
      this.mainHumidity,
      this.mainTempMin,
      this.mainTempMax,
      this.windSpeed,
      this.countryCode,
      this.cityName,
      this.sunrise,
      this.sunset,
      this.timezone});

  final int? weatherId;
  final String? weatherMain;
  final String? weatherDescription;
  final String? weatherIcon;

  final double? mainTemp;
  final double? mainFeelsLike;
  final int? mainPressure;
  final int? mainHumidity;
  final double? mainTempMin;
  final double? mainTempMax;

  final double? windSpeed;
  final String? countryCode;
  final String? cityName;
  final int? sunrise;
  final int? sunset;
  final int? timezone;

  factory WeatherInfo.fromJson(dynamic json) {
    if (json == null) {
      return WeatherInfo();
    }

    return WeatherInfo(
        weatherId: json['weather'][0]['id'],
        weatherMain: json['weather'][0]['main'],
        weatherDescription: json['weather'][0]['description'],
        weatherIcon: json['weather'][0]['icon'],
        mainTemp: json['main']['temp'],
        mainFeelsLike: json['main']['feels_like'],
        mainPressure: json['main']['pressure'],
        mainHumidity: json['main']['humidity'],
        mainTempMin: json['main']['temp_min'],
        mainTempMax: json['main']['temp_max'],
        windSpeed: json['wind']['speed'],
        countryCode: json['sys']['country'],
        cityName: json['name'],
        sunrise: json['sys']['sunrise'],
        sunset: json['sys']['sunset'],
        timezone: json['timezone']);
  }

  @override
  String toString() {
    return 'WeatherInfo{cityName: $cityName}';
  }
}
