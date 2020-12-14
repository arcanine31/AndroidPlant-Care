package com.example.plant_care.database

import android.provider.BaseColumns

object DbContract {

    object DataEntry: BaseColumns {
        const val TABLE_NAME = "plant"
        const val COLUMN_NAMA_TANAMAN = "nama"
        const val COLUMN_PERAWATAN = "need"
        const val COLUMN_TIME_JAM = "jam"
        const val COLUMN_TIME_MENIT = "menit"
    }
}