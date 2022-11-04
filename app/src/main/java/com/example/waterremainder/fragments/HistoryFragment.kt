package com.example.waterremainder.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waterremainder.MainActivity
import com.example.waterremainder.adapter.FilterDataAdapter
import com.example.waterremainder.adapter.WaterHistoryListAdapter
import com.example.waterremainder.databinding.FragmentHistoryBinding
import com.example.waterremainder.model.FilteredClass
import com.example.waterremainder.model.WaterData
import com.example.waterremainder.viewmodel.WaterViewModel
import com.google.gson.Gson
import java.util.ArrayList
import java.util.HashMap

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
    lateinit var filterAdapter: FilterDataAdapter
    var listFinal: ArrayList<FilteredClass> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        viewModel = (activity as MainActivity).vm
//        setUpRV()
        setUpFilterRV()


        viewModel.getAllWater.observe(requireActivity(), Observer{list ->
            list?.let {

                for ((k, v) in sortArrayByDate(list)) {
                    val filteredClass = FilteredClass()
                    filteredClass.date = k
                    filteredClass.listWaterData =v
                    listFinal.add(filteredClass)
                }

                Log.d("FINAL_LIST", "onCreateView: "+ Gson().toJson(listFinal))

                filterAdapter.differ.submitList(listFinal)

            }
        })

        /*viewModel.getAllWater.observe(requireActivity(), Observer { list ->
            Log.d("GET_WATER_LIST", "onCreateView: "+list)

            var abc = sortArrayByDate(list)
            Log.d("GET_WATER_LIST_ABC", "onCreateView: " + Gson().toJson(abc))
        })*/

        return binding.root
    }

    fun sortArrayByDate(list: List<WaterData>) : HashMap<String, MutableList<WaterData>> {
        var sortedList= HashMap<String, MutableList<WaterData>>() // create hashmap to store data
        var temp: MutableList<WaterData>? = ArrayList<WaterData>()
        for (item in list!!) {
            temp = sortedList?.get(item.valueOfTheDay) // get date and remove timing

            if (temp != null)     //if this is not null it mean this contain items
                temp.add(item)
            else {
                temp = ArrayList<WaterData>()  //if this is null it means this is new data or new data
                temp.add(item)
            }

            sortedList?.put(item.valueOfTheDay!!, temp)
        }

        return sortedList
    }


    /*fun setUpRV() {
        waterHistoryAdapter = WaterHistoryListAdapter()
        binding.recyclerView.apply {
            adapter = waterHistoryAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }*/

    fun setUpFilterRV() {
        filterAdapter = FilterDataAdapter()
        binding.recyclerView.apply {
            adapter = filterAdapter
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