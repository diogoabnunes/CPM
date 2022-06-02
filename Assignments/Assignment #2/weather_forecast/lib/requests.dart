import 'package:http/http.dart' as http;

Future<String> getCityWeather() async {
  final response = await http.get(Uri.http('api.openweathermap.org', '/data/2.5/weather',
      { 'appid': 'a178a302491f20b4079a8e30ef112a78',
        'units': 'metric',
        'q': 'Porto,Portugal',
        'lang': 'PT'
      }
  ));
  if (response.statusCode == 200) {
    return response.body;
  } else {
    throw Exception('HTTP failed');
  }
}

Future<String> getFiveDaysWeather() async {
  final response = await http.get(Uri.http('api.openweathermap.org', '/data/2.5/forecast',
      { 'appid': 'a178a302491f20b4079a8e30ef112a78',
        'units': 'metric',
        'q': 'Porto,Portugal',
        'lang': 'PT',
        'cnt': '24'
        }
  ));
  if (response.statusCode == 200) {
    return response.body;
  } else {
    throw Exception('HTTP failed');
  }
}

// getFiveDaysWeather()