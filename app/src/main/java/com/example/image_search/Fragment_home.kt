package com.example.image_search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.image_search.databinding.FragmentHomeBinding

class Fragment_home : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container,false)
        return binding.root
    }

    private fun initView() = with(binding) {

        //검색 버튼
        binding.mainSearch.setOnClickListener{

        }

    }


}