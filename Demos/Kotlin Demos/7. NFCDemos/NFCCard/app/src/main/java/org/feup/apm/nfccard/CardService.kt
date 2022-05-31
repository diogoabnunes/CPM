package org.feup.apm.nfccard

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import java.util.*

private const val LOYALTY_CARD_AID = "F222222222" // AID for this applet service.
private const val SELECT_APDU_HEADER = "00A40400" // SmartCard select AID command

class CardService : HostApduService() {
  private val SELECT_OK_SW = HexStringToByteArray("9000")     // "OK" status word (0x9000)
  private val UNKNOWN_CMD_SW = HexStringToByteArray("0000")   // "UNKNOWN" status word (0X0000)
  private val SELECT_APDU = BuildSelectApdu(LOYALTY_CARD_AID)    // the 'Select Aid x' APDU

  // return card account on Select command (with correct AID)
  override fun processCommandApdu(command: ByteArray, bundle: Bundle?): ByteArray {
    return if (Arrays.equals(SELECT_APDU, command)) {
      val accountBytes = AccountNrStore.getAccount(this).toByteArray()
      ConcatArrays(accountBytes, SELECT_OK_SW)
    }
    else
      UNKNOWN_CMD_SW
  }

  override fun onDeactivated(i: Int) {}

  // Format: [CLASS | INSTRUCTION | PARAMETER 1 | PARAMETER 2 | LENGTH | DATA]
  private fun BuildSelectApdu(aid: String): ByteArray {
    return HexStringToByteArray(SELECT_APDU_HEADER + String.format("%02X", aid.length / 2) + aid)
  }

  private fun HexStringToByteArray(s: String): ByteArray {
    val data = ByteArray(s.length / 2)
    var k = 0
    while (k < s.length) {
      data[k / 2] = ((Character.digit(s[k], 16) shl 4) + Character.digit(s[k + 1], 16)).toByte()
      k += 2
    }
    return data
  }

  private fun ConcatArrays(first: ByteArray, vararg others: ByteArray): ByteArray {
    var totalLen = first.size
    for (array in others) totalLen += array.size
    val result = Arrays.copyOf(first, totalLen)
    var offset = first.size
    for (array in others) {
      System.arraycopy(array, 0, result, offset, array.size)
      offset += array.size
    }
    return result
  }
}
