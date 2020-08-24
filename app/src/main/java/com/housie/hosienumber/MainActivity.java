package com.housie.hosienumber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, Utils.ConfirmationDialogListner {

    MainActivityViewModel mainActivityViewModel;
    TextView textView;
    GridView gridView;
    Button bt_random;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch swich_sound, switch_ToAutoOrManual, switch_board;
    Handler handler;
    boolean isAuto =true, isMute =true,isfirst=true;



    private ArrayList<Integer> housieNumberList = new ArrayList<>();
    HousieNumbersGridAdapter housieNumbersGridAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            pickRandomNumber();
            pickAutoRamdomNumber();
        }
    };


    private void pickAutoRamdomNumber() {

        handler.postDelayed(runnable,3000);
    }


    private void stopAutoRandom()
    {
        handler.removeCallbacks(runnable);
    }
    private void init() {
        mainActivityViewModel=new MainActivityViewModel();
        alertDialog=new AlertDialog.Builder(this).create();
        bt_random = findViewById(R.id.bt_random);
        switch_board = findViewById(R.id.switch_hideGrid);
        switch_board.setOnCheckedChangeListener(this);
        switch_ToAutoOrManual = findViewById(R.id.switch_Auto);

        bt_random.setOnClickListener(this);

        switch_ToAutoOrManual.setOnCheckedChangeListener(this);

        textView=findViewById(R.id.textView);
        gridView = findViewById(R.id.gridView_RandomValuesList);

        swich_sound = findViewById(R.id.switch_sound);
        swich_sound.setOnCheckedChangeListener(this);
        setHousieNumberBeanList();
        housieNumbersGridAdapter=new HousieNumbersGridAdapter(this,housieNumberList);
        gridView.setAdapter(housieNumbersGridAdapter);
        handler=new Handler();

        findViewById(R.id.iv_restart).setOnClickListener(this);

        setbt_random();
        setbt_board();
        setbt_sound();
        setbt_auto();

        speak("I am Ready !");
    }

    private void setbt_auto() {
        switch_ToAutoOrManual.setChecked(true);
    }

    private void setbt_sound() {
        swich_sound.setChecked(true);
    }

    private void setbt_board() {
        switch_board.setChecked(true);
    }


    AlertDialog alertDialog;
    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.bt_random )
        {
            pickRandomNumber();
            pauseGenarateButton();

        }else if (view.getId()==R.id.iv_restart)
        {
            handler.removeCallbacks(runnable);
            Utils.takeConfirmation(alertDialog,"Do you Want Start Again",this);
        }

    }

    private void setbt_random() {
        bt_random.setEnabled(!isAuto);
    }

    private void pauseGenarateButton() {

        findViewById(R.id.bt_random).setEnabled(false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.bt_random).setEnabled(true);

            }
        },1000);

    }

    private void pickRandomNumber() {
        int number =mainActivityViewModel.getRandomNumber();

        if (number!=-1)
        {
            textView.setText(String.valueOf(number));
            if(!isMute)
            speak(String.valueOf(number));
            updateTable();
        }
      else
        {
            Toast.makeText(getApplicationContext(),"boundry crosed",Toast.LENGTH_SHORT).show();
            runnable=null;

        }


    }

    private void updateTable() {
        housieNumbersGridAdapter.setRandomPickedlist(mainActivityViewModel.getRandomValuesList());

    }

    private void speak(String number) {
        if (!isMute)
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


    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isfirst && isAuto)
            pickAutoRamdomNumber();
        else
            isfirst=false;

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton.getId()==R.id.switch_Auto)
        {
            isAuto=b;
            setbt_random();

            if(isAuto) {
                pickAutoRamdomNumber();
            }
            else{
                Log.e("TAG", "onClick: IN else");
                stopAutoRandom();
            }

            speak(isAuto ? "System Pick Coins For You" :"Pick coins yourself");

        }else if (compoundButton.getId()==R.id.switch_sound)
        {
            isMute=!b;
            speak(isMute ? "muted" :"unmuted");


        }else if (compoundButton.getId()==R.id.switch_hideGrid)
        {
            gridView.setVisibility(switch_board.isChecked() ? View.VISIBLE :View.INVISIBLE);
        }
    }

    @Override
    public void onclickYes() {
        alertDialog.dismiss();

        onrestarts();
    }

    private void onrestarts() {

        mainActivityViewModel.restart();
        textView.setText("");
        updateTable();

        if (isAuto)
        pickAutoRamdomNumber();

    }

    @Override
    public void onclickNo() {
        alertDialog.dismiss();
        if (isAuto)
            pickAutoRamdomNumber();
    }
}
