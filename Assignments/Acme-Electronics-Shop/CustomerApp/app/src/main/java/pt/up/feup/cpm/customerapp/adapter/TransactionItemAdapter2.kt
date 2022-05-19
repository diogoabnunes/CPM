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

class TransactionItemAdapter2(var mCtx: Context, var resources: Int, var items: List<TransactionItem>):ArrayAdapter<TransactionItem>(mCtx, resources, items){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(resources, null)

        val name : TextView = view.findViewById(R.id.transaction_id)
        val date: TextView = view.findViewById(R.id.date)
        val price: TextView = view.findViewById(R.id.price)

        var mItem: TransactionItem = items[position]
        name.text = mItem.product?.name.toString()
        price.text = mItem.product?.price.toString()
        date.text = mItem.quantity.toString()
        return view
    }
}