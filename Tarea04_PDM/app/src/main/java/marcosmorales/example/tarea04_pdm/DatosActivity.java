package marcosmorales.example.tarea04_pdm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DatosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enlazamos con el archivo de diseño donde pusimos el tvResumenPedido
        setContentView(R.layout.activity_main);

        // Enlazamos el TextView del resumen que creamos en el XML
        TextView tvResumenPedido = findViewById(R.id.tvResumenPedido);

        // Obtenemos el Intent que nos envió MainActivity
        Intent intent = getIntent();

        // Extraemos los datos usando exactamente las mismas llaves/nombres clave
        String bebida = intent.getStringExtra("BEBIDA_ELEGIDA");
        String extras = intent.getStringExtra("EXTRAS_ELEGIDOS");

        // Formateamos el texto de manera elegante para el usuario
        String resumenFinal = "Resumen de tu orden en Café Sócrates:\n\n" +
                " BEBIDA:\n" + bebida + "\n\n" +
                " EXTRAS:\n" + extras;

        // Mostramos el texto final en la pantalla
        tvResumenPedido.setText(resumenFinal);
    }
}