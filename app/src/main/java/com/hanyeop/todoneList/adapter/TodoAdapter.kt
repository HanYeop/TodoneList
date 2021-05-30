package com.hanyeop.todoneList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hanyeop.todoneList.databinding.TodoItemBinding
import com.hanyeop.todoneList.model.Memo
import com.hanyeop.todoneList.ui.dialog.UpdateDialog
import com.hanyeop.todoneList.ui.dialog.UpdateDialogInterface
import com.hanyeop.todoneList.viewmodel.MemoViewModel

class TodoAdapter(private val memoViewModel: MemoViewModel) : RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {

    private var memoList = emptyList<Memo>()

    // 뷰 홀더에 데이터를 바인딩
    class MyViewHolder(private val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root),
            UpdateDialogInterface{
        lateinit var memo : Memo
        lateinit var memoViewModel: MemoViewModel

        fun bind(currentMemo : Memo, memoViewModel: MemoViewModel){
            binding.memo = currentMemo
            this.memoViewModel = memoViewModel

            // 체크 리스너 초기화 해줘 중복 오류 방지
            binding.memoCheckBox.setOnCheckedChangeListener(null)

            // 메모 체크 시 체크 데이터 업데이트
            binding.memoCheckBox.setOnCheckedChangeListener { _, check ->
                if (check) {
                    memo = Memo(currentMemo.id, true, currentMemo.content,
                            currentMemo.year, currentMemo.month, currentMemo.day)
                    this.memoViewModel.updateMemo(memo)
                }
                else {
                    memo = Memo(currentMemo.id, false, currentMemo.content,
                            currentMemo.year, currentMemo.month, currentMemo.day)
                    this.memoViewModel.updateMemo(memo)
                }
            }

            // 삭제 버튼 클릭 시 메모 삭제
            binding.deleteButton.setOnClickListener {
                memoViewModel.deleteMemo(currentMemo)
            }

            // 수정 버튼 클릭 시 다이얼로그 띄움
            binding.updateButton.setOnClickListener {
                memo = currentMemo
                val myCustomDialog = UpdateDialog(binding.updateButton.context,this)
                myCustomDialog.show()
            }
        }

        // 다이얼로그의 결과값으로 업데이트 해줌
        override fun onOkButtonClicked(content: String) {
            val updateMemo = Memo(memo.id,memo.check,content,memo.year,memo.month,memo.day)
            memoViewModel.updateMemo(updateMemo)
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