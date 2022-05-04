package org.feup.apm.callhttp

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

//**************************************************************************
// classes to call REST operation GetUsers
class GetUsers(val act: MainActivity, val baseAddress: String) : Runnable {
  override fun run() {
    val url: URL
    var urlConnection: HttpURLConnection? = null
    try {
      url = URL("http://$baseAddress:8701/Rest/users")
      act.writeText("GET " + url.toExternalForm())
      urlConnection = url.openConnection() as HttpURLConnection
      urlConnection.doInput = true
      urlConnection.setRequestProperty("Content-Type", "application/json")
      urlConnection.useCaches = false
      val responseCode = urlConnection.responseCode
      if (responseCode == 200)
        act.appendText(readStream(urlConnection.inputStream))
      else
        act.appendText("Code: $responseCode")
    } catch (e: Exception) {
      act.appendText(e.toString())
    } finally {
      urlConnection?.disconnect()
    }
  }
}

//**************************************************************************
// Class to call REST operation GetUser
class GetUser(val act: MainActivity, val baseAddress: String, val userId: String) : Runnable {
  override fun run() {
    val url: URL
    var urlConnection: HttpURLConnection? = null
    try {
      url = URL("http://$baseAddress:8701/Rest/users/$userId")
      act.writeText("GET " + url.toExternalForm())
      urlConnection = url.openConnection() as HttpURLConnection
      urlConnection.doInput = true
      urlConnection.setRequestProperty("Content-Type", "application/json")
      urlConnection.useCaches = false
      val responseCode = urlConnection.responseCode
      if (responseCode == 200)
        act.appendText(readStream(urlConnection.inputStream))
      else
        act.appendText("Code: $responseCode")
    }
    catch (e: Exception) {
      act.appendText(e.toString())
    }
    finally {
      urlConnection?.disconnect()
    }
  }
}

//**************************************************************************
// Class to call REST operation AddUser
class AddUser(val act: MainActivity, val baseAddress: String, val userName: String) : Runnable {
  override fun run() {
    val url: URL
    var urlConnection: HttpURLConnection? = null
    try {
      url = URL("http://$baseAddress:8701/Rest/users")
      act.writeText("POST " + url.toExternalForm())
      urlConnection = url.openConnection() as HttpURLConnection
      urlConnection.doOutput = true
      urlConnection.doInput = true
      urlConnection.requestMethod = "POST"
      urlConnection.setRequestProperty("Content-Type", "application/json")
      urlConnection.useCaches = false
      val outputStream = DataOutputStream(urlConnection.outputStream)
      val payload = "\"" + userName + "\""
      act.appendText("payload: $payload")
      outputStream.writeBytes(payload)
      outputStream.flush()
      outputStream.close()

      // get response
      val responseCode = urlConnection.responseCode
      if (responseCode == 200)
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

//**************************************************************************
// Class to call REST operation DeleteUser
class DelUser(val act: MainActivity, val baseAddress: String, val userId: String) : Runnable {
  override fun run() {
    val url: URL
    var urlConnection: HttpURLConnection? = null
    try {
      url = URL("http://$baseAddress:8701/Rest/users/$userId")
      act.writeText("DELETE " + url.toExternalForm())
      urlConnection = url.openConnection() as HttpURLConnection
      urlConnection.requestMethod = "DELETE"
      urlConnection.setRequestProperty("Content-Type", "application/json")
      urlConnection.useCaches = false

      // get response
      val responseCode = urlConnection.responseCode
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

//**************************************************************************
// Class to call REST operation ChangeUser
class ChUser(val act: MainActivity, val baseAddress: String, val userId: String, val userName: String) :  Runnable {
  override fun run() {
    val url: URL
    var urlConnection: HttpURLConnection? = null
    try {
      url = URL("http://$baseAddress:8701/Rest/users/$userId")
      act.writeText("PUT " + url.toExternalForm())
      urlConnection = url.openConnection() as HttpURLConnection
      urlConnection.doOutput = true
      urlConnection.requestMethod = "PUT"
      urlConnection.setRequestProperty("Content-Type", "application/json")
      urlConnection.useCaches = false
      val outputStream = DataOutputStream(urlConnection.outputStream)
      val payload = "\"" + userName + "\""
      act.appendText("payload: $payload")
      outputStream.writeBytes(payload)
      outputStream.flush()
      outputStream.close()

      // get response
      val responseCode = urlConnection.responseCode
      act.appendText("Code: $responseCode")
    } catch (e: java.lang.Exception) {
      act.appendText(e.toString())
    } finally {
      urlConnection?.disconnect()
    }
  }
}
