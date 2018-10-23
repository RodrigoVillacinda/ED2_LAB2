package CIF_RSA;

public class DescifradoRSA {


    private int Valor;
    private int[] llave;

    public DescifradoRSA(int valor, int[] llave) {
        this.Valor = valor;
        this.llave = llave;
    }

    public int Descifrado(){
        int N=0;

        int n = llave[0];
        int d = llave[1];

        int base = Valor;
        int exponente = d;
        int resultado = (int)Math.pow(base,exponente);

        N = resultado % (n);

        return N;
    }


}
