package com.hanyeop.TodoneList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.TodoneList.R
import com.hanyeop.TodoneList.databinding.TodoItemBinding
import com.hanyeop.TodoneList.model.Memo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {

    private var memoList = emptyList<Memo>()

    class MyViewHolder(val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root)

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    // 뷰 홀더에 데이터를 바인딩
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = memoList[position]
        val currentContent = currentItem.content
        val currentYear = currentItem.year
        val currentMonth = currentItem.month
        val currentDay = currentItem.day

        val s_currentYear = currentYear.toString()
        var s_currentMonth = currentMonth.toString()
        var s_currentDay = currentDay.toString()

        if(currentMonth < 10) s_currentMonth = "0$currentMonth"
        if(currentDay < 10) s_currentDay = "0$currentDay"

        holder.binding.memoCheckBox.text = currentContent
        holder.binding.dateTextView.text = "$s_currentYear/$s_currentMonth/$s_currentDay"

    }

    // 뷰 홀더의 개수 리턴
    override fun getItemCount(): Int {
        return memoList.size
    }

    // 메모 리스트 갱신
    fun setData(memo : List<Memo>){
        memoList = memo
        notifyDataSetChanged()
    }
}