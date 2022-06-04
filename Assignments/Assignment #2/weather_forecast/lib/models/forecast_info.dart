class ForecastInfo {
  ForecastInfo({this.mainTemp, this.mainTempMin, this.mainTempMax, this.mainPressure, this.mainHumidity, this.weatherID, this.weatherMain, this.weatherDescription, this.weatherIcon, this.windSpeed, this.probabilityOfPrecipitation, this.forecastedData});

  final String? mainTemp; // list.main.temp
  final String? mainTempMin; // list.main.temp_min
  final String? mainTempMax; // list.main.temp_max
  final int? mainPressure; // list.main.pressure
  final int? mainHumidity; // list.main.humidity

  final int? weatherID; // list.weather.id
  final String? weatherMain; // list.weather.main
  final String? weatherDescription; // list.weather.description
  final String? weatherIcon; // list.weather.icon

  final double? windSpeed; // list.wind.speed
  final double? probabilityOfPrecipitation; // list.pop

  final String? forecastedData; // list.dt_txt

  factory ForecastInfo.fromJson(dynamic json) {
    if (json == null) {
      return ForecastInfo();
    }

    return ForecastInfo(
        mainTemp: json['main']['temp'],
        mainTempMin: json['main']['temp_min'],
        mainTempMax: json['main']['temp_max'],
        mainPressure: json['main']['pressure'],
        mainHumidity: json['main']['humidity'],
        weatherID: json['weather']['id'],
        weatherMain: json['weather']['main'],
        weatherDescription: json['weather']['description'],
        weatherIcon: json['weather']['icon'],
        windSpeed: json['wind']['speed'],
        probabilityOfPrecipitation: json['pop'],
        forecastedData: json['dt_txt']
    );
  }

  @override
  String toString() {
    return 'ForecastInfo{dt_txt: $forecastedData}';
  }
}