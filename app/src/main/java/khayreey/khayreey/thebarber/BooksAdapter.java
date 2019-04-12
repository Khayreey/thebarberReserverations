package khayreey.khayreey.thebarber;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import khayreey.khayreey.thebarber.BooksAdapter.BooksViewHolder;

public class BooksAdapter extends RecyclerView.Adapter<BooksViewHolder>
{
    Context context;
    List<bookList> booklist;

    public BooksAdapter(Context context, List<bookList> booklist) {
        this.context = context;
        this.booklist = booklist;
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position)
    {
        bookList bookList1=booklist.get(position);
        holder.name.setText(bookList1.getName());
        holder.price.setText(bookList1.getPrice());
        holder.time.setText(bookList1.getTime());
        holder.date.setText(bookList1.getDate());

        if (bookList1.getShaveHair().length()==0)
        {
            holder.hair.setVisibility(View.GONE);
        }else {holder.hair.setText(bookList1.getShaveHair());}
        if (bookList1.getShaveBear().length()==0)
        {
            holder.bear.setVisibility(View.GONE);
        }else {holder.bear.setText(bookList1.getShaveBear());}
        if (bookList1.getHairPigment().length()==0)
        {
            holder.pigment.setVisibility(View.GONE);
        }else {holder.pigment.setText(bookList1.getHairPigment());}
        if (bookList1.getFaceMusk().length()==0)
        {
            holder.musk.setVisibility(View.GONE);
        }else {holder.musk.setText(bookList1.getFaceMusk());}
    }

    @Override
    public int getItemCount()
    {
     return booklist.size();
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder
{
    TextView price,name,hair,bear,pigment,musk,time,date;

    public BooksViewHolder(View itemView)
    {
        super(itemView);
    price=itemView.findViewById(R.id.price);
    name=itemView.findViewById(R.id.customer_name);
    hair=itemView.findViewById(R.id.hair_textView);
    bear=itemView.findViewById(R.id.beard_textView);
    pigment=itemView.findViewById(R.id.pigment_textView);
    musk=itemView.findViewById(R.id.pigment_textView);
    time=itemView.findViewById(R.id.time_textView);
    date=itemView.findViewById(R.id.date_textView);
    }
}
}
