import 'package:flutter/material.dart';
import 'painter.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Canvas',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: HomePage()
    );
  }
}

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  double _len = 0.0;
  double _x = 0.0;
  double _y = 0.0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Circle triangle page"),
      ),
      body: Container(
        child: Center(
          child: GestureDetector(
            onHorizontalDragStart: (detail) {
              _x = detail.globalPosition.dx;
            },
            onVerticalDragStart: (detail) {
              _y = detail.globalPosition.dy;
            },
            onHorizontalDragUpdate: (detail) {
              setState(() {
                _len -= detail.globalPosition.dx - _x;
                _x = detail.globalPosition.dx;
              });
            },
            onVerticalDragUpdate: (detail) {
              setState(() {
                _len += detail.globalPosition.dy - _y;
                _y = detail.globalPosition.dy;
              });
            },
            child: Container(
              width: 300,
              height: 300,
              child: CustomPaint(
                painter: (CircleTrianglePainter(scrollLen: _len)),
                child: Container(),
              ),
            ),
          ),
        )
      ),
    );
  }
}