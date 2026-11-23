package com.example.artspace;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ArtworkDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_artwork_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left + 48, systemBars.top + 48, systemBars.right + 48, systemBars.bottom + 48);
            return insets;
        });

        TextView tvDetails = findViewById(R.id.tvDetails);
        Button btnVisitWebsite = findViewById(R.id.btnVisitWebsite);
        Button btnBack = findViewById(R.id.btnBack);


        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");
        String artist = intent.getStringExtra("ARTIST");
        String year = intent.getStringExtra("YEAR");


        String detailsText = "اسم العمل: " + title + "\n"
                + "الفنان / المعماري: " + artist + "\n"
                + "سنة الإنشاء: " + year;
        tvDetails.setText(detailsText);


        btnVisitWebsite.setOnClickListener(v -> {
            String url = "https://google.com";
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(webIntent);
        });


        btnBack.setOnClickListener(v -> finish());
    }
}
