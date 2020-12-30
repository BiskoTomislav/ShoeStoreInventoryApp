package com.udacity.shoestore.models

interface User {
    val name: String
}

data class AppUser(override var name: String) : User

class NoUser(override val name: String = "No user logged in") : User