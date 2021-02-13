package com.arjun1194.nativenews.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arjun1194.nativenews.R
import com.arjun1194.nativenews.data.model.DataResponse
import com.arjun1194.nativenews.data.model.Source
import com.arjun1194.nativenews.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModels()
    lateinit var binding: FragmentDashboardBinding
    private val sourcesAdapter = SourcesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = sourcesAdapter
        getSources()


    }

    private fun getSources() {
        dashboardViewModel.getSource()
        dashboardViewModel.sources.observe(viewLifecycleOwner) {
            when(it){
                is DataResponse.Error -> showError(it.error)
                is DataResponse.Success -> showData(it.data)
            }
        }
    }

    private fun showData(data: List<Source>) {
        sourcesAdapter.setItems(data)
    }

    private fun showError(error: Throwable) {
        Toast.makeText(requireContext(),error.message,Toast.LENGTH_LONG).show()
    }
}