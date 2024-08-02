
package com.pol.sane.jove.digitalshelter.data.interfaces

import com.pol.sane.jove.digitalshelter.data.database_entities.Dog

interface DogServiceInterface {
        //TODO: create Dog
        //TODO: get all Dogs that have the user's id
        //TODO: get all Dogs that match with a Filters object
        //TODO: create dog search Filters class
        //TODO: get all saved dogs from a user
        //TODO:
        suspend fun createDog(dog: Dog)
        suspend fun authenticateUser(email: String, password: String, setViewModelSnackbarText: (String) -> Unit)


}
