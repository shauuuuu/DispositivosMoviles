package marcosmorales.example.tarea03_pdm;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    private String[] mMenuSections;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

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
                mDrawerLayout.closeDrawer(mDrawerList); // Cierra el menú al seleccionar
            }
        });

        // Configura el botón del ActionBar (la hamburguesa)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
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