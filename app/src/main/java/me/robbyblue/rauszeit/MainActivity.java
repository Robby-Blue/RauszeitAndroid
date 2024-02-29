package me.robbyblue.rauszeit;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

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

        EditText searchBar = findViewById(R.id.searchBar);
        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Intent intent = new Intent().setClass(this, SearchActivity.class);
                intent.putExtra("query", searchBar.getText().toString());
                startActivity(intent);
                return true;
            }
            return false;
        });

        searchBar.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            // clears text and focus after closed
            if (oldTop < top) {
                searchBar.clearFocus();
                searchBar.setText("");
            }
        });
    }

}