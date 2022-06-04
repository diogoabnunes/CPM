import 'package:http/http.dart' as http;
import 'package:weather_forecast/models/constants.dart';

Future<String> getCityWeather(String city) async {
  final response = await http.get(Uri.http('api.openweathermap.org', '/data/2.5/weather',
      { 'appid': APP_ID,
        'units': UNITS_METRIC,
        'q': city,
        'lang': LANGUAGE_PT
      }
  ));
  if (response.statusCode == 200) {
    return response.body;
  } else {
    throw Exception('HTTP failed');
  }
}

Future<String> getFiveDaysWeather(String city) async {
  final response = await http.get(Uri.http('api.openweathermap.org', '/data/2.5/forecast',
      { 'appid': APP_ID,
        'units': UNITS_METRIC,
        'q': city,
        'lang': LANGUAGE_PT
        }
  ));
  if (response.statusCode == 200) {
    return response.body;
  } else {
    throw Exception('HTTP failed');
  }
}