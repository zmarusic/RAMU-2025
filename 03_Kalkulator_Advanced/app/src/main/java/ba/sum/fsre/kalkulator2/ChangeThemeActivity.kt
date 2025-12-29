package ba.sum.fsre.kalkulator2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.widget.Toolbar

class ChangeThemeActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    private lateinit var toolbar2: Toolbar
    private lateinit var mySwitch: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_change_theme)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.appBarLayout2)) { v, insets ->
            val topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            v.setPadding(0, topInset, 0, 0)
            insets
        }

        // findViewById
        toolbar2 = findViewById(R.id.toolbar2)
        mySwitch = findViewById(R.id.mySwitch)
        toolbar2.setNavigationOnClickListener {
            finish()
        }


        mySwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            sharedPreferences = this.getSharedPreferences("Dark Theme", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("switch",true)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("switch",false)
            }
            editor.apply()
        }

    }

    override fun onResume() {
        super.onResume()

        sharedPreferences = this.getSharedPreferences("Dark Theme",Context.MODE_PRIVATE)
        val isDark = sharedPreferences.getBoolean("switch",false)
        mySwitch.isChecked = isDark
    }

}