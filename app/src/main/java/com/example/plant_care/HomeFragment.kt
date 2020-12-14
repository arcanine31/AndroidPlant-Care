package com.example.plant_care

import android.os.Bundle
import android.provider.BaseColumns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plant_care.adapter.PlantAdapter
import com.example.plant_care.database.DbContract
import com.example.plant_care.database.ReaderDbHelper
import com.example.plant_care.model.Plant
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    lateinit var plantAdapter: PlantAdapter
    val lm = LinearLayoutManager(activity)

    lateinit var dbHelper: ReaderDbHelper


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbHelper = ReaderDbHelper(activity!!)
        initView()
    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    fun initView(){


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

        val addPlant = mutableListOf<Plant>()
        with(cursor){
            while (moveToNext()){
                val id = getLong(getColumnIndex(BaseColumns._ID))
                val name = getString(getColumnIndexOrThrow(DbContract.DataEntry.COLUMN_NAMA_TANAMAN))
                val need = getString(getColumnIndexOrThrow(DbContract.DataEntry.COLUMN_PERAWATAN))
                val jam = getString(getColumnIndexOrThrow(DbContract.DataEntry.COLUMN_TIME_JAM))
                val menit = getString(getColumnIndexOrThrow(DbContract.DataEntry.COLUMN_TIME_MENIT))
                addPlant.add(Plant(id, name, need, jam, menit))
            }
        }

//        addPlantList.add(Plant("NANAN", "Nananana", "19999"))
//        addPlantList.add(Plant("NANAN", "Nananana", "19999"))
//        addPlantList.add(Plant("NANAN", "Nananana", "19999"))
//        addPlantList.add(Plant("NANAN", "Nananana", "19999"))
//        addPlantList.add(Plant("NANAN", "Nananana", "19999"))
        rcview_item.layoutManager = lm
        plantAdapter = PlantAdapter(activity!!)
        rcview_item.adapter = plantAdapter
        plantAdapter.setPlant(addPlant)
    }
}