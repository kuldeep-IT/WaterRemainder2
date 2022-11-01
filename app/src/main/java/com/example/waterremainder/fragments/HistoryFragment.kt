package com.example.waterremainder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waterremainder.MainActivity
import com.example.waterremainder.adapter.WaterHistoryListAdapter
import com.example.waterremainder.databinding.FragmentHistoryBinding
import com.example.waterremainder.viewmodel.WaterViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {


    lateinit var binding: FragmentHistoryBinding
    lateinit var viewModel: WaterViewModel
    lateinit var waterHistoryAdapter: WaterHistoryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        viewModel = (activity as MainActivity).vm
        setUpRV()
        viewModel.getAllWater.observe(requireActivity(), Observer{list ->
            list?.let {
                waterHistoryAdapter.differ.submitList(it)

            }
        })

        return binding.root
    }

    fun setUpRV() {
        waterHistoryAdapter = WaterHistoryListAdapter()
        binding.recyclerView.apply {
            adapter = waterHistoryAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HistoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            HistoryFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}