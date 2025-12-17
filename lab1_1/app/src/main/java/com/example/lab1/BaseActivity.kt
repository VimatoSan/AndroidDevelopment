package com.example.lab1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge

abstract class BaseActivity : AppCompatActivity()  {
    val tag = "MY_APP"

    protected abstract val layoutId: Int
    protected abstract val buttonId: Int
    protected abstract val nextActivityClass: Class<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle("created");
        enableEdgeToEdge()
        setContentView(layoutId)
        initButton()
    }

    private fun logLifecycle(status: String) {
        Log.d(tag, "${this.componentName.shortClassName} $status")
    }

    private fun initButton() {
        val btnNext: Button = this.findViewById(buttonId);
        btnNext.setOnClickListener {
            logLifecycle("go to ${nextActivityClass.simpleName}")
            val intent = Intent(this, nextActivityClass)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("started")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("resumed")
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("paused")
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle("destroyed")
    }
}