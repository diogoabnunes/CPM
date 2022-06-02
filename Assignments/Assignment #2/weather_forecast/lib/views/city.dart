import 'package:flutter/material.dart';

class City extends StatefulWidget {
  const City({Key? key, required this.title}) : super(key: key);
  final String title;
  // WeatherInfo
  // ForecastInfo

  @override
  State<City> createState() => _CityState();
}

class _CityState extends State<City> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text(widget.title)), // WeatherInfo.city
        resizeToAvoidBottomInset: false,
        body: Stack(children: [
          Container(
              padding: const EdgeInsets.only(top: 30),
              child: Column(
                children: [
                  const Text("Porto",
                      style: TextStyle(
                          fontSize: 35,
                          color: Colors.black54)
                  ),
                  Center(child: Row(
                    children: [
                      Container(
                        decoration: const BoxDecoration(
                          image: DecorationImage(
                            image: NetworkImage(
                                'https://cdn1.iconfinder.com/data/icons/weather-forecast-meteorology-color-1/128/weather-partly-sunny-512.png'),
                            fit: BoxFit.cover,
                          ),
                        ),
                      ),
                      const Text("14º",
                          textAlign: TextAlign.center,
                          style: TextStyle(
                              color: Colors.blue,
                              fontSize: 60)
                      )
                    ],
                  )),
                ],
              ))]));
  }
}
