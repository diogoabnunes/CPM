package pt.up.feup.cpm.customerapp.http

import pt.up.feup.cpm.customerapp.activities.*
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL

class GetTransactions(val act: PastTransactions, val userID: String): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("$SERVER/transaction/getfromID/$userID")
            println("GET " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doInput = true
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false
            val responseCode = urlConnection.responseCode
            if (responseCode == 200) {
                act.response = readStream(urlConnection.inputStream)
                println("Hey " + act.response)
            }
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
                act.transaction_res = (readStream(urlConnection.inputStream))
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