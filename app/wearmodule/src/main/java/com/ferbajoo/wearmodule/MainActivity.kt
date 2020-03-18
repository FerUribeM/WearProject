package com.ferbajoo.wearmodule

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import android.widget.Toast

class MainActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun showNofications(v: View) {
        Toast.makeText(this, "Se dio click", Toast.LENGTH_SHORT).show()
        buildWearableOnlyNotifications("Titulo de wear", "Mensaje de Wear")
        finish()
    }

    private fun buildWearableOnlyNotifications(title: String, content: String) {
        val notification = Notification.Builder(this)
            .setSmallIcon(R.drawable.close_button).setContentTitle(title)
            .setContentText(content)

        (getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager)?.notify(
            1,
            notification.build()
        )
    }
}
