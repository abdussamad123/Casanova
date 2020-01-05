package casanova.com.casanova.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import casanova.com.casanova.R;
import casanova.com.casanova.SharedPreference.AppSharedPreference;
import casanova.com.casanova.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener{

    TextView username,mobile_no;
    NavigationView navigationView;
    ImageView share;
    AppSharedPreference appSharedPreference;

/*
    @Override
    protected void attachBaseContext(Context newBase) {
        String lang = Locale.getDefault().getDisplayLanguage();
        //------------set launage select by user----------------
        if (lang.equalsIgnoreCase("English")) {
            MyApplication.getInstance().getPreferenceSettings().setLauguageEng(false);
        } else if (lang.equalsIgnoreCase("हिन्दी")) {
            MyApplication.getInstance().getPreferenceSettings().setLauguageEng(true);
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            if (MyApplication.getInstance().getPreferenceSettings().getLanguageEng()) {
                super.attachBaseContext(MyContextWrapper.wrap(newBase, AppConstant.LANGUAGE_HINDI));
            } else {
                super.attachBaseContext(MyContextWrapper.wrap(newBase, AppConstant.LANGUAGE_ENGLISH));
            }
        } else {
            if (MyApplication.getInstance().getPreferenceSettings().getLanguageEng()) {
                MyApplication.language(AppConstant.LANGUAGE_HINDI);
            } else {
                MyApplication.language(AppConstant.LANGUAGE_ENGLISH);
            }
            super.attachBaseContext(newBase);
        }
    }
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      //  share=findViewById(R.id.share);
       // share.setVisibility(View.VISIBLE);






        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ImageView imageView=findViewById(R.id.navigation);
        ImageView profile=findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        //toolbar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        toggle.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);

            }
        });

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Fragment mFragment = null;
        mFragment = new HomeFragment();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, mFragment).commit();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header=navigationView.getHeaderView(0);

        navigationView.setNavigationItemSelectedListener(this);
        mFragment = null;
       // mFragment = new HomeFragment1();
       // android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.frameLayout, mFragment).commit();

       // username=header.findViewById(R.id.user_name);
       // mobile_no=header.findViewById(R.id.mobile_no);
        /*if (sharedpreferences.getString("name","").equals("")){

        }else {
            String data=sharedpreferences.getString("name","");
            username.setText(data);
            mobile_no.setText(sharedpreferences.getString("phone",""));
        }

        if (sharedpreferences.getString("name","").equals("")){

            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_main_drawer);


        }else {

            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_main_drawer_logout);

        }*/



    }








    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        if (id == R.id.offices) {
            //Intent intent=new Intent(MainActivity.this,ReedemMyPointsActivity.class);
            //startActivity(intent);



        }

        else if (id == R.id.offices) {
           // Intent intent=new Intent(MainActivity.this,OfficeActivity.class);
            //intent.putExtra("location","location_offices");
            //startActivity(intent);


        }

        else if (id == R.id.offices) {

            //Intent intent=new Intent(MainActivity.this,MyPointsActivity.class);
            //startActivity(intent);


        } else if (id == R.id.offices) {

            //Intent intent=new Intent(MainActivity.this,MyGiftsActivity.class);
            //startActivity(intent);



        }

        else if (id == R.id.offices) {

            //Intent intent=new Intent(MainActivity.this,MyPromotionActivity.class);
            //startActivity(intent);



        }

        else if (id==R.id.logout){

            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                    .setMessage("Are you sure you want to Logout?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences settings = MainActivity.this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                            settings.edit().clear().commit();
                            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                            intent.putExtra("logout","logout");
                            startActivity(intent);
                        }
                    }).setNegativeButton("No", null).show();

        }
        /*else if (id == R.id.share) {

            final String appPackageName = getPackageName();
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String sAux = "\nSearch PG/Flat/Room on your fingertips without brokerage\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id="+ appPackageName;
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }




        }
        else if (id == R.id.about_us) {

            Intent intent=new Intent(MainActivity.this,AboutUsActivity.class);
            startActivity(intent);

        }

        else if (id == R.id.chat_with_us) {

            Uri uri = Uri.parse("smsto:" + "7777050050");
            Intent i = new Intent(Intent.ACTION_SENDTO, uri);
            i.putExtra("sms_body", "hii");
            i.setPackage("com.whatsapp");
            startActivity(i);

        }*/
/*
        else if (id == R.id.call_us) {

            Dexter.withActivity(MainActivity.this)
                    .withPermissions(
                            Manifest.permission.CALL_PHONE)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            // check if all permissions are granted
                            if (report.areAllPermissionsGranted()) {
                                String number = "7777050050";
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:" + number));
                                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    // TODO: Consider calling
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return;
                                }
                                startActivity(intent);
                            }

                            // check for permanent denial of any permission
                            if (report.isAnyPermissionPermanentlyDenied()) {
                                // show alert dialog navigating to Settings
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).
                    withErrorListener(new PermissionRequestErrorListener() {
                        @Override
                        public void onError(DexterError error) {
                            Toast.makeText(MainActivity.this, "Error occurred! " + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .onSameThread()
                    .check();

        }*/




      /*  else if (id == R.id.sign_in) {
            if (sharedpreferences.getString("name","").equals("")){

                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);


            }else {

                new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                        .setMessage("Are you sure you want to Logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences settings = MainActivity.this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                settings.edit().clear().commit();
                                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("No", null).show();

            }


        }

        else if (id == R.id.sign_up) {
            Intent intent=new Intent(MainActivity.this,RegistrationPageActivity.class);
            startActivity(intent);}*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
       /* new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("No", null).show();*/
    }


}
