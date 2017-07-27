package jb.com.teamattnd.ui.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import jb.com.teamattnd.R




class LoginActivity : AppCompatActivity() {

    private var mAuthTask: LoginActivity.UserLoginTask? = null
    // UI references.
    private var mEmailView: EditText? = null
    private var mPasswordView: EditText? = null
    private var mProgressView: View? = null
    private var mLoginFormView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        mEmailView = findViewById(R.id.email) as EditText

        mPasswordView = findViewById(R.id.password) as EditText
//        mPasswordView!!.setOnEditorActionListener( TextView.OnEditorActionListener { textView, id, keyEvent ->
//            if ( id == R.id.login || id == EditorInfo.IME_NULL){
////                attemptLogin()
//                return@OnEditorActionListener true
//            }
//            false
//        })

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
        val email = mEmailView!!.text.toString()
        val password = mPasswordView!!.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView!!.error = getString(R.string.error_invalid_password)
            focusView = mPasswordView
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView!!.error = getString(R.string.error_field_required)
            focusView = mEmailView
            cancel = true
        } else if (!isEmailValid(email)) {
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
//            mAuthTask!!.execute(null as Void?)
//            login()
            Handler().postDelayed(
                    {
                        // On complete call either onLoginSuccess or onLoginFailed
                        login()
                        // onLoginFailed();
                        showProgress(false)
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

            for (credential in LoginActivity.ADMIN_CREDENTIALS) {
                val pieces = credential.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                if (pieces[0] == mEmail) {
                    // Account exists, return true if the password matches.
                    return pieces[1] == mPassword
                }
            }

            // TODO: register the new account here.
            return true
        }

        override fun onPostExecute(success: Boolean?) {
            mAuthTask = null
            showProgress(false)

            if (success!!) {
                login()
                finish()
            } else {
                mPasswordView!!.error = getString(R.string.error_incorrect_password)
                mPasswordView!!.requestFocus()
            }
        }

        override fun onCancelled() {
            mAuthTask = null
            showProgress(false)
        }
    }

    companion object {

        /**
         * Id to identity READ_CONTACTS permission request.
         */
        private val REQUEST_READ_CONTACTS = 0

        /**
         * A dummy authentication store containing known user names and passwords.
         * TODO: remove after connecting to a real authentication system.
         */
        private val ADMIN_CREDENTIALS = arrayOf("a@a.com:11111", "bar@example.com:world")
    }

    fun login()
    {
        showProgress(false)
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    var progressDialog: ProgressDialog? = null
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
