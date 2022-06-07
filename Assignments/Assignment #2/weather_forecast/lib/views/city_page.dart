import 'package:flutter/material.dart';
import 'package:weather_forecast/models/weather_info.dart';
import 'package:weather_forecast/models/forecast_info.dart';
import 'package:weather_forecast/utils/functions.dart';
import 'package:weather_forecast/views/tomorrow_page.dart';
import 'package:weather_forecast/views/week_page.dart';

class CityPage extends StatefulWidget {
  const CityPage(
      {Key? key, required this.weatherInfo, required this.forecastInfo})
      : super(key: key);
  final WeatherInfo weatherInfo;
  final ForecastInfo forecastInfo;

  @override
  State<CityPage> createState() => _CityPageState();
}

class _CityPageState extends State<CityPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: const Text("Is IT raining?")),
        resizeToAvoidBottomInset: false,
        body: Stack(children: [
          getBackground(),
          SingleChildScrollView(
              child: Stack(
          children: [
          const SizedBox(height: 50),
          Container(
              padding: const EdgeInsets.only(top: 30),
              child: Column(children: [
                Text(widget.weatherInfo.cityName.toString(),
                    style: const TextStyle(
                        fontSize: 35, color: Colors.white)),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    const SizedBox(height: 50),
                    Container(
                        padding: const EdgeInsets.only(top: 30),
                        child: Column(children: [
                          Text(widget.weatherInfo.cityName.toString(),
                              style: const TextStyle(
                                  fontSize: 35, color: Colors.white)),
                          Text(widget.weatherInfo.weatherDescription.toString(),
                              style: const TextStyle(
                                  fontSize: 20, color: Colors.white)),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Image.network(
                                'https://openweathermap.org/img/wn/${widget.weatherInfo.weatherIcon}@4x.png',
                                width: 150,
                              ),
                              Text('${widget.weatherInfo.mainTemp?.toInt()}ºC',
                                  textAlign: TextAlign.center,
                                  style: const TextStyle(
                                      color: Colors.lightBlueAccent,
                                      fontSize: 70))
                            ],
                          ),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Text('Max.:${widget.weatherInfo.mainTempMax?.toInt()}º',
                                  textAlign: TextAlign.center,
                                  style: const TextStyle(
                                      color: Colors.white,
                                      fontSize: 20)),
                              Text(' Min.:${widget.weatherInfo.mainTempMin?.toInt()}º',
                                  textAlign: TextAlign.center,
                                  style: const TextStyle(
                                      color: Colors.white,
                                      fontSize: 20))
                            ],
                          ),
                          const SizedBox(height: 20),
                          const Divider(
                            height: 1,
                            thickness: 1,
                            color: Colors.white70,
                          ),
                          const SizedBox(height: 20),
                          SizedBox(
                            height: 100,
                            child: ListView.builder(
                              itemCount: 8,
                              scrollDirection: Axis.horizontal,
                              itemBuilder: (context, index) => SizedBox(
                                height: 90,
                                width: 90,
                                child: Column(children: [
                                  Text(
                                    "${parseHours(widget.forecastInfo.list?[index].dtTxt)} h",
                                    style: const TextStyle(color: Colors.white),
                                  ),
                                  Image.network(
                                    'https://openweathermap.org/img/wn/${widget.forecastInfo.list?[index].weatherIcon}@4x.png',
                                    width: 60,
                                  ),
                                  Text(
                                    "${widget.forecastInfo.list?[index].mainTemp?.toInt()}ºC",
                                    style: const TextStyle(color: Colors.white),
                                  ),
                                ]),
                              ),
                            ),
                          ),
                          const SizedBox(height: 10),
                          const Divider(
                            height: 1,
                            thickness: 1,
                            color: Colors.white70,
                          ),
                          const SizedBox(height: 10),
                          Row(children: [
                            const SizedBox(width: 10),
                            Row(children: [
                              data("Thermal Sensation",
                                  "${widget.weatherInfo.mainFeelsLike?.toInt()}ºC")
                            ]),
                            const SizedBox(width: 32),
                            Row(children: [
                              data("Wind",
                                  ("${widget.weatherInfo.windSpeed} km/h"))
                            ]),
                          ]),
                          Row(children: [
                            const SizedBox(width: 10),
                            Row(children: [
                              data("Humidity",
                                  "${widget.weatherInfo.mainHumidity}%")
                            ]),
                            const SizedBox(width: 120),
                            Row(children: [
                              data("Pressure",
                                  "${widget.weatherInfo.mainPressure} hPa")
                            ]),
                          ]),
                          const SizedBox(height: 10),
                          const Divider(
                            height: 1,
                            thickness: 1,
                            color: Colors.white70,
                          ),
                          const SizedBox(height: 10),
                          Row(children: [
                            TextButton(
                                onPressed: () => Navigator.push(
                                      context,
                                      MaterialPageRoute(
                                          builder: (context) => TomorrowPage(
                                                weatherInfo: widget.weatherInfo,
                                                forecastInfo:
                                                    widget.forecastInfo,
                                              )),
                                    ),
                                child: const SizedBox(
                                    width: 350,
                                    child: Text('Tomorrow Forecast',
                                        style: TextStyle(
                                          color: Colors.white,
                                          fontSize: 20,
                                          fontWeight: FontWeight.bold,
                                        )))),
                          ]),
                          const SizedBox(height: 10),
                          const Divider(
                            height: 1,
                            thickness: 1,
                            color: Colors.white70,
                          ),
                          const SizedBox(height: 10),
                          Row(children: [
                            TextButton(
                                onPressed: () => Navigator.push(
                                      context,
                                      MaterialPageRoute(
                                          builder: (context) => WeekPage(
                                                weatherInfo: widget.weatherInfo,
                                                forecastInfo:
                                                    widget.forecastInfo,
                                              )),
                                    ),
                                child: const SizedBox(
                                    width: 350,
                                    child: Text('Weekly Forecast',
                                        style: TextStyle(
                                          color: Colors.white,
                                          fontSize: 20,
                                          fontWeight: FontWeight.bold,
                                        )))),
                          ])
                        ]))
                  ],
                ),
                const Divider(
                  height: 1,
                  thickness: 1,
                  color: Colors.white70,
                ),
                const SizedBox(height: 20),
                SizedBox(
                  height: 100,
                  child: ListView.builder(
                    itemCount: 8,
                    scrollDirection: Axis.horizontal,
                    itemBuilder: (context, index) => SizedBox(
                      height: 90,
                      width: 90,
                      child: Column(children: [
                        Text(
                          "${parseHours(widget.forecastInfo.list?[index].dtTxt)} h",
                          style: const TextStyle(color: Colors.white),
                        ),
                        Image.network(
                          'https://openweathermap.org/img/wn/${widget.forecastInfo.list?[index].weatherIcon}@4x.png',
                          width: 60,
                        ),
                        Text(
                          "${widget.forecastInfo.list?[index].mainTemp?.toInt()}ºC",
                          style: const TextStyle(color: Colors.white),
                        ),
                      ]),
                    ),
                  ),
                ),
                const SizedBox(height: 10),
                const Divider(
                  height: 1,
                  thickness: 1,
                  color: Colors.white70,
                ),
                const SizedBox(height: 10),
                Row(children: [
                  const SizedBox(width: 10),
                  Row(children: [
                    data("Thermal Sensation",
                        "${widget.weatherInfo.mainFeelsLike?.toInt()}ºC")
                  ]),
                  const SizedBox(width: 32),
                  Row(children: [
                    data("Wind",
                        ("${widget.weatherInfo.windSpeed} km/h"))
                  ]),
                ]),
                Row(children: [
                  const SizedBox(width: 10),
                  Row(children: [
                    data("Humidity",
                        "${widget.weatherInfo.mainHumidity}%")
                  ]),
                  const SizedBox(width: 120),
                  Row(children: [
                    data("Pressure",
                        "${widget.weatherInfo.mainPressure} hPa")
                  ]),
                ]),
                const SizedBox(height: 10),
                const Divider(
                  height: 1,
                  thickness: 1,
                  color: Colors.white70,
                ),
                const SizedBox(height: 10),
                Row(children: [
                  TextButton(
                      onPressed: () => Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => TomorrowPage(
                              weatherInfo: widget.weatherInfo,
                              forecastInfo:
                              widget.forecastInfo,
                            )),
                      ),
                      child: const SizedBox(
                          width: 350,
                          child: Text('Tomorrow Forecast',
                              style: TextStyle(
                                color: Colors.white,
                                fontSize: 20,
                                fontWeight: FontWeight.bold,
                              )))),
                ]),
                const SizedBox(height: 10),
                const Divider(
                  height: 1,
                  thickness: 1,
                  color: Colors.white70,
                ),
                const SizedBox(height: 10),
                Row(children: [
                  TextButton(
                      onPressed: () => Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => WeekPage(
                              weatherInfo: widget.weatherInfo,
                              forecastInfo:
                              widget.forecastInfo,
                            )),
                      ),
                      child: const SizedBox(
                          width: 350,
                          child: Text('Weekly Forecast',
                              style: TextStyle(
                                color: Colors.white,
                                fontSize: 20,
                                fontWeight: FontWeight.bold,
                              )))),
                ])
              ]))
        ],
        ))
        ]));
  }

  Column data(title, data) {
    return Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Align(
            alignment: Alignment.centerLeft,
            child: Text(
              title,
              style: const TextStyle(
                color: Colors.white,
                fontSize: 20,
                fontWeight: FontWeight.bold,
              ),
            ),
          ),
          Align(
            alignment: Alignment.centerLeft,
            child: Text(
              data,
              style: const TextStyle(color: Colors.white, fontSize: 30),
            ),
          ),
          const SizedBox(width: 10),
        ]);
  }
}
