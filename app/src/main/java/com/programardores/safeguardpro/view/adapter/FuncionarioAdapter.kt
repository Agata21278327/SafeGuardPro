package com.programardores.safeguardpro.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.programardores.safeguardpro.databinding.ListItemFuncionarioBinding
import com.programardores.safeguardpro.service.model.Funcionario

class FuncionarioAdapter(funcionarios: List<Funcionario>?, private val clickListListener: (Funcionario) -> Unit) :
    RecyclerView.Adapter<FuncionarioAdapter.FuncionarioViewHolder>() {

    //Criar uma lista vazia de funcionarios
    private var funcionarioList: List<Funcionario> = arrayListOf()

    class FuncionarioViewHolder(private val binding: ListItemFuncionarioBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //Carrega as informações da funcionarios na lista
        fun bind(funcionario: Funcionario, clickListListener: (Funcionario) -> Unit) {

            binding.root.setOnClickListener {
                clickListListener(funcionario)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuncionarioViewHolder {

        //configura o binding da lista
        val listItemFuncionarioBinding =
            ListItemFuncionarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FuncionarioViewHolder(listItemFuncionarioBinding)
    }

    override fun getItemCount(): Int {
        return  funcionarioList.count()
    }

    override fun onBindViewHolder(holder: FuncionarioViewHolder, position: Int) {
        holder.bind(funcionarioList[position], clickListListener)
    }

    // carrega a lista de funcionarios paara serem exibidas
    fun updateFuncionario(list: List<Funcionario>){
        funcionarioList = list
        notifyDataSetChanged()
    }
}