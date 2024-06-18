package com.example.zooapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimalAdapter(private val onDeleteClick: (Animal) -> Unit) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    private var animals: List<Animal> = emptyList()

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameOfAnAnimal)
        val continentTextView: TextView = itemView.findViewById(R.id.continent)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.animal_item, parent, false)
        return AnimalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val currentAnimal = animals[position]
        holder.nameTextView.text = currentAnimal.name
        holder.continentTextView.text = currentAnimal.continent
        holder.deleteButton.setOnClickListener { onDeleteClick(currentAnimal) }
    }

    override fun getItemCount() = animals.size

    fun setAnimals(animals: List<Animal>) {
        this.animals = animals
        notifyDataSetChanged()
    }
}
