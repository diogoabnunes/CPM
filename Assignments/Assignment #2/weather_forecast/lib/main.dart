import 'package:flutter/material.dart';
import 'package:weather_forecast/views/cities.dart';

import 'file_manager.dart';

void main() {
  runApp(const WeatherApp());
}

class WeatherApp extends StatelessWidget {
  const WeatherApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'is IT raining?',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: Cities(title: 'is IT raining?', storage: FileManager()),
    );
  }
}
