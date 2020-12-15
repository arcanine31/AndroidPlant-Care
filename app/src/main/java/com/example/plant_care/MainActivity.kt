package com.example.plant_care

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add.*

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "channel_example"
    private val notificationID = 101


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        bottom_navigation.setOnNavigationItemSelectedListener(onBottomNavListener)



        var frHome = supportFragmentManager.beginTransaction()
        frHome.add(R.id.frag_main, HomeFragment())
        frHome.commit()

    }

    private val onBottomNavListener = BottomNavigationView.OnNavigationItemSelectedListener { i ->
        var selectedFr: Fragment = HomeFragment()

        when(i.itemId){
            R.id.mn_list -> {
                selectedFr = HomeFragment()
            }
            R.id.mn_add -> {
                selectedFr = AddFragment()
            }
            R.id.mn_about -> {
                selectedFr = AboutFragment()
            }
        }

        var fr = supportFragmentManager.beginTransaction()
        fr.replace(R.id.frag_main, selectedFr)
        fr.commit()

        true
    }


    fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "NotificationTitileExample"
            val textDesc = "NotificationDescExample"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = textDesc
            }

            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

    fun sendNotification(){
        val notifBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logoplant)
                .setContentTitle("NotifContentTitle")
                .setContentText("NotifContentText")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationID, notifBuilder.build())
        }
    }
}