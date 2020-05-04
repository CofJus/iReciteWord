package com.hhu.ireciteword.ui;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hhu.ireciteword.R;
import com.hhu.ireciteword.httpservice.sentence.Sentence;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.hhu.ireciteword.utils.VoicePlayer.playVoice;

/**
 * 每日一句 Activity
 * @author Ji Rui
 * @date 2020/3/27
 */

public class SentenceActivity extends AppCompatActivity {
    ImageView imgView;
    TextView textEnglish;
    TextView textChinese;
    Button buttonVoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);
        initView();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Sentence st = new Sentence();
                try {
                    downloadPicture(st.getImg());
                    textChinese.setText(st.getChinese());
                    textEnglish.setText(st.getEnglish());
                    //TODO sometimes can't play voice
                    buttonVoice.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                playVoice(st.getTTS());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void initView() {
        imgView = findViewById(R.id.imgViewOkhttp);
        textChinese = findViewById(R.id.Chinese);
        textEnglish = findViewById(R.id.English);
        buttonVoice = findViewById(R.id.Voice);
    }

    /* Update UI */
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            byte[] picture = (byte[]) msg.obj;
            Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);
            //设置图片
            imgView.setImageBitmap(bitmap);
        }
    };

    /**
     * Download Pictures
     */
    public void downloadPicture(String path) {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(path)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                handler.sendEmptyMessage(606);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                byte[] pictureBt = response.body().bytes();
                //通过handler更新UI
                Message message = handler.obtainMessage();
                message.obj = pictureBt;
                message.what = 200;
                handler.sendMessage(message);
            }
        });
    }
}
