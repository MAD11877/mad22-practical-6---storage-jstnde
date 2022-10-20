package sg.edu.np.mad.madpractical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class login extends AppCompatActivity {
    List<String> logins = new ArrayList<>();
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reference = FirebaseDatabase.getInstance("https://mad-practical-6-87257-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

        EditText etUsername = findViewById(R.id.editTextTextPersonName);
        EditText etPassword = findViewById(R.id.editTextTextPassword);

        Button loginBtn = findViewById(R.id.button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logins.size() == 0) {
                    return;
                }
                for (String login : logins) {
                    List<String> user = Arrays.asList(login.split(","));
                    String username = user.get(0);
                    String password = user.get(1);

                    if (etUsername.getText().toString().equals(username) && etPassword.getText().toString().equals(password)) {
                        Intent intent = new Intent(login.this, ListActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        reference.child("Users").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        logins = new ArrayList<>();
                        if (snapshot.hasChildren()) {
                            for (DataSnapshot ss: snapshot.getChildren()) {
                                String user = "";
                                user += ss.child("username").getValue() + ",";
                                user += ss.child("password").getValue();
                                logins.add(user);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );
    }
}