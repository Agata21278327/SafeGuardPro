package com.programardores.safeguardpro.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.programardores.safeguardpro.databinding.ListItemEpiBinding
import com.programardores.safeguardpro.service.model.Epi

class EpiAdapter(epis: List<Epi>?, private val clickListListener: (Epi) -> Unit) :
    RecyclerView.Adapter<EpiAdapter.EpiViewHolder>() {

    //Criar uma lista vazia de epis
    private var epiList: List<Epi> = arrayListOf()

    class EpiViewHolder(private val binding: ListItemEpiBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //Carrega as informações da pessoa na lista
        fun bind(epi: Epi, clickListListener: (Epi) -> Unit) {

            binding.root.setOnClickListener {
                clickListListener(epi)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpiViewHolder {

        //configura o binding da lista
        val ListItemEpiBinding =
            ListItemEpiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpiViewHolder(ListItemEpiBinding)
    }

    override fun getItemCount(): Int {
        return  epiList.count()
    }

    override fun onBindViewHolder(holder: EpiViewHolder, position: Int) {
        holder.bind(epiList[position], clickListListener)
    }

    // carrega a lista de epis paara serem exibidas
    fun updateEpi(list: List<Epi>){
        epiList = list
        notifyDataSetChanged()
    }
}