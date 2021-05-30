package com.hanyeop.todoneList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.todoneList.databinding.TodoItemBinding
import com.hanyeop.todoneList.model.Memo
import com.hanyeop.todoneList.viewmodel.MemoViewModel

class TodoAdapter(private val memoViewModel: MemoViewModel) : RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {

    private var memoList = emptyList<Memo>()

    // 뷰 홀더에 데이터를 바인딩
    class MyViewHolder(private val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(currentMemo : Memo, memoViewModel: MemoViewModel){
            binding.memo = currentMemo

            // 체크 리스너 초기화 해줘 중복 오류 방지
            binding.memoCheckBox.setOnCheckedChangeListener(null)

            // 메모 체크 시 체크 데이터 업데이트
            binding.memoCheckBox.setOnCheckedChangeListener { _, check ->
                if (check) {
                    val memo = Memo(currentMemo.id, true, currentMemo.content,
                            currentMemo.year, currentMemo.month, currentMemo.day)
                    memoViewModel.updateMemo(memo)
                }
                else {
                    val memo = Memo(currentMemo.id, false, currentMemo.content,
                            currentMemo.year, currentMemo.month, currentMemo.day)
                    memoViewModel.updateMemo(memo)
                }
            }

            // 삭제 버튼 클릭 시 메모 삭제
            binding.deleteButton.setOnClickListener {
                memoViewModel.deleteMemo(currentMemo)
            }
        }
    }

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    // 바인딩 함수로 넘겨줌
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(memoList[position],memoViewModel)
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

    // 아이템에 아이디를 설정해줌 (깜빡이는 현상방지)
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


}