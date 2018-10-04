package ZigZag;

import java.util.ArrayList;
import java.util.List;

public class ZigZag {


    public ZigZag(int niveles, String texto) {
        this.niveles = niveles;
        Texto = texto;
    }

    public int niveles;
    public String Texto;

    public int TamañoOla(){
        int resultado=0;
        resultado = (niveles*2)-2;
        return resultado;
    }

    private int NumeroOlas(){
        int resultado=0;
        resultado = TamañoTexto()/TamañoOla();
        return resultado;

    }

    private int TamañoBloque(){
        int resultado=0;
        resultado = 2*NumeroOlas();
        return resultado;

    }

    private int TamañoTexto(){
        int resultado=1;
        char TamañoTexto[]=Texto.toCharArray();
        for (int i=0;i<TamañoTexto.length; i++)
        {
            resultado++;
        }
        return resultado;

    }

    private List ContediooLAS(){
        List<Character> ListaResultado = new ArrayList<Character>();
        char TamañoTexto[]=Texto.toCharArray();
        char a=' ';
        for (int i=0; i < TamañoOla(); i++){
            ListaResultado=(TamañoTexto[i]==' ')? ListaResultado.add(): ListaResultado.add(TamañoTexto[i]);
        }
        return ListaResultado;
    }


}
