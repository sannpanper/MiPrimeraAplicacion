package com.example.gestioncuentabar;

import android.os.Bundle;
import android.widget.*;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    EditText etCuenta;
    CheckBox cbPropina;
    SeekBar sbPropina;
    TextView tvPorcentaje, tvResultado;
    RadioGroup rgPago;
    RadioButton rbEfectivo, rbTarjeta;
    RatingBar rbValoracion;
    Button btnCalcular;

    int colorNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCuenta = findViewById(R.id.etCuenta);
        cbPropina = findViewById(R.id.cbPropina);
        sbPropina = findViewById(R.id.sbPropina);
        tvPorcentaje = findViewById(R.id.tvPorcentaje);
        tvResultado = findViewById(R.id.tvResultado);
        rgPago = findViewById(R.id.rgPago);
        rbEfectivo = findViewById(R.id.rbEfectivo);
        rbTarjeta = findViewById(R.id.rbTarjeta);
        rbValoracion = findViewById(R.id.rbValoracion);
        btnCalcular = findViewById(R.id.btnCalcular);

        colorNormal = tvResultado.getCurrentTextColor();

        // Actualizamos el porcentaje al mover el SeekBar
        sbPropina.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvPorcentaje.setText(getString(R.string.porcentaje, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Calculamos la cuenta al pulsar el botón
        btnCalcular.setOnClickListener(v -> calcularCuenta());
    }

    private void calcularCuenta() {

        // Comprobamos si el campo está vacío
        String textoCuenta = etCuenta.getText().toString().trim();

        if (textoCuenta.isEmpty()) {
            mostrarError(getString(R.string.error_vacio));
            return;
        }

        double cuenta;

        try {
            // Permitimos utilizar punto o coma decimal
            cuenta = Double.parseDouble(textoCuenta.replace(",", "."));

        } catch (NumberFormatException e) {
            mostrarError(getString(R.string.error_formato));
            return;
        }

        // Comprobamos que el importe sea mayor que cero
        if (cuenta <= 0) {
            mostrarError(getString(R.string.error_cantidad));
            return;
        }

        double total = cuenta;

        // Añadimos la propina si está seleccionado el CheckBox
        if (cbPropina.isChecked()) {

            double porcentaje = sbPropina.getProgress();
            double propina = cuenta * porcentaje / 100;

            total = cuenta + propina;
        }

        // Obtenemos el metodo de pago seleccionado
        int seleccionado = rgPago.getCheckedRadioButtonId();

        String metodoPago;

        if (seleccionado == R.id.rbEfectivo) {
            metodoPago = getString(R.string.efectivo);
        } else {
            metodoPago = getString(R.string.tarjeta);
        }

        // Obtenemos la valoración del servicio
        float valoracion = rbValoracion.getRating();

        // Formateamos el importe como moneda
        String importe = NumberFormat.getCurrencyInstance().format(total);

        // Mostramos el resultado
        tvResultado.setTextColor(colorNormal);

        tvResultado.setText(getString(
                R.string.resultado,
                importe,
                metodoPago,
                valoracion
        ));

        tvResultado.setVisibility(TextView.VISIBLE);
    }

    // Mostramos los errores en rojo
    private void mostrarError(String mensaje) {

        tvResultado.setTextColor(Color.RED);
        tvResultado.setText(mensaje);
        tvResultado.setVisibility(TextView.VISIBLE);
    }
}