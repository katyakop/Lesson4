package ru.mirea.kopytina.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import ru.mirea.kopytina.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Runnable runn1 = new Runnable() {
            public void run() {
                Log.d("runn1", "runn1");
                binding.tvInfo.setText("runn1");

            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                Log.d("runn2", "runn2");
                binding.tvInfo.setText("runn2");

            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                Log.d("runn3", "runn3");
                binding.tvInfo.setText("runn3");

            }
        };

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.tvInfo.postDelayed(runn3, 10000);


                    binding.tvInfo.post(runn2);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}