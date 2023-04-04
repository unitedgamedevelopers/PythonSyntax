package com.georgiancollege.pythonsyntax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LevelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        val tutorialTextView = findViewById<TextView>(R.id.tutorialTextView)
        val proceedBtn = findViewById<Button>(R.id.proceedBtn)

        val db = Firebase.firestore


        val tutorialText = db.collection("levels").document("level1").collection("level-tutorial").document("tutorial")
        tutorialText.get()
            .addOnSuccessListener { document ->

                if(document!=null){
                    val messageText = document.getString("info");
                    tutorialTextView.text = messageText
                    Log.d("TAG", "Working")
                }
                else{
                    Log.d("TAG", "No such document")
                }
            }
            .addOnFailureListener{exception->
                Log.d("TAG", "Error getting document: $exception")
            }

    }
}