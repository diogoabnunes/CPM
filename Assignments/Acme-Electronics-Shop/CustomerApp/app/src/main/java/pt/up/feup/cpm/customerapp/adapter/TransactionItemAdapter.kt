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

class TransactionItemAdapter(var mCtx: Context, var resources: Int, var items: List<Transaction>):ArrayAdapter<Transaction>(mCtx, resources, items){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(resources, null)

        val id : TextView = view.findViewById(R.id.transaction_id)
        val date: TextView = view.findViewById(R.id.date)
        val price: TextView = view.findViewById(R.id.price)

        var mItem:Transaction = items[position]
        id.text = mItem.getTransactionId()
        date.text = mItem.getDate()
        price.text = mItem.getPrice()
        return view
    }
}