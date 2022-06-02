import 'package:flutter/material.dart';

class City extends StatefulWidget {
  const City({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  State<City> createState() => _CityState();
}

class _CityState extends State<City> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text(widget.title)),
        resizeToAvoidBottomInset: false,
        body: const Center(
          child: Text('Hello World'),
        ));
  }
}
