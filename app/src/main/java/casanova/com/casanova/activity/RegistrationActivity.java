package casanova.com.casanova.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;

import casanova.com.casanova.R;

public class RegistrationActivity extends AppCompatActivity {
private Button backBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        backBTN=findViewById(R.id.backBTN);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.makeIntent(RegistrationActivity.this,LoginOrRegisterActivity.class,true);
            }
        });

        TabHost tabs = (TabHost) findViewById(R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec = tabs.newTabSpec("Phone Number");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Phone Number");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("Password");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Password");
        tabs.addTab(spec);



    }
}
