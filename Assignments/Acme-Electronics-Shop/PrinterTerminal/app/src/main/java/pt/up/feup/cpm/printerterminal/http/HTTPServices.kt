package pt.up.feup.cpm.printerterminal.http

import android.widget.Toast
import kotlinx.coroutines.delay
import pt.up.feup.cpm.printerterminal.ShowScanInfo
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

var SERVER = "https://49c3-193-136-33-109.eu.ngrok.io"

fun readStream(input: InputStream): String {
    var reader: BufferedReader? = null
    var line: String?
    val response = StringBuilder()
    try {
        reader = BufferedReader(InputStreamReader(input))
        while (reader.readLine().also{ line = it } != null)
            response.append(line)
    }
    catch (e: IOException) {
        return "readStream: " + e.message
    }
    finally {
        reader?.close()
    }
    return response.toString()
}
class GetTransaction(val act: ShowScanInfo, val transactionID: String?): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            val str_url="$SERVER/transaction/get/$transactionID"
            println("ola")
            url = URL(str_url)
            println("GET " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doInput = true
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false
            val responseCode = urlConnection.responseCode
            if (responseCode == 200) {
                act.transactions_res = (readStream(urlConnection.inputStream))
                println("HELLLLLO")
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

class ChangeToPrinted(val act: ShowScanInfo, val id: String) : Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("$SERVER/transaction/getAndUpdate/$id")
            println("GET " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doInput = true
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false

            // get response
            val responseCode = urlConnection.responseCode
            if (responseCode == 200)
                act.printed_res = (readStream(urlConnection.inputStream))
            else
                println("Code: $responseCode")
        } catch (e: java.lang.Exception) {
            println(e.toString())
        } finally {
            urlConnection?.disconnect()
        }
    }
}

class GetCustomer(val act: ShowScanInfo,val userID: String): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("$SERVER/customer/getfromID/$userID")
            println("GET " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doInput = true
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false
            val responseCode = urlConnection.responseCode
            if (responseCode == 200)
                act.user_res = (readStream(urlConnection.inputStream))
            else
                println("Code: $responseCode")
        } catch (e: Exception) {
            println(e.toString())
        } finally {
            urlConnection?.disconnect()
        }
    }
}