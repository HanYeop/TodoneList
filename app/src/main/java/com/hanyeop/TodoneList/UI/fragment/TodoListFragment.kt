package com.hanyeop.TodoneList.UI.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hanyeop.TodoneList.R
import com.hanyeop.TodoneList.databinding.FragmentTodoListBinding
import com.hanyeop.TodoneList.model.Memo
import com.hanyeop.TodoneList.viewmodel.MemoViewModel

class TodoListFragment : Fragment() {

    private var binding : FragmentTodoListBinding? = null
    private val memoViewModel: MemoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 상단 메뉴 추가
        setHasOptionsMenu(true)
        // 뷰바인딩
        binding = FragmentTodoListBinding.inflate(inflater,container,false)

        binding!!.addButton.setOnClickListener {
            Toast.makeText(activity, "테스트", Toast.LENGTH_SHORT).show()
            onFabClicked()
        }

        return binding!!.root

//        memoViewModel.readAllData.observe(viewLifecycleOwner, Observer {
//            adapter.setData(it)
//        })
    }

    // Fab 클릭시 다이얼로그 띄움
    fun onFabClicked(){
        val memo = Memo(false,"테스트")
        memoViewModel.addUser(memo)
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