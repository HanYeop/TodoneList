package com.hanyeop.TodoneList.UI.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hanyeop.TodoneList.R
import com.hanyeop.TodoneList.UI.dialog.MyCustomDialog
import com.hanyeop.TodoneList.adapter.TodoAdapter
import com.hanyeop.TodoneList.databinding.FragmentTodoListBinding
import com.hanyeop.TodoneList.model.Memo
import com.hanyeop.TodoneList.viewmodel.MemoViewModel

class TodoListFragment : Fragment() {

    private var binding : FragmentTodoListBinding? = null
    private val memoViewModel: MemoViewModel by viewModels() // 뷰모델 연결
    private val adapter : TodoAdapter by lazy { TodoAdapter() } // 어댑터 선언

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 상단 메뉴 추가
        setHasOptionsMenu(true)
        // 뷰바인딩
        binding = FragmentTodoListBinding.inflate(inflater,container,false)

        // 아이템을 가로로 하나씩 보여주고 어댑터 연결
        binding!!.todoRecyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        binding!!.todoRecyclerView.adapter = adapter

        // 리스트 관찰하여 변경시 어댑터에 전달해줌
        memoViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        // Fab 클릭시 다이얼로그 띄움
        binding!!.dialogButton.setOnClickListener {
            Toast.makeText(activity, "테스트", Toast.LENGTH_SHORT).show()
            onFabClicked()
        }

        return binding!!.root
    }

    // Fab 클릭시 사용되는 함수
    private fun onFabClicked(){
        val myCustomDialog = MyCustomDialog(activity!!)
        myCustomDialog.show()
    }

    // 서치바 추가
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu,menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
//        searchView?.isSubmitButtonEnabled = true
//        searchView?.setOnQueryTextListener(this)
    }

    // 프래그먼트는 뷰보다 오래 지속 . 프래그먼트의 onDestroyView() 메서드에서 결합 클래스 인스턴스 참조를 정리
    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}