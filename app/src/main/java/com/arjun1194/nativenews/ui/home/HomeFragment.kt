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
        getTopHeadlines()

    }

    private fun getTopHeadlines(){
        homeViewModel.getTopHeadlines()
        homeViewModel.response.observe(viewLifecycleOwner){
            when(it){
                is NewsResponse.Success -> showData(it.topHeadlinesResponse)
                is NewsResponse.Error -> showError(it.message)
            }

        }
    }

    private fun showError(message:String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }

    private fun showData(items:TopHeadlinesResponse){
        Log.d(TAG, "showData: $items")
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}