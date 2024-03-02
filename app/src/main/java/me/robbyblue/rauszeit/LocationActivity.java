package me.robbyblue.rauszeit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.URLSpan;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        String url = getIntent().getStringExtra("link");

        RauszeitAPI.getLocation(url, (location) -> {
            String addressString = location.getExactAddress() + "\n" + location.getGeneralAddress();

            SpannableStringBuilder addressSpan = new SpannableStringBuilder(addressString);
            addressSpan.setSpan(new URLSpan(""), 0, addressString.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

            ((TextView) findViewById(R.id.locationName)).setText(location.getName());
            ((TextView) findViewById(R.id.locationAddress)).setText(addressSpan);
            ((TextView) findViewById(R.id.locationDescription)).setText(location.getDescription());

            findViewById(R.id.locationAddress).setOnClickListener((e) -> {
                // create and uri encode addr
                String address = location.getExactAddress() + " " + location.getGeneralAddress();
                address = Uri.encode(address);

                // try to open it in app
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + address);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(mapIntent);
            });
        });
    }

}