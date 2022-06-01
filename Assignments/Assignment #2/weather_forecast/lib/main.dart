import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

void main() {
  runApp(const WeatherApp());
}

class WeatherApp extends StatelessWidget {
  const WeatherApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Home',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: const Cities(title: 'Weather Forecast'),
    );
  }
}
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
        appBar: AppBar(title: Text(widget.title)),
        body: Stack(children: [
          Container(
            decoration: BoxDecoration(
              image: DecorationImage(
                image: NetworkImage(
                    'https://www.publicdomainpictures.net/pictures/310000/nahled/-1571046681AOt.jpg'),
                fit: BoxFit.cover,
              ),
            ),
          ),
          Expanded(
              flex: 1,
              child: Stack(children: <Widget>[
                Container(
                  child: AppBar(
                    backgroundColor: Colors.transparent,
                    elevation: 0,
                    leading: IconButton(
                      icon: Icon(
                        Icons.menu,
                        color: Colors.white,
                      ),
                      onPressed: () {},
                    ),
                  ),
                ),
                Container(
                  padding: EdgeInsets.only(top: 100, left: 20, right: 20),
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
              ])),
          Expanded(
              flex: 2,
              child: Stack(children: <Widget>[
                Padding(
                    padding: EdgeInsets.symmetric(horizontal: 15),
                    child: Container(
                        padding: EdgeInsets.only(top: 200),
                        child: Align(
                            alignment: Alignment.topLeft,
                            child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: <Widget>[
                                  Container(
                                    height: 400,
                                    child: ListView.separated(
                                      physics: BouncingScrollPhysics(),
                                      scrollDirection: Axis.vertical,
                                      separatorBuilder: (context, index) =>
                                          VerticalDivider(
                                        color: Colors.transparent,
                                        width: 5,
                                      ),
                                      itemCount: 6,
                                      itemBuilder: (context, index) {
                                        //CurrentWeatherData data;
                                        //(controller.dataList.length > 0)
                                        //? data = controller.dataList[index]
                                        //    : data = null;
                                        return Container(
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
                                        );
                                      },
                                    ),
                                  ),
                                ]))))
              ]))
        ]));
  }
}

/*class Home extends StatefulWidget {
  const Home({Key? key, required this.title}) : super(key: key);
  final String title;

  //@override
  //State<Home> createState() => _HomeState();
}*/

/*class _HomeState extends State<Home> {
  Future<String> message = Future<String>.value('');

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(widget.title)),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
                onPressed: () { setState(() { message = getCityWeather(); }); } ,
                child: const Text('Add City')
            ),
            FutureBuilder<String> (
                future: message,
                builder: (context, snapshot) {
                  if (snapshot.hasData) {
                    return Text(snapshot.data!);
                  } else if (snapshot.hasError) {
                    return Text('${snapshot.error}');
                  }
                  return const CircularProgressIndicator();
                }
            )
          ],
        )
      ),
    );
  }
}*/

Future<String> getCityWeather() async {
  final response =
      await http.get(Uri.http('api.openweathermap.org', '/data/2.5/weather', {
    'appid': 'a178a302491f20b4079a8e30ef112a78',
    'units': 'metric',
    'q': 'Porto, Portugal'
  }));
  if (response.statusCode == 200) {
    return response.body;
  } else {
    throw Exception('HTTP failed');
  }
}
