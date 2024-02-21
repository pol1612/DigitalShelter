
package com.pol.sane.jove.digitalshelter.model.service.interfaces

interface UserServiceInterface {
  val currentUserId: String
  fun createAccountAndAuthenticate(email: String, password: String): Boolean
  fun authenticate(email: String, password: String): Boolean
  fun sendRecoveryEmail(email: String): Boolean
  fun deleteAccount(): Boolean
  fun signOut(): Boolean
}
