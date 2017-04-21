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

public class RegularAdapter extends RecyclerView.Adapter<RegularAdapter.RegularHolder> {

    Context context;
    ArrayList<RegularEvent> regularevent = new ArrayList<>();

    public RegularAdapter(Context context, ArrayList<RegularEvent> list) {
        this.context = context;
        regularevent = list;
    }

    @Override
    public RegularAdapter.RegularHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RegularAdapter.RegularHolder(context, LayoutInflater.from(parent.getContext()).inflate(R.layout.regular_row, parent, false));
    }
//    }
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
////        View row = inflater.inflate(R.layout.regular_row, parent, false);
////        Item item = new Item(row);
////        return item;
//    }

        @Override
        public void onBindViewHolder(RegularAdapter.RegularHolder holder, final int position){
            RegularEvent regularAdapt = regularevent.get(position);
            RegularEventActivity.regularPosition = position;

            holder.getTextView().setText(regularAdapt.getRegularGoal());
            holder.getTextView().setMovementMethod(new ScrollingMovementMethod());
            holder.getButtonDone().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    regularevent.remove(position);
                    notifyDataSetChanged();
                }
            });
            holder.getButtonUpdate().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RegularEventActivity.editMode = true;
                    Intent intent = new Intent(context , RegularEventActivity.class);
                    notifyDataSetChanged();
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount () {
            return regularevent.size();
        }
//    public class Item extends RecyclerView.ViewHolder {
//        TextView textView;
//        public Item(View itemView) {
//            super(itemView);
//            textView = (TextView) itemView.findViewById(R.id.textViewRegularRow);
//        }
//    }
        public class RegularHolder extends RecyclerView.ViewHolder {

            private TextView textView;
            private Button buttonEdit;
            private Button buttonDone;

          public RegularHolder(Context context, View itemView){
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.tv_regular);
                buttonDone = (Button) itemView.findViewById(R.id.regularDone);
                buttonEdit = (Button) itemView.findViewById(R.id.regularEdit);
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