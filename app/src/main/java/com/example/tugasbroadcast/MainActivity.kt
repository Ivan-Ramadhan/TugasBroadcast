package com.example.tugasbroadcast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    private var mReceiver = CustomReceiver()
    var intentFilter: IntentFilter? = null


    private val ACTION_CUSTOM_BROADCAST: String = BuildConfig.APPLICATION_ID.toString() + ".ACTION_CUSTOM_BROADCAST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intentFilter = IntentFilter(ACTION_CUSTOM_BROADCAST);

        intentFilter?.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter?.addAction(Intent.ACTION_POWER_DISCONNECTED)

        this.registerReceiver(mReceiver, intentFilter)

        LocalBroadcastManager.getInstance(this).registerReceiver(
            mReceiver, IntentFilter(
                ACTION_CUSTOM_BROADCAST
            )
        )

    }


    fun sendCustomBroadcast(view: View?){
        val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)

        LocalBroadcastManager.getInstance(this)
            .sendBroadcast(customBroadcastIntent)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(mReceiver, intentFilter)
    }

    override fun onDestroy() {
        unregisterReceiver(mReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver)
        super.onDestroy()
    }

}