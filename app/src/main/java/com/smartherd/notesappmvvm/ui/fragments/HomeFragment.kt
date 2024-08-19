package com.smartherd.notesappmvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.smartherd.notesappmvvm.R
import com.smartherd.notesappmvvm.adapter.NotesAdapter
import com.smartherd.notesappmvvm.databinding.FragmentHomeBinding
import com.smartherd.notesappmvvm.model.Notes
import com.smartherd.notesappmvvm.viewmodel.NotesViewModel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel : NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        binding.btnAddNotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->

            val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
            oldMyNotes = notesList as ArrayList<Notes>
            binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
            adapter = NotesAdapter(requireContext(), notesList)
            binding.rcvAllNotes.adapter = adapter
        }


        binding.filterAll.setOnClickListener {

            viewModel.getNotes().observe(viewLifecycleOwner) {notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }

        binding.filterLow.setOnClickListener{
                viewModel.getLowNotes().observe(viewLifecycleOwner){notesList ->
                    oldMyNotes = notesList as ArrayList<Notes>
                    val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                    binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                    adapter = NotesAdapter(requireContext(), notesList)
                    binding.rcvAllNotes.adapter = adapter
                }
        }

        binding.filterMedium.setOnClickListener {
                viewModel.getMediumNotes().observe(viewLifecycleOwner){notesList ->
                    oldMyNotes = notesList as ArrayList<Notes>
                    val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                    binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                    adapter = NotesAdapter(requireContext(),notesList)
                    binding.rcvAllNotes.adapter = adapter
                }
        }

        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner){notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item = menu.findItem(R.id.app_bar_search)

        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes here..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                notesFilter(newText)
                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesFilter(newText: String?) {
        val newFilteredList = ArrayList<Notes>()

        for (i in oldMyNotes){
            if (i.title.contains(newText!!) || i.subtitle.contains(newText!!)){
                newFilteredList.add(i)
            }
        }

        adapter.filtering(newFilteredList)
    }
}