package pt.up.feup.cpm.customerapp.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.models.Transaction
import pt.up.feup.cpm.customerapp.models.TransactionItem

class TransactionItemAdapter(var mCtx: Context, var resources: Int, var items: List<Transaction>):ArrayAdapter<Transaction>(mCtx, resources, items){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(resources, null)

        val id : TextView = view.findViewById(R.id.transaction_id)
        val date: TextView = view.findViewById(R.id.date)
        val price: TextView = view.findViewById(R.id.price)

        var mItem:Transaction = items[position]
        id.text = "Transaction" + " #" + mItem.transactionID.toString().take(4).uppercase() + mItem.transactionID.toString().takeLast(4).uppercase()
        date.text = mItem.date.toString().take(10) + "  " + (mItem.date.toString().takeLast(13)).take(5)
        price.text = calculateTotal(mItem.content) + "€"
        return view
    }

    private fun calculateTotal(transactionItems: MutableList<TransactionItem>): String {
        var total = 0.0
        for (transactionItem in transactionItems ) {
            val price = transactionItem.product?.price
            val quanti = transactionItem.quantity
            val num = price?.times(quanti!!)
            if (num != null) {
                total += num
            }
        }
        return "%.2f".format(total)
    }
}