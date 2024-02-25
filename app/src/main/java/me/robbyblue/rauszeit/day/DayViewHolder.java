package me.robbyblue.rauszeit.day;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import me.robbyblue.rauszeit.R;

public class DayViewHolder extends RecyclerView.ViewHolder {

    View view;
    TextView dateTextView;
    RecyclerView eventsRecycler;

    public DayViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        dateTextView = itemView.findViewById(R.id.dayDate);
        eventsRecycler = itemView.findViewById(R.id.dayEventsRecycler);
    }

}
