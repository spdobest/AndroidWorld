package spm.androidworld.all.sqlcipher

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_sql_cipher_db.*
import net.sqlcipher.database.SQLiteDatabase
import spm.androidworld.all.R

class SqlCipherDbActivity : AppCompatActivity(), View.OnClickListener {

    val list = ArrayList<TodoDto>()
    lateinit var todoAdapter: ToDoRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sql_cipher_db)

        SQLiteDatabase.loadLibs(this)

        btnSubmit.setOnClickListener(this)
        loadTodo()
    }


    fun insertData(word: String, def: String) {
        val todo = TodoDto(word, def)
        DatabaseHelper(this).insertTodo(this, "abcd", todo)
    }

    fun loadTodo() {
        todoAdapter = ToDoRecyclerAdapter(list, this)
        recyclerViewTodo.layoutManager = LinearLayoutManager(this)
        recyclerViewTodo.adapter = todoAdapter
        list.addAll(DatabaseHelper(this).getAllToDo(this, "abcd"))
        todoAdapter.notifyDataSetChanged()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnSubmit -> {
                insertData(etWord.text.toString(), etDef.text.toString())
            }
        }
    }
}
