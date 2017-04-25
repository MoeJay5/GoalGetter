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

class FinancialAdapter extends RecyclerView.Adapter<FinancialAdapter.FinancialHolder> {

    private Context context;
    private ArrayList<FinancialEvent> financialEvents = new ArrayList<>();

    FinancialAdapter(Context context, ArrayList<FinancialEvent> list) {
        this.context = context;
        financialEvents = list;
    }

    @Override
    public FinancialAdapter.FinancialHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FinancialAdapter.FinancialHolder(context, LayoutInflater.from(parent.getContext()).inflate(R.layout.financial_row, parent, false));
    }

    @Override
    public void onBindViewHolder(FinancialAdapter.FinancialHolder holder, final int position){
        FinancialEvent financialAdapt = financialEvents.get(position);

        double financialGoal = Double.parseDouble(financialAdapt.getFinancialGoal());
        double financialCurrentGoal = 0;
        if(financialAdapt.getFinancialCurrentGoal().length() > 0 && Double.parseDouble(financialAdapt.getFinancialCurrentGoal()) > 0)
            financialCurrentGoal = Double.parseDouble(financialAdapt.getFinancialCurrentGoal());
        double financialRemaining = financialGoal - financialCurrentGoal;

        holder.getTextView().setText("Current: $" + castToInt(financialCurrentGoal) + ", Goal: $" + castToInt(financialGoal) + ", Remaining: $" + castToInt(financialRemaining));
        holder.getTextView().setMovementMethod(new ScrollingMovementMethod());
        holder.getButtonDone().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                financialEvents.remove(position);
                if (MainActivity.financialList.size() <= 0)
                    MainActivity.financialTitle.setVisibility(View.INVISIBLE);
                notifyDataSetChanged();
            }
        });
        holder.getButtonUpdate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinancialActivity.financialEditMode = true;
                FinancialActivity.financialPosition = position;
                Intent intent = new Intent(context , FinancialActivity.class);
                notifyDataSetChanged();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount () {
        return financialEvents.size();
    }

    class FinancialHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private Button buttonEdit;
        private Button buttonDone;

        FinancialHolder(Context context, View itemView){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_financial);
            buttonDone = (Button) itemView.findViewById(R.id.financialDone);
            buttonEdit = (Button) itemView.findViewById(R.id.financialEdit);
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

    private Object castToInt(double value) {
        if ((value - (int) value) == 0)
            return (int) value;
        else
            return value;
    }
}