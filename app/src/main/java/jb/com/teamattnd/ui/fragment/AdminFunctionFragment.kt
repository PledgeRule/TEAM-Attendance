package jb.com.teamattnd.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jb.com.teamattnd.R
import jb.com.teamattnd.util.Controller
import kotlinx.android.synthetic.main.fragment_admin_func.view.*

/**
 * Created by juhi.baranwal on 02-Aug-17.
 */

class AdminFunctionFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)

        var rootView: View = inflater?.inflate(R.layout.fragment_admin_func, container, false)!!
        loadUserView(rootView)
        return rootView

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun  loadUserView(view: View) {
        if(Controller.isAdmin){
            view.rl_adminFuncView.visibility  = View.VISIBLE
            view.tv_note.visibility = View.GONE
        }
        else
        {
            view.rl_adminFuncView.visibility  = View.GONE
            view.tv_note.visibility = View.VISIBLE
        }

    }
}