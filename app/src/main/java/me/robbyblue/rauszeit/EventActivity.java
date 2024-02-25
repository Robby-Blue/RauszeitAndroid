package me.robbyblue.rauszeit;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        String url = getIntent().getStringExtra("link");

        RauszeitAPI.getEvent(url, (event) -> {
            ((TextView) findViewById(R.id.eventName)).setText(event.getName());
            ((TextView) findViewById(R.id.eventDescription)).setText(event.getDescription());
        });
    }

}