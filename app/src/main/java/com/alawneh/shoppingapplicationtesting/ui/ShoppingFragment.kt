package com.alawneh.shoppingapplicationtesting.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alawneh.shoppingapplicationtesting.R
import com.alawneh.shoppingapplicationtesting.databinding.FragmentShoppingBinding
import com.alawneh.shoppingapplicationtesting.viewmodels.ShoppingViewModel

class ShoppingFragment : Fragment() {

    private lateinit var binding: FragmentShoppingBinding
    private lateinit var viewModel: ShoppingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shopping, container, false)
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