package com.bonsaisoftware.dailybook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.bonsaisoftware.dailybook.navigation.Navigation
import com.bonsaisoftware.dailybook.ui.theme.DailyBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailyBookTheme {
                Navigation()
            }
        }
    }
}
