package com.abadzheva.dogs;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewDog;
    private ProgressBar progressBar;
    private Button buttonLoadImage;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // ----------------- onCreate --------------------
        initViews();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.loadDogImage();
        viewModel.getIsError().observe(this, isError -> {
            if (isError) {
                Toast.makeText(
                        MainActivity.this,
                        R.string.toast_loading_error_we_are_sorry,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        viewModel.getIsLoading().observe(
                this,
                loading -> progressBar.setVisibility(
                        loading ? View.VISIBLE : View.GONE
                )
        );
        viewModel.getDogImage().observe(
                this,
                dogImage -> Glide.with(MainActivity.this)
                        .load(dogImage.getMessage())
                        .into(imageViewDog)
        );

        buttonLoadImage.setOnClickListener(v -> viewModel.loadDogImage());
    }

    private void initViews() {
        imageViewDog = findViewById(R.id.imageViewDog);
        progressBar = findViewById(R.id.progressBar);
        buttonLoadImage = findViewById(R.id.buttonLoadImage);
    }
}