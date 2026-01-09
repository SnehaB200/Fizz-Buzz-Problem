package com.example.fizzbizzapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StartGameActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView randomNum;
    private Button fizzBtn, buzzBtn, fizzBuzzBtn, nextBtn, hintBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        randomNum = findViewById(R.id.randomNumber);
        fizzBtn = findViewById(R.id.fizzBTN);
        buzzBtn = findViewById(R.id.buzzBTN);
        fizzBuzzBtn = findViewById(R.id.fizzBuzzBTN);
        nextBtn = findViewById(R.id.nextBTN);
        hintBtn = findViewById(R.id.hintBTN);

        generateRandomNum();

        fizzBtn.setOnClickListener(this);
        buzzBtn.setOnClickListener(this);
        fizzBuzzBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        hintBtn.setOnClickListener(this);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(StartGameActivity.this)
                        .setMessage("Are you sure you want to EXIT??")
                        .setCancelable(false)
                        .setPositiveButton("Yes", (dialog, which) -> finish())
                        .setNegativeButton("No", (dialog, which) -> dialog.cancel())
                        .show();
            }
        });
    }

    public void generateRandomNum(){
        int num = (int) Math.floor((Math.random() * 100) + 1 );
        randomNum.setText(String.valueOf(num));
    }

    public void showHintDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Hint")
                .setMessage("Fizz: Number is divisible by 3.\n\n" +
                        "Buzz: Number is divisible by 5.\n\n" +
                        "FizzBuzz: Number is divisible by both 3 and 5.")
                .setPositiveButton("OK", null)
                .show();
    }

    public void onClick(View v){
        int ranNum = Integer.parseInt(randomNum.getText().toString());
        int id = v.getId();
        if (id == R.id.fizzBTN) {
            if (ranNum % 3 == 0 && ranNum % 5 != 0) {
                generateRandomNum();
            } else {
                alertDialog();
            }
        } else if (id == R.id.buzzBTN) {
            if (ranNum % 5 == 0 && ranNum % 3 != 0) {
                generateRandomNum();
            } else {
                alertDialog();
            }
        }else if (id == R.id.fizzBuzzBTN) {
            if (ranNum % 15 == 0) {
                generateRandomNum();
            } else {
                alertDialog();
            }
        } else if (id == R.id.nextBTN) {
            if (ranNum % 3 != 0 && ranNum % 5 != 0) {
                generateRandomNum();
            } else {
                alertDialog();
            }
        }else if (id == R.id.hintBTN) {
            showHintDialog();
        }
    }

    public void alertDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Game Over")
                .setMessage("Wrong answer, would you like to play again?")
                .setPositiveButton("Play Again", (dialog, which) -> generateRandomNum())
                .setNegativeButton("Exit", (dialog, which) -> finish())
                .setCancelable(false)
                .show();
    }
}