package com.goalgetter.goalgetterroot;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.method.BaseMovementMethod;
import android.text.method.LinkMovementMethod;
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

public class FinancialAdapter extends RecyclerView.Adapter<FinancialAdapter.FinancialHolder> {

    private double financialGoal = 0;
    private double financialCurrentGoal = 0;
    private double financialRemaining;
    Context context;
    ArrayList<FinancialEvent> financialevent = new ArrayList<>();

    public FinancialAdapter(Context context, ArrayList<FinancialEvent> list) {
        this.context = context;
        financialevent = list;
    }

    @Override
    public FinancialAdapter.FinancialHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FinancialAdapter.FinancialHolder(context, LayoutInflater.from(parent.getContext()).inflate(R.layout.financial_row, parent, false));
    }
//    }
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
////        View row = inflater.inflate(R.layout.financial_row, parent, false);
////        Item item = new Item(row);
////        return item;
//    }

        @Override
        public void onBindViewHolder(FinancialAdapter.FinancialHolder holder, final int position){
            FinancialEvent financialAdapt = financialevent.get(position);
            FinancialActivity.financialPosition = position;

            financialGoal = Double.parseDouble(financialAdapt.getFinancialGoal());
            if(financialAdapt.getFinancialCurrentGoal().length() > 0 && Double.parseDouble(financialAdapt.getFinancialCurrentGoal()) > 0)
                financialCurrentGoal = Double.parseDouble(financialAdapt.getFinancialCurrentGoal());
            else
                financialCurrentGoal = 0;
            financialRemaining = financialGoal - financialCurrentGoal;

            holder.getTextView().setText("Current: $" + financialCurrentGoal + ", Goal: $" + financialGoal + ", Remaining: $" + financialRemaining);
            holder.getTextView().setMovementMethod(new ScrollingMovementMethod());
            holder.getButtonDone().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    financialevent.remove(position);
                    if (MainActivity.financialList.size() <= 0)
                        MainActivity.financialTitle.setVisibility(View.INVISIBLE);
                    notifyDataSetChanged();
                }
            });
            holder.getButtonUpdate().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FinancialActivity.editMode = true;
                    Intent intent = new Intent(context , FinancialActivity.class);
                    notifyDataSetChanged();
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount () {
            return financialevent.size();
        }
//    public class Item extends RecyclerView.ViewHolder {
//        TextView textView;
//        public Item(View itemView) {
//            super(itemView);
//            textView = (TextView) itemView.findViewById(R.id.textViewFinancialRow);
//        }
//    }
        public class FinancialHolder extends RecyclerView.ViewHolder {

            private TextView textView;
            private Button buttonEdit;
            private Button buttonDone;

          public FinancialHolder(Context context, View itemView){
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.tv_financial);
                buttonDone = (Button) itemView.findViewById(R.id.financialDone);
                buttonEdit = (Button) itemView.findViewById(R.id.financialEdit);
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