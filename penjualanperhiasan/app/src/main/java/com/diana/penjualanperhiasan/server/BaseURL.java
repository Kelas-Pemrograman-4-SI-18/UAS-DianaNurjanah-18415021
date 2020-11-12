package com.diana.penjualanperhiasan.server;

public class BaseURL {

 public static String baseUrl = "http://192.168.43.64:5050/";

 public static String login    = baseUrl + "user/login" ;
 public static String register = baseUrl + "user/registrasi";

 //perhiasan
 public static String dataPerhiasan = baseUrl + "perhiasan/dataperhiasan";
 public static String editDataPerhiasan = baseUrl + "perhiasan/ubah/";
 public static String hapusData = baseUrl + "perhiasan/hapus/";
 public static String inputPerhiasan = baseUrl + "perhiasan/input";

}
