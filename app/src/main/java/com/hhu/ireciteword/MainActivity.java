package com.hhu.ireciteword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.hhu.ireciteword.ui.Dakachallenge_back;
import com.hhu.ireciteword.ui.HeaderActivity;
import com.hhu.ireciteword.ui.HelpActivity;
import com.hhu.ireciteword.ui.ImportWordBookActivity;
import com.hhu.ireciteword.ui.LearningSpeedActivity;
import com.hhu.ireciteword.ui.ListwordBackHome;
import com.hhu.ireciteword.ui.LockScreenWordsActivity;
import com.hhu.ireciteword.ui.MyPagerAdapter;
import com.hhu.ireciteword.ui.NoticeActivity;
import com.hhu.ireciteword.ui.SentenceActivity;
import com.hhu.ireciteword.ui.SettingActivity;
import com.hhu.ireciteword.ui.WordBookActivity;
import com.hhu.ireciteword.ui.Word_recite1;

import java.util.ArrayList;
import java.util.List;

/*
 * 石倍瑜 2020/4/16：
 * class OnClick()
 * private void setListeners()
 *
 * 李雪滢 2020/4/17
 * viewPager.setCurrentItem(0);
 * ViewPager
 */

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    List<View> lsViews = new ArrayList<>();//声明ViewPager需要使用的View对象
    MyPagerAdapter pagerAdapter;//声明适配器对象
    RadioButton rbChat, rbDiscover, rbMe;
    RadioGroup radioGroup;

    private LinearLayout llNotice;         //通知
    private LinearLayout llWordBook;       //单词书
    private LinearLayout llNewWordList;    //生词本
    private LinearLayout llLearningSpeed;  //学习进度
    private LinearLayout llImport;         //导入单词书
    private LinearLayout llLockScreenWord;  //锁屏单词
    private LinearLayout llHelp;            //帮助与反馈
    private LinearLayout llSetting;         //设置
    private ImageView llHeader;           //头像
    private LinearLayout llDailyAttendance;     //打卡

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, "目前在主活动中", Toast.LENGTH_LONG).show();


        //Initialise Views
        viewPager = (ViewPager) findViewById(R.id.main_vp);
        radioGroup = (RadioGroup) findViewById(R.id.main_rg);
        rbChat = (RadioButton) findViewById(R.id.rbHome);
        rbDiscover = (RadioButton) findViewById(R.id.rbDiscover);
        rbMe = (RadioButton) findViewById(R.id.rbMe);
        rbChat.setChecked(true);

        View view1 = getLayoutInflater().inflate(R.layout.page_me, null, false);

        lsViews.add(getLayoutInflater().inflate(R.layout.page_home, null, false));
        lsViews.add(getLayoutInflater().inflate(R.layout.page_discover, null, false));
        lsViews.add(view1);


        lsViews.add(getLayoutInflater().inflate(R.layout.page_home, null, false));
        lsViews.add(getLayoutInflater().inflate(R.layout.page_discover, null, false));
        lsViews.add(getLayoutInflater().inflate(R.layout.page_me, null, false));
        pagerAdapter = new MyPagerAdapter(lsViews);
        viewPager.setAdapter(pagerAdapter);


        llNotice = view1.findViewById(R.id.ll_notice);
        llWordBook = view1.findViewById(R.id.ll_word_book);
        llHeader = view1.findViewById(R.id.iv_header);
        llDailyAttendance = view1.findViewById(R.id.ll_daily_attendance);
        llNewWordList = view1.findViewById(R.id.ll_new_word_list);
        llLearningSpeed = view1.findViewById(R.id.ll_learning_speed);
        llImport = view1.findViewById(R.id.ll_import);
        llLockScreenWord = view1.findViewById(R.id.ll_Lock);
        llHelp = view1.findViewById(R.id.ll_help);
        llSetting = view1.findViewById(R.id.ll_setting);


        setListeners();      //设置点击事件的方法


        pagerAdapter = new MyPagerAdapter(lsViews);
        viewPager.setAdapter(pagerAdapter);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (positionOffsetPixels == 0) {
                    //Do something on selected page at position
                    //位于第一页时，单词列表，实现监听事件
                    Button button_wordlist = (Button) findViewById(R.id.button_wordlist);
                    button_wordlist.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent it = new Intent(MainActivity.this, ListwordBackHome.class);
                            startActivity(it);
                            Toast.makeText(MainActivity.this, "进入单词界面", Toast.LENGTH_LONG).show();

                        }
                    });
                    //开始背单词
                    Button btnStart = (Button) findViewById(R.id.start_word_resite);//主页的开始背单词按钮
                    btnStart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent it = new Intent(MainActivity.this, Word_recite1.class);
                            startActivity(it);
                            Toast.makeText(MainActivity.this, "进入背单词界面", Toast.LENGTH_LONG).show();
                        }
                    });
                    //打卡日历
                    Button btnCalendar = (Button) findViewById(R.id.calendar);
                    btnCalendar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent it = new Intent(MainActivity.this, com.hhu.ireciteword.ui.Calendar.class);
                            startActivity(it);
                            Toast.makeText(MainActivity.this, "进入打卡日历", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            //选中ViewPage页的时候触发该事件
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0://Chats
                        rbChat.setChecked(true);
                        break;
                    case 1://Discover
                        rbDiscover.setChecked(true);
                        break;
                    case 2://Me
                        rbMe.setChecked(true);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //设置Radio选中的时候切换ViewPager
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rbHome:
                        viewPager.setCurrentItem(0);

                        break;
                    case R.id.rbDiscover:
                        viewPager.setCurrentItem(1);
                        //位于第二页时，每日一句，实现监听
                        Button everydayword = (Button) findViewById(R.id.everydayword);
                        everydayword.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(MainActivity.this, SentenceActivity.class);
                                startActivity(it);
                                Toast.makeText(MainActivity.this, "进入每日一句界面", Toast.LENGTH_LONG).show();

                            }
                        });
                        Button dakachallenge = (Button) findViewById(R.id.dakachallenge);
                        dakachallenge.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(MainActivity.this, Dakachallenge_back.class);
                                startActivity(it);
                                Toast.makeText(MainActivity.this, "进入打卡挑战界面", Toast.LENGTH_LONG).show();
                            }
                        });


                        break;
                    case R.id.rbMe:
                        viewPager.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void setListeners() {              //实现点击事件的方法
        OnClick onClick = new OnClick();
        llNotice.setOnClickListener(onClick);
        llWordBook.setOnClickListener(onClick);
        llHeader.setOnClickListener(onClick);
        llDailyAttendance.setOnClickListener(onClick);
        llSetting.setOnClickListener(onClick);
        llLearningSpeed.setOnClickListener(onClick);
        llImport.setOnClickListener(onClick);
        llHelp.setOnClickListener(onClick);
        llLockScreenWord.setOnClickListener(onClick);
        llNewWordList.setOnClickListener(onClick);
    }


    private class OnClick implements View.OnClickListener {      //点击事件类实现OnClickListener接口

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {

                case R.id.ll_notice:
                    //跳转到通知页面，下同
                    intent = new Intent(MainActivity.this, NoticeActivity.class);
                    intent.putExtra("content", "通知");
                    break;
                case R.id.ll_word_book:
                    intent = new Intent(MainActivity.this, WordBookActivity.class);
                    intent.putExtra("content", "单词书");
                    break;
                case R.id.iv_header:
                    intent = new Intent(MainActivity.this, HeaderActivity.class);
                    intent.putExtra("content", "头像");
                    break;
                case R.id.ll_daily_attendance:
                    intent = new Intent(MainActivity.this, com.hhu.ireciteword.ui.Calendar.class);
                    intent.putExtra("content", "打卡");
                    break;
                case R.id.ll_new_word_list:
                    intent = new Intent(MainActivity.this, ListwordBackHome.class);
                    intent.putExtra("content", "生词本");
                    break;
                case R.id.ll_learning_speed:
                    intent = new Intent(MainActivity.this, LearningSpeedActivity.class);
                    intent.putExtra("content", "学习进度");
                    break;
                case R.id.ll_import:
                    intent = new Intent(MainActivity.this, ImportWordBookActivity.class);
                    intent.putExtra("content", "导入单词书");
                    break;
                case R.id.ll_Lock:
                    intent = new Intent(MainActivity.this, LockScreenWordsActivity.class);
                    intent.putExtra("content", "锁屏单词");
                    break;
                case R.id.ll_help:
                    intent = new Intent(MainActivity.this, HelpActivity.class);
                    intent.putExtra("content", "帮助与反馈");
                    break;
                case R.id.ll_setting:
                    intent = new Intent(MainActivity.this, SettingActivity.class);
                    intent.putExtra("content", "设置");
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    }
}
