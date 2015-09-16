package com.pidilino.algebra;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

// Problemas de aritmética básica
public class aritmetica extends Activity {

   private static final String TAG = "aritmetica";
   private static final String OPERANDO1 = "operando1";
   private static final String OPERANDO2 = "operando2";
   private static final String OPERADOR = "operador";
   private static final String RESULTADO = "resultado";
   private static final String RESPUESTA = "respuesta";

   private EditText mEditOperando1;
   private EditText mEditOperando2;
   private ImageView mImgOperador;
   private ImageView mImgIgual;
   private EditText mEditRespuesta;

   private Button mBtnAceptar;
   private Button mBtnSiguiente;

   private int operando1, operando2, resultado, respuesta;
   private char operador;

   Random brandon = new Random();

   public void actualizar() {

      operando1 = brandon.nextInt(1000);
      operando2 = brandon.nextInt(1000);

      switch( brandon.nextInt(4) ) {
         case 0:
            operador = '+';
            resultado = operando1 + operando2;
            mImgOperador.setImageResource(R.drawable.suma);
            break;
         case 1:
            operador = '-';
            resultado = operando1 - operando2;
            mImgOperador.setImageResource(R.drawable.resta);
            break;
         case 2:
            operador = '*';
            operando2 = brandon.nextInt(100);
            resultado = operando1 * operando2;
            mImgOperador.setImageResource(R.drawable.multiplicacion);
            break;
         case 3:
            operador = '/';
            do {
               operando2 = brandon.nextInt(100);
               resultado = brandon.nextInt(100);
            } while( operando2 == 0 );
            operando1 = operando2 * resultado;
            mImgOperador.setImageResource(R.drawable.division);
            break;
      }

      mEditOperando1.setText(String.valueOf(operando1));
      mEditOperando2.setText(String.valueOf(operando2));
      mImgIgual.setImageResource(R.drawable.igual);
      mEditRespuesta.setText("");
   }

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.aritmetica);

      mEditOperando1 = (EditText)findViewById(R.id.editOperando1);
      mEditOperando2 = (EditText)findViewById(R.id.editOperando2);
      mEditRespuesta = (EditText)findViewById(R.id.editRespuesta);

      mImgOperador = (ImageView)findViewById(R.id.imgOperador);
      mImgIgual = (ImageView)findViewById(R.id.imgIgual);

      mBtnAceptar = (Button)findViewById(R.id.btnAceptar);
      mBtnAceptar.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            try {
               respuesta = Integer.valueOf(mEditRespuesta.getText().toString());
            }
            catch( Exception e1 ) {

            }
            finally {
               respuesta = 0;
            }
            if( respuesta == resultado ) {
               Toast.makeText( aritmetica.this, R.string.msjCorrecto, Toast.LENGTH_SHORT ).show();
            }
            else {
               Toast.makeText( aritmetica.this, R.string.msjIncorrecto, Toast.LENGTH_SHORT ).show();
            }
            actualizar();
         }
      });

      mBtnSiguiente = (Button)findViewById(R.id.btnSiguiente);
      mBtnSiguiente.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            actualizar();
         }
      });

      if( savedInstanceState != null ) {
         operando1 = savedInstanceState.getInt(OPERANDO1);
         operador = savedInstanceState.getChar(OPERADOR);
         operando2 = savedInstanceState.getInt(OPERANDO2);
         resultado = savedInstanceState.getInt(RESULTADO);
         respuesta = savedInstanceState.getInt(RESPUESTA);

         mEditOperando1.setText(String.valueOf(operando1));

         switch( operador ) {
            case '+':
               mImgOperador.setImageResource(R.drawable.suma);
               break;
            case '-':
               mImgOperador.setImageResource(R.drawable.resta);
               break;
            case '*':
               mImgOperador.setImageResource(R.drawable.multiplicacion);
               break;
            case '/':
               mImgOperador.setImageResource(R.drawable.division);
               break;
         }

         mEditOperando2.setText(String.valueOf(operando2));
         mImgIgual.setImageResource(R.drawable.igual);
         mEditRespuesta.setText(String.valueOf(respuesta));
      }
      else {
         actualizar();
      }
   }

   @Override
   public void onSaveInstanceState( Bundle savedInstanceState ) {
      super.onSaveInstanceState(savedInstanceState);
      Log.i(TAG, "onSaveInstanceState");
      savedInstanceState.putInt(OPERANDO1, operando1);
      savedInstanceState.putInt(OPERANDO2, operando2);
      savedInstanceState.putInt(RESULTADO, resultado);
      savedInstanceState.putInt(RESPUESTA, respuesta);
      savedInstanceState.putChar(OPERADOR, operador);
   }
}