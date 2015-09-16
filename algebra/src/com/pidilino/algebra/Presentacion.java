package com.pidilino.algebra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by F�jur on 9/12/2015.
 */
public class Presentacion extends Activity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      // Asignar el recurso de layout "splash" a la presentación
      setContentView(R.layout.splash);

      Thread timerThread = new Thread() {
         public void run() {
            try {
               // Tiempo que durará la presentación de la aplicación (en milisegundos)
               sleep(2000);
            } catch(InterruptedException e1){
               e1.printStackTrace();
            } finally {
               // Pasar de la presentación a la aplicación principal
               Intent intent = new Intent(Presentacion.this, algebra_elemental.class);
               startActivity(intent);
            }
         }
      };
      timerThread.start();
   }

   @Override
   protected void onPause() {
      // Evitar que el botón de regreso muestre la pantalla de presentación eliminando la instancia
      super.onPause();
      finish();
   }
}
