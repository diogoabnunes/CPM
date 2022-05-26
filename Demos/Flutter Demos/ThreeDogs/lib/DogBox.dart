import 'package:flutter/material.dart';

class DogBox extends StatelessWidget {
  String name;

  DogBox(this.name);

  @override
  Widget build(BuildContext context) {
    return DecoratedBox(
      decoration: BoxDecoration(color: Colors.lightBlueAccent),
      child: Padding(
        padding: EdgeInsets.all(12.0),
        child: Text(name),
      ),
    );
  }
}