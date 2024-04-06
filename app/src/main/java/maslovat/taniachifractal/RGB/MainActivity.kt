package maslovat.taniachifractal.RGB

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import maslovat.taniachifractal.RGB.databinding.ActivityMainBinding
import maslovat.taniachifractal.RGB.databinding.NewColorDialogBinding
import java.lang.Math.abs

class MainActivity : AppCompatActivity() {

    private var red=0; private var green=0; private var blue=0;

    private lateinit var fld: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fld=ActivityMainBinding.inflate(layoutInflater)
        setContentView(fld.root)
        updateColor()
        fld.colorWindow.setOnClickListener{btNewColor_Click()}
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("r",red)
        outState.putInt("g",green)
        outState.putInt("b",blue)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        red=savedInstanceState.getInt("r")
        green=savedInstanceState.getInt("g")
        blue=savedInstanceState.getInt("b")
        updateColor()
    }
    /**Create a dialog for color values input*/
    private fun btNewColor_Click()
    {
        val dialogFld = NewColorDialogBinding.inflate(layoutInflater)
        val dialog=AlertDialog.Builder(this)
            .setTitle("RGB")
            .setView(dialogFld.root)
            .setPositiveButton("OK"){d,which->
                var redStr = dialogFld.redInput.text.toString();
                var greenStr = dialogFld.greenInput.text.toString();
                var blueStr = dialogFld.blueInput.text.toString()

                if (redStr.length>3) redStr="255"
                if (greenStr.length>3) greenStr="255"
                if (blueStr.length>3) blueStr="255"

                if (redStr.isNotBlank()) red=redStr.toInt(); if (red>255) red=255
                if (greenStr.isNotBlank()) green=greenStr.toInt(); if (green>255) green=255
                if (blueStr.isNotBlank()) blue=blueStr.toInt(); if (blue>255) blue=255

                updateColor()
            }
            .create()
        dialog.show()
    }
    /**Change color of the square on the screen and the color info*/
    private fun updateColor()
    {
        fld.colorWindow.setBackgroundColor(Color.rgb(red, green, blue))
        fld.colorInfo.text="red: $red\ngreen: $green\nblue: $blue"
        var antiRed = 255-red
        var antiGreen = 255-green
        var antiBlue = 255-blue
        if(abs(red - antiRed)<20) antiRed+=20
        if(abs(green - antiGreen)<20) antiGreen+=20
        if(abs(blue - antiBlue)<20) antiBlue+=20
        fld.colorInfo.setTextColor(Color.rgb(antiRed,antiGreen,antiBlue))
    }
}