package com.pol.sane.jove.digitalshelter.model.service.implementations

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.pol.sane.jove.digitalshelter.model.UserDetails
import com.pol.sane.jove.digitalshelter.model.service.User
import com.pol.sane.jove.digitalshelter.model.service.interfaces.AccountServiceInterface
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountService(private val auth: FirebaseAuth): AccountServiceInterface {
    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()
    override val hasUser: Boolean
        get() = auth.currentUser != null
    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend(auth.currentUser?.let { User(it.uid) } ?: User())
                }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    override suspend fun createAccountAndAuthenticate(email: String, password: String) {
        Log.i("AccountService::createAccountAndAuthenticate","signUp service method")
        auth.createUserWithEmailAndPassword(email,password).await()
        authenticate(email, password)

    }

    override suspend fun authenticate(email: String, password: String) {
        Log.i("AccountService::authenticate","login service method")
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("AccountService::authenticate::success","login realized: ${auth.currentUser?.email.toString()}")
                } else {
                    Log.i("AccountService::authenticate::failure",task.exception?.message ?: "An unknown error occurred")
                }
            }
    }

    override suspend fun sendRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}