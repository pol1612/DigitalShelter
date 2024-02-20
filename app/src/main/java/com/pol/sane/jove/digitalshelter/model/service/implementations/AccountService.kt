package com.pol.sane.jove.digitalshelter.model.service.implementations

import com.google.firebase.auth.FirebaseAuth
import com.pol.sane.jove.digitalshelter.model.UserDetails
import com.pol.sane.jove.digitalshelter.model.service.interfaces.AccountServiceInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountService @Inject constructor(
    private val auth: FirebaseAuth
): AccountServiceInterface {
    override val currentUserId: String
        get() = TODO("Not yet implemented")
    override val hasUser: Boolean
        get() = TODO("Not yet implemented")
    override val currentUser: Flow<UserDetails>
        get() = TODO("Not yet implemented")

    override suspend fun createAccountAndAuthenticate(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun authenticate(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun sendRecoveryEmail(email: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAccount() {
        TODO("Not yet implemented")
    }

    override suspend fun signOut() {
        TODO("Not yet implemented")
    }
}