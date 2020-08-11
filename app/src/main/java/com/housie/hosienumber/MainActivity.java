package com.housie.hosienumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MainActivityViewModel mainActivityViewModel;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mainActivityViewModel=new MainActivityViewModel();
        button= findViewById(R.id.bt_random);
        button.setOnClickListener(this);
        textView=findViewById(R.id.textView);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.bt_random)
        {
            getRandomNumber();
        }
    }

    private void getRandomNumber() {


        int number =mainActivityViewModel.getRandomNumber();

        if (number!=-1)
        {
            textView.setText(String.valueOf(number));
        }
      else
        {
            Toast.makeText(getApplicationContext(),"boundry crosed",Toast.LENGTH_SHORT).show();
        }


    }
}
