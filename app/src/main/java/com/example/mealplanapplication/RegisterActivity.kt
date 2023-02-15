package com.example.mealplanapplication

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar

/**
 * Register Activity
 * Function of this class:
 * read in user's personal info
 * Checks to verify this is correct (i.e age is int / weight > 0)
 *      Error messages if incorrect + must redo
 * Store this in database (or elsewhere)
 * Open DietaryInfoActivity
 */

class RegisterActivity : AppCompatActivity() {
    lateinit var userName : EditText
    lateinit var userAge : EditText
    lateinit var userGender : Spinner
    lateinit var userHeight : EditText
    lateinit var userWeight : EditText
    lateinit var continueButton : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userName = findViewById(R.id.userName)
        userAge = findViewById(R.id.userAge)
        userGender = findViewById(R.id.userGender)
        userHeight = findViewById(R.id.userHeight)
        userWeight = findViewById(R.id.userWeight)

        continueButton = findViewById(R.id.continue_button)

        continueButton.setOnClickListener{v -> continueClick(v)}
    }

    private fun continueClick(v: View?) {
        val uName = userName.text.toString()
        val uAge = userAge.text.toString()
        val uHeight = userHeight.text.toString()
        val uWeight = userWeight.text.toString()


        if (uName.length == 0 || uAge.length == 0 ||
            uWeight.length == 0 || uHeight.length == 0) {
            userName.requestFocus()
            userName.setError("Field cannot be empty")
        } else if (!TextUtils.isDigitsOnly(uAge)) {
            userAge.requestFocus()
            userAge.setError("Please enter an age (0-99)")
        } else if (!TextUtils.isDigitsOnly(uWeight)) {
            userWeight.requestFocus()
            userWeight.setError("Please enter weight as a number")
        } else if (!TextUtils.isDigitsOnly(uHeight)) {
            userHeight.requestFocus()
            userHeight.setError("Please enter height as a number")
        } else if (userGender != null) {
            userGender.requestFocus()
            displayMessage(continueButton, "Please select an option")
        } else {
            //Store values into database
            //val intent = Intent(this, DietaryInfoActivity::class.java)
            //startActivity(intent)
        }


    }

    private fun displayMessage(view: View, message: String) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackBar.show()
    }
}