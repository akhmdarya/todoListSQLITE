package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var logo = findViewById<ImageView>(R.id.ic_logo)

        //startActivity(Intent(this,Dashboard::class.java))

        val animation_in =  AnimationUtils.loadAnimation(this,R.anim.splash_in)
        val animation_out =  AnimationUtils.loadAnimation(this,R.anim.splash_out)
        logo.startAnimation(animation_in)


        Handler().postDelayed({
            ic_logo.startAnimation(animation_out)
            Handler().postDelayed({
                ic_logo.visibility=View.GONE
                startActivity(Intent(this,Dashboard::class.java))
                finish()
            },500)
    },1500)

    }
}