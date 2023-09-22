package com.example.image_search.Locker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.image_search.ItemModel
import com.example.image_search.Search.Search_Model
import com.example.image_search.Util
import com.example.image_search.Util.getDateTimestampWithFormat
import com.example.image_search.databinding.MainItemBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

class Locker_Adapter (var Locker_Context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = mutableListOf<Search_Model>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        Glide.with(Locker_Context)
            .load(items[position].Url)
            .into((holder as ItemViewHolder).image)

        holder.title.text = items[position].Title
        holder.like.visibility = View.GONE
        holder.datetime.text =
            convertTimeStampToDate(
                items[position].dateTime,
                "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
                "yyyy-MM-dd HH:mm:ss"
            )

    }

    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * RecyclerView의 각 항목을 표현하는 ViewHolder 클래스입니다.
     */
    inner class ItemViewHolder(binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root) {

        var image: ImageView = binding.itemImage
        var datetime: TextView = binding.itemDate
        var title: TextView = binding.itemTitle
        var like: ImageView = binding.likeImage
        var mainLayout: ConstraintLayout = binding.mainLayout

        init {
            // 북마크 페이지에서는 '좋아요' 아이콘을 숨긴다.
            like.visibility = View.GONE

            // 아이템 클릭 리스너 설정
            mainLayout.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    items.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }
    fun convertTimeStampToDate(timestamp: String?, fromFormatformat: String?, toFormatformat: String?) :String {
        var date: Date? = null
        var res = ""
        try {
            val format = SimpleDateFormat(fromFormatformat)
            date = format.parse(timestamp)
        } catch (e: ParseException){
            e.printStackTrace()
        }
        val sdf = SimpleDateFormat(toFormatformat)
        res = sdf.format(date)
        return res
    }

}
