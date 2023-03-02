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


var checkLogin : Boolean = false
class MainActivity : AppCompatActivity() {
    // For google Sign In
    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

    //    For intents
    private lateinit var loginButton : Button
    private lateinit var loginEmail : TextInputEditText
    private lateinit var loginPassword : TextInputEditText
    private lateinit var signUp : TextView

    //    for email sign in
    private lateinit var eAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Google Sign in
        auth = FirebaseAuth.getInstance()
<<<<<<< HEAD
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().build()
=======
       val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
           .requestIdToken(getString(R.string.default_web_client_id))
           .requestEmail().build()
>>>>>>> 53b4940 (Made some changes)

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        findViewById<Button>(R.id.gSignInBtn).setOnClickListener {
            signInGoogle()
        }

//        ---------Intents for changing activities----------
        loginButton= findViewById(R.id.loginButton)
        loginEmail = findViewById(R.id.loginEmail)
        loginPassword = findViewById(R.id.loginPassword)
        loginButton.setOnClickListener {
            startActivity(Intent(this, loggedIn::class.java))
            logInUser()
        }

        signUp = findViewById(R.id.logIn)
        signUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

//        ---------For email sign in--------
        eAuth = FirebaseAuth.getInstance()

    }

    private fun logInUser() {
        val liEmail = loginEmail.text.toString().trim()
        val liPassword = loginPassword.text.toString().trim()

        if (liEmail.isEmpty()){
            loginEmail.error = "*It is required!"
            loginEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(liEmail).matches()){
            loginEmail.error = "*Please enter a valid email"
            loginEmail.requestFocus()
            return
        }

        if (liPassword.isEmpty()){
            loginPassword.error = "*It is required!"
            loginPassword.requestFocus()
            return
        }

    }

    //    --------Google sign in methods--------
    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
        checkLogin = true

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
<<<<<<< HEAD

            }

=======
            }
>>>>>>> 53b4940 (Made some changes)
        }
    }

}