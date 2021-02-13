package com.arjun1194.nativenews.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.arjun1194.nativenews.R
import com.arjun1194.nativenews.data.model.Article
import com.arjun1194.nativenews.data.model.DataResponse
import com.arjun1194.nativenews.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val topHeadlinesAdapter = TopHeadlinesAdapter()
    private val homeViewModel: HomeViewModel by viewModels()
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.articleList.adapter = topHeadlinesAdapter
        getTopHeadlines()
        showLoader()
        setupSwipeRefresh()


    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.apply {
            setOnRefreshListener {
                homeViewModel.getTopHeadlines()
            }
        }
    }

    private fun getTopHeadlines() {
        homeViewModel.getTopHeadlines()
        homeViewModel.response.observe(viewLifecycleOwner) {
            when (it) {
                is DataResponse.Success<List<Article>> -> showData(it.data)
                is DataResponse.Error -> showError(it.error)
            }

        }
    }

    private fun showLoader() {
        homeViewModel.showLoader.observe(viewLifecycleOwner) {
            if (it) {
                binding.articleList.visibility = View.GONE
                binding.loader.visibility = View.VISIBLE
            } else {
                binding.articleList.visibility = View.VISIBLE
                binding.loader.visibility = View.GONE
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(requireContext(), throwable.message, Toast.LENGTH_LONG).show()
    }

    private fun showData(articles: List<Article>) {
        Log.d(TAG, "showData: Total Results are  ${articles.size}")
        topHeadlinesAdapter.setItems(articles)
    }



    companion object {
        private const val TAG = "HomeFragment"
    }
}