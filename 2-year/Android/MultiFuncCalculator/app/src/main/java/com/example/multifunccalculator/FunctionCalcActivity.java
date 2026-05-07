package com.example.multifunccalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FunctionCalcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_function);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Func_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button modeCloseButton = findViewById(R.id.FuncCalButtonMode);
        Intent intent = new Intent(FunctionCalcActivity.this, ModeSelectActivity.class);
        modeCloseButton.setOnClickListener(v -> startActivity(intent));




    }
}