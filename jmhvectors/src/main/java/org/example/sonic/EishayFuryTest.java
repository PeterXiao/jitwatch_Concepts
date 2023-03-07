package org.example.sonic;

import com.alibaba.fastjson2.JSON;

import java.util.List;

public class EishayFuryTest  {


public static void main(String[] args) throws Exception {
        String str = "{\"images\": [{\n" +
        "      \"height\":768,\n" +
        "      \"size\":\"LARGE\",\n" +
        "      \"title\":\"Javaone Keynote\",\n" +
        "      \"uri\":\"http://javaone.com/keynote_large.jpg\",\n" +
        "      \"width\":1024\n" +
        "    }, {\n" +
        "      \"height\":240,\n" +
        "      \"size\":\"SMALL\",\n" +
        "      \"title\":\"Javaone Keynote\",\n" +
        "      \"uri\":\"http://javaone.com/keynote_small.jpg\",\n" +
        "      \"width\":320\n" +
        "    }\n" +
        "  ],\n" +
        "  \"media\": {\n" +
        "    \"bitrate\":262144,\n" +
        "    \"duration\":18000000,\n" +
        "    \"format\":\"video/mpg4\",\n" +
        "    \"height\":480,\n" +
        "    \"persons\": [\n" +
        "      \"Bill Gates\",\n" +
        "      \"Steve Jobs\"\n" +
        "    ],\n" +
        "    \"player\":\"JAVA\",\n" +
        "    \"size\":58982400,\n" +
        "    \"title\":\"Javaone Keynote\",\n" +
        "    \"uri\":\"http://javaone.com/keynote.mpg\",\n" +
        "    \"width\":640\n" +
        "  }\n" +
        "}";

        MediaContent mediaContent = JSON.parseObject(str, MediaContent.class);
//
//        io.fury.ThreadSafeFury furyCompatible = io.fury.Fury.builder()
//                .withLanguage(io.fury.Language.JAVA)
//                .withReferenceTracking(true)
//                .disableSecureMode()
//                .withCompatibleMode(io.fury.serializers.CompatibleMode.COMPATIBLE)
//                .buildThreadSafeFury();
//
//        byte[] furyBytes = furyCompatible.serialize(mediaContent);
//        System.out.println("fury bytes size : " + furyBytes.length);
//
//        int LOOP_COUNT = 1000000;
//        for (int j = 0; j < 5; ++j) {
//            long start = System.currentTimeMillis();
//            for (int i = 0; i < LOOP_COUNT; i++) {
//                furyCompatible.serialize(mediaContent);
//            }
//            long millis = System.currentTimeMillis() - start;
//            System.out.println("fastjson2 eishay fury-serialize time : " + millis);
//        }
//
//        for (int j = 0; j < 5; ++j) {
//            long start = System.currentTimeMillis();
//            for (int i = 0; i < LOOP_COUNT; i++) {
//                furyCompatible.deserialize(furyBytes);
//            }
//            long millis = System.currentTimeMillis() - start;
//            System.out.println("fastjson2 eishay fury-deserialize time : " + millis);
//        }
        }



}
