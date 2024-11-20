package com.example.aidsync

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.aidsync.data.AppDatabase
import com.example.aidsync.data.dao.UserDao
import com.example.aidsync.data.entities.User
import com.example.aidsync.data.repositories.UserRepository
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class UserAuthenticationTest {
    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao
    private lateinit var userRepository: UserRepository

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        userDao = db.userDao()
    }

    /**
     * Close the database after each test.
     */
    @After
    @Throws(IOException::class)
    fun tearDown() {
        if (this::db.isInitialized) {
            db.close()
        }
    }


    /**
     * Test that the database is initialized correctly.
     */
    @Test
    fun database_is_initialized_correctly() {
        assertNotNull(db)
        assertNotNull(userDao)
    }

    /**
     * Test that the user can be registered successfully and retrieved.
     */
    @Test
    fun register_user_and_retrieve_successfully() = runBlocking {
        val user = User(
            name = "John Doe",
            email = "john.doe@example.com",
            password = "password123"
        )

        userRepository = UserRepository(userDao)
        userRepository.registerUser(user)

        val retrievedUser = userRepository.getUserByEmail(user.email)
        assertNotNull(retrievedUser)
    }

    /**
     * Test that the user cannot be registered with an existing email.
     */
    @Test
    fun registration_fails_with_duplicate_email() = runBlocking {
        val user = User(
            name = "John Doe",
            email = "john.doe@example.com",
            password = "password123"
        )

        userRepository = UserRepository(userDao)
        userRepository.registerUser(user)

        val userTwo = User(
            name = "Jane Doe",
            email = "john.doe@example.com",
            password = "password456"
        )

        val isRegistrationSuccess = userRepository.registerUser(userTwo)
        assert(!isRegistrationSuccess)
    }

    /**
     * Test that the user can login successfully.
     */
    @Test
    fun login_succeeds_with_valid_credentials() = runBlocking {
        val user = User(
            name = "John Doe",
            email = "john.doe@example.com",
            password = "password123"
        )

        userRepository = UserRepository(userDao)
        userRepository.registerUser(user)

        val isLoginSuccess = userRepository.loginUser(user)
        assert(isLoginSuccess)
    }

    /**
     * Test that the user cannot login with an invalid email.
     */
    @Test
    fun login_fails_with_invalid_email() = runBlocking {
        val user = User(
            name = "John Doe",
            email = "john.doe@example.com",
            password = "password123"
        )

        userRepository = UserRepository(userDao)
        userRepository.registerUser(user)

        val isLoginSuccess = userRepository.loginUser(user.copy(email = "invalid_email"))
        assert(!isLoginSuccess)
    }

    /**
     * Test that the user cannot login with an invalid password.
     */
    @Test
    fun login_fails_with_invalid_password() = runBlocking {
        val user = User(
            name = "John Doe",
            email = "john.doe@example.com",
            password = "password123"
        )

        userRepository = UserRepository(userDao)
        userRepository.registerUser(user)

        val isLoginSuccess = userRepository.loginUser(user.copy(password = "invalid_password"))
        assert(!isLoginSuccess)
    }


}