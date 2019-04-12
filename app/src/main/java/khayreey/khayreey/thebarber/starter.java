package khayreey.khayreey.thebarber;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class starter extends AppCompatActivity
{
    FloatingActionButton fab;
    RecyclerView list;
    RecyclerView.LayoutManager layoutManager;
    BooksAdapter booksAdapter;
    List<bookList> bookLists;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        fab=findViewById(R.id.fab);
        list=findViewById(R.id.list);
        progressBar=findViewById(R.id.progress_bar);
        layoutManager=new LinearLayoutManager(getApplicationContext());
        list.setLayoutManager(layoutManager);
        list.setHasFixedSize(true);
        bookLists=new ArrayList<>();
        booksAdapter=new BooksAdapter(getApplicationContext(),bookLists);
        list.setAdapter(booksAdapter);
       firebaseDatabase=FirebaseDatabase.getInstance();
       databaseReference=firebaseDatabase.getReference().child("allBooks");
       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot)
           {
               bookLists.clear();
               for (DataSnapshot snapshot:dataSnapshot.getChildren())
               {
                 bookList bookList=snapshot.getValue(bookList.class);
                 bookLists.add(bookList);
               }
               booksAdapter.notifyDataSetChanged();
               progressBar.setVisibility(View.GONE);

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError)
           {
               progressBar.setVisibility(View.GONE);

           }
       });

fab.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent= new Intent(getApplicationContext(),booking.class);
        startActivity(intent);
    }
});
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
