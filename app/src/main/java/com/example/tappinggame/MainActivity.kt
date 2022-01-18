package com.example.tappinggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    lateinit var playBtn : Button
    lateinit var runTimer : TextView
    var t = 10

    lateinit var highScore : TextView
    lateinit var latestScore : TextView
    var lastScore = 0
    var hscore = 0

    lateinit var seekbar: SeekBar
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        highScore = findViewById(R.id.highScore)
        latestScore = findViewById(R.id.LastScore)

        val preferences = getSharedPreferences("PREF", 0)
        lastScore = preferences.getInt("lastScore", 0)
        hscore = preferences.getInt("hscore", 0)
        if(lastScore>hscore){
            hscore = lastScore
            val editor = preferences.edit()
            editor.putInt("hscore", hscore)
            editor.apply()
        }
        highScore.text = "$hscore"
        latestScore.text = "$lastScore"

        playBtn = findViewById(R.id.play)
        playBtn.setOnClickListener {
            val intent = Intent(applicationContext, PlayGround::class.java)
            intent.putExtra("message_key", t.toString())
            startActivity(intent)
            finish()
        }

        val btnreset : Button = findViewById(R.id.reset)
        btnreset.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Reset All Scores ? ")
                .setNegativeButton("Cancel"){dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton("All Scores"){dialog, _ ->
                    highScore.text = "0"
                    latestScore.text = "0"
                    lastScore = preferences.getInt("lastScore", 0)
                    hscore = preferences.getInt("hscore", 0)
                    val editor = preferences.edit()
                    editor.putInt("lastScore", 0)
                    editor.putInt("hscore", 0)
                    editor.apply()
                    dialog.dismiss()
                }
                .create()
                .show()
        }


        seekbar = findViewById(R.id.seek_bar)
        textView = findViewById(R.id.text_view)

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                t = progress
                textView.text = "$t/30"
                runTimer = findViewById(R.id.RunTimer)
                runTimer.text = "$t s"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        val closing : Button = findViewById(R.id.close)
        closing.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Do you want to EXIT the app ?")
                .setNegativeButton("N0"){dialog, _ -> dialog.dismiss()}
                .setPositiveButton("YES"){dialog, _ ->
                    finish()
                    dialog.dismiss()
                }
                .create()
                .show()
        }

    }

    override fun onBackPressed() {
        Toast.makeText(this, "Back Functionality Disabled", Toast.LENGTH_SHORT).show()
    }
}