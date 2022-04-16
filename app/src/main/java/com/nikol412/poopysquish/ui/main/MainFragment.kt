package com.nikol412.poopysquish.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import com.nikol412.poopysquish.databinding.MainFragmentBinding
import com.nikol412.poopysquish.ui.main.adapter.KanbanTableAdapter
import com.nikol412.poopysquish.utils.viewBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private val binding by viewBinding(MainFragmentBinding::inflate)
    private val boardAdapter by lazy {
        KanbanTableAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpAdapter()
        subscribeToViewModel()
        return binding.root
    }

    private fun setUpAdapter() {
        binding.boardRecyclerView.adapter = boardAdapter
        binding.buttonAddCard.setOnClickListener { /* todo implement */ }
        LinearSnapHelper().attachToRecyclerView(binding.boardRecyclerView)
    }

    private fun subscribeToViewModel() = with(viewModel) {
        boardColumnsList.observe(viewLifecycleOwner) {
            boardAdapter.setNewItems(it)
        }
    }
}