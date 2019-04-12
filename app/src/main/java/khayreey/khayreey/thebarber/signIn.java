package khayreey.khayreey.thebarber;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signIn extends AppCompatActivity {

    EditText email_field_signIn,password_field_signIn;
    String emailN,passwordN;
    Button signIn_btn;
    FirebaseUser user;
    FirebaseAuth auth;
    ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email_field_signIn=findViewById(R.id.email_field_signIn);
        password_field_signIn=findViewById(R.id.password_field_signIn);
        signIn_btn=findViewById(R.id.signIn2_btn);
        bar=findViewById(R.id.bar);
        auth=FirebaseAuth.getInstance();
        signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailN=email_field_signIn.getText().toString();
                passwordN=password_field_signIn.getText().toString();
                if (emailN.length()==0 || passwordN.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"enter valid data",Toast.LENGTH_SHORT).show();
                }else
                {
                    bar.setVisibility(View.VISIBLE);
                    signIn(emailN,passwordN);
                }


            }
        });
    }
    public void signIn (String email,String password)
    {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                   user=auth.getCurrentUser();
                   if (user!=null)
                   {
                      if (user.isEmailVerified())
                      {
                          bar.setVisibility(View.INVISIBLE);
                          Intent intent=new Intent(getApplicationContext(),starter.class);
                          startActivity(intent);
                      }else{
                          bar.setVisibility(View.INVISIBLE);
                          Toast.makeText(getApplicationContext(),"please verify your email",Toast.LENGTH_SHORT).show();
                      }
                   }
                }else {
                    bar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"please enter valid data",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
