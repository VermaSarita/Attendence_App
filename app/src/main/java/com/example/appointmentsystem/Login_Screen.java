package com.example.appointmentsystem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.appointmentsystem.databinding.ActivityLoginScreenBinding;
import java.util.ArrayList;


public class Login_Screen extends AppCompatActivity {
 ActivityLoginScreenBinding binding;
    ArrayList<ModelClass> list;
 Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityLoginScreenBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();

        list.add(new ModelClass(R.drawable.ic_baseline_person_outline_24,"Dr.Nilanshu Singh","Consultant Head & Neack Surgery","OPD Fees - 500","0000000000",5f));
        list.add( new ModelClass(R.drawable.dr_saurabh_meet,"Saurabh Rai","Plastic Surgeon"," OPD Fee - 1000","+91-9554545479",5f));
        list.add( new ModelClass(R.drawable.dr_ritesh,"DR. Ritesh Gangwar","Cardiologist","OPD Fee - 1000","+91-9650170666",5f));
        list.add( new ModelClass(R.drawable.dr_vibhor_meet,"Dr. Vibhor Mahendru","Surgical Oncologist","Fee - 1000","+91-9794811226",5f));
        list.add( new ModelClass(R.drawable.admin_dr,"Dr. Archana Singh","General Physician","OPD Fee - 800","+91-9650170666",5f));
        list.add( new ModelClass(R.drawable.dr_apeksha_meet,"Dr. Apeksha Vishnoi","Gynaecologist","OPD Fee - 800","0000000000",5f));
        list.add( new ModelClass(R.drawable.dr_shruti_meet,"Dr.Shruti Kirti Rai","Consultant Psychiatrist","OPD Fee - 800","+91-7379998880",5f));
        list.add( new ModelClass(R.drawable.ic_baseline_person_outline_24,"Dr.Anjana Singh","Lab Medicine","OPD Fee -","0000000000",5f));
        list.add( new ModelClass(R.drawable.ic_baseline_person_outline_24,"Dr. Jagdeep Verma","Anaesthesia","OPD Fee-1000","0000000000",5f));
        list.add( new ModelClass(R.drawable.ic_baseline_person_outline_24,"Dr.Deepanshu Mallan","Critical Care","OPD Fee-1000","0000000000",5f));
//        list.add( new ModelClass(R.drawable.ic_baseline_person_outline_24,"Name","Deparment",5f));


        Adapter adapterClass = new Adapter(list,Login_Screen.this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerView.setAdapter(adapterClass);
        binding.backArrow.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( Login_Screen.this,Dahboard_Screen.class ));
            }
        });
        setContentView(binding.getRoot());
    }
}