package ZigZag;

import android.provider.FontRequest;

import java.util.ArrayList;
import java.util.List;

public class DescifradoZigZag {

    public DescifradoZigZag(int niveles, String texto) {
        this.niveles = niveles;
        Texto = texto;
    }

    public int niveles;
    public String Texto;

    public int TamañoOla() {
        int resultado = 0;
        resultado = (niveles * 2) - 2;
        return resultado;
    }

    //devuelva la cantidad de olas que existen
    private int NumeroOlas() {
        int resultado = 0;
        resultado = TamañoTexto() / TamañoOla();
        return resultado;

    }

    private int TamañoBloque() {
        int resultado = 0;
        resultado = 2 * NumeroOlas();
        return resultado;

    }

    private int TamañoTexto() {
        int resultado = 0;
        char TamañoTexto[] = Texto.toCharArray();
        for (int i = 0; i < TamañoTexto.length; i++) {
            resultado++;
        }
        return resultado;

    }

    private List<Character> ListaCaracteresTotales() {
        List<Character> lista = new ArrayList<Character>();
        char caracteres[] = Texto.toCharArray();

        for (int i = 0; i < TamañoTexto(); i++) {
            lista.add(caracteres[i]);
        }

        return lista;
    }

    public List<List<Character>> ListaDescifrado() {
        List<List<Character>> ListasOlas = new ArrayList<>();
        List<Character> temporal = ListaCaracteresTotales();


        List<Character> ListaAuxiliar = new ArrayList<>();
        for (int j = 0; j < NumeroOlas(); j++) {
            ListaAuxiliar.add(temporal.remove(0));
        }
        ListasOlas.add(ListaAuxiliar);


        ListaAuxiliar = new ArrayList<>();
        int CantidadBloques = temporal.size() / TamañoBloque();
        for (int i = 0; i < CantidadBloques; i++) {
            ListaAuxiliar = new ArrayList<>();
            for (int j = 0; j < TamañoBloque(); j++) {
                ListaAuxiliar.add(temporal.remove(0));
            }
            ListasOlas.add(ListaAuxiliar);
        }


        ListaAuxiliar = new ArrayList<>();
        int Tamaño = temporal.size() - 1;
        for (int j = 0; j <= Tamaño; j++) {
            ListaAuxiliar.add(temporal.remove(0));
        }
        ListasOlas.add(ListaAuxiliar);


        return ListasOlas;
    }

    public String Descifrado() {

        String descifrado = "";
        List<List<Character>> temporal = ListaDescifrado();

        for (int i = 0; i < temporal.get(0).size() - 1; i++) {


            for (int j = 0; j <= temporal.get(0).size()-1; j++) {
                if (j == 0 && i == 0) {
                    for (int x = 0; x < temporal.get(0).size()-1; x++) {
                        descifrado = descifrado + temporal.get(x).remove(0).toString();
                    }
                    j=j+1;
                }

                for (int h=j; h< temporal.get(0).size()-1; h --){
                    if (h>=0) {
                        descifrado = descifrado + temporal.get(h).remove(0).toString();
                    }
                    else{
                        h= temporal.get(0).size();
                    }
                }

            }


        }
        return descifrado;
    }

}
