package com.example.zooapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimalViewModel(application: Application) : AndroidViewModel(application) {
    private val animalDao: AnimalDao = AnimalDatabase.getDatabase(application).animalDao()
    val allAnimals: LiveData<List<Animal>> = animalDao.getAllAnimals()

    fun insertOrUpdate(animal: Animal) = viewModelScope.launch(Dispatchers.IO) {
        val existingAnimal = animalDao.getAnimalByName(animal.name)
        if (existingAnimal != null) {
            val updatedAnimal = existingAnimal.copy(continent = animal.continent)
            animalDao.update(updatedAnimal)
        } else {
            animalDao.insert(animal)
        }
    }

    fun delete(animal: Animal) = viewModelScope.launch(Dispatchers.IO) {
        animalDao.delete(animal)
    }
}
