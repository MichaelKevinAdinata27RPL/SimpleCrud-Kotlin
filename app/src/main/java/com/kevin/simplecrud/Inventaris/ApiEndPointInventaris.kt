package com.kevin.simplecrud.Inventaris

class ApiEndPointInventaris {
    companion object {

        private val SERVER = "https://admincrud.000webhostapp.com/inventaris/"
        val CREATE = SERVER + "create.php"
        val READ = SERVER + "read.php"
        val DELETE = SERVER + "delete.php"
        val UPDATE = SERVER + "update.php"

    }
}