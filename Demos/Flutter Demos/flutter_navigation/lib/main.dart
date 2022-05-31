import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(
    title: 'Nav Basics',
    home: FirstPage()
  ));
}

class FirstPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('First Page')),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
            Padding(
              padding: EdgeInsets.all(2.0),
              child: Image.asset('assets/flutter12.png'),
            ),
            ElevatedButton(
              child: Text('Go to second'),
              onPressed: () {
                Navigator.push(context, MaterialPageRoute(builder: (context) => SecondPage()));
              }
            )
          ]
        )
      )
    );
  }
}

class SecondPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Second Page')),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
            Padding(
              padding: EdgeInsets.all(2.0),
              child: Image.asset('assets/flutter12.png')
            ),
            ElevatedButton(
              child: Text('Go back'),
              onPressed: () {
                Navigator.pop(context);
              }
            )
          ]
        )
      )
    );
  }
}