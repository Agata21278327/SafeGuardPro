package com.programardores.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.programardores.safeguardpro.R
import com.programardores.safeguardpro.databinding.FragmentCadastroEpiBinding
import com.programardores.safeguardpro.databinding.FragmentLoginTelaBinding
import com.programardores.safeguardpro.service.model.Login
import com.programardores.safeguardpro.viewmodel.FuncionarioCadastroViewModel


class LoginTelaFragment : Fragment() {
    private val viewModelFuncionario: FuncionarioCadastroViewModel by viewModels()

    private var _binding: FragmentLoginTelaBinding? = null
    private val binding: FragmentLoginTelaBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginTelaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var senha = ""
        var cpf = ""

        binding.btnEntrar.setOnClickListener {
            cpf = binding.edtUsuario.editableText.toString()
            senha = binding.edtSenha.editableText.toString()

            if ((cpf.isBlank() || cpf.isEmpty()) || (senha.isBlank() || senha.isEmpty())) {
                Toast.makeText(requireContext(), "Preencha os campos", Toast.LENGTH_LONG).show()
            } else {
                viewModelFuncionario.getFuncionarioByCpf(cpf)
            }
        }

        viewModelFuncionario.funcionario.observe(viewLifecycleOwner) {
            if (it.senha == senha && it.cpf == cpf){
                Login.userConected(it.id, it.cpf, it.admin)

                if (it.admin) {
                    findNavController().navigate(R.id.telaGestorFragment)
                } else {
                    findNavController().navigate(R.id.telaFuncionarioFragment)
                }
            } else {
                Toast.makeText(requireContext(), "Usuario ou senha inválidos", Toast.LENGTH_LONG).show()
            }
        }
    }
}