import 'forecast_info.dart';

class ListForecastInfo {
  ListForecastInfo({this.list});

  final List<ForecastInfo>? list;

  factory ListForecastInfo.fromJson(Map<String, dynamic> json) {
    if (json.keys.isEmpty) {
      return ListForecastInfo();
    }

    return ListForecastInfo(
        list: (json['list'] as List).map((i) => ForecastInfo.fromJson(i)).toList()
    );
  }

  @override
  String toString() {
    return 'ListForecastInfo{list: ${list?.length}, 1stDate: ${list?[0].forecastedData}, lastDate: ${list?[39].forecastedData}';
  }
}