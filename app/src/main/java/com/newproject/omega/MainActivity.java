package com.newproject.omega;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import static com.newproject.omega.RegisterActivity.setSignUpFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int homeFragment = 0;
    private static final int cartFragment = 1;
    private static final int OrdersFragment = 2;
    private static final int MYWISH_Fragment = 3;
    private static final int reward_Fragment = 4;
    private static final int account_fragment = 5;
    public static boolean showCart = false;


    private FrameLayout frameLayout;
    private int currentFragment = -1;
    private NavigationView navigationView;
    private ImageView actionBarLogo;

    private Window window;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        actionBarLogo = findViewById(R.id.acitonBar_logo);
        setSupportActionBar(toolbar);

        window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        // we removed floating action button
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        // previously we have written actionToggle code over here
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Over here by default we have selected first Item.
        navigationView.getMenu().getItem(0).setChecked(true);


        frameLayout = findViewById(R.id.main_frameLayout);

        if (showCart) {

            drawer.setDrawerLockMode(1);
            // todo: actually what was happening before Actionbar is being placed over Toggle button.
            // so that why we are going to generate the our ActionToggleButton in else so that it dont over lap each other
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            goToFragment("My Cart ", new MyCartFragment(), -2);
        } else {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            setFragment(new HomeFrag(), homeFragment);

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (currentFragment == homeFragment) {
                // over here when we will click back press if the currentFrag is homeFrag
                // then we will back out from the application otherise in else statement
                // any other fragment will come to or set to home fragment.
                currentFragment = -1;
                super.onBackPressed();

            } else {

                if (showCart) {
                    showCart = false;
                    finish();// now the activity is finished.
                } else {
                    actionBarLogo.setVisibility(View.VISIBLE);// again logo we appears
                    invalidateOptionsMenu();
                    setFragment(new HomeFrag(), homeFragment);
                    navigationView.getMenu().getItem(0).setChecked(true);

                }

            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (currentFragment == homeFragment) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);

            getMenuInflater().inflate(R.menu.main, menu);


        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.main_search_icon) {
            // todo: search
            return true;
        } else if (id == R.id.main_notification_icon) {
            // todo: notification
            return true;
        } else if (id == R.id.main_cart_icon) {
            final Dialog signInDialog = new Dialog(MainActivity.this);

            signInDialog.setContentView(R.layout.signin_dialog);
            signInDialog.setCancelable(true);
            signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            Button dialogSignInBtn = signInDialog.findViewById(R.id.dialog_sign_in_btn);
            Button dialogSignUpBtn = signInDialog.findViewById(R.id.dialog_sign_up_btn);

            final Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
            dialogSignInBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSignUpFragment = false;
                    startActivity(registerIntent);
                }
            });

            dialogSignUpBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signInDialog.dismiss();

                    setSignUpFragment = true;
                    startActivity(registerIntent);
                }
            });
            // if i dont call show method then it will never going to display my dialog box
            signInDialog.show();

            // goToFragment("My Cart", new MyCartFragment(), cartFragment);
            return true;
        } else if (id == R.id.main_notification_icon) {
            // todo: cart
            return true;
        } else if (id == android.R.id.home) {
            if (showCart) {
                showCart = false;
                finish();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToFragment(String title, Fragment fragment, int fragNumber) {
        // this method will eliminates all the menus from the action bar.

        actionBarLogo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment, fragNumber);
        if (fragNumber == cartFragment) {
            navigationView.getMenu().getItem(3).setChecked(true);

        }


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        // todo: ctrl + Alt + L to format our any android java code.
        int id = item.getItemId();

        if (id == R.id.nav_myMall) {
            // Handle the camera action
            // this will again re-create the icons which got hide
            actionBarLogo.setVisibility(View.VISIBLE);// again logo we appears
            invalidateOptionsMenu();
            setFragment(new HomeFrag(), homeFragment);
        } else if (id == R.id.nav_myOrders) {
            // Handle the camera action
            goToFragment("My Orders", new MyOrdersFragment(), OrdersFragment);

        } else if (id == R.id.nav_myRewards) {
            goToFragment("My Rewards", new MyRewardsFragment(), reward_Fragment);

        } else if (id == R.id.nav_myCart) {
            goToFragment("My Cart", new MyCartFragment(), cartFragment);

        } else if (id == R.id.nav_myWishlist) {
            goToFragment("My WishList", new MyWishListFragment(), MYWISH_Fragment);

        } else if (id == R.id.nav_myAccount) {
            goToFragment("My Account", new MyAccountFragment(), account_fragment);

        } else if (id == R.id.nav_mySignOut) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Important notice some kind of warning or error is coming related to fragment packages
    // so to make it clear and consice i have used fragment library which is written below:
    /* android.support.v4.app public abstract class FragmentTransaction
extends Object
Static library support version of the framework's android.app.FragmentTransaction. Used to write apps that run on platforms prior to Android 3.0. When running on Android 3.0 or above, this implementation is still used; it does not try to switch to the framework's implementation. See the framework SDK documentation for a class overview.

  Gradle: com.android.support:support-fragment:27.1.1@aar*/
    private void setFragment(Fragment fragment, int fragmentNo) {
        // this will not load fragment again and again
        if (fragmentNo != currentFragment) {

            if (fragmentNo == reward_Fragment) {

                window.setStatusBarColor(Color.parseColor("#CC0000"));
                toolbar.setBackgroundColor(Color.parseColor("#cc0000"));
            } else {
                window.setStatusBarColor(getResources().getColor(R.color.newBarColor));
                toolbar.setBackgroundColor(getResources().getColor(R.color.newBarColor));
            }

            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);


            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }

    }
}
