package com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.domain.use_case

import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.repository.AuthRepository
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.use_case.LogInUser
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.util.AuthConstants
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
internal class LogInUserTest {

    @Mock
    private lateinit var authRepository: AuthRepository
    private lateinit var logInUser: LogInUser

    private lateinit var closeable: AutoCloseable

    @Before
    fun setUp() = runTest{
        closeable = MockitoAnnotations.openMocks(this)

        authRepository = Mockito.mock(AuthRepository::class.java)
        Mockito.`when`(authRepository.logInUser(anyString(), anyString())).thenReturn(Result.Success)
        logInUser = LogInUser(
            repository = authRepository,
            resourceProvider = ResourceProvider(context = RuntimeEnvironment.getApplication())
        )
    }

    @Test
    fun `log in with blank email, returned error result`() = runTest {
        val result = logInUser(
            email = "",
            password = AuthConstants.ValidPassword
        )

        assertTrue(result is Result.Error)
    }

    @Test
    fun `log in with blank password, returned error result`() = runTest {
        val result = logInUser(
            email = AuthConstants.ValidEmail,
            password = ""
        )

        assertTrue(result is Result.Error)
    }

    @Test
    fun `log in with invalid email, error result`() = runTest {
        val result = logInUser(
            email = AuthConstants.InvalidEmail,
            password = AuthConstants.ValidPassword
        )

        assertTrue(result is Result.Error)
    }

    @Test
    fun `log in user, returned success result`() = runTest {
        val result = logInUser(
            email = AuthConstants.ValidEmail,
            password = AuthConstants.ValidPassword
        )

        assertTrue(result is Result.Success)
    }

    @After
    fun after(){
        closeable.close()
    }
}