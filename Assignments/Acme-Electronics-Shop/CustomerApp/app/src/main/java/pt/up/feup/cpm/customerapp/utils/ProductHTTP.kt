package pt.up.feup.cpm.customerapp.utils

import pt.up.feup.cpm.customerapp.activities.Home
import pt.up.feup.cpm.customerapp.activities.ShoppingCart
import pt.up.feup.cpm.customerapp.models.Product
import java.net.HttpURLConnection
import java.net.URL

class GetProducts(val result: Home): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("$SERVER/product/get-all")
            result.writeText("GET " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doInput = true
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false
            val responseCode = urlConnection.responseCode
            if (responseCode == 200)
                result.writeText(readStream(urlConnection.inputStream))
            else
                result.writeText("Code: $responseCode")
        } catch (e: Exception) {
            result.writeText(e.toString())
        } finally {
            urlConnection?.disconnect()
        }
    }
}

class GetProduct(val productID: Product): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("$SERVER/product/$productID")
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