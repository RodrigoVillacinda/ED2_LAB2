package S_DES;

import java.util.function.BinaryOperator;

public class CifradoSDES {

    private int Key[];
    private int K1[];
    private int K2[];

    public CifradoSDES(int[] key, int[] k1, int[] k2) {
        Key = key;
        K1 = k1;
        this.K2 = k2;
    }


    //paso2
    private int[] EightPermutations(){
        int eightpermutations[] = new int[8];

        eightpermutations[0] = Key[1]; //2
        eightpermutations[1] = Key[5]; //6
        eightpermutations[2] = Key[2]; //3
        eightpermutations[3] = Key[0]; //1
        eightpermutations[4] = Key[3]; //4
        eightpermutations[5] = Key[7]; //8
        eightpermutations[6] = Key[5]; //5
        eightpermutations[7] = Key[6]; //7

        return eightpermutations;
    }

    //paso3, dividir izquierda
    private int[] LeftBits(){

        int[] leftbits=new int[4];

        leftbits[0] = EightPermutations()[0];
        leftbits[1] = EightPermutations()[1];
        leftbits[2] = EightPermutations()[2];
        leftbits[3] = EightPermutations()[3];


        return leftbits;

    }

    //paso4, dividir derecha Tamño: 4 bits
    private int[] RightBits(){

        int[] righbits=new int[4];

        righbits[0] = EightPermutations()[4];
        righbits[1] = EightPermutations()[5];
        righbits[2] = EightPermutations()[6];
        righbits[3] = EightPermutations()[7];


        return righbits;

    }

    //paso5, expandir y pemutar, OP
    private int[] ExpandRigh(){
        int[] expandright= new int[8];

        expandright[0] = RightBits()[3]; //4
        expandright[1] = RightBits()[0]; //1
        expandright[2] = RightBits()[1]; //2
        expandright[3] = RightBits()[2]; //3
        expandright[4] = RightBits()[1]; //2
        expandright[5] = RightBits()[2]; //3
        expandright[6] = RightBits()[3]; //4
        expandright[7] = RightBits()[0]; //1


        return expandright;
    }

    //paso6 opXORk1
    private int[] OPXORK1(){
        int[] opxork1= new int[8];
        int[] k1=K1;

        opxork1[0] = ExpandRigh()[0] ^ k1[0];
        opxork1[1] = ExpandRigh()[1] ^ k1[1];
        opxork1[2] = ExpandRigh()[2] ^ k1[2];
        opxork1[3] = ExpandRigh()[3] ^ k1[3];
        opxork1[4] = ExpandRigh()[4] ^ k1[4];
        opxork1[5] = ExpandRigh()[5] ^ k1[5];
        opxork1[6] = ExpandRigh()[6] ^ k1[6];
        opxork1[7] = ExpandRigh()[7] ^ k1[7];

        return opxork1;
    }

    private int[] S0(){
        int[] s0 = new int[2];
        int[] opxork1 = new int[8];
        int row=0;
        int column=0;

        int roww=0;
        int columnn=0;

        opxork1[0] = OPXORK1()[0];
        opxork1[1] = OPXORK1()[1];
        opxork1[2] = OPXORK1()[2];
        opxork1[3] = OPXORK1()[3];

        int[][] box0=new int[4][4];
        box0[0][0]=01;
        box0[0][1]=00;
        box0[0][2]=11;
        box0[0][3]=10;

        box0[1][0]=11;
        box0[1][1]=00;
        box0[1][2]=01;
        box0[1][3]=00;

        box0[2][0]=00;
        box0[2][1]=10;
        box0[2][2]=01;
        box0[2][3]=11;

        box0[3][0]=11;
        box0[3][1]=01;
        box0[3][2]=11;
        box0[3][3]=00;


        int o1 = opxork1[0];
        int o2 = opxork1[3];

        row = (o1*2) + (o2*1); //2


        int i1 = opxork1[1];
        int i2 = opxork1[2];

        column =(i1*2) + (i2*1); //3

        roww = box0[row][column]; //00

        char[] rowColumn = Integer.toString(roww).toCharArray();

        for (int i=0; i<3; i++){

            columnn=rowColumn[i];
            s0[i] = columnn;

        }

        return  s0;
    }

    private int[] S1(){
        int[] s0 = new int[2];
        int[] opxork1 = new int[8];
        int row=0;
        int column=0;

        int roww=0;
        int columnn=0;

        opxork1[0] = OPXORK1()[4];
        opxork1[1] = OPXORK1()[5];
        opxork1[2] = OPXORK1()[6];
        opxork1[3] = OPXORK1()[7];

        int[][] box0=new int[4][4];
        box0[0][0]=01;
        box0[0][1]=00;
        box0[0][2]=11;
        box0[0][3]=10;

        box0[1][0]=11;
        box0[1][1]=00;
        box0[1][2]=01;
        box0[1][3]=00;

        box0[2][0]=00;
        box0[2][1]=10;
        box0[2][2]=01;
        box0[2][3]=11;

        box0[3][0]=11;
        box0[3][1]=01;
        box0[3][2]=11;
        box0[3][3]=00;


        int o1 = opxork1[0];
        int o2 = opxork1[3];

        row = (o1*2) + (o2*1); //2


        int i1 = opxork1[1];
        int i2 = opxork1[2];

        column =(i1*2) + (i2*1); //3

        roww = box0[row][column]; //00

        char[] rowColumn = Integer.toString(roww).toCharArray();

        for (int i=0; i<3; i++){

            columnn=rowColumn[i];
            s0[i] = columnn;

        }

        return  s0;
    }

    private int[] S0andS1(){
        int[] s0ands1 = new int[4];

        s0ands1[0] = S0()[0];
        s0ands1[1] = S0()[1];
        s0ands1[2] = S1()[0];
        s0ands1[3] = S1()[1];

        return s0ands1;
    }

    //PASO7: permutación de 4 bits, sobre la union de la box0 y box1
    private int[] FourPermutations(){
        int[] fourpermutations= new int[4];

        fourpermutations[0] = S0andS1()[1]; //2
        fourpermutations[1] = S0andS1()[3]; //4
        fourpermutations[2] = S0andS1()[2]; //3
        fourpermutations[3] = S0andS1()[0]; //1

        return fourpermutations;
    }

    //PASO8: Paso3 XOR paso 7, tamño: 4 bits
    private int[] LeftbitsXORFourpermutations(){
        int[] leftbitsXORfourpemutations = new int[4];

        leftbitsXORfourpemutations[0] = FourPermutations()[0] ^ LeftBits()[0];
        leftbitsXORfourpemutations[1] = FourPermutations()[1] ^ LeftBits()[1];
        leftbitsXORfourpemutations[2] = FourPermutations()[2] ^ LeftBits()[2];
        leftbitsXORfourpemutations[3] = FourPermutations()[3] ^ LeftBits()[3];

        return leftbitsXORfourpemutations;
    }

    //PASO9: paso8 + permutación de 8 a la derecha || Tamaño: 8bits
    private int[] XORandRightBits(){
        int[] xorANDrightbits = new int[8];

        xorANDrightbits[0] = LeftbitsXORFourpermutations()[0];
        xorANDrightbits[1] = LeftbitsXORFourpermutations()[1];
        xorANDrightbits[2] = LeftbitsXORFourpermutations()[2];
        xorANDrightbits[3] = LeftbitsXORFourpermutations()[3];

        xorANDrightbits[4] = RightBits()[0];
        xorANDrightbits[5] = RightBits()[1];
        xorANDrightbits[6] = RightBits()[2];
        xorANDrightbits[7] = RightBits()[3];

        return xorANDrightbits;
    }

    //PASO10: mitad izquierdad del paso9 || Tamaaño: 4 bits
    private int[] LeftBitsXOR(){

        int[] righbits=new int[4];

        righbits[0] = XORandRightBits()[0];
        righbits[1] = XORandRightBits()[1];
        righbits[2] = XORandRightBits()[2];
        righbits[3] = XORandRightBits()[3];


        return righbits;

    }

    //PASO11: mitad derecha del paso9 || Tamaaño: 4 bits
    private int[] RightBitsXOR(){

        int[] righbits=new int[4];

        righbits[0] = XORandRightBits()[4];
        righbits[1] = XORandRightBits()[5];
        righbits[2] = XORandRightBits()[6];
        righbits[3] = XORandRightBits()[7];


        return righbits;

    }

    //PASO12: invertir y unir || Tamaño: 8 bits
    private int[] EightBits(){
        int[] eightbits = new int[8];

        eightbits[0] = RightBitsXOR()[0];
        eightbits[1] = RightBitsXOR()[1];
        eightbits[2] = RightBitsXOR()[2];
        eightbits[3] = RightBitsXOR()[3];

        eightbits[4] = LeftBitsXOR()[0];
        eightbits[5] = LeftBitsXOR()[1];
        eightbits[6] = LeftBitsXOR()[2];
        eightbits[7] = LeftBitsXOR()[3];


        return eightbits;
    }


    //----------------------------------------------------KEY 2--------------------------------------------------------------------

    //PASO12: permutar paso11 || Tamaño: 8 bits
    private int[] EightPermutationsI(){
        int eightpermutations[] = new int[8];

        eightpermutations[0] = EightBits()[1]; //2
        eightpermutations[1] = EightBits()[5]; //6
        eightpermutations[2] = EightBits()[2]; //3
        eightpermutations[3] = EightBits()[0]; //1
        eightpermutations[4] = EightBits()[3]; //4
        eightpermutations[5] = EightBits()[7]; //8
        eightpermutations[6] = EightBits()[5]; //5
        eightpermutations[7] = EightBits()[6]; //7

        return eightpermutations;
    }

    //paso13, dividir izquierda
    private int[] LeftBitsK2(){

        int[] leftbits=new int[4];

        leftbits[0] = EightPermutationsI()[0];
        leftbits[1] = EightPermutationsI()[1];
        leftbits[2] = EightPermutationsI()[2];
        leftbits[3] = EightPermutationsI()[3];


        return leftbits;

    }

    //paso14, dividir derecha Tamño: 4 bits
    private int[] RightBitsK2(){

        int[] righbits=new int[4];

        righbits[0] = EightPermutationsI()[4];
        righbits[1] = EightPermutationsI()[5];
        righbits[2] = EightPermutationsI()[6];
        righbits[3] = EightPermutationsI()[7];


        return righbits;

    }

    //paso15, expandir y pemutar, OP
    private int[] ExpandRighK2(){
        int[] expandright= new int[8];

        expandright[0] = RightBitsK2()[3]; //4
        expandright[1] = RightBitsK2()[0]; //1
        expandright[2] = RightBitsK2()[1]; //2
        expandright[3] = RightBitsK2()[2]; //3
        expandright[4] = RightBitsK2()[1]; //2
        expandright[5] = RightBitsK2()[2]; //3
        expandright[6] = RightBitsK2()[3]; //4
        expandright[7] = RightBitsK2()[0]; //1


        return expandright;
    }

    //paso16 opXORk1
    private int[] OPXORK2(){
        int[] opxork1= new int[8];
        int[] k1=K1;

        opxork1[0] = K2[0] ^ ExpandRighK2()[0];
        opxork1[1] = K2[1] ^ ExpandRighK2()[1];
        opxork1[2] = K2[2] ^ ExpandRighK2()[2];
        opxork1[3] = K2[3] ^ ExpandRighK2()[3];
        opxork1[4] = K2[4] ^ ExpandRighK2()[4];
        opxork1[5] = K2[5] ^ ExpandRighK2()[5];
        opxork1[6] = K2[6] ^ ExpandRighK2()[6];
        opxork1[7] = K2[7] ^ ExpandRighK2()[7];

        return opxork1;
    }

    private int[] S0K2(){
        int[] s0 = new int[2];
        int[] opxork1 = new int[8];
        int row=0;
        int column=0;

        int roww=0;
        int columnn=0;

        opxork1[0] = OPXORK2()[0];
        opxork1[1] = OPXORK2()[1];
        opxork1[2] = OPXORK2()[2];
        opxork1[3] = OPXORK2()[3];

        int[][] box0=new int[4][4];
        box0[0][0]=01;
        box0[0][1]=00;
        box0[0][2]=11;
        box0[0][3]=10;

        box0[1][0]=11;
        box0[1][1]=00;
        box0[1][2]=01;
        box0[1][3]=00;

        box0[2][0]=00;
        box0[2][1]=10;
        box0[2][2]=01;
        box0[2][3]=11;

        box0[3][0]=11;
        box0[3][1]=01;
        box0[3][2]=11;
        box0[3][3]=00;


        int o1 = opxork1[0];
        int o2 = opxork1[3];

        row = (o1*2) + (o2*1); //2


        int i1 = opxork1[1];
        int i2 = opxork1[2];

        column =(i1*2) + (i2*1); //3

        roww = box0[row][column]; //00

        char[] rowColumn = Integer.toString(roww).toCharArray();

        for (int i=0; i<3; i++){

            columnn=rowColumn[i];
            s0[i] = columnn;

        }

        return  s0;
    }

    private int[] S1K2(){
        int[] s0 = new int[2];
        int[] opxork1 = new int[8];
        int row=0;
        int column=0;

        int roww=0;
        int columnn=0;

        opxork1[0] = OPXORK2()[4];
        opxork1[1] = OPXORK2()[5];
        opxork1[2] = OPXORK2()[6];
        opxork1[3] = OPXORK2()[7];

        int[][] box0=new int[4][4];
        box0[0][0]=01;
        box0[0][1]=00;
        box0[0][2]=11;
        box0[0][3]=10;

        box0[1][0]=11;
        box0[1][1]=00;
        box0[1][2]=01;
        box0[1][3]=00;

        box0[2][0]=00;
        box0[2][1]=10;
        box0[2][2]=01;
        box0[2][3]=11;

        box0[3][0]=11;
        box0[3][1]=01;
        box0[3][2]=11;
        box0[3][3]=00;


        int o1 = opxork1[0];
        int o2 = opxork1[3];

        row = (o1*2) + (o2*1); //2


        int i1 = opxork1[1];
        int i2 = opxork1[2];

        column =(i1*2) + (i2*1); //3

        roww = box0[row][column]; //00

        char[] rowColumn = Integer.toString(roww).toCharArray();

        for (int i=0; i<3; i++){

            columnn=rowColumn[i];
            s0[i] = columnn;

        }

        return  s0;
    }

    private int[] S0andS1K2(){
        int[] s0ands1 = new int[4];

        s0ands1[0] = S0K2()[0];
        s0ands1[1] = S0K2()[1];
        s0ands1[2] = S1K2()[0];
        s0ands1[3] = S1K2()[1];

        return s0ands1;
    }

    //PASO17: permutación de 4 bits, sobre la union de la box0 y box1
    private int[] FourPermutationsK2(){
        int[] fourpermutations= new int[4];

        fourpermutations[0] = S0andS1K2()[1]; //2
        fourpermutations[1] = S0andS1K2()[3]; //4
        fourpermutations[2] = S0andS1K2()[2]; //3
        fourpermutations[3] = S0andS1K2()[0]; //1

        return fourpermutations;
    }

    //PASO18: Paso3 XOR paso 7, tamño: 4 bits
    private int[] LeftbitsXORFourpermutationsK2(){
        int[] leftbitsXORfourpemutations = new int[4];

        leftbitsXORfourpemutations[0] = FourPermutations()[0] ^ LeftBitsK2()[0];
        leftbitsXORfourpemutations[1] = FourPermutations()[1] ^ LeftBitsK2()[1];
        leftbitsXORfourpemutations[2] = FourPermutations()[2] ^ LeftBitsK2()[2];
        leftbitsXORfourpemutations[3] = FourPermutations()[3] ^ LeftBitsK2()[3];

        return leftbitsXORfourpemutations;
    }

    //PASO19: paso18 + permutación de 8 a la derecha || Tamaño: 8bits
    private int[] XORandRightBitsK2(){
        int[] xorANDrightbits = new int[8];

        xorANDrightbits[0] = LeftbitsXORFourpermutationsK2()[0];
        xorANDrightbits[1] = LeftbitsXORFourpermutationsK2()[1];
        xorANDrightbits[2] = LeftbitsXORFourpermutationsK2()[2];
        xorANDrightbits[3] = LeftbitsXORFourpermutationsK2()[3];

        xorANDrightbits[4] = RightBitsK2()[0];
        xorANDrightbits[5] = RightBitsK2()[1];
        xorANDrightbits[6] = RightBitsK2()[2];
        xorANDrightbits[7] = RightBitsK2()[3];

        return xorANDrightbits;
    }

    //PASO20: mitad izquierdad del paso9 || Tamaaño: 4 bits
    private int[] LeftBitsXORK2(){

        int[] righbits=new int[4];

        righbits[0] = XORandRightBitsK2()[0];
        righbits[1] = XORandRightBitsK2()[1];
        righbits[2] = XORandRightBitsK2()[2];
        righbits[3] = XORandRightBitsK2()[3];


        return righbits;

    }

    //PASO21: mitad derecha del paso9 || Tamaaño: 4 bits
    private int[] RightBitsXORK2(){

        int[] righbits=new int[4];

        righbits[0] = XORandRightBitsK2()[4];
        righbits[1] = XORandRightBitsK2()[5];
        righbits[2] = XORandRightBitsK2()[6];
        righbits[3] = XORandRightBitsK2()[7];

        return righbits;

    }

    //PASO22: invertir y unir || Tamaño: 8 bits
    private int[] EightBitsK2(){
        int[] eightbits = new int[8];

        eightbits[0] = RightBitsXORK2()[0];
        eightbits[1] = RightBitsXORK2()[1];
        eightbits[2] = RightBitsXORK2()[2];
        eightbits[3] = RightBitsXORK2()[3];

        eightbits[4] = LeftBitsXORK2()[0];
        eightbits[5] = LeftBitsXORK2()[1];
        eightbits[6] = LeftBitsXORK2()[2];
        eightbits[7] = LeftBitsXORK2()[3];


        return eightbits;
    }

    private int[] EightPermutationsIK2(){
        int eightpermutations[] = new int[8];

        eightpermutations[0] = EightBitsK2()[1]; //2
        eightpermutations[1] = EightBitsK2()[5]; //6
        eightpermutations[2] = EightBitsK2()[2]; //3
        eightpermutations[3] = EightBitsK2()[0]; //1
        eightpermutations[4] = EightBitsK2()[3]; //4
        eightpermutations[5] = EightBitsK2()[7]; //8
        eightpermutations[6] = EightBitsK2()[5]; //5
        eightpermutations[7] = EightBitsK2()[6]; //7

        return eightpermutations;
    }



}
