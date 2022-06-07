import 'package:flutter/material.dart';
import 'package:weather_forecast/models/weather_info.dart';
import 'package:weather_forecast/models/forecast_info.dart';

class TomorrowPage extends StatefulWidget {
  const TomorrowPage(
      {Key? key, required this.weatherInfo, required this.forecastInfo})
      : super(key: key);

  final WeatherInfo weatherInfo;
  final ForecastInfo forecastInfo;

  @override
  State<TomorrowPage> createState() => _TomorrowPageState();
}

class _TomorrowPageState extends State<TomorrowPage> {
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
              Row(children: [
                graphTitle('Temperature Tomorrow (ºC)')
              ]),
              const SizedBox(height: 5),
              Row(children: [
                temperatureGraph(widget.forecastInfo.list)
              ]),
              const SizedBox(height: 20),
              Row(children: [
                graphTitle('Precipitation Tomorrow (%)')
              ]),
              const SizedBox(height: 5),
              Row(children: [
                precipitationGraph(widget.forecastInfo.list)
              ]),
              const SizedBox(height: 20),
              Row(children: [graphTitle('Wind Tomorrow (km/h)')]),
              const SizedBox(height: 5),
              Row(children: [windGraph(widget.forecastInfo.list)]),
              const SizedBox(height: 20),
              Row(children: [
                graphTitle('Pressure Tomorrow (hPa)')
              ]),
              const SizedBox(height: 5),
              Row(children: [
                pressureGraph(widget.forecastInfo.list)
              ]),
              const SizedBox(height: 20),
              Row(children: [graphTitle('Humidity Tomorrow (%)')]),
              const SizedBox(height: 5),
              Row(children: [
                humidityGraph(widget.forecastInfo.list)
              ]),
            ],
          ),
        )
      ),
    )
  }
}