package com.moon.libaccount.adapter


/**
 * @author ry
 * @date 2019-09-29
 */
//class HistoryAccountAdapter :
//    ListAdapter<HistoryAccount, HistoryAccountAdapter.HistoryAccountAdapterVH>(diff) {
//
//    var clickListener: ((HistoryAccount) -> Unit)? = null
//    var clearListener:((HistoryAccount) -> Unit)? = null
//
//    override fun onBindViewHolder(holder: HistoryAccountAdapterVH, position: Int) {
//        val item = getItem(position)
//        holder.tvName.text = item.userName
//        holder.clearImage.setOnClickListener {
//            clearListener?.invoke(item)
//        }
//        holder.itemView.setOnClickListener {
//            clickListener?.invoke(item)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAccountAdapterVH {
//        return HistoryAccountAdapterVH.create(parent)
//    }
//
//
//    companion object {
//        val diff = object : DiffUtil.ItemCallback<HistoryAccount>() {
//            override fun areContentsTheSame(
//                oldItem: HistoryAccount,
//                newItem: HistoryAccount
//            ): Boolean {
//                return oldItem.uid== newItem.uid
//            }
//
//            override fun areItemsTheSame(
//                oldItem: HistoryAccount,
//                newItem: HistoryAccount
//            ): Boolean {
//                return oldItem.uid == newItem.uid
//            }
//        }
//    }
//
//    class HistoryAccountAdapterVH(view: View) : RecyclerView.ViewHolder(view) {
//        val tvName = itemView.find<TextView>(R.id.history_name)
//        val clearImage = itemView.find<ImageView>(R.id.clear_account)
//        companion object {
//            fun create(parent: ViewGroup): HistoryAccountAdapterVH {
//                val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.item_history_account, parent, false)
//                return HistoryAccountAdapterVH(view)
//            }
//        }
//    }
//}