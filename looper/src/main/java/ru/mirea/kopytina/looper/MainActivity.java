package ru.mirea.kopytina.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import java.util.concurrent.TimeUnit;

import ru.mirea.kopytina.looper.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {

                Log.d(MainActivity.class.getSimpleName(), "Task execute. This is result: " + msg.getData().getString("result"));
            }
        };
        MyLooper myLooper = new MyLooper(mainThreadHandler);
        myLooper.start();
        binding.editTextAge.setText("Введите возраст");
        binding.editTextWork.setText("Профессия: ");
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = Integer.parseInt(String.valueOf(binding.editTextAge.getText()));
                String work = String.valueOf(binding.editTextWork.getText());
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("KEY", work);
                try {
                    TimeUnit.SECONDS.sleep(age);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                msg.setData(bundle);
                myLooper.mHandler.sendMessage(msg);
            };
        });
    }
}