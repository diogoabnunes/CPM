package pt.up.feup.cpm.customerapp.utils

import pt.up.feup.cpm.customerapp.activities.Home
import pt.up.feup.cpm.customerapp.activities.Login
import pt.up.feup.cpm.customerapp.activities.Register
import pt.up.feup.cpm.customerapp.models.Customer
import pt.up.feup.cpm.customerapp.models.Product
import pt.up.feup.cpm.customerapp.models.Transaction
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL

class GetTransactions(): Runnable {
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
                System.out.println(readStream(urlConnection.inputStream))
            else
                System.out.println("Code: $responseCode")
        } catch (e: Exception) {
            System.out.println(e.toString())
        } finally {
            urlConnection?.disconnect()
        }
    }
}

class GetTransaction(val userID: String): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("$SERVER/transaction/$userID")
            System.out.println("GET " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doInput = true
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false
            val responseCode = urlConnection.responseCode
            if (responseCode == 200)
                System.out.println(readStream(urlConnection.inputStream))
            else
                System.out.println("Code: $responseCode")
        } catch (e: Exception) {
            System.out.println(e.toString())
        } finally {
            urlConnection?.disconnect()
        }
    }
}

class AddTransaction(val body: String) : Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL(SERVER + "/transaction/add")
            System.out.println("POST " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doOutput = true
            urlConnection.doInput = true
            urlConnection.requestMethod = "POST"
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false
            val outputStream = DataOutputStream(urlConnection.outputStream)
//            val body = "{ " +
//                    "\"userID\": \"${transaction.getUserID()}\", " +
//                    "\"content\": \"${transaction.getContent()}\", " +
//                    "\"fiscalNumber\": \"${transaction.getDate()}\", " +
//                    "\"cardValidity\": \"${transaction.getPaid()}\"" + " }"
//            System.out.println("body: $body")
            outputStream.writeBytes(body)
            outputStream.flush()
            outputStream.close()

            // get response
            val responseCode = urlConnection.responseCode
            if (responseCode == 201)
                System.out.println(readStream(urlConnection.inputStream))
            else
                System.out.println("Code: $responseCode")
        }
        catch (e: java.lang.Exception) {
            System.out.println(e.toString())
        }
        finally {
            urlConnection?.disconnect()
        }
    }
}