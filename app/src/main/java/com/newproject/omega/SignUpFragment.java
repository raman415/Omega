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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

   private TextView alreadyHaveAccountSignUP;
   private FrameLayout parentFrameLayoutSignUP;
   private EditText email,fullName,password,confirmPassword;
   private Button  signUpBtn;
   private ImageButton closingBtn;
   private FirebaseFirestore firebaseFirestore;
   private ProgressBar progressBar;
   private FirebaseAuth firebaseAuth;

   private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        alreadyHaveAccountSignUP = view.findViewById(R.id.tv_HaveAccount);
        parentFrameLayoutSignUP  = getActivity().findViewById(R.id.local_register_FL);

        email = view.findViewById(R.id.signInEmail);
        fullName = view.findViewById(R.id.signUpName);
        password = view.findViewById(R.id.signInPass);
        confirmPassword = view.findViewById(R.id.signUpConPass);

        closingBtn = view.findViewById(R.id.signInBtnClose);
        signUpBtn = view.findViewById(R.id.signInMainBtn);

        progressBar = view.findViewById(R.id.signUpprogressBar);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = firebaseFirestore.getInstance();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alreadyHaveAccountSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignFragment());
            }
        });
        closingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainIntentfun();
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
        fullName.addTextChangedListener(new TextWatcher() {
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
        confirmPassword.addTextChangedListener(new TextWatcher() {
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

        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                checkEmailAndPassCode();

            }
        });

    }
    private void setFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFrameLayoutSignUP.getId(),fragment);
        fragmentTransaction.commit();

    }

    private void checkInputData(){
        if (!TextUtils.isEmpty(email.getText())){
            if(!TextUtils.isEmpty(fullName.getText())){
                if(!TextUtils.isEmpty(password.getText()) && password.length()>=8){
                    if(!TextUtils.isEmpty(confirmPassword.getText())){
                        progressBar.setVisibility(View.VISIBLE);
                        signUpBtn.setEnabled(true);
                        signUpBtn.setTextColor(getResources().getColor(R.color.common_google_signin_btn_text_light_pressed));
                    }else{
                        signUpBtn.setEnabled(false);
                        signUpBtn.setTextColor(Color.argb(50,255,255,255));
                    }
                }else{
                    signUpBtn.setEnabled(false);
                    signUpBtn.setTextColor(Color.argb(50,255,255,255));
                }
            }else{
                signUpBtn.setEnabled(false);
                signUpBtn.setTextColor(Color.argb(50,255,255,255));
            }
        }else{
            signUpBtn.setEnabled(false);
            signUpBtn.setTextColor(Color.argb(50,255,255,255));
        }
    }

    private void checkEmailAndPassCode(){

        Drawable warningErrorIcon = getResources().getDrawable(R.mipmap.alertwarning);
        warningErrorIcon.setBounds(0,0,warningErrorIcon.getIntrinsicWidth(),warningErrorIcon.getIntrinsicHeight());
            if(email.getText().toString().matches(emailPattern)){
                if(password.getText().toString().equals(confirmPassword.getText().toString())){

                    progressBar.setVisibility(View.VISIBLE);

                    signUpBtn.setEnabled(false);
                    signUpBtn.setTextColor(Color.argb(50,255,255,255));
                    firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),
                            password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                // Collecting data using Hash Technique adding up in userDATa var
                                Map<Object,String> userData = new HashMap<>();
                                userData.put("fullname",fullName.getText().toString());
                                // Overhere, data is uploading on database using fireStore
                                // Moreover, users folder will be created on successful operation main Activity will be
                                // opened.
                                firebaseFirestore.collection("USERS").add(userData)
                                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                                if (task.isSuccessful()){
                                                    mainIntentfun();

                                                }else {
                                                    progressBar.setVisibility(View.INVISIBLE);
                                                    signUpBtn.setEnabled(true);
                                                    signUpBtn.setTextColor(getResources().getColor(R.color.common_google_signin_btn_text_light_pressed));
                                                    String error = task.getException().getMessage();
                                                    Toast.makeText(getActivity(),error,Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });

                            }else {
                                progressBar.setVisibility(View.INVISIBLE);
                                signUpBtn.setEnabled(true);
                                signUpBtn.setTextColor(getResources().getColor(R.color.common_google_signin_btn_text_light_pressed));
                                String error = task.getException().getMessage();
                                Toast.makeText(getActivity(),error,Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else {
                 confirmPassword.setError("Password is Incorrect:",warningErrorIcon);
                }
            }else{

                email.setError("InvalidFormat!",warningErrorIcon);

            }

    }


    private void mainIntentfun(){
        Intent mainIntent = new Intent(getActivity(),MainActivity.class);
        startActivity(mainIntent);
        getActivity().finish();
    }
}
