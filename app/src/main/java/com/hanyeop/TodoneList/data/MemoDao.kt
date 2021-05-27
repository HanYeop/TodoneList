package com.hanyeop.TodoneList.data

import androidx.room.*
import com.hanyeop.TodoneList.model.Memo
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDao {
    // OnConflictStrategy.IGNORE = 동일한 아이디가 있을 시 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMemo(memo : Memo)

    @Update
    suspend fun updateMemo(memo : Memo)

    @Delete
    suspend fun deleteMemo(memo : Memo)

    // 큰 날짜부터 출력
    @Query("SELECT * FROM Memo ORDER BY year DESC, month DESC, day DESC, id DESC")
    fun readAllData() : Flow<List<Memo>>

    @Query("SELECT * FROM Memo WHERE content LIKE :searchQuery")
    fun searchDatabase(searchQuery : String) : Flow<List<Memo>>
}