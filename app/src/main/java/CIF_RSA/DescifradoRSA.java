package CIF_RSA;

import java.math.BigInteger;

public class DescifradoRSA {


    private int Valor;
    private int[] llave;

    public DescifradoRSA(int valor, int[] llave) {
        this.Valor = valor;
        this.llave = llave;
    }

    public BigInteger pow(BigInteger base, BigInteger exponent) {
        BigInteger result = BigInteger.ONE;
        while (exponent.signum() > 0) {
            if (exponent.testBit(0)) result = result.multiply(base);
            base = base.multiply(base);
            exponent = exponent.shiftRight(1);
        }
        return result;
    }

    public BigInteger Descifrado(){
        //BigInteger N = new BigInteger("0");

        int n = llave[0];
        int d = llave[1];

        int base = Valor;
        int exponente = d;

        BigInteger no = new BigInteger(Integer.toString(n));
        BigInteger val =pow(new BigInteger(Integer.toString(base)), new BigInteger(Integer.toString(exponente)) );
        BigInteger N = val.mod(no);




        return N;
    }


}
