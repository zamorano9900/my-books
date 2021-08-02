package com.zamorano.mybooks.modules

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zamorano.mybooks.R
import com.zamorano.mybooks.modules.categoryBookCollection.CategoryBookActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launchMainActivity()
    }

    private fun launchMainActivity(){
        val intent = Intent(this, CategoryBookActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun launchMockActivity(){
        //val intent = Intent(this, CategoryBookDetailActivity::class.java)
        startActivity(intent)
        finish()
    }
}