package com.android.example.project.list

import android.app.Application
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import androidx.recyclerview.widget.RecyclerView
import com.android.example.project.database.Contact
import com.android.example.project.database.DatabaseDAO
import kotlinx.coroutines.*
import java.lang.StringBuilder

class ListsViewModel (private val database: DatabaseDAO, application: Application) : AndroidViewModel(application){
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val contacts = database.get()


//    val contactString = Transformations.map(contacts) { contacts ->
//        formatContact(contacts)
//    }
//
//    private fun formatContact(contact: List<Contact>): Spanned {
//        val sb = StringBuilder()
//        sb.apply {
//            //append(resources.getString(R.string.title))
//            contact.forEach {
//                append(it.nameS)
//                append(" ")
//                append(it.phone)
//                append("<br>")
//            }
//        }
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
//        } else {
//            HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
//        }
//    }
    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }
    fun onActivityClear(){
        uiScope.launch {
            clear()
        }
    }
}
class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)