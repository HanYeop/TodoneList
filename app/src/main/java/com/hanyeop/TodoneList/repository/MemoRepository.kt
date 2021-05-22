package com.hanyeop.TodoneList.repository

import com.hanyeop.TodoneList.data.MemoDao
import com.hanyeop.TodoneList.model.Memo
import kotlinx.coroutines.flow.Flow

// 앱에서 사용하는 데이터와 그 데이터 통신을 하는 역할
class MemoRepository(private val memoDao: MemoDao) {
    val readAllData : Flow<List<Memo>> = memoDao.readAllData()

    suspend fun addUser(memo: Memo){
        memoDao.addUser(memo)
    }

    suspend fun updateUser(memo: Memo){
        memoDao.updateUser(memo)
    }

    suspend fun deleteUser(memo: Memo){
        memoDao.deleteUser(memo)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Memo>> {
        return memoDao.searchDatabase(searchQuery)
    }
}