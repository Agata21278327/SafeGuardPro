package com.programardores.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.programardores.safeguardpro.R
import com.programardores.safeguardpro.databinding.FragmentCadastroEpiBinding
import com.programardores.safeguardpro.databinding.FragmentTelaFuncionarioBinding


class TelaFuncionarioFragment : Fragment() {
    private var _binding: FragmentTelaFuncionarioBinding? = null
    private val binding: FragmentTelaFuncionarioBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTelaFuncionarioBinding.inflate(inflater, container, false)
        return binding.root
    }
}