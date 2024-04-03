
package com.pol.sane.jove.digitalshelter.data.interfaces

import com.pol.sane.jove.digitalshelter.data.pojo.User

interface UserServiceInterface {
        suspend fun createUser(email: String, password: String, setSnackbarText: (String) -> Unit )
        suspend fun authenticateUser(email: String, password: String, setViewModelSnackbarText: (String) -> Unit)
        suspend fun deleteCurrentAccount(): Boolean
        suspend fun signOut()

        val currentUser: User
        suspend fun sendRecoveryEmail(
                email: String,
                setViewModelSnackbartext: (String) -> Unit
        )

}
