package com.pol.sane.jove.digitalshelter.data.database_entities

import com.google.firebase.Timestamp
import java.util.Date

data class Dog(
    val dogName: String = "",
    val dogDescription: String = "",
    val dogSize: String = "",
    val isDogMale: Boolean = true,
    val dogShelterAuthUserId: String = "",
    val dogImageUrl: String = "",
    val dogStatus: String = "",
    val authUsersIdsOfUsersThatBookmarkedDog: Array<String> = arrayOf(),
    val dogDateOfBirth: Timestamp = Timestamp(Date())
)
