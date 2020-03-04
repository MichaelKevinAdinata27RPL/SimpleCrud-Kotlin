package com.kevin.simplecrud.Guru

class ApiEndPointGuru {
    companion object {

        private val SERVER = "https://admincrud.000webhostapp.com/guru/"
        val CREATE = SERVER + "create.php"
        val READ = SERVER + "read.php"
        val DELETE = SERVER + "delete.php"
        val UPDATE = SERVER + "update.php"
    }
}