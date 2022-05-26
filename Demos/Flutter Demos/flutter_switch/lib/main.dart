import 'package:flutter/material.dart';

void main() {
  runApp(MyAppStateful());
}

class MyAppStateful extends StatefulWidget {
  MyAppStateful({Key? key, this.title}) : super(key: key);
  final String? title;

  @override
  _MyAppStatefulState createState() => _MyAppStatefulState();
}

class _MyAppStatefulState extends State<MyAppStateful> {
  bool value = false;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        backgroundColor: value ? Colors.tealAccent : Colors.amber,
        appBar: AppBar(title: Text('Stateful Widget Demo')),
        body: Center(
          child: Switch(
            value: value,
            onChanged: (v) {
              setState(() {
                value = v;
              });
            }
          )
        )
      )
    );
  }
}