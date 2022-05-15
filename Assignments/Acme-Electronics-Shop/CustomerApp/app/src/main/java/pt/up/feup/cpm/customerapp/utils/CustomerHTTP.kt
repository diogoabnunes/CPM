package pt.up.feup.cpm.customerapp.utils

import org.json.JSONObject
import pt.up.feup.cpm.customerapp.activities.Login
import pt.up.feup.cpm.customerapp.activities.Register
import pt.up.feup.cpm.customerapp.models.Customer
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL

class GetCustomers(): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL(SERVER + "/customer/get-all")
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

class GetCustomer(val act: Login, val userID: Customer): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL(SERVER + "/customer/$userID")
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

class AddCustomer(val act: Register, val body: String) : Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("$SERVER/customer/register")
            act.writeText("POST " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doOutput = true
            urlConnection.doInput = true
            urlConnection.requestMethod = "POST"
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false
            val outputStream = DataOutputStream(urlConnection.outputStream)
            act.appendText("body: $body")
            outputStream.writeBytes(body)
            outputStream.flush()
            outputStream.close()

            // get response
            val responseCode = urlConnection.responseCode
            if (responseCode == 201)
                act.appendText(readStream(urlConnection.inputStream))
            else
                act.appendText("Code: $responseCode")
        }
        catch (e: java.lang.Exception) {
            act.appendText(e.toString())
        }
        finally {
            urlConnection?.disconnect()
        }
    }
}

class LoginCustomer(val act: Login, val body: String): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("$SERVER/customer/login")
            System.out.println("POST " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doOutput = true
            urlConnection.doInput = true
            urlConnection.requestMethod = "POST"
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false
            val outputStream = DataOutputStream(urlConnection.outputStream)
            act.appendText("body: $body")
            outputStream.writeBytes(body)
            outputStream.flush()
            outputStream.close()

            // get response
            val responseCode = urlConnection.responseCode
            if (responseCode == 200)
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