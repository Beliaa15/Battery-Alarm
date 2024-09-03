package com.belia.batteryalarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.belia.batteryalarm.ui.theme.BatteryAlarmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BatteryAlarmTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    BatteryAlarm()
                }
            }
        }
    }
}


@Composable
fun BatteryAlarm(modifier: Modifier = Modifier) {
    val batteryImage = remember { mutableIntStateOf(R.drawable.battery_full) }
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        LaunchedEffect(Unit) {
            BatteryReceiver.register(
                context = context,
                img = batteryImage
            )
        }

        Image(
            painter = painterResource(batteryImage.intValue),
            contentDescription = "Battery image",
            modifier = modifier.size(250.dp),
        )
    }
}
