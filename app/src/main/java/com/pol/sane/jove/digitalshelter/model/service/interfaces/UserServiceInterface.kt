
package com.pol.sane.jove.digitalshelter.model.service.interfaces

import com.pol.sane.jove.digitalshelter.model.service.User

interface UserServiceInterface {
        suspend fun createUser(email: String, password: String, setSnackbarText: (String) -> Unit )
        fun authenticateUser(email: String, password: String, setViewModelSnackbarText: (String) -> Unit): Boolean
        fun deleteCurrentAccount(): Boolean
        fun signOut(): Boolean

        val currentUser: User
        suspend fun sendRecoveryEmail(
                email: String,
                setViewModelSnackbartext: (String) -> Unit
        )

}
