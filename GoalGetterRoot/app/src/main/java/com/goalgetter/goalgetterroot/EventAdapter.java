package com.goalgetter.goalgetterroot;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    private Context context;
    private ArrayList<EventEvent> eventEvents = new ArrayList<>();

    EventAdapter(Context context, ArrayList<EventEvent> list) {
        this.context = context;
        eventEvents = list;
    }

    @Override
    public EventAdapter.EventHolder onCreateViewHolder(ViewGroup parent, int viewType) { return new EventAdapter.EventHolder(context, LayoutInflater.from(parent.getContext()).inflate(R.layout.event_row, parent, false));}

    @Override
    public void onBindViewHolder(EventAdapter.EventHolder holder, final int position){
        EventEvent eventAdapt = eventEvents.get(position);

        String eventName = eventAdapt.getEventName();
        String eventGoal = eventAdapt.getEventGoal();
        int daysRemaining = eventAdapt.getEventDays();

        holder.getTextView().setText(eventGoal + " for " + eventName + " with " + daysRemaining + " Days left.");
        holder.getTextView().setMovementMethod(new ScrollingMovementMethod());
        holder.getButtonDone().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventEvents.remove(position);
                if (MainActivity.eventList.size() <= 0)
                    MainActivity.eventTitle.setVisibility(View.INVISIBLE);
                notifyDataSetChanged();
            }
        });
        holder.getButtonUpdate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventActivity.eventEditMode = true;
                EventActivity.eventPosition = position;
                Intent intent = new Intent(context , EventActivity.class);
                notifyDataSetChanged();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount () { return eventEvents.size(); }

    class EventHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private Button buttonEdit;
        private Button buttonDone;

        EventHolder(Context context, View itemView){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_event);
            buttonDone = (Button) itemView.findViewById(R.id.eventDone);
            buttonEdit = (Button) itemView.findViewById(R.id.eventEdit);
        }

        TextView getTextView() { return textView; }

        Button getButtonDone() { return buttonDone; }

        Button getButtonUpdate() { return buttonEdit; }
    }
}