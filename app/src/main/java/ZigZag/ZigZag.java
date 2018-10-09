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

    //devuelva la cantidad de olas que existen
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
        int resultado=0;
        char TamañoTexto[]=Texto.toCharArray();
        for (int i=0;i<TamañoTexto.length; i++)
        {
            resultado++;
        }
        return resultado;

    }

    private List<Character> ListaCaracteresTotales(){
        List<Character> lista = new ArrayList<Character>();
        char caracteres[]= Texto.toCharArray();

        for (int i=0;i<TamañoTexto(); i++){
            lista.add(caracteres[i]);
        }

        return  lista;
    }

    public List ContedionoLista(){

        List<Character> ListaResultado = new ArrayList<Character>();
        char TamañoText[]=Texto.toCharArray();
        char res=' ';

        if (!ListaCaracteresTotales().isEmpty()) {
            for (int i = 0; i < TamañoOla(); i++) {
                res = ListaCaracteresTotales().remove(i);
                ListaResultado.add(res);
            }
        }
        else {

            ListaResultado.add('1');
        }

        return ListaResultado;
    }


    public List<List<Character>> ListasCompresion(){
        String TextoCompreso="";

        List<List<Character>> ListasOlas=new ArrayList<>();

        char TamañoText[]=Texto.toCharArray();
        char res=' ';

        List<Character> Temporal= ListaCaracteresTotales();


        for (int i=0; i<=NumeroOlas(); i++){ //5
            List<Character> ListaPrueba = new ArrayList<>();

            if (!Temporal.isEmpty()) {
                for (int j = 0; j <TamañoOla()  ; j++) {

                    res = Temporal.remove(0);
                    ListaPrueba.add(res);
                    if (Temporal.isEmpty()){
                        int tamaño=TamañoOla()-ListaPrueba.size();
                        for (int h=0; h < tamaño; h++){
                            ListaPrueba.add('|');

                        }
                        //ListasOlas.add(ListaPrueba);
                        j=TamañoOla()+1;
                    }

                }

                ListasOlas.add(ListaPrueba);
                //ListaPrueba.clear();
            }
            else {

                ListaPrueba.add('1');
            }


        }



        return ListasOlas;
    }

    public String Compresion(){

        String caracter="";
        String compresion="";
        List<String> Litaaa= new ArrayList<>();


        for (int i=0; i <=ListasCompresion().get(0).size()-1; i++ ){

            caracter = ListasCompresion().get(0).get(i).toString() + ListasCompresion().get(1).get(i).toString() +
                    ListasCompresion().get(2).get(i).toString() + ListasCompresion().get(3).get(i).toString()+
            ListasCompresion().get(4).get(i).toString();


            Litaaa.add(caracter);
        }

        compresion = Litaaa.toString();
        for (int i=0;i<Litaaa.size(); i++){
            compresion=ListasCompresion().get(i).toString();
        }

        return compresion;
    }

}


