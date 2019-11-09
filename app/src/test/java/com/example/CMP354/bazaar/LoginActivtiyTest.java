package com.example.CMP354.bazaar;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.CMP354.bazaar.Activities.LoginActivity;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowApplication;

import java.sql.SQLException;

import static com.example.CMP354.bazaar.Activities.LoginActivity.createConnection;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.robolectric.Shadows.shadowOf;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class LoginActivtiyTest {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button buttonU;
    private Button buttonA;
    private Button buttonSignup;

    LoginActivity activity;
    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        activity = Robolectric.setupActivity(LoginActivity.class);
        buttonU = (Button) activity.findViewById(R.id.user_login_in_button);
        buttonA = (Button) activity.findViewById(R.id.admin_login_in_button);
        buttonSignup = (Button) activity.findViewById(R.id.email_sign_up_button);
        mEmailView = (AutoCompleteTextView) activity.findViewById(R.id.email);
        mPasswordView = (EditText) activity.findViewById(R.id.password);
    }


    @Test
    public void UserLoginWithEmptyUsernameAndPassword() {
        buttonU.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity should not start", application.getNextStartedActivity(), is(nullValue()));
        assertThat("Show error for Email field ", mEmailView.getError(), is(CoreMatchers.notNullValue()));
        assertThat("Show error for Password field ", mPasswordView.getError(), is(CoreMatchers.notNullValue()));
    }

    @Test
    public void AdminLoginWithEmptyUsernameAndPassword() {
        buttonA.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity should not start", application.getNextStartedActivity(), is(nullValue()));
        assertThat("Show error for Email field ", mEmailView.getError(), is(CoreMatchers.notNullValue()));
        assertThat("Show error for Password field ", mPasswordView.getError(), is(CoreMatchers.notNullValue()));
    }

   @Test
    public void UserLoginEmailFailure() {
        mEmailView.setText("invalid@email");
        buttonU.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity should not start", application.getNextStartedActivity(), is(nullValue()));
        assertThat("Show error for Email field ", mEmailView.getError(), is(CoreMatchers.notNullValue()));
    }
    @Test
    public void AdminLoginEmailFailure() {
        mEmailView.setText("invalid@email");
        buttonA.performClick();

        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        assertThat("Next activity should not start", application.getNextStartedActivity(), is(nullValue()));
        assertThat("Show error for Email field ", mEmailView.getError(), is(CoreMatchers.notNullValue()));
    }
       @Test
    public void viewSignUpActivityStartedOnClick() {
        buttonSignup.performClick();
           ShadowApplication application = shadowOf(RuntimeEnvironment.application);
           assertThat("Next activity has started", application.getNextStartedActivity(), is(notNullValue()));

	    }
	 /*   @Test
	  public void viewAllActivityStartedOnClick() {
            ShadowApplication application = shadowOf(RuntimeEnvironment.application);

      	        activity.findViewById(R.id.email_sign_up_button).performClick();
    	        Intent expectedIntent = new Intent(activity, SignUpActivity.class);
            assertThat("Next activity has started", application.getNextStartedActivity(),(CoreMatchers.equalTo(expectedIntent)));

            assertThat(application.getNextStartedActivity()).isEqualTo(expectedIntent);
	    }*/



}
