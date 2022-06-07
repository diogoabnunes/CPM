import 'package:flutter/material.dart';
import 'package:weather_forecast/models/weather_info.dart';
import 'package:weather_forecast/models/forecast_info.dart';
import 'package:intl/intl.dart';
import 'dart:math';

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
  List<DailyInfo> weeklyInfo=[];

  @override
  Widget build(BuildContext context) {
    weeklyInfo=getWeekInfo(widget.forecastInfo.list!);
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
                        weeklyInfo[index].weekday,
                        weeklyInfo[index].icon,
                        //'https://openweathermap.org/img/wn/${weeklyInfo[index].icon}@4x.png',
                      ( weeklyInfo[index].maxTemp?.toInt()).toString(),
                      ( weeklyInfo[index].minTemp?.toInt()).toString(),
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
          child:Text(
            icon,
            style: const TextStyle(color: Colors.white, fontSize: 25),
          ) /*Image.network(
            icon,
            width: 40,
          ),*/
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

  List<DailyInfo> getWeekInfo(List<Forecast> list){
    List<DailyInfo> week =[];

    List<String> weekdays=[];
    List<List<String?>> iconList=[];
    List<List<num?>> maxTempList=[];
    List<List<num?>> minTempList=[];

    List<String?> iconAux=[];
    List<num?> maxTempAux=[];
    List<num?> minTempAux=[];

    for(Forecast forecast in list){
      String weekday = DateFormat('EEEE').format(DateTime.parse('${forecast.dtTxt}'));
      if( !weekdays.contains(weekday)){
          if(iconAux.isNotEmpty){
            iconList.add(iconAux);
            iconAux.clear();
          }
          if(maxTempAux.isNotEmpty) {
            maxTempList.add(maxTempAux);
            maxTempAux.clear();
          }
          if(minTempAux.isNotEmpty) {
            minTempList.add(minTempAux);
            minTempAux.clear();
          }

          weekdays.add(weekday);
      }

      iconAux.add(forecast.weatherIcon);
      maxTempAux.add(forecast.mainTempMax);
      minTempAux.add(forecast.mainTempMin);

      if(forecast==list.last){
        minTempList.add(minTempAux);
        maxTempList.add(maxTempAux);
        iconList.add(iconAux);
      }
    }

      for(int i=0; i<weekdays.length;i++){
        // find icon
        String icon=findICon(iconList[i]);
        // find maxtemp
        num? max= (maxTempList[i]).reduce((value, element) => value! > element!? value: element);
        // find mintemp
        num? min= (minTempList[i]).reduce((value, element) => value! < element!? value: element);

        week.add(DailyInfo(weekday: weekdays[i],icon: icon,maxTemp: max,minTemp: min));

      }

      return week;
    }

  String findICon(List<String?> list) {
    var dict = Map();

    list.forEach((l) {
      if(!dict.containsKey(l)) {
        dict[l] = 1;
      } else {
        dict[l] +=1;
      }
    });

    List sortedValues = dict.values.toList()..sort();
    int popularValue = sortedValues.last;

    dict.forEach((k, v) {
      if (v == popularValue) {
        return k;
      }
      print(k);
    });
    return dict.keys.toString();
  }


}

class DailyInfo {

  DailyInfo({this.weekday,this.icon,this.maxTemp, this.minTemp});

  final String? weekday;
  final String? icon;
  final num? maxTemp;
  final num? minTemp;
}