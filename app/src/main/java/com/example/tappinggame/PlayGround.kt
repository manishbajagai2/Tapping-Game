package com.example.tappinggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*

class PlayGround : AppCompatActivity(), View.OnClickListener  {
    lateinit var button1 : Button
    lateinit var button2 : Button
    lateinit var button3 : Button
    lateinit var button4 : Button
    lateinit var button5 : Button
    lateinit var button6 : Button

    lateinit var ins : String

    lateinit var scoring : TextView
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.play_ground)

        button1 = findViewById(R.id.Gyes)
        button2 = findViewById(R.id.Gno)
        button3 = findViewById(R.id.Ryes)
        button4 = findViewById(R.id.Rno)
        button5 = findViewById(R.id.Pyes)
        button6 = findViewById(R.id.Pno)

        val inst :TextView = findViewById(R.id.randInst)
        ins = inst()
        inst.text = ins

        scoring = findViewById(R.id.scoring)
        scoring.text = score.toString()

        randPos(button1,800)
        randPos(button2,800)
        randPos(button3,900)
        randPos(button4,900)
        randPos(button5,1000)
        randPos(button6,1000)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)

        val running : TextView = findViewById(R.id.running)
        val timeRan : TextView = findViewById(R.id.timeRan)



        val intent = intent
        val str = intent.getStringExtra("message_key")
        running.text = "$str s"
        val s : Long = str!!.toLong() * 1000

        object : CountDownTimer( s , 1000) {

            override fun onTick(millisUntilFinished: Long) {
                running.text = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                timeRan.text = "Time's Up!"
                MaterialAlertDialogBuilder(this@PlayGround)
                    .setTitle("Time's Up")
                    .setMessage("Your score is : $score")
                    .setCancelable(false)
                    .setPositiveButton("Play again"){dialog, _ ->
                        val preferences = getSharedPreferences("PREF", 0)
                        val editor = preferences.edit()
                        editor.putInt("lastScore", score)
                        editor.apply()
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        finish()
                        dialog.dismiss()
                    }
                    .create()
                    .show()
            }
        }.start()
    }

    override fun onBackPressed() {
        Toast.makeText(this@PlayGround, "Back Functionality Disabled till Timer Ends", Toast.LENGTH_SHORT).show()
    }

    private fun randPos(Btn: Button, Time:Long) {
        val time: Long = Time
        val btn: Button = Btn
        val screenWidth = this.resources.displayMetrics.widthPixels
        val screenHeight = this.resources.displayMetrics.heightPixels
        Timer().schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    val rand = Random()
                    val dx: Float = rand.nextFloat() * screenWidth
                    val dy: Float = rand.nextFloat() * screenHeight
                    btn.animate()
                        .x(dx)
                        .y(dy)
                        .setDuration(0)
                        .start()
                }
            }
        }, 0, time)
    }

    private fun inst(): String{
        val color = arrayOf<String>("Red","Green","Purple")
        val text = arrayOf<String>("Yes","No")
        return "${color.random()} color with ${text.random()} text"
    }

    override fun onClick(view: View) {
        val (s1, s2, s3) = arrayOf("Red color with Yes text", "Red color with No text", "Green color with Yes text")
        val (s4, s5, s6) = arrayOf("Green color with No text", "Purple color with Yes text", "Purple color with No text")

        val inc = 5
        val dec = 10

        when(ins){
            s1 ->{
                when (view.id) {
                    R.id.Gyes -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Gno -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Ryes -> {
                        score+=inc
                        scoring.text = score.toString()
                    }
                    R.id.Rno -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Pyes -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Pno -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    else -> { }
                }
            }
            s2 ->{
                when (view.id) {
                    R.id.Gyes -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Gno -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Ryes -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Rno -> {
                        score+=inc
                        scoring.text = score.toString()
                    }
                    R.id.Pyes -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Pno -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    else -> { }
                }
            }
            s3 ->{
                when (view.id) {
                    R.id.Gyes -> {
                        score+=inc
                        scoring.text = score.toString()
                    }
                    R.id.Gno -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Ryes -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Rno -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Pyes -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Pno -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    else -> { }
                }
            }
            s4 ->{
                when (view.id) {
                    R.id.Gyes -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Gno -> {
                        score+=inc
                        scoring.text = score.toString()
                    }
                    R.id.Ryes -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Rno -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Pyes -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Pno -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    else -> { }
                }
            }
            s5 ->{
                when (view.id) {
                    R.id.Gyes -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Gno -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Ryes -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Rno -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Pyes -> {
                        score+=inc
                        scoring.text = score.toString()
                    }
                    R.id.Pno -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    else -> { }
                }
            }
            s6 ->{
                when (view.id) {
                    R.id.Gyes -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Gno -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Ryes -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Rno -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Pyes -> {
                        score-=dec
                        scoring.text = score.toString()
                    }
                    R.id.Pno -> {
                        score+=inc
                        scoring.text = score.toString()
                    }
                    else -> { }
                }
            }
        }

    }

}