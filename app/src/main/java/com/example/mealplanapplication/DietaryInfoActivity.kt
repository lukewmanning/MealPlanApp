package com.example.mealplanapplication

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar

/**
 * Main functions of DietaryInfoActivity
 * Read in DietaryInfo specific to user
 *      #meals, meal timings, diet style (i.e keto),
 *      likes/dislikes + allergies
 * Error checks
 */

class DietaryInfoActivity : AppCompatActivity() {
    lateinit var likedInput : EditText
    lateinit var dislikedInput : EditText
    lateinit var allergicInput : EditText
    lateinit var likedConfirm : MaterialButton
    lateinit var dislikedConfirm : MaterialButton
    lateinit var allergicConfirm : MaterialButton
    lateinit var continueButton : MaterialButton
    lateinit var gainWeightBtn : MaterialButton
    lateinit var loseWeightBtn : MaterialButton
    lateinit var healthBtn : MaterialButton
    lateinit var likedView : ListView
    lateinit var dislikedView : ListView
    lateinit var allergicView : ListView
    lateinit var likedList : ArrayList<String>
    lateinit var dislikedList : ArrayList<String>
    lateinit var allergicList : ArrayList<String>
    lateinit var likedArrayAdapter: ArrayAdapter<*>
    lateinit var dislikedArrayAdapter: ArrayAdapter<*>
    lateinit var allergicArrayAdapter: ArrayAdapter<*>
    lateinit var goal: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dietary_info)

        //UI Elements for goal buttons
        gainWeightBtn = findViewById(R.id.gainWeightButton)
        loseWeightBtn = findViewById(R.id.loseWeightButton)
        healthBtn = findViewById(R.id.healthButton)
        //UI Elements for liked foods
        likedView = findViewById(R.id.foods_liked)
        likedConfirm = findViewById(R.id.confirmLiked)
        likedInput = findViewById(R.id.addLiked)
        //UI Elements for disliked foods
        dislikedView = findViewById(R.id.foods_disliked)
        dislikedConfirm = findViewById(R.id.confirmDisliked)
        dislikedInput = findViewById(R.id.addDisliked)
        //UI Elements for allergies
        allergicView = findViewById(R.id.allergic)
        allergicConfirm = findViewById(R.id.confirmAllergic)
        allergicInput = findViewById(R.id.addLiked)
        //UI Element for continue button
        continueButton = findViewById(R.id.continueButton)

        //Array Adapters
        likedArrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, likedList)
        dislikedArrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dislikedList)
        allergicArrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, allergicList)

        //on Click Listeners
        gainWeightBtn.setOnClickListener{v -> goalClick(v)}
        loseWeightBtn.setOnClickListener{v -> goalClick(v)}
        healthBtn.setOnClickListener{v -> goalClick(v)}
        likedConfirm.setOnClickListener{v -> oneClick(v, likedArrayAdapter, likedInput, likedList)}
        dislikedConfirm.setOnClickListener{v -> oneClick(v, dislikedArrayAdapter, dislikedInput, dislikedList)}
        allergicConfirm.setOnClickListener{v -> oneClick(v, allergicArrayAdapter, allergicInput, allergicList)}
        continueButton.setOnClickListener{v -> continueClick(v)}
    }

    private fun continueClick(v: View) {
        //Also needs to store info into the database
        //Continue to Main Activity

        if (goal != null) {
            //val newIntent = Intent(this, MealActivity :: class.java)
            //startActivity(newIntent)
        } else {
            displayMessage(continueButton, "No goal has been chosen")
        }
    }

    private fun oneClick(v: View, a: ArrayAdapter<*>, t: EditText, l: ArrayList<String>) {
        val text = t.text.toString()
        l.add(text)

        a.notifyDataSetChanged()
    }

    private fun goalClick(v: View) {
        //Set goal var to show which button was clicked - confirm one was set
        //store this in the database as their goal
    }

    private fun displayMessage(view: View, message: String) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackBar.show()
    }

    /*
    private fun likedClick(v: View) {
        val text = likedInput.text.toString()
        likedList.add(text)

        likedArrayAdapter.notifyDataSetChanged()
    }
    private fun dislikedClick(v: View) {
        val text = dislikedInput.text.toString()
        dislikedList.add(text)

        dislikedArrayAdapter.notifyDataSetChanged()
    }
    private fun allergicClick(v: View) {
        val text = likedInput.text.toString()
        likedList.add(text)

        allergicArrayAdapter.notifyDataSetChanged()
    }*/
}