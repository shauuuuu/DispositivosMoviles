package marcosmorales.example.proyectofinal_pdm;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DatosActivity extends AppCompatActivity {

    // Variables para los componentes visuales
    private EditText etNombre, etDireccion;
    private Button btnConfirmar;
    private TextView tvResumenPedido;

    // Variables para almacenar los datos que vienen del Intent
    private String bebida, extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Apunta al XML de entrega

        // Enlazamos los componentes visuales con el archivo XML
        tvResumenPedido = findViewById(R.id.tvResumenPedido);
        etNombre = findViewById(R.id.etNombre);
        etDireccion = findViewById(R.id.etDireccion);
        btnConfirmar = findViewById(R.id.btnConfirmar);

        // Se recuperan los datos enviados desde MainActivity mediante el Intent
        Intent intent = getIntent();
        bebida = intent.getStringExtra("BEBIDA_ELEGIDA");
        extras = intent.getStringExtra("EXTRAS_ELEGIDOS");

        // Se muestra el resumen de la orden de Café Sócrates en el TextView
        String resumenFinal = "Resumen de tu orden en Café Sócrates:\n\n" +
                " BEBIDA:\n" + bebida + "\n\n" +
                " EXTRAS:\n" + extras;
        tvResumenPedido.setText(resumenFinal);

        // Se configura el evento de clic para el botón Confirmar Pedido
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarPedido();
            }
        });
    }

    // Metodo encargado de validar y guardar la información en SQLite
    private void registrarPedido() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String nombre = etNombre.getText().toString().trim();
        String direccion = etDireccion.getText().toString().trim();

        if (!nombre.isEmpty() && !direccion.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("nombre_cliente", nombre);
            registro.put("direccion", direccion);
            registro.put("bebida", bebida);
            registro.put("extras", extras);

            bd.insert("pedidos", null, registro);

            // Comentamos el cierre para que el Database Inspector se quede abierto y se pueda ver la tabla
            bd.close();

            Toast.makeText(DatosActivity.this, "¡Pedido registrado con éxito!", Toast.LENGTH_LONG).show();

            // Se salta a la pantalla de éxito
            Intent intentExito = new Intent(DatosActivity.this, ExitoActivity.class);
            startActivity(intentExito);

            // Finalizamos la actividad para que no se quede guardada en el botón "Atrás"
            finish();

        } else {
            Toast.makeText(this, "Por favor, ingresa tu nombre y dirección de envío", Toast.LENGTH_SHORT).show();
        }
    }
}