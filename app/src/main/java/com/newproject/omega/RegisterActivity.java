package com.newproject.omega;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

public class RegisterActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    public static boolean onResetPasswordFragment = false;
    public static boolean setSignUpFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        frameLayout = findViewById(R.id.local_register_FL);
        // Over, setFragment is used to set fragment by default on Create Activity

       if (setSignUpFragment){
           setSignUpFragment = false;
           setDefaulterFragment(new SignUpFragment());
       }else {
           setDefaulterFragment(new SignFragment()); // <-- over here by default we are providing signIN fragment.

       }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // if the user is pressing back button key
        if(keyCode == event.KEYCODE_BACK){
            if(onResetPasswordFragment){
                // Over here again we shifting towards normal setting because we never want it ot stay on Activity forever
                // for that simply we false the value of variable.
                onResetPasswordFragment=false;
                setFragment(new SignFragment());
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);

    }
    private void setDefaulterFragment(Fragment fragment){

            // Interesting point over here is that we are in Fragment so first we have to fetch Activity then we proceed towords transcations.
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
            fragmentTransaction.replace(frameLayout.getId(),fragment);
            fragmentTransaction.commit();


    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // in replace method first we setting id of frameLayout and secondly we setting up fragment
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}
