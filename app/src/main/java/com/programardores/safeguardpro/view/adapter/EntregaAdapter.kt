package com.programardores.safeguardpro.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.programardores.safeguardpro.databinding.ListItemEntregaBinding
import com.programardores.safeguardpro.service.model.Entrega

class EntregaAdapter(entregas: List<Entrega>?, private val clickListListener: (Entrega) -> Unit) :
    RecyclerView.Adapter<EntregaAdapter.EntregaViewHolder>() {

    //Criar uma lista vazia de entregas
    private var entregaList: List<Entrega> = arrayListOf()

    class EntregaViewHolder(private val binding: ListItemEntregaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //Carrega as informações da entrega na lista
        fun bind(entrega: Entrega, clickListListener: (Entrega) -> Unit) {

            binding.root.setOnClickListener {
                clickListListener(entrega)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntregaViewHolder {

        //configura o binding da lista
        val listItemEntregaBinding =
            ListItemEntregaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EntregaViewHolder(listItemEntregaBinding)
    }

    override fun getItemCount(): Int {
        return  entregaList.count()
    }

    override fun onBindViewHolder(holder: EntregaViewHolder, position: Int) {
        holder.bind(entregaList[position], clickListListener)
    }

    // carrega a lista de entregas paara serem exibidas
    fun updateEntrega(list: List<Entrega>){
        entregaList = list
        notifyDataSetChanged()
    }
}