package pt.up.feup.cpm.customerapp.utils

import pt.up.feup.cpm.customerapp.activities.*
import pt.up.feup.cpm.customerapp.models.*
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

private fun readStream(input: InputStream): String {
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

class GetCustomers(val act: Login): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("http://localhost:3000/customer/list")
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
            url = URL("http://localhost:3000/customer/$userID")
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

class AddCustomer(val act: Register, val customer: Customer) : Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("http://10.0.2.2:3000/customer/add")
            act.writeText("POST " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doOutput = true
            urlConnection.doInput = true
            urlConnection.requestMethod = "POST"
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false
            val outputStream = DataOutputStream(urlConnection.outputStream)
            val payload: String? = "\"{" +
                    "name: \"${customer.getName()}\"," +
                    "address: \"${customer.getAddress()}\"," +
                    "fiscalNumber: \"${customer.getFiscalNumber()}\"," +
                    "email: \"${customer.getEmail()}\"," +
                    "password: \"${customer.getPassword()}\"," +
                    "cardType: \"${customer.getCardType()}\"," +
                    "cardNumber: \"${customer.getCardNumber()}\"," +
                    "cardValidity: \"${customer.getCardValidity()}\"" + "}\""
            act.appendText("payload: $payload")
            outputStream.writeBytes(payload)
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

class LoginCustomer(val act: Login, val email: String, password: String): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("http://localhost:3000/customer/login")
            System.out.println("POST " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doOutput = true
            urlConnection.doInput = true
            urlConnection.requestMethod = "POST"
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false
            val outputStream = DataOutputStream(urlConnection.outputStream)
            val payload = "\"" + email + "\""
            System.out.println("payload: $payload")
            outputStream.writeBytes(payload)
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