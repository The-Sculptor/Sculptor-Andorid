package com.umc.sculptor

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.umc.sculptor.databinding.ActivityMainBinding
import com.umc.sculptor.ui.home.AlarmFragment
import com.umc.sculptor.ui.home.MyPageFragment


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)
        setSupportActionBar(binding.toolbar)

        // Disable displaying the title in the Toolbar
        supportActionBar?.setDisplayShowTitleEnabled(false)


        binding.ivProfile.setOnClickListener {
            replaceFragmentToMyPage()
        }

        binding.ivNotification.setOnClickListener {
            replaceFragmentAlarmPage()
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.ivProfile.setOnClickListener {
            replaceFragmentToMyPage()
        }

        binding.ivNotification.setOnClickListener {
            replaceFragmentAlarmPage()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController(R.id.nav_graph).navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    fun hideBottomNav(state: Boolean){
        if(state){
            binding.bottomNavigation.visibility = View.GONE
        }else{
            binding.bottomNavigation.visibility = View.VISIBLE
        }
    }


    fun hideIconAndShowBack(state: Boolean){
        if(state){
            binding.ivBack.visibility = View.VISIBLE
            binding.ivNotification.visibility = View.GONE
            binding.ivProfile.visibility = View.GONE
        }else{
            binding.ivBack.visibility = View.GONE
            binding.ivNotification.visibility = View.VISIBLE
            binding.ivProfile.visibility = View.VISIBLE
        }
    }

    private fun replaceFragmentToMyPage() {
        val secondFragment = MyPageFragment()

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.fragmentContainerView2, secondFragment)

        transaction.addToBackStack(null) // 백스택에 추가하여 뒤로 가기를 처리할 수 있도록 함
        transaction.commit()
    }

    private fun replaceFragmentAlarmPage() {
        val secondFragment = AlarmFragment()

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.fragmentContainerView2, secondFragment)

        transaction.addToBackStack(null) // 백스택에 추가하여 뒤로 가기를 처리할 수 있도록 함
        transaction.commit()
    }

}