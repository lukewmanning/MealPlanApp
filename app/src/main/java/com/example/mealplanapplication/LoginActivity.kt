package com.example.mealplanapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    //authentication variables
    private var mAuth = FirebaseAuth.getInstance()
    private var currentUser = mAuth.currentUser

    lateinit var emailText: EditText
    lateinit var passwordText: EditText
    lateinit var loginButton: MaterialButton
    lateinit var registerButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        //UI Elements
        emailText = findViewById(R.id.enterEmail)
        passwordText = findViewById(R.id.enterPassword)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)


        loginButton.setOnClickListener { v -> loginClick(v) }
        registerButton.setOnClickListener { v -> registerClick(v) }

    }

    private fun registerClick(view: View) {
        if (mAuth.currentUser != null) {
            displayMessage(view, getString(R.string.registerLoggedIn))
        } else {
            mAuth.createUserWithEmailAndPassword(
                emailText.text.toString(),
                passwordText.text.toString()
            ).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    closeKeyBoard()
                    update()

                    //Launch Next Activity
                    try {
                        //val newIntent = Intent(this, RegisterActivity::class.java)
                        //startActivity(newIntent)
                    } catch (e : Exception) {
                        Log.i("Activities", "null input")
                    }

                } else {
                    closeKeyBoard()
                    displayMessage(registerButton, getString(R.string.regErr))
                }
            }
        }
    }

    private fun loginClick(view: View) {
        mAuth.signInWithEmailAndPassword(
            emailText.text.toString(),
            passwordText.text.toString()
        ).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                closeKeyBoard()
                update()

                //val newIntent = Intent(this, NewsActivity :: class.java)
                //startActivity(newIntent)
            } else {
                closeKeyBoard()
                displayMessage(loginButton, getString(R.string.logErr))
            }
        }
    }

    //To be moved to other activities once app is complete
    private fun logoutClick(view: View) {
        currentUser = mAuth.currentUser
        mAuth.signOut()
        update()
    }

    //Update UI
    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        //updateUI(currentUser)

        update()
    }

    override fun onStop() {
        super.onStop()
        currentUser = mAuth.currentUser
        mAuth.signOut()
    }

    private fun displayMessage(view: View, message: String) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackBar.show()
    }

    fun update() {
        currentUser = mAuth.currentUser

        val currentEmail = currentUser?.email

        val welcome = findViewById<TextView>(R.id.welcomeView)

        if (currentEmail == null) {
            welcome.text = getString(R.string.notLoggedIn)
        } else {
            welcome.text = getString(R.string.loggedIn)
        }
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }



}