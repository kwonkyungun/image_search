package com.example.image_search.Search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.image_search.Util.getDateTimestampWithFormat
import com.example.image_search.databinding.MainItemBinding

class SearchAdapter(private var searchContext: Context) : RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {

    var items = ArrayList<Search_Model>()

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

        Glide.with(searchContext)
            .load(currentItem.Url)
            .into(holder.image)

        holder.title.text = currentItem.Title
        holder.like.visibility = if (currentItem.Like) View.VISIBLE else View.INVISIBLE
        holder.datetime.text = getDateTimestampWithFormat(
            currentItem.dateTime,
            //초기 날짜가 "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00" 이러한 형식으로 나타나서 yyyy-MM-dd HH:mm:ss 이러한 형식으로 바꿔줬다.
            "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
            "yyyy-MM-dd HH:mm:ss"
        )
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        var image: ImageView = binding.itemImage
        var datetime: TextView = binding.itemDate
        var title: TextView = binding.itemTitle
        var like: ImageView = binding.likeImage
        var mainLayout: ConstraintLayout = binding.mainLayout

        init {
            image.setOnClickListener(this)
            like.visibility = View.GONE
            mainLayout.setOnClickListener(this)
        }


        override fun onClick(view: View) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
            val item = items[position]

            item.Like = !item.Like

//            if (item.Like) {
//                (mContext as MainActivity).addLikedItem(item)
//            } else {
//                (mContext as MainActivity).removeLikedItem(item)
//            }

            notifyItemChanged(position)
        }
    }
}
