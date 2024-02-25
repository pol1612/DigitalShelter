
package com.pol.sane.jove.digitalshelter.model.service.interfaces

import com.pol.sane.jove.digitalshelter.model.service.User

interface UserServiceInterface {
        fun createAccountAndAuthenticate(email: String, password: String): Boolean
        fun authenticate(email: String, password: String): Boolean
        fun deleteCurrentAccount(): Boolean
        fun signOut(): Boolean

        val currentUser: User
        suspend fun sendRecoveryEmail(
                email: String,
                setViewModelSnackbartext: (String) -> Unit
        )
}
