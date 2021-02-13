package com.arjun1194.nativenews.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.arjun1194.nativenews.data.model.Article
import com.arjun1194.nativenews.data.model.NewsResponse
import com.arjun1194.nativenews.data.model.TopHeadlinesResponse
import com.arjun1194.nativenews.databinding.FragmentHomeBinding
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


    }

    private fun getTopHeadlines(){
        homeViewModel.getTopHeadlines().observe(viewLifecycleOwner){
            when(it){
                is NewsResponse.Success -> showData(it.topHeadlinesResponse)
                is NewsResponse.Error -> showError(it.message)
            }

        }
    }

    private fun showLoader(){
        homeViewModel.showLoader.observe(viewLifecycleOwner){
             if(it){
                 binding.articleList.visibility = View.GONE
                 binding.loader.visibility = View.VISIBLE
             }  else {
                 binding.articleList.visibility = View.VISIBLE
                 binding.loader.visibility = View.GONE
             }
        }
    }

    private fun showError(message:String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }

    private fun showData(items:TopHeadlinesResponse){
        Log.d(TAG, "showData: Total Results are  ${items.totalResults}")
        topHeadlinesAdapter.setItems(items.articles)
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}