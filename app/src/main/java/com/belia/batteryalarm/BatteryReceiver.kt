package com.belia.batteryalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.runtime.MutableState

class BatteryReceiver(private val img: MutableState<Int>) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BATTERY_LOW)
            img.value = R.drawable.battery_low
        else if (intent.action == Intent.ACTION_BATTERY_OKAY)
            img.value = R.drawable.battery_full
    }


    companion object {
        fun register(context: Context, img: MutableState<Int>) {
            val intentFilter = IntentFilter().apply {
                addAction(Intent.ACTION_BATTERY_LOW)
                addAction(Intent.ACTION_BATTERY_OKAY)
            }
            val receiver = BatteryReceiver(img)
            context.registerReceiver(receiver, intentFilter)
        }
    }
}