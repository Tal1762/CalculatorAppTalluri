package com.example.calculatorapptalluri;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<String> inputLine = new ArrayList<>();
    boolean enterPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void inputNum(View v, int n){
        TextView inputTV = findViewById(R.id.inputView);
        TextView outputTV = findViewById(R.id.outputView);
        if (enterPressed) {
            outputTV.setTextSize(24);
            ColorStateList c = outputTV.getTextColors();
            outputTV.setTextColor(inputTV.getTextColors());
            inputTV.setTextSize(32);
            inputTV.setTextColor(c);
            enterPressed = false;
            clearAll(v);
        }
        if (inputTV.getText().equals("0")){
            inputTV.setText("" + n);
        }
        else{
            inputTV.setText("" + inputTV.getText() + n);
        }
        if (inputLine.size() != 0 &&
                !equalsOperator(inputLine.get(inputLine.size()-1)) &&
                !inputLine.get(inputLine.size()-1).equals("0")) {
            inputLine.set(inputLine.size() - 1, inputLine.get(inputLine.size() - 1) + n);
        }
        else if (inputLine.size() != 0 && inputLine.get(inputLine.size()-1).equals("0")){
            inputLine.set(inputLine.size() - 1, "" + n);
        }
        else{
            inputLine.add("" + n);
        }
    }

    public boolean equalsOperator(String s){
        if (!s.equals("+") &&
                !s.equals("-") &&
                !s.equals("×") &&
                !s.equals("÷") &&
                !s.equals("^") &&
                !s.equals("√") &&
                !s.equals("ln") &&
                !s.equals("%")){
            return false;
        }
        return true;
    }

    public void inputOperator(String s){
        TextView inputTV = findViewById(R.id.inputView);
        if (!inputTV.getText().equals("0") &&
                !equalsOperator((String)inputTV.getText()) &&
                !s.equals("√") &&
                !s.equals("ln")){
            inputLine.add(s);
            inputTV.setText(inputTV.getText() + s);
        }
        else if (inputTV.getText().equals("0") && (s.equals("√") || s.equals("ln"))){
            if (inputLine.size() == 1) {
                inputLine.set(0, s);
            }
            else{
                inputLine.add(s);
            }
            inputTV.setText(s);
        }
        else if (!inputTV.getText().equals("0")
                    && (s.equals("√") || s.equals("ln"))
                    && !equalsOperator(inputTV.getText().toString().substring(inputTV.getText().length()-1))){
            inputLine.add("×");
            inputLine.add(s);
            inputTV.setText(inputTV.getText() + "×" + s);
        }
        else if (!inputTV.getText().equals("0")
                && (s.equals("√") || s.equals("ln"))
                && equalsOperator(inputTV.getText().toString().substring(inputTV.getText().length()-1))){
            inputLine.add(s);
            inputTV.setText(inputTV.getText() + s);
        }
    }

    public void findAndCalc(String op, ArrayList<String> inputLine){
        for (int i=0; i<inputLine.size(); i++){
            if (inputLine.get(i).equals(op)){
                if (op.equals("+")) {
                    inputLine.set(i - 1, "" + (Double.parseDouble(inputLine.get(i-1)) + Double.parseDouble(inputLine.get(i+1))));
                    inputLine.remove(i);
                    inputLine.remove(i);
                    i-=1;
                }
                else if (op.equals("-")) {
                    inputLine.set(i - 1, "" + (Double.parseDouble(inputLine.get(i-1)) - Double.parseDouble(inputLine.get(i+1))));
                    inputLine.remove(i);
                    inputLine.remove(i);
                    i-=1;
                }
                else if (op.equals("×")) {
                    inputLine.set(i - 1, "" + (Double.parseDouble(inputLine.get(i-1)) * Double.parseDouble(inputLine.get(i+1))));
                    inputLine.remove(i);
                    inputLine.remove(i);
                    i-=1;
                }
                else if (op.equals("÷")) {
                    inputLine.set(i - 1, "" + (Double.parseDouble(inputLine.get(i-1)) / Double.parseDouble(inputLine.get(i+1))));
                    inputLine.remove(i);
                    inputLine.remove(i);
                    i-=1;
                }
                else if (op.equals("√")) {
                    inputLine.set(i, "" + Math.sqrt(Double.parseDouble(inputLine.get(i+1))));
                    inputLine.remove(i+1);
                }
                else if (op.equals("^")) {
                    inputLine.set(i - 1, "" + Math.pow(Double.parseDouble(inputLine.get(i-1)), Double.parseDouble(inputLine.get(i+1))));
                    inputLine.remove(i);
                    inputLine.remove(i);
                    i-=1;
                }
                else if (op.equals("%")) {
                    inputLine.set(i - 1, "" + (Double.parseDouble(inputLine.get(i-1)) % Double.parseDouble(inputLine.get(i+1))));
                    inputLine.remove(i);
                    inputLine.remove(i);
                    i-=1;
                }
                else if (op.equals("ln")) {
                    inputLine.set(i, "" + Math.log(Double.parseDouble(inputLine.get(i+1))));
                    inputLine.remove(i+1);
                }
            }
        }
    }

    public void printResult(View v){
        TextView outputTV = findViewById(R.id.outputView);
        TextView inputTV = findViewById(R.id.inputView);

        if (!enterPressed) {
            inputTV.setTextSize(24);
            ColorStateList c = inputTV.getTextColors();
            inputTV.setTextColor(outputTV.getTextColors());
            outputTV.setTextSize(32);
            outputTV.setTextColor(c);
            enterPressed = true;
        }
    }

    public void hintResult(View v){
        TextView outputTV = findViewById(R.id.outputView);
        ArrayList<String> tempLine = new ArrayList<>();
        for (String s : inputLine){
            tempLine.add(s);
        }
        if (inputLine.size() > 0) {
            while (tempLine.size() > 0 && equalsOperator(tempLine.get(tempLine.size()-1))){
                tempLine.remove(tempLine.size()-1);
            }
            findAndCalc("ln", tempLine);
            findAndCalc("√", tempLine);
            findAndCalc("^", tempLine);
            findAndCalc("%", tempLine);
            findAndCalc("÷", tempLine);
            findAndCalc("×", tempLine);
            findAndCalc("-", tempLine);
            findAndCalc("+", tempLine);
            if (tempLine.size() > 0 && !tempLine.get(0).equals("")) {
                outputTV.setText("" + Double.parseDouble(tempLine.get(0)));
            }
            else{
                outputTV.setText("0.0");
            }
        }
        else {
            outputTV.setText("0.0");
        }
        if (outputTV.getText().equals("-Infinity") ||
            outputTV.getText().equals("Infinity") ||
            outputTV.getText().equals("-Infinity")){
            outputTV.setText("No Real Solution");
        }
    }

    public void plusInput(View v){
        TextView plusTV = findViewById(R.id.plusBtn);
        if (plusTV.getText().equals("+")) {
            inputOperator("+");
        }
        else if (!enterPressed){
            inputOperator("√");
        }
    }

    public void minusInput(View v){
        TextView minusTV = findViewById(R.id.minusBtn);
        if (minusTV.getText().equals("-")) {
            inputOperator("-");
        }
        else{
            inputOperator("^");
        }
    }

    public void multiplyInput(View v){
        TextView multiplyTV = findViewById(R.id.multiplyBtn);
        if (multiplyTV.getText().equals("×")) {
            inputOperator("×");
        }
        else{
            inputOperator("%");
        }
    }

    public void divideInput(View v){
        TextView divideTV = findViewById(R.id.divideBtn);
        if (divideTV.getText().equals("÷")) {
            inputOperator("÷");
        }
        else if (!enterPressed){
            inputOperator("ln");
        }
    }

    public void switchFxns(View v){
        TextView plusTV = findViewById(R.id.plusBtn);
        TextView minusTV = findViewById(R.id.minusBtn);
        TextView multiplyTV = findViewById(R.id.multiplyBtn);
        TextView divideTV = findViewById(R.id.divideBtn);
        TextView moreFxnsTV = findViewById(R.id.moreBtn);
        if (plusTV.getText().equals("+")) {
            plusTV.setText("√");
            minusTV.setText("^");
            multiplyTV.setText("%");
            divideTV.setText("ln");
            moreFxnsTV.setText("Back");
        }
        else {
            plusTV.setText("+");
            minusTV.setText("-");
            multiplyTV.setText("×");
            divideTV.setText("÷");
            moreFxnsTV.setText("More Fxns");
        }
    }

    public void number1Input(View v){
        inputNum(v, 1);
        hintResult(v);
    }

    public void number2Input(View v){
        inputNum(v, 2);
        hintResult(v);
    }

    public void number3Input(View v){
        inputNum(v, 3);
        hintResult(v);
    }

    public void number4Input(View v){
        inputNum(v, 4);
        hintResult(v);
    }

    public void number5Input(View v){
        inputNum(v, 5);
        hintResult(v);
    }

    public void number6Input(View v){
        inputNum(v, 6);
        hintResult(v);
    }

    public void number7Input(View v){
        inputNum(v, 7);
        hintResult(v);
    }

    public void number8Input(View v){
        inputNum(v, 8);
        hintResult(v);
    }

    public void number9Input(View v){
        inputNum(v, 9);
        hintResult(v);
    }

    public void number0Input(View v){
        inputNum(v, 0);
        hintResult(v);
    }

    public void backspace(View v){
        TextView inputTV = findViewById(R.id.inputView);
        TextView outputTV = findViewById(R.id.outputView);
        if (enterPressed) {
            outputTV.setTextSize(24);
            ColorStateList c = outputTV.getTextColors();
            outputTV.setTextColor(inputTV.getTextColors());
            inputTV.setTextSize(32);
            inputTV.setTextColor(c);
            enterPressed = false;
            clearAll(v);
        }
        if (inputLine.size() != 0){
            if (inputTV.getText().length() > 1 &&
                    ((String)inputTV.getText()).substring(inputTV.getText().length()-2).equals("ln")){
                inputTV.setText(((String)inputTV.getText()).substring(0, inputTV.getText().length()-1));
            }
            inputTV.setText(((String) inputTV.getText()).substring(0, inputTV.getText().length() - 1));
            if (inputTV.getText().equals("")){
                inputTV.setText("0");
                inputLine.clear();
                inputLine.add("0");
            }
            if (!inputLine.get(inputLine.size()-1).equals("ln")) {
                inputLine.set(inputLine.size() - 1,
                        inputLine.get(inputLine.size() - 1).substring(0, inputLine.get(inputLine.size() - 1).length() - 1));
            }
            else{
                inputLine.set(inputLine.size() - 1,
                        inputLine.get(inputLine.size() - 1).substring(0, inputLine.get(inputLine.size() - 1).length() - 2));
            }
            if (inputLine.get(inputLine.size()-1).equals("")){
                inputLine.remove(inputLine.size()-1);
            }
            if (inputTV.getText().length() > 0) {
                hintResult(v);
            }
        }
    }

    public void clearAll(View v){
        TextView inputTV = findViewById(R.id.inputView);
        TextView outputTV = findViewById(R.id.outputView);
        if (enterPressed) {
            outputTV.setTextSize(24);
            ColorStateList c = outputTV.getTextColors();
            outputTV.setTextColor(inputTV.getTextColors());
            inputTV.setTextSize(32);
            inputTV.setTextColor(c);
            enterPressed = false;
            clearAll(v);
        }
        inputLine.clear();
        inputLine.add("0");
        inputTV.setText("0");
        hintResult(v);
    }
}