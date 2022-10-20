package sg.edu.np.mad.madpractical;

import static sg.edu.np.mad.madpractical.ListActivity.users;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button followBtn = findViewById(R.id.followBtn);
        Button msgBtn = findViewById(R.id.msgBtn);
        TextView name = findViewById(R.id.nameTxt);
        Intent intent = getIntent();
        User user = users.get(intent.getIntExtra("position", 0));

        name.setText(user.name);
        followBtn.setText(user.followed ? "Unfollow" : "Follow");

        followBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.ToggleFollow();
                Toast.makeText(getApplicationContext(), user.followed ? "Followed" : "Unfollowed", Toast.LENGTH_SHORT).show();
                followBtn.setText(user.followed ? "Unfollow" : "Follow");
            }
        });

        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(intent);
            }
        });
    }
}