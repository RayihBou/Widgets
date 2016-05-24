package comrayihbou.github.mascotas;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/*Importante: En el archivo Gradle se debe importar en la libreria (Module: app) la siguiente linea de comando compile 'com.android.support:design:23.3.0'
  para poder usar todos los widget de esta aplicacion.
  Este soporte de librerias se encuentra en la pagina de android: https://developer.android.com/topic/libraries/support-library/features.html#custom-tabs.

  Para aplicar el Cordinator layout se debe importar los atributos del relative layoute para luego asignarle solo los parametro de weight y heigh al mismo
  como se indica en el activity Login.*/

/*Uso de:
 ºRaised Button.
 ºFloatingActionButton.
 ºAplicacion de Temas para el pulsado del boton y mensaje tipo Toast y Snackbar.
 ºCordinator Layout usado para poder eliminar los mensajes Toast y SnackBar.
 ºRefresh Indicator, Poner atencion a la implementacion del widget necesario para ello
 ºCardView, Minimiza el uso de ListView de una forma mas dinamica (Se tiene que agregar por Gradle con la libreria com.android.support:cardview-v7:23.3.0).
*/
public class LoginActivity extends AppCompatActivity {

    //Creacion de variable Swipe Refresh Layout
    SwipeRefreshLayout sfiMiIndicadorRefresh; //Creacion de Variable SwipeRefresh
    ListView lstMiLista; //Creacion de Variable ListView
    Adapter adaptador; //Adaptador para el Refresh Indicator, funciona como auxiliar para manejar todos los datos de nuestro archivo string.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        agregarFAB(); //Agregar Floating Action Button al metodo en onCreate.

        sfiMiIndicadorRefresh = (SwipeRefreshLayout) findViewById(R.id.sfiMiIndicadorRefresh); //Se necesita el casteo (SwipeRefreshLayout) para poder encontrar el view y asi conectar la variable.
        lstMiLista = (ListView) findViewById(R.id.lstMiLista); //Lo mismo para el listView.

        String[] mascotas = getResources().getStringArray(R.array.mascotas); // Importar la lista de string en xml al nuevo arreglo mascotas que estamos generando en nuestro archivo java.
        lstMiLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, mascotas)); //El layout que se ingresa al final en el parametro se refiere a un patron a seguir para el adapter para el list view.

        sfiMiIndicadorRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { //Aplicación del metodo OnRefresh para ser usado en nuestra lista y la accion a tomar.
            @Override
            public void onRefresh() {

            }
        });

    }

    //Implementacion del metodo Refresh Indicator.

    public void refrescandoContenido(){

        //Esto es lo que deseamos refrescar.

        String[] mascotas = getResources().getStringArray(R.array.mascotas); // Importar la lista de string en xml al nuevo arreglo mascotas que estamos generando en nuestro archivo java.
        lstMiLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, mascotas)); //El layout que se ingresa al final en el parametro se refiere a un patron a seguir para el adapter para el list view.
        sfiMiIndicadorRefresh.setRefreshing(false);

    }

    //Creacion del metodo listener para el boton
    public void agregarFAB(){
        FloatingActionButton miFAB = (FloatingActionButton) findViewById(R.id.fabBola); //Constructor
        miFAB.setOnClickListener(new View.OnClickListener() { //setOnClickListener (Listener) Se crea automaticamente todo el metodo requerido.
            @Override
            public void onClick(View v) {

                //Creacion de Mensaje a Mostrar Toast.
                Toast.makeText(getBaseContext(), getResources().getString(R.string.mensajeToast), Toast.LENGTH_LONG).show(); //Metodo que se ha dejado de usar.

                //Creacion de Mensaje a Mostrar SnackBar.
                Snackbar.make(v, getResources().getString(R.string.mensajeSnack), Snackbar.LENGTH_LONG) //Metodo utilizado actualmente en MaterialDesign. Cumplen con la misma funcion.
                .setAction(getResources().getString(R.string.textoAccion), new View.OnClickListener() { //El metodo setAction define una accion dentro del SnackBar del mensaje a mostrar.
                    @Override //Este metodo se genera automaticamente en el momento que colocamos new View.OnClickListener.
                    public void onClick(View v) {
                        Log.i("SnackBar", "Click en Listener"); // Aqui se muestra un mensaje en consola cuando pulsemos en el mensaje del SnackBar.
                    }
                })
                        .setActionTextColor(getResources().getColor(R.color.colorPrimaryDark)) //Aqui cambiar el color que se muestra en el mensaje de Accion en el SnackBar
                        .show(); //No olvidar de colocar el metodo .show() para poder mostrar todo.

            }
        });
    }
}
