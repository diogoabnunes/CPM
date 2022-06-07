class ForecastInfo {
  ForecastInfo({this.list});

  final List<Forecast>? list;

  factory ForecastInfo.fromJson(Map<String, dynamic> json) {
    if (json['list'] == null) {
      return ForecastInfo(list: []);
    }

    List<Forecast> l = <Forecast>[];
    for (var i = 0; i < json['cnt']; i++) {
      l.add(Forecast.fromJson(json['list'][i]));
    }
    return ForecastInfo(list: l);
  }
}

class Forecast {
  Forecast(
      {this.mainTemp,
      this.mainFeelsLike,
      this.mainTempMin,
      this.mainTempMax,
      this.mainPressure,
      this.mainHumidity,
      this.weatherId,
      this.weatherMain,
      this.weatherDescription,
      this.weatherIcon,
      this.windSpeed,
      this.visibility,
      this.pop,
      this.dtTxt});

  final num? mainTemp;
  final num? mainFeelsLike;
  final num? mainTempMin;
  final num? mainTempMax;
  final num? mainPressure;
  final num? mainHumidity;

  final num? weatherId;
  final String? weatherMain;
  final String? weatherDescription;
  final String? weatherIcon;

  final num? windSpeed;
  final num? visibility;
  final num? pop;
  final String? dtTxt;

  factory Forecast.fromJson(dynamic json) {
    if (json == null) {
      return Forecast();
    }

    return Forecast(
        mainTemp: json['main']['temp'],
        mainFeelsLike: json['main']['feels_like'],
        mainTempMin: json['main']['temp_min'],
        mainTempMax: json['main']['temp_max'],
        mainPressure: json['main']['pressure'],
        mainHumidity: json['main']['humidity'],
        weatherId: json['weather'][0]['id'],
        weatherMain: json['weather'][0]['main'],
        weatherDescription: json['weather'][0]['description'],
        weatherIcon: json['weather'][0]['icon'],
        windSpeed: json['wind']['speed'],
        visibility: json['visibility'],
        pop: json['pop'],
        dtTxt: json['dt_txt']);
  }
}

String? parseHours(String? datetime) {
  return datetime?.split(' ')[1].split(':')[0];
}

String? parseDay(String? datetime) {
  return datetime?.split(' ')[0].split('-')[2];
}

String? parseMonth(String? datetime) {
  return datetime?.split(' ')[0].split('-')[1];
}

int? getFirstTomorrowIndex(List<Forecast>? list) {
  for (var item in list!) {
    if (parseHours(item.dtTxt) == "00") {
      return list.indexOf(item);
    }
  }
  return null;
}
