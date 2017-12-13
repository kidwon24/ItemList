package mom.example.itemlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import mom.example.itemlist.fragments.ItemsListFragment
import mom.example.itemlist.model.Pokemon

class MainActivity : AppCompatActivity() {

    val items = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.items.addAll(AppHelper.parsePokemonList(this))

        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val itemsListFragment = ItemsListFragment()
        itemsListFragment.load(anActivity = this)
    }
}
