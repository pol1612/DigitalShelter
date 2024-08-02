
package com.pol.sane.jove.digitalshelter.data.interfaces

import com.pol.sane.jove.digitalshelter.data.database_entities.User

interface UserServiceInterface {
        val currentUser: User
        suspend fun createUser(email: String, password: String, setSnackbarText: (String) -> Unit )
        suspend fun authenticateUser(email: String, password: String, setViewModelSnackbarText: (String) -> Unit)
        suspend fun deleteCurrentAccount(): Boolean
        suspend fun signOut()
        suspend fun sendRecoveryEmail(email: String, setViewModelSnackbarText: (String) -> Unit)

}
