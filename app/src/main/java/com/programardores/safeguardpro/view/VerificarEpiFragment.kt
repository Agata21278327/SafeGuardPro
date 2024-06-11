package com.programardores.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.programardores.safeguardpro.R
import com.programardores.safeguardpro.databinding.FragmentCadastroEpiBinding
import com.programardores.safeguardpro.databinding.FragmentVerificarEpiBinding


class VerificarEpiFragment : Fragment() {
    private var _binding: FragmentVerificarEpiBinding? = null
    private val binding: FragmentVerificarEpiBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVerificarEpiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.tvNome.text = "Nome do EPI:"
        var nome = binding.edtUsuario.editableText.toString()
        binding.tvNome.text = nome

        binding.btnConfimar.setOnClickListener {

        }
    }
}