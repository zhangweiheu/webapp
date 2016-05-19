package com.data.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScoreLabelDirectRecommend {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreLabelDirectRecommend.class);

    private int[][] aa;
    private int[][] L;
    private int[] currentTeam;
    private int i0;
    private boolean prune;
    public double[][]score;

    public ScoreLabelDirectRecommend(int[][] aa,int[][] L,int[] currentTeam,int i0,boolean prune){
        this.aa = aa;
        this.L = L;
        this.currentTeam = currentTeam;
        this.i0 = i0;
        this.prune = prune;
    }
    public double[][] label_direct_recommend(){

        int mapsize = aa.length;
        int []remainTeam = this.setdiff(currentTeam, i0);
        if(remainTeam == null){
            return null;
        }
        for(int i = 0; i < remainTeam.length;i++){
            currentTeam[i] = remainTeam[i];
        }
        currentTeam[currentTeam.length - 1] = i0;

        int [][]A1 = subMatrix(this.aa,this.currentTeam,this.currentTeam);
        //cand = setdiff((1:n),currentTeam);
        int []orignalTeam = new int[mapsize];
        for(int i = 0; i < mapsize;i++){
            orignalTeam[i] = i+1;
        }
        int []cand = new int[mapsize - currentTeam.length];
        int index = 0;
        for(int i = 0; i < mapsize;i++){
            boolean isIn = false;
            for(int j = 0; j < currentTeam.length;j++){
                if(currentTeam[j] == orignalTeam[i]){
                    isIn = true;
                }
            }
            if(!isIn){
                cand[index] = orignalTeam[i];
                index++;
            }
        }
        ////
        if(prune){
            int[][]tempMatrxix = sumColum(subMatrix(this.aa,cand,remainTeam));
            int []tempCand;
            int length = 0;;
            for(int i = 0; i < tempMatrxix.length;i++){
                if(tempMatrxix[i][0] > 0){
                    length++;
                }
            }
            tempCand = new int[length];
            int indexcand= 0;
            for(int i = 0; i < tempMatrxix.length;i++){
                if(tempMatrxix[i][0] > 0){
                    tempCand[indexcand] = cand[i];
                    indexcand++;
                }
            }
            cand = tempCand;
        }
        //
        double  c = 0.00000001;
        //
        int dn = L[0].length;
        this.score = zeros(cand.length,2);
        //

        for(int i = 0; i < cand.length;i++){
            int []newTeam = new int[remainTeam.length + 1];
            for(int k = 0; k <remainTeam.length;k++ ){
                newTeam[k] = remainTeam[k];
            }
            newTeam[newTeam.length - 1] = cand[i];
            int[][] LL = zeros(newTeam.length*newTeam.length);
            for(int j = 0; j < dn;j++){
                LL = sumMatrix(LL,kron(duijiaoMatixx(this.L,currentTeam,j),duijiaoMatixx(this.L,newTeam,j)));

            }
            int [][]A2 = subMatrix(this.aa,newTeam,newTeam);
            this.score[i][0] = label_gs(A1,A2,LL,c);
            this.score[i][1] = cand[i];

        }
return score;

    }
    private int[] setdiff(int[]currentTeam, int i0){

        boolean isexits = false;
        for(int i = 0; i < currentTeam.length;i++){
            if(i0 == currentTeam[i]){
                isexits = true;
            }
        }
        if(isexits){
            int []remainTeam = new int[currentTeam.length - 1];
            int index = 0;
            for(int i = 0; i < currentTeam.length;i++){
                if(i0 != currentTeam[i]){
                    remainTeam[index] = currentTeam[i];
                    index++;
                }
            }
            return remainTeam;
        }
        else
        {
            LOGGER.error("出问题了1");
            return null;
        }

    }
    private int[][] subMatrix(int [][]aa, int []currentTeamRow, int []currentTeamColumn){
        int [][]sMatrix = new int[currentTeamRow.length][currentTeamColumn.length];

        sortMatrix(currentTeamRow);
        sortMatrix(currentTeamColumn);

        //todo 循环出问题
        for(int i = 0;i <currentTeamRow.length;i++){
            for(int j = 0; j < currentTeamColumn.length;j++){
                sMatrix[i][j] = aa[currentTeamRow[i] - 1][currentTeamColumn[j] - 1];
            }
        }
        return sMatrix;
    }
    private void sortMatrix(int []currentTeam){
        for(int i = 0; i < currentTeam.length; i++){
            for(int j = 1; j <  currentTeam.length - i; j++){
                if(currentTeam[j] < currentTeam[j - 1]){
                    int temp = currentTeam[j];
                    currentTeam[j] = currentTeam[j - 1];
                    currentTeam[j - 1] = temp;
                }
            }
        }
    }
    private int[][] sumColum(int [][]matrix){
        int [][]sumColumnMatrix = new int[matrix.length][1];
        for(int i = 0; i < matrix.length;i++){
            int sum = 0;
            for(int j = 0; j < matrix[i].length;j++){
                sum += matrix[i][j];
            }
            sumColumnMatrix[i][0] = sum;
        }
        return sumColumnMatrix;
    }
    private double[][] zeros(int row,int column){
        double [][]zerosMatrix = new double[row][column];
        return zerosMatrix;
    }
    private int[][]zeros(int size){
        int [][]zerosMatrix = new int[size][size];
        return zerosMatrix;
    }
    private int[][]duijiaoMatixx(int[][]theL, int[]currentTeam,int j){
        int[][]newTema = new int[currentTeam.length][currentTeam.length];
        for(int i = 0; i < currentTeam.length;i++){
            if(currentTeam[i] - 1 < 0 || currentTeam[i] - 1 > theL.length){
                System.out.println("row: "+ (currentTeam[i] - 1));
            }
            if(j < 0 || j > theL[0].length ){
                System.out.println("column: "+ j);
            }
            newTema[i][i] = theL[currentTeam[i] - 1][j];
        }
        return newTema;
    }
    private int[][]kron(int [][]matrixA, int[][]matrixB){
        int row = matrixA.length*matrixB.length;
        int column = matrixA[0].length*matrixB[0].length;
        int [][]tempKron = new int[row][column];

        int rowindex = 0;
        int columnindex = 0;
        int kb = 0;
        for(int i = 0; i < matrixA.length;){
            for(int j = 0; j <matrixA[i].length;j++){
                for(int kc = 0; kc <matrixB[kb].length;kc++){
                    tempKron[rowindex][columnindex] = matrixA[i][j]*matrixB[kb][kc];
                    columnindex++;
                }
            }
            kb++;
            rowindex++;
            columnindex = 0;
            if(kb == matrixB.length){
                kb = 0;
                i++;
            }
        }
        return tempKron;
    }
    private double[][]kron(double [][]matrixA, double[][]matrixB){
        int row = matrixA.length*matrixB.length;
        int column = matrixA[0].length*matrixB[0].length;
        double [][]tempKron = new double[row][column];

        int rowindex = 0;
        int columnindex = 0;
        int kb = 0;
        for(int i = 0; i < matrixA.length;){
            for(int j = 0; j <matrixA[i].length;j++){
                for(int kc = 0; kc <matrixB[kb].length;kc++){
                    tempKron[rowindex][columnindex] = matrixA[i][j]*matrixB[kb][kc];
                    columnindex++;
                }
            }
            kb++;
            rowindex++;
            columnindex = 0;
            if(kb == matrixB.length){
                kb = 0;
                i++;
            }
        }
        return tempKron;
    }

    private double[][]kron(double [][]matrixA, int[][]matrixB){
        int row = matrixA.length*matrixB.length;
        int column = matrixA[0].length*matrixB[0].length;
        double [][]tempKron = new double[row][column];

        int rowindex = 0;
        int columnindex = 0;
        int kb = 0;
        for(int i = 0; i < matrixA.length;){
            for(int j = 0; j <matrixA[i].length;j++){
                for(int kc = 0; kc <matrixB[kb].length;kc++){
                    tempKron[rowindex][columnindex] = matrixA[i][j]*matrixB[kb][kc];
                    columnindex++;
                }
            }
            kb++;
            rowindex++;
            columnindex = 0;
            if(kb == matrixB.length){
                kb = 0;
                i++;
            }
        }
        return tempKron;
    }
    private double[][]kron(int [][]matrixA, double[][]matrixB){
        int row = matrixA.length*matrixB.length;
        int column = matrixA[0].length*matrixB[0].length;
        double [][]tempKron = new double[row][column];

        int rowindex = 0;
        int columnindex = 0;
        int kb = 0;
        for(int i = 0; i < matrixA.length;){
            for(int j = 0; j <matrixA[i].length;j++){
                for(int kc = 0; kc <matrixB[kb].length;kc++){
                    tempKron[rowindex][columnindex] = matrixA[i][j]*matrixB[kb][kc];
                    columnindex++;
                }
            }
            kb++;
            rowindex++;
            columnindex = 0;
            if(kb == matrixB.length){
                kb = 0;
                i++;
            }
        }
        return tempKron;
    }
    private int[][]sumMatrix(int [][]matrixA, int [][]matrixB){
        int [][]sumM;
        if(matrixA.length == matrixB.length && matrixA[0].length ==matrixB[0].length ){
            sumM = new int[matrixB.length][matrixB.length];
            for(int i = 0; i < matrixB.length;i++){
                for(int j = 0; j <matrixB[i].length;j++){
                    sumM[i][j] = matrixA[i][j] +matrixB[i][j];
                }
            }
        }
        else
        {

            System.out.println("Á½¾ØÕóÏà¼ÓÎ¬¶È²»Ò»ÖÂ£¡");
            System.out.println(matrixA.length + "  Ma£¡ " + matrixA[0].length);
            System.out.println(matrixB.length + "  Mb£¡ " + matrixB[0].length);
            return null;
        }
        return sumM;
    }
    private double label_gs(int [][]A, int [][]B, int [][]L, double c){

        int n1 = A.length;
        int n2 = B.length;

        double [][]p1 = divisionMatrix(ones(n1,1),n1);
        double [][]p2 = divisionMatrix(ones(n2,1),n2);
        double [][]q1 = divisionMatrix(ones(n1,1),n1);
        double [][]q2 = divisionMatrix(ones(n2,1),n2);
        int [][]X = kron(A,B);
        double [][]qx = kron(q1,q2);
        double [][]px = kron(p1,p2);
        double [][]eyeMatrix = eye(n1*n2);
        double [][]tempMatrix = multiplyMatrix(multiplyMatrix(c,L),X);
        double [][]tempInvesMatrix = subtractMatrix(eyeMatrix,tempMatrix);
        double [][]inversM = inverseMatrix(tempInvesMatrix);
        double [][]tempMatrix2 = multiplyMatrix(multiplyMatrix(inversM,L),px);
        double [][] sim =multiplyMatrix(transposeMatrix(qx),tempMatrix2);
        return sim[0][0];
    }

    private int[][] ones(int a, int b){
        int [][]onesMatrix = new int[a][b];
        for(int i = 0; i < a; i++){
            for(int j = 0; j < b; j++){
                onesMatrix[i][j] = 1;
            }
        }
        return onesMatrix;
    }
    private double[][] divisionMatrix(int [][]a, int p){
        double [][]dMatrix = new double[a.length][a[0].length];
        for(int i = 0; i <a.length; i++){
            for(int j = 0; j < a[0].length;j++){
                dMatrix[i][j] = (float)a[i][j]*1.0/p;
            }
        }
        return dMatrix;
    }
    private double[][] transposeMatrix(double [][]qx){
        double [][]qpi = new double[qx[0].length][qx.length];
		/*this.print(qx);*/

        for(int i = 0; i < qx.length;i++){
            for(int j = 0; j <qx[0].length;j++){
                qpi[j][i] = qx[i][j];
            }
        }
        return qpi;
    }
    private double[][] eye(int size){
        double [][]eyematrix = new double[size][size];
        for(int i = 0; i < size; i++){
            eyematrix[i][i] = 1;
        }
        return eyematrix;
    }


    private double[][] multiplyMatrix(double [][]A, double [][]B){
        double [][]mulMatrix = new double[A.length][B[0].length];
        for(int i = 0; i < A.length;i++){
            for(int j = 0; j <B[0].length;j++){
                double sum = 0;
                for(int k = 0; k < A[0].length;k++){
                    sum  += A[i][k]*B[k][j];
                }
                mulMatrix[i][j] = sum;
            }
        }
        return mulMatrix;
    }
    private int[][] multiplyMatrix(int [][]A, int [][]B){
        int [][]mulMatrix = new int[A.length][B[0].length];
        for(int i = 0; i < A.length;i++){
            for(int j = 0; j <B[0].length;j++){
                int sum = 0;
                for(int k = 0; k < A[0].length;k++){
                    sum  += A[i][k]*B[k][j];
                }
                mulMatrix[i][j] = sum;
            }
        }
        return mulMatrix;
    }
    private double[][] multiplyMatrix(double c, int [][]B){
        double [][]mulMatrix = new double[B.length][B[0].length];
        for(int i = 0; i < B.length;i++){
            for(int j = 0; j <B[0].length;j++){
                mulMatrix[i][j] = c * B[i][j];
            }
        }
        return mulMatrix;
    }
    private double[][] multiplyMatrix(double [][]A, int [][]B){
        double [][]mulMatrix = new double[A.length][B[0].length];
        for(int i = 0; i < A.length;i++){
            for(int j = 0; j <B[0].length;j++){
                double sum = 0;
                for(int k = 0; k < A[0].length;k++){
                    sum  += A[i][k]*B[k][j];
                }
                mulMatrix[i][j] = sum;
            }
        }
        return mulMatrix;
    }
    private double[][] multiplyMatrix(int [][]A, double [][]B){
        double [][]mulMatrix = new double[A.length][B[0].length];
        for(int i = 0; i < A.length;i++){
            for(int j = 0; j <B[0].length;j++){
                double sum = 0;
                for(int k = 0; k < A[0].length;k++){
                    sum  += A[i][k]*B[k][j];
                }
                mulMatrix[i][j] = sum;
            }
        }
        return mulMatrix;
    }

    public double[][] inverseMatrix(double [][]origanlMatrix) {
        int cyq=JudgmentMatrix (origanlMatrix);
        double invMatrix[][]=new double[origanlMatrix.length][origanlMatrix.length];
        if(cyq==1){
            invMatrix=Computematrix(origanlMatrix);
        }
        return invMatrix;
    }
    public int JudgmentMatrix (double [][]a){
        double b[][]=new double[a.length][a[0].length];
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length;j++){
                b[i][j]=a[i][j];
            }
        }
        if(b.length!=b[0].length) {
            LOGGER.warn("出问题了2");
            return 0;
        }
        else {
            for(int i=0;i<b.length;i++){
                for(int j=0;j<b.length;j++){
                    if(b[i][j]!=0)break;
                    if(j==(b.length-1)&&(b[i][b.length-1]==0)){
                        LOGGER.warn("出问题了3");
                        return 0;
                    }
                }
            }
            for(int i=0;i<b.length;i++){
                for(int j=0;j<b.length;j++){
                    if(b[j][i]!=0)break;
                    if(j==(b.length-1)&&(b[b.length-1][j]==0)){
                        LOGGER.warn("出问题了4");
                        return 0;
                    }
                }
            }
            for(int i=0;i<b.length;i++){
                if(b[i][i]==0){
                    for(int j=0;j<b.length;j++){
                        if(b[j][i]!=0){
                            for(int m=0;m<b.length;m++){
                                double zhongjianyuan=b[j][m];
                                b[j][m]=b[i][m];
                                b[i][m]=zhongjianyuan;
                            }
                        }
                    }
                }
            }
            for(int i=0;i<b.length-1;i++){
                for(int j=i;j<b.length-1;j++){
                    double zhongyuan=(b[j+1][i]/b[i][i]);
                    for(int k=0;k<b[0].length;k++){
                        b[j+1][k]=b[j+1][k]-b[i][k]*zhongyuan;
                    }
                }
            }
            double z=1;
            for(int i=0;i<b.length;i++){
                z=z*b[i][i];
            }
            if(z==0){
                LOGGER.warn("出问题了5");
                return 0;
            }
        }
        return 1;
    }

    public  double[][] Computematrix(double a[][]){
        double[][]b=new double[a.length][2*a[0].length];
        for(int i=0;i<a.length;i++){
            for(int j=0;j<b[0].length;j++){
                if(j<a.length)b[i][j]=a[i][j];
                else{
                    if(j==i+a.length)b[i][j]=1;
                    else b[i][j]=0;
                }
            }
        }
        for(int i=0;i<b.length;i++){
            if(b[i][i]==0){
                for(int j=0;j<b.length;j++){
                    if(b[j][i]!=0){
                        for(int m=0;m<b[0].length;m++){
                            double zhongjianyuan=b[j][m];
                            b[j][m]=b[i][m];
                            b[i][m]=zhongjianyuan;
                        }
                    }
                }
            }
        }
        for(int i=0;i<b.length-1;i++){
            for(int j=i;j<b.length-1;j++){
                double zhongyuan=(b[j+1][i]/b[i][i]);
                for(int k=0;k<b[0].length;k++){
                    b[j+1][k]=b[j+1][k]-b[i][k]*zhongyuan;
                }
            }
        }
        for(int i=0;i<b.length;i++){
            double zhuduiyuan=b[i][i];
            for(int j=0;j<b[0].length;j++){
                b[i][j]=b[i][j]/zhuduiyuan;
            }
        }
        for(int m=b.length-1;m>0;m--){
            for(int i=m;i>0;i--){
                double daihuanzhi=b[i-1][m];
                for(int j=m;j<b[0].length;j++){
                    b[i-1][j]=b[i-1][j]-daihuanzhi*b[i][j];
                }
            }
        }
        for(int i=0;i<b.length;i++){
            for(int j=b.length;j<b[0].length;j++){
                a[i][j-b.length]=b[i][j];
            }
        }
        return a;
    }

    double [][]subtractMatrix(double [][]a, double [][]b){
        if(a.length != b.length || a[0].length != b[0].length){
            LOGGER.warn("出问题了6");
            return null;
        }
        double [][]matrix = new double [a.length][a[0].length];
        for(int i = 0; i < a.length;i++)
        {
            for(int j = 0; j < a[0].length;j++){
                matrix[i][j] = a[i][j] - b[i][j];
            }
        }
        return matrix;
    }
    double [][]subtractMatrix(int [][]a, int [][]b){
        if(a.length != b.length || a[0].length != b[0].length){
            LOGGER.warn("出问题了7");
            return null;
        }
        double [][]matrix = new double [a.length][a[0].length];
        for(int i = 0; i < a.length;i++)
        {
            for(int j = 0; j < a[0].length;j++){
                matrix[i][j] = a[i][j] - b[i][j];
            }
        }
        return matrix;
    }

    double [][]subtractMatrix(int [][]a, double [][]b){
        if(a.length != b.length || a[0].length != b[0].length){
            LOGGER.warn("出问题了8");
            return null;
        }
        double [][]matrix = new double [a.length][a[0].length];
        for(int i = 0; i < a.length;i++)
        {
            for(int j = 0; j < a[0].length;j++){
                matrix[i][j] = a[i][j] - b[i][j];
            }
        }
        return matrix;
    }

    double [][]subtractMatrix(double [][]a, int [][]b){
        if(a.length != b.length || a[0].length != b[0].length){
            LOGGER.warn("出问题了9");
            return null;
        }
        double [][]matrix = new double [a.length][a[0].length];
        for(int i = 0; i < a.length;i++)
        {
            for(int j = 0; j < a[0].length;j++){
                matrix[i][j] = a[i][j] - b[i][j];
            }
        }
        return matrix;
    }

    void print(double [][]ma){
        System.out.println("---------------------");
        for(int i = 0; i < ma.length;i++){
            for(int j = 0; j < ma[i].length;j++){
                System.out.print(ma[i][j] + ",");
            }
            System.out.println();
        }
    }
    void print(int [][]ma){
        System.out.println("---------------------");
        for(int i = 0; i < ma.length;i++){
            for(int j = 0; j < ma[i].length;j++){
                System.out.print(ma[i][j] + ",");
            }
            System.out.println();
        }
    }
    void print(int []ma){
        System.out.println("---------------------");
        for(int i = 0; i < ma.length;i++){
            System.out.print(ma[i] + " ");
        }
    }
    void print(double []ma){
        System.out.println("---------------------");
        for(int i = 0; i < ma.length;i++){
            System.out.print(ma[i] + " ");
        }
    }
}
