package com.example.speechtotext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    static final int Permission_code=1;
    TextView res;
    Button listen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res=findViewById(R.id.stt);
        listen=findViewById(R.id.listen);
        listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO)== PackageManager.PERMISSION_GRANTED){
            Granted();
        }
        else{
            RequestPer();
        }
            }
        });
    }
    public void Granted(){
        Intent speechrecoginizer =new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechrecoginizer.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechrecoginizer.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speechrecoginizer.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak something");
        try {
            startActivityForResult(speechrecoginizer,1);

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK && data!=null){
                    ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    res.setText(result.get(0));
                }
                break;
        }
    }

    public void RequestPer(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},Permission_code);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==Permission_code){
            if(permissions.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED ){
                Granted();
            }
        }

    }
}