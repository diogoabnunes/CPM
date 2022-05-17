package pt.up.feup.cpm.printerterminal.http

import pt.up.feup.cpm.printerterminal.ShowScanInfo
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

var SERVER = "https://a561-89-153-186-47.eu.ngrok.io"

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

class GetTransaction(val act: ShowScanInfo, val transactionID: String): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("$SERVER/transaction/get/$transactionID")
            System.out.println("GET " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doInput = true
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false
            val responseCode = urlConnection.responseCode
            if (responseCode == 200)
                act.writeText((readStream(urlConnection.inputStream)))
            else
                System.out.println("Code: $responseCode")
        } catch (e: Exception) {
            System.out.println(e.toString())
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