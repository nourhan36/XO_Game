package com.example.xo_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class XO_Game extends AppCompatActivity {

    TextView playerOneScoreTV;
    TextView playerTwoScoreTV;
    ConstraintLayout mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xo_game);
        playerOneScoreTV = findViewById(R.id.tv_scorePlayer1);
        playerTwoScoreTV = findViewById(R.id.tv_scorePlayer2);
        mainView = findViewById(R.id.xoGame);
    }

    int playerOneScore = 0;
    int playerTwoScore = 0;
    int counter = 0;

    public void onPlayerClick(View view) {
        Button clickedButton = ((Button) view);
        if (!clickedButton.getText().toString().isEmpty())
            return;

        if (counter % 2 == 0)
            clickedButton.setText("X");
        else
            clickedButton.setText("O");

        counter++;
        if (checkWinner("X")) {
            initBoard();
            playerOneScore++;
            playerOneScoreTV.setText(playerOneScore + "");
        } else if (checkWinner("O")) {
            initBoard();
            playerTwoScore++;
            playerTwoScoreTV.setText(playerTwoScore + "");
        } else if (counter == 9) {
            initBoard();
        }
    }

    void initBoard() {
        for (int i = 0; i < 9; i++)
            ((Button) mainView.findViewWithTag(i + "")).setText("");

        counter = 0;
    }

    private boolean checkWinner(String playerSymbol) {
        for (int i = 0; i < 9; i += 3) {
            if (((Button) mainView.findViewWithTag(i + "")).getText().toString().equals(playerSymbol)
                    && ((Button) mainView.findViewWithTag((i + 1) + "")).getText().toString().equals(playerSymbol)
                    && ((Button) mainView.findViewWithTag((i + 2) + "")).getText().toString().equals(playerSymbol))
                return true;
        }
        for (int i = 0; i < 3; i++) {
            if (((Button) mainView.findViewWithTag(i + "")).getText().toString().equals(playerSymbol)
                    && ((Button) mainView.findViewWithTag((i + 3) + "")).getText().toString().equals(playerSymbol)
                    && ((Button) mainView.findViewWithTag((i + 6) + "")).getText().toString().equals(playerSymbol))
                return true;
        }
        if (((Button) mainView.findViewWithTag(0 + "")).getText().toString().equals(playerSymbol)
                && ((Button) mainView.findViewWithTag(4 + "")).getText().toString().equals(playerSymbol)
                && ((Button) mainView.findViewWithTag(8 + "")).getText().toString().equals(playerSymbol)) {
            return true;
        }

        if (((Button) mainView.findViewWithTag(2 + "")).getText().toString().equals(playerSymbol)
                && ((Button) mainView.findViewWithTag(4 + "")).getText().toString().equals(playerSymbol)
                && ((Button) mainView.findViewWithTag(6 + "")).getText().toString().equals(playerSymbol)) {
            return true;
        }

        return false;
    }

}