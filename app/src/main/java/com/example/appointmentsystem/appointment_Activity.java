package com.example.appointmentsystem;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.appointmentsystem.Model.Appointment.ReqAppointment;
import com.example.appointmentsystem.Model.Appointment.ResAppointment;
import com.example.appointmentsystem.Model.Department.Message;
import com.example.appointmentsystem.Network.Api;
import com.example.appointmentsystem.Network.RetrofitInstance;
import com.example.appointmentsystem.databinding.ActivityAppointmentBinding;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class appointment_Activity extends AppCompatActivity {
    ActivityAppointmentBinding binding;
    TextView okay_text, cancel_text;
    Api apiinterface;
    private List<Message> messageList;
    private ArrayList<Message> message;
    private ArrayAdapter<Message> spinnerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityAppointmentBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());


        Dialog dialog = new Dialog(appointment_Activity.this);

        binding.bookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.setContentView(R.layout.success_dialog);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                okay_text = dialog.findViewById(R.id.okay_text);
                cancel_text = dialog.findViewById(R.id.cancel_text);

                cancel_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        //Toast.makeText(appointment_Activity.this, "cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                okay_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        startActivity(new Intent( appointment_Activity.this,Dahboard_Screen.class ));
                       // Toast.makeText(appointment_Activity.this, "okay clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();

            }
        });

        binding.backArrow.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( appointment_Activity.this,Dahboard_Screen.class ));
            }
        });


        binding.bookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = binding.fullName.getText().toString().trim();
                String mobileNumber = binding.mobileNumber.getText().toString().trim();
                String email = binding.email.getText().toString().trim();
                String dob = binding.DOB.getText().toString().trim();
                String address = binding.address.getText().toString().trim();
                String gender = "";

                int selectedRadioButtonId = binding.groupradio.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    RadioButton groupradio = findViewById(selectedRadioButtonId);
                    gender = groupradio.getText().toString().trim();
                } else {

                    Toast.makeText(appointment_Activity.this, "Please select a gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (fullName.equals("")) {
                    binding.fullName.setError("Enter your name");
                    return;
                }

                if (mobileNumber.equals("")) {
                    binding.mobileNumber.setError("Please enter a mobile number");
                    return;
                }

                if (email.equals("")) {
                    binding.email.setError("Please enter an email address");
                    return;
                }

                if (dob.equals("")) {
                    binding.DOB.setError("Please enter a date of birth");
                    return;
                }

                if (isFutureDate(dob)) {
                    binding.DOB.setError("Future date of birth is not allowed");
                    return;
                }

                if (address.equals("")) {
                    binding.address.setError("Please enter an address");
                    return;
                }

                validatePhoneNumber(mobileNumber);
                validateEmail(email);
                validateName(fullName);
                isFutureDate(dob);

                if (binding.mobileNumber.getError() == null && binding.email.getError() == null && binding.fullName.getError() == null ) {
                    // Show success dialog
                    Dialog dialog = new Dialog(appointment_Activity.this);
                    dialog.setContentView(R.layout.success_dialog);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.setCancelable(false);
                    dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                    TextView okayText = dialog.findViewById(R.id.okay_text);
                    TextView cancelText = dialog.findViewById(R.id.cancel_text);

                    cancelText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    okayText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            startActivity(new Intent(appointment_Activity.this, MainActivity.class));
                        }
                    });
                    BookAppointment();
                   // dialog.show();

                    // Clear form fields
                    binding.fullName.setText("");
                    binding.mobileNumber.setText("");
                    binding.email.setText("");
                    binding.DOB.setText("");
                    binding.address.setText("");
                    binding.groupradio.clearCheck();
                }
            }
        });



        binding.DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        appointment_Activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                binding.DOB.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        },
                        year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
                datePickerDialog.show();
            }
        });
        //**********name validation ***************
        binding.fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for this example
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Call the validateName function with the entered text
                validateName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed for this example
            }
        });
        //**************email validation ***********
        binding.email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for this example
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Call the validateEmail function with the entered text
                validateEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed for this example
            }
        });
        //***********phone number**********//
        binding.mobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                validatePhoneNumber(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private boolean isFutureDate(String dob) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date dobDate = sdf.parse(dob);
            Calendar currentDate = Calendar.getInstance();
            currentDate.set(Calendar.HOUR_OF_DAY, 0);
            currentDate.set(Calendar.MINUTE, 0);
            currentDate.set(Calendar.SECOND, 0);
            currentDate.set(Calendar.MILLISECOND, 0);

            return dobDate.after(currentDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    //**********name validation **************

    private void validateName(String name) {
        if (name.matches(".*\\d.*")) {
            binding.fullName.setError("Name should not contain digits");
        } else {
            binding.fullName.setError(null);
        }
    }

    private boolean validateEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            // binding.emailAddress.setError("Email address is required");
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.contains("@")) {
            binding.email.setError("Invalid email address");
        } else {
            binding.email.setError(null); // Clear any previous errors
        }
        return false;
    }


    private boolean validatePhoneNumber(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            // binding.phoneNo.setError("Phone number is required");
        } else if (phoneNumber.length() != 10) {
            binding.mobileNumber.setError("Phone number must have 10 digits");
        } else if (phoneNumber.charAt(0) < '6' || phoneNumber.equals("12345")) {
            binding.mobileNumber.setError("Invalid mobile number. Phone number must start with 6 or a digit greater than 6.");
        } else {
            binding.mobileNumber.setError(null); // Clear any previous errors
        }
        return false;
    }

