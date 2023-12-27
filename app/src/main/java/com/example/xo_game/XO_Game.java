package com.example.xo_game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class XO_Game<MainActivity> extends AppCompatActivity {

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
            alertDialog(this, "Player 1 Won!", "Do you want to play again?");
        } else if (checkWinner("O")) {
            initBoard();
            playerTwoScore++;
            playerTwoScoreTV.setText(playerTwoScore + "");
            alertDialog(this, "Player 2 Won!", "Do you want to play again?");
        } else if (counter == 9) {
            initBoard();
            alertDialog(this, "Draw!", "Do you want to play again?");
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

    private void alertDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.dismiss();
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
            playerOneScore = 0;
            playerTwoScore = 0;
            playerOneScoreTV.setText("0");
            playerTwoScoreTV.setText("0");
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}