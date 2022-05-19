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

        val name : TextView = view.findViewById(R.id.productName)
        val quantity: TextView = view.findViewById(R.id.quantity1)
        val price: TextView = view.findViewById(R.id.pricee)

        var mItem: TransactionItem = items[position]
        name.text = mItem.product?.name.toString()
        quantity.text = " " + mItem.quantity.toString()
        price.text = mItem.product?.price.toString() + "€"


        return view
    }
}