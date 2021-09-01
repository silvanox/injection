package com.example.injection.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.injection.R
import com.example.injection.adapter.GithubUserAdapter
import com.example.injection.databinding.MainFragmentBinding
import com.example.injection.model.User
import com.example.injection.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private val adapter: GithubUserAdapter = GithubUserAdapter()

    private val observePage = Observer<Int> {
        viewModel.fetchUsers()
    }

    private val observeUsers = Observer<List<User>> { list ->

        binding.linearLayout.visibility = View.GONE
        adapter.refresh(list)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = MainFragmentBinding.bind(view)

        viewModel.repositories.observe(viewLifecycleOwner, observeUsers)
        viewModel.page.observe(viewLifecycleOwner, observePage)

        binding.repositoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.repositoriesRecyclerView.adapter = adapter

        setEventsForButtons()

        callForMoreItems()

    }

    private fun setEventsForButtons() {

        binding.repositoriesRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    callForMoreItems()
                }
            }

        })

    }

    fun callForMoreItems() {
        binding.linearLayout.visibility = View.VISIBLE
        viewModel.nextPage()
    }

}