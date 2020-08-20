package com.housie.hosienumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MainActivityViewModel mainActivityViewModel;
    TextView textView;
    GridView gridView;
    Button button,btn_mute,button_switchToAutoOrManual;
    Switch switch_hideGrid;
    Handler handler;
    boolean flagForAutoOrGenerate=true,flagForMuteOrUnmute=false;

    Runnable runnable;

    private ArrayList<Integer> housieNumberList = new ArrayList<>();
    HousieNumbersGridAdapter housieNumbersGridAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void setAuto()  {

            runnable = new Runnable() {
                @Override
                public void run() {
                    getRandomNumber();
                    getAutoRandom();
                }
            };
            getAutoRandom();
    }

    private void getAutoRandom() {

        handler.postDelayed(runnable,5000);
    }


    private void init() {
        mainActivityViewModel=new MainActivityViewModel();
        button = findViewById(R.id.bt_random);
        switch_hideGrid = findViewById(R.id.switch_hideGrid);
        switch_hideGrid.setOnClickListener(this);
        button_switchToAutoOrManual= findViewById(R.id.bt_autoOrGenerate);
        button.setOnClickListener(this);
        button_switchToAutoOrManual.setOnClickListener(this);
        textView=findViewById(R.id.textView);
        gridView = findViewById(R.id.gridView_RandomValuesList);
        btn_mute = findViewById(R.id.btn_mute);
        btn_mute.setOnClickListener(this);
        setHousieNumberBeanList();
        housieNumbersGridAdapter=new HousieNumbersGridAdapter(this,housieNumberList);
        gridView.setAdapter(housieNumbersGridAdapter);
        handler=new Handler();

        speak("I Am Ready");

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.bt_random )
        {
            getRandomNumber();
        }else if(view.getId() == R.id.btn_mute){
            if(flagForMuteOrUnmute){
                flagForMuteOrUnmute=false;
            }else{
                flagForMuteOrUnmute=true;
            }
        }
        else if(view.getId() == R.id.switch_hideGrid){
            if(((Switch)view).isChecked()){
                gridView.setVisibility(View.INVISIBLE);
            }else{
                gridView.setVisibility(View.VISIBLE);
            }
        }
        else if(view.getId() == R.id.bt_autoOrGenerate){
            if(flagForAutoOrGenerate){
                setAuto();
                button.setVisibility(View.INVISIBLE);
                flagForAutoOrGenerate = false;
            }else{
                Log.e("TAG", "onClick: IN else");
                handler.removeCallbacks(runnable);
                button.setVisibility(View.VISIBLE);
                getRandomNumber();
                flagForAutoOrGenerate = true;
            }
        }
    }

    private void getRandomNumber() {
        int number =mainActivityViewModel.getRandomNumber();

        if (number!=-1)
        {
            textView.setText(String.valueOf(number));
            if(!flagForMuteOrUnmute)
            speak(String.valueOf(number));
            housieNumbersGridAdapter.setRandomPickedlist(mainActivityViewModel.getRandomValuesList());
        }
      else
        {
            Toast.makeText(getApplicationContext(),"boundry crosed",Toast.LENGTH_SHORT).show();
            runnable=null;

        }


    }

    private void speak(String number) {
        mainActivityViewModel.speakNumber(String.valueOf(number));
    }


    private void getRandomValueList(){
        ArrayList<Integer> randomList =(ArrayList<Integer>) mainActivityViewModel.getRandomValuesList();
    }

    private void setHousieNumberBeanList(){

        for(int i=1;i<=90;i++){
            housieNumberList.add(i);
        }
    }
}
