import 'package:http/http.dart' as http;
import 'package:weather_forecast/utils/constants.dart';

Future<String> getCityWeather(String city) async {
  final response = await http.get(Uri.http(openWeatherMapAPI, weatherPath,
      { 'appid': appID,
        'units': unitsMETRIC,
        'q': city
      }
  ));
  if (response.statusCode == 200) {
    return response.body;
  } else {
    throw Exception('HTTP failed');
  }
}

Future<String> getFiveDaysWeather(String city) async {
  final response = await http.get(Uri.http(openWeatherMapAPI, forecastPath,
      { 'appid': appID,
        'units': unitsMETRIC,
        'q': city
        }
  ));
  if (response.statusCode == 200) {
    return response.body;
  } else {
    throw Exception('HTTP failed');
  }
}