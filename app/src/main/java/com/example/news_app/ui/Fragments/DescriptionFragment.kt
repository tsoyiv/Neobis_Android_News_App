package com.example.news_app.ui.Fragments

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.news_app.R
import com.example.news_app.models.Article
import com.example.news_app.ui.MainActivity
import com.example.news_app.ui.view_models.NewsViewModel
import kotlinx.android.synthetic.main.fragment_description.view.*


class DescriptionFragment : Fragment() {

    lateinit var viewModel: NewsViewModel
    val args: DescriptionFragmentArgs by navArgs()
    lateinit var txt_title: TextView
    lateinit var txt_description: TextView
    lateinit var txt_context: TextView
    lateinit var img_news: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_description, container, false)

        view.return_from_des.setOnClickListener {
            findNavController().navigate(R.id.action_descriptionFragment_to_mainFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        val article = args.article

        txt_title = view.findViewById(R.id.desc_title)
        txt_description = view.findViewById(R.id.desc_description)
        txt_context = view.findViewById(R.id.desc_context)
        img_news = view.findViewById(R.id.desc_image)

        txt_title.text = article.title
        txt_description.text = article.description
        txt_context.text = article.content
        img_news.load(article.urlToImage)
    }
}