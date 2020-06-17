package com.hhu.ireciteword.ui;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.hhu.ireciteword.Dateutil.DateFormatType;
import com.hhu.ireciteword.R;
import com.hhu.ireciteword.data.MyDB;
import com.hhu.ireciteword.data.vo.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.hhu.ireciteword.Dateutil.MyFormat.getTimeStr;
import static com.hhu.ireciteword.Dateutil.MyFormat.myDateFormat;


/**
 *created by 沈思彤 on 2020/5/13
 *
 */
public class NoteMainActivity extends BaseActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    MyDB myDB;
    private ListView myListView;
    private Button createButton;
    private MyBaseAdapter myBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_main);
        ImageButton btnBack5=(ImageButton)findViewById(R.id.bback);
        btnBack5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
    }

    //初始化控件
    private void init(){
        createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(this);

        myListView = findViewById(R.id.list_view);

        List<Record> noteRecordList = new ArrayList<>();
        myDB = new MyDB(this);
        SQLiteDatabase db = myDB.getReadableDatabase();
        Cursor cursor = db.query(MyDB.TABLE_NAME_RECORD,null,
                null,null,null,
                null, MyDB.NOTICE_TIME+","+ MyDB.RECORD_TIME+" DESC");
        if(cursor.moveToFirst()){
            Record noteRecord;
            while (!cursor.isAfterLast()){
                noteRecord = new  Record();
                noteRecord.setId(
                        Integer.valueOf(cursor.getString(cursor.getColumnIndex(MyDB.RECORD_ID))));
                noteRecord.setTitleName(
                        cursor.getString(cursor.getColumnIndex(MyDB.RECORD_TITLE))
                );
                noteRecord.setTextBody(
                        cursor.getString(cursor.getColumnIndex(MyDB.RECORD_BODY))
                );
                noteRecord.setCreateTime(
                        cursor.getString(cursor.getColumnIndex(MyDB.RECORD_TIME)));

                noteRecordList.add(noteRecord);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        // 创建一个Adapter的实例
        myBaseAdapter = new MyBaseAdapter(this, noteRecordList, R.layout.activity_note_list_item);
        myListView.setAdapter(myBaseAdapter);
        // 设置点击监听
        myListView.setOnItemClickListener(this);
        myListView.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createButton:
                Intent intent = new Intent(NoteMainActivity.this, EditActivity.class);
                startActivity(intent);
                NoteMainActivity.this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(NoteMainActivity.this, AmendActivity.class);
        Record noteRecord = ( Record) myListView.getItemAtPosition(position);
        intent.putExtra(MyDB.RECORD_TITLE, noteRecord.getTitleName().trim());
        intent.putExtra(MyDB.RECORD_BODY, noteRecord.getTextBody().trim());
        intent.putExtra(MyDB.RECORD_TIME, noteRecord.getCreateTime().trim());
        intent.putExtra(MyDB.RECORD_ID, noteRecord.getId().toString().trim());

        this.startActivity(intent);
        NoteMainActivity.this.finish();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Record noteRecord = ( Record) myListView.getItemAtPosition(position);
        showDialog(noteRecord,position);
        return true;
    }

    void showDialog(final  Record noteRecord, final int position){

        final AlertDialog.Builder dialog =
                new AlertDialog.Builder(NoteMainActivity.this);
        dialog.setTitle("是否删除？");
        String textBody = noteRecord.getTextBody();
        dialog.setMessage(
                textBody.length()>150?textBody.substring(0,150)+"...":textBody);
        dialog.setPositiveButton("删除",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = myDB.getWritableDatabase();
                            db.delete(MyDB.TABLE_NAME_RECORD,
                                MyDB.RECORD_ID +"=?",
                                new String[]{String.valueOf(noteRecord.getId())});
                        db.close();
                        myBaseAdapter.removeItem(position);
                        myListView.post(new Runnable() {
                            @Override
                            public void run() {
                                myBaseAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
        dialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        dialog.show();
    }



    /**
     * ListView展示的适配器类
     */
    class MyBaseAdapter extends BaseAdapter {
        private List< Record> noteRecordList;//数据集合
        private Context context;
        private int layoutId;

        public MyBaseAdapter(Context context, List< Record> noteRecordList, int layoutId){
            this.context = context;
            this.noteRecordList = noteRecordList;
            this.layoutId = layoutId;
        }

        @Override
        public int getCount() {
            if (noteRecordList !=null&& noteRecordList.size()>0)
                return noteRecordList.size();
            else
                return 0;
        }

        @Override
        public Object getItem(int position) {
            if (noteRecordList !=null&& noteRecordList.size()>0)
                return noteRecordList.get(position);
            else
                return null;
        }

        public void removeItem(int position){
            this.noteRecordList.remove(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(
                        getApplicationContext()).inflate(R.layout.activity_note_list_item, parent,
                        false);
                viewHolder  = new ViewHolder();
                viewHolder.titleView = convertView.findViewById(R.id.list_item_title);
                viewHolder.bodyView = convertView.findViewById(R.id.list_item_body);
                viewHolder.timeView = convertView.findViewById(R.id.list_item_time);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Record noteRecord = noteRecordList.get(position);
            String tile = noteRecord.getTitleName();
            viewHolder.titleView.setText((position+1)+"."+(tile.length()>7?tile.substring(0,7)+"...":tile));
//            viewHolder.titleView.setText(tile);
            String body = noteRecord.getTextBody();
            viewHolder.bodyView.setText(body.length()>13?body.substring(0,12)+"...":body);
//            viewHolder.bodyView.setText(body);
            String createTime = noteRecord.getCreateTime();
            Date date = myDateFormat(createTime, DateFormatType.NORMAL_TIME);
            viewHolder.timeView.setText(getTimeStr(date));
            return convertView;
        }
    }

    /**
     * ListView里的组件包装类
     */
    class ViewHolder{
        TextView titleView;
        TextView bodyView;
        TextView timeView;
    }

}

