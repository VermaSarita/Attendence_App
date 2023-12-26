package com.example.appointmentsystem.Network;
import com.example.appointmentsystem.Model.Appointment.ReqAppointment;
import com.example.appointmentsystem.Model.Appointment.ResAppointment;
import com.example.appointmentsystem.Model.Department.ResDepartment;
import com.example.appointmentsystem.Model.Doctor.ReqDoctor;
import com.example.appointmentsystem.Model.Doctor.ResDoctor;
import com.example.appointmentsystem.Model.DoctorSlot.ReqSlot;
import com.example.appointmentsystem.Model.DoctorSlot.ResSlot;
import com.example.appointmentsystem.Model.Pharmacy.ReqPharmacy;
import com.example.appointmentsystem.Model.Pharmacy.ResPharmacy;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @POST("api/Registration")
    Call<ResAppointment>getappointment(@Body ReqAppointment reqAppointment);

    @POST("api/Pharmacy")
    Call<ResPharmacy>getPharmacy(@Body ReqPharmacy reqPharmacy);

    @GET("api/Department")
    Call<ResDepartment>getDepartment();

    @POST("api/Doctor")
    Call<ResDoctor>getDoctor(@Body ReqDoctor reqDoctor);

    @POST("api/TimeSlot")
    Call<ResSlot>getSlot(@Body ReqSlot reqSlot);


}
