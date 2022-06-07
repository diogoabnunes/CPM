import 'package:flutter/material.dart';
import 'package:weather_forecast/views/cities_page.dart';
import 'package:weather_forecast/utils/file_manager.dart';
import 'package:weather_forecast/utils/constants.dart';

void main() {
  runApp(const WeatherApp());
}

class WeatherApp extends StatelessWidget {
  const WeatherApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Is IT raining?',
      theme: ThemeData(primarySwatch: MaterialColor(degradeBlue, color)),
      home: CitiesPage(title: 'Is IT raining?', storage: FileManager()),
    );
  }
}
