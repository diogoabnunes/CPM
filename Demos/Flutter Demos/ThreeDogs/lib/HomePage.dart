import 'package:flutter/material.dart';
import 'package:three_dogs/DogBox.dart';

class DogsPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Three Dogs in Boxes')
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            DogBox('Rocky'),
            SizedBox(height: 8.0),
            DogBox('Spot'),
            SizedBox(height: 8.0),
            DogBox('Fido')
          ]
        )
      )
    );
  }
}