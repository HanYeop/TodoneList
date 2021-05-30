package com.hanyeop.todoneList.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.hanyeop.todoneList.data.MemoDatabase
import com.hanyeop.todoneList.model.Memo
import com.hanyeop.todoneList.repository.MemoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// 뷰모델은 DB에 직접 접근하지 않아야함. Repository 에서 데이터 통신.
class MemoViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Memo>>
    private val repository : MemoRepository

    init{
        val memoDao = MemoDatabase.getDatabase(application)!!.memoDao()
        repository = MemoRepository(memoDao)
        readAllData = repository.readAllData.asLiveData()
    }

    fun addMemo(memo : Memo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMemo(memo)
        }
    }

    fun updateMemo(memo : Memo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMemo(memo)
        }
    }

    fun deleteMemo(memo : Memo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMemo(memo)
        }
    }

    fun readDateData(year : Int, month : Int, day : Int): LiveData<List<Memo>> {
        return repository.readDateData(year, month, day).asLiveData()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Memo>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }
}