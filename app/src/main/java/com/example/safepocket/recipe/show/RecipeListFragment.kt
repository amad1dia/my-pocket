package com.example.safepocket.recipe.show

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.safepocket.R
import com.example.safepocket.database.AppDatabase
import com.example.safepocket.recipe.add.AddRecipeActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        appDatabase = AppDatabase.getInstance(mContext)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        view.findViewById<FloatingActionButton>(R.id.add_recipe).setOnClickListener {
            val intent = Intent(mContext, AddRecipeActivity::class.java)
            startActivity(intent)
        }

        val listRecipes = appDatabase?.recipeDao()?.list()
        Log.d(TAG, "${listRecipes}")
        val recipeAdapter = listRecipes?.let { RecipeAdapter(it) }
        recyclerView.layoutManager = LinearLayoutManager(mContext)
        recyclerView.adapter = recipeAdapter

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

}
