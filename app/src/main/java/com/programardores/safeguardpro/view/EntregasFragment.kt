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
import com.programardores.safeguardpro.databinding.FragmentEntregasBinding
import com.programardores.safeguardpro.view.adapter.EntregaAdapter
import com.programardores.safeguardpro.viewmodel.EntregaCadastroViewModel


class EntregasFragment : Fragment() {
    //Chamar a viewmodel
    private val viewModel: EntregaCadastroViewModel by viewModels()

    // Chamar o adapter
    private lateinit var adapter: EntregaAdapter

    private var _binding: FragmentEntregasBinding? = null
    private val binding: FragmentEntregasBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntregasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Quando clicar em algum item da lista
        adapter = EntregaAdapter(viewModel.entregaList.value){entrega ->
            val entregaBundle = Bundle()
            entregaBundle.putInt("entregaId", entrega.id)
            arguments = entregaBundle
            findNavController().navigate(R.id.entregasEpiFragment, arguments)
        }

        // Configura a recycler
        val recycler = binding.rvEntregas
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        // Observa para adicionar um item na lista quando for adiconado
        viewModel.entregaList.observe(viewLifecycleOwner) {
            adapter.updateEntrega(it)
        }


        // Navegar para a tela de cadastro de entrega
        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.entregasEpiFragment)
        }

        // Carregar as entregas cadastradas
        viewModel.load()
    }

}