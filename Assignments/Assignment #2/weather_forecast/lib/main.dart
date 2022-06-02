import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:weather_forecast/services/requests.dart';
import 'package:weather_forecast/models/weather_info.dart';

void main() {
  runApp(const WeatherApp());
}

class WeatherApp extends StatelessWidget {
  const WeatherApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Home',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: const Home(title: 'Weather Forecast'),
    );
  }
}

class Home extends StatefulWidget {
  const Home({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {
  String message = "";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(widget.title)),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
                onPressed: () {
                  setState(() {
                    getCityWeather().then((result) => {
                      message = result
                    });
                    WeatherInfo wi = WeatherInfo.fromJson(jsonDecode(message.toString()));
                    print(wi);
                    });
                  } ,
                child: const Text('Add City')
            )
          ],
        )
      ),
    );
  }
}

