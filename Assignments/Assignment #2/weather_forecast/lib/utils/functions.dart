
import 'package:flutter/material.dart';
import 'package:weather_forecast/utils/constants.dart';

Container getBackground(){
  return Container(
    decoration: const BoxDecoration(
      image: DecorationImage(
          image: AssetImage(backgroundImage),
          fit: BoxFit.fill),
    ),);
}