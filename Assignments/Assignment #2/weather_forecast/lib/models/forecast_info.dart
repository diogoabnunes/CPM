class ListForecastInfo {
  List<ForecastInfo>? list;

  ListForecastInfo({this.list});

  ListForecastInfo.fromJson(Map<String, dynamic> json) {
    if (json['list'] != null) {
      list = <ForecastInfo>[];
      json['list'].forEach((v) {
        list!.add(ForecastInfo.fromJson(v));
      });
    }
  }
}

class ForecastInfo {
  int? dt;
  Main? main;
  List<Weather>? weather;
  Wind? wind;
  int? visibility;
  double? pop;
  String? dtTxt;

  ForecastInfo(
      {this.dt,
        this.main,
        this.weather,
        this.wind,
        this.visibility,
        this.pop,
        this.dtTxt});

  ForecastInfo.fromJson(Map<String, dynamic> json) {
    main = json['main'] != null ? Main.fromJson(json['main']) : null;
    if (json['weather'] != null) {
      weather = <Weather>[];
      json['weather'].forEach((v) {
        weather!.add(Weather.fromJson(v));
      });
    }
    wind = json['wind'] != null ? Wind.fromJson(json['wind']) : null;
    pop = json['pop'];
    dtTxt = json['dt_txt'];
  }
}

class Main {
  double? temp;
  double? tempMin;
  double? tempMax;
  int? pressure;
  int? humidity;

  Main(
      {this.temp,
        this.tempMin,
        this.tempMax,
        this.pressure,
        this.humidity});

  Main.fromJson(Map<String, dynamic> json) {
    temp = json['temp'];
    tempMin = json['temp_min'];
    tempMax = json['temp_max'];
    pressure = json['pressure'];
    humidity = json['humidity'];
  }
}

class Weather {
  int? id;
  String? main;
  String? description;
  String? icon;

  Weather({this.id, this.main, this.description, this.icon});

  Weather.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    main = json['main'];
    description = json['description'];
    icon = json['icon'];
  }
}

class Wind {
  double? speed;

  Wind({this.speed});

  Wind.fromJson(Map<String, dynamic> json) {
    speed = json['speed'];
  }
}