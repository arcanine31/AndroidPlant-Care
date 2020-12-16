package com.example.plant_care

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.example.plant_care.database.DbContract
import com.example.plant_care.database.ReaderDbHelper
import com.example.plant_care.model.Plant
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add.*
import java.util.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "channel_example"
    private val notificationID = 101

    lateinit var dbHelper: ReaderDbHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()
        createNotifPlantCare()

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

//    NOTIF TESTER

//    fun sendNotification(PlantName: String, PlantNeed: String){
//
//        val notifBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.drawable.logoplant)
//                .setContentTitle(PlantName)
//                .setContentText(PlantNeed)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//        with(NotificationManagerCompat.from(this)){
//            notify(notificationID, notifBuilder.build())
//        }
//    }





    fun createNotifPlantCare(){
//   Declaring Calender and time
        val dateTime = Calendar.getInstance(Locale.getDefault())
//        dateTime.timeInMillis = System.currentTimeMillis()

//        getting item from database
        dbHelper = ReaderDbHelper(this)
        val db = dbHelper.readableDatabase
        val projection = arrayOf(BaseColumns._ID,
                DbContract.DataEntry.COLUMN_NAMA_TANAMAN,
                DbContract.DataEntry.COLUMN_PERAWATAN,
                DbContract.DataEntry.COLUMN_TIME_JAM,
                DbContract.DataEntry.COLUMN_TIME_MENIT)

        val cursor = db.query(
                DbContract.DataEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
        )


//Creating scheduled notif using existing data

        val i = Intent(this, ReminderBroadcast::class.java)
        val pi = PendingIntent.getBroadcast(this@MainActivity, 0, i, 0)

        val alarmManager: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val addPlant = mutableListOf<Plant>()
        with(cursor){
            while (moveToNext()){
                val jam = getString(getColumnIndexOrThrow(com.example.plant_care.database.DbContract.DataEntry.COLUMN_TIME_JAM))
                val menit = getString(getColumnIndexOrThrow(com.example.plant_care.database.DbContract.DataEntry.COLUMN_TIME_MENIT))
//                declaring current time
                val currentTime = System.currentTimeMillis()

                dateTime.set(Calendar.HOUR_OF_DAY, jam.toString().toInt())
                dateTime.set(Calendar.MINUTE, menit.toString().toInt())
                val counter = (((dateTime.get(Calendar.HOUR_OF_DAY) * 3600) + (dateTime.get(Calendar.MINUTE) * 60)) * 1000).toLong()
                val remainTime =  counter - System.currentTimeMillis()

                Log.i("time", remainTime.toString())
                Log.i("time", System.currentTimeMillis().toString())
                Log.i("timecounter", counter.toString())
                Log.i("timecounter", dateTime.get(Calendar.HOUR_OF_DAY).toString() + " Dan " + dateTime.get(Calendar.MINUTE).toString())


                if (remainTime<0){
                    alarmManager.set(AlarmManager.RTC_WAKEUP,remainTime, pi);
                }else{
                    continue
                }

            }
        }
    }

}
