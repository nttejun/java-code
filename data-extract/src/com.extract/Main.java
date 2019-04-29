package com.extract;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*** 요청, 응답에 대한 성공 데이터 추출 ***/
public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        /*** 파일 목록 추출 ***/
        List<String> today = new ArrayList<>();
        List<String> yesterday= new ArrayList<>();

        String isTodayDir = "/data-extract/indentity-verify/example1/logs/today/";
        String isYesterdayDir = "/data-extract/indentity-verify/example1/logs/yesterday/";

        /*** 파일 배열에 담기 ***/
        for (File info : new File(isTodayDir).listFiles()) {
            if (info.isFile()) {
                today.add(isTodayDir+info.getName());
            }
        }

        for (File info : new File(isYesterdayDir).listFiles()) {
            if (info.isFile()) {
                yesterday.add(isYesterdayDir+info.getName());
            }
        }

        /*** 오름차순 정렬 ***/
        Ascending ascending = new Ascending();
        Collections.sort(today, ascending);
        Collections.sort(yesterday, ascending);

        /*** 파일 목록 확인 ***/
        for (int index = 0; index < today.size(); index++) {
            System.out.println(today.get(index));
        }

        for (int index = 0; index < yesterday.size(); index++) {
            System.out.println(yesterday.get(index));
        }

        /*** 요청 데이터 추출 ***/
        BufferedReader reqReader = new BufferedReader(new FileReader("/data-extract/indentity-verify/logs/log.txt"));

        JSONParser reqJsonParser = new JSONParser();
        JSONObject reqJsonObject;

        String reqLine = reqReader.readLine();

        List<Object> reqCsList = new ArrayList<>();
        List<Object> reqEsList = new ArrayList<>();

        while (reqLine != null) {

            if (reqLine.contains("Request Data")) {

                if (reqLine.contains("\"operation\":\"REQUEST_PHONENUM\"")) {

                    if (reqLine.contains("\"version\":\"V10\"")) {

                        // JSON String 데이터 시작 인덱스
                        int i = reqLine.indexOf("{\"reqType\"");

                        // JSON String 데이터
                        String s = reqLine.substring(i);

                        reqJsonObject = (JSONObject) reqJsonParser.parse(s);
                        reqCsList.add(reqJsonObject.get("sessionID"));

                    } else if (reqLine.contains("\"version\":\"S10\"")) {

                        // JSON String 데이터 시작 인덱스
                        int i = reqLine.indexOf("{\"reqType\"");

                        // JSON String 데이터 추출
                        String s = reqLine.substring(i);

                        reqJsonObject = (JSONObject) reqJsonParser.parse(s);
                        reqEsList.add(reqJsonObject.get("sessionID"));

                    }
                }
            }
            reqLine = reqReader.readLine();
        }

        /*** 응답 데이터 추출 ***/
        BufferedReader resReader = new BufferedReader(new FileReader("/Users/wjjeong/devTmonet/easySign/server/data-extract/indentity-verify/logs/t-proxy1_smsci.txt_dosed.txt"));

        JSONParser resJsonParser = new JSONParser();
        JSONObject resJsonObject;

        String resLine = resReader.readLine();

        int esCnt = 0;
        int csCnt = 0;

        while (resLine != null) {

            if (resLine.contains("Sending Data")) {

                if (resLine.contains("\"operation\":\"REQUEST_PHONENUM\"")) {

                    if (resLine.contains("\"resultCode\":\"0000\"")) {

                        // JSON String 데이터 시작 인덱스
                        int resIndex = resLine.indexOf("{\"messageNumber\"");

                        // JSON String 데이터
                        String resStr = resLine.substring(resIndex);

                        resJsonObject = (JSONObject) resJsonParser.parse(resStr);

                        System.out.print(resJsonObject.get("sessionID") + " ");

                        // 버전별 카운팅
                        if(Arrays.asList(reqCsList).toString().contains((CharSequence) resJsonObject.get("sessionID"))){
                            csCnt++;
                        } else if (Arrays.asList(reqEsList).toString().contains((CharSequence) resJsonObject.get("sessionID"))){
                            esCnt++;
                        }
                    }
                }
            }
            resLine = resReader.readLine();
        }

    }

}

