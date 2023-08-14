package com.codexdroid.theguru.x_dev_space_x

import com.codexdroid.theguru.utility.AppConstants
import java.util.PriorityQueue

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
//------------------------------------------------------------------------------------------------------------
  /**
            MaterialAlertDialogBuilder(this@HomeActivity)
                .setTitle(getString(R.string.app_exit_title,getString(R.string.app_name)))
                .setMessage(getString(R.string.app_exit_desc,getString(R.string.app_name)))
                .setPositiveButton(getString(R.string.yes_pls)) { _, _ -> finishAffinity() }
                .setNegativeButton(getString(R.string.no_i_don_t)){ dialog, _ -> dialog.dismiss() }
                .create().show()
**/

/***

private fun requestForCredentials() {
Firebase.firestore.collection(AppConstants.Firestore.COLLECTION_LOGINS)
.get()
.addOnSuccessListener { result ->
result.forEach {
val map = it.data as HashMap<String, Any>
val email = map[AppConstants.Firestore.LOGIN_ADMIN_EMAIL].toString()
val password = map[AppConstants.Firestore.LOGIN_ADMIN_PASSWORD].toString()
Log.d("AXE","Email: $email  ||  Password: $password")
}
}
}

 **/