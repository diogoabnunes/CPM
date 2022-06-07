import 'package:flutter/material.dart';
import 'package:weather_forecast/models/weather_info.dart';
import 'package:weather_forecast/models/forecast_info.dart';

class WeekPage extends StatefulWidget {
  const WeekPage(
      {Key? key, required this.weatherInfo, required this.forecastInfo})
      : super(key: key);

  final WeatherInfo weatherInfo;
  final ForecastInfo forecastInfo;

  @override
  State<WeekPage> createState() => _WeekPageState();
}

class _WeekPageState extends State<WeekPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Is IT raining?")),
      resizeToAvoidBottomInset: false,
      body: SingleChildScrollView(
          child: Container(
            decoration: const BoxDecoration(
              image: DecorationImage(
                  image: AssetImage('assets/images/degrade.jpg'),
                  fit: BoxFit.cover),
            ),
            child: Column(
              children: [
                Row(children: const [
                  Text('Temperature Tomorrow (ºC)')
                ]),
                const SizedBox(height: 5),
              ],
            ),
          )
      ),
    );
  }
}