package com.example.e_cardcontroller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Cards recycler
        var cards_recycler = findViewById<RecyclerView>(R.id.cardsRecyclerView)

        //Left onClick
        var leftButton = findViewById<ImageButton>(R.id.leftButton)
        leftButton.setOnClickListener {
            Toast.makeText(this@MainActivity, "Left.", Toast.LENGTH_SHORT).show()
        }

        //Right onClick
        var rightButton = findViewById<ImageButton>(R.id.rightButton)
        rightButton.setOnClickListener {
            Toast.makeText(this@MainActivity, "Right.", Toast.LENGTH_SHORT).show()
        }

        //Button 1 onClick
        var button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            Toast.makeText(this@MainActivity, "1", Toast.LENGTH_SHORT).show()
        }

        //Button 2 onClick
        var button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            Toast.makeText(this@MainActivity, "2", Toast.LENGTH_SHORT).show()
        }

        //Button 3 onClick
        var button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            Toast.makeText(this@MainActivity, "3", Toast.LENGTH_SHORT).show()
        }

        //Button 4 onClick
        var button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener {
            Toast.makeText(this@MainActivity, "4", Toast.LENGTH_SHORT).show()
        }
    }

}
