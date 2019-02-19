package com.zhao.home.debug

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.zhao.base.debug.ContainerActivity
import com.zhao.home.HomeFragment

class DebugActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, ContainerActivity::class.java)
        intent.putExtra("fragment", HomeFragment::class.java!!.canonicalName)
        this.startActivity(intent)
        finish()
    }
}