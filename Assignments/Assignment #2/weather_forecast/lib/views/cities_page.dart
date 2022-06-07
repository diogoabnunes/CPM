import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:weather_forecast/views/city_page.dart';
import 'package:weather_forecast/models/weather_info.dart';
import 'package:weather_forecast/models/forecast_info.dart';
import 'package:weather_forecast/services/requests.dart';
import 'package:weather_forecast/utils/file_manager.dart';

class CitiesPage extends StatefulWidget {
  const CitiesPage({Key? key, required this.title, required this.storage})
      : super(key: key);
  final String title;
  final FileManager storage;

  @override
  State<CitiesPage> createState() => _CitiesPageState();
}

class _CitiesPageState extends State<CitiesPage> {
  List<String> cities = [];
  List<WeatherInfo> citiesWeather = <WeatherInfo>[];
  List<ForecastInfo> citiesForecast = <ForecastInfo>[];

  final TextEditingController _controller = TextEditingController();
  late Future<String> _value;

  Future<String> loadInfo() async {
    List<String> citiesLoaded = [];
    await widget.storage.read().then((value) => {
          setState(() {
            citiesLoaded = value;
            widget.storage.clear();
          })
        });
    for (var city in citiesLoaded) {
      requestCity(city);
    }
    return 'hello';
  }

  Future<String> requestCity(String str) async {
    try {
      for (var i = 0; i < citiesWeather.length; i++) {
        if (citiesWeather[i].cityName.toString().toLowerCase() ==
            str.toLowerCase()) throw Exception("duplicate city");
      }
      WeatherInfo wi = WeatherInfo();
      await getCityWeather(str).then((result) => {
            wi = WeatherInfo.fromJson(jsonDecode(result)),
          });
      ForecastInfo fi = ForecastInfo(list: []);
      await getFiveDaysWeather(str).then((result) => {
            fi = ForecastInfo.fromJson(jsonDecode(result)),
          });
      setState(() {
        citiesWeather.add(wi);
        citiesForecast.add(fi);
        cities.add(str);
      });
      widget.storage.write(cities);
      return 'new city';
    } catch (e) {
      return e.toString();
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
                              height: 500,
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
                                  return GestureDetector(
                                    onTap: () {
                                      Navigator.push(
                                        context,
                                        MaterialPageRoute(
                                            builder: (context) => CityPage(
                                                  weatherInfo:
                                                      citiesWeather[index],
                                                  forecastInfo:
                                                      citiesForecast[index],
                                                )),
                                      );
                                    },
                                    onLongPress: () => showDialog<String>(
                                        context: context,
                                        builder: (BuildContext context) =>
                                            AlertDialog(
                                              title: Text(citiesWeather[index]
                                                  .cityName
                                                  .toString()),
                                              content: const Text(
                                                  "Do you want to delete this city?"),
                                              actions: [
                                                TextButton(
                                                  onPressed: () {
                                                    Navigator.pop(
                                                        context, 'Cancel');
                                                  },
                                                  child: const Text('Cancel'),
                                                ),
                                                TextButton(
                                                  onPressed: () {
                                                    setState(() {
                                                      cities.removeAt(index);
                                                      citiesWeather
                                                          .removeAt(index);
                                                      // citiesForecast
                                                      //     .removeAt(index);
                                                    });
                                                    Navigator.pop(
                                                        context, 'Delete');
                                                  },
                                                  child: const Text('Delete'),
                                                )
                                              ],
                                            )),
                                    child: SizedBox(
                                      width: 140,
                                      height: 120,
                                      child: Card(
                                          color: Colors.white70,
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
            citiesWeather[index].cityName.toString(),
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
    if (citiesWeather[i].weatherMain?.toString() == "Rain" ||
        ["200", "201", "202", "310", "311", "312", "313", "314"]
            .contains(citiesWeather[i].weatherId?.toString())) {
      comment = "Yes, IT is raining!";
    } else {
      comment = "No, IT's not raining...";
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
