package pt.up.feup.cpm.customerapp.utils

import java.io.*

var SERVER = "https://f415-89-153-186-47.eu.ngrok.io"

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

class GetCustomers(): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("http://10.0.2.2:3000/customer/get-all")
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

class GetProducts(): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("http://10.0.2.2:3000/product/get-all")
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
            url = URL("http://10.0.2.2:3000/customer/$userID")
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

class GetProduct(val act: Login, val productID: Product): Runnable {
    override fun run() {
        val url: URL
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL("http://10.0.2.2:3000/product/$productID")
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
            url = URL("http://127.0.0.1:3000/customer/register")
            act.writeText("POST " + url.toExternalForm())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doOutput = true
            urlConnection.doInput = true
            urlConnection.requestMethod = "POST"
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.useCaches = false
            val outputStream = DataOutputStream(urlConnection.outputStream)
            val body = "{ " +
                    "\"name\": \"${customer.getName()}\", " +
                    "\"address\": \"${customer.getAddress()}\", " +
                    "\"fiscalNumber\": \"${customer.getFiscalNumber()}\", " +
                    "\"email\": \"${customer.getEmail()}\", " +
                    "\"password\": \"${customer.getPassword()}\", " +
                    "\"cardType\": \"${customer.getCardType()}\", " +
                    "\"cardNumber\": \"${customer.getCardNumber()}\", " +
                    "\"cardValidity\": \"${customer.getCardValidity()}\"" + " }"
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

class LoginCustomer(val act: Login, val email: String, val password: String): Runnable {
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
            val body = "{ " +
                    "\"email\": \"$email\", " +
                    "\"password\": \"$password\"" + " }"
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