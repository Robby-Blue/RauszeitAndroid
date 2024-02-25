package me.robbyblue.rauszeit.eventpreview;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import me.robbyblue.rauszeit.R;

public class EventPreviewViewHolder extends RecyclerView.ViewHolder {

    View view;
    TextView eventName;
    TextView eventDescription;

    public EventPreviewViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        eventName = itemView.findViewById(R.id.eventName);
        eventDescription = itemView.findViewById(R.id.eventDescription);
    }

}
