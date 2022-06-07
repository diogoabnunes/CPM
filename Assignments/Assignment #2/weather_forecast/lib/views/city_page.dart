import 'package:flutter/material.dart';
import 'package:weather_forecast/models/weather_info.dart';
import 'package:weather_forecast/models/forecast_info.dart';
import 'package:syncfusion_flutter_charts/charts.dart';

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
        body: SingleChildScrollView(
            child: Container(
                decoration: const BoxDecoration(
                  image: DecorationImage(
                      image: AssetImage('assets/images/degrade.jpg'),
                      fit: BoxFit.cover),
                ),
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
                          const Divider(
                            height: 1,
                            thickness: 1,
                            color: Colors.white,
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
                            color: Colors.white,
                          ),
                          const SizedBox(height: 10),
                          Row(children: [
                            Row(children: [
                              data("Thermal Sensation",
                                  "${widget.weatherInfo.mainFeelsLike?.toInt()}ºC")
                            ]),
                            const SizedBox(width: 50),
                            Row(children: [
                              data("Wind",
                                  ("${widget.weatherInfo.windSpeed} km/h"))
                            ]),
                          ]),
                          Row(children: [
                            Row(children: [
                              data("Humidity",
                                  "${widget.weatherInfo.mainHumidity}%")
                            ]),
                            const SizedBox(width: 50),
                            Row(children: [
                              data("Pressure",
                                  "${widget.weatherInfo.mainPressure} hPa")
                            ]),
                          ]),
                          const SizedBox(height: 10),
                          const Divider(
                            height: 1,
                            thickness: 1,
                            color: Colors.white,
                          ),
                          const SizedBox(height: 10),
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
                        ]))
                  ],
                ))));
  }

  Column data(title, data) {
    return Column(children: [
      Text(
        title,
        style: const TextStyle(
          color: Colors.white,
          fontSize: 20,
          fontWeight: FontWeight.bold,
        ),
      ),
      const SizedBox(width: 10),
      Text(
        data,
        style: const TextStyle(color: Colors.white, fontSize: 30),
      ),
    ]);
  }

  Container graphTitle(title) {
    return Container(
      padding: const EdgeInsets.only(top: 10),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: <Widget>[
          Text(
            title,
            style: Theme.of(context).textTheme.caption?.copyWith(
                  fontSize: 20,
                  color: Colors.white,
                  fontWeight: FontWeight.bold,
                ),
          ),
        ],
      ),
    );
  }

  SizedBox temperatureGraph(List<Forecast>? list) {
    return SizedBox(
      width: MediaQuery.of(context).size.width,
      height: 240,
      child: Card(
        elevation: 5,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(15),
        ),
        child: SfCartesianChart(
          primaryXAxis: CategoryAxis(),
          series: <ChartSeries<Forecast, String>>[
            SplineSeries<Forecast, String>(
              dataSource: list?.sublist(getFirstTomorrowIndex(list)!,
                      getFirstTomorrowIndex(list)! + 8) ??
                  [],
              xValueMapper: (Forecast f, _) => parseHours(f.dtTxt),
              yValueMapper: (Forecast f, _) => f.mainTemp,
            ),
          ],
        ),
      ),
    );
  }

  SizedBox precipitationGraph(List<Forecast>? list) {
    return SizedBox(
      width: MediaQuery.of(context).size.width,
      height: 240,
      child: Card(
        elevation: 5,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(15),
        ),
        child: SfCartesianChart(
          primaryXAxis: CategoryAxis(),
          series: <ChartSeries<Forecast, String>>[
            SplineSeries<Forecast, String>(
              dataSource: list?.sublist(getFirstTomorrowIndex(list)!,
                      getFirstTomorrowIndex(list)! + 8) ??
                  [],
              xValueMapper: (Forecast f, _) => parseHours(f.dtTxt),
              yValueMapper: (Forecast f, _) => 100 * f.pop!,
            ),
          ],
        ),
      ),
    );
  }

  SizedBox windGraph(List<Forecast>? list) {
    return SizedBox(
      width: MediaQuery.of(context).size.width,
      height: 240,
      child: Card(
        elevation: 5,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(15),
        ),
        child: SfCartesianChart(
          primaryXAxis: CategoryAxis(),
          series: <ChartSeries<Forecast, String>>[
            SplineSeries<Forecast, String>(
              dataSource: list?.sublist(getFirstTomorrowIndex(list)!,
                      getFirstTomorrowIndex(list)! + 8) ??
                  [],
              xValueMapper: (Forecast f, _) => parseHours(f.dtTxt),
              yValueMapper: (Forecast f, _) => f.windSpeed,
            ),
          ],
        ),
      ),
    );
  }

  SizedBox pressureGraph(List<Forecast>? list) {
    return SizedBox(
      width: MediaQuery.of(context).size.width,
      height: 240,
      child: Card(
        elevation: 5,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(15),
        ),
        child: SfCartesianChart(
          primaryXAxis: CategoryAxis(),
          series: <ChartSeries<Forecast, String>>[
            SplineSeries<Forecast, String>(
              dataSource: list?.sublist(getFirstTomorrowIndex(list)!,
                      getFirstTomorrowIndex(list)! + 8) ??
                  [],
              xValueMapper: (Forecast f, _) => parseHours(f.dtTxt),
              yValueMapper: (Forecast f, _) => f.mainPressure,
            ),
          ],
        ),
      ),
    );
  }

  SizedBox humidityGraph(List<Forecast>? list) {
    return SizedBox(
      width: MediaQuery.of(context).size.width,
      height: 240,
      child: Card(
        elevation: 5,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(15),
        ),
        child: SfCartesianChart(
          primaryXAxis: CategoryAxis(),
          series: <ChartSeries<Forecast, String>>[
            SplineSeries<Forecast, String>(
              dataSource: list?.sublist(getFirstTomorrowIndex(list)!,
                      getFirstTomorrowIndex(list)! + 8) ??
                  [],
              xValueMapper: (Forecast f, _) => parseHours(f.dtTxt),
              yValueMapper: (Forecast f, _) => f.mainHumidity,
            ),
          ],
        ),
      ),
    );
  }
}