//    private void BookAppointment() {
//        apiinterface = RetrofitInstance.getRetrofitInstance( ).create(Api.class);
//        Call<ResAppointment> resAppointmentCall = apiinterface.getappointment(new ReqAppointment("" + binding.fullName.getText( ).toString( ), "" + binding.mobileNumber.getText( ).toString( ), "" + binding.email.getText( ).toString( ), "" + binding.DOB.getText().toString( ),""+binding.address.getText().toString(),""+binding.groupradio.toString(),""+binding.fullName.getText().toString()));
//
//        resAppointmentCall.enqueue(new Callback<ResAppointment>( ) {
//            @Override
//            public void onResponse(Call<ResAppointment> call, Response<ResAppointment> response) {
//                ResAppointment resRegistration = response.body( );
//                if (resRegistration != null) {
//                    if (resRegistration.getResponse( ).equals("11")) {
//                        Toast.makeText(appointment_Activity.this, "Appointment Booked ", Toast.LENGTH_SHORT).show( );
//                    }  else if (resRegistration.getResponse( ).equals("10")) {
//                        Toast.makeText(appointment_Activity.this, " Appointment failed", Toast.LENGTH_SHORT).show( );
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResAppointment> call, Throwable t) {
//                Toast.makeText(appointment_Activity.this, "Something went wrong", Toast.LENGTH_SHORT).show( );
//
//            }
//        });
//
//    }


private void BookAppointment() {
    apiinterface = RetrofitInstance.getRetrofitInstance().create(Api.class);

    int selectedRadioButtonId = binding.groupradio.getCheckedRadioButtonId();
    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
    String gender = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "";

    Call<ResAppointment> resAppointmentCall = apiinterface.getappointment(new ReqAppointment(
            "" + binding.fullName.getText().toString(),
            "" + binding.mobileNumber.getText().toString(),
            "" + binding.email.getText().toString(),
            "" + binding.DOB.getText().toString(),
            "" + binding.address.getText().toString(),
            gender, // Use the selected radio button value
            ""
    ));

    resAppointmentCall.enqueue(new Callback<ResAppointment>() {
        @Override
        public void onResponse(Call<ResAppointment> call, Response<ResAppointment> response) {
            ResAppointment resRegistration = response.body();
            if (resRegistration != null) {
                if ("11".equals(resRegistration.getResponse())) {
                    Toast.makeText(appointment_Activity.this, "Appointment Booked", Toast.LENGTH_SHORT).show();
                } else if ("10".equals(resRegistration.getResponse())) {
                    Toast.makeText(appointment_Activity.this, "Appointment failed", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<ResAppointment> call, Throwable t) {
            Toast.makeText(appointment_Activity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    });
}



}