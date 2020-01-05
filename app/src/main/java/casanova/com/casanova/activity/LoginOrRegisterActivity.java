package casanova.com.casanova.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import casanova.com.casanova.R;

public class LoginOrRegisterActivity extends AppCompatActivity {

    Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);

        login=findViewById(R.id.login);
        register=findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            AppUtils.makeIntent(LoginOrRegisterActivity.this,RegistrationActivity.class,false);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginOrRegisterActivity.this,LoginAsActivity.class);
                startActivity(intent);
            }
        });
    }
}
