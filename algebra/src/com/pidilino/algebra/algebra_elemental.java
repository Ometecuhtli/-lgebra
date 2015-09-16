package com.pidilino.algebra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class algebra_elemental extends Activity {

   private ImageView mMenúPrincipalOpción1;
   private ImageView mMenúPrincipalOpción2;
   private ImageView mMenúPrincipalOpción3;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);

      // Iniciar sección de Aritmética
      mMenúPrincipalOpción1 = (ImageView)findViewById(R.id.imgMenúPrincipalOpción1);
      mMenúPrincipalOpción1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread opc1 = new Thread() {
               public void run() {
                  // Pasar de la aplicación principal a la pantalla de ejercicios (aritmética básica)
                  startActivity( new Intent(algebra_elemental.this, aritmetica.class) );
               }
            };
            opc1.start();

         }
      });

      // Iniciar sección de Expresiones algebraicas
      mMenúPrincipalOpción2 = (ImageView)findViewById(R.id.imgMenúPrincipalOpción2);
      mMenúPrincipalOpción2.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread opc1 = new Thread() {
               public void run() {
                  // Pasar de la aplicación principal a la pantalla de ejercicios (aritmética básica)
                  startActivity( new Intent(algebra_elemental.this, expresiones.class) );
               }
            };
            opc1.start();

         }
      });

      // Iniciar sección de Igualdades y desigualdades
      mMenúPrincipalOpción3 = (ImageView)findViewById(R.id.imgMenúPrincipalOpción3);
      mMenúPrincipalOpción3.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Thread opc1 = new Thread() {
               public void run() {
                  // Pasar de la aplicación principal a la pantalla de ejercicios (aritmética básica)
                  startActivity( new Intent(algebra_elemental.this, igualdades.class) );
               }
            };
            opc1.start();

         }
      });
   }
}