package CIF_RSA;

public class CifradoRSA {

    private int Valor;
    private int[] llave;

    public CifradoRSA(int valor, int[] llave) {
        this.Valor = valor;
        this.llave = llave;
    }

    public int Cifrado(){
        int cifrado=0;


        int n = llave[2];
        int e = llave[3];

        int base = Valor;
        int exponente = e;
        int resultado = (int)Math.pow(base,exponente);

        cifrado = resultado % (n);

        return cifrado;
    }

}
