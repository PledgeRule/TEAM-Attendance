package jb.com.teamattnd.ui.activities

import android.app.Application
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText

import jb.com.teamattnd.R
import android.support.design.widget.Snackbar
import jb.com.teamattnd.util.Constant
import jb.com.teamattnd.util.Controller
import jb.com.teamattnd.util.PreferenceHelper


class LoginActivity : AppCompatActivity() {

//    private var mAuthTask: LoginActivity.UserLoginTask? = null
    // UI references.
    var progressDialog: ProgressDialog? = null
    private var mEmailView: EditText? = null
    private var mPasswordView: EditText? = null
    private var mProgressView: View? = null
    private var mLoginFormView: View? = null
    private var email: String? = null
    private var password: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        mEmailView = findViewById(R.id.email) as EditText
        mPasswordView = findViewById(R.id.password) as EditText

        val mEmailSignInButton = findViewById(R.id.email_sign_in_button) as Button
        mEmailSignInButton.setOnClickListener { attemptLogin() }

        mLoginFormView = findViewById(R.id.login_form)
        mProgressView = findViewById(R.id.login_progress)
    }

    fun attemptLogin()
    {
        // Reset errors.
        mEmailView!!.error = null
        mPasswordView!!.error = null

        // Store values at the time of the login attempt.
        email = mEmailView!!.text.toString()
        password = mPasswordView!!.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password!!)) {
            mPasswordView!!.error = getString(R.string.error_invalid_password)
            focusView = mPasswordView
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView!!.error = getString(R.string.error_field_required)
            focusView = mEmailView
            cancel = true
        } else if (!isEmailValid(email!!)) {
            mEmailView!!.error = getString(R.string.error_invalid_email)
            focusView = mEmailView
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView!!.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
//            mAuthTask = UserLoginTask(email, password)
//            mAuthTask!!.execute()
//            login()
            Handler().postDelayed(
                    {
                        showProgress(false)
                        // On complete call either onLoginSuccess or onLoginFailed
                        login()

                    }, 3000)
        }

    }

    private fun isEmailValid(email: String): Boolean {
        //TODO: Replace this with your own logic
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 4
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    inner class UserLoginTask internal constructor(private val mEmail: String, private val mPassword: String) : AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void): Boolean? {
            // TODO: attempt authentication against a network service.


            try {
                // Simulate network access.
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                return false
            }


            if(mEmail == Constant.ADMIN_USERNAME && mPassword == Constant.ADMIN_PASSWORD) {
//                defaultSharedPreferences.edit()
//                        .putBoolean(usp!!.ISADMIN, true)
//                        .apply()
            }


            return true
        }

        override fun onPostExecute(success: Boolean?) {
//            mAuthTask = null
            showProgress(false)

            if (success!!) {
                login()
            } else {
                Snackbar.make(mLoginFormView!!, "Credentials are incorrect", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            }
        }

        override fun onCancelled() {
//            mAuthTask = null
            showProgress(false)
        }
    }

    fun login()
    {

        Controller.setUserType(this, email!!, password!!)
        PreferenceHelper.setBooleanPreference(this, Constant.IS_LOGGED_IN, true)
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }


    private fun showProgress(show: Boolean) {

            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.

        if (progressDialog == null) {
            progressDialog = ProgressDialog(this@LoginActivity)
        }

        if(show) {
            progressDialog!!.setIndeterminate(true)
            progressDialog!!.setMessage("Authenticating...")
            progressDialog!!.show()
        }
        else
            progressDialog!!.dismiss()

    }

}
