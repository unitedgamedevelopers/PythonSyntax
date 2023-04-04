package com.georgiancollege.pythonsyntax

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_sign_up)


        auth = Firebase.auth
        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)

        // Sign up button setup
        val buttonSignUp = findViewById<Button>(R.id.buttonSignUp)
        buttonSignUp.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty())
            {
                Toast.makeText(this, "Please enter credentials", Toast.LENGTH_SHORT).show()
            }
            else
            {
                signUp(email, password)
            }
        }

        //Sign in button setup
        val buttonSignIn = findViewById<Button>(R.id.sign_in_button)
        buttonSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign up success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    // Go to the next activity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign up fails, display a message to the user.
                    val errorCode = (task.exception as FirebaseAuthException).errorCode
                    // Handle the error as you wish
                    signUpErrorCodesHandler(errorCode)
                }
            }
    }

    private fun signUpErrorCodesHandler(error: String){
        when (error) {
            "ERROR_INVALID_CUSTOM_TOKEN" -> Toast.makeText(this, "Invalid custom token format", Toast.LENGTH_SHORT).show()
            "ERROR_CUSTOM_TOKEN_MISMATCH" -> Toast.makeText(this, "Custom token does not match Firebase project", Toast.LENGTH_SHORT).show()
            "ERROR_INVALID_CREDENTIAL" -> Toast.makeText(this, "Invalid auth credential", Toast.LENGTH_SHORT).show()
            "ERROR_INVALID_EMAIL" -> Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
            "ERROR_WRONG_PASSWORD" -> Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show()
            "ERROR_USER_MISMATCH" -> Toast.makeText(this, "Supplied credentials do not match previous user", Toast.LENGTH_SHORT).show()
            "ERROR_REQUIRES_RECENT_LOGIN" -> Toast.makeText(this, "Operation requires recent authentication", Toast.LENGTH_SHORT).show()
            "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL" -> Toast.makeText(this, "Account with email already exists", Toast.LENGTH_SHORT).show()
            "ERROR_EMAIL_ALREADY_IN_USE" -> Toast.makeText(this, "Email address already in use", Toast.LENGTH_SHORT).show()
            "ERROR_CREDENTIAL_ALREADY_IN_USE" -> Toast.makeText(this, "Credential already in use with different user account", Toast.LENGTH_SHORT).show()
            "ERROR_USER_DISABLED" -> Toast.makeText(this, "User account has been disabled", Toast.LENGTH_SHORT).show()
            "ERROR_USER_TOKEN_EXPIRED" -> Toast.makeText(this, "User token has expired, please sign in again", Toast.LENGTH_SHORT).show()
            "ERROR_USER_NOT_FOUND" -> Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
            "ERROR_INVALID_USER_TOKEN" -> Toast.makeText(this, "Invalid user token, please sign in again", Toast.LENGTH_SHORT).show()
            "ERROR_OPERATION_NOT_ALLOWED" -> Toast.makeText(this, "This operation is not allowed", Toast.LENGTH_SHORT).show()
            "ERROR_WEAK_PASSWORD" -> Toast.makeText(this, "Password is too weak, please choose a stronger one", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, "Unknown error occurred", Toast.LENGTH_SHORT).show()
        }
    }
}