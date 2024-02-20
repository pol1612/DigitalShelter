
package com.pol.sane.jove.digitalshelter.model.service.interfaces

import com.pol.sane.jove.digitalshelter.model.UserDetails
import com.pol.sane.jove.digitalshelter.model.service.User
import kotlinx.coroutines.flow.Flow

interface AccountServiceInterface {
  val currentUserId: String
  val hasUser: Boolean

  val currentUser: Flow<User>

  suspend fun createAccountAndAuthenticate(email: String, password: String)
  suspend fun authenticate(email: String, password: String)
  suspend fun sendRecoveryEmail(email: String)
  suspend fun deleteAccount()
  suspend fun signOut()
}
