package me.robbyblue.rauszeit;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.URLSpan;
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
            ((TextView) findViewById(R.id.eventDate)).setText(event.getDate());
            ((TextView) findViewById(R.id.eventTime)).setText(event.getTime());
            ((TextView) findViewById(R.id.eventDescription)).setText(event.getDescription());

            TextView locationView = findViewById(R.id.eventLocation);
            String locationName = event.getLocationName();
            SpannableStringBuilder locationSpan = new SpannableStringBuilder(locationName);
            locationSpan.setSpan(new URLSpan(""), 0, locationName.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
            locationView.setText(locationSpan);

            locationView.setOnClickListener((e) -> {
                // TODO: open activity about the location
            });
        });
    }

}