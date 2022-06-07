import 'package:flutter/material.dart';

const appID = 'a178a302491f20b4079a8e30ef112a78';
const unitsMETRIC = 'metric';
const openWeatherMapAPI = 'api.openweathermap.org';
const weatherPath = '/data/2.5/weather';
const forecastPath = '/data/2.5/forecast';
const backgroundImage = 'assets/images/degrade.jpg';
const fileName = 'cities.txt';

Map<int, Color> color ={
  50:const Color.fromRGBO(136,14,79, .1),
  100:const Color.fromRGBO(136,14,79, .2),
  200:const Color.fromRGBO(136,14,79, .3),
  300:const Color.fromRGBO(136,14,79, .4),
  400:const Color.fromRGBO(136,14,79, .5),
  500:const Color.fromRGBO(136,14,79, .6),
  600:const Color.fromRGBO(136,14,79, .7),
  700:const Color.fromRGBO(136,14,79, .8),
  800:const Color.fromRGBO(136,14,79, .9),
  900:const Color.fromRGBO(136,14,79, 1),
};

const int degradeBlue = 0xFF355e99;
const int midnightBlue = 0xFF41729f;
const int blueGray = 0xFF5885af;