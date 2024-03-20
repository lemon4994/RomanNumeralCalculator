package chiarello.nicholas.romannumeralcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends Activity {
    String input = "";
    String result = "";
    boolean answered = false;
    Roman roman = new Roman();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    /**
     * Updates the display with the numbers you put in, or deletes one if backspace is pressed. 0 is not allowed when the field is empty.
     * @param view One of the numbers or "<".
     */
    public void updateInput(View view) {
        if(answered){
            ((TextView)findViewById(R.id.answer2)).setText("");
            answered = false;
        }

        if(view.getId() == R.id.button1){
            input = input + "1";
        }else if(view.getId() == R.id.button2){
            input = input + "2";
        }else if(view.getId() == R.id.button3){
            input = input + "3";
        }else if(view.getId() == R.id.button4){
            input = input + "4";
        }else if(view.getId() == R.id.button5){
            input = input + "5";
        }else if(view.getId() == R.id.button6){
            input = input + "6";
        }else if(view.getId() == R.id.button7){
            input = input + "7";
        }else if(view.getId() == R.id.button8){
            input = input + "8";
        }else if(view.getId() == R.id.button9){
            input = input + "9";
        }else if((view.getId() == R.id.button0) && !(input.equals(""))){
            input = input + "0";
        }else if((view.getId() == R.id.buttonBack) && !(input.equals(""))) {
            input = input.substring(0, input.length()-1);
        }
        ((TextView)findViewById(R.id.answer2)).setText(input);
    }

    /**
     * Passes the input to Roman, which converts it to a Numeral.
     * @param view The button that is pressed, should be "=".
     */
    public void convert(View view){
        answered = true;
        if((view.getId() == R.id.buttonEqual) && !(input.equals(""))){
            if (Long.parseLong(input) >= 5000){
                ((TextView)findViewById(R.id.answer2)).setText("Too Big");
            }else{
                ((TextView)findViewById(R.id.answer2)).setText(roman.convertToString(Short.parseShort(input)));
            }
        }
        input = "";
    }

    /**
     * Switches to the page that converts Numerals to Numbers.
     * @param view The button that is pressed, should be the large one at the top.
     */
    public void switchPage(View view){
        if(view.getId() == R.id.toNumbers){
            result = "";
            input = "";
            answered = false;
            startActivity(new Intent(Main2Activity.this, MainActivity.class));
        }
    }
}
