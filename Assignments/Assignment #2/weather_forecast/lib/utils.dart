import 'package:flutter/material.dart';

Container getBackground(String url){
  return  Container(
    decoration: BoxDecoration(
      image: DecorationImage(
        image: NetworkImage(url),
        fit: BoxFit.cover,
      ),
    ),
  );
}