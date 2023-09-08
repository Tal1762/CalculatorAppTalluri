package com.example.calculatorapptalluri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void number1Input(View v){
        TextView inputTV = findViewById(R.id.inputView);
        if (inputTV.getText().equals("0")){
            inputTV.setText("1");
        }
        inputTV.setText(inputTV.getText() + "1");
    }

    public void number2Input(View v){
        TextView inputTV = findViewById(R.id.inputView);
        if (inputTV.getText().equals("0")){
            inputTV.setText("2");
        }
        inputTV.setText(inputTV.getText() + "2");
    }

    public void number3Input(View v){
        TextView inputTV = findViewById(R.id.inputView);
        if (inputTV.getText().equals("0")){
            inputTV.setText("3");
        }
        inputTV.setText(inputTV.getText() + "3");
    }

    public void number4Input(View v){
        TextView inputTV = findViewById(R.id.inputView);
        if (inputTV.getText().equals("0")){
            inputTV.setText("4");
        }
        inputTV.setText(inputTV.getText() + "4");
    }

    public void number5Input(View v){
        TextView inputTV = findViewById(R.id.inputView);
        if (inputTV.getText().equals("0")){
            inputTV.setText("5");
        }
        inputTV.setText(inputTV.getText() + "5");
    }

    public void number6Input(View v){
        TextView inputTV = findViewById(R.id.inputView);
        if (inputTV.getText().equals("0")){
            inputTV.setText("6");
        }
        inputTV.setText(inputTV.getText() + "6");
    }

    public void number7Input(View v){
        TextView inputTV = findViewById(R.id.inputView);
        if (inputTV.getText().equals("0")){
            inputTV.setText("7");
        }
        inputTV.setText(inputTV.getText() + "7");
    }

    public void number8Input(View v){
        TextView inputTV = findViewById(R.id.inputView);
        if (inputTV.getText().equals("0")){
            inputTV.setText("8");
        }
        inputTV.setText(inputTV.getText() + "8");
    }

    public void number9Input(View v){
        TextView inputTV = findViewById(R.id.inputView);
        if (inputTV.getText().equals("0")){
            inputTV.setText("9");
        }
        inputTV.setText(inputTV.getText() + "9");
    }

    public void number0Input(View v){
        TextView inputTV = findViewById(R.id.inputView);
        if (inputTV.getText().equals("0")){
            inputTV.setText("0");
        }
        inputTV.setText(inputTV.getText() + "0");
    }
}