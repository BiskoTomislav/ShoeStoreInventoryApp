package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentAddShoeBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class AddShoeFragment : Fragment() {

    private lateinit var binding: FragmentAddShoeBinding

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    private val viewModel: ShoeListViewModel by activityViewModels()

    private val newShoe: Shoe = Shoe()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView")

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_shoe, container, false)

        binding.newShoe = newShoe
        binding.addButton.setOnClickListener {

            viewModel.addNewItem(newShoe)

            // Use findNavController().navigateUp() instead of creating a new instance of ShoeListFragment.
            findNavController().navigateUp()
        }


        binding.cancelButton.setOnClickListener {
            // Use findNavController().navigateUp() instead of creating a new instance of ShoeListFragment.
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}