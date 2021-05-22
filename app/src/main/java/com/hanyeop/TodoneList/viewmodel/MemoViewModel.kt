package com.hanyeop.TodoneList.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.hanyeop.TodoneList.data.MemoDatabase
import com.hanyeop.TodoneList.model.Memo
import com.hanyeop.TodoneList.repository.MemoRepository
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

    fun addUser(memo : Memo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(memo)
        }
    }

    fun updateUser(memo : Memo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(memo)
        }
    }

    fun deleteUser(memo : Memo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(memo)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Memo>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }
}