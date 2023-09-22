package com.example.image_search.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.image_search.MainActivity
import com.example.image_search.Search.Search_Model
import com.example.image_search.databinding.MainItemBinding

class RecycleAdapter(private val mContext: Context):RecyclerView.Adapter<RecycleAdapter.ItemViewHolder>() {

    var items = ArrayList<Search_Model>()


    //아이템 목록을 초기화하는 메서드입니다.
    fun clearItem() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]

        Glide.with(mContext)
            .load(currentItem.Url)
            .into(holder.iv_image)

        holder.iv_like.visibility = if (currentItem.Like) View.VISIBLE else View.INVISIBLE
        holder.tv_title.text = currentItem.Title
        holder.tv_datetime.text = getDateFromTimestampWithFormat(
            currentItem.dateTime,
            //초기 날짜가 "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00" 이러한 형식으로 나타나서 yyyy-MM-dd HH:mm:ss 이러한 형식으로 바꿔줬다.
            "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
            "yyyy-MM-dd HH:mm:ss"
        )
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        var iv_image: ImageView = binding.itemImage
        var iv_like: ImageView = binding.likeImage
        var tv_title: TextView = binding.itemTitle
        var tv_datetime: TextView = binding.itemDate
        var mainLayout: ConstraintLayout = binding.mainLayout

        init {
            iv_like.visibility = View.GONE
            iv_image.setOnClickListener(this)
            mainLayout.setOnClickListener(this)
        }


        //각 항목 클릭 시 발생하는 이벤트를 처리하는 메서드입니다.
        override fun onClick(view: View) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
            val item = items[position]

            item.Like = !item.Like

            if (item.Like) {
                (mContext as MainActivity).addLikedItem(item)
            } else {
                (mContext as MainActivity).removeLikedItem(item)
            }

            notifyItemChanged(position)
        }
    }
}
