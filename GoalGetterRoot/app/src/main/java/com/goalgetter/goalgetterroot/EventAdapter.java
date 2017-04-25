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

/**
 * Created by moeja on 4/17/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    private String eventName;
    private String eventGoal;
    private int daysRemaining;
    Context context;
    ArrayList<EventEvent> eventevent = new ArrayList<>();

    public EventAdapter(Context context, ArrayList<EventEvent> list) {
        this.context = context;
        eventevent = list;
    }

    @Override
    public EventAdapter.EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventAdapter.EventHolder(context, LayoutInflater.from(parent.getContext()).inflate(R.layout.event_row, parent, false));
    }
//    }
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
////        View row = inflater.inflate(R.layout.event_row, parent, false);
////        Item item = new Item(row);
////        return item;
//    }

        @Override
        public void onBindViewHolder(EventAdapter.EventHolder holder, final int position){
            EventEvent eventAdapt = eventevent.get(position);

            eventName = eventAdapt.getEventName();
            eventGoal = eventAdapt.getEventGoal();
            daysRemaining = eventAdapt.getEventDays();//

            holder.getTextView().setText(eventGoal + " for " + eventName + " with " + daysRemaining + " Days left.");
            holder.getTextView().setMovementMethod(new ScrollingMovementMethod());
            holder.getButtonDone().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventevent.remove(position);
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
        public int getItemCount () {
            return eventevent.size();
        }
//    public class Item extends RecyclerView.ViewHolder {
//        TextView textView;
//        public Item(View itemView) {
//            super(itemView);
//            textView = (TextView) itemView.findViewById(R.id.textViewEventRow);
//        }
//    }
        public class EventHolder extends RecyclerView.ViewHolder {

            private TextView textView;
            private Button buttonEdit;
            private Button buttonDone;

          public EventHolder(Context context, View itemView){
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.tv_event);
                buttonDone = (Button) itemView.findViewById(R.id.eventDone);
                buttonEdit = (Button) itemView.findViewById(R.id.eventEdit);
            }

            public TextView getTextView() {
                return textView;
            }

            public Button getButtonDone() {
                return buttonDone;
            }

            public Button getButtonUpdate() {
                return buttonEdit;
            }
    }
}