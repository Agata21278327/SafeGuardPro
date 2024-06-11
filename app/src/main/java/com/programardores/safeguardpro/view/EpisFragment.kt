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
import com.programardores.safeguardpro.databinding.FragmentEpisBinding
import com.programardores.safeguardpro.view.adapter.EpiAdapter
import com.programardores.safeguardpro.viewmodel.EpiCadastroViewModel

class EpisFragment : Fragment() {

    //Chamar a viewmodel
    private val viewModel: EpiCadastroViewModel by viewModels()

    // Chamar o adapter
    private lateinit var adapter: EpiAdapter

    private var _binding: FragmentEpisBinding? = null
    private val binding: FragmentEpisBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEpisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Quando clicar em algum item da lista
        adapter = EpiAdapter(viewModel.epiList.value){epi ->
            val epiBundle = Bundle()
            epiBundle.putInt("epiId", epi.id)
            arguments = epiBundle
            findNavController().navigate(R.id.cadastroEpiFragment, arguments)
        }

        // Configura a recycler
        val recycler = binding.rvEpis
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        // Observa para adicionar um item na lista quando for adiconado
        viewModel.epiList.observe(viewLifecycleOwner) {
            adapter.updateEpi(it)
        }


        // Navegar para a tela de cadastro de epi
        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.cadastroEpiFragment)
        }

        // Carregar as epis cadastradas
        viewModel.load()
    }
}