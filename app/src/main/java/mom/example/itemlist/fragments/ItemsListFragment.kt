package mom.example.itemlist.fragments

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mom.example.itemlist.MainActivity
import mom.example.itemlist.R
import mom.example.itemlist.adapters.ItemListRecycleViewAdapter

class ItemsListFragment : BaseFragment() {
    override val fragmentLayoutReference = R.layout.fragment_item_list

    init {
        fragmentContainerId = R.id.mainFragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragmentLayoutReference, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.itemList)
        val adapter = ItemListRecycleViewAdapter(activity as Context, (activity as MainActivity).items)
        val layoutManager = LinearLayoutManager(activity as Context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}
