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
        } finally {

        }
    }


    override fun authenticateUser(email: String, password: String, setViewModelSnackbarText: (String) -> Unit): Boolean {
        var userHasBeenAuthenticated = false
        Log.i("AccountService::authenticate","login service method")
        auth.signInWithEmailAndPassword(email, password)
            .addOnFailureListener { task ->
                Log.i("UserService::authenticate::failure", "${task.message}")

                when(task.message){
                    "The supplied auth credential is incorrect, malformed or has expired." -> { setViewModelSnackbarText("Wrong email or password credentials.") }
                    "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> { setViewModelSnackbarText("Network error.")}
                    else -> { setViewModelSnackbarText("Unknown error.") }
                }

            }
            .addOnSuccessListener {
                Log.i("AccountService::authenticate::success","login realized: ${auth.currentUser?.email.toString()}")
                userHasBeenAuthenticated = true
                setViewModelSnackbarText("The user was successfully logged in.")
            }
        return userHasBeenAuthenticated
    }

     override suspend fun sendRecoveryEmail(email: String, setViewModelSnackbarText: (String) -> Unit){
        auth.sendPasswordResetEmail(email)
            .addOnFailureListener {
                Log.i("UserService::sendRecoveryEmail::failure", "email not sent")
                setViewModelSnackbarText("There was an error delivering the email.")
            }
            .addOnSuccessListener {
                Log.i("UserService::sendRecoveryEmail::success", "email sent")
                setViewModelSnackbarText("The email was successfully sent.")

            }

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