package casanova.com.casanova.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import casanova.com.casanova.R;

public class LoginAsActivity extends AppCompatActivity {

    Button login_as;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as);
        login_as=findViewById(R.id.login_as);

        login_as.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginAsActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
