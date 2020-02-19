package com.example.servicetest2;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    MusicQueue musicQueue = new MusicQueue();
    private MusicService.MusicBinder musicBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicBinder = (MusicService.MusicBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button qifengle = (Button) findViewById(R.id.qifengle);
        Button qianli = (Button) findViewById(R.id.qianli);
        Button bofangqi = (Button) findViewById(R.id.xiangqing);

        qifengle.setOnClickListener(this);
        qianli.setOnClickListener(this);
        bofangqi.setOnClickListener(this);

        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        bindService(intent, connection, BIND_AUTO_CREATE);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    musicBinder.initmediaplayer(0);
                } else {
                    Toast.makeText(this, "拒绝将导致程序无法使用", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.qifengle:
                musicBinder.stop();
                File file = new File(Environment.getExternalStorageDirectory(),"起风了.mp3");
                MusicQueue.Music qifengle = new MusicQueue.Music("起风了","吴青峰", file.getPath());
                if (!musicQueue.isinclude(qifengle.musicname)){
                    MusicQueue.queue.addElement(qifengle);
                }
                MusicQueue.i = musicQueue.getindex(qifengle.musicname);
                musicBinder.initmediaplayer(MusicQueue.i);
                musicBinder.play();
                break;
            case R.id.qianli:
                musicBinder.stop();
                File file1 = new File(Environment.getExternalStorageDirectory(),"千里行走.mp3");
                MusicQueue.Music qianli = new MusicQueue.Music("千里行走", "暗杠、寅子", file1.getPath());
                if (!musicQueue.isinclude(qianli.musicname)){
                    MusicQueue.queue.addElement(qianli);
                }
                MusicQueue.i = musicQueue.getindex(qianli.musicname);
                musicBinder.initmediaplayer(MusicQueue.i);
                musicBinder.play();
                break;
            case R.id.xiangqing:
                Intent intent1 = new Intent(MainActivity.this, player.class);
                startActivity(intent1);
                break;
        }
    }
}
