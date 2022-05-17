package pt.up.feup.cpm.customerapp.models

import androidx.constraintlayout.motion.widget.TransitionBuilder
import java.io.Serializable
import kotlin.collections.ArrayList

class Transaction(
    var transactionID: String? = null,
    var userID: String? = null,
    var content: MutableList<TransactionItem> = mutableListOf(),
    //private var date: Date? = null
    var date: String? = null,
    var printed: Boolean = false
): Serializable