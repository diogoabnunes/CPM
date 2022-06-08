import 'package:flutter/material.dart';
import 'package:weather_forecast/models/weather_info.dart';
import 'package:weather_forecast/models/forecast_info.dart';
import 'package:weather_forecast/utils/functions.dart';
import 'package:weather_forecast/views/tomorrow_page.dart';
import 'package:weather_forecast/views/week_page.dart';

class CityPage extends StatelessWidget {
  const CityPage(
      {Key? key, required this.weatherInfo, required this.forecastInfo})
      : super(key: key);
  final WeatherInfo weatherInfo;
  final ForecastInfo forecastInfo;

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
                    Text(weatherInfo.cityName.toString(),
                        style:
                            const TextStyle(fontSize: 35, color: Colors.white)),
                    Text(weatherInfo.weatherDescription.toString(),
                        style:
                            const TextStyle(fontSize: 20, color: Colors.white)),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Image.network(
                          'https://openweathermap.org/img/wn/${weatherInfo.weatherIcon}@4x.png',
                          width: 150,
                        ),
                        Text('${weatherInfo.mainTemp?.toInt()}ºC',
                            textAlign: TextAlign.center,
                            style: const TextStyle(
                                color: Colors.white, fontSize: 70))
                      ],
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Text('Min.:${weatherInfo.mainTempMin?.toInt()}ºC',
                            textAlign: TextAlign.center,
                            style: const TextStyle(
                                color: Colors.white, fontSize: 20)),
                        Text(

                            '       Max.:${weatherInfo.mainTempMax?.toInt()}ºC',
                            textAlign: TextAlign.center,
                            style: const TextStyle(
                                color: Colors.white, fontSize: 20))
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
                              "${parseHours(forecastInfo.list?[index + 1].dtTxt)} h",
                              style: const TextStyle(
                                  color: Colors.white,
                                  fontWeight: FontWeight.bold),
                            ),
                            Image.network(
                              'https://openweathermap.org/img/wn/${forecastInfo.list?[index + 1].weatherIcon}@4x.png',
                              width: 60,
                            ),
                            Text(
                              "${forecastInfo.list?[index + 1].mainTemp?.toInt()}ºC",
                              style: const TextStyle(
                                  color: Colors.white,
                                  fontWeight: FontWeight.bold),
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
                            "${weatherInfo.mainFeelsLike?.toInt()}ºC")
                      ]),
                      const SizedBox(width: 32),
                      Row(children: [
                        data("Wind", ("${weatherInfo.windSpeed} km/h"))
                      ]),
                    ]),
                    Row(children: [
                      const SizedBox(width: 10),
                      Row(children: [
                        data("Humidity", "${weatherInfo.mainHumidity}%")
                      ]),
                      const SizedBox(width: 120),
                      Row(children: [
                        data("Pressure",
                            "${weatherInfo.mainPressure} hPa")
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
                                          weatherInfo: weatherInfo,
                                          forecastInfo: forecastInfo,
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
                                          weatherInfo: weatherInfo,
                                          forecastInfo: forecastInfo,
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
                    ]),
                    const Divider(
                      height: 1,
                      thickness: 1,
                      color: Colors.white70,
                    ),
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
