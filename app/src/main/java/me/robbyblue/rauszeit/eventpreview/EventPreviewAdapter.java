package me.robbyblue.rauszeit.eventpreview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.robbyblue.rauszeit.EventActivity;
import me.robbyblue.rauszeit.R;

public class EventPreviewAdapter extends RecyclerView.Adapter<EventPreviewViewHolder> {

    ArrayList<EventPreview> events;

    public EventPreviewAdapter(ArrayList<EventPreview> events) {
        this.events = events;
    }

    @NonNull
    public EventPreviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventPreviewViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_eventpreview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventPreviewViewHolder holder, int position) {
        EventPreview event = events.get(position);

        holder.eventName.setText(event.getName());
        holder.eventDescription.setText(event.getDescription());

        holder.view.setOnClickListener((v) -> {
            Context context = v.getContext();
            context.startActivity(new Intent().setClass(context, EventActivity.class).putExtra("link", event.getLink()));
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
