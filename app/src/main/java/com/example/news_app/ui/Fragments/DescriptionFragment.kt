package com.example.news_app.ui.Fragments

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.text.BoringLayout
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
import androidx.core.content.ContextCompat
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.news_app.R
import com.example.news_app.adapters.NewsAdapter
import com.example.news_app.models.Article
import com.example.news_app.ui.MainActivity
import com.example.news_app.ui.view_models.NewsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_description.*
import kotlinx.android.synthetic.main.fragment_description.view.*
import kotlinx.android.synthetic.main.fragment_fav.*


class DescriptionFragment : Fragment() {

    lateinit var viewModel: NewsViewModel
    val args: DescriptionFragmentArgs by navArgs()
    lateinit var txt_title: TextView
    lateinit var txt_description: TextView
    lateinit var txt_context: TextView
    lateinit var img_news: ImageView
    lateinit var newsAdapter: NewsAdapter
    var isFavorite: Boolean = false
    private val savedArticleIds = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        val articleId = article.url

        if (savedArticleIds.contains(articleId)) {
            already_saved_btn.visibility = View.VISIBLE
            saved_btn.visibility = View.GONE
        } else {
            already_saved_btn.visibility = View.GONE
            saved_btn.visibility = View.VISIBLE
        }

        saved_btn.setOnClickListener {
            if (savedArticleIds.contains(articleId)) {
                Toast.makeText(requireContext(), "Article already saved!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.saveArticle(article)
                Toast.makeText(requireContext(), "Article saved!", Toast.LENGTH_SHORT).show()
                savedArticleIds.add(articleId)
                already_saved_btn.visibility = View.VISIBLE
                saved_btn.visibility = View.GONE
            }
        }


//        saved_btn.setOnClickListener {
//            viewModel.saveArticle(article)
//            Toast.makeText(requireContext(), "Article saved!", Toast.LENGTH_SHORT).show()
//            already_saved_btn.visibility = View.VISIBLE
//            saved_btn.visibility = View.GONE
//        }
    }
}