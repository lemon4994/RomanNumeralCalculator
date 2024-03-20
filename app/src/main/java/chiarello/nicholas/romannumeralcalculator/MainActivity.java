package chiarello.nicholas.romannumeralcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String input = "";
    int result = 0;
    boolean answered = false;
    Roman roman = new Roman();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * Adds the selected letter to the display, or removes one if the backspace key is pressed.
     * @param view One of the letters or "<".
     */
    public void updateInput(View view){
        if(answered){
            ((TextView)findViewById(R.id.answer)).setText("");
            input = "";
            answered = false;
        }
        if(view.getId() == R.id.buttonI) {
            input = input + "I";
        }else if(view.getId() == R.id.buttonV) {
            input = input + "V";
        }else if(view.getId() == R.id.buttonX) {
            input = input + "X";
        }else if(view.getId() == R.id.buttonL) {
            input = input + "L";
        }else if(view.getId() == R.id.buttonC) {
            input = input + "C";
        }else if(view.getId() == R.id.buttonD) {
            input = input + "D";
        }else if(view.getId() == R.id.buttonM) {
            input = input + "M";
        }else if((view.getId() == R.id.buttonBack) && !(input.equals(""))) {
            input = input.substring(0, input.length()-1);
        }
        ((TextView)findViewById(R.id.answer)).setText(input);
    }

    /**
     * Passes the input to Roman which changes it to a Number if possible.
     * @param view The button that is pressed, should be the "=" button.
     */
    public void convert(View view) {
        answered = true;
        if(view.getId() == R.id.buttonEqual){
            result = roman.convertToInt(input);
            if (result == -1){
                ((TextView)findViewById(R.id.answer)).setText("Incorrect Format");
            }else if(result == -2){
                ((TextView)findViewById(R.id.answer)).setText("Invalid Characters");
            }else if(result == -3){
                ((TextView)findViewById(R.id.answer)).setText("Too Big");
            }else{
                ((TextView)findViewById(R.id.answer)).setText(String.format("%d", result));
            }
        }
        input = "";
    }

    /**
     * Will switch page (and buttons) in order to convert to a Numeral. The app will not have a history and switching back and forth does not cause Back to cycle through the pages in reverse.
     * @param view The button that is pressed, should be the large button on the top of the screen.
     */
    public void switchPage(View view){
        if(view.getId() == R.id.toNumerals){
            result = 0;
            input = "";
            answered = false;
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
        }
    }
}
