package com.example.multifunccalculator;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button DefCalBtnAC = findViewById(R.id.DefaCalButtonAC);        //AC
        Button DefCalBtnMem = findViewById(R.id.DefaCalButtonMem);      //M
        Button DefCalBtnBS = findViewById(R.id.DefaCalButtonBS);        //← backspace
        Button DefCalBtnDiv = findViewById(R.id.DefaCalButtonDiv);      //÷
        Button DefCalBtnMul = findViewById(R.id.DefaCalButtonMul);      //×
        Button DefCalBtnSub = findViewById(R.id.DefaCalButtonSub);      //-
        Button DefCalBtnAdd = findViewById(R.id.DefaCalButtonAdd);      //+
        Button DefCalBtnMode = findViewById(R.id.DefaCalButtonMode);    //mode
        Button DefCalBtnCom = findViewById(R.id.DefaCalButtonCom);      //.
        Button DefCalBtnEqu = findViewById(R.id.DefaCalButtonEqu);      //=

        Button DefCalBtn0 = findViewById(R.id.DefaCalButton0);
        Button DefCalBtn1 = findViewById(R.id.DefaCalButton1);
        Button DefCalBtn2 = findViewById(R.id.DefaCalButton2);
        Button DefCalBtn3 = findViewById(R.id.DefaCalButton3);
        Button DefCalBtn4 = findViewById(R.id.DefaCalButton4);
        Button DefCalBtn5 = findViewById(R.id.DefaCalButton5);
        Button DefCalBtn6 = findViewById(R.id.DefaCalButton6);
        Button DefCalBtn7 = findViewById(R.id.DefaCalButton7);
        Button DefCalBtn8 = findViewById(R.id.DefaCalButton8);
        Button DefCalBtn9 = findViewById(R.id.DefaCalButton9);


        DefCalBtnMode.setOnClickListener(new BtnClickListener());
        DefCalBtnAC.setOnClickListener(new BtnClickListener());
        DefCalBtnBS.setOnClickListener(new BtnClickListener());
        DefCalBtnMem.setOnClickListener(new BtnClickListener());
        DefCalBtnDiv.setOnClickListener(new BtnClickListener());
        DefCalBtnMul.setOnClickListener(new BtnClickListener());
        DefCalBtnSub.setOnClickListener(new BtnClickListener());
        DefCalBtnAdd.setOnClickListener(new BtnClickListener());
        DefCalBtnCom.setOnClickListener(new BtnClickListener());
        DefCalBtnEqu.setOnClickListener(new BtnClickListener());
        DefCalBtn0.setOnClickListener(new BtnClickListener());
        DefCalBtn1.setOnClickListener(new BtnClickListener());
        DefCalBtn2.setOnClickListener(new BtnClickListener());
        DefCalBtn3.setOnClickListener(new BtnClickListener());
        DefCalBtn4.setOnClickListener(new BtnClickListener());
        DefCalBtn5.setOnClickListener(new BtnClickListener());
        DefCalBtn6.setOnClickListener(new BtnClickListener());
        DefCalBtn7.setOnClickListener(new BtnClickListener());
        DefCalBtn8.setOnClickListener(new BtnClickListener());
        DefCalBtn9.setOnClickListener(new BtnClickListener());



    }

    private class BtnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //ボタンの文字を取得
            Button btnid = (Button) view;
            String btnStr = btnid.getText().toString();


            //CalculatView 上のやつ から文字列の取得
            TextView  viewTxtView = findViewById(R.id.CalculatView);
            String viewCurTxt = viewTxtView.getText().toString();

            //CalculatValue 下のやつ から文字列の取得
            TextView valTxtView = findViewById(R.id.CalculatValue);
            String valCurTxt = valTxtView.getText().toString();

            CalcDatabaseHelper db = new CalcDatabaseHelper(MainActivity.this);




            //文字のはみ出し対策の最終文字の␣を削除
            if (viewCurTxt.length() > 1) {
                viewCurTxt = viewCurTxt.substring(0, viewCurTxt.length() - 2);  // 最後の2文字を削除
            }
            if (valCurTxt.length() > 1) {
                valCurTxt = valCurTxt.substring(0, valCurTxt.length() - 2);  // 最後の2文字を削除
            }

            //前回に押されたボタン
            char pastBtn = ' ';

            if (viewCurTxt.length() > 1) {

                pastBtn = viewCurTxt.charAt(viewCurTxt.length() - 2);  // 最後の文字だけ取得

            }



            //更新後文字列
            String updViewText = viewCurTxt;
            String updValText = "";


            if (pastBtn == '=') {

                updViewText = "";
                valCurTxt = "";

            }

            if (btnStr.equals("mode")) {

                Intent intent = new Intent(MainActivity.this, ModeSelectActivity.class);
                startActivity(intent);

                return;

                //ACボタンで実行
            }else if (btnStr.equals("AC")) {
                //クリア
                updViewText = "";
                updValText = "0";

            //← backspaceで実行
            } else if (btnStr.equals("←")) {

                //数値が一桁以上の時
                if (valCurTxt.length() > 1) {
                    //文字を一桁後ろから減らす
                    updValText = valCurTxt.substring(0, valCurTxt.length() - 1);

                    //数値が一桁のとき
                } else {
                    //数値を0にリセット
                    updValText = "0";
                }

            } else if (btnStr.equals("M")) {

                showSelectDialog();

                return;

            //演算子で実行
            } else if (btnStr.equals("÷") || btnStr.equals("×") || btnStr.equals("+") || btnStr.equals("-")) {

                if (pastBtn == '=') {

                    updViewText = "";

                    updValText = "0";

                } else {

                    //数値と演算子をTxtViewに表記
                    updViewText = viewCurTxt + valCurTxt + " " + btnStr + " ";

                    updValText = "0";

                }

            } else if (btnStr.equals("=")){

                if (!(viewCurTxt.equals("")) && (pastBtn == '÷' || pastBtn == '×' || pastBtn == '+' || pastBtn == '-')) {

                    if (viewCurTxt.contains("÷") == true) {

                        viewCurTxt = viewCurTxt.replace('÷', '/');

                    } else if (viewCurTxt.contains("×") == true) {

                        viewCurTxt = viewCurTxt.replace('×', '*');

                    }


                    updViewText = viewCurTxt + valCurTxt;
                    updValText = Calculator.calculate(updViewText);
                    updViewText = updViewText + " " + btnStr + " ";


                    if (viewCurTxt.contains("/") == true) {

                        updViewText = updViewText.replace('/', '÷');

                    } else if (viewCurTxt.contains("*") == true) {

                        updViewText = updViewText.replace('*', '×');

                    }

                    db.addLog(updViewText, updValText);

                }else{

                    updValText = valCurTxt;

                }

            //数値が０の時かつ . 小数点以外で実行
            } else if (valCurTxt.length() == 1 && valCurTxt.equals("0") && !(btnStr.equals("."))){
                //置換
                 updValText = btnStr;

            //数字で実行
            }else {

                if (pastBtn == '=' && btnStr.equals(".")) {

                    valCurTxt = "0";

                }

                if (valCurTxt.contains(".") == true && btnStr.equals(".") == true){

                    updValText = valCurTxt;

                }else {

                    //結合
                    updValText = valCurTxt + btnStr;

                }
            }


            if (updValText.length() == 0) {
                updValText = "0";
            }


            if (updViewText.length() > 1) {
                updViewText = updViewText + "  ";  // 最後に␣を挿入する
            }

            if (updValText.length() > 1) {
                updValText = updValText + "  ";  // 最後に␣を挿入する
            }


            // TextViewにセット
            viewTxtView.setText(updViewText);
            valTxtView.setText(updValText);

            //画面スクロールを一番右にする
            HorizontalScrollView viewHsv = findViewById(R.id.horizonCalView);
            viewHsv.post(() -> viewHsv.fullScroll(View.FOCUS_RIGHT));
            HorizontalScrollView valHsv = findViewById(R.id.horizonCalVal);
            valHsv.post(() -> valHsv.fullScroll(View.FOCUS_RIGHT));

        }
    }

    private final List<AlertDialog> openDialogs = new ArrayList<>();

    private void showSelectDialog() {

        SingleValueDatabaseHelper helper = new SingleValueDatabaseHelper(this);



        // レイアウトを読み込み
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_select, null);

        TextView  viewTxtView = findViewById(R.id.CalculatView);
        String viewCurTxt = viewTxtView.getText().toString();

        TextView valTxtView = findViewById(R.id.CalculatValue);
        String valCurTxt = valTxtView.getText().toString();


        // ListView を取得
        Button CallSelectMemoButton = dialogView.findViewById(R.id.CallSelectMemoButton);
        Button AddSelectMemoButton = dialogView.findViewById(R.id.AddSelectMemoButton);
        Button SubSelectMemoButton = dialogView.findViewById(R.id.SubSelectMemoButton);
        Button ClearSelectMemoButton = dialogView.findViewById(R.id.ClearSelectMemoButton);
        Button LogSelectMemoButton = dialogView.findViewById(R.id.LogSelectMemoButton);
        Button CloseSelectMemoButton = dialogView.findViewById(R.id.CloseSelectMemoButton); // ← 戻るボタン


        // AlertDialogを変数に保持
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.CENTER; // ← ここをCENTER, TOPに変えると中央・上部表示
            params.width = WindowManager.LayoutParams.MATCH_PARENT; // 幅いっぱい
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setAttributes(params);
            //window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // ← 背景が白すぎる場合に透明に
        }

        CallSelectMemoButton.setOnClickListener(v -> {
            if (viewCurTxt.trim().endsWith("=")) {
                viewTxtView.setText("");
            }
            valTxtView.setText(helper.getValue());
            dialog.dismiss();
            openDialogs.clear();
        });

        AddSelectMemoButton.setOnClickListener(v -> {
            helper.add(valCurTxt);
            dialog.dismiss();
            openDialogs.clear();
        });

        SubSelectMemoButton.setOnClickListener(v -> {
            helper.sub(valCurTxt);
            dialog.dismiss();
            openDialogs.clear();
        });

        ClearSelectMemoButton.setOnClickListener(v -> {
            helper.clear();
            dialog.dismiss();
            openDialogs.clear();
        });

        LogSelectMemoButton.setOnClickListener(v -> showLogDialog());

        // 戻るボタンのクリックで閉じる
        CloseSelectMemoButton.setOnClickListener(v -> dialog.dismiss());


        openDialogs.add(dialog); // ★ リストに追加

        // ダイアログ表示
        dialog.show();
    }


    private void showLogDialog() {
        // レイアウトを読み込み
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_log, null);

        // ListView を取得
        TextView valTxtView = findViewById(R.id.CalculatValue);
        ListView listView = dialogView.findViewById(R.id.log_list);
        Button closeButton = dialogView.findViewById(R.id.LogCloseButton); // ← 戻るボタン

        // DB から履歴を取得
        CalcDatabaseHelper dbHelper = new CalcDatabaseHelper(this);
        List<String> log = dbHelper.getLog();

        // ArrayAdapter で表示
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                log
        );
        listView.setAdapter(adapter);

        // AlertDialogを変数に保持
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.BOTTOM; // ← ここをCENTER, TOPに変えると中央・上部表示
            params.width = WindowManager.LayoutParams.MATCH_PARENT; // 幅いっぱい
            //params.dimAmount = 0.1f;
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setAttributes(params);
            //window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // ← 背景が白すぎる場合に透明に
        }


        // 戻るボタンのクリックで閉じる
        closeButton.setOnClickListener(v -> dialog.dismiss());


        openDialogs.add(dialog); // ★ リストに追加

        // ダイアログ表示
        dialog.show();


        listView.setOnItemClickListener((parent, view, position, id) -> {
            List<String> Log = dbHelper.getResult();

            String result = Log.get(position);
            valTxtView.setText(result);



            for (AlertDialog d : openDialogs) {
                if (d.isShowing()) {
                    d.dismiss();
                }
            }
            openDialogs.clear(); // リストも空にしておく

        });
    }

}
