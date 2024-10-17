package com.example.ko_papir_ollo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int JatekosPont = 0;
    private int GepPont = 0;
    private TextView eredmenyTextView;
    private TextView jatekospontTextView;
    private TextView geppontTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView scissors = findViewById(R.id.Ollo);
        ImageView paper = findViewById(R.id.Papir);
        ImageView rock = findViewById(R.id.Ko);
        eredmenyTextView = findViewById(R.id.Eredmeny);
        jatekospontTextView = findViewById(R.id.Jatekospont);
        geppontTextView = findViewById(R.id.Geppont);

        scissors.setOnClickListener(v -> playGame(2)); // Olló
        paper.setOnClickListener(v -> playGame(1)); // Papír
        rock.setOnClickListener(v -> playGame(0)); // Kő
    }

    private void playGame(int playerChoice) {
        int computerChoice = new Random().nextInt(3); // 0: Kő, 1: PapÍr, 2: Olló
        int result = determineWinner(playerChoice, computerChoice);

        switch (result) {
            case 1:
                JatekosPont++;
                eredmenyTextView.setText("Te nyertél!");
                break;
            case -1:
                GepPont++;
                eredmenyTextView.setText("Számítógép nyert!");
                break;
            case 0:
                eredmenyTextView.setText("Döntetlen!");
                break;
        }

        PontokKimutatasa();

        if (JatekosPont == 3 || GepPont == 3) {
            showEndGameDialog();
        }
    }

    private int determineWinner(int player, int computer) {
        if (player == computer) return 0;
        if ((player == 0 && computer == 2) || (player == 1 && computer == 0) || (player == 2 && computer == 1)) {
            return 1; // Player wins
        } else {
            return -1; // Computer wins
        }
    }

    private void PontokKimutatasa() {
        jatekospontTextView.setText("Játékos pontja: " + JatekosPont);
        geppontTextView.setText("Számítógép pontja: " + GepPont);
    }
    private void showEndGameDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(JatekosPont == 3 ? "Győzelem" : "Vereség");
        alertDialog.setMessage("Szeretnél új játékot játszani?");
        alertDialog.setPositiveButton("Igen", (dialog, which) -> resetGame());
        alertDialog.setNegativeButton("Nem", (dialog, which) -> finish());
        alertDialog.show();
    }

    private void resetGame() {
        JatekosPont = 0;
        GepPont = 0;
        PontokKimutatasa();
        eredmenyTextView.setText("Eredmény");
    }
}