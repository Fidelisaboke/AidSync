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

        // Pass valid email and password to the loginUser function
        val isLoginSuccess = userRepository.loginUser(user.email, user.password)
        assert(isLoginSuccess) { "Login should succeed with valid credentials." }
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

        // Pass invalid email and valid password to the loginUser function
        val isLoginSuccess = userRepository.loginUser("invalid_email@example.com", user.password)
        assert(!isLoginSuccess) { "Login should fail with an invalid email." }
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

        // Pass valid email and invalid password to the loginUser function
        val isLoginSuccess = userRepository.loginUser(user.email, "invalid_password")
        assert(!isLoginSuccess) { "Login should fail with an invalid password." }
    }

    /**
     * Test that the getUserByEmail functions works correctly..
     */
    @Test
    fun retrieve_user_by_email() = runBlocking {
        val user = User(
            name = "Alice Smith",
            email = "alice.smith@example.com",
            password = "securepassword"
        )

        userRepository = UserRepository(userDao)
        userRepository.registerUser(user)

        val retrievedUser = userRepository.getUserByEmail("alice.smith@example.com")
        assertNotNull(retrievedUser)
        assert(retrievedUser?.name == "Alice Smith")
        assert(retrievedUser?.email == "alice.smith@example.com")
    }

    /**
     * Test that the user cannot login with empty email or password.
     */
    @Test
    fun registration_fails_with_empty_email_or_password() = runBlocking {
        val user = User(name = "Empty Email", email = "", password = "password123")
        val userWithEmptyPassword = User(name = "Empty Password", email = "empty.password@example.com", password = "")

        userRepository = UserRepository(userDao)

        val successEmptyEmail = userRepository.registerUser(user)
        val successEmptyPassword = userRepository.registerUser(userWithEmptyPassword)

        assert(!successEmptyEmail)
        assert(!successEmptyPassword)
    }

    /**
     * Test that the login is case-sensitive for emails.
     */



}