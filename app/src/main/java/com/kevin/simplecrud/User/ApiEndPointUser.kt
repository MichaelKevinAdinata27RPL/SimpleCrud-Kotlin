package com.kevin.simplecrud.User

class ApiEndPointUser {
    companion object {

        private val SERVER = "https://admincrud.000webhostapp.com/user/"
        val CREATE = SERVER + "create.php"
        val READ = SERVER + "read.php"
        val DELETE = SERVER + "delete.php"
        val UPDATE = SERVER + "update.php"
    }
}
