package com.hanyeop.todoneList.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hanyeop.todoneList.adapter.TodoAdapter
import com.hanyeop.todoneList.databinding.FragmentDoneListBinding
import com.hanyeop.todoneList.viewmodel.MemoViewModel

class DoneListFragment : Fragment() {

    private var binding : FragmentDoneListBinding? = null
    private val memoViewModel: MemoViewModel by viewModels() // 뷰모델 연결
    private val adapter : TodoAdapter by lazy { TodoAdapter(memoViewModel) } // 어댑터 선언

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // 뷰바인딩
        binding = FragmentDoneListBinding.inflate(inflater,container,false)

        // 아이템에 아이디를 설정해줌 (깜빡이는 현상방지)
        adapter.setHasStableIds(true)

        // 아이템을 가로로 하나씩 보여주고 어댑터 연결
        binding!!.doneRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        binding!!.doneRecyclerView.adapter = adapter

        // 리스트 관찰하여 변경시 어댑터에 전달해줌
        memoViewModel.readDoneData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        return binding!!.root
    }
}