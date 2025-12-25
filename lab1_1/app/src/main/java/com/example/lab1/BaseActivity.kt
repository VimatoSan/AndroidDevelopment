package com.example.lab1

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

abstract class BaseActivity : AppCompatActivity()  {
    val tag = "MY_APP"

    protected abstract val layoutId: Int
    protected abstract val buttonId: Int
    protected abstract val nextActivityClass: Class<*>

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycle.addObserver(LifecycleLogger())

        setContentView(layoutId)
        initButton()
    }


    private fun initButton() {
        val btnNext: Button = this.findViewById(buttonId);
        btnNext.setOnClickListener {
            Log.d(tag, "go to ${nextActivityClass.simpleName}")
            val intent = Intent(this, nextActivityClass)
            startActivity(intent)
        }
    }

    private fun getActivityName(): String {
        return this::class.java.simpleName
    }

    inner class LifecycleLogger : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            log("CREATED")
        }

        override fun onStart(owner: LifecycleOwner) {
            log("STARTED")
        }

        override fun onPause(owner: LifecycleOwner) {
            log("PAUSED")
        }

        override fun onResume(owner: LifecycleOwner) {
            log("RESUMED")
        }

        override fun onDestroy(owner: LifecycleOwner) {
            log("DESTROYED")
        }

        override fun onStop(owner: LifecycleOwner) {
            log("STOPPED")
        }

        private fun log(state: String) {
            Log.d(tag, "${getActivityName()} - $state")
        }
    }
}