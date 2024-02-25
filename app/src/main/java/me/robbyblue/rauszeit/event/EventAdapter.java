package me.robbyblue.rauszeit.event;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.robbyblue.rauszeit.R;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    ArrayList<Event> events;

    public EventAdapter(ArrayList<Event> events) {
        this.events = events;
    }

    @NonNull
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = events.get(position);

        holder.eventName.setText(event.getName());
        holder.eventDescription.setText(event.getDescription());

        holder.view.setOnClickListener((v) -> {
            // TODO: open event
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
