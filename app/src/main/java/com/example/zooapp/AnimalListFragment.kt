package com.example.zooapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AnimalListFragment : Fragment() {

    private lateinit var animalViewModel: AnimalViewModel
    private lateinit var animalAdapter: AnimalAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_animal_list, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        animalAdapter = AnimalAdapter { animal -> animalViewModel.delete(animal) }
        recyclerView.adapter = animalAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        animalViewModel = ViewModelProvider(requireActivity()).get(AnimalViewModel::class.java)
        animalViewModel.allAnimals.observe(viewLifecycleOwner, { animals ->
            animals?.let { animalAdapter.setAnimals(it) }
        })

        return view
    }
}
