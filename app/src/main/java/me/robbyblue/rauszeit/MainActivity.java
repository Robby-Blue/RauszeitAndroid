package me.robbyblue.rauszeit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import me.robbyblue.rauszeit.day.DayAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RauszeitAPI.getAllEvents((days) -> {
            DayAdapter adapter = new DayAdapter(days);
            RecyclerView recycler = findViewById(R.id.daysRecycler);
            recycler.setLayoutManager(new LinearLayoutManager(this));
            recycler.setAdapter(adapter);
        });
    }

}