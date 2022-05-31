import 'package:flutter/material.dart';
import 'dart:math';

class SizeUtil {
  Size designSize = Size(500.0, 500.0);
  Size _logicalSize = Size(1.0, 1.0);

  get width => _logicalSize.width;
  get height => _logicalSize.height;
  set logicSize(Size size) => _logicalSize = size;

  //@param w is the design w;
  double getAxisX(double w) {
    return (w * width) / designSize.width;
  }

// the y direction
  double getAxisY(double h) {
    return (h * height) / designSize.height;
  }

  // diagonal direction value with design size s.
  double getAxisBoth(double s) {
    return s *
        sqrt((width * width + height * height) /
            (designSize.width * designSize.width +
                designSize.height * designSize.height));
  }
}