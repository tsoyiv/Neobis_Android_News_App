package com.example.news_app.ui.Fragments

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.news_app.R
import com.example.news_app.models.Article
import com.example.news_app.ui.MainActivity
import com.example.news_app.ui.view_models.NewsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_description.*
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

//        txt_context = view.findViewById(R.id.desc_context)
//        txt_context.text = article.content
//        txt_context.maxLines = Integer.MAX_VALUE
//        txt_context.ellipsize = null
//        txt_context.isScrollContainer = true
//        txt_context.setHorizontallyScrolling(false)
//        txt_context.movementMethod = ScrollingMovementMethod()
//        val stopButton: FloatingActionButton = view.findViewById(R.id.saved_btn)
//        val startButton: FloatingActionButton = view.findViewById(R.id.delete_btn)

        saved_btn.setOnClickListener {
            viewModel.saveArticle(article)
            Toast.makeText(requireContext(), "Article saved!", Toast.LENGTH_SHORT).show()
        }
//        delete_btn.setOnClickListener {
//            //viewModel.deleteArticle(article)
//            Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
//            startButton.setVisibility( View.VISIBLE );
//            stopButton.setVisibility( View.GONE );
//        }
    }
}