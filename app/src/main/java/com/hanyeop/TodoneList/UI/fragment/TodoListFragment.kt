package com.hanyeop.TodoneList.UI.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import com.hanyeop.TodoneList.R
import com.hanyeop.TodoneList.databinding.FragmentTodoListBinding

class TodoListFragment : Fragment() {

    private var binding : FragmentTodoListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 상단 메뉴 추가
        setHasOptionsMenu(true)
        // 뷰바인딩
        binding = FragmentTodoListBinding.inflate(inflater,container,false)
        return binding!!.root
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