package com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.use_case

import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Result
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.repository.AuthRepository
import com.gmail.bodziowaty6978.fitnessappv2.feature_auth.domain.use_case.ResetPasswordWithEmail
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
internal class ResetPasswordWithEmailTest{
    @Mock
    private lateinit var authRepository: AuthRepository
    private lateinit var resetPasswordWithEmail: ResetPasswordWithEmail
    private lateinit var closeable: AutoCloseable

    @Before
    fun setUp() = runTest{
        closeable = MockitoAnnotations.openMocks(this)

        authRepository = Mockito.mock(AuthRepository::class.java)
        Mockito.`when`(authRepository.sendPasswordResetEmail(anyString())).thenReturn(Result.Success)
        resetPasswordWithEmail = ResetPasswordWithEmail(
            repository = authRepository,
            resourceProvider = ResourceProvider(context = RuntimeEnvironment.getApplication())
        )
    }

    @Test
    fun `reset password with blank email, returned error result`() = runBlocking {
        val result = resetPasswordWithEmail(
            email = ""
        )
        assertTrue(result is Result.Error)
    }

    @Test
    fun `reset password with email, returned success result`() = runBlocking {
        val result = resetPasswordWithEmail(
            email = AuthConstants.ValidEmail
        )

        assertTrue(result is Result.Success )
    }

    @Test
    fun `reset password with invalid email, error result`() = runTest {
        val result = resetPasswordWithEmail(
            email = AuthConstants.InvalidEmail,
        )

        assertTrue(result is Result.Error)
    }

    @After
    fun after(){
        closeable.close()
    }


}