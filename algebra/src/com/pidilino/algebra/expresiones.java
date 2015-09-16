package com.pidilino.algebra;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.*;
import org.w3c.dom.Text;

import java.util.Random;

// Problemas de expresiones algebraicas
public class expresiones extends Activity {

   private static final String TAG = "expresiones";
   private static final String EXPRESIÓN = "expresión";
   private static final String VALOR_X = "valor_x";
   private static final String RESPUESTA = "respuesta";
   private static final String RESULTADO = "resultado";

   private Button mBtnAceptar;
   private Button mBtnSiguiente;
   private android.widget.TextView mTextExpresión;
   private EditText mEditEncuentraValor;
   private EditText mEditRespuesta;

   private String expresión;
   private int valor_x, resultado, respuesta;

   Random brandon = new Random();

   public void actualizar() {
      int número = 0;
      boolean siguiente_operación = true;

      expresión = "";
      resultado = 0;
      valor_x = brandon.nextInt(10) + 1;

      // Variable al cubo
      if( (número = brandon.nextInt(10)) > 0 ) {
         expresión += ((número > 1) ? String.valueOf(número) : "") + "x^3";
         resultado = (int) (número * Math.pow(valor_x, 3));
         // La siguiente operación es suma (true) o resta (false)
         siguiente_operación = brandon.nextBoolean();
      }

      // Variable al cuadrado
      if( (número = brandon.nextInt(10)) > 0 ) {
         if( siguiente_operación ) {
            expresión += " + " + ((número > 1) ? String.valueOf(número) : "") + "x^2";
            resultado += número * valor_x * valor_x;
         }
         else {
            expresión += " - " + ((número > 1) ? String.valueOf(número) : "") + "x^2";
            resultado -= número * valor_x * valor_x;
         }
         // La siguiente operación es suma (true) o resta (false)
         siguiente_operación = brandon.nextBoolean();
      }

      // Variable multiplicada por una constante
      if( (número = brandon.nextInt(10)) > 0 ) {
         if( siguiente_operación ) {
            expresión += " + " + ((número > 1) ? String.valueOf(número) : "") + "x";
            resultado += número * valor_x;
         }
         else {
            expresión += " - " + ((número > 1) ? String.valueOf(número) : "") + "x";
            resultado -= número * valor_x;
         }
         // La siguiente operación es suma (true) o resta (false)
         siguiente_operación = brandon.nextBoolean();
      }

      // Constante solitaria
      if( (número = brandon.nextInt(10)) > 0 ) {
         if (siguiente_operación) {
            expresión += " + " + String.valueOf(número);
            resultado += número;
         } else {
            expresión += " - " + String.valueOf(número);
            resultado -= número;
         }
      }
      // Indicar al usuario cómo evaluar la expresión
      mEditEncuentraValor.setText("Resuelve la expresión para x = " + String.valueOf(valor_x));

      // Importante: Mostrar la expresión al usuario
      if( expresión.startsWith(" ") )
         expresión = expresión.substring(3);
      mTextExpresión.setText(expresión);

      // Resetear el valor de respuesta
      mEditRespuesta.setText("");
   }

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.expresiones);

      mBtnAceptar = (Button) findViewById(R.id.btnAceptar);
      mBtnAceptar.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            respuesta = 0;
            try {
               respuesta = Integer.parseInt(mEditRespuesta.getText().toString());
            } catch (Exception e1) {
               e1.printStackTrace();
            }
            if (respuesta == resultado) {
               Toast.makeText(expresiones.this, R.string.msjCorrecto, Toast.LENGTH_SHORT).show();
            } else {
               Toast.makeText(expresiones.this, R.string.msjIncorrecto, Toast.LENGTH_SHORT).show();
            }
            actualizar();
         }
      });

      mBtnSiguiente = (Button)findViewById(R.id.btnSiguiente);
      mBtnSiguiente.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) { actualizar(); }
      });

      mEditEncuentraValor = (EditText) findViewById(R.id.editEncuentraValor);
      mEditRespuesta = (EditText) findViewById(R.id.editRespuesta);
      mTextExpresión = (TextView) findViewById(R.id.textExpresión);

      mTextExpresión.setTextSize(TypedValue.COMPLEX_UNIT_PX,
            getResources().getDimension(R.dimen.text_large));

      //getResources().getDimensionPixelSize(R.dimen.textSize);

      if (savedInstanceState != null) {
         // Recuperar valores almacenados en vista previa
         expresión = savedInstanceState.getString(EXPRESIÓN);
         valor_x = savedInstanceState.getChar(VALOR_X);
         resultado = savedInstanceState.getInt(RESULTADO);
         respuesta = savedInstanceState.getInt(RESPUESTA);

         // Mostrar la ecuación en el TextView
         mTextExpresión.setText(expresión);
         mTextExpresión.setTypeface(null, Typeface.BOLD_ITALIC);

         // Indicar al usuario cómo evaluar la expresión
         mEditEncuentraValor.setText("Resuelve la expresión para x = " + String.valueOf(valor_x));

         // Respuesta dada previamente
         mEditRespuesta.setText(String.valueOf(respuesta));
      }
      else
         actualizar();

   }

   @Override
   public void onSaveInstanceState( Bundle savedInstanceState ) {
      super.onSaveInstanceState(savedInstanceState);
      Log.i(TAG, "onSaveInstanceState");
      savedInstanceState.putString(EXPRESIÓN, expresión);
      savedInstanceState.putInt(RESPUESTA, respuesta);
      savedInstanceState.putInt(RESULTADO, resultado);
      savedInstanceState.putInt(VALOR_X, valor_x);
   }
}