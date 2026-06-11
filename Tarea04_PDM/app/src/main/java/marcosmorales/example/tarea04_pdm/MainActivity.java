package marcosmorales.example.tarea04_pdm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    private String[] mMenuSections;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // Declaramos el botón restaurado
    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        // Se inicializan los componentes del drawer
        mMenuSections = getResources().getStringArray(R.array.opciones_drawer);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);

        // Se configura el adaptador de la lista
        mDrawerList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mMenuSections));

        // Evento al hacer clic en el menú lateral
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String opcion = (String) parent.getItemAtPosition(position);
                Log.d("Accion", "Opción del drawer seleccionada: " + opcion); // Registro en logcat
                mDrawerLayout.closeDrawers(); // Cierra todos los menús abiertos de forma segura
            }
        });

        // Se configura el botón del ActionBar (la hamburguesa)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        // Lógica restaurada de la Tarea 2
        btnContinuar = findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Se obtiene la bebida seleccionada del RadioGroup
                android.widget.RadioGroup rgBebidas = findViewById(R.id.rgBebidas);
                String bebidaSeleccionada = "Bebida no seleccionada";
                int idSeleccionado = rgBebidas.getCheckedRadioButtonId();

                // Verificamos que sí se haya seleccionado una opción
                if (idSeleccionado != -1) {
                    android.widget.RadioButton rb = findViewById(idSeleccionado);
                    bebidaSeleccionada = rb.getText().toString();
                }

                // Se obtienen los extras seleccionados de los CheckBoxes
                android.widget.CheckBox cbLeche = findViewById(R.id.cbLeche);
                android.widget.CheckBox cbEndulzante = findViewById(R.id.cbEndulzante);
                String extras = "";

                if (cbLeche.isChecked()) {
                    extras += "- Leche de almendras\n";
                }
                if (cbEndulzante.isChecked()) {
                    extras += "- Endulzante sin calorías\n";
                }
                if (extras.isEmpty()) {
                    extras = "- Sin extras";
                }

                // Creamos el Intent y empacamos los datos con putExtra
                Intent intent = new Intent(MainActivity.this, DatosActivity.class);
                intent.putExtra("BEBIDA_ELEGIDA", bebidaSeleccionada);
                intent.putExtra("EXTRAS_ELEGIDOS", extras);

                // Se inicia la segunda pantalla
                startActivity(intent);
            }
        });
    }

    // Muestra el ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    // Eventos al hacer clic en los botones del ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();

        if (id == R.id.action_buscar) {
            Log.d("Accion", "Buscar un pedido anterior");
            return true;
        } else if (id == R.id.action_compartir) {
            Log.d("Accion", "Compartir detalle del pedido");
            return true;
        } else if (id == R.id.action_info) {
            Log.d("Accion", "Mostrar información de Café Sócrates");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}