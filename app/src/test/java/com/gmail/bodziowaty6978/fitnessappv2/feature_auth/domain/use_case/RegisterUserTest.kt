package com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.use_case

import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.common.util.CustomResult
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.repository.AuthRepository
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.util.AuthConstants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
internal class RegisterUserTest{

    @Mock
    private lateinit var authRepository: AuthRepository
    private lateinit var registerUser: RegisterUser

    private lateinit var closeable: AutoCloseable

    @Before
    fun setUp() = runTest{
        closeable = MockitoAnnotations.openMocks(this)
        authRepository = Mockito.mock(AuthRepository::class.java)
        Mockito.`when`(authRepository.registerUser(anyString(),anyString())).thenReturn(CustomResult.Success)
        registerUser = RegisterUser(
            repository = authRepository,
            resourceProvider = ResourceProvider(context = RuntimeEnvironment.getApplication())
        )
    }

    @Test
    fun `register with blank email, returned error result`() = runBlocking {
        val result = registerUser(
            email = "",
            password = AuthConstants.ValidPassword,
            confirmPassword = AuthConstants.ValidPassword,
            username = AuthConstants.ValidUsername
        )

        assertTrue(result is CustomResult.Error)
    }

    @Test
    fun `register with password 5 characters long, returned error result`() = runBlocking {
        val result = registerUser(
            email = AuthConstants.ValidEmail,
            password = AuthConstants.ShortPassword,
            confirmPassword = AuthConstants.ShortPassword,
            username = AuthConstants.ValidUsername
        )

        assertTrue(result is CustomResult.Error)
    }

    @Test
    fun `register with password 25 characters long, returned error result`() = runBlocking {
        val result = registerUser(
            email = AuthConstants.ValidEmail,
            password = AuthConstants.LongPassword,
            confirmPassword = AuthConstants.LongPassword,
            username = AuthConstants.ValidUsername
        )

        assertTrue(result is CustomResult.Error)
    }

    @Test
    fun `register with 2 different passwords, returned error result`() = runBlocking {
        val result = registerUser(
            email = AuthConstants.ValidEmail,
            password = AuthConstants.ValidPassword,
            confirmPassword = AuthConstants.ValidPassword2,
            username = AuthConstants.ValidUsername
        )

        assertTrue(result is CustomResult.Error)
    }

    @Test
    fun `register with invalid email, error result`() = runTest {
        val result = registerUser(
            email = AuthConstants.InvalidEmail,
            password = AuthConstants.ValidPassword,
            confirmPassword = AuthConstants.ValidPassword,
            username = AuthConstants.ValidUsername
        )

        assertTrue(result is CustomResult.Error)
    }



    @Test
    fun `register user, returned success result`() = runBlocking {
        val result = registerUser(
            email = AuthConstants.ValidEmail,
            password = AuthConstants.ValidPassword,
            confirmPassword = AuthConstants.ValidPassword,
            username = AuthConstants.ValidUsername
        )

        assertTrue(result is CustomResult.Success )
    }

    @After
    fun after(){
        closeable.close()
    }
}

