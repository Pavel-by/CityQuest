package com.cityquest.hackathon.cityquest

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.cityquest.hackathon.cityquest.quests.QuestsFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.cityquest.hackathon.cityquest.R.id.toolbar
import com.cityquest.hackathon.cityquest.users.UserActivity
import com.cityquest.hackathon.cityquest.users.storage.User
import com.cityquest.hackathon.cityquest.users.storage.UserService
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.internal.operators.observable.ObservableOnErrorNext
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.view_main_nav_header.*
import kotlinx.android.synthetic.main.view_main_nav_header.view.*
import java.util.function.Consumer


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setFragment(QuestsFragment())
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.open, R.string.close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        val headerView = layoutInflater.inflate(R.layout.view_main_nav_header, nav_view, false)
        //nav_view.addHeaderView(headerView)
        UserService.instance.user("")
            .subscribe(
                object: DisposableObserver<User>() {
                    override fun onComplete() {
                    }

                    override fun onNext(user: User) {
                        headerView.nav_action_button.text = user.email
                        headerView.nav_action_button.setOnClickListener {
                            UserActivity.start(user.id)
                        }
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(this@MainActivity, e.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            )
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setFragment(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment, fragment)
            .commit()
        title = fragment.getTitle()
    }
}
