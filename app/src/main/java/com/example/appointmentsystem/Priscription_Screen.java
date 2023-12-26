package com.example.appointmentsystem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.appointmentsystem.Model.Pharmacy.ReqPharmacy;
import com.example.appointmentsystem.Model.Pharmacy.ResPharmacy;
import com.example.appointmentsystem.Network.Api;
import com.example.appointmentsystem.Network.RetrofitInstance;
import com.example.appointmentsystem.databinding.ActivityPriscriptionScreenBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Priscription_Screen extends AppCompatActivity {
  ActivityPriscriptionScreenBinding binding;
    private static final int SELECT_IMAGE = 1;
    private static final int REQUEST_CODE = 100;
    Uri imageToUploadUri;
    private Object Uri;
    Button BSelectImage;
    ImageView IVPreviewImage;
    int SELECT_PICTURE = 200;
    private static final int PICK_IMAGE = 1;
    private static final int REQUEST_CAMERA = 2;
    private static final String[] PERMISSIONS_CAMERA = {Manifest.permission.CAMERA};
    Api apiinterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityPriscriptionScreenBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = binding.patientName.getText().toString().trim();
                String attendent = binding.attendentName.getText().toString().trim();
                String mobileNumber = binding.phoneNo.getText().toString().trim();
                String email = binding.emailAddress.getText().toString().trim();
                String address = binding.address.getText().toString().trim();

                if (fullName.equals("")) {
                    binding.patientName.setError("Enter your name");
                    return;
                }

                if (mobileNumber.equals("") && email.equals("")) {
                    binding.phoneNo.setError("Please enter a mobile number or an email address");
                    binding.emailAddress.setError("Please enter a mobile number or an email address");
                    return;
                }

                if (address.equals("")) {
                    binding.address.setError("Please enter an address");
                    return;
                }
                validatePhoneNumber(mobileNumber);
                validateEmail(email);
                validateName(fullName);

                if (binding.phoneNo.getError() == null && binding.emailAddress.getError() == null && binding.patientName.getError() == null) {
                    // Show success dialog
                    Dialog dialog = new Dialog(Priscription_Screen.this);
                    dialog.setContentView(R.layout.success_dialog);
                    dialog.getWindow( ).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.setCancelable(false);
                    dialog.getWindow( ).getAttributes( ).windowAnimations = R.style.animation;

                    TextView okayText = dialog.findViewById(R.id.okay_text);
                    TextView cancelText = dialog.findViewById(R.id.cancel_text);

                    cancelText.setOnClickListener(new View.OnClickListener( ) {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss( );
                        }
                    });

                    okayText.setOnClickListener(new View.OnClickListener( ) {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss( );
                            startActivity(new Intent(Priscription_Screen.this, MainActivity.class));
                        }
                    });
                    Pharmacy();
                }
                // Clear form fields
                binding.patientName.setText("");
                binding.phoneNo.setText("");
                binding.attendentName.setText("");
                binding.emailAddress.setText("");
                binding.address.setText("");
                binding.image.setImageURI(null);
            }
        });

        binding.selectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageSourceDialog();
            }
        });

        //*********Name validation ********//
        binding.patientName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //*********Attendent  validation ********//
        binding.attendentName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validatename(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //**************email validation ***********
        binding.emailAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for this example
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                validateEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //***********phone number**********//

        binding.phoneNo.addTextChangedListener(new TextWatcher() {
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

    private void showImageSourceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select image source");
        builder.setItems(new CharSequence[]{"Gallery", "Camera"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        pickImageFromGallery();
                        break;
                    case 1:
                        checkCameraPermissions();
                        break;
                    default:
                        break;
                }
            }
        });
        builder.show();
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }

    private void checkCameraPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS_CAMERA, REQUEST_CAMERA);
        }
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera( );
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PICK_IMAGE:
                    Uri selectedImage = data.getData();
                    binding.image.setImageURI(selectedImage);
                    break;
                case REQUEST_CAMERA:
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    binding.image.setImageBitmap(photo);
                    break;
                default:
                    break;
            }
        }
    }

    private void validateName(String name) {
        if (name.matches(".*\\d.*")) {
            binding.patientName.setError("Name should not contain digits");
        } else {
            binding.patientName.setError(null);
        }
    }

    private void validatename(String name) {
        if (name.matches(".*\\d.*")) {
            binding.attendentName.setError("Name should not contain digits");
        } else {
            binding.attendentName.setError(null);
        }
    }
    private void validateEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            binding.emailAddress.setError("Email address is required");
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.contains("@")) {
            binding.emailAddress.setError("Invalid email address");
        } else {
            binding.emailAddress.setError(null);
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            binding.phoneNo.setError("Phone number is required");
        } else if (phoneNumber.length() != 10) {
            binding.phoneNo.setError("Phone number must have 10 digits");
        } else if (phoneNumber.charAt(0) < '6' || phoneNumber.equals("12345")) {
            binding.phoneNo.setError("Invalid mobile number. Phone number must start with 6 or a digit greater than 6.");
        } else {
            binding.phoneNo.setError(null);
        }
    }

    private void Pharmacy() {

        apiinterface = RetrofitInstance.getRetrofitInstance( ).create(Api.class);
        Call<ResPharmacy> resPharmacyCall = apiinterface.getPharmacy(new ReqPharmacy("" + binding.patientName.getText( ).toString( ), "" + binding.attendentName.getText( ).toString( ), "" + binding.phoneNo.getText( ).toString( ), "" + binding.emailAddress.toString( ),""+binding.address.getText().toString(),""+binding.image.toString()));

        resPharmacyCall.enqueue(new Callback<ResPharmacy>( ) {
            @Override
            public void onResponse(Call<ResPharmacy> call, Response<ResPharmacy> response) {
                ResPharmacy resPharmacy = response.body( );
                if (resPharmacy != null) {
                    if (resPharmacy.getResponse( ).equals("11")) {
                        Toast.makeText(Priscription_Screen.this, "Appointment Booked ", Toast.LENGTH_SHORT).show( );
                    } else if (resPharmacy.getResponse( ).equals("30")) {
                        Toast.makeText(Priscription_Screen.this, "Mobile number already exist", Toast.LENGTH_SHORT).show( );
                    } else if (resPharmacy.getResponse( ).equals("10")) {
                        Toast.makeText(Priscription_Screen.this, " Appointment failed", Toast.LENGTH_SHORT).show( );
                    }
                }
            }

            @Override
            public void onFailure(Call<ResPharmacy> call, Throwable t) {
                Toast.makeText(Priscription_Screen.this, "Something went wrong", Toast.LENGTH_SHORT).show( );

            }
        });

    }

}