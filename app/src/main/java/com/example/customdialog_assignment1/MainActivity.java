package com.example.customdialog_assignment1;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button DialogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
    }

    private void initViews() {
        DialogButton = findViewById(R.id.DialogButton);
    }
    private void initListeners() {
        DialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });
    }

    public void showCustomDialog() {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.activity_customdialog);

        SeekBar priceSeekBar = dialog.findViewById(R.id.priceSeekBar);
        TextView priceRangeText = dialog.findViewById(R.id.priceRangeText);
        CheckBox checkBox1BHK = dialog.findViewById(R.id.checkbox_1bhk);
        CheckBox checkBox2BHK = dialog.findViewById(R.id.checkbox_2bhk);
        CheckBox checkBox3BHK = dialog.findViewById(R.id.checkbox_3bhk);
        Button button = dialog.findViewById(R.id.button);

        priceSeekBar.setMax(0);
        priceSeekBar.setProgress(0);
        priceRangeText.setText("0");

        initCheckboxLogic(checkBox1BHK, checkBox2BHK, checkBox3BHK);

        initDialogListeners(dialog, priceSeekBar, priceRangeText, checkBox1BHK, checkBox2BHK, checkBox3BHK, button);

        dialog.show();
    }

    private void initCheckboxLogic(CheckBox checkBox1BHK, CheckBox checkBox2BHK, CheckBox checkBox3BHK) {
        checkBox1BHK.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBox2BHK.setChecked(false);
                checkBox3BHK.setChecked(false);
            }
        });

        checkBox2BHK.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBox1BHK.setChecked(false);
                checkBox3BHK.setChecked(false);
            }
        });

        checkBox3BHK.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkBox1BHK.setChecked(false);
                checkBox2BHK.setChecked(false);
            }
        });
    }

    private void initDialogListeners(Dialog dialog, SeekBar priceSeekBar, TextView priceRangeText, CheckBox checkBox1BHK, CheckBox checkBox2BHK, CheckBox checkBox3BHK, Button buttonOk) {

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox1BHK.isChecked()) {
                    priceSeekBar.setProgress(25);
                    priceRangeText.setText("Price Range: 25 Lakh");
                } else if (checkBox2BHK.isChecked()) {
                    priceSeekBar.setProgress(50);
                    priceRangeText.setText("Price Range: 50 Lakh");
                } else if (checkBox3BHK.isChecked()) {
                    priceSeekBar.setProgress(100);
                    priceRangeText.setText("Price Range: 1 Crore");
                }
            }
        });
    }
}
