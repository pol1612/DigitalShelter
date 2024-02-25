

package com.pol.sane.jove.digitalshelter.model.service.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pol.sane.jove.digitalshelter.model.service.implementations.UserDetailsService
import com.pol.sane.jove.digitalshelter.model.service.implementations.UserService
import com.pol.sane.jove.digitalshelter.model.service.interfaces.UserDetailsServiceInterface
import com.pol.sane.jove.digitalshelter.model.service.interfaces.UserServiceInterface
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