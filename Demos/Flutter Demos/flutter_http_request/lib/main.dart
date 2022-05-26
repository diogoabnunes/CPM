import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'HTTP Demo',
        theme: ThemeData(primarySwatch: Colors.blue),
        home: MyHomePage(title: 'HTTP Request')
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
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
              child: Text('Do Request'),
              onPressed: () {
                setState(() {
                  message = getResponse();
                });
              }
            ),
            FutureBuilder<String> (
              future: message,
              builder: (context, snapshot) {
                if (snapshot.hasData)
                  return Text(snapshot.data!);
                else if (snapshot.hasError)
                  return Text('${snapshot.error}');
                return CircularProgressIndicator();
              }
            )
          ]
        )
      )
    );
  }
}

Future<String> getResponse() async {
  final response = await http.get(Uri.http('data.fixer.io', '/api/latest',
      { 'access_key': '30d32b83f4fe085e9d7d25174db2e2bb',
        'base': 'EUR',
        'symbols': 'USD,GBP'}
  ));
  if (response.statusCode == 200)
    return response.body;
  else
    throw Exception('HTTP failed');
}