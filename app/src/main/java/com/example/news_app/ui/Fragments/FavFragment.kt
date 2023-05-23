package com.example.news_app.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.example.news_app.R
import com.example.news_app.databinding.FragmentMainBinding
import com.example.news_app.ui.MainActivity
import com.example.news_app.ui.view_models.NewsViewModel
import kotlinx.android.synthetic.main.fragment_fav.view.*


class FavFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fav, container, false)

        view.return_from_saved.setOnClickListener {
            findNavController().navigate(R.id.action_favFragment_to_mainFragment)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        val searchView = view.findViewById<SearchView>(R.id.search_view_saved)
        searchView.queryHint = "Поиск"
    }
}