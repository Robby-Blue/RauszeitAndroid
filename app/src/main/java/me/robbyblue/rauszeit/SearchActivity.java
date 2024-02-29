package me.robbyblue.rauszeit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import me.robbyblue.rauszeit.eventpreview.EventPreviewAdapter;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);

        String query = getIntent().getStringExtra("query");

        RauszeitAPI.getSearchResults(query, (events) -> {
            EventPreviewAdapter adapter = new EventPreviewAdapter(events);
            RecyclerView recycler = findViewById(R.id.searchResultsRecycler);
            recycler.setLayoutManager(new LinearLayoutManager(this));
            recycler.setAdapter(adapter);
        });
    }

}