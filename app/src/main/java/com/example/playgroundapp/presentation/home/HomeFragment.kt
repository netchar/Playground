package com.example.playgroundapp.presentation.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.playgroundapp.R
import com.example.playgroundapp.data.DataMapper
import com.example.playgroundapp.data.repository.CharacterRepositoryImpl
import com.example.playgroundapp.domain.interactors.AuthorInteractorImpl
import com.example.playgroundapp.App
import com.example.playgroundapp.data.remote.source.CharacterRemoteDataSourceImpl

class HomeFragment : Fragment(R.layout.fragment_first) {

    private lateinit var refreshLayout: SwipeRefreshLayout

    private val viewModel by lazy {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val api = (requireContext().applicationContext as App).authorApiService
                val mapper = DataMapper()
                val dataSource = CharacterRemoteDataSourceImpl(api)
                val repository = CharacterRepositoryImpl(dataSource, mapper)
                val interactor = AuthorInteractorImpl(repository)
                return HomeViewModel(interactor) as T
            }
        }
        ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }

    private val homeAdapter = HomeAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
    }

    private fun setupViews(view: View) {
        val recycler = view.findViewById<RecyclerView>(R.id.home_recycler)
        recycler.setHasFixedSize(true)
        refreshLayout= view.findViewById<SwipeRefreshLayout>(R.id.home_layout_refresh)
        recycler.adapter = homeAdapter

        refreshLayout.setOnRefreshListener { viewModel.refresh() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe()
    }

    private fun observe() {
        viewModel.items.observe(viewLifecycleOwner, { items ->
            homeAdapter.submitItems(items)
        })
        viewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            refreshLayout.isRefreshing = isLoading
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        })
    }
}

