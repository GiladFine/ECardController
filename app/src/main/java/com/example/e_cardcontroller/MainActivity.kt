package com.example.e_cardcontroller

import android.media.Image
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.util.JsonReader
import android.view.LayoutInflater
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.json.JSONArray
import java.net.URL
import java.net.HttpURLConnection
import org.json.JSONObject
import android.os.StrictMode

class MainActivity : AppCompatActivity() {




    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)


        var leftButton = findViewById<ImageButton>(R.id.leftButton)
        var rightButton = findViewById<ImageButton>(R.id.rightButton)

        var button1 = findViewById<Button>(R.id.button1)
        var button2 = findViewById<Button>(R.id.button2)
        var button3 = findViewById<Button>(R.id.button3)
        var button4 = findViewById<Button>(R.id.button4)

        //Get Button Strings
        val base_url = "http://172.16.2.11"

        val url = URL(base_url + "/get_button_names")

            with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"  // optional default is GET


                inputStream.bufferedReader().use { bufferedReader ->
                    bufferedReader.lines().forEach { line ->
                    val jsonshit = JSONObject(line)
                    button1.setText(jsonshit.getString("1"))
                    button2.setText(jsonshit.getString("2"))
                    button3.setText(jsonshit.getString("3"))
                    button4.setText(jsonshit.getString("4"))
                }
            }
        }


        var cardsImages = mapOf<String, Int>(
            Pair("2C", R.drawable.c2), Pair("2D", R.drawable.d2), Pair("2H", R.drawable.h2), Pair("2S", R.drawable.s2),
            Pair("3C", R.drawable.c3), Pair("3D", R.drawable.d3), Pair("3H", R.drawable.h3), Pair("3S", R.drawable.s3),
            Pair("4C", R.drawable.c4), Pair("4D", R.drawable.d4), Pair("4H", R.drawable.h4), Pair("4S", R.drawable.s4),
            Pair("5C", R.drawable.c5), Pair("5D", R.drawable.d5), Pair("5H", R.drawable.h5), Pair("5S", R.drawable.s5),
            Pair("6C", R.drawable.c6), Pair("6D", R.drawable.d6), Pair("6H", R.drawable.h6), Pair("6S", R.drawable.s6),
            Pair("7C", R.drawable.c7), Pair("7D", R.drawable.d7), Pair("7H", R.drawable.h7), Pair("7S", R.drawable.s7),
            Pair("8C", R.drawable.c8), Pair("8D", R.drawable.d8), Pair("8H", R.drawable.h8), Pair("8S", R.drawable.s8),
            Pair("9C", R.drawable.c9), Pair("9D", R.drawable.d9), Pair("9H", R.drawable.h9), Pair("9S", R.drawable.s9),
            Pair("10C", R.drawable.c10), Pair("10D", R.drawable.d10), Pair("10H", R.drawable.h10), Pair("10S", R.drawable.s10),
            Pair("JC", R.drawable.cj), Pair("JD", R.drawable.dj), Pair("JH", R.drawable.hj), Pair("JS", R.drawable.sj),
            Pair("QC", R.drawable.cq), Pair("QD", R.drawable.dq), Pair("QH", R.drawable.hq), Pair("QS", R.drawable.sq),
            Pair("KC", R.drawable.ck), Pair("KD", R.drawable.dk), Pair("KH", R.drawable.hk), Pair("KS", R.drawable.sk),
            Pair("AC", R.drawable.ca), Pair("AD", R.drawable.da), Pair("AH", R.drawable.ha), Pair("AS", R.drawable.sa),
            Pair("Joker1", R.drawable.joker1), Pair("Joker1", R.drawable.joker1)
            )

        var cardsLayout = findViewById<LinearLayout>(R.id.cardsLayout)
        var cardsInflater = LayoutInflater.from(this)

        fun clearCardsDisplay()
        {
            cardsLayout.removeAllViews()
        }

        fun displayCard(card: Int)
        {
            var view = cardsInflater.inflate(R.layout.item, cardsLayout, false)

            var cardImage = view.findViewById<ImageView>(R.id.image_view)
            cardImage.setImageResource(card)

            cardsLayout.addView(view)
        }

        clearCardsDisplay()
        for (card in cardsImages)
        {
            displayCard(card.value)
        }

        //Left onClick
        leftButton.setOnClickListener {
            Toast.makeText(this@MainActivity, "Left.", Toast.LENGTH_SHORT).show()

        }

        //Right onClick
        rightButton.setOnClickListener {
            Toast.makeText(this@MainActivity, "Right.", Toast.LENGTH_SHORT).show()
        }

        //Button 1 onClick
        button1.setOnClickListener {
            Toast.makeText(this@MainActivity, "1", Toast.LENGTH_SHORT).show()

            val url1 = URL(base_url + "/button1_pressed")

            with(url1.openConnection() as HttpURLConnection) {
                requestMethod = "POST"  // optional default is GET

                inputStream.bufferedReader().use {
                    it.lines().forEach { line ->
                        val jsonshit = JSONObject(line)
                        val command = jsonshit.getString("Command")
                        if (command == "show_hand")
                        {
                            val args: JSONArray = jsonshit.getJSONArray("args")
                            clearCardsDisplay()
                            for(i in 0..args.length())
                            {
                                if(cardsImages[args[i].toString()] != null)
                                {
                                    val cardImage: Int = cardsImages[args[i].toString()]!!
                                    displayCard(cardImage)
                                }
                            }
                        }
                    }
                }
            }
        }

        //Button 2 onClick
        button2.setOnClickListener {
            val url2 = URL(base_url + "/button2_pressed")

            with(url2.openConnection() as HttpURLConnection) {
                requestMethod = "POST"  // optional default is GET

                inputStream.bufferedReader().use {
                    it.lines().forEach { line ->
                        val jsonshit = JSONObject(line)
                        val command = jsonshit.getString("Command")
                        if (command == "message") {
                            val message: String = jsonshit.getString("string")
                            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                        }
                        else if (command == "show_hand and message")
                        {
                            val message: String = jsonshit.getString("string")
                            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()

                            val args: JSONArray = jsonshit.getJSONArray("args")
                            clearCardsDisplay()
                            for(i in 0..args.length())
                            {
                                if(cardsImages[args[i].toString()] != null)
                                {
                                    val cardImage: Int = cardsImages[args[i].toString()]!!
                                    displayCard(cardImage)
                                }
                            }
                        }
                    }
                }
            }

        }

        //Button 3 onClick
        button3.setOnClickListener {
            Toast.makeText(this@MainActivity, "3", Toast.LENGTH_SHORT).show()
            var response = JSONObject().put("Button Name", "b3")

        }

        //Button 4 onClick
        button4.setOnClickListener {
            Toast.makeText(this@MainActivity, "4", Toast.LENGTH_SHORT).show()
            var response = JSONObject().put("Button Name", "b4")
        }
    }

}
