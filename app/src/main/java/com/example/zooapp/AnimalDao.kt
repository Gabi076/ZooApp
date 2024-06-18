package com.example.zooapp
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnimalDao {
    @Query("SELECT * FROM animals")
    fun getAllAnimals(): LiveData<List<Animal>>

    @Query("SELECT * FROM animals WHERE name = :name LIMIT 1")
    suspend fun getAnimalByName(name: String): Animal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(animal: Animal): Long

    @Update
    suspend fun update(animal: Animal): Int

    @Delete
    suspend fun delete(animal: Animal): Int
}