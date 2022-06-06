import 'package:flutter/material.dart';
import 'package:weather_forecast/models/weather_info.dart';
import 'package:weather_forecast/models/forecast_info.dart';
import 'package:syncfusion_flutter_charts/charts.dart';

class City extends StatefulWidget {
  const City(
      {Key? key, required this.weatherInfo , required this.listForecastInfo
      })
      : super(key: key);
  final WeatherInfo weatherInfo;

  final ListForecastInfo listForecastInfo;

  @override
  State<City> createState() => _CityState();
}

class _CityState extends State<City> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("Is IT raining?")),
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
                    SizedBox(
                      height: 50,
                    ),
                    Container(
                        padding: const EdgeInsets.only(top: 30),
                        child: Column(children: [
                          Text(widget.weatherInfo.cityName.toString(),
                              style: const TextStyle(
                                  fontSize: 35, color: Colors.white)),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            //Center Row contents horizontally,
                            children: [
                              Image.network(
                                'https://openweathermap.org/img/wn/${widget.weatherInfo.weatherIcon}@4x.png',
                                width: 150,
                              ),
                              Text(
                                  (widget.weatherInfo.mainTemp?.toInt())
                                          .toString() +
                                      'ºC',
                                  textAlign: TextAlign.center,
                                  style: TextStyle(
                                      color: Colors.lightBlueAccent, fontSize: 70))
                            ],
                          ),
                          Divider(

                          ),
                          SizedBox(
                            height: 20,
                          ),
                          SizedBox(
                            height: 100,
                            child: ListView.builder(
                              itemCount: 5,
                              scrollDirection: Axis.horizontal,
                              itemBuilder: (context, index) => SizedBox(
                                height: 90,
                                width: 90,
                                child: Column(children: [
                                  Text(
                                    "$index h",
                                    style:
                                        const TextStyle(color: Colors.white),
                                  ),
                                  Image.network(
                                    'https://openweathermap.org/img/wn/${widget.weatherInfo.weatherIcon}@4x.png',
                                    width: 60,
                                  ),
                                  const Text(
                                    "22º",
                                    style: TextStyle(color: Colors.white),
                                  ),
                                ]),
                              ),
                            ),
                          ),
                          SizedBox(
                            height: 20,
                          ),
                          Row(children: [
                            Row(children: [data("Precipitation", "10%")]),
                            const SizedBox(width: 50),
                            Row(children: [
                              data(
                                  "Wind",
                                  (widget.weatherInfo.wind.toString() +
                                      " km/h"))
                            ]),
                          ]),
                          Row(children: [
                            Row(children: [
                              data(
                                  "Humidity",
                                  widget.weatherInfo.mainHumidity.toString() +
                                      "%")
                            ]),
                            const SizedBox(width: 50),
                            Row(children: [
                              data(
                                  "Pressure",
                                  widget.weatherInfo.mainPressure.toString() +
                                      " hPa")
                            ]),
                          ]),
                          SizedBox(
                            height: 20,
                          ),
                          Row(children: [graphTitle('Temperature Tomorrow')]),
                          SizedBox(
                            height: 5,
                          ),
                          Row(children: [graph()]),
                          SizedBox(
                            height: 20,
                          ),
                          Row(children: [graphTitle('Precipitation Tomorrow')]),
                          SizedBox(
                            height: 5,
                          ),
                          Row(children: [graph()]),
                          SizedBox(
                            height: 20,
                          ),
                          Row(children: [graphTitle('Wind Tomorrow')]),
                          SizedBox(
                            height: 5, // <-- SEE HERE
                          ),
                          Row(children: [graph()]),
                        ]))
                  ],
                ))));
  }

  Container graphTitle(title) {
    return Container(
      padding: const EdgeInsets.only(top: 10),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: <Widget>[
          Text(
            title.toUpperCase(),
            style: Theme.of(context).textTheme.caption?.copyWith(
                  fontSize: 20,
                  color: Colors.black45,
                  fontWeight: FontWeight.bold,
                ),
          ),
        ],
      ),
    );
  }

  SizedBox graph() {
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
          series: <ChartSeries<ExpenseData, String>>[
            SplineSeries<ExpenseData, String>(
              dataSource: getChartData(),
              xValueMapper: (ExpenseData exp, _) => exp.expenseCategory,
              yValueMapper: (ExpenseData exp, _) => exp.son,
            ),
          ],
        ),
      ),
    );
  }

  Column data(title, data) {
    return Column(children: [
      Text(
        title,
        style: const TextStyle(color: Colors.white, fontSize: 20,
          fontWeight: FontWeight.bold,),
      ),
      const SizedBox(width: 10),
      Text(
        data,
        style: const TextStyle(color: Colors.white, fontSize: 30),
      ),
    ]);
  }

  ///////////////////////////////////////////////////////////////////////////////////////////
  /////////////////// MUDAR AS FUNÇÕES ABAIXO ///////////////////////////////////////////////
  ///////////////////////////////////////////////////////////////////////////////////////////

  //DATA para os gráficos
  List<ExpenseData> getChartData() {
    final List<ExpenseData> chartData = [
      ExpenseData('Food', 55, 40, 45, 48),
      ExpenseData('Transport', 33, 45, 54, 28),
      ExpenseData('Medical', 43, 23, 20, 34),
      ExpenseData('Clothes', 32, 54, 23, 54),
      ExpenseData('Books', 56, 18, 43, 55),
      ExpenseData('Others', 23, 54, 33, 56),
    ];
    return chartData;
  }
}

//Classe aleatória do exemplo dos gráficos
class ExpenseData {
  ExpenseData(
      this.expenseCategory, this.father, this.mother, this.son, this.daughter);

  final String expenseCategory;
  final num father;
  final num mother;
  final num son;
  final num daughter;
}
