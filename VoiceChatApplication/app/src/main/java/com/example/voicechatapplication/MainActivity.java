package com.example.voicechatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText Voice_txt;
ImageButton Btn_voice;
Button Button;
    ArrayList<String> text;
private static final int RESULT_SPEECH = 1;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     Voice_txt= (EditText) findViewById(R.id.voice_txt);
     Btn_voice = (ImageButton) findViewById(R.id.btn_voice);
     Button =  (Button) findViewById(R.id.button);

     Btn_voice.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
             intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
             intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"te-IN");
         }
     });

     Button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             String msg=Voice_txt.getText().toString();
             Intent shareIntent=new Intent(Intent.ACTION_SEND);
             shareIntent.putExtra(Intent.EXTRA_TEXT,msg);
             shareIntent.setType("text/plain");
             shareIntent.setPackage("com.whatsapp");
             startActivity(shareIntent);
         }
     });

    }
    @Override
    protected void onActivityResult(int resultCode, int responseCode, Intent data) {

        super.onActivityResult(resultCode, responseCode, data);
        switch (responseCode){
            case RESULT_SPEECH:{

                if(resultCode==RESULT_OK && null!=data){
                    text= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Voice_txt.append(text.get(0)+" ");
                }


            }
        }

    }
}