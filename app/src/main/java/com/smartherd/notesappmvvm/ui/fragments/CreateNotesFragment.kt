package com.smartherd.notesappmvvm.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.smartherd.notesappmvvm.R
import com.smartherd.notesappmvvm.databinding.FragmentCreateNotesBinding
import com.smartherd.notesappmvvm.model.Notes
import com.smartherd.notesappmvvm.viewmodel.NotesViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
    var priority : String = "1"
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNotesBinding.inflate(layoutInflater)

        binding.pRed.setImageResource(R.drawable.baseline_done_24)

        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
            val title = binding.edtTitle.text.toString()
            val subTitle = binding.edtSubtitles.text.toString()
            val notes = binding.edtNotes.text.toString()

            val currentDate = Date()
            val dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(currentDate)

            val data = Notes(null,
                title = title,
                subtitle = subTitle,
                notes = notes,
                date = formattedDate.toString(),
                priority
                )
            viewModel.addNotes(data)

            Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }
}