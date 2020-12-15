package com.example.plant_care

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Spinner
import android.widget.Toast
import com.example.plant_care.database.DbContract
import com.example.plant_care.database.ReaderDbHelper
import com.example.plant_care.model.Plant
import kotlinx.android.synthetic.main.activity_edit_plant.*
import kotlinx.android.synthetic.main.activity_edit_plant.et_jam
import kotlinx.android.synthetic.main.activity_edit_plant.et_menit
import kotlinx.android.synthetic.main.activity_edit_plant.et_name
import kotlinx.android.synthetic.main.activity_edit_plant.et_need
import kotlinx.android.synthetic.main.fragment_add.*

class EditPlant : AppCompatActivity() {

    lateinit var dbHelper: ReaderDbHelper
    lateinit var db : SQLiteDatabase
    lateinit var plant : Plant
    var p: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_plant)

        dbHelper = ReaderDbHelper(this)
        db = dbHelper.writableDatabase

        initListener()
        initView()
    }

    fun initView(){
        plant = intent.getParcelableExtra("data")!!

        et_name.setText(plant.name)
        et_need.setText(plant.need)
        et_jam.setText(plant.time)
        et_menit.setText(plant.menit)

    }

    fun initListener(){
        bt_ubah.setOnClickListener{
            var nama = et_name.text.toString()
            var need = et_need.text.toString()
            var jam = et_jam.text.toString()
            var menit = et_menit.text.toString()

            if (nama.isNullOrEmpty()){
                et_name.error = "Silakan isi nama tanaman"
                et_name.requestFocus()
            }else if (need.isNullOrEmpty()){
                et_need.error = "Silakan isi kebutuhan tanaman"
                et_need.requestFocus()
            }else if (jam.isNullOrEmpty()){
                et_jam.error = "Silakan isi jam"
                et_jam.requestFocus()
            }else if (jam.toInt()>23 || jam.toInt()<0){
                et_jam.error = "Jam salah"
                et_jam.requestFocus()
            }else if (menit.toInt()>59 || menit.toInt()<0){
                et_menit.error = "menit salah"
                et_name.requestFocus()
            }else if (menit.isNullOrEmpty() ){
                et_menit.error = "Silakan isi menit"
                et_name.requestFocus()
            } else {

                val selection = "${BaseColumns._ID} LIKE ?"
                val selectionArg = arrayOf(plant.id.toString())

                val values = ContentValues().apply {
                    put(DbContract.DataEntry.COLUMN_NAMA_TANAMAN, nama)
                    put(DbContract.DataEntry.COLUMN_PERAWATAN, need)
                    put(DbContract.DataEntry.COLUMN_TIME_JAM, jam)
                    put(DbContract.DataEntry.COLUMN_TIME_MENIT, menit)
                }

                val editRow = db.update(DbContract.DataEntry.TABLE_NAME, values, selection, selectionArg)
                if (editRow == -1){
                    Toast.makeText(this, "Edit data gagal", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Edit data berhasil", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, MainActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i)
                    finish()
                }
            }
        }

        bt_hapus.setOnClickListener{
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