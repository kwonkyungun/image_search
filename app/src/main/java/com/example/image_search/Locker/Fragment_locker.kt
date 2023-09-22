package com.example.image_search.Locker

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.image_search.MainActivity
import com.example.image_search.R
import com.example.image_search.Search.Search_Model
import com.example.image_search.databinding.FragmentLockerBinding


class Fragment_locker : Fragment() {
    private lateinit var Locker_Context: Context
    private var binding: FragmentLockerBinding? = null
    private lateinit var lockerAdapter : Locker_Adapter
    private var searchboxItems: List<Search_Model> = listOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Locker_Context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainActivity = activity as MainActivity
        searchboxItems = mainActivity.likedItems

        lockerAdapter = Locker_Adapter(Locker_Context).apply {
            items = searchboxItems.toMutableList()
        }

        binding = FragmentLockerBinding.inflate(inflater, container, false).apply {
            lockerRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            lockerRv.adapter = lockerAdapter
        }

        return binding?.root
    }


}