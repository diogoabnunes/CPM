import 'dart:io';
import 'package:path_provider/path_provider.dart';
import 'package:weather_forecast/utils/constants.dart';

class FileManager {
  Future<String> get _localPath async {
    final directory = await getApplicationDocumentsDirectory();

    return directory.path;
  }

  Future<File> get _localFile async {
    final path = await _localPath;
    return File('$path/$fileName');
  }

  Future<File> write(List<String> list) async {
    final file = await _localFile;
    if (list.isEmpty) return file.writeAsString('');
    for (var item in list) {
      item = item.toLowerCase();
    }
    // print("Writing ${list.join(',')} in ${file.path}");
    return file.writeAsString(list.join(','));
  }

  Future<List<String>> read() async {
    try {
      final file = await _localFile;
      final contents = await file.readAsString();
      // print("Reading $contents from ${file.path}");
      if (contents == '') return [];
      return contents.split(',');
    } catch (e) {
      return [];
    }
  }

  Future<File> clear() async {
    final file = await _localFile;
    return file.writeAsString('');
  }
}
