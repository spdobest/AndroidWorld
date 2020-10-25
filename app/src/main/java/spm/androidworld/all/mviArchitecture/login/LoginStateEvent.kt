package spm.androidworld.all.mviArchitecture.login


/**
 * Created by Sibaprasad Mohanty on 13/08/20.
 * sp.dobest@gmail.com
 */

sealed class LoginStateEvent {

    object Finished : LoginStateEvent()

    class LoginUser(
        val emailId: String,
        val password: String
    ) : LoginStateEvent()

    object None : LoginStateEvent()

    object Loading : LoginStateEvent()
}