
package com.programardores.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.programardores.safeguardpro.R
import com.programardores.safeguardpro.databinding.FragmentCadastroEpiBinding
import com.programardores.safeguardpro.databinding.FragmentEpiTelaInicialBinding

class EpiTelaInicialFragment : Fragment() {
    private var _binding: FragmentEpiTelaInicialBinding? = null
    private val binding: FragmentEpiTelaInicialBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEpiTelaInicialBinding.inflate(inflater, container, false)
        return binding.root
    }
}