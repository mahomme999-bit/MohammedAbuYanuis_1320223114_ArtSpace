package com.example.artspace;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "current_artwork_index";

    private TextView tvCounter, tvTitle, tvArtistYear;
    private ImageView ivArtwork;
    private Button btnPrevious, btnNext;

    private int currentIndex = 0;


    private final int[] artworkImages = {
            R.drawable.art1,
            R.drawable.art2,
            R.drawable.art3,
            R.drawable.art4,
            R.drawable.art5,
            R.drawable.art6
    };

    private final String[] artworkTitles = {
            "مروّض السلاحف",
            "تمثال نهضة مصر",
            "نصب الحرية",
            "عازفة العود",
            "أهرامات الجيزة",
            "قبة الصخرة المشرفة"
    };


    private final String[] artistNames = {
            "عثمان حمدي بك",
            "محمود مختار",
            "كارليس زالي",
            "أوراتسيو جنتيلسكي",
            "حم إيونو",
            "رجاء بن حيوة"
    };


    private final String[] creationYears = {
            "1906",
            "1928",
            "1935",
            "1612",
            "2560 ق.م",
            "691"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left + 40, systemBars.top + 40, systemBars.right + 40, systemBars.bottom + 40);
            return insets;
        });


        tvCounter = findViewById(R.id.tvCounter);
        ivArtwork = findViewById(R.id.ivArtwork);
        tvTitle = findViewById(R.id.tvTitle);
        tvArtistYear = findViewById(R.id.tvArtistYear);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);


        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }


        updateArtworkUI();


        btnPrevious.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateArtworkUI();
            }
        });


        btnNext.setOnClickListener(v -> {
            if (currentIndex < artworkImages.length - 1) {
                currentIndex++;
                updateArtworkUI();
            }
        });


        ivArtwork.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);
            intent.putExtra("TITLE", artworkTitles[currentIndex]);
            intent.putExtra("ARTIST", artistNames[currentIndex]);
            intent.putExtra("YEAR", creationYears[currentIndex]);
            startActivity(intent);
        });
    }

    private void updateArtworkUI() {
        int total = artworkImages.length;


        tvCounter.setText((currentIndex + 1) + "/" + total);


        ivArtwork.setImageResource(artworkImages[currentIndex]);
        tvTitle.setText(artworkTitles[currentIndex]);
        tvArtistYear.setText(artistNames[currentIndex] + " (" + creationYears[currentIndex] + ")");


        btnPrevious.setEnabled(currentIndex > 0);
        btnNext.setEnabled(currentIndex < total - 1);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, currentIndex);
    }
}
