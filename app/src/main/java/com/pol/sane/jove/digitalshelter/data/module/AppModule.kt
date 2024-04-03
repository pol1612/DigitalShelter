

package com.pol.sane.jove.digitalshelter.data.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pol.sane.jove.digitalshelter.data.implementations.UserDetailsService
import com.pol.sane.jove.digitalshelter.data.implementations.UserService
import com.pol.sane.jove.digitalshelter.data.interfaces.UserDetailsServiceInterface
import com.pol.sane.jove.digitalshelter.data.interfaces.UserServiceInterface
import org.koin.dsl.module

val appModule = module {
    //Firebase Instances
    single { FirebaseAuth.getInstance() } // Provide FirebaseAuth instance
    single { FirebaseFirestore.getInstance() } // Provide FirebaseFirestore instance
    // Services Implementations
    single<UserServiceInterface> { UserService(get()) }
    single<UserDetailsServiceInterface> { UserDetailsService(get(),get()) }

    //ViewModels
    //viewModel {LoginViewModel()}
}