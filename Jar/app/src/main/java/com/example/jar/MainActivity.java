package com.example.jar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView messageText;
    private ImageView jarImage;
    private MediaPlayer mediaPlayer;
    private String[] loveMessages = {
            "You are my sunshine! ☀️",
            "I love you to the moon and back! 🌙",
            "Hug me now! 🤗",
            "You're my favorite person! ❤️",
            "Every moment with you is magical! ✨",
            "You make my heart skip a beat! 💓",
            "Your smile lights up my day! 😊",
            "I am so lucky to have you! 🍀",
            "You're my heart’s greatest treasure! 💖",
            "You are my happy place! 🏡",
            "Forever isn't long enough with you! 💍",
            "You complete me! 🧩",
            "I fall for you more every day! 🍂",
            "You're my dream come true! ✨",
            "Being with you is the best part of my day! 🌈"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageText = findViewById(R.id.messageText);
        jarImage = findViewById(R.id.jarImage);
        mediaPlayer = MediaPlayer.create(this, R.raw.love_sound); // Play sound on tap

        jarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRandomMessage();
            }
        });
    }

    private void showRandomMessage() {
        Random random = new Random();
        int index = random.nextInt(loveMessages.length);
        messageText.setText(loveMessages[index]);

        // Play sound
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }

        // Add animation
        jarImage.animate().rotation(20).setDuration(100).withEndAction(() ->
                jarImage.animate().rotation(-20).setDuration(100).start()
        ).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
