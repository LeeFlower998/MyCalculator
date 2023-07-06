package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int flag_isZero = 0;
    private TextView textView2;
    private TextView textView3;
    private Button button_0;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_8;
    private Button button_9;
    private Button button_point;
    private Button button_plus;
    private Button button_subtract;
    private Button button_multiply;
    private Button button_divide;
    private Button button_sin;
    private Button button_cos;
    private Button button_tan;
    private Button button_sqrt;
    private Button button_DEL;
    private Button button_AC;
    private Button button_equal;
    private Button button_exchange;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        button_0 = (Button) findViewById(R.id.Button_0);
        button_1 = (Button) findViewById(R.id.Button_1);
        button_2 = (Button) findViewById(R.id.Button_2);
        button_3 = (Button) findViewById(R.id.Button_3);
        button_4 = (Button) findViewById(R.id.Button_4);
        button_5 = (Button) findViewById(R.id.Button_5);
        button_6 = (Button) findViewById(R.id.Button_6);
        button_7 = (Button) findViewById(R.id.Button_7);
        button_8 = (Button) findViewById(R.id.Button_8);
        button_9 = (Button) findViewById(R.id.Button_9);
        button_point = (Button) findViewById(R.id.Button_point);
        button_plus = (Button) findViewById(R.id.Button_plus);
        button_subtract = (Button) findViewById(R.id.Button_subtract);
        button_multiply = (Button) findViewById(R.id.Button_multiply);
        button_divide = (Button) findViewById(R.id.Button_divide);
        button_sin = (Button) findViewById(R.id.Button_sin);
        button_cos = (Button) findViewById(R.id.Button_cos);
        button_tan = (Button) findViewById(R.id.Button_tan);
        button_sqrt = (Button) findViewById(R.id.Button_sqrt);
        button_DEL = (Button) findViewById(R.id.Button_DEL);
        button_AC = (Button) findViewById(R.id.Button_AC);
        button_equal = (Button) findViewById(R.id.Button_equal);
        button_exchange = (Button) findViewById(R.id.Button_exchange);

        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_point.setOnClickListener(this);
        button_plus.setOnClickListener(this);
        button_subtract.setOnClickListener(this);
        button_multiply.setOnClickListener(this);
        button_divide.setOnClickListener(this);
        button_sin.setOnClickListener(this);
        button_cos.setOnClickListener(this);
        button_tan.setOnClickListener(this);
        button_sqrt.setOnClickListener(this);
        button_DEL.setOnClickListener(this);
        button_AC.setOnClickListener(this);
        button_equal.setOnClickListener(this);
        button_exchange.setOnClickListener(this);
    }

    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.Button_equal) calculator();
        else if (id == R.id.Button_0) appendNumber("0");
        else if (id == R.id.Button_1) appendNumber("1");
        else if (id == R.id.Button_2) appendNumber("2");
        else if (id == R.id.Button_3) appendNumber("3");
        else if (id == R.id.Button_4) appendNumber("4");
        else if (id == R.id.Button_5) appendNumber("5");
        else if (id == R.id.Button_6) appendNumber("6");
        else if (id == R.id.Button_7) appendNumber("7");
        else if (id == R.id.Button_8) appendNumber("8");
        else if (id == R.id.Button_9) appendNumber("9");
        else if (id == R.id.Button_point) appendNumber(".");
        else if (id == R.id.Button_plus) appendOperator("+");
        else if (id == R.id.Button_subtract) appendOperator("-");
        else if (id == R.id.Button_multiply) appendOperator("×");
        else if (id == R.id.Button_divide) appendOperator("÷");
        else if (id == R.id.Button_sin) arithmetic("sin");
        else if (id == R.id.Button_cos) arithmetic("cos");
        else if (id == R.id.Button_tan) arithmetic("tan");
        else if (id == R.id.Button_sqrt) arithmetic("√");
        else if (id == R.id.Button_DEL) delete();
        else if (id == R.id.Button_AC) clear();
        else if (id == R.id.Button_exchange) exchange();
    }

    private void calculator() {
        String currentText2 = textView2.getText().toString();
        String currentText3 = textView3.getText().toString();
        String expression = "";
        if (!currentText2.isEmpty() && currentText2.charAt(currentText2.length() - 1) == '=')
            return;
        if (!currentText3.equals("error")) {
            expression = currentText2 + currentText3;
            String newText2 = expression + "=";
            textView2.setText(newText2);
            textView3.setText("");
            if (expression.isEmpty()) return;
            else if (!isValidExpression(expression)) {
                textView3.setText("error");
                return;
            }
        } else return;

        Stack<BigDecimal> numberStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char previousChar = ' ';
            char currentChar = expression.charAt(i);
            char nextChar = ' ';
            if (i > 0) previousChar = expression.charAt(i - 1);
            if (i + 1 < expression.length()) nextChar = expression.charAt(i + 1);

            if (Character.isDigit(currentChar) || (i == 0 && currentChar == '-') || (i > 0 && i + 1 < expression.length() && isOperator(previousChar) && !isOperator(nextChar) && currentChar == '-')) {
                StringBuilder sb = new StringBuilder();
                sb.append(currentChar);
                while (i + 1 < expression.length() && (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '.')) {
                    sb.append(expression.charAt(i + 1));
                    i++;
                }
                BigDecimal number = new BigDecimal(sb.toString());
                numberStack.push(number);
            } else if (isOperator(currentChar)) {
                while (!operatorStack.isEmpty() && hasHigherPriority(operatorStack.peek(), currentChar)) {
                    performOperation(numberStack, operatorStack);
                }
                operatorStack.push(currentChar);
            }
        }

        while (!operatorStack.isEmpty()) {
            performOperation(numberStack, operatorStack);
            if (flag_isZero == 1) {
                textView3.setText("error");
                return;
            }
        }

        // 取整，避免整数结果出现小数点
        BigDecimal result = round(numberStack.pop());

        // 如果结果的有效数字>13，则提示error
        if (result.toString().length() > 13) textView3.setText("error");
        else textView3.setText(result.toString());
    }

    private void performOperation(Stack<BigDecimal> numberStack, Stack<Character> operatorStack) {
        char operator = operatorStack.pop();
        BigDecimal operand2 = numberStack.pop();
        BigDecimal operand1 = numberStack.pop();
        BigDecimal result = null;

        // 检查除数是否为0
        if (operator == '÷' && operand2.compareTo(BigDecimal.ZERO) == 0) {
            flag_isZero = 1;
            return;
        }

        switch (operator) {
            case '+':
                result = operand1.add(operand2);
                // 如果有小数部分，则至多保留13位有效数字
                if (result.scale() > 0) {
                    String temp = result.toString();
                    temp = temp.substring(0, Math.min(temp.length(), 13));
                    result = new BigDecimal(temp);
                }
                break;
            case '-':
                result = operand1.subtract(operand2);
                if (result.scale() > 0) {
                    String temp = result.toString();
                    temp = temp.substring(0, Math.min(temp.length(), 13));
                    result = new BigDecimal(temp);
                }
                break;
            case '×':
                result = operand1.multiply(operand2);
                if (result.scale() > 0) {
                    String temp = result.toString();
                    temp = temp.substring(0, Math.min(temp.length(), 13));
                    result = new BigDecimal(temp);
                }
                break;
            case '÷':
                result = operand1.divide(operand2, 13, RoundingMode.HALF_UP);
                if (result.scale() > 0) {
                    String temp = result.toString();
                    temp = temp.substring(0, Math.min(temp.length(), 13));
                    result = new BigDecimal(temp);
                }
                break;
        }
        numberStack.push(result);
    }

    private void appendNumber(String number) {
        String currentText2 = textView2.getText().toString();
        String currentText3 = textView3.getText().toString();
        if (number == ".") {
            if (currentText3.isEmpty() || (!currentText2.isEmpty() && currentText2.charAt(currentText2.length() - 1) == '=')) {
                textView2.setText("");
                textView3.setText("0.");
            }
            else {
                if (currentText3.equals("error")) return;
                if (currentText2.isEmpty() || (!currentText2.isEmpty() && currentText2.charAt(currentText2.length() - 1) != '=')) {
                    for (int i = 0; i < currentText3.length(); i++) {
                        char chr = currentText3.charAt(i);
                        if (chr == '.')
                            return;
                    }
                    String newText3 = currentText3 + number;
                    textView3.setText(newText3);
                }
            }
        } else {
            if (currentText3.equals("error")) return;
            if (currentText2.isEmpty() || (!currentText2.isEmpty() && currentText2.charAt(currentText2.length() - 1) != '=')) {
                String newText3 = currentText3 + number;
                textView3.setText(newText3);
            }
            if (!currentText2.isEmpty() && currentText2.charAt(currentText2.length() - 1) == '=') {
                textView2.setText("");
                textView3.setText(number);
            }
        }
    }

    private void appendOperator(String operator) {
        String currentText2 = textView2.getText().toString();
        String currentText3 = textView3.getText().toString();
        if (currentText3.isEmpty() || currentText3.equals("error")) return;
        if (currentText2.isEmpty() || currentText2.charAt(currentText2.length() - 1) != '=') {
            String newText2 = currentText2 + currentText3.substring(0, Math.min(currentText3.length(), 13)) + operator;
            textView2.setText(newText2);
        } else {
            textView2.setText("");
            String newText2 = currentText3.substring(0, Math.min(currentText3.length(), 13)) + operator;
            textView2.setText(newText2);
        }
        textView3.setText("");
    }

    private void arithmetic(String operator) {
        String currentText2 = textView2.getText().toString();
        String currentText3 = textView3.getText().toString();
        if (currentText3.isEmpty() || currentText3.equals("error")) return;
        else {
            String newText2 = null;
            double temp = 0;
            BigDecimal result = null;
            if (operator.equals("sin")) {
                newText2 = currentText2 + "sin" + currentText3 + "=";
                temp = Math.sin(Math.toRadians(new BigDecimal(currentText3).doubleValue()));
            } else if (operator.equals("cos")) {
                newText2 = currentText2 + "cos" + currentText3 + "=";
                temp = Math.cos(Math.toRadians(new BigDecimal(currentText3).doubleValue()));
            } else if (operator.equals("tan")) {
                newText2 = currentText2 + "tan" + currentText3 + "=";
                temp = Math.tan(Math.toRadians(new BigDecimal(currentText3).doubleValue()));
            } else if (operator.equals("√")) {
                newText2 = currentText2 + "√" + currentText3 + "=";
                if (new BigDecimal(currentText3).compareTo(new BigDecimal(0)) > 0)
                    temp = Math.sqrt(new BigDecimal(currentText3).doubleValue());
                else {
                    textView2.setText(newText2);
                    textView3.setText("error");
                    return;
                }
            }
            // 取整，避免整数结果出现小数点
            result = round(BigDecimal.valueOf(temp));
            // 如果有小数部分，则至多保留13位有效数字
            if (result.scale() > 0) {
                String temp2 = result.toString();
                temp2 = temp2.substring(0, Math.min(temp2.length(), 13));
                result = new BigDecimal(temp2);
            }
            if (!currentText2.isEmpty() && currentText2.charAt(currentText2.length() - 1) == '=')
                newText2 = operator + currentText3 + "=";
            if (!currentText2.isEmpty() && currentText2.charAt(currentText2.length() - 1) != '=') {
                textView3.setText(result.toString());
                calculator();
            } else {
                textView3.setText(result.toString());
            }
            textView2.setText(newText2);
        }
    }

    private void delete() {
        String currentText2 = textView2.getText().toString();
        String currentText3 = textView3.getText().toString();
        if (currentText3.isEmpty() || currentText3.equals("error")) return;
        if (currentText2.isEmpty() || currentText2.charAt(currentText2.length() - 1) != '=') {
            String newText3 = currentText3.substring(0, currentText3.length() - 1);
            textView3.setText(newText3);
        } else {
            textView2.setText("");
            String newText3 = currentText3.substring(0, currentText3.length() - 1);
            textView3.setText(newText3);
        }
    }

    private void clear() {
        textView2.setText("");
        textView3.setText("");
        flag_isZero = 0;
    }

    private void exchange() {
        String currentText2 = textView2.getText().toString();
        String currentText3 = textView3.getText().toString();
        if (currentText3.isEmpty() || currentText3.equals("error")) return;
        String newText3;
        if (currentText3.charAt(0) == '-') {
            newText3 = currentText3.substring(1);
        } else {
            newText3 = "-" + currentText3;
        }
        textView3.setText(newText3);
        if (!currentText2.isEmpty() && currentText2.charAt(currentText2.length() - 1) == '=')
            textView2.setText("");
    }

    private boolean isValidExpression(String expression) {
        // 检查算式是否为error
        if (expression.equals("error")) return false;

        // 检查算式的结尾是否为运算符
        char lastChar = expression.charAt(expression.length() - 1);
        if (isOperator(lastChar)) return false;

        // 检查算式中是否有非法的连续运算符
        for (int i = 0; i < expression.length() - 1; i++) {
            char currentChar = expression.charAt(i);
            char nextChar = expression.charAt(i + 1);
            if ((isOperator(currentChar) && isOperator(nextChar) && nextChar != '-') || (currentChar == '-' && nextChar == '-'))
                return false;
        }

        // 算式合法
        return true;
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '×' || ch == '÷';
    }

    private boolean hasHigherPriority(char op1, char op2) {
        return (op1 == '×' || op1 == '÷') && (op2 == '+' || op2 == '-');
    }

    private BigDecimal round(BigDecimal data) {
        int temp1 = data.intValue();
        BigDecimal temp2 = BigDecimal.valueOf(temp1);
        if (data.compareTo(temp2) != 0) return data;
        else return temp2;
    }
}
