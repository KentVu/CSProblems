package com.kentvu.csproblems

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_problems.*

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
        supportFragmentManager.beginTransaction().replace(R.id.fragment_root, MainFragment()).commit()
    }

    override fun displayProblem(problem: Problem) {
        (supportFragmentManager.findFragmentById(R.id.fragment_root) as ProblemsFragment).display(problem)
    }

    override fun displayResult(result: Any) {
        (supportFragmentManager.findFragmentById(R.id.fragment_root) as MainFragment).display(result)
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

    fun buttonClick() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_root, ProblemsFragment())
            .addToBackStack(null)
            .commit()
    }

    fun mainCreated() {
        val arr = arrayOf(1, 4, 3, 6, 7, 2)
        (supportFragmentManager.findFragmentById(R.id.fragment_root) as MainFragment).input = arr.joinToString()
        presenter.evtListener.onMainCreate(arr)
    }

    fun problemsViewCreated() {
        presenter.evtListener.onProblemsCreate()
    }
}

class ProblemsFragment() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View? {
        return inflater.inflate(R.layout.content_problems, container, false)
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).problemsViewCreated()
    }

    fun display(problem: Problem) {
        view!!.findViewById<TextView>(R.id.title).text = problem.title
        description.text = problem.description
        solutionLang.text = problem.solutions.first().lang.displayName
        solution.text = problem.solutions.first().code
    }
}

class MainFragment : Fragment() {

    var input: String = ""
        set(value) {
            textView.text = value
            field = value
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View? {
        return inflater.inflate(R.layout.content_main, container, false)
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button).setOnClickListener {
            (activity as MainActivity).buttonClick()
        }
        (activity as MainActivity).mainCreated()
    }

    fun display(result: Any) {
        textView2.text = result.toString()
    }
}

class TODOProblemRepo(val application: Application) : ProblemRepository {
    override fun loadProblem(): Problem {
        return Repo(application.assets.open("input.yaml").bufferedReader().use{
            it.readText()
        }).problems.first()
    }

}
