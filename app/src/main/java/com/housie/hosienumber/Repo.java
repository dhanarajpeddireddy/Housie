package com.housie.hosienumber;

import android.speech.tts.TextToSpeech;

import java.util.List;

public class Repo implements TextToSpeech.OnInitListener {


    private HouseNumberHelper houseNumberHelper;

    private SpeechUtil speechUtil;

     static Repo repo;

    public static Repo getinstance()
    {
       if (repo==null)
       {
           repo = new Repo();
       }

       return repo;
    }

    private Repo ()
    {
        houseNumberHelper=new HouseNumberHelper();
        speechUtil=new SpeechUtil(HousieApplication.getContext(),this);
    }



    public int requestRandomNumber()
    {
        return houseNumberHelper.getRandomHouseNumber();
    }

    public List<Integer> requestRandomValueList() {
        return houseNumberHelper.getRandomValueList();
    }

    public void requestSpeakNumber(String text) {

        if (speachUtilStatus)
        speechUtil.speak(text);
        else
            speechText=text;

    }

    String speechText;

    boolean speachUtilStatus=false;
    @Override
    public void onInit(int i) {
        speachUtilStatus=true;
        requestSpeakNumber(speechText);
    }
}
