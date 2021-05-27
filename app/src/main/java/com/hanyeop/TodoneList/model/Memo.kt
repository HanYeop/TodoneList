package com.hanyeop.TodoneList.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memo(
    val check : Boolean,
    val content : String,
    val year : Int,
    val month : Int,
    val day : Int
    ) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}

