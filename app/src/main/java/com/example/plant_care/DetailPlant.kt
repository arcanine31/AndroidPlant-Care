package com.example.plant_care

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Toast
import com.example.plant_care.database.DbContract
import com.example.plant_care.database.ReaderDbHelper
import com.example.plant_care.model.Plant
import kotlinx.android.synthetic.main.activity_detail_plant.*

class DetailPlant : AppCompatActivity() {


    lateinit var dbHelper: ReaderDbHelper
    lateinit var db : SQLiteDatabase
    lateinit var plant: Plant


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_plant)

        dbHelper = ReaderDbHelper(this)
        db = dbHelper.readableDatabase

        initView()
        initListener()
    }

    fun initView(){

        plant = intent.getParcelableExtra("data")!!
        tv_display_name.text = plant.name
        tv_display_need.text = plant.need
        tv_display_time.text = plant.time
        tv_display_menit.text = plant.menit
    }

    fun initListener(){
        bt_edit.setOnClickListener{
            val i = Intent(this, EditPlant::class.java)
            i.putExtra("data",plant)
            startActivity(i)
            finish()
        }

        bt_delete.setOnClickListener{

            val selection = "${BaseColumns._ID} LIKE ?"
            val selectionArg = arrayOf(plant.id.toString())

            val deleteRow = db.delete(DbContract.DataEntry.TABLE_NAME, selection, selectionArg)
            if (deleteRow == -1){
                Toast.makeText(this, "Hapus data gagal", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Hapus data berhasil", Toast.LENGTH_SHORT).show()
                finish()
            }

        }
    }

}