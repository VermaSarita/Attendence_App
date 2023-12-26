package com.example.appointmentsystem;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.example.appointmentsystem.databinding.ActivityDahboardScreenBinding;

public class Dahboard_Screen extends AppCompatActivity {
  ActivityDahboardScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityDahboardScreenBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);

        binding.attendance.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( Dahboard_Screen.this,MainActivity.class ));
            }
        });

        binding.callAmbulance.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:+91 +91 9415082119"));//change the number
                startActivity(callIntent);
            }
        });
        binding.doctors.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( Dahboard_Screen.this,Login_Screen.class ));
            }
        });
        binding.addressTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = "Alyantra Medicity Super Speciality Hospital, Plot No. TC-49-V-XIII, Vibhuti Khand, Gomtinagar, Lucknow, Uttar Pradesh, India - 226010";
                Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + address);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.android.chrome"); // Use Chrome browser
                startActivity(mapIntent);
            }
        });

        setContentView(binding.getRoot());
        binding.pharmacy.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( Dahboard_Screen.this,Priscription_Screen.class ));
            }
        });

    }
}