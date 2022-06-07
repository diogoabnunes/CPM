import 'package:flutter/material.dart';
import 'package:weather_forecast/models/weather_info.dart';
import 'package:weather_forecast/models/forecast_info.dart';
import 'package:intl/intl.dart';
import 'package:collection/collection.dart';
import 'package:dart_numerics/dart_numerics.dart';

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
  List<DailyInfo> weeklyInfo = [];

  @override
  Widget build(BuildContext context) {
    weeklyInfo = getWeekInfo(widget.forecastInfo.list!);
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
            const SizedBox(
              height: 20,
            ),
            Row(children: const [
              SizedBox(
                width: 70,
              ),
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
                        weeklyInfo[index].weekday,
                        'https://openweathermap.org/img/wn/${weeklyInfo[index].icon}@4x.png',
                        (weeklyInfo[index].maxTemp?.toInt()).toString(),
                        (weeklyInfo[index].minTemp?.toInt()).toString(),
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
            width: 70,
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
              maxTemp + "º  ",
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

  List<DailyInfo> getWeekInfo(List<Forecast> list) {
    // for (var i in list) {
    //   print(i);
    // }

    List<DailyInfo> result = <DailyInfo>[];
    List<DailyInfo> daily = <DailyInfo>[];
    String today = DateFormat('EEEE').format(DateTime.now());

    for (var i = 0; i < list.length; i++) {
      Forecast f = list[i];
      String weekday = DateFormat('EEEE').format(DateTime.parse('${f.dtTxt}'));
      if (weekday == today) continue;

      daily.add(DailyInfo(
          weekday: weekday,
          icon: f.weatherIcon,
          maxTemp: f.mainTempMax,
          minTemp: f.mainTempMin));
    }

    var groupby = groupBy(daily, (DailyInfo di) => di.weekday);

    for (var day in groupby.keys) {
      num maxTemp = int64MinValue;
      num minTemp = int64MaxValue;
      String? icon;

      var icons = {};

      for (DailyInfo di in groupby[day]!.toList()) {
        maxTemp = (di.maxTemp! > maxTemp) ? di.maxTemp! : maxTemp;
        minTemp = (di.minTemp! < minTemp) ? di.minTemp! : minTemp;
        if (!icons.containsKey(di.icon)) {
          icons[di.icon] = 1;
        }
        else {
          icons[di.icon] += 1;
        }
      }

      List sortedValues = icons.values.toList()..sort();
      int popularValue = sortedValues.last;
      icons.forEach((k, v) {
        if (v == popularValue) {
          icon = k;
        }
      });

      result.add(DailyInfo(
        weekday: day,
        icon: icon,
        maxTemp: maxTemp,
        minTemp: minTemp
      ));
    }

    return result;
  }
}

class DailyInfo {
  DailyInfo({this.weekday, this.icon, this.maxTemp, this.minTemp});

  final String? weekday;
  final String? icon;
  final num? maxTemp;
  final num? minTemp;
}
