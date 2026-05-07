package com.example.multifunccalculator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ModeSelectActivity extends AppCompatActivity {

    private ListView calcList;
    private List<ModeItem> modes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mode_select);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Mode_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calcList = findViewById(R.id.Modelist);

        // モード一覧を定義
        modes = new ArrayList<>();
        modes.add(new ModeItem("標準", true));
        modes.add(new ModeItem("関数", true));
        modes.add(new ModeItem("統計", false));
        modes.add(new ModeItem("プログラマ", false));

        // アダプター設定
        ArrayAdapter<ModeItem> adapter = new ArrayAdapter<ModeItem>(
                this,
                android.R.layout.simple_list_item_1,
                modes
        ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                ModeItem item = getItem(position);
                if (item != null) {
                    view.setText(item.name + (item.isImplemented ? "" : "（未実装）"));
                    view.setTextColor(item.isImplemented ? Color.BLACK : Color.GRAY);
                }
                return view;
            }
        };

        calcList.setAdapter(adapter);

        // クリックイベント処理
        calcList.setOnItemClickListener((parent, view, position, id) -> {
            ModeItem selected = modes.get(position);
            if (!selected.isImplemented) {
//                Toast.makeText(this, selected.name + " は未実装です", Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(this, selected.name + " モードを起動します", Toast.LENGTH_SHORT).show();
                // TODO: 実装済みモードの画面遷移などをここで処理
                switch (selected.name) {
                    case "標準":
                        Intent intent = new Intent(ModeSelectActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();
                        break;

                    case "関数":
                        Intent funcIntent = new Intent(this, FunctionCalcActivity.class);
                        funcIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(funcIntent);
                        break;
//
//                    case "履歴":
//                        Intent historyIntent = new Intent(this, HistoryActivity.class);
//                        startActivity(historyIntent);
//                        break;
//
//                    default:
//                        Toast.makeText(this, "まだ遷移先が設定されていません", Toast.LENGTH_SHORT).show();
//                        break;
                }
            }
        });

        // 閉じるボタン処理
        Button modeCloseButton = findViewById(R.id.ModeCloseButton);
        modeCloseButton.setOnClickListener(v -> finish());
    }

    // モード情報クラス
    public static class ModeItem {
        String name;
        boolean isImplemented;

        public ModeItem(String name, boolean isImplemented) {
            this.name = name;
            this.isImplemented = isImplemented;
        }

        @Override
        public String toString() {
            return name; // 表示用（必須）
        }
    }
}
