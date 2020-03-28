package com.hhu.ireciteword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hhu.ireciteword.httpservice.TranslateService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Start TranslateService */
        findViewById(R.id.btnStartService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this,com.hhu.ireciteword.httpservice.TranslateService.class));
            }
        });

        /* Stop TranslateService */
        findViewById(R.id.btnStopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this,com.hhu.ireciteword.httpservice.TranslateService.class));
            }
        });
    }

}
