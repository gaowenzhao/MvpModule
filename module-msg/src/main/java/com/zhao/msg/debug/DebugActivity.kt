package com.zhao.msg.debug

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.zhao.base.debug.ContainerActivity
import com.zhao.msg.MsgFragment

class DebugActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, ContainerActivity::class.java)
        intent.putExtra("fragment", MsgFragment::class.java!!.canonicalName)
        this.startActivity(intent)
        finish()
    }
}