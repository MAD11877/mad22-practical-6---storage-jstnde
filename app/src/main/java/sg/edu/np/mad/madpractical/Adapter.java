package sg.edu.np.mad.madpractical;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    ArrayList<User> users;
    Context context;

    public Adapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (users.get(position).name.endsWith("7")) {
            return 0;
        }
        return 1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                viewType == 1 ? R.layout.recycler : R.layout.recycler2,
                parent,
                false
        );
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User user = users.get(position);

        holder.name.setText(user.name);
        holder.desc.setText(user.description);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Profile");
        builder.setMessage(user.name);
        builder.setCancelable(false);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
