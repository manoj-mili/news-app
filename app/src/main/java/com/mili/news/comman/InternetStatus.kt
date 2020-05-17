package com.mili.news.comman

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData

/**
 * Class which notifies any class consuming this class about internet connection status
 */
class InternetStatus(private val context: Context) :
    LiveData<Boolean>() {
    override fun onActive() {
        super.onActive()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(internetStatus, filter)
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(internetStatus)
    }

    private val internetStatus: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(
            context: Context,
            intent: Intent
        ) {
            if (ConnectivityManager.CONNECTIVITY_ACTION == intent.action) {
                val noConnectivity =
                    intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
                postValue(!noConnectivity)
            }
        }
    }

}