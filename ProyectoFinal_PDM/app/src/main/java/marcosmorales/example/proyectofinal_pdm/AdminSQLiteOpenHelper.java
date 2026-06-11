package marcosmorales.example.proyectofinal_pdm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    // Constructor de la clase
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Este metodo se ejecuta automáticamente cuando se crea la base de datos por primera vez
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Aquí diseñamos la tabla "pedidos" usando lenguaje SQL
        db.execSQL("CREATE TABLE pedidos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre_cliente TEXT, " +
                "direccion TEXT, " +
                "bebida TEXT, " +
                "extras TEXT)");
    }

    // Este metodo se usa si en el futuro se quiere actualizar la estructura de la tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS pedidos");
        onCreate(db);
    }
}