package com.kentvu.csproblems

import android.app.Application
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), View {
    val presenter: Presenter by lazy { Presenter(AndroidLog(), this, TODOProblemRepo(application)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Log.d("MainActivity", "presenter:$presenter")

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        presenter.evtListener.onActivityCreate()
    }

    override fun display(problem: Problem) {
        findViewById<TextView>(R.id.title).text = problem.title
        description.text = problem.description
        solutionLang.text = problem.solutions.first().lang.displayName
        solution.text = problem.solutions.first().code
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

class TODOProblemRepo(val application: Application) : ProblemRepository {
    override fun loadProblem(): Problem {
        return Repo(application.assets.open("input.yaml").bufferedReader().use{
            it.readText()
        }).problems.first()
    }

}
