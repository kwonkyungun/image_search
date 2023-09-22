package com.example.image_search.Search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.adapters.SearchViewBindingAdapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.image_search.Adapter.RecycleAdapter

import com.example.image_search.databinding.FragmentHomeBinding

class Fragment_Search : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var gridmanager: StaggeredGridLayoutManager
    private lateinit var adapter: SearchViewBindingAdapter
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container,false)

        setView()//뷰 설정
        setListnears()// 리스너 설정
        return binding.root

    }

    //초기 View 설정
    private fun setView() {
        //리사이클러뷰 그리드로 설정
        gridmanager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.rvItem.layoutManager = gridmanager

        adapter = RecycleAdapter(mContext)
        binding.rvItem.adapter = adapter
        //추가 삭제 이벤트 실행시 깜박거림 버그 때문에 아래 코드 추가
        binding.rvItem.itemAnimator = null

        val lastSearch = Utils.get


    }
    private fun setListnears() {
        //검색 버튼
        binding.mainSearch.setOnClickListener {
            val search = binding.mainSearchBox.text.toString()
            if (search.isNotEmpty()){
                Utils.saveLastSearch(requireContext(),search)
                adapter.clearItem()
                fetachImageResults(search)
            }else {
                Toast.makeText(mContext,"검색어를 입력해주세요",Toast.LENGTH_SHORT).show()
            }


        }
    }


}