package net.adamallard.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static boolean correct = true;
    static int rand;

    public void guess(View view) {
        Random randGen = new Random();
        TextView msg = (TextView) findViewById(R.id.msg);
        EditText input = (EditText)findViewById(R.id.input);

        if (correct) {
            rand = randGen.nextInt(100) + 1;
            correct = false;
        }

        try {
            if (Integer.parseInt(input.getText().toString()) < rand) {
                msg.setText("Too low");
            } else if (Integer.parseInt(input.getText().toString()) > rand) {
                msg.setText("Too high");
            } else {
                msg.setText("You got it!\nPlay Again?");
                correct = true;
            }
        }catch(NumberFormatException e){
            msg.setText("Enter a number!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
