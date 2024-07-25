package com.alawneh.shoppingapplicationtesting.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alawneh.shoppingapplicationtesting.R
import com.alawneh.shoppingapplicationtesting.databinding.FragmentAddShoppingItemBinding
import com.alawneh.shoppingapplicationtesting.viewmodels.ShoppingViewModel

class AddShoppingFragment : Fragment() {
    private lateinit var binding: FragmentAddShoppingItemBinding
    private lateinit var viewModel: ShoppingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_shopping_item, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariables()
    }

    private fun initVariables() {
        viewModel = ViewModelProvider(requireActivity())[ShoppingViewModel::class.java]
    }
}