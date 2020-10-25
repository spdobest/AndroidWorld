package spm.androidworld.all.mviArchitecture.login


/**
 * Created by Sibaprasad Mohanty on 13/08/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */


data class LoginResponse(
    val statusCode: Int = 0,
    val message: String = "",
    val loginData: LoginData? = null
)

data class LoginData(val userId: String = "", val name: String = "")