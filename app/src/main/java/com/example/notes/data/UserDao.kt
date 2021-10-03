package com.example.notes.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM user_table ORDER By id ASC")
    fun readAllData(): LiveData<List<User>>
}