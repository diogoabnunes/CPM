package pt.up.feup.cpm.customerapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import pt.up.feup.cpm.customerapp.R
import pt.up.feup.cpm.customerapp.models.TransactionItem


class ShoppingCartAdapter(var mCtx: Context, var resources: Int, var items: List<TransactionItem>):
    ArrayAdapter<TransactionItem>(mCtx, resources, items){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)

        val name : TextView = view.findViewById(R.id.productName)
        val quantity: TextView = view.findViewById(R.id.quantity)
        val price: TextView = view.findViewById(R.id.price)

        val subtractQuantity = view.findViewById(R.id.decrease_btn) as TextView
        val addQuantity = view.findViewById(R.id.increase_btn) as TextView
        val removeItem = view.findViewById(R.id.remove_btn) as TextView


        var mItem: TransactionItem = items[position]

        addQuantity.setOnClickListener {
            mItem.addQuantity()
            quantity.text = mItem.quantity.toString()
            //adicionar func para atualizar o preço final
            notifyDataSetChanged()
        }

        subtractQuantity.setOnClickListener {
            mItem.removeQuantity()
            quantity.text = mItem.quantity.toString()
            //adicionar func para atualizar o preço final
            notifyDataSetChanged()
        }

        //removeItem.setOnClickListener(View.OnClickListener {
        //    items.remove(position)
        //    notifyDataSetChanged()
        //})

        quantity.text = mItem.quantity.toString()
        name.text = mItem.product?.name.toString()
        price.text = mItem.product?.price.toString() + "€"

        return view
    }
}