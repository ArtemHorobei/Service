package com.example.microsoft.service.UI;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.microsoft.service.R;
import com.example.microsoft.service.Constants;
import com.example.microsoft.service.UI.Adapter.MyRecyclerAdapter;
import com.example.microsoft.service.utils.Services.MyService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSend, btnClear, startService, stopService; //создаем поля класса кнопки
    private TextView tv; //создаем поле класса текствью для отображения текста
    private NotificationManager manager; //менеджер для уведомлений
    private Notification myNotication; //само уведомление
    private RecyclerView recyclerView; //ресайклервью для отображения айтемов
    private ProgressBar progressBar;
    private BroadcastReceiver mReceiver; //компонент нужен для отображения, что программа не зависла, а выполняет продолжительную работу.
    private MyRecyclerAdapter mAdapter; //наш адаптер
    private RecyclerView.LayoutManager mLayoutManager; //для управления позиционирования
    public final static String FILE_NAME = "filename";
    private RelativeLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialise();
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
    private void initialise(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        startService = (Button) findViewById(R.id.btn_start_service);
        startService.setOnClickListener(this);
        btnClear = (Button) findViewById(R.id.btn_clear_notif);
        btnClear.setOnClickListener(this);
        view = (RelativeLayout) findViewById(R.id.main_activity);
        Intent intent = getIntent();
        String fileName = intent.getStringExtra(FILE_NAME);
        if (!TextUtils.isEmpty(fileName)) {
            tv.setText(fileName);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_clear_notif:
                manager.cancel(1);
                break;
            case R.id.btn_start_service:
                startService(new Intent(this, MyService.class));
                mReceiver = getReceiver();
                registerReceiver(mReceiver, new IntentFilter(Constants.BROADCAST_ACTION));
                startService.setVisibility(View.GONE);
                btnClear.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
//                finish();
                break;
            default:
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void onStop() {
        unregisterReceiver(mReceiver);
        super.onStop();
    }

    private BroadcastReceiver getReceiver() {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                progressBar.setVisibility(View.GONE);
//                btnClear.setVisibility(View.GONE);
                view.setBackgroundResource(R.drawable.page);
                ArrayList arrayList = intent.getIntegerArrayListExtra(Constants.ATTR_IMAGES);
                mAdapter = new MyRecyclerAdapter(arrayList, MainActivity.this);
                recyclerView.setAdapter(mAdapter);
            }
        };
    }
}
