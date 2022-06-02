import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:weather_forecast/services/requests.dart';
import 'models/weather_info.dart';

Container getBackground(String url) {
  return Container(
    decoration: BoxDecoration(
      image: DecorationImage(
        image: NetworkImage(url),
        fit: BoxFit.cover,
      ),
    ),
  );
}