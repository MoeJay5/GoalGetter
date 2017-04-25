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

class RegularAdapter extends RecyclerView.Adapter<RegularAdapter.RegularHolder> {

    private Context context;
    private ArrayList<RegularEvent> regularEvents = new ArrayList<>();

    RegularAdapter(Context context, ArrayList<RegularEvent> list) {
        this.context = context;
        regularEvents = list;
    }

    @Override
    public RegularAdapter.RegularHolder onCreateViewHolder(ViewGroup parent, int viewType) { return new RegularAdapter.RegularHolder(context, LayoutInflater.from(parent.getContext()).inflate(R.layout.regular_row, parent, false)); }

        @Override
        public void onBindViewHolder(RegularAdapter.RegularHolder holder, final int position){
            RegularEvent regularAdapt = regularEvents.get(position);

            holder.getTextView().setText(regularAdapt.getRegularGoal());
            holder.getTextView().setMovementMethod(new ScrollingMovementMethod());
            holder.getButtonDone().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    regularEvents.remove(position);
                    if (MainActivity.regularList.size() <= 0)
                        MainActivity.regularTitle.setVisibility(View.INVISIBLE);
                    notifyDataSetChanged();
                }
            });
            holder.getButtonUpdate().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RegularActivity.regularEditMode = true;
                    RegularActivity.regularPosition = position;
                    Intent intent = new Intent(context , RegularActivity.class);
                    notifyDataSetChanged();
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount () {
            return regularEvents.size();
        }

        class RegularHolder extends RecyclerView.ViewHolder {

            private TextView textView;
            private Button buttonEdit;
            private Button buttonDone;

            RegularHolder(Context context, View itemView){
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.tv_regular);
                buttonDone = (Button) itemView.findViewById(R.id.regularDone);
                buttonEdit = (Button) itemView.findViewById(R.id.regularEdit);
            }

            TextView getTextView() {
                return textView;
            }

            Button getButtonDone() {
                return buttonDone;
            }

            Button getButtonUpdate() {
                return buttonEdit;
            }
    }
}