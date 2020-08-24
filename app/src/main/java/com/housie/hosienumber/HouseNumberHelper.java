package com.housie.hosienumber;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HouseNumberHelper {

    Random random = new Random();
    ArrayList<Integer> randomValues = new ArrayList<>();

    public int getRandomHouseNumber() {
        int randomnumber=-1;
        if(randomValues.size()< Constants.BOUNDORY_NUMBER-1) {
            boolean value;
            do {
             randomnumber = random.nextInt(Constants.BOUNDORY_NUMBER);
                if (randomValues.contains(randomnumber)||randomnumber ==0) {
                    value=true;
                } else {
                    Log.e("random", "sameNumber" + randomnumber);
                    randomValues.add(randomnumber);
                    value=false;
                }

            }while (value);
        }

return randomnumber;
    }

    public void displayRandomNumbers() {
        for (int number : randomValues) {
            Log.e("succsses", "genreated value" + number);
        }

    }

    public List<Integer> getRandomValueList() {
        return randomValues;
    }


    public void emptyList()
    {
        randomValues.clear();
    }
}

