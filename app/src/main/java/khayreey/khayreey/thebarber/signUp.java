package khayreey.khayreey.thebarber;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signUp extends AppCompatActivity {
FirebaseAuth auth;
 EditText email_field_signUp,password_field_signUp;
Button signUp_btn;
 String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth=FirebaseAuth.getInstance();
        email_field_signUp=findViewById(R.id.email_field_signUp);
        password_field_signUp=findViewById(R.id.password_field_signUp);
        signUp_btn=findViewById(R.id.signUp2_btn);
signUp_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v)
    {
    email=email_field_signUp.getText().toString();
    password=password_field_signUp.getText().toString();
    if (email.length()==0 || password.length()==0)
    {
        Toast.makeText(getApplicationContext(), "please enter valid data", Toast.LENGTH_SHORT).show();
    }
    else{
        signUp(email,password);

        }

    }
});
    }
public void signUp (String email,String password)
{
    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
    {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task)
        {
         if(task.isSuccessful())
         {
             FirebaseUser user=auth.getCurrentUser();
             if (user!=null)
             {
                 user.sendEmailVerification();
                 Intent intent=new Intent(getApplicationContext(),emailVer.class);
                 startActivity(intent);
             }
         }
         else {
             Toast.makeText(getApplicationContext(), "enter valid email and password", Toast.LENGTH_SHORT).show();
              }
        }
    });
}

}

