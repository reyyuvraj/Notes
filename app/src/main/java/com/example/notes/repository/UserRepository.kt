package com.example.notes.repository

import androidx.lifecycle.LiveData
import com.example.notes.data.User
import com.example.notes.data.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }



}