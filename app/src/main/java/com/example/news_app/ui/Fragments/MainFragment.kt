package com.example.news_app.ui.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_app.R
import com.example.news_app.adapters.NewsAdapter
import com.example.news_app.databinding.FragmentMainBinding
import com.example.news_app.ui.MainActivity
import com.example.news_app.ui.view_models.NewsViewModel
import com.example.news_app.util.Resource
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    val TAG = "BreakingNewsFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        view.fav_button_main.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favFragment)
        }
        view.refresh_button_main.setOnClickListener {
            showProgressBar()
            viewModel.updatePage()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
             val bundle = Bundle().apply {
                 putSerializable("article", it)
             }
            findNavController().navigate(R.id.action_mainFragment_to_descriptionFragment, bundle)
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressbar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressbar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                else -> {}
            }
        })
        val searchView = view.findViewById<SearchView>(R.id.search_view_main)
        searchView.queryHint = "Поиск"
    }

    private fun hideProgressbar() {
        progressBar_main.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar_main.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        recycler_view_main.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}