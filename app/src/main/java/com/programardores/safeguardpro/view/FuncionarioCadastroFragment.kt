package com.programardores.safeguardpro.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.programardores.safeguardpro.R
import com.programardores.safeguardpro.databinding.FragmentCadastroEpiBinding
import com.programardores.safeguardpro.databinding.FragmentFuncionarioCadastroBinding

class FuncionarioCadastroFragment : Fragment() {
    private var _binding: FragmentFuncionarioCadastroBinding? = null
    private val binding: FragmentFuncionarioCadastroBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFuncionarioCadastroBinding.inflate(inflater, container, false)
        return binding.root


    }
}

