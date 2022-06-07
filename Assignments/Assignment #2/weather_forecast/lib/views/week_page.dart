import 'package:flutter/material.dart';
import 'package:weather_forecast/models/weather_info.dart';
import 'package:weather_forecast/models/forecast_info.dart';
import 'package:intl/intl.dart';

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
            const SizedBox(height: 20,),
            Row(children: const [
              const SizedBox(width: 70,),
              Text(
                  'This Week Forecast',
                  style: TextStyle(
                    fontSize: 30,
                    color: Colors.white,
                    fontWeight: FontWeight.bold,
                  ),
                ),
            ]),
            const SizedBox(height: 40),
            SizedBox(
              height: 600,
              child: ListView.builder(
                itemCount: 5,
                scrollDirection: Axis.vertical,
                itemBuilder: (context, index) => SizedBox(
                  child: Column(children: [
                    Row(children: [
                      row(
                        DateFormat('EEEE').format(DateTime.parse('${widget.forecastInfo.list?[index].dtTxt}')),
                        'https://openweathermap.org/img/wn/${widget.forecastInfo.list?[index].weatherIcon}@4x.png',
                      (widget.forecastInfo.list?[index].mainTempMax?.toInt()).toString(),
                      (widget.forecastInfo.list?[index].mainTempMin?.toInt()).toString(),
                      )
                    ]),
                  ]),
                ),
              ),
            ),
          ],
        ),
      )),
    );
  }

  Row row(day, icon, maxTemp, minTemp) {
    return Row(children: <Widget>[
      const SizedBox(width: 10),
      Align(
        alignment: Alignment.centerLeft,
        child: SizedBox(
          width: 150,
          height: 50,
          child: Text(day,
              style: const TextStyle(
                color: Colors.white,
                fontSize: 25,
              )),
        ),
      ),
      Align(
        alignment: Alignment.centerLeft,
        child: SizedBox(
          width: 80,
          height: 50,
          child: Image.network(
            icon,
            width: 40,
          ),
        ),
      ),
      const SizedBox(width: 60),
      Row(children: <Widget>[
        Align(
          alignment: Alignment.centerLeft,
          child: SizedBox(
            width: 50,
            height: 50,
            child: Text(
              maxTemp + "º  " ,
              style: const TextStyle(color: Colors.white, fontSize: 25),
            ),
          ),
        ),
        Align(
          alignment: Alignment.centerLeft,
          child: SizedBox(
            width: 50,
            height: 50,
            child: Text(
              minTemp + "º",
              style: const TextStyle(color: Colors.black45, fontSize: 25),
            ),
          ),
        ),
        const SizedBox(height: 10),
      ]),
    ]);
  }
}
