package com.example.currencyparserxml;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {

    public interface OnCurrencyClick {
        void onCurrencyClicked(String code, double rate);
    }

    private final ArrayList<String> list;
    private final OnCurrencyClick listener;

    public CurrencyAdapter(ArrayList<String> list, OnCurrencyClick listener) {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_currency, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int pos) {
        String code = list.get(pos);
        double rate = MainActivity.currencyMap.get(code);
        holder.txt.setText(code + "  -  " + rate);
        holder.itemView.setOnClickListener(v -> listener.onCurrencyClicked(code, rate));
    }

    @Override
    public int getItemCount() { return list.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        ViewHolder(View item) {
            super(item);
            txt = item.findViewById(R.id.txtCurrencyRow);
        }
    }
}
