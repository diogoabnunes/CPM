package pt.up.feup.cpm.customerapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.security.KeyPairGeneratorSpec
import android.util.Base64
import android.widget.TextView
import pt.up.feup.cpm.customerapp.R
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import org.json.JSONObject
import pt.up.feup.cpm.customerapp.utils.*
import pt.up.feup.cpm.customerapp.http.AddCustomer
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.SecureRandom
import java.security.Signature
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*
import javax.crypto.Cipher
import javax.security.auth.x500.X500Principal

class Register : AppCompatActivity() {
    val name by lazy { findViewById<EditText>(R.id.name) }
    val address by lazy { findViewById<EditText>(R.id.address) }
    val fiscal_number by lazy { findViewById<EditText>(R.id.fiscal_number) }
    val email by lazy { findViewById<EditText>(R.id.email) }
    val password by lazy { findViewById<EditText>(R.id.password) }
    val card_type by lazy { findViewById<RadioGroup>(R.id.card_type) }
    val card_number by lazy { findViewById<EditText>(R.id.card_number) }
    val card_validity by lazy { findViewById<EditText>(R.id.card_validity) }
    val register_button by lazy { findViewById<Button>(R.id.register_button) }
    val tvResponse by lazy { findViewById<TextView>(R.id.tv_response) }
    var content = ByteArray(0)
    var result = ByteArray(0)
    var clear = ByteArray(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button.setOnClickListener {
            val selected = card_type.checkedRadioButtonId
            val type = if (selected == R.id.credit_card) "Credit"
            else "Debit"

            var js = JSONObject();
            js.accumulate("name", name.text.toString())
            js.accumulate("address", address.text.toString())
            js.accumulate("fiscalNumber", fiscal_number.text.toString())
            js.accumulate("email", email.text.toString())
            js.accumulate("password", password.text.toString())
            js.accumulate("cardType", type)
            js.accumulate("cardNumber", card_number.text.toString())
            js.accumulate("cardValidity", card_validity.text.toString())

            generateAndStoreKeys()
            js.accumulate("publicKey", getPubKey())

            Thread(AddCustomer(this, js.toString())).start()
        }
    }

    fun appendText(value: String) {
        runOnUiThread { tvResponse.text = tvResponse.text.toString() + "\n" + value }
    }

    fun writeText(value: String) {
        runOnUiThread { tvResponse.text = value }
    }

    private fun keysPresent(): Boolean {
        val entry = KeyStore.getInstance(Constants.ANDROID_KEYSTORE).run {
            load(null)
            getEntry(Constants.keyname, null)
        }
        return (entry !== null)
    }

    private fun generateAndStoreKeys(): Boolean {
        try {
            if (!keysPresent()) {
                val start = GregorianCalendar()
                val end = GregorianCalendar().apply { add(Calendar.YEAR, 10) }
                val kgen = KeyPairGenerator.getInstance(Constants.KEY_ALGO, Constants.ANDROID_KEYSTORE)
                val spec = KeyPairGeneratorSpec.Builder(this)
                    .setKeySize(Constants.KEY_SIZE)
                    .setAlias(Constants.keyname)
                    .setSubject(X500Principal("CN=" + Constants.keyname))
                    .setSerialNumber(BigInteger.valueOf(Constants.serialNr))
                    .setStartDate(start.time)
                    .setEndDate(end.time)
                    .build()
                kgen.initialize(spec)
                kgen.generateKeyPair()
            }
        }
        catch (ex: Exception) {
            println(ex.message)
            return false
        }
        return true
    }

    fun getPubKey(): PubKey {
        val pkey = PubKey(ByteArray(0), ByteArray(0))
        try {
            val entry = KeyStore.getInstance(Constants.ANDROID_KEYSTORE).run {
                load(null)
                getEntry(Constants.keyname, null)
            }
            val pub = (entry as KeyStore.PrivateKeyEntry).certificate.publicKey
            pkey.modulus = (pub as RSAPublicKey).getModulus().toByteArray()
            pkey.exponent = (pub as RSAPublicKey).getPublicExponent().toByteArray()
        }
        catch (ex: Exception) {
            println(ex.message)
        }
        return pkey
    }

    fun getPrivExp(): ByteArray {
        var exp = ByteArray(0)
        try {
            val entry = KeyStore.getInstance(Constants.ANDROID_KEYSTORE).run {
                load(null)
                getEntry(Constants.keyname, null)
            }
            val priv = (entry as KeyStore.PrivateKeyEntry).privateKey
            exp = (priv as RSAPrivateKey).getPrivateExponent().toByteArray()
        }
        catch (ex: Exception) {
            println(ex.message)
        }
        return exp
    }

    private fun showKeys(): String {
        val pkey = getPubKey()
        val privExp = getPrivExp()
        return String.format(InStrings.showKeysFormat, pkey.modulus.size, byteArrayToHex(pkey.modulus),
            byteArrayToHex(pkey.exponent), privExp.size, byteArrayToHex(privExp))
    }

    fun genRandomContent():String {
        content = ByteArray(20)
        SecureRandom().nextBytes(content)
        return String.format(InStrings.contentFormat , content.size, byteArrayToHex(content))
    }

    fun encryptContent(): String {
        if (content.isEmpty()) return ""
        clear = ByteArray(0)
        try {
            val entry = KeyStore.getInstance(Constants.ANDROID_KEYSTORE).run {
                load(null)
                getEntry(Constants.keyname, null)
            }
            val prKey = (entry as KeyStore.PrivateKeyEntry).privateKey
            val cipher: Cipher = Cipher.getInstance(Constants.ENC_ALGO)
            cipher.init(Cipher.ENCRYPT_MODE, prKey)
            result = cipher.doFinal(content)
            // display encrypted result
            return String.format(InStrings.encFormat , result.size, byteArrayToHex(result))
        }
        catch (e: Exception) {
            return e.message.toString()
        }
    }

    fun decryptResult(): String {
        if (content.isEmpty() || result.isEmpty()) return ""
        try {
            val entry = KeyStore.getInstance(Constants.ANDROID_KEYSTORE).run {
                load(null)
                getEntry(Constants.keyname, null)
            }
            val puKey = (entry as KeyStore.PrivateKeyEntry).certificate.publicKey
            val cipher: Cipher = Cipher.getInstance(Constants.ENC_ALGO)
            cipher.init(Cipher.DECRYPT_MODE, puKey)
            clear = cipher.doFinal(result)
            // display decrypted clear content
            return String.format(InStrings.decFormat , clear.size, byteArrayToHex(clear))
        }
        catch (e: Exception) {
            return e.message.toString()
        }
    }

    fun signContent(): String {
        if (content.size == 0) return ""
        clear = ByteArray(0)
        try {
            val entry = KeyStore.getInstance(Constants.ANDROID_KEYSTORE).run {
                load(null)
                getEntry(Constants.keyname, null)
            }
            val prKey = (entry as KeyStore.PrivateKeyEntry).privateKey
            val sg = Signature.getInstance(Constants.SIGN_ALGO)
            sg.initSign(prKey)
            sg.update(content)
            result = sg.sign()
            // display signature
            return String.format(InStrings.signFormat , result.size, byteArrayToHex(result))
        }
        catch  (e: Exception) {
            return e.message.toString()
        }
    }

    fun verifySignature(): String {
        if (content.isEmpty() || result.isEmpty()) return ""
        try {
            val entry = KeyStore.getInstance(Constants.ANDROID_KEYSTORE).run {
                load(null)
                getEntry(Constants.keyname, null)
            }
            val puKey = (entry as KeyStore.PrivateKeyEntry).certificate.publicKey
            val sg = Signature.getInstance(Constants.SIGN_ALGO)
            sg.initVerify(puKey)
            sg.update(content)
            val verified = sg.verify(result)
            return if (verified) "verified" else "INVALID!"
        }
        catch (e: Exception) {
            return e.message.toString()
        }
    }

    fun showCertificate(): String {
        clear()
        try {
            val entry = KeyStore.getInstance(Constants.ANDROID_KEYSTORE).run {
                load(null)
                getEntry(Constants.keyname, null)
            }
            val cert = (entry as KeyStore.PrivateKeyEntry).certificate
            val encCert = cert.encoded
            val strCert = cert.toString()
            val b64Cert = InStrings.beginCert + Base64.encodeToString(encCert, Base64.DEFAULT) + InStrings.endCert
            return String.format(InStrings.certFormat, encCert.size, byteArrayToHex(encCert),
                b64Cert.length, b64Cert, strCert)
        }
        catch (e: Exception) {
            return e.message.toString()
        }
    }

    fun clear() {
        content = ByteArray(0)
        result = ByteArray(0)
        clear = ByteArray(0)
    }
}

data class PubKey(var modulus: ByteArray, var exponent: ByteArray)

fun byteArrayToHex(ba: ByteArray): String {
    val sb = StringBuilder(ba.size * 2)
    for (b in ba) sb.append(String.format("%02x", b))
    return sb.toString()
}