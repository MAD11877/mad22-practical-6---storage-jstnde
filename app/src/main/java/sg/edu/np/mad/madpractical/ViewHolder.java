package sg.edu.np.mad.madpractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView img;
    TextView name;
    TextView desc;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.listImg);
        name = itemView.findViewById(R.id.listName);
        desc = itemView.findViewById(R.id.listDesc);
    }
}
