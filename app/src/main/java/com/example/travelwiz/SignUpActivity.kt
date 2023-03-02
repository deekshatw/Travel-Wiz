package com.example.travelwiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class SignUpActivity : AppCompatActivity() {
    //  for google sign in
    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

    //    for email sign in
    private lateinit var eAuth: FirebaseAuth

    //    initializing vars
    private lateinit var suEmail : TextInputEditText
    private lateinit var suPhone : TextInputEditText
    private lateinit var suPassword : TextInputEditText
    private lateinit var suConfirmPassword : TextInputEditText
    private lateinit var signUpButton : Button
    private lateinit var logIn : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

//        -------vars-------
        suEmail = findViewById(R.id.suEmail)
        suPhone = findViewById(R.id.suPhone)
        suPassword = findViewById(R.id.suPassword)
        suConfirmPassword = findViewById(R.id.suConfirmPassword)
        signUpButton = findViewById(R.id.signUpButton)
        logIn = findViewById(R.id.logIn)

//        -------intents------
        signUpButton.setOnClickListener {
            startActivity(Intent(this, loggedIn::class.java))
            signUpUser()
        }
        logIn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }


//        ------google sign in--------
        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        findViewById<Button>(R.id.gSignInBtn).setOnClickListener {
            signInGoogle()
        }
    }

    private fun signUpUser() {
        val signUpEmail = suEmail.text.toString().trim()
        val signUpPhone = suPhone.text.toString().trim()
        val signUpPassword = suPassword.text.toString().trim()
        val signUpConfirmPassword = suConfirmPassword.text.toString().trim()

        if (signUpEmail.isEmpty()){
            suEmail.error = "It is required!"
            suEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(signUpEmail).matches()){
            suEmail.error = "Please enter a valid email"
            suEmail.requestFocus()
            return
        }

        if (signUpPhone.isEmpty()){
            suPhone.error = "It is required!"
            suPhone.requestFocus()
            return
        }

        if (!Patterns.PHONE.matcher(signUpPhone).matches()){
            suPhone.error = "Please enter a valid phone number"
            suPhone.requestFocus()
            return
        }

        if (signUpPassword.isEmpty()){
            suPassword.error = "It is required!"
            suPassword.requestFocus()
            return
        }
        if (signUpPassword.length<6){
            suPassword.error = "Password should contain minimum 6 characters!"
            suPassword.requestFocus()
            return
        }
        if (signUpConfirmPassword.isEmpty()){
            suConfirmPassword.error = "It is required!"
            suPassword.requestFocus()
            return
        }
        if (signUpConfirmPassword.length<6){
            suConfirmPassword.error = "Password should contain minimum 6 characters!"
            suConfirmPassword.requestFocus()
            return
        }

        if (signUpPassword!=signUpConfirmPassword){
            suConfirmPassword.error = "Passwords do not match!"
            suPassword.requestFocus()
            return
        }


    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if(account!= null){
                updateUI(account)
            }
        } else{
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                val intent : Intent = Intent(this, loggedIn::class.java)
                intent.putExtra("email", account.email)
                intent.putExtra("name", account.displayName)
                startActivity(intent)
            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

            }

        }
    }
}