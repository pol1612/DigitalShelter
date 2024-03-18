package com.pol.sane.jove.digitalshelter.model.service.implementations

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.pol.sane.jove.digitalshelter.model.service.User
import com.pol.sane.jove.digitalshelter.model.service.interfaces.UserServiceInterface
import kotlinx.coroutines.tasks.await

class UserService(private val auth: FirebaseAuth): UserServiceInterface {
    override val  currentUser: User
        get() = User(auth.currentUser?.uid,auth.currentUser?.email)

    override suspend fun createUser(
        email: String,
        password: String,
        setSnackbarText: (String) -> Unit
    ) {
        try {
            auth.createUserWithEmailAndPassword(email, password).await()

        } catch (e: FirebaseAuthWeakPasswordException) {
            // Weak password exception
            Log.w("UserCreation", "Weak password: ${e.message}")
            setSnackbarText("Unable to sign-up user, the password is too insecure.")
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            // Invalid credentials exception (e.g., malformed email, empty password)
            Log.w("UserCreation", "Invalid credentials: ${e.message}")
            setSnackbarText("Unable to sign-up user due to a malformed email or empty password.")

        } catch (e: FirebaseAuthUserCollisionException) {
            // User collision exception (user with same email already exists)
            Log.w("UserCreation", "User collision: ${e.message}")
            setSnackbarText("Unable to sign-up user due another user having the same email.")
        } catch (e: Exception) {
            // Other exceptions
            Log.e("UserCreation", "Error creating user: ${e.message}")
            setSnackbarText("Unable to sign-up user due to unknown error.")
        }
    }


    override suspend fun authenticateUser(email: String, password: String, setViewModelSnackbarText: (String) -> Unit) {
        Log.i("AccountService::authenticate","login service method")
        try {
            auth.signInWithEmailAndPassword(email, password).await()

            setViewModelSnackbarText("The user was successfully logged in.")

            Log.i("AccountService::authenticate::success","login realized: ${auth.currentUser?.email.toString()}")
        }catch(e: Exception){
            when(e.message){

                "The supplied auth credential is incorrect, malformed or has expired." -> { setViewModelSnackbarText("Wrong email or password credentials.") }

                "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> { setViewModelSnackbarText("Network error.")}

                else -> { setViewModelSnackbarText("Unknown error.") }

            }
        }
    }

     override suspend fun sendRecoveryEmail(email: String, setViewModelSnackbarText: (String) -> Unit){
        try {
            auth.sendPasswordResetEmail(email).await()
            Log.i("UserService::sendRecoveryEmail::success", "email sent")
            setViewModelSnackbarText("The email was successfully sent.")

        }catch (e:Exception){
            Log.i("UserService::sendRecoveryEmail::failure", "email not sent")
            setViewModelSnackbarText("There was an error delivering the email.")
        }
    }



    override suspend fun deleteCurrentAccount(): Boolean {
        auth.currentUser!!.delete().await()
        return false
        //TODO
    }

    override suspend fun signOut() {
        auth.signOut()
        Log.i("signOut","user logged out")
    }
}