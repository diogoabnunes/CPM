import 'package:flutter/material.dart';
import 'package:weather_forecast/models/weather_info.dart';
import 'package:syncfusion_flutter_charts/charts.dart';

class City extends StatefulWidget {
  const City(
      {Key? key, required this.weatherInfo/*, required this.listForecastInfo*/})
      : super(key: key);
  final WeatherInfo weatherInfo;
  //final ListForecastInfo listForecastInfo;

  @override
  State<City> createState() => _CityState();
}

class _CityState extends State<City> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text(widget.weatherInfo.cityName.toString())),
        resizeToAvoidBottomInset: false,
        body: SingleChildScrollView(
            child: Stack(children: [
          Container(
              padding: const EdgeInsets.only(top: 30),
              child: Column(children: [
                Text(widget.weatherInfo.cityName.toString(),
                    style:
                        const TextStyle(fontSize: 35, color: Colors.black54)),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  //Center Row contents horizontally,
                  children: [
                    Image.network(
                      'https://cdn1.iconfinder.com/data/icons/weather-forecast-meteorology-color-1/128/weather-partly-sunny-512.png',
                      width: 100,
                    ),
                    const Text("14º",
                        textAlign: TextAlign.center,
                        style: TextStyle(color: Colors.blue, fontSize: 70))
                  ],
                ),
                SizedBox(
                  height: 70,
                  child: ListView.builder(
                    itemCount: 24,
                    scrollDirection: Axis.horizontal,
                    itemBuilder: (context, index) => SizedBox(
                      height: 70,
                      width: 70,
                      child: Column(children: [
                        Text(
                          "$index h",
                          style: const TextStyle(color: Colors.black54),
                        ),
                        Image.network(
                          'https://cdn1.iconfinder.com/data/icons/weather-forecast-meteorology-color-1/128/weather-partly-sunny-512.png',
                          width: 30,
                        ),
                        const Text(
                          "22º",
                          style: TextStyle(color: Colors.black54),
                        ),
                      ]),
                    ),
                  ),
                ),
                Row(children: [
                  Row(children: [data("Precipitation", "10%")]),
                  const SizedBox(width: 50),
                  Row(children: [data("Wind", "10 km/h")]),
                ]),
                Row(children: [
                  Row(children: [data("Humidity", "54%")]),
                  const SizedBox(width: 50),
                  Row(children: [data("Pressure", "1017 hPa")]),
                ]),
                Row(children: [graphTitle('Temperature Tomorrow')]),
                Row(children: [graph()]),
                Row(children: [graphTitle('Precipitation Tomorrow')]),
                Row(children: [graph()]),
                Row(children: [graphTitle('Wind Tomorrow')]),
                Row(children: [graph()]),
              ]))
        ])));
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
                  fontSize: 18,
                  fontWeight: FontWeight.bold,
                  color: Colors.black45,
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
        style: const TextStyle(color: Colors.black54, fontSize: 20),
      ),
      Text(
        data,
        style: const TextStyle(color: Colors.black54, fontSize: 30),
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
