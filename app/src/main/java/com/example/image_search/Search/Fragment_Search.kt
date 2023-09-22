package com.example.image_search.Search

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.adapters.SearchViewBindingAdapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.image_search.ItemModel
import com.example.image_search.NetWorkClient.apiService
import com.example.image_search.Util
import com.example.image_search.databinding.FragmentSearchBinding
import com.jblee.imagesearch.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Fragment_Search : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var gridmanager: StaggeredGridLayoutManager
    private lateinit var adapter: SearchAdapter
    private lateinit var searchContext: Context

    private var dataList : ArrayList<Search_Model> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchContext = context
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container,false)

        setView()//뷰 설정
        setListnears()// 리스너 설정
        return binding.root

    }

    //초기 View 설정
    private fun setView() {
        //리사이클러뷰 그리드로 설정
        gridmanager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.rvItem.layoutManager = gridmanager

        adapter = SearchAdapter(searchContext)
        binding.rvItem.adapter = adapter
        //추가 삭제 이벤트 실행시 깜박거림 버그 때문에 아래 코드 추가
        binding.rvItem.itemAnimator = null

        val lastSearch = Util.getLastSearch(requireContext())
        binding.mainSearchBox.setText(lastSearch)

    }
    private fun setListnears() {

        //검색 버튼
        binding.mainSearch.setOnClickListener {
            val search = binding.mainSearchBox.text.toString()
            if (search.isNotEmpty()){
                Util.saveLastSearch(requireContext(), search)
                adapter.clearItem()
                ImageResults(search)
            }else {
                Toast.makeText(searchContext,"검색어를 입력해주세요",Toast.LENGTH_SHORT).show()
            }


        }
    }


    private fun ImageResults(query: String) {
        apiService.image_search(Constants.HEADER, query, "recency", 1, 80)
            ?.enqueue(object : Callback<ItemModel?> {
                override fun onResponse(call: Call<ItemModel?>, response: Response<ItemModel?>) {
                    response.body()?.meta?.let { meta ->
                        if (meta.totalCount > 0) {
                            response.body()!!.documents.forEach { document ->
                                val title = document.displaySitename
                                val datetime = document.datetime
                                val url = document.thumbnailUrl
                                dataList.add(Search_Model(title, datetime, url))
                            }
                        }
                    }
                    adapter.items = dataList
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<ItemModel?>, t: Throwable) {
                }
            })
    }


}