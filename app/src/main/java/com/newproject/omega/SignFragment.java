package com.newproject.omega;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.newproject.omega.RegisterActivity.onResetPasswordFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignFragment extends Fragment {

    private TextView dontHaveAnAccount,forgotPassword;
    private FrameLayout parentFrameLayout;
    // we can't assign this variable directly so we go through different technique.

    private EditText email,password;
    private ImageButton closeBtn;
    private Button mainSignInBtn;
    private FirebaseAuth signInfirebaseAuth;
    private ProgressBar signInprogressBar;

    // Email regular expression
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    public SignFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign, container, false);
        // assigning variable over because we are in fragment so we can't directly  use findViewByID
        // same as for parentFrameLayout
        dontHaveAnAccount = view.findViewById(R.id.tv_HaveAccount);
        email = view.findViewById(R.id.signInEmail);
        password = view.findViewById(R.id.signInPass);
        closeBtn = view.findViewById(R.id.signInBtnClose);
        mainSignInBtn = view.findViewById(R.id.signInMainBtn);
        signInfirebaseAuth = FirebaseAuth.getInstance();
        forgotPassword = view.findViewById(R.id.signInForgotPassword);
        // it's inside Activity not in the View Class
        parentFrameLayout = getActivity().findViewById(R.id.local_register_FL);
        signInprogressBar = view.findViewById(R.id.signInprogressBar);

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent resetCodeIntent = new Intent(getActivity(),Re)
                onResetPasswordFragment = true;
                setFragment(new ResetCodeFrag());
            }
        });
        dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Over, simple logic is that we are in SignIn- Fragment we have to go at SignUp when user clicks on txt.
                // so we are going to provide signUp fragment
                setFragment(new SignUpFragment());
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainIntentSignIn();
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputData();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               checkInputData();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // when user click on main sign In button user
        // 1 First we will check email pattern after that we will
        // After authenication
        mainSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkEmailsAndPassword();
            }
        });

    }

    private void setFragment(Fragment fragment){
    // Interesting point over here is that we are in Fragment so first we have to fetch Activity then we proceed towords transcations.
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void checkInputData(){
        // if the text field is empty
        if(!TextUtils.isEmpty(email.getText())){
            if(!TextUtils.isEmpty(password.getText())){
                //Below code is only for testing purposes.
                //signInprogressBar.setVisibility(View.VISIBLE);
                mainSignInBtn.setEnabled(true);
                mainSignInBtn.setTextColor(Color.rgb(255,255,255));

            }else {
                mainSignInBtn.setEnabled(false);
                mainSignInBtn.setTextColor(Color.argb(50,255,255,255));
            }
        }else {
            mainSignInBtn.setEnabled(false);
            mainSignInBtn.setTextColor(Color.argb(50,255,255,255));
        }
    }

    private void checkEmailsAndPassword(){

        Drawable customErroIcon = getResources().getDrawable(R.mipmap.alertwarning);
        customErroIcon.setBounds(0,0,customErroIcon.getIntrinsicWidth(),customErroIcon.getIntrinsicHeight());
        if(email.getText().toString().matches(emailPattern)){
            if(password.length()>=8){

                signInprogressBar.setVisibility(View.VISIBLE);

                mainSignInBtn.setEnabled(false);
                mainSignInBtn.setTextColor(Color.argb(50,255,255,255));


                // it's go success we will check this name of user exits or not and then signIn
                signInfirebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    mainIntentSignIn();

                                }else {
                                    signInprogressBar.setVisibility(View.INVISIBLE);

                                    mainSignInBtn.setEnabled(true);
                                    mainSignInBtn.setTextColor(Color.rgb(255,255,255));
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(),error,Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }else{
                password.setError("Incorrect Password!",customErroIcon);
                //Toast.makeText(getActivity(),"Incorrect Email or Password ",Toast.LENGTH_LONG).show();
            }
        }else {

            email.setError("Invalid Email format!",customErroIcon);
           // Toast.makeText(getActivity(),"Incorrect Email or Password ",Toast.LENGTH_LONG).show();
        }

    }

    private void mainIntentSignIn(){
        Intent mainIntent = new Intent(getActivity(),MainActivity.class);
        startActivity(mainIntent);
        getActivity().finish();
    }
}
