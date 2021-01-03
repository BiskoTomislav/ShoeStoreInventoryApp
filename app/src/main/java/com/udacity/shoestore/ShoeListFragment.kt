package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.LayoutShoeItemBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView")
        setHasOptionsMenu(true)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        // Fill ScrollView with shoe list
        viewModel.shoes.value?.forEach {
            val layoutShoeItemBinding: LayoutShoeItemBinding =
                DataBindingUtil.inflate(inflater, R.layout.layout_shoe_item, container, false)
            addShoeItemLayoutIntoScrollView(layoutShoeItemBinding, it)
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

            // Remove shoe list from stack after logout,
            // otherwise we could return with back button without login.
            Navigation.findNavController(binding.root).popBackStack(R.id.shoeListFragment, true)

        }
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun addShoeItemLayoutIntoScrollView(
        layoutShoeItemBinding: LayoutShoeItemBinding,
        shoe: Shoe
    ) {
        with(layoutShoeItemBinding) {
            shoeNameTextView.text = shoe.name
            shoeCompanyTextView.text = shoe.company
            shoeSizetextView.text = shoe.size.toString()
            shoeDescriptionTextView.text = shoe.description

            binding.shoeListLinearLayout.addView(this.root)
        }
    }
}