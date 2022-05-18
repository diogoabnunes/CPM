package pt.up.feup.cpm.printerterminal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import pt.up.feup.cpm.printerterminal.models.TransactionItem


class InfoAdapter(var mCtx: Context, var resources: Int, var items: List<TransactionItem>):
    ArrayAdapter<TransactionItem>(mCtx, resources, items){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)

        val name : TextView = view.findViewById(R.id.productName)
        val quantity: TextView = view.findViewById(R.id.quantity)
        val price: TextView = view.findViewById(R.id.price)

        val mItem: TransactionItem = items[position]

        quantity.text = mItem.quantity.toString()
        name.text = mItem.product?.name.toString()
        price.text = mItem.product?.price.toString() + "€"

        return view
    }
}