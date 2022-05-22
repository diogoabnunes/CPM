package pt.up.feup.cpm.customerapp.utils

object InStrings {
    const val beginCert = "-----BEGIN CERTIFICATE-----\n"
    const val endCert = "-----END CERTIFICATE-----\n"
    const val showKeysFormat = "Modulus(%d):\n%s\nExponent: %s\nPrivate Exponent(%d):\n%s"
    const val contentFormat = "Content(%d):\n%s"
    const val encFormat = "Encrypted(%d):\n%s"
    const val decFormat = "Decrypted(%s):\n%s"
    const val signFormat = "Signature(%s):\n%s"
    const val certFormat = "(DER:%d):\n%s\n\nPEM(b64:%d):\n%s\n%s"
}