package com.hhu.ireciteword.httpservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.hhu.ireciteword.R;
import com.hhu.ireciteword.utils.ParseJSON;

import org.json.JSONException;

import static com.hhu.ireciteword.httpservice.Translate.translateResult;
import static com.hhu.ireciteword.utils.ParseJSON.parseJSON;

public class TranslateService extends Service {
    public TranslateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            /* Translate线程 */
            @Override
            public void run() {
                String res="FUCK!";
                try {
                    res = translateResult("ass");
                    //暂时输出到控制台
                    System.out.println(res);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }
}
