package com.zhao.base.utils.router

class RouterUrl {

    companion object {
        private const val ACTIVITY = "Activity"
        private const val FRAGMENT = "Fragment"

        const val NONE = ""//无跳转目标 可以选择finish当前界面
    }
    //=====================================================

    object App {
        private const val model = "/app/"
        const val Main = model + "main" + ACTIVITY
        const val Welcome = model + "welcome" + ACTIVITY
    }

    object Home {
        private const val model = "/home/"
        const val TabHome = model + "tabHome" + FRAGMENT
    }

    object Msg {
        private const val model = "/Msg/"
        const val TabMsg = model + "tabMsg" + FRAGMENT
    }
    object User {
        private const val model = "/user/"
        const val TabUser = model + "tabUser" + FRAGMENT
    }


    object Web {
        private const val model = "/web/"
        const val H5 = model + "web" + ACTIVITY //vm版H5 web
    }
}