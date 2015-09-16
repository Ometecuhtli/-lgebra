package com.pidilino.algebra;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

// Problemas de igualdades y desigualdades
public class igualdades extends Activity {

   private static final String TAG = "igualdades";
   private static final String OPERANDO11 = "operando11";
   private static final String OPERANDO12 = "operando12";
   private static final String OPERADOR11 = "operador11";
   private static final String OPERANDO21 = "operando21";
   private static final String OPERANDO22 = "operando22";
   private static final String OPERADOR21 = "operador21";
   private static final String RESPUESTA = "respuesta";

   private EditText mEditOperando11;
   private EditText mEditOperando12;
   private ImageView mImgOperador11;
   private EditText mEditOperando21;
   private EditText mEditOperando22;
   private ImageView mImgOperador21;
   private ImageView mImgIgual;
   private EditText mEditRespuesta;

   private Button mBtnAceptar;
   private Button mBtnSiguiente;

   private int operando11, operando12, operando21, operando22, respuesta;
   private char operador11, operador21;

   Random brandon = new Random();

   public void actualizar() {

      operando12 = brandon.nextInt(100);
      operando21 = brandon.nextInt(100);
      operando22 = brandon.nextInt(100);

      switch( brandon.nextInt(4) ) {
         case 0:
            operador21 = '+';
            mImgOperador21.setImageResource(R.drawable.suma);
            operando11 = operando21 + operando22;
            actualizar2();
            break;
         case 1:
            operador21 = '-';
            mImgOperador21.setImageResource(R.drawable.resta);
            operando11 = operando21 - operando22;
            actualizar2();
            break;
         case 2:
            operador21 = '*';
            operando21 = brandon.nextInt(20);
            operando22 = brandon.nextInt(20);
            mImgOperador21.setImageResource(R.drawable.multiplicacion);
            operando11 = operando21 * operando22;
            actualizar2();
            break;
         case 3:
            operador21 = '/';
            operando12 = brandon.nextInt(20);
            operando22 = brandon.nextInt(10) + 1;
            operando21 = operando22 * operando12;
            mImgOperador21.setImageResource(R.drawable.division);
            operando11 = brandon.nextInt(10);
            switch( brandon.nextInt(2) ) {
               case 0:
                  operador11 = '+';
                  operando12 -= operando11;
                  mImgOperador11.setImageResource(R.drawable.suma);
                  break;
               case 1:
                  operador11 = '-';
                  operando12 += operando11;
                  mImgOperador11.setImageResource(R.drawable.resta);
                  break;
            }
            break;
      }

      // La variable x es el primer operando (operando11)
      mEditOperando11.setText("x");
      mEditOperando11.setTypeface(null, Typeface.BOLD_ITALIC);
      mEditOperando11.setEnabled(false);

      // Operandos variables
      mEditOperando12.setText(String.valueOf(operando12));
      mEditOperando21.setText(String.valueOf(operando21));
      mEditOperando22.setText(String.valueOf(operando22));

      mEditRespuesta.setText("");
   }

   public void actualizar2() {
      switch( brandon.nextInt(4) ) {
         case 0:
            operador11 = '+';
            mImgOperador11.setImageResource(R.drawable.suma);
            operando11 -= operando12;
            break;
         case 1:
            operador11 = '-';
            mImgOperador11.setImageResource(R.drawable.resta);
            operando11 += operando12;
            break;
         case 2:
            operador11 = '*';
            do {
               operando12 = brandon.nextInt(10) + 1;
            } while( operando11 % operando12 != 0);
            mImgOperador11.setImageResource(R.drawable.multiplicacion);
            operando11 /= operando12;
            break;
         case 3:
            operador11 = '/';
            operando12 = brandon.nextInt(20) + 1;
            operando11 *= operando12;
            mImgOperador11.setImageResource(R.drawable.division);
            break;
      }
   }

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.igualdades);

      mEditOperando11 = (EditText)findViewById(R.id.editOperando11);
      mImgOperador11 = (ImageView)findViewById(R.id.imgOperador11);
      mEditOperando12 = (EditText)findViewById(R.id.editOperando12);
      mImgIgual = (ImageView)findViewById(R.id.imgIgual);
      mEditOperando21 = (EditText)findViewById(R.id.editOperando21);
      mImgOperador21 = (ImageView)findViewById(R.id.imgOperador21);
      mEditOperando22 = (EditText)findViewById(R.id.editOperando22);
      mEditRespuesta = (EditText)findViewById(R.id.editRespuesta);

      mBtnAceptar = (Button)findViewById(R.id.btnAceptar);
      mBtnAceptar.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            respuesta = 0;
            try {
               respuesta = Integer.parseInt(mEditRespuesta.getText().toString());
            }
            catch( Exception e1 ) {
               e1.printStackTrace();
            }
            if( respuesta == operando11 ) {
               Toast.makeText( igualdades.this, R.string.msjCorrecto, Toast.LENGTH_SHORT ).show();
            }
            else {
               Toast.makeText( igualdades.this, R.string.msjIncorrecto, Toast.LENGTH_SHORT ).show();
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
         operando11 = savedInstanceState.getInt(OPERANDO11);
         operador11 = savedInstanceState.getChar(OPERADOR11);
         operando12 = savedInstanceState.getInt(OPERANDO12);
         operando21 = savedInstanceState.getInt(OPERANDO21);
         operador21 = savedInstanceState.getChar(OPERADOR21);
         operando22 = savedInstanceState.getInt(OPERANDO22);
         respuesta = savedInstanceState.getInt(RESPUESTA);

         // La variable x es el primer operando (operando11)
         mEditOperando11.setText("x");
         mEditOperando11.setTypeface(null, Typeface.BOLD_ITALIC);
         mEditOperando11.setEnabled(false);
         mEditOperando12.setText(String.valueOf(operando12));

         switch( operador11 ) {
            case '+':
               mImgOperador11.setImageResource(R.drawable.suma);
               break;
            case '-':
               mImgOperador11.setImageResource(R.drawable.resta);
               break;
            case '*':
               mImgOperador11.setImageResource(R.drawable.multiplicacion);
               break;
            case '/':
               mImgOperador11.setImageResource(R.drawable.division);
               break;
         }

         mEditOperando21.setText(String.valueOf(operando21));
         mEditOperando22.setText(String.valueOf(operando22));

         switch( operador21 ) {
            case '+':
               mImgOperador21.setImageResource(R.drawable.suma);
               break;
            case '-':
               mImgOperador21.setImageResource(R.drawable.resta);
               break;
            case '*':
               mImgOperador21.setImageResource(R.drawable.multiplicacion);
               break;
            case '/':
               mImgOperador21.setImageResource(R.drawable.division);
               break;
         }

         mEditRespuesta.setText(String.valueOf(respuesta));
      }

      else {
         actualizar();
      }

      mImgIgual.setImageResource(R.drawable.igual);
   }

   @Override
   public void onSaveInstanceState( Bundle savedInstanceState ) {
      super.onSaveInstanceState(savedInstanceState);
      Log.i(TAG, "onSaveInstanceState");
      savedInstanceState.putInt(OPERANDO11, operando11);
      savedInstanceState.putInt(OPERANDO12, operando12);
      savedInstanceState.putChar(OPERADOR11, operador11);
      savedInstanceState.putInt(OPERANDO21, operando21);
      savedInstanceState.putInt(OPERANDO22, operando22);
      savedInstanceState.putChar(OPERADOR21, operador21);
      savedInstanceState.putInt(RESPUESTA, respuesta);
   }
}