package com.programardores.safeguardpro.view

import android.app.AlertDialog
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
import com.programardores.safeguardpro.databinding.FragmentFuncionarioCadastroBinding
import com.programardores.safeguardpro.service.model.Epi
import com.programardores.safeguardpro.viewmodel.EpiCadastroViewModel
import java.time.LocalDateTime

class CadastroEpiFragment : Fragment() {
    private val viewModel: EpiCadastroViewModel by viewModels()

    private var _binding: FragmentCadastroEpiBinding? = null
    private val binding: FragmentCadastroEpiBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCadastroEpiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Carregar o epi caso tenha selecionado
        arguments?.let {
            viewModel.getEpi(it.getInt("epiId"))
        }

        binding.btnConfirmar.setOnClickListener {
            var nome = binding.edtNome.editableText.toString()
            var validade = binding.edtData.editableText.toString()
            var descricao = binding.tvDesc.editableText.toString()
            if (nome != "" && validade != "" && descricao != "") {

                val epi = Epi(
                    nome = nome,
                    validade = validade,
                    descricao = descricao
                )

                viewModel.epi.value?.let {
                    epi.id = it.id
                    viewModel.update(epi)

                } ?: run {
                    viewModel.insert(epi)
                }

                binding.edtNome.editableText.clear()
                binding.edtData.editableText.clear()
                binding.edtDesc.editableText.clear()
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnDeletar.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Exclusão de pessoa")
                .setMessage("Você realmente deseja excluir?")
                .setPositiveButton("Sim"){ _,_ ->
                    viewModel.delete(viewModel.epi.value?.id ?: 0)
                    findNavController().navigateUp()
                }
                .setNegativeButton("Não"){_,_ ->}
                .show()
        }

        viewModel.epi.observe(viewLifecycleOwner){epi ->
            binding.edtNome.setText(epi.nome)
            binding.edtData.setText(epi.validade)
            binding.edtDesc.setText(epi.descricao)

            binding.btnDeletar.visibility = View.VISIBLE
        }
    }

}