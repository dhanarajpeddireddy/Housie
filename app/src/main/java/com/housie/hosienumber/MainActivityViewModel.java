package com.housie.hosienumber;

import java.util.List;

public class MainActivityViewModel {


    public int getRandomNumber()
    {
       return Repo.getinstance().requestRandomNumber();
    }

    public List<Integer> getRandomValuesList(){
        return Repo.getinstance().requestRandomValueList();
    }


    public void speakNumber(String text)
    {
        Repo.getinstance().requestSpeakNumber(text);
    }


    public void  restart()
    {
        Repo.getinstance().requestEmpty();
    }

}
