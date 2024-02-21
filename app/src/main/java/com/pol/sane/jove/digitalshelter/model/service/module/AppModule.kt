

package com.pol.sane.jove.digitalshelter.model.service.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pol.sane.jove.digitalshelter.model.service.implementations.AccountService
import com.pol.sane.jove.digitalshelter.model.service.interfaces.AccountServiceInterface
import com.pol.sane.jove.digitalshelter.ui.screens.auth.login.LoginViewModel
import org.koin.dsl.module

val appModule = module {
    //Firebase Instances
    single { FirebaseAuth.getInstance() } // Provide FirebaseAuth instance
    single { FirebaseFirestore.getInstance() } // Provide FirebaseFirestore instance
    // Services Implementations
    single<AccountServiceInterface> { AccountService(get()) }
    //ViewModels
    //viewModel {LoginViewModel()}
}