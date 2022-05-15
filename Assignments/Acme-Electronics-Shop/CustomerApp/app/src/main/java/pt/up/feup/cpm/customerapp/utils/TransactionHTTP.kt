package pt.up.feup.cpm.customerapp.utils

import pt.up.feup.cpm.customerapp.activities.*
import pt.up.feup.cpm.customerapp.models.Customer
import pt.up.feup.cpm.customerapp.models.Product
import pt.up.feup.cpm.customerapp.models.Transaction
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL

class GetTransactions(val result: Home): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("$SERVER/transaction/get-all")
            System.out.println("GET " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doInput = true
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false
            val responseCode = urlConnection.responseCode
            if (responseCode == 200)
                println(readStream(urlConnection.inputStream))
            else
                println("Code: $responseCode")
        } catch (e: Exception) {
            println(e.toString())
        } finally {
            urlConnection?.disconnect()
        }
    }
}

class GetTransaction(act: PastTransactions, val userID: String): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("$SERVER/transaction/$userID")
            println("GET " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doInput = true
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false
            val responseCode = urlConnection.responseCode
            if (responseCode == 200)
                println(readStream(urlConnection.inputStream))
            else
                println("Code: $responseCode")
        } catch (e: Exception) {
            println(e.toString())
        } finally {
            urlConnection?.disconnect()
        }
    }
}

class AddTransaction(val act: ShoppingCart, val body: String) : Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("$SERVER/transaction/add")
            println("POST " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doOutput = true
            urlConnection.doInput = true
            urlConnection.requestMethod = "POST"
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false
            val outputStream = DataOutputStream(urlConnection.outputStream)
            outputStream.writeBytes(body)
            outputStream.flush()
            outputStream.close()

            // get response
            val responseCode = urlConnection.responseCode
            if (responseCode == 201)
                act.writeText(readStream(urlConnection.inputStream))
            else
                println("Code: $responseCode")
        }
        catch (e: java.lang.Exception) {
            println(e.toString())
        }
        finally {
            urlConnection?.disconnect()
        }
    }
}