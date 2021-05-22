package com.hanyeop.TodoneList.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hanyeop.TodoneList.model.Memo

/* entities = 사용할 엔티티 선언, version = 엔티티 구조 변경 시 구분해주는 역할
   exportSchema = 스키마 내보내기 설정 */
@Database(entities = [Memo::class], version = 1, exportSchema = false)
abstract class MemoDatabase : RoomDatabase(){

    abstract fun memoDao() : MemoDao

    companion object{
        /* @Volatile = 접근가능한 변수의 값을 cache를 통해 사용하지 않고
        thread가 직접 main memory에 접근 하게하여 동기화. */
        @Volatile
        private var instance : MemoDatabase? = null

        // 싱글톤으로 생성 (자주 생성 시 성능 손해). 이미 존재할 경우 생성하지 않고 바로 반환
        fun getDatabase(context : Context) : MemoDatabase? {
            if(instance == null){
                synchronized(MemoDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MemoDatabase::class.java,
                        "memo_database"
                    ).build()
                }
            }
            return instance
        }
    }
}