package com.example.currencyparserxml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.*;
import android.text.Editable;
import android.text.TextWatcher;
import java.text.DecimalFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity implements DataLoader.DataCallback, CurrencyAdapter.OnCurrencyClick {

    RecyclerView recyclerViewRates;
    EditText txtFilter, txtAmount;
    AutoCompleteTextView autoConvertTo;
    TextView txtResult, txtFromLabel;

    ArrayList<String> currencyList = new ArrayList<>();
    ArrayList<String> fullCurrencyList = new ArrayList<>();
    public static HashMap<String, Double> currencyMap = new HashMap<>();

    CurrencyAdapter adapter;

    String API_URL = "https://open.er-api.com/v6/latest/USD";

    String fromCode = null;
    double fromRate = 1.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewRates = findViewById(R.id.recyclerViewRates);
        txtFilter = findViewById(R.id.txtFilter);
        txtAmount = findViewById(R.id.txtAmount);
        autoConvertTo = findViewById(R.id.autoConvertTo);
        txtResult = findViewById(R.id.txtResult);
        txtFromLabel = findViewById(R.id.txtFromLabel);

        recyclerViewRates.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CurrencyAdapter(currencyList, this);
        recyclerViewRates.setAdapter(adapter);

        txtFilter.addTextChangedListener(new TextWatcher() {
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { filter(s.toString()); }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });

        txtAmount.addTextChangedListener(new TextWatcher() {
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { convert(); }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });

        autoConvertTo.setOnItemClickListener((parent, view, position, id) -> convert());

        new DataLoader(this).execute(API_URL);
    }

    @Override
    public void onDataLoaded(String jsonData) {
        currencyMap = Parser.parseRates(jsonData);

        fullCurrencyList.clear();
        fullCurrencyList.addAll(currencyMap.keySet());
        Collections.sort(fullCurrencyList);

        currencyList.clear();
        currencyList.addAll(fullCurrencyList);
        adapter.notifyDataSetChanged();

        ArrayAdapter<String> targetAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, fullCurrencyList);
        autoConvertTo.setAdapter(targetAdapter);
    }

    private void filter(String text) {
        text = text.trim().toUpperCase();
        currencyList.clear();
        for (String code : fullCurrencyList) {
            if (code.contains(text)) currencyList.add(code);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCurrencyClicked(String code, double rate) {
        fromCode = code;
        fromRate = rate;
        txtFromLabel.setText("Convert From: " + code);
        convert();
    }

    private void convert() {
        if (fromCode == null) return;
        if (txtAmount.getText().toString().trim().isEmpty()) return;

        String toCode = autoConvertTo.getText().toString().trim();
        if (!currencyMap.containsKey(toCode)) return;

        double amount = Double.parseDouble(txtAmount.getText().toString().trim());
        double toRate = currencyMap.get(toCode);

        double result = amount * (toRate / fromRate);

        DecimalFormat df = new DecimalFormat("#,###.##");
        txtResult.setText(df.format(amount) + " " + fromCode + " → " + df.format(result) + " " + toCode);
    }
}
