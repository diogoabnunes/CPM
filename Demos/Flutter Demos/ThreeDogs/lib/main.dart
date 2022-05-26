import 'package:flutter/material.dart';
import 'package:three_dogs/HomePage.dart';

void main() {
  runApp(DogsApp());
}

class DogsApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'My Dogs App',
      home: DogsPage()
    );
  }
}