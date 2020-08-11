package com.housie.hosienumber;

public class MainActivityViewModel {

    public int getRandomNumber()
    {
       return Repo.getinstance().requestRandomNumber();
    }

}
