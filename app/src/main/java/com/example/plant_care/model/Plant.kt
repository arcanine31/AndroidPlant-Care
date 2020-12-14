package com.example.plant_care.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
class Plant (
    var id: Long ?= 0,
    var name: String ?= "",
    var need: String ?= "",
    var time: String ?= "",
    var menit: String ?= "",
        ) : Parcelable
//{
//    private var id = 0L
//    private var name = ""
//    private var need = ""
//    private var time = ""
//    private var menit = ""
//
//    constructor(id: Long, name: String, need: String, jam: String, menit: String){
//        this.id = id
//        this.name = name
//        this.need = need
//        this.time = jam
//        this.menit =menit
//    }
//
//    fun getId(): Long{
//        return id
//    }
//
//    fun setId(id: Long){
//        this.id = id
//    }
//    fun getName(): String{
//        return name
//    }
//
//    fun setName(name: String){
//        this.name = name
//    }
//
//    fun getNeed(): String{
//        return need
//    }
//
//    fun setNeed(need: String){
//        this.need = need
//    }
//
//    fun getTime(): String{
//        return time
//    }
//
//    fun setTime(time: String){
//        this.time = time
//    }
//
//    fun getMenit(): String{
//        return menit
//    }
//
//    fun setMenit(menit: String){
//        this.menit = menit
//    }
//
//    @Parcelize
//
//    data class Plant () : Parcelable
//}

