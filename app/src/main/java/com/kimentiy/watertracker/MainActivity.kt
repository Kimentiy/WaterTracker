package com.kimentiy.watertracker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dropbox.core.DbxRequestConfig
import com.dropbox.core.v2.DbxClientV2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            showAccountInfo()
        }
    }

    private fun showAccountInfo() {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                val config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build()
                val client = DbxClientV2(config, BuildConfig.API_KEY)

                val account = client.users().currentAccount

                withContext(Dispatchers.Main) {
                    findViewById<TextView>(R.id.text).text = account.name.displayName
                }
            }
        }
    }
}
