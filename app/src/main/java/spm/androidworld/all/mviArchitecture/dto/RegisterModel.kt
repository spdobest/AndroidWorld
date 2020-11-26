package spm.androidworld.all.mviArchitecture.dto


/**
 * Created by Sibaprasad Mohanty on 25/11/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */

data class RegisterModel(
    val name: String,
    val emailId: String,
    val gender: String,
    val address: String,
    val password: String
)