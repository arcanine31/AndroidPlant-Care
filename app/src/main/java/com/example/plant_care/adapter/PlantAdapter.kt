package com.example.plant_care.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.plant_care.EditPlant
import com.example.plant_care.R
import com.example.plant_care.model.Plant

class PlantAdapter (val context: Context): RecyclerView.Adapter<PlantAdapter.PlantViewHolder>(){

    private var plants : List<Plant> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: PlantAdapter.PlantViewHolder, position: Int) {
        holder.bindModel(plants[position])
    }

    override fun getItemCount(): Int {
        return plants.size
    }

    fun setPlant(data: List<Plant>){
        plants = data
    }

    inner class PlantViewHolder(item: View): RecyclerView.ViewHolder(item){

        val txtName: TextView = item.findViewById(R.id.tv_item_name)
        val txtNeed: TextView = item.findViewById(R.id.tv_item_need)
        val txtTime: TextView = item.findViewById(R.id.tv_item_time)
        val txtMenit: TextView = item.findViewById(R.id.tv_item_menit)
        val cardViewPlant : CardView = item.findViewById(R.id.cv_plant)

        fun bindModel(m: Plant){
            txtName.text = m.name
            txtNeed.text = m.need
            txtTime.text = m.time
            txtMenit.text = m.menit

            cardViewPlant.setOnClickListener{
                var i = Intent(context, EditPlant::class.java)
                i.putExtra("status", false)
                i.putExtra("data", m)
                context.startActivity(i)
            }

        }
    }
}