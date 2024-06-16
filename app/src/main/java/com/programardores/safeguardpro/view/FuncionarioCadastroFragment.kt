package com.programardores.safeguardpro.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
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
import com.programardores.safeguardpro.service.model.Funcionario
import com.programardores.safeguardpro.viewmodel.FuncionarioCadastroViewModel
import java.time.LocalDateTime

class FuncionarioCadastroFragment : Fragment() {
    private val viewModel: FuncionarioCadastroViewModel by viewModels()
    private var _binding: FragmentFuncionarioCadastroBinding? = null
    private val binding: FragmentFuncionarioCadastroBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFuncionarioCadastroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1 - Carregar o funcionario caso tenha selecionado
        arguments?.let {
            viewModel.getFuncionario(it.getInt("funcionarioId"))
        }

        // 2 - Click do botao cadastrar
        binding.btnCadastrar.setOnClickListener {
            // 3 - Coletar os dados dos edits
            var nome = binding.edtNome.editableText.toString()
            var cpf = binding.edtCpf.editableText.toString()
            var senha = binding.edtSenha.editableText.toString()

            // 4 - Verificar se nao sao vazios
            if (nome != "" && cpf != "" && senha != "") {

                // 5 - preencher os dados da classe
                val funcionario = Funcionario(
                    nome = nome,
                    cpf = cpf,
                    senha = senha
                )

                // 6 - Decidir se vai atualizar ou criar
                viewModel.funcionario.value?.let {
                    funcionario.id = it.id
                    viewModel.update(funcionario)

                } ?: run {
                    viewModel.insert(funcionario)
                }

                // 7 - Limpar os edits
                binding.edtNome.editableText.clear()
                binding.edtCpf.editableText.clear()
                binding.edtSenha.editableText.clear()
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }
        }

        // 9 - Click do botao deletar
        binding.btnDeletar.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Exclusão de funcionário")
                .setMessage("Você realmente deseja excluir?")
                .setPositiveButton("Sim") { _, _ ->
                    viewModel.delete(viewModel.funcionario.value?.id ?: 0)
                    findNavController().navigateUp()
                }
                .setNegativeButton("Não") { _, _ -> }
                .show()
        }

        // 10 - Observar a variavel da viewmodel
        viewModel.funcionario.observe(viewLifecycleOwner) { funcionario ->

            // 11 - Popular os edits
            binding.edtNome.setText(funcionario.nome)
            binding.edtCpf.setText(funcionario.cpf)
            binding.edtSenha.setText(funcionario.senha)

            // 12 - Mostrar o botao de excluir
            binding.btnDeletar.visibility = View.VISIBLE
        }

        // 13 - Observar a variavel da viewmodel para mostrar os erros
        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()
            Log.e("erro Emprestimo", it)
        }
    }
}
