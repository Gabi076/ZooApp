package com.example.zooapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var animalViewModel: AnimalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameOfAnAnimal: EditText = findViewById(R.id.nameOfAnAnimal)
        val continent: EditText = findViewById(R.id.continent)
        val addButton: Button = findViewById(R.id.addButton)

        animalViewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)

        addButton.setOnClickListener {
            val name = nameOfAnAnimal.text.toString().trim()
            val continentName = continent.text.toString().trim()
            val continents = listOf("africa", "europe", "south america", "antarctica", "north america", "asia", "australia")
            if (name.isNotEmpty() && continentName.isNotEmpty()) {
                if (continents.contains(continentName.lowercase())) {
                    val animal = Animal(name = name, continent = continentName)
                    animalViewModel.insertOrUpdate(animal)
                    nameOfAnAnimal.text.clear()
                    continent.text.clear()
                }
                else {
                    Toast.makeText(this, "The continent does not exist", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.animalListFragment, AnimalListFragment())
                .commitNow()
        }
    }
}