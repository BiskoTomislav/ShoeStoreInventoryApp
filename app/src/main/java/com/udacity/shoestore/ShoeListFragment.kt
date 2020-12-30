package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.NoUser
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onResume() {
        super.onResume()
        Timber.d("onResume ${findNavController()} with user ${ShoeApp.user}")

        // Jump to login fragment if user is not logged in
        when (ShoeApp.user) {
            is NoUser -> findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView")
        setHasOptionsMenu(true)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        // Fill ScrollView with shoe list
        viewModel.shoes.value?.forEach {
            val view: LinearLayout =
                inflater.inflate(R.layout.layout_shoe_item, container, false) as LinearLayout
            addShoeItemLayoutIntoScrollView(view, it)
        }

        // Navigation to adding a new shoe
        binding.addNewItemFloatingActionButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToAddShoeFragment())
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (R.id.loginFragment == item.itemId) {
            ShoeApp.logout()
        }
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun addShoeItemLayoutIntoScrollView(
        view: LinearLayout,
        it: Shoe
    ) {
        val nameTextView = view.findViewById<TextView>(R.id.shoeName_textView)
        nameTextView.text = it.name

        val companyTextView = view.findViewById<TextView>(R.id.shoeCompany_textView)
        companyTextView.text = it.company

        val sizeTextView = view.findViewById<TextView>(R.id.shoeSizetextView)
        Timber.d("Add size: ${it.size.toString()}")
        sizeTextView.text = it.size.toString()

        val descriptionTextView = view.findViewById<TextView>(R.id.shoeDescription_textView)
        descriptionTextView.text = it.description

        binding.shoeListLinearLayout.addView(view)
    }
}