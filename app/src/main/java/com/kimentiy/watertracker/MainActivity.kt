package com.kimentiy.watertracker

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dropbox.core.DbxRequestConfig
import com.dropbox.core.v2.DbxClientV2
import com.kimentiy.watertracker.domain.AmountOfWater
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    private val config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build()
    private val client = DbxClientV2(config, BuildConfig.API_KEY)
    private var drankToday = AmountOfWater(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dailyAimTextView = findViewById<TextView>(R.id.text_daily_aim)

        findViewById<SeekBar>(R.id.seekbar_daily_aim).apply {
            setOnSeekBarChangeListener(SeekBarListener(onValueChanged = {
                dailyAimTextView.text = it.toString()
            }))
        }

        val drankTodayTextView = findViewById<TextView>(R.id.text_drank)

        drankTodayTextView.text = drankToday.valueInMl.toString()

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

    class SeekBarListener(private val onValueChanged: (Int) -> Unit) :
        SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (seekBar != null && fromUser) {
                val roundedProgress = (progress.toFloat() / 50).roundToInt() * 50

                seekBar.progress = roundedProgress

                onValueChanged(roundedProgress)
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit

        override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
    }
}
