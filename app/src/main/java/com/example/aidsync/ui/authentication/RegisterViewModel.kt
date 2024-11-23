package com.example.aidsync.ui.authentication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.aidsync.data.AppDatabase
import com.example.aidsync.data.entities.User
import com.example.aidsync.data.repositories.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application): AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val dao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(dao)
    }

    suspend fun register(user: User): Boolean {
        return repository.registerUser(user)
    }
}