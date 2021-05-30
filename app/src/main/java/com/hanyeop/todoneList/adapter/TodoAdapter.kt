package com.hanyeop.todoneList.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.todoneList.databinding.TodoItemBinding
import com.hanyeop.todoneList.model.Memo
import com.hanyeop.todoneList.viewmodel.MemoViewModel

class TodoAdapter(private val memoViewModel: MemoViewModel) : RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {

    private var memoList = emptyList<Memo>()

    class MyViewHolder(val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root)

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    // 뷰 홀더에 데이터를 바인딩
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        // 현재 아이템 정보 저장
        val currentItem = memoList[position]
        val currentId = currentItem.id
        val currentCheck = currentItem.check
        val currentContent = currentItem.content
        val currentYear = currentItem.year
        val currentMonth = currentItem.month
        val currentDay = currentItem.day

        // text 에 보여주기 위하여 String 으로 변환
        val s_currentYear = currentYear.toString()
        var s_currentMonth = currentMonth.toString()
        var s_currentDay = currentDay.toString()

        if(currentMonth < 10) s_currentMonth = "0$currentMonth"
        if(currentDay < 10) s_currentDay = "0$currentDay"

        // 현재 메모 내용과 날짜를 보여줌
        holder.binding.memoCheckBox.text = currentContent
        holder.binding.dateTextView.text = "$s_currentYear/$s_currentMonth/$s_currentDay"

        // 리스너를 초기화 해줘서 오류 방지
        holder.binding.memoCheckBox.setOnCheckedChangeListener(null)
        // 그 메모의 체크 상태를 동기화
        holder.binding.memoCheckBox.isChecked = currentCheck

        // 체크시 그 메모의 체크 상태를 동기화
        holder.binding.memoCheckBox.setOnCheckedChangeListener { _, b ->
            if(b){
                Log.d("test5", "$currentId $currentContent 체크됨")
                val memo = Memo(currentId,true,currentContent,currentYear,currentMonth,currentDay)
                memoViewModel.updateMemo(memo)
            }
            else{
                Log.d("test5", "$currentId $currentContent 체크해제됨.")
                val memo = Memo(currentId,false,currentContent,currentYear,currentMonth,currentDay)
                memoViewModel.updateMemo(memo)
            }
        }
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