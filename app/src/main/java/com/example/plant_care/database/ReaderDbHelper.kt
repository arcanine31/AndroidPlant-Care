package com.example.plant_care.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class ReaderDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    private val SQL_CREATE_ENTRY = "CREATE TABLE ${DbContract.DataEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${DbContract.DataEntry.COLUMN_NAMA_TANAMAN} TEXT," +
            "${DbContract.DataEntry.COLUMN_PERAWATAN} TEXT," +
            "${DbContract.DataEntry.COLUMN_TIME_JAM} INTEGER," +
            "${DbContract.DataEntry.COLUMN_TIME_MENIT} INTEGER)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    companion object{
        const val DATABASE_NAME = "plant.db"
        const val DATABASE_VERSION = 1
    }


}