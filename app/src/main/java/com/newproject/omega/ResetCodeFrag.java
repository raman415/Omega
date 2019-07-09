package com.newproject.omega;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResetCodeFrag extends Fragment {

    private EditText enterEmailId;
    private Button resetCodeButton;
    private TextView goBack;
    private FrameLayout parentFrameLayoutResetCode;
    private FirebaseAuth rcfirebaseAuth;
    // over here we will give data type to linear layout as ViewGroup because it will help
    // in transcation and animaiton
    private ViewGroup emailIconContainer;
    private ImageView rc_emailIcon;
    private TextView  rc_emailIconText;
    private ProgressBar rc_progressBar;

    public ResetCodeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_reset_code, container, false);
        enterEmailId = view.findViewById(R.id.rcEdtEmail);
        resetCodeButton= view.findViewById(R.id.resetCodeBtn);
        goBack=view.findViewById(R.id.rcBackLink);


        //parentFrameLayoutResetCode = getActivity().findViewById(R.id.rcFrameLayout);
        // sometime above code works and some time not it's having issue with SDK we are using
        // for e.g in latest API we can use this feature using getActivity() by previous it's done by view.

        parentFrameLayoutResetCode = view.findViewById(R.id.rcFrameLayout);
        rcfirebaseAuth=FirebaseAuth.getInstance();

        emailIconContainer = view.findViewById(R.id.resetCode_linearLayout);
        rc_emailIcon = view.findViewById(R.id.forgot_email_icon);
        rc_emailIconText = view.findViewById(R.id.resetTextView);
        rc_progressBar = view.findViewById(R.id.resetCodeprogressBar);


        return view;

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        enterEmailId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               checkEmailInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        resetCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(emailIconContainer);
                rc_emailIconText.setVisibility(view.GONE);

                // Any component will place or animation effects themselves with help of transitions
                // over here in EmailIconContainer is LL all full animation can work on it.
                TransitionManager.beginDelayedTransition(emailIconContainer);
                rc_emailIcon.setVisibility(View.VISIBLE);
                rc_progressBar.setVisibility(View.VISIBLE);

                resetCodeButton.setEnabled(false);
                resetCodeButton.setTextColor(Color.argb(50,255,255,255));

                // working on progress bar and email icon over here.


                resetCodeButton.setEnabled(false);
                resetCodeButton.setTextColor(Color.argb(50,255,255,255));

                rcfirebaseAuth.sendPasswordResetEmail(enterEmailId.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                  // Testing phase
                                // Toast.makeText(getActivity(),"Entered Email Id: "+enterEmailId.getText().toString().trim(),Toast.LENGTH_LONG).show();

                                if(task.isSuccessful()){
                                    /*ScaleAnimation scaleAnimation = new ScaleAnimation(1,0,1,0,
                                            rc_emailIcon.getWidth()*2,rc_emailIcon.getHeight()*2);
                                    scaleAnimation.setDuration(100);
                                    scaleAnimation.setInterpolator(new AccelerateInterpolator());
                                    scaleAnimation.setRepeatMode(Animation.REVERSE);
                                    scaleAnimation.setRepeatCount(1);


                                    scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {



                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });*/
                                    //TransitionManager.beginDelayedTransition(emailIconContainer);
                                   // rc_emailIconText.setVisibility(View.VISIBLE);
                                    rc_emailIconText.setText("Recovery email sent successfully ! check your inbox");
                                    rc_emailIconText.setTextColor(getResources().getColor(R.color.succesGreen));
                                    rc_emailIcon.setImageResource(R.mipmap.green_email);
                                    rc_emailIcon.setVisibility(View.VISIBLE);

                                    TransitionManager.beginDelayedTransition(emailIconContainer);
                                    rc_emailIconText.setVisibility(View.VISIBLE);

                                   // rc_emailIcon.startAnimation(scaleAnimation);
                                   // Toast.makeText(getActivity(),"Password is sended to your Email Acount ",Toast.LENGTH_LONG).show();
                                }else {
                                    String rc_error = task.getException().getMessage();
                                   // Toast.makeText(getActivity(),"Hello"+rc_error,Toast.LENGTH_LONG).show();
                                    resetCodeButton.setEnabled(true);
                                    resetCodeButton.setTextColor(Color.rgb(255,255,255));

                                    rc_emailIconText.setText(rc_error);
                                    rc_emailIconText.setTextColor(getResources().getColor(R.color.warningRed));

                                    TransitionManager.beginDelayedTransition(emailIconContainer);
                                    rc_emailIconText.setVisibility(View.VISIBLE);


                                }
                                rc_progressBar.setVisibility(View.GONE);

                                /*However, either sendPassword works or not we have to enabled clickablitly of button */

                            }
                        });
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragmentRCFrag(new SignFragment());
            }
        });

    }
    private void checkEmailInputs(){

        if(TextUtils.isEmpty(enterEmailId.getText())){
            resetCodeButton.setEnabled(false);
            resetCodeButton.setTextColor(Color.argb(50,255,255,255));
            // this kind of argb means partial white.
        }else {
            resetCodeButton.setEnabled(true);
            resetCodeButton.setTextColor(Color.rgb(255,255,255));
        }

    }

    private void setFragmentRCFrag(Fragment fragment){

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFrameLayoutResetCode.getId(),fragment);
        fragmentTransaction.commit();

    }
}
