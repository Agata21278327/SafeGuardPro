package com.programardores.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.programardores.safeguardpro.R
import com.programardores.safeguardpro.databinding.FragmentEntregasEpiBinding
import com.programardores.safeguardpro.databinding.FragmentFuncionariosBinding
import com.programardores.safeguardpro.view.adapter.FuncionarioAdapter
import com.programardores.safeguardpro.viewmodel.FuncionarioCadastroViewModel


class FuncionariosFragment : Fragment() {

    //Chamar a viewmodel
    private val viewModel: FuncionarioCadastroViewModel by viewModels()

    // Chamar o adapter
    private lateinit var adapter: FuncionarioAdapter

    private var _binding: FragmentFuncionariosBinding? = null
    private val binding: FragmentFuncionariosBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFuncionariosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Quando clicar em algum item da lista
        adapter = FuncionarioAdapter(viewModel.funcionarioList.value){funcionario ->
            val funcionarioBundle = Bundle()
            funcionarioBundle.putInt("funcionarioId", funcionario.id)
            arguments = funcionarioBundle
            findNavController().navigate(R.id.funcionarioCadastroFragment, arguments)
        }

        // Configura a recycler
        val recycler = binding.rvFuncionarios
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        // Observa para adicionar um item na lista quando for adiconado
        viewModel.funcionarioList.observe(viewLifecycleOwner) {
            adapter.updateFuncionario(it)
        }


        // Navegar para a tela de cadastro de funcionario
        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.funcionarioCadastroFragment)
        }

        // Carregar as funcionarios cadastradas
        viewModel.load()
    }
}