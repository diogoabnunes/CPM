class ListForecastInfo {
  List<ForecastInfo> list;

  ListForecastInfo({required this.list});

  factory ListForecastInfo.fromJson(dynamic json) {
    if(json['list'] == null ){
      return  ListForecastInfo(list:[]);
    }
    List<ForecastInfo> info=[];
    for(var i=0; i<json['cnt']; i++){
      info.add(ForecastInfo.fromJson(json['list'][i]));
    }
    return ListForecastInfo(list:info);
  }
}

class ForecastInfo {
  int? dt;
  Main? main;
  Weather? weather;
  String? wind;
  int? visibility;
  String? pop;
  String? dtTxt;

  ForecastInfo(
      {this.dt,
        this.main,
        this.weather,
        this.wind,
        this.visibility,
        this.pop,
        this.dtTxt});

  factory ForecastInfo.fromJson(dynamic json) {
    Main main= Main();
    if(json['main']!= null){
      main = Main.fromJson(json['main']);
    }

    Weather weather =Weather();
    if (json['weather'] != null) {
        weather=Weather.fromJson(json['weather'][0]);
    }
   String wind =json['wind']['speed'].toString();

    String pop = json['pop'].toString();
    String dtTxt = json['dt_txt'];
    int visibility = json['visibility'];
    int dt = json['dt'];

    return ForecastInfo(dt: dt ,main: main ,weather: weather, wind: wind, visibility: visibility, pop: pop, dtTxt: dtTxt );
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

  factory Main.fromJson(dynamic json) {
    double temp = json['temp'];
    double tempMin = json['temp_min'];
    double tempMax = json['temp_max'];
    int pressure = json['pressure'];
    int humidity = json['humidity'];
    return Main(temp: temp, tempMin: tempMin, tempMax: tempMax, pressure: pressure, humidity: humidity);
  }
}

class Weather {
  int? id;
  String? main;
  String? description;
  String? icon;

  Weather({this.id, this.main, this.description, this.icon});

  factory Weather.fromJson(dynamic json) {
    int id = json['id'];
    String main = json['main'];
    String description = json['description'];
    String icon = json['icon'];
    return Weather(id: id, main: main, description: description, icon: icon);
  }
}

/*class Wind {
  double? speed;

  Wind({this.speed});

  factory Wind.fromJson(dynamic json) {
    return Wind(speed: json['speed']);
  }
}*/