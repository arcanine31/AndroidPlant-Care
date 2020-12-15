package com.example.plant_care

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.plant_care.database.DbContract
import com.example.plant_care.database.ReaderDbHelper
import kotlinx.android.synthetic.main.fragment_add.*
import java.sql.RowId


class AddFragment : Fragment() {

    lateinit var dbHelper : ReaderDbHelper
    lateinit var db : SQLiteDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = ReaderDbHelper(activity!!)
        db = dbHelper.writableDatabase

        initAddData()
    }

    private fun initAddData() {
        bt_tambah.setOnClickListener{
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
                val values = ContentValues().apply {
                    put(DbContract.DataEntry.COLUMN_NAMA_TANAMAN, nama)
                    put(DbContract.DataEntry.COLUMN_PERAWATAN, need)
                    put(DbContract.DataEntry.COLUMN_TIME_JAM, jam)
                    put(DbContract.DataEntry.COLUMN_TIME_MENIT, menit)
                }

                val newRowid = db.insert(DbContract.DataEntry.TABLE_NAME,null, values)
                if (newRowid == -1L){
                    Toast.makeText(activity, "Tambah data gagal", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(activity, "Tambah data berhasil", Toast.LENGTH_SHORT).show()

                }
            }


        }

    }

}