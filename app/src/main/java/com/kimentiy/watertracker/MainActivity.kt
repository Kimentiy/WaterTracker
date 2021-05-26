package com.kimentiy.watertracker

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dropbox.core.DbxRequestConfig
import com.dropbox.core.v2.DbxClientV2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build()
    private val client = DbxClientV2(config, "BuildConfig.API_KEY")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportFragmentManager.findFragmentById(android.R.id.content) == null) {
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, MainFragment())
                .commit()
        }

//        val dailyAimTextView = findViewById<TextView>(R.id.text_daily_aim)
//
//        findViewById<SeekBar>(R.id.seekbar_daily_aim).apply {
//            setOnSeekBarChangeListener(SeekBarListener(onValueChanged = {
//                dailyAimTextView.text = it.toString()
//            }))
//        }
//
//        val drankTodayTextView = findViewById<TextView>(R.id.text_drank)
//
//        drankTodayTextView.text = drankToday.valueInMl.toString()

        // showAccountInfo()
    }

    private fun showAccountInfo() {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                val account = client.users().currentAccount

                withContext(Dispatchers.Main) {
                    findViewById<TextView>(R.id.text).text = account.name.displayName
                }
            }
        }
    }

    private fun uploadFile() {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                assets.open("simplefile").use { inputStream ->
                    val metadata =
                        client.files().uploadBuilder("/test.txt").uploadAndFinish(inputStream)

                    withContext(Dispatchers.Main) {
                        findViewById<TextView>(R.id.text).text =
                            "File uploaded, ${metadata.pathDisplay}"
                    }
                }
            }
        }
    }
}
