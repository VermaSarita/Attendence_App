package com.example.appointmentsystem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import com.example.appointmentsystem.Model.Department.Message;
import com.example.appointmentsystem.Model.Department.ResDepartment;
import com.example.appointmentsystem.Model.Doctor.ReqDoctor;
import com.example.appointmentsystem.Model.Doctor.ResDoctor;
import com.example.appointmentsystem.Model.DoctorSlot.ReqSlot;
import com.example.appointmentsystem.Model.DoctorSlot.ResSlot;
import com.example.appointmentsystem.Model.DoctorSlot.Slot;
import com.example.appointmentsystem.Model.SlotModel;
import com.example.appointmentsystem.Network.Api;
import com.example.appointmentsystem.Network.RetrofitInstance;
import com.example.appointmentsystem.Utils.Constense;
import com.example.appointmentsystem.Utils.MStash;
import com.example.appointmentsystem.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayAdapter<Message> spinnerAdapter;
    ArrayAdapter<String> doctorSpinnerAdapter;
    Api apiinterface;
    MStash mStash;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initview();
        onclicklistener();

        progressBar = binding.progressBar; // Initialize progressBar from XML
        progressBar.setVisibility(View.VISIBLE);

        // Set up the department spinner

        spinnerAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, new ArrayList<>());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(spinnerAdapter);

        // Set up the doctor spinner

        doctorSpinnerAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, new ArrayList<>());
        doctorSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner2.setAdapter(doctorSpinnerAdapter);

        apiinterface = RetrofitInstance.getRetrofitInstance().create(Api.class);
        mStash = MStash.getInstance(getApplicationContext());
        fetchDepartments();
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Message selectedDepartment = spinnerAdapter.getItem(position);
                int departmentID = selectedDepartment.getId();
                GetDoctor(departmentID);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void onclicklistener() {
        binding.submitBtn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( MainActivity.this,appointment_Activity.class ));
            }
        });

        binding.datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                binding.textView.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

    }

    private void initview() {

        ////*************** Horizontal Categoery RecyclerView******************

        List<SlotModel> slotModelList = new ArrayList<>();

        slotModelList.add(new SlotModel("10:00 AM"));
        slotModelList.add(new SlotModel("10:15 AM"));
        slotModelList.add(new SlotModel("10:30 AM"));
        slotModelList.add(new SlotModel("10:45 AM"));
        slotModelList.add(new SlotModel("11:00 AM"));
        slotModelList.add(new SlotModel("11:15 AM"));
        slotModelList.add(new SlotModel("11:30 AM"));
        slotModelList.add(new SlotModel("11:45 AM"));
        slotModelList.add(new SlotModel("12:00 AM"));

//       binding.recyclerview.setHasFixedSize(true);
//        SlotAdapter slotAdapter = new SlotAdapter(slotModelList, getApplicationContext());
//        int spanCount = 3;
//        binding.recyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(), spanCount));
//        binding.recyclerview.setAdapter(slotAdapter);

    }

    private void fetchDepartments() {
        progressBar.setVisibility(View.VISIBLE);
        Call<ResDepartment> call = apiinterface.getDepartment();
        call.enqueue(new Callback<ResDepartment>() {
            @Override
            public void onResponse(Call<ResDepartment> call, Response<ResDepartment> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    List<Message> departmentList = response.body().getMessage();
                    spinnerAdapter.clear();
                    spinnerAdapter.addAll(departmentList);
                    spinnerAdapter.notifyDataSetChanged();
                    int departmentID = departmentList.get(0).getId();
                    mStash.setValue(Constense.ID, departmentID);
                    GetDoctor(departmentID);
                } else {

                }
            }

            @Override
            public void onFailure(Call<ResDepartment> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void GetDoctor(int departmentID) {
        progressBar.setVisibility(View.VISIBLE);
        ReqDoctor reqDoctor = new ReqDoctor(String.valueOf(departmentID));

        Call<ResDoctor> resDoctorCall = apiinterface.getDoctor(reqDoctor);
        resDoctorCall.enqueue(new Callback<ResDoctor>() {
            @Override
            public void onResponse(Call<ResDoctor> call, Response<ResDoctor> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    List<com.example.appointmentsystem.Model.Doctor.Message> messageList = response.body().getMessage();
                    List<String> doctorNames = new ArrayList<>();

                    for (com.example.appointmentsystem.Model.Doctor.Message doctor : messageList) {
                        doctorNames.add(doctor.getDoctor());
                    }
                    doctorSpinnerAdapter.clear();
                    doctorSpinnerAdapter.addAll(doctorNames);
                    doctorSpinnerAdapter.notifyDataSetChanged();
                    if (!messageList.isEmpty()) {
                        int doctorId = messageList.get(0).getId();
                        fetchAndBindSlots(String.valueOf(doctorId));
                    }
                } else {
                }
            }
            @Override
            public void onFailure(Call<ResDoctor> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    private void fetchAndBindSlots(String doctorId) {
        progressBar.setVisibility(View.VISIBLE);
        ReqSlot reqSlot = new ReqSlot(doctorId);

        Call<ResSlot> slotCall = apiinterface.getSlot(reqSlot);
        slotCall.enqueue(new Callback<ResSlot>() {
            @Override
            public void onResponse(Call<ResSlot> call, Response<ResSlot> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    ResSlot resSlot = response.body();
                    ArrayList<Slot> slots = resSlot.getSlots();

                    if (slots != null && !slots.isEmpty()) {
                        bindSlotsToRecyclerView(slots);
                    } else {
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<ResSlot> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    private void bindSlotsToRecyclerView(ArrayList<Slot> slots) {
        int spanCount = 4;
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(), spanCount));
        SlotAdapter slotAdapter = new SlotAdapter(slots, MainActivity.this);
        binding.recyclerview.setAdapter(slotAdapter);

    }

}
