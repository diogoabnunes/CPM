import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:weather_forecast/views/city.dart';

import '../models/weather_info.dart';
import '../services/requests.dart';

class Cities extends StatefulWidget {
  const Cities({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  State<Cities> createState() => _CitiesState();
}

class _CitiesState extends State<Cities> {
  List<String> cities = ["Porto", "Sydney"];
  List<WeatherInfo> citiesWeather = <WeatherInfo>[];

  //List<ListForecastInfo> citiesForecast = <ListForecastInfo>[];
  late Future<String> _value;

  Future<String> loadInfo() async {
    citiesWeather.clear();
    for (var city in cities) {
      WeatherInfo wi;
      await getCityWeather(city).then((result) => {
            wi = WeatherInfo.fromJson(jsonDecode(result)),
            citiesWeather.add(wi)
          });
      // await getFiveDaysWeather(city).then((result) =>
      // {
      //   lfi = ForecastInfo.fromJson(jsonDecode(result)) as ListForecastInfo,
      //   citiesForecast.add(lfi)
      // });
    }
    return 'hello';
  }

  Future<String> requestCity(String str) async {
    WeatherInfo wi;
    await getCityWeather(str).then((result) => {
          wi = WeatherInfo.fromJson(jsonDecode(result)),
          citiesWeather.add(wi),
          cities.add(str)
        });
    // await getFiveDaysWeather(str).then((result) =>
    // {
    //   lfi = ForecastInfo.fromJson(jsonDecode(result)) as ListForecastInfo,
    //   citiesForecast.add(lfi)
    // });
    return 'new city';
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
              image: AssetImage("assets/images/bluesky.jpg"),
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
                print(snapshot.connectionState);
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return CircularProgressIndicator();
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
        child: Stack(children: <Widget>[
          Container(
            padding: const EdgeInsets.only(top: 40, left: 20, right: 20),
            child: TextField(
              style: const TextStyle(
                color: Colors.white,
              ),
              textInputAction: TextInputAction.search,
              onSubmitted: (String str) {
                setState(() {
                  _value = requestCity(str);
                });
              },
              decoration: InputDecoration(
                suffix: const Icon(
                  Icons.search,
                  color: Colors.white,
                ),
                hintStyle: const TextStyle(color: Colors.white),
                hintText: 'Search'.toUpperCase(),
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
        ]));
  }

  Expanded getListView() {
    return Expanded(
        flex: 2,
        child: Stack(children: <Widget>[
          Padding(
              padding: const EdgeInsets.symmetric(horizontal: 15),
              child: Container(
                  padding: const EdgeInsets.only(top: 150),
                  child: Align(
                      alignment: Alignment.topLeft,
                      child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: <Widget>[
                            Container(
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
                                            builder: (context) => const City(
                                                title:
                                                    "Weather Forecast")), // aqui é pra mandar a WeatherInfo
                                      );
                                    },
                                    child: Container(
                                      width: 140,
                                      height: 120,
                                      child: Card(
                                          shape: RoundedRectangleBorder(
                                            borderRadius:
                                                BorderRadius.circular(15),
                                          ),
                                          child: Container(
                                            child: Row(
                                                mainAxisAlignment:
                                                    MainAxisAlignment.start,
                                                children: <Widget>[
                                                  Column(
                                                    mainAxisAlignment:
                                                        MainAxisAlignment
                                                            .center,
                                                    children: [
                                                      Container(
                                                        width: 100,
                                                        height: 100,
                                                        decoration:
                                                            BoxDecoration(
                                                          image:
                                                              DecorationImage(
                                                            image: NetworkImage(
                                                                'https://openweathermap.org/img/wn/' +
                                                                    citiesWeather[
                                                                            index]
                                                                        .weatherIcon
                                                                        .toString() +
                                                                    '@4x.png'),
                                                            fit: BoxFit.cover,
                                                          ),
                                                        ),
                                                      ),
                                                    ],
                                                  ),
                                                  Column(
                                                    mainAxisAlignment:
                                                        MainAxisAlignment
                                                            .center,
                                                    children: [
                                                      Align(
                                                        alignment: Alignment
                                                            .centerLeft,
                                                        child: Container(
                                                          child: Text(
                                                            citiesWeather[index]
                                                                    .cityName
                                                                    .toString() +
                                                                ': ' +
                                                                (citiesWeather[
                                                                            index]
                                                                        .mainTemp
                                                                        ?.toInt())
                                                                    .toString() +
                                                                'ºC',
                                                            style: Theme.of(
                                                                    context)
                                                                .textTheme
                                                                .caption
                                                                ?.copyWith(
                                                                  fontSize: 18,
                                                                  fontWeight:
                                                                      FontWeight
                                                                          .bold,
                                                                  color: Colors
                                                                      .black45,
                                                                  fontFamily:
                                                                      'flutterfonts',
                                                                ),
                                                          ),
                                                        ),
                                                      ),
                                                      Align(
                                                        alignment: Alignment
                                                            .centerLeft,
                                                        child: Container(
                                                          child: Text(
                                                            citiesWeather[index]
                                                                .weatherDescription
                                                                .toString(),
                                                            style: Theme.of(
                                                                    context)
                                                                .textTheme
                                                                .caption
                                                                ?.copyWith(
                                                                  color: Colors
                                                                      .black45,
                                                                  fontFamily:
                                                                      'flutterfonts',
                                                                  fontSize: 14,
                                                                ),
                                                          ),
                                                        ),
                                                      ),
                                                    ],
                                                  ),
                                                  Column(
                                                      mainAxisAlignment:
                                                          MainAxisAlignment
                                                              .center,
                                                      children: [
                                                        Align(
                                                            alignment: Alignment
                                                                .centerRight,
                                                            child: Column(
                                                              mainAxisAlignment:
                                                                  MainAxisAlignment
                                                                      .end,
                                                              children: [
                                                                Row(
                                                                  children: [
                                                                    Text(
                                                                      (citiesWeather[index].mainTempMax?.toInt())
                                                                              .toString() +
                                                                          'ºC',
                                                                      style: Theme.of(
                                                                              context)
                                                                          .textTheme
                                                                          .caption
                                                                          ?.copyWith(
                                                                            color:
                                                                                Colors.black45,
                                                                            fontFamily:
                                                                                'flutterfonts',
                                                                            fontSize:
                                                                                14,
                                                                          ),
                                                                    )
                                                                  ],
                                                                ),
                                                                Row(
                                                                  children: [
                                                                    Text(
                                                                      (citiesWeather[index].mainTempMin?.toInt())
                                                                              .toString() +
                                                                          'ºC',
                                                                      style: Theme.of(
                                                                              context)
                                                                          .textTheme
                                                                          .caption
                                                                          ?.copyWith(
                                                                            color:
                                                                                Colors.black45,
                                                                            fontFamily:
                                                                                'flutterfonts',
                                                                            fontSize:
                                                                                14,
                                                                          ),
                                                                    )
                                                                  ],
                                                                )
                                                              ],
                                                            )),
                                                      ])
                                                ]),
                                          )),
                                    ),
                                  );
                                },
                              ),
                            ),
                          ]))))
        ]));
  }
}
