import 'dart:io';
import 'package:path_provider/path_provider.dart';
import 'package:weather_forecast/utils.dart';
import 'package:flutter/services.dart';

class FileManager {
  Future<void> write(List<String> cities) async {
    String str = cities.join(',');
  }

  Future<List<String>> read() async {
    String str = "";
    List<String> list = str.split(',');
    return list;
  }
}