package ba.sum.fsre.loginfirebase

import android.content.Context


class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit()
            .putBoolean("isLoggedIn", isLoggedIn)
            .apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("isLoggedIn", false)
    }

    fun logout() {
        prefs.edit()
            .clear()
            .apply()
    }
}