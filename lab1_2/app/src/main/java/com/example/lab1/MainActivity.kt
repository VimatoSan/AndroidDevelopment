package com.example.lab1

import android.app.Activity
import android.app.Application
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab1.ui.theme.BackgroundA
import com.example.lab1.ui.theme.BackgroundB
import com.example.lab1.ui.theme.BackgroundC
import com.example.lab1.ui.theme.ButtonColor

class MainActivity : ComponentActivity() {
    val tag = "MY_APP"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycle.addObserver(LifecycleLogger())
        setContent {
            AppNav()
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

@Composable
fun AppNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "A") {
        composable("A") {
            A({
                navController.navigate("B")
                Log.d("MY_APP", "Swap to B")
            })
        }

        composable("B") {
            B({
                navController.navigate("C") {
                    Log.d("MY_APP", "Swap to C")
                }
            })
        }

        composable("C") {
            C({
                navController.navigate("A") {
                    popUpTo("A")
                    launchSingleTop = true
                    Log.d("MY_APP", "Swap to A")
                }
            })
        }
    }
}

@Composable
fun ScreenTemplate(backgroundColor: Color, onNext: () -> Unit) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(color = backgroundColor)
            .fillMaxSize()
    ) {
        Button(
            colors=ButtonDefaults.buttonColors(containerColor = ButtonColor),
            modifier = Modifier
                .width(140.dp)
                .height(50.dp),
            onClick = onNext
        ) {
            Text(
                text = "Next",
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun A(onNext: () -> Unit) {
    ScreenTemplate(backgroundColor = BackgroundA, onNext = onNext)
}

@Composable
fun B(onNext: () -> Unit) {
    ScreenTemplate(backgroundColor = BackgroundB, onNext = onNext)
}

@Composable
fun C(onNext: () -> Unit) {
    ScreenTemplate(backgroundColor = BackgroundC, onNext = onNext)
}


