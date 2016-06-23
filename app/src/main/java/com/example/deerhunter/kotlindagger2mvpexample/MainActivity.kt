package com.example.deerhunter.kotlindagger2mvpexample

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.deerhunter.kotlindagger2mvpexample.adapters.GsonListAdapter
import com.example.deerhunter.kotlindagger2mvpexample.adapters.dividers.DividerItemDecoration
import com.example.deerhunter.kotlindagger2mvpexample.inject.components.DaggerMainActivityComponent
import com.example.deerhunter.kotlindagger2mvpexample.inject.modules.MainActivityModule
import com.example.deerhunter.kotlindagger2mvpexample.mvp.models.StackOverflowQuestions
import com.example.deerhunter.kotlindagger2mvpexample.mvp.presenters.MainActivityPresenter
import com.example.deerhunter.kotlindagger2mvpexample.mvp.views.MainActivityMvpView
import com.example.deerhunter.kotlindagger2mvpexample.mvp.views.viewstate.MyCustomViewState
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpViewStateDelegateCallback
import com.hannesdorfmann.mosby.mvp.delegate.ActivityMvpViewStateDelegateImpl
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainActivityMvpView, ActivityMvpViewStateDelegateCallback<MainActivityMvpView, MainActivityPresenter> {
    @Inject lateinit var aPresenter: MainActivityPresenter

    protected var mvpDelegate = ActivityMvpViewStateDelegateImpl(this)

    protected var aRetainInstance: Boolean = true

    protected var viewStatea: ViewState<MainActivityMvpView>? = null

    protected var restoringViewStateA = true


    override fun setRetainInstance(retainingInstance: Boolean) {
        aRetainInstance = retainingInstance
    }

    override fun getPresenter(): MainActivityPresenter {
        return aPresenter
    }

    override fun setPresenter(presenter: MainActivityPresenter) {
        aPresenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMainActivityComponent.builder().applicationComponent((application as MvpApplication).component).mainActivityModule(MainActivityModule()).build().inject(this)
        mvpDelegate.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        fab.setOnClickListener { presenter.processFloatingButtonClicked() }

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.setDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        list.layoutManager = LinearLayoutManager(this)
        list.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL, R.drawable.grey_list_divider))
        list.adapter = GsonListAdapter(this)

        button.setOnClickListener { presenter.processButtonClicked()}
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    @SuppressWarnings("StatementWithEmptyBody")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        presenter.processMenuItemClicked(item)
        return true
    }

    override fun showSnackbar(text: String) {
        Snackbar.make(coordinatorLayout, text, Snackbar.LENGTH_LONG).show()
    }

    override fun showDrawer() {
        drawer_layout.openDrawer(GravityCompat.START)
    }

    override fun showTextViewMode() {
        (viewState as MyCustomViewState).state = MyCustomViewState.State.TEXT_VIEW
        list.visibility = View.GONE
        helloWorld.visibility = View.VISIBLE
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showListMode() {
        (viewState as MyCustomViewState).state = MyCustomViewState.State.LIST
        helloWorld.visibility = View.GONE
        list.visibility = View.VISIBLE
    }

    override fun setListData(data: StackOverflowQuestions) {
        (list.adapter as GsonListAdapter).setData(data)
        (viewState as MyCustomViewState).data = data
    }

    override fun closeDrawer() {
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    override fun onDestroy() {
        super.onDestroy()
        mvpDelegate.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mvpDelegate.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        mvpDelegate.onPause()
    }

    override fun onResume() {
        super.onResume()
        mvpDelegate.onResume()
    }

    override fun onStart() {
        super.onStart()
        mvpDelegate.onStart()
    }

    override fun onStop() {
        super.onStop()
        mvpDelegate.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        mvpDelegate.onRestart()
    }

    override fun onContentChanged() {
        super.onContentChanged()
        mvpDelegate.onContentChanged()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mvpDelegate.onPostCreate(savedInstanceState)
    }

    /**
     * Instantiate a presenter instance

     * @return The [MvpPresenter] for this view
     */
    override fun createPresenter(): MainActivityPresenter = aPresenter

    override fun getMvpView(): MainActivityMvpView {
        return this
    }

    override fun isRetainInstance(): Boolean {
        return aRetainInstance
    }

    override fun shouldInstanceBeRetained(): Boolean {
        return aRetainInstance && isChangingConfigurations
    }

    override fun onRetainNonMosbyCustomNonConfigurationInstance(): Any? {
        return null
    }

    /**
     * Internally used by Mosby. Use [.onRetainNonMosbyCustomNonConfigurationInstance] and
     * [.getNonMosbyLastCustomNonConfigurationInstance]
     */
    override fun onRetainCustomNonConfigurationInstance(): Any {
        return mvpDelegate.onRetainCustomNonConfigurationInstance()
    }

    override fun getNonMosbyLastCustomNonConfigurationInstance(): Any {
        return mvpDelegate.getNonMosbyLastCustomNonConfigurationInstance()
    }



    /**
     * A simple flag that indicates if the restoring ViewState  is in progress right now.
     */




    override fun isRestoringViewState(): Boolean {
        return restoringViewStateA
    }

    override fun onViewStateInstanceRestored(instanceStateRetained: Boolean) {
        val myCustomViewState = viewState as MyCustomViewState
        when (myCustomViewState.state) {
            MyCustomViewState.State.LIST -> {
                setListData(myCustomViewState.data)
                showListMode()
            }
            MyCustomViewState.State.TEXT_VIEW -> {
                showTextViewMode()
            }
        }
    }

    /**
     * Creates the ViewState instance
     */
    override fun createViewState(): ViewState<MainActivityMvpView> {
        return MyCustomViewState()
    }


    override fun onNewViewStateInstance() {
        showTextViewMode()
    }

    override fun setViewState(viewState: ViewState<MainActivityMvpView>?) {
        viewStatea = viewState
    }

    override fun getViewState(): ViewState<MainActivityMvpView>? {
        return viewStatea
    }

    override fun setRestoringViewState(restoringViewState: Boolean) {
        restoringViewStateA = restoringViewState
    }
}
