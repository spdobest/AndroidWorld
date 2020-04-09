package com.heyyy.main.utility


/**
 * Created by Sibaprasad Mohanty on 2020-01-15.
 * Wipro Limited
 */

class AppConstant {

    companion object {
        val EMPTY_TEXT = ""
        val MALE = "Male"
        val FEMALE = "Female"

        val NAV_TYPE_MEN = 1
        val NAV_TYPE_WOMEN = 2
        val NAV_TYPE_KIDS = 3
    }

    interface BundleKeys {
        companion object {
            val USER_NAME: String = "userName"
            val USER_ID: String = "userId"
            val RECIEVER_ID: String = "recieverName"
            val RECIEVER_NAME: String = "recieverId"
            val SENDER_RECIEVER_ID: String = "senderReceiverId"
        }
    }

}
