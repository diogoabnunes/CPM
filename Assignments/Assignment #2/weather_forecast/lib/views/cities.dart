import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:weather_forecast/views/city.dart';
import 'package:weather_forecast/models/weather_info.dart';
import 'package:weather_forecast/models/list_forecast_info.dart';
import 'package:weather_forecast/models/forecast_info.dart';
import 'package:weather_forecast/services/requests.dart';
import 'package:weather_forecast/file_manager.dart';

class Cities extends StatefulWidget {
  const Cities({Key? key, required this.title, required this.storage})
      : super(key: key);
  final String title;
  final FileManager storage;

  @override
  State<Cities> createState() => _CitiesState();
}

class _CitiesState extends State<Cities> {
  List<String> cities = [];
  List<WeatherInfo> citiesWeather = <WeatherInfo>[];
  final TextEditingController _controller = TextEditingController();

  //List<ListForecastInfo> citiesForecast = <ListForecastInfo>[];
  late Future<String> _value;

  Future<String> loadInfo() async {
    await widget.storage.read().then((value) => {
        cities = value
    });
    for (var city in cities) {
      try {
        WeatherInfo wi;
        await getCityWeather(city).then((result) => {
              wi = WeatherInfo.fromJson(jsonDecode(result)),
              citiesWeather.add(wi)
            });
        //ListForecastInfo lfi;
        // await getFiveDaysWeather(city).then((result) =>
        // {
        //   lfi = ForecastInfo.fromJson(jsonDecode(result)) as ListForecastInfo,
        //   citiesForecast.add(lfi)
        // });
        cities.add(city);
        widget.storage.write(cities);
      } catch (e) {
        return 'exception';
      }
    }
    return 'hello';
  }

  Future<String> requestCity(String str) async {
    try {
      WeatherInfo wi;
      await getCityWeather(str).then((result) => {
            wi = WeatherInfo.fromJson(jsonDecode(result)),
            citiesWeather.add(wi),
          });
      // ListForecastInfo lfi;
      // await getFiveDaysWeather(str).then((result) =>
      // {
      //   lfi = ForecastInfo.fromJson(jsonDecode(result)) as ListForecastInfo,
      //   citiesForecast.add(lfi)
      // });
      cities.add(str);
      widget.storage.write(cities);
      return 'new city';
    } catch (e) {
      return 'exception';
    }
  }

  @override
  initState() {
    super.initState();
    _value = loadInfo();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(widget.title)),
      resizeToAvoidBottomInset: false,
      body: Container(
        constraints: const BoxConstraints.expand(),
        decoration: const BoxDecoration(
          image: DecorationImage(
              image: AssetImage('assets/images/degrade.jpg'),
              fit: BoxFit.cover),
        ),
        child: Stack(
          children: <Widget>[
            Row(children: [getSearch()]),
            FutureBuilder<String>(
              future: _value,
              builder: (
                BuildContext context,
                AsyncSnapshot<String> snapshot,
              ) {
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return const CircularProgressIndicator();
                } else if (snapshot.connectionState == ConnectionState.done) {
                  if (snapshot.hasError) {
                    return const Text('Error');
                  } else if (snapshot.hasData) {
                    return Row(
                      children: [getListView()],
                    );
                  } else {
                    return const Text('Empty data');
                  }
                } else {
                  return Text('State: ${snapshot.connectionState}');
                }
              },
            )
          ],
        ),
      ),
    );
  }

  Expanded getSearch() {
    return Expanded(
      flex: 1,
      child: Container(
        padding: const EdgeInsets.only(top: 20, left: 20, right: 20),
        child: TextField(
          controller: _controller,
          style: const TextStyle(
            color: Colors.white,
          ),
          textInputAction: TextInputAction.search,
          onSubmitted: (String str) {
            setState(() {
              _value = requestCity(str);
              _controller.clear();
            });
          },
          decoration: InputDecoration(
            suffixIcon: const Icon(
              Icons.search,
              color: Colors.white,
            ),
            hintStyle: const TextStyle(color: Colors.white),
            hintText: 'Add city!',
            border: OutlineInputBorder(
              borderRadius: BorderRadius.circular(10),
              borderSide: const BorderSide(color: Colors.white),
            ),
            focusedBorder: OutlineInputBorder(
              borderRadius: BorderRadius.circular(10),
              borderSide: const BorderSide(color: Colors.white),
            ),
            enabledBorder: OutlineInputBorder(
              borderRadius: BorderRadius.circular(10),
              borderSide: const BorderSide(color: Colors.white),
            ),
          ),
        ),
      ),
    );
  }

  Expanded getListView() {
    return Expanded(
        flex: 2,
        child: Stack(children: <Widget>[
          Padding(
              padding: const EdgeInsets.symmetric(horizontal: 15),
              child: Container(
                  padding: const EdgeInsets.only(top: 100),
                  child: Align(
                      alignment: Alignment.topLeft,
                      child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: <Widget>[
                            SizedBox(
                              height: 450,
                              child: ListView.separated(
                                physics: const BouncingScrollPhysics(),
                                scrollDirection: Axis.vertical,
                                separatorBuilder: (context, index) =>
                                    const VerticalDivider(
                                  color: Colors.transparent,
                                  width: 5,
                                ),
                                itemCount: citiesWeather.length,
                                itemBuilder: (context, index) {
                                  //CurrentWeatherData data;
                                  //(controller.dataList.length > 0)
                                  //? data = controller.dataList[index]
                                  //    : data = null;
                                  return GestureDetector(
                                    onTap: () {
                                      Navigator.push(
                                        context,
                                        MaterialPageRoute(
                                            builder: (context) => City(
                                                  weatherInfo:
                                                      citiesWeather[index],
                                                  //listForecastInfo: citiesForecast[index],
                                                )), // aqui é pra mandar a WeatherInfo e ListForecastInfo
                                      );
                                    },
                                    child: SizedBox(
                                      width: 140,
                                      height: 120,
                                      child: Card(
                                          color: Colors.white38,
                                          shape: RoundedRectangleBorder(
                                            borderRadius:
                                                BorderRadius.circular(15),
                                          ),
                                          child: Row(
                                              mainAxisAlignment:
                                                  MainAxisAlignment
                                                      .spaceBetween,
                                              children: <Widget>[
                                                displayCityIcon(index),
                                                displayCityNameAndDescription(
                                                    index),
                                                displayCityTemperature(index)
                                              ])),
                                    ),
                                  );
                                },
                              ),
                            ),
                          ]))))
        ]));
  }

  Column displayCityIcon(int index) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Container(
          width: 100,
          height: 100,
          decoration: BoxDecoration(
            image: DecorationImage(
              image: NetworkImage(
                  'https://openweathermap.org/img/wn/${citiesWeather[index].weatherIcon}@4x.png'),
              fit: BoxFit.cover,
            ),
          ),
        ),
      ],
    );
  }

  Column displayCityNameAndDescription(int index) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Align(
          alignment: Alignment.centerLeft,
          child: Text(
            '${citiesWeather[index].cityName}',
            style: Theme.of(context).textTheme.caption?.copyWith(
                  fontSize: 18,
                  fontWeight: FontWeight.bold,
                  color: Colors.black54,
                  fontFamily: 'flutterfonts',
                ),
          ),
        ),
        Align(
          alignment: Alignment.centerLeft,
          child: Text(
            citiesWeather[index].weatherDescription.toString(),
            style: Theme.of(context).textTheme.caption?.copyWith(
                  color: Colors.black54,
                  fontFamily: 'flutterfonts',
                  fontSize: 14,
                ),
          ),
        ),
        getComment(index)
      ],
    );
  }

  Align getComment(int i) {
    String comment;
    if (citiesWeather[i].weatherMain.toString() == "Rain" ||
        ["200", "201", "202", "310", "311", "312", "313", "314"]
            .contains(citiesWeather[i].weatherID.toString())) {
      comment = "Yes, IT is!";
    } else {
      comment = "No, IT's not...";
    }

    return Align(
      alignment: Alignment.centerLeft,
      child: Text(
        comment,
        style: Theme.of(context).textTheme.caption?.copyWith(
              color: Colors.black54,
              fontFamily: 'flutterfonts',
              fontSize: 14,
            ),
      ),
    );
  }

  Column displayCityTemperature(int index) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Container(
          width: 100,
          height: 100,
          alignment: Alignment.centerRight,
          padding: const EdgeInsets.only(right: 15),
          child: Text(
            '${citiesWeather[index].mainTemp?.toInt()}ºC',
            style: Theme.of(context).textTheme.caption?.copyWith(
                  fontSize: 30,
                  fontWeight: FontWeight.bold,
                  color: Colors.black54,
                  fontFamily: 'flutterfonts',
                ),
          ),
        )
      ],
    );
  }
}
