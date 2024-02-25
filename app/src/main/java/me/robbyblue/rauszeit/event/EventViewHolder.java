package me.robbyblue.rauszeit.event;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import me.robbyblue.rauszeit.R;

public class EventViewHolder extends RecyclerView.ViewHolder {

    View view;
    TextView eventName;
    TextView eventDescription;

    public EventViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        eventName = itemView.findViewById(R.id.eventName);
        eventDescription = itemView.findViewById(R.id.eventDescription);
    }

}
