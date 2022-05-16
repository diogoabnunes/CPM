package pt.up.feup.cpm.customerapp.http

import pt.up.feup.cpm.customerapp.activities.Home
import pt.up.feup.cpm.customerapp.activities.ShowScanInfo
import java.net.HttpURLConnection
import java.net.URL

class GetProducts(val result: Home): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("$SERVER/product/get-all")
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

class GetProduct(val act: ShowScanInfo, val productID: String?): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("$SERVER/product/get/$productID")
            println("GET " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doInput = true
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false
            val responseCode = urlConnection.responseCode
            if (responseCode == 200)
                act.writeText(readStream(urlConnection.inputStream))
            else
                println("Code: $responseCode")
        } catch (e: Exception) {
            println(e.toString())
        } finally {
            urlConnection?.disconnect()
        }
    }
}