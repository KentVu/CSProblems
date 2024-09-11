package com.kentvu.csproblems.old

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SpinnerAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.kentvu.csproblems.AndroidLog
import com.kentvu.csproblems.Presenter
import com.kentvu.csproblems.PresenterImpl
import com.kentvu.csproblems.Problem
import com.kentvu.csproblems.ProblemRepository
import com.kentvu.csproblems.R
import com.kentvu.csproblems.View

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_problems.*

class MainActivity : AppCompatActivity(), View {
    val presenter: Presenter by lazy { PresenterImpl(AndroidLog(), this, ProblemRepository.TODO(
      application.assets.open("input.yaml").bufferedReader().use {
          it.readText()
      })) }

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

    private val mainFragment: MainFragment
        get() = supportFragmentManager.findFragmentById(R.id.fragment_root) as MainFragment

    override fun displayResult(result: Any) {
        mainFragment.display(result)
    }

    override fun populateAlgos(algos: List<String>) {
        mainFragment.populateAlgos(algos)
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

    fun buttonDetailClick() {
        transitionToProblemsFragment()
    }

    private fun transitionToProblemsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_root, ProblemsFragment())
            .addToBackStack(null)
            .commit()
    }

    fun buttonRunClick() {
        val arr = mainFragment.input.split("""\s*,\s*""".toRegex()).map { it.toInt() }.toIntArray()
        presenter.evtListener.buttonRunClick(mainFragment.currentAlgo, arr)
    }

    fun mainCreated() {
        presenter.evtListener.onMainCreate()
    }

    fun problemsViewCreated() {
        presenter.evtListener.onProblemsCreate()
    }
}

class ProblemsFragment : Fragment() {
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

    var input: String
        get() = txt_input.text.toString()
        set(value) {
            txt_input.text = Editable.Factory.getInstance().newEditable(value)
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
        view.findViewById<Button>(R.id.btn_detail).setOnClickListener {
            (activity as MainActivity).buttonDetailClick()
        }
        btn_run.setOnClickListener {
            (activity as MainActivity).buttonRunClick()
        }
        (activity as MainActivity).mainCreated()
    }

    fun display(result: Any) {
        txt_result.text = result.toString()
    }

    private lateinit var algosAdapter: SpinnerAdapter
    val currentAlgo: String get() = spinner.selectedItem as String

    fun populateAlgos(algos: List<String>) {
        algosAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            algos
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinner.adapter = algosAdapter
    }
}

