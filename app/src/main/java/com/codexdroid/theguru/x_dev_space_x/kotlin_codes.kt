package com.codexdroid.theguru.x_dev_space_x
//
//class WordListAdapter : ListAdapter<Word, WordViewHolder>(WordsComparator()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
//        return WordViewHolder.create(parent)
//    }
//
//    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
//        val current = getItem(position)
//        holder.bind(current.word)
//    }
//
//    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val wordItemView: TextView = itemView.findViewById(R.id.textView)
//
//        fun bind(text: String?) {
//            wordItemView.text = text
//        }
//
//        companion object {
//            fun create(parent: ViewGroup): WordViewHolder {
//                val view: View = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.recyclerview_item, parent, false)
//                return WordViewHolder(view)
//            }
//        }
//    }
//
//    class WordsComparator : DiffUtil.ItemCallback<Word>() {
//        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
//            return oldItem === newItem
//        }
//
//        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
//            return oldItem.word == newItem.word
//        }
//    }
//}