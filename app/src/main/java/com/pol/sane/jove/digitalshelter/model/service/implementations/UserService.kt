package com.pol.sane.jove.digitalshelter.model.service.implementations

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.pol.sane.jove.digitalshelter.model.service.User
import com.pol.sane.jove.digitalshelter.model.service.interfaces.UserServiceInterface
import kotlinx.coroutines.tasks.await

class UserService(private val auth: FirebaseAuth): UserServiceInterface {
    override val currentUser: User
        get() = User(auth.currentUser?.uid,auth.currentUser?.email)

    override fun createAccountAndAuthenticate(email: String, password: String): Boolean {
        var userHasBeenSignedUpAndAuthenticated = false
        Log.i("AccountService::createAccountAndAuthenticate","signUp service method")
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("AccountService::createAccountAndAuthenticate::success","signUp realized")
                    userHasBeenSignedUpAndAuthenticated = authenticate(email, password)
                } else {
                    Log.i("AccountService::createAccountAndAuthenticate::failure",task.exception?.message ?: "An unknown error occurred")
                }
            }
        return userHasBeenSignedUpAndAuthenticated
    }

     override fun authenticate(email: String, password: String): Boolean {
        var userHasBeenAuthenticated = false
        Log.i("AccountService::authenticate","login service method")
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("AccountService::authenticate::success","login realized: ${auth.currentUser?.email.toString()}")
                    userHasBeenAuthenticated = true
                } else {
                    Log.i("AccountService::authenticate::failure",task.exception?.message ?: "An unknown error occurred")
                }
            }
        return userHasBeenAuthenticated
    }

    override fun sendRecoveryEmail(email: String): Boolean{
        auth.sendPasswordResetEmail(email)
        return false
        //TODO
    }

    override fun deleteCurrentAccount(): Boolean {
        auth.currentUser!!.delete()
        return false
        //TODO
    }

    override fun signOut(): Boolean {
        auth.signOut()
        return false
        //TODO
    }
}