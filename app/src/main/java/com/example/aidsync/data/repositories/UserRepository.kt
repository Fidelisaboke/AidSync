package com.example.aidsync.data.repositories

import com.example.aidsync.data.dao.UserDao
import com.example.aidsync.data.entities.User
import org.mindrot.jbcrypt.BCrypt

/**
 * Repository class for user-related operations. It also handles authentication.
 */
class UserRepository(val dao: UserDao) {
    /**
     * Registers a new user with the provided credentials.
     */
    suspend fun registerUser(user: User): Boolean {
        val existingUser = dao.getUserByEmail(user.email)
        if (existingUser != null) return false

        val hashedPassword = hashPassword(user.password)
        val newUser = user.copy(password = hashedPassword)
        dao.insertUser(newUser)
        return true
    }

    /**
     * Login user with the provided credentials.
     */
    suspend fun loginUser(email: String, password: String): Boolean {
        val existingUser = dao.getUserByEmail(email) ?: return false
        return verifyPassword(password, existingUser.password)
    }

    /**
     * Fetch a user by ID.
     */
    suspend fun getUserById(id: Int): User?  {
        return dao.getUserById(id)
    }

    /**
     * Retrieves a user by their email.
     */
    suspend fun getUserByEmail(email: String): User? {
        return dao.getUserByEmail(email)
    }

    /**
     * Update user details.
     */
    suspend fun updateUser(user: User) {
        dao.updateUser(user)
    }

    /**
     * Deletes a user.
     */
    suspend fun deleteUser(user: User){
        dao.deleteUser(user)
    }

    /**
     * Hashes the provided password using BCrypt.
     */
    private fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    /**
     * Verifies the provided password against the hashed password.
     */
    private fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(plainPassword, hashedPassword)
    }
}