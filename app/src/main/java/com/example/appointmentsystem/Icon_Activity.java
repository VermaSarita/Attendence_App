package com.example.appointmentsystem;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.appointmentsystem.databinding.ActivityIconBinding;

public class Icon_Activity extends AppCompatActivity {
   ActivityIconBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       binding=ActivityIconBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        binding.attendance.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( Icon_Activity.this,MainActivity.class ));
            }
        });
        setContentView(binding.getRoot());

    }
}