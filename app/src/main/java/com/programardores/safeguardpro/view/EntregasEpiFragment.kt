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
import com.programardores.safeguardpro.databinding.FragmentEntregasEpiBinding
import com.programardores.safeguardpro.databinding.FragmentFuncionarioCadastroBinding
import com.programardores.safeguardpro.service.model.Entrega
import com.programardores.safeguardpro.service.model.Epi
import com.programardores.safeguardpro.viewmodel.EntregaCadastroViewModel
import com.programardores.safeguardpro.viewmodel.EpiCadastroViewModel

class EntregasEpiFragment : Fragment() {
    private val viewModel: EntregaCadastroViewModel by viewModels()

    private var _binding: FragmentEntregasEpiBinding? = null
    private val binding: FragmentEntregasEpiBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntregasEpiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Carregar o epi caso tenha selecionado
        arguments?.let {
            viewModel.getEntrega(it.getInt("entregaId"))
        }

        binding.btnConfirmar.setOnClickListener {
            var epi_id: Int = 0
            var funcionario_id: Int = 0
            var data_entrega = binding.edtEntrega.editableText.toString()
            var ca: Int = 0
            var periodo = binding.edtPeriodo.editableText.toString()
            if (epi_id != 0 && funcionario_id != 0 && data_entrega != "" && ca != 0 && periodo != "") {

                val entrega = Entrega(
                    epi_id = epi_id,
                    funcionario_id = funcionario_id,
                    data_entrega = data_entrega,
                    ca = ca,
                    periodo = periodo
                )

                viewModel.entrega.value?.let {
                    entrega.id = it.id
                    viewModel.update(entrega)

                } ?: run {
                    viewModel.insert(entrega)
                }

                binding.edtCodigo.editableText.clear()
                binding.edtFunc.editableText.clear()
                binding.edtEntrega.editableText.clear()
                binding.edtCa.editableText.clear()
                binding.edtPeriodo.editableText.clear()
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
                    viewModel.delete(viewModel.entrega.value?.id ?: 0)
                    findNavController().navigateUp()
                }
                .setNegativeButton("Não"){_,_ ->}
                .show()
        }

        viewModel.entrega.observe(viewLifecycleOwner){entrega ->
            binding.edtCodigo.setText(entrega.epi_id)
            binding.edtFunc.setText(entrega.funcionario_id)
            binding.edtEntrega.setText(entrega.data_entrega)
            binding.edtCa.setText(entrega.ca)
            binding.edtPeriodo.setText(entrega.periodo)

            binding.btnDeletar.visibility = View.VISIBLE
        }
    }
}
