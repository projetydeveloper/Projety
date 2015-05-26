package com.projety.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.projety.app.ProjetYApplication;
import com.projety.app.R;
import com.projety.model.User;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;

/**
 * A login screen that offers login via email/password.
 */

public class LoginActivity extends FragmentActivity {



    public static final String TAG = "LoginActivity";


    // UI references.
    private LoginButton mFacebookLoginBtn;
    private TwitterLoginButton mTwitterLoginBtn;
    private Button mMailLoginBtn ;

    private UiLifecycleHelper uiHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        if(getSkipLoginActivity()){
            openHomeScreen();
        }

        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(getString(R.string.twitter_key), getString(R.string.twitter_secret));
        Fabric.with(this, new Twitter(authConfig));

        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        mFacebookLoginBtn = (LoginButton) findViewById(R.id.facebook_login_button);
        mFacebookLoginBtn.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
            @Override
            public void onUserInfoFetched(GraphUser user) {
                if (user != null) {

                    User mUser=new User();
                    mUser.setName(user.getLastName()+" "+user.getFirstName());
                    mUser.setLoginId(""+user.getId());
                    mUser.setLoginProvider(User.LOGIN_PROVIDER_FACEBOOK);
                    setSkipLoginActivity(mUser);

                }
            }
        });


        mTwitterLoginBtn = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        mTwitterLoginBtn.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
               // Do something with result, which provides a TwitterSession for making API calls
                TwitterSession session=result.data;
                User mUser=new User();
                mUser.setName(session.getUserName());
                mUser.setLoginId(""+session.getId());
                mUser.setLoginProvider(User.LOGIN_PROVIDER_TWITTER);

                setSkipLoginActivity(mUser);

                openHomeScreen();

            }

            @Override
            public void failure(TwitterException exception) {
            // Do something on failure
            }
        });


        mMailLoginBtn = (Button)findViewById(R.id.login_btn_mail);
        mMailLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginMailActivity.class);
                //intent.putExtra(TAG, msg);
                startActivity(intent);
            }
        });

    }


    //
    public  void setSkipLoginActivity(User mUser){

        SharedPreferences sharedPref = getSharedPreferences(ProjetYApplication.getPREFS_NAME(), 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(ProjetYApplication.getPREF_SHOW_LOGIN_ACTIVITY(), true);
        editor.commit();
        ProjetYApplication.setmUser(mUser);
    }

    //
    public  boolean getSkipLoginActivity(){
        SharedPreferences sharedPref = getSharedPreferences(ProjetYApplication.getPREFS_NAME(), 0);
        return sharedPref.getBoolean(ProjetYApplication.getPREF_SHOW_LOGIN_ACTIVITY(),false);
    }


    /** Called when the user clicks the Send button */
    public void openHomeScreen() {
        Intent intent = new Intent(this, HomeActivity.class);
        //intent.putExtra(TAG, msg);
        startActivity(intent);
        finish();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Facebook
        uiHelper.onActivityResult(requestCode, resultCode, data);

        //Twitter
        mTwitterLoginBtn.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        uiHelper.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);

    }

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i(TAG, "Logged in...");
            openHomeScreen();

        } else if (state.isClosed()) {
            Log.i(TAG, "Logged out...");
        }
    }

}



