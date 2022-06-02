import 'package:flutter/material.dart';
import 'package:weather_forecast/utils.dart';
import 'package:weather_forecast/city.dart';

class Cities extends StatefulWidget {
  const Cities({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  State<Cities> createState() => _CitiesState();
}

class _CitiesState extends State<Cities> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Cities")),
      resizeToAvoidBottomInset: false,
      body: Stack(
        children: [
          getBackground('https://i.pinimg.com/564x/1e/da/30/1eda30c2aa2def050aee09c5517fbc17.jpg'),
          getSearch(),
          getListView()
        ],
      ),
    );
  }
}

Expanded getSearch() {
  return Expanded(
      flex: 1,
      child: Stack(children: <Widget>[
        Container(
          padding: EdgeInsets.only(top: 40, left: 20, right: 20),
          child: TextField(
            //onChanged: (value) => controller.city = value,
            style: TextStyle(
              color: Colors.white,
            ),
            textInputAction: TextInputAction.search,
            //onSubmitted: (value) => controller.updateWeather(),
            decoration: InputDecoration(
              suffix: Icon(
                Icons.search,
                color: Colors.white,
              ),
              hintStyle: TextStyle(color: Colors.white),
              hintText: 'Search'.toUpperCase(),
              border: OutlineInputBorder(
                borderRadius: BorderRadius.circular(10),
                borderSide: BorderSide(color: Colors.white),
              ),
              focusedBorder: OutlineInputBorder(
                borderRadius: BorderRadius.circular(10),
                borderSide: BorderSide(color: Colors.white),
              ),
              enabledBorder: OutlineInputBorder(
                borderRadius: BorderRadius.circular(10),
                borderSide: BorderSide(color: Colors.white),
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
            padding: EdgeInsets.symmetric(horizontal: 15),
            child: Container(
                padding: EdgeInsets.only(top: 150),
                child: Align(
                    alignment: Alignment.topLeft,
                    child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: <Widget>[
                          Container(
                            height: 450,
                            child: ListView.separated(
                              physics: BouncingScrollPhysics(),
                              scrollDirection: Axis.vertical,
                              separatorBuilder: (context, index) =>
                                  VerticalDivider(
                                    color: Colors.transparent,
                                    width: 5,
                                  ),
                              itemCount: 2,
                              itemBuilder: (context, index) {
                                //CurrentWeatherData data;
                                //(controller.dataList.length > 0)
                                //? data = controller.dataList[index]
                                //    : data = null;
                                return GestureDetector(
                                    onTap: () {
                                      Navigator.push(
                                        context,
                                        MaterialPageRoute(builder: (context) => City(title: "Weather Forecast")),
                                      );
                                    },
                                    child: Container(
                                      width: 140,
                                      height: 150,
                                      child: Card(
                                        shape: RoundedRectangleBorder(
                                          borderRadius:
                                          BorderRadius.circular(15),
                                        ),
                                        child: Container(
                                          child: Column(
                                            mainAxisAlignment:
                                            MainAxisAlignment.center,
                                            children: <Widget>[
                                              Text(
                                                "Viana do Castelo",
                                                style: Theme.of(context)
                                                    .textTheme
                                                    .caption
                                                    ?.copyWith(
                                                  fontSize: 18,
                                                  fontWeight:
                                                  FontWeight.bold,
                                                  color: Colors.black45,
                                                  fontFamily:
                                                  'flutterfonts',
                                                ),
                                              ),
                                              Text(
                                                "22ºC",
                                                style: Theme.of(context)
                                                    .textTheme
                                                    .caption
                                                    ?.copyWith(
                                                  fontSize: 18,
                                                  fontWeight:
                                                  FontWeight.bold,
                                                  color: Colors.black45,
                                                  fontFamily:
                                                  'flutterfonts',
                                                ),
                                              ),
                                              Container(
                                                width: 50,
                                                height: 50,
                                                decoration: BoxDecoration(
                                                  image: DecorationImage(
                                                    image: NetworkImage(
                                                        'https://cdn1.iconfinder.com/data/icons/weather-forecast-meteorology-color-1/128/weather-partly-sunny-512.png'),
                                                    fit: BoxFit.cover,
                                                  ),
                                                ),
                                              ),
                                              Text(
                                                "FRIO",
                                                style: Theme.of(context)
                                                    .textTheme
                                                    .caption
                                                    ?.copyWith(
                                                  color: Colors.black45,
                                                  fontFamily:
                                                  'flutterfonts',
                                                  fontSize: 14,
                                                ),
                                              ),
                                            ],
                                          ),
                                        ),
                                      ),
                                    )
                                );
                              },
                            ),
                          ),
                        ]))))
      ]));
}