package khayreey.khayreey.thebarber;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class booking extends AppCompatActivity
{
EditText name_field;
CheckBox shave_hair,shave_bear,hair_pigment,face_mask;
Button setTime_btn,setDate_btn,bookNow_btn;
static TextView setTime_txt,setDate_txt;
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
String name,hair="",bear="",pigment="",musk="",time_txt,date_txt;
double price=0;
String totalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        name_field=findViewById(R.id.name_field);
        shave_hair=findViewById(R.id.shave_hair);
        shave_bear=findViewById(R.id.shave_bear);
        hair_pigment=findViewById(R.id.hair_pigment);
        face_mask=findViewById(R.id.face_mask);
        setTime_btn=findViewById(R.id.setTime_btn);
        setDate_btn=findViewById(R.id.setDate_btn);
        bookNow_btn=findViewById(R.id.bookNow_btn);
        setTime_txt=findViewById(R.id.setTime_txt);
        setDate_txt=findViewById(R.id.setDate_txt);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

        setTime_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager() ,"TimePicker");
            }
        });
        setDate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager() ,"DatePicker");

            }
        });

        bookNow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
             name=name_field.getText().toString();

             if (shave_hair.isChecked())
             {
                 price=price+25;
                 hair="shave hair";
             }
                if (shave_bear.isChecked())
                {
                    price=price+15;
                    bear="shave bear";
                }
                if (hair_pigment.isChecked())
                {
                    price=price+35;
                    pigment="hair pigment";
                }
                if (face_mask.isChecked())
                {
                    price=price+20;
                    musk="face mask";
                }
                time_txt=setTime_txt.getText().toString();
                date_txt=setDate_txt.getText().toString();

                if (name.length()==0||time_txt.length()==0||date_txt.length()==0||hair.length()==0&&bear.length()==0&&pigment.length()==0&&musk.length()==0)
                {
                    Toast.makeText(booking.this, "please Enter A Valid Data", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        totalPrice=Double.toString(price);
                      writeInDataBase(name,hair,bear,pigment,musk,date_txt,time_txt,totalPrice);
                      Intent intent=new Intent(getApplicationContext(),starter.class);
                      startActivity(intent);
                    }
            }
        });
    }
     public void writeInDataBase(String name,String hair,String bear,String pigment,String musk,String time,String date,String price)
     {
      bookList bookList=new bookList(name,hair,bear,pigment,musk,time,date,price);
      String key =databaseReference.child("allBooks").push().getKey();
      databaseReference.child("allBooks").child(key).setValue(bookList);
     }


    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {
            int hour = hourOfDay;
            int minutes = minute;
            String timeSet = "";
            if (hour > 12) {
                hour -= 12;
                timeSet = "PM";
            } else if (hour == 0) {
                hour += 12;
                timeSet = "AM";
            } else if (hour == 12){
                timeSet = "PM";
            }else{
                timeSet = "AM";
            }

            String min = "";
            if (minutes < 10)
                min = "0" + minutes ;
            else
                min = String.valueOf(minutes);

            String aTime = new StringBuilder().append(hour).append(':')
                    .append(min ).append(" ").append(timeSet).toString();
            setTime_txt.setText(aTime);
        }
    }
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of TimePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month,day);

        }

        public void onDateSet(DatePicker view, int year, int month,int day)
        {
            int month2=month+1;
            setDate_txt.setText(day + "/" + month2 + "/" + year );
        }
    }
}
