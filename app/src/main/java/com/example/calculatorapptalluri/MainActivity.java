package com.example.calculatorapptalluri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<String> inputLine = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void inputNum(int n){
        TextView inputTV = findViewById(R.id.inputView);
        if (inputTV.getText().equals("0")){
            inputTV.setText("" + n);
        }
        else{
            inputTV.setText("" + inputTV.getText() + n);
        }
        if (inputLine.size() != 0) {
            inputLine.set(inputLine.size() - 1, inputLine.get(inputLine.size() - 1) + n);
        }
        else{
            inputLine.add("" + n);
        }
    }

    public boolean equalsOperator(String s){
        if (!s.endsWith("+") &&
                !s.endsWith("-") &&
                !s.endsWith("×") &&
                !s.endsWith("÷") &&
                !s.endsWith("√") &&
                !s.endsWith("^") &&
                !s.endsWith("%") &&
                !s.endsWith("ln")){
            return false;
        }
        return true;
    }

    public void inputOperator(String s){
        TextView inputTV = findViewById(R.id.inputView);
        if (!inputTV.getText().equals("0") &&
                !equalsOperator((String)inputTV.getText())){
            inputLine.add(s);
            inputTV.setText(inputTV.getText() + s);
        }
    }

    public void findAndCalc(String op){
        for (int i=0; i<inputLine.size(); i++){
            if (inputLine.get(i).equals(op)){
                if (op.equals("+")) {
                    inputLine.set(i - 1, "" + (Integer.parseInt(inputLine.get(i-1)) + Integer.parseInt(inputLine.get(i+1))));
                    inputLine.remove(i);
                    inputLine.remove(i+1);
                }
                else if (op.equals("-")) {
                    inputLine.set(i - 1, "" + (Integer.parseInt(inputLine.get(i-1)) - Integer.parseInt(inputLine.get(i+1))));
                    inputLine.remove(i);
                    inputLine.remove(i+1);
                }
                else if (op.equals("×")) {
                    inputLine.set(i - 1, "" + (Integer.parseInt(inputLine.get(i-1)) * Integer.parseInt(inputLine.get(i+1))));
                    inputLine.remove(i);
                    inputLine.remove(i+1);
                }
                else if (op.equals("÷")) {
                    inputLine.set(i - 1, "" + (Integer.parseInt(inputLine.get(i-1)) / Integer.parseInt(inputLine.get(i+1))));
                    inputLine.remove(i);
                    inputLine.remove(i+1);
                }
                else if (op.equals("√")) {
                    inputLine.set(i, "" + Math.sqrt(Integer.parseInt(inputLine.get(i+1))));
                    inputLine.remove(i+1);
                }
                else if (op.equals("^")) {
                    inputLine.set(i - 1, "" + Math.round(Math.pow(Integer.parseInt(inputLine.get(i-1)), Integer.parseInt(inputLine.get(i+1)))));
                    inputLine.remove(i);
                    inputLine.remove(i+1);
                }
                else if (op.equals("%")) {
                    inputLine.set(i - 1, "" + (Integer.parseInt(inputLine.get(i-1)) % Integer.parseInt(inputLine.get(i+1))));
                    inputLine.remove(i);
                    inputLine.remove(i+1);
                }
                else if (op.equals("ln")) {
                    inputLine.set(i, "" + Math.log(Integer.parseInt(inputLine.get(i+1))));
                    inputLine.remove(i+1);
                }
            }
        }
    }

    public void printResult(View v){
        if (inputLine.size() > 0) {
            TextView outputTV = findViewById(R.id.outputView);
            findAndCalc("ln");
            findAndCalc("√");
            findAndCalc("^");
            findAndCalc("%");
            findAndCalc("÷");
            findAndCalc("×");
            findAndCalc("-");
            findAndCalc("+");
            outputTV.setText(inputLine.get(0));
        }
    }

    public void plusInput(View v){
        inputOperator("+");
    }

    public void minusInput(View v){
        inputOperator("-");
    }

    public void multiplyInput(View v){
        inputOperator("×");
    }

    public void divideInput(View v){
        inputOperator("÷");
    }

    public void number1Input(View v){
        inputNum(1);
    }

    public void number2Input(View v){
        inputNum(2);
    }

    public void number3Input(View v){
        inputNum(3);
    }

    public void number4Input(View v){
        inputNum(4);
    }

    public void number5Input(View v){
        inputNum(5);
    }

    public void number6Input(View v){
        inputNum(6);
    }

    public void number7Input(View v){
        inputNum(7);
    }

    public void number8Input(View v){
        inputNum(8);
    }

    public void number9Input(View v){
        inputNum(9);
    }

    public void number0Input(View v){
        inputNum(0);
    }

    public void backspace(View v){
        if (inputLine.size() > 0){
            TextView inputTV = findViewById(R.id.inputView);
            inputLine.remove(inputLine.size()-1);
            inputTV.setText(((String)inputTV.getText()).substring(0, inputTV.getText().length()-1));
            if (inputTV.getText().equals("")){
                inputTV.setText("0");
            }
        }
    }

    public void clearAll(View v){
        TextView inputTV = findViewById(R.id.inputView);
        inputLine.clear();
        inputTV.setText("0");
    }
}