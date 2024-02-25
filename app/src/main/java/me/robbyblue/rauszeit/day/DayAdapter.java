package me.robbyblue.rauszeit.day;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.robbyblue.rauszeit.R;
import me.robbyblue.rauszeit.eventpreview.EventPreviewAdapter;

public class DayAdapter extends RecyclerView.Adapter<DayViewHolder> {

    ArrayList<Day> days;

    public DayAdapter(ArrayList<Day> days){
        this.days = days;
    }

    @NonNull
    public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DayViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        Day day = days.get(position);

        holder.dateTextView.setText(day.getDate());

        EventPreviewAdapter adapter = new EventPreviewAdapter(day.getEvents());
        RecyclerView recycler = holder.eventsRecycler;
        recycler.setLayoutManager(new LinearLayoutManager(holder.eventsRecycler.getContext()));
        recycler.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return days.size();
    }
}
