
package com.pol.sane.jove.digitalshelter.model.service

import com.pol.sane.jove.digitalshelter.model.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
  val currentUserId: String
  val hasUser: Boolean

  val currentUser: Flow<User>

  suspend fun authenticate(email: String, password: String)
  suspend fun sendRecoveryEmail(email: String)
  suspend fun deleteAccount()
  suspend fun signOut()
}
