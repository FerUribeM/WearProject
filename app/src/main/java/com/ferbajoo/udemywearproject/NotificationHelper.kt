package com.ferbajoo.udemywearproject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat


/**
 * Created by Fernando U on 3/18/20.
 */
class NotificationHelper(context: Context) {

    private val mContext: Context = context
    private var mNotificationManager: NotificationManager? = null
    private var mBuilder: NotificationCompat.Builder? = null

    /**
     * Create and push the notification
     */
    fun createNotification(title: String?, message: String?, hasAction: Boolean = false) {
        /**Creates an explicit intent for an Activity in your app */
        val resultIntent = Intent(mContext, MainActivity::class.java)
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val resultPendingIntent = PendingIntent.getActivity(
            mContext,
            0 /* Request code */, resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        mBuilder = NotificationCompat.Builder(mContext, "wear")
        mBuilder?.setSmallIcon(R.drawable.ic_notifications_paused_black_24dp)
        mBuilder?.setContentTitle(title)
            ?.setContentText(message)
            ?.setAutoCancel(false)
            ?.setContentIntent(resultPendingIntent)
        mNotificationManager =
            mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        if (hasAction) {
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("www.google.com/maps"))
            val pendingMapIntent = PendingIntent.getActivity(mContext, 0, mapIntent, 0)
            mBuilder?.addAction(
                R.drawable.ic_notifications_paused_black_24dp,
                "Launch Google Maps",
                pendingMapIntent
            )
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "NOTIFICATION_CHANNEL_NAME",
                importance
            )
            notificationChannel.enableVibration(true)
            assert(mNotificationManager != null)
            mBuilder?.setChannelId(NOTIFICATION_CHANNEL_ID)
            mNotificationManager!!.createNotificationChannel(notificationChannel)
        }
        assert(mNotificationManager != null)
        mNotificationManager!!.notify(0 /* Request Code */, mBuilder?.build())
    }

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "10001"
    }

}
