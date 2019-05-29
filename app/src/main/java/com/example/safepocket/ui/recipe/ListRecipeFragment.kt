package com.example.safepocket.ui.recipe

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.safepocket.R
import com.example.safepocket.db.AppDatabase
import com.example.safepocket.utlis.currencyFormat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RecipeListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 *
 */
class RecipeListFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private var appDatabase: AppDatabase? = null
    private lateinit var mContext: Context
    val TAG = "RecipeListFragment"
    lateinit var viewModel: RecipeViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        appDatabase = AppDatabase.getInstance(mContext)
        viewModel = ViewModelProviders.of(this).get(RecipeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        //Change amount textview
        activity?.findViewById<Toolbar>(R.id.toolbar)?.visibility = GONE
        val amount = currencyFormat(viewModel.totalAmount())
        view.findViewById<TextView>(R.id.total_amount).setText(amount)

        view.findViewById<FloatingActionButton>(R.id.add_recipe).setOnClickListener {
            val intent = Intent(mContext, AddRecipeActivity::class.java)
            startActivity(intent)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            view.findViewById<TextView>(R.id.description).setDescriptionText()
        }

        val recipeAdapter = RecipeAdapter()
        recyclerView.layoutManager = LinearLayoutManager(mContext)
        recyclerView.adapter = recipeAdapter

        viewModel.getRecipes().observe(this, Observer {listRecipes->
            listRecipes?.let { recipeAdapter.setRecipes(it) }
            Log.d(TAG, "${listRecipes}")

        })
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "attached")
        mContext = context
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun TextView.setDescriptionText() {
        val mois = LocalDate.now().month
        Log.d(TAG, mois.toString())
        when {
            mois.toString().startsWith("A") ->
                this.setText(getString(R.string.description).replace("de *mois*", "d'${mois}"))

        }
    }
}
