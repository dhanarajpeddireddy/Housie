package com.housie.hosienumber;

import android.util.Log;

public class Repo {


    HouseNumberHelper houseNumberHelper;

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
    }



    public int requestRandomNumber()
    {
            return houseNumberHelper.randomHouseNumber();


    }

}
