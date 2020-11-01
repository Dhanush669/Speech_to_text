package com.example.speechtotext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    static final int Permission_code=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)== PackageManager.PERMISSION_GRANTED){
            Granted();
        }
        else{
            RequestPer();
        }
    }
    public void Granted(){
        Intent speechrecog=new Intent();

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