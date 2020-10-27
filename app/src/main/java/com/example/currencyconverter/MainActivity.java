package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    static final double USD2VND  = 23180.34d, EUR2VND = 27397.26d, GBP2VND = 30202.36d, JPY2VND = 221.09d,
    CNY2VND = 3453.04d, HKD2VND = 2990.43d, SGD2VND = 17032.87d, THB2VND = 741.29d, KRW2VND = 20.46d;

    EditText editMount, editResult;
    Spinner spnFrom, spnTo;

    String[] currency = {"VND", "USD", "EUR", "GBP", "JPY", "CNY", "HKD", "SGD", "THB", "KRW"};
    String from ="", to = "";
    String mount = "", result ="";
    DecimalFormat decimalFormat = new DecimalFormat("###.##");

    TextWatcher generalTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(editMount.getText().hashCode()==s.hashCode() && editMount.hasFocus()){
                mount = editMount.getText().toString();
                if (mount.isEmpty()){
                    mount = "0";
                    editMount.setText("0");
                }
                if (to.equals("VND")){
                    switch (from){
                        case "VND":
                            editResult.setText(mount);
                            break;
                        case "USD":
                            editResult.setText(decimalFormat.format(Double.parseDouble(mount)*USD2VND).replace(',', '.'));
                            break;
                        case "EUR":
                            editResult.setText(decimalFormat.format(Double.parseDouble(mount)*EUR2VND).replace(',', '.'));
                            break;
                        case "GBP":
                            editResult.setText(decimalFormat.format(Double.parseDouble(mount)*GBP2VND).replace(',', '.'));
                            break;
                        case "JPY":
                            editResult.setText(decimalFormat.format(Double.parseDouble(mount)*JPY2VND).replace(',', '.'));
                            break;
                        case "CNY":
                            editResult.setText(decimalFormat.format(Double.parseDouble(mount)*CNY2VND).replace(',', '.'));
                            break;
                        case "HKD":
                            editResult.setText(decimalFormat.format(Double.parseDouble(mount)*HKD2VND).replace(',', '.'));
                            break;
                        case "SGD":
                            editResult.setText(decimalFormat.format(Double.parseDouble(mount)*SGD2VND).replace(',', '.'));
                            break;
                        case "THB":
                            editResult.setText(decimalFormat.format(Double.parseDouble(mount)*THB2VND).replace(',', '.'));
                            break;
                        case "KRW":
                            editResult.setText(decimalFormat.format(Double.parseDouble(mount)*KRW2VND).replace(',', '.'));
                            break;
                    }
                }
            }else if (editResult.getText().hashCode()==s.hashCode() && editResult.hasFocus()){
                result = editResult.getText().toString();
                if (result.isEmpty()){
                    result = "0";
                    editResult.setText("0");
                }
                if (to.equals("VND")){
                    switch (from){
                        case "VND":
                            editMount.setText(result);
                            break;
                        case "USD":
                            editMount.setText(decimalFormat.format(Double.parseDouble(result)/USD2VND).replace(',', '.'));
                            break;
                        case "EUR":
                            editMount.setText(decimalFormat.format(Double.parseDouble(result)/EUR2VND).replace(',', '.'));
                            break;
                        case "GBP":
                            editMount.setText(decimalFormat.format(Double.parseDouble(result)/GBP2VND).replace(',', '.'));
                            break;
                        case "JPY":
                            editMount.setText(decimalFormat.format(Double.parseDouble(result)/JPY2VND).replace(',', '.'));
                            break;
                        case "CNY":
                            editMount.setText(decimalFormat.format(Double.parseDouble(result)/CNY2VND).replace(',', '.'));
                            break;
                        case "HKD":
                            editMount.setText(decimalFormat.format(Double.parseDouble(result)/HKD2VND).replace(',', '.'));
                            break;
                        case "SGD":
                            editMount.setText(decimalFormat.format(Double.parseDouble(result)/SGD2VND).replace(',', '.'));
                            break;
                        case "THB":
                            editMount.setText(decimalFormat.format(Double.parseDouble(result)/THB2VND).replace(',', '.'));
                            break;
                        case "KRW":
                            editMount.setText(decimalFormat.format(Double.parseDouble(result)/KRW2VND).replace(',', '.'));
                            break;
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editMount = findViewById(R.id.editMount);
        editResult = findViewById(R.id.editResult);

        spnFrom = findViewById(R.id.spnFrom);
        spnTo = findViewById(R.id.spnTo);

        ArrayAdapter<String> adapterFrom = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                currency);

        ArrayAdapter<String> adapterTo = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                currency){
            @Override
            public boolean isEnabled(int position){
                if(position > 0)
                {
                    // Disable the second item from Spinner
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position>0) {
                    // Set the disable item text color
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnFrom.setAdapter(adapterFrom);
        spnFrom.setSelection(1);
        spnTo.setAdapter(adapterTo);
        spnTo.setSelection(0);

        editMount.setText("1");
        editResult.setText(Double.toString(USD2VND));

        editMount.addTextChangedListener(generalTextWatcher);
        editResult.addTextChangedListener(generalTextWatcher);

        spnFrom.setOnItemSelectedListener(this);
        spnTo.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        switch (parent.getId()){
            case R.id.spnFrom:
                from = spnFrom.getSelectedItem().toString();
                break;
            case R.id.spnTo:
                to = spnTo.getSelectedItem().toString();
                break;
        }

        mount = editMount.getText().toString();
        if (mount.isEmpty()){
            mount = "0";
            editMount.setText("0");
        }
        if (to.equals("VND")){
            switch (from){
                case "VND":
                    editResult.setText(mount);
                    break;
                case "USD":
                    editResult.setText(decimalFormat.format(Double.parseDouble(mount)*USD2VND).replace(',', '.'));
                    break;
                case "EUR":
                    editResult.setText(decimalFormat.format(Double.parseDouble(mount)*EUR2VND).replace(',', '.'));
                    break;
                case "GBP":
                    editResult.setText(decimalFormat.format(Double.parseDouble(mount)*GBP2VND).replace(',', '.'));
                    break;
                case "JPY":
                    editResult.setText(decimalFormat.format(Double.parseDouble(mount)*JPY2VND).replace(',', '.'));
                    break;
                case "CNY":
                    editResult.setText(decimalFormat.format(Double.parseDouble(mount)*CNY2VND).replace(',', '.'));
                    break;
                case "HKD":
                    editResult.setText(decimalFormat.format(Double.parseDouble(mount)*HKD2VND).replace(',', '.'));
                    break;
                case "SGD":
                    editResult.setText(decimalFormat.format(Double.parseDouble(mount)*SGD2VND).replace(',', '.'));
                    break;
                case "THB":
                    editResult.setText(decimalFormat.format(Double.parseDouble(mount)*THB2VND).replace(',', '.'));
                    break;
                case "KRW":
                    editResult.setText(decimalFormat.format(Double.parseDouble(mount)*KRW2VND).replace(',', '.'));
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
}