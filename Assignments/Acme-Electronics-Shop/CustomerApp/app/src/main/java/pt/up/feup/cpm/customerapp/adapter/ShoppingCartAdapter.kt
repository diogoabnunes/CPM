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


        //ADICIONA QUANTIDADE
        addQuantity.setOnClickListener(View.OnClickListener {
            mItem.addQuantity()
            quantity.text = mItem.getQuantity().toString()
            //adicionar func para atualizar o preço final
            notifyDataSetChanged()
        })

        subtractQuantity.setOnClickListener(View.OnClickListener {
            mItem.removeQuantity()
            quantity.text = mItem.getQuantity().toString()
            //adicionar func para atualizar o preço final
            notifyDataSetChanged()
        })

        removeItem.setOnClickListener(View.OnClickListener {
            items.drop(position)
            notifyDataSetChanged()
        })

        quantity.text = mItem.getQuantity().toString()
        name.text = mItem.getProduct()?.getName()
        price.text = mItem.getProduct()?.getPrice().toString() + "€"
        return view
    }
}