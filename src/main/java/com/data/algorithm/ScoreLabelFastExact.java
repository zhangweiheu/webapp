package com.data.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScoreLabelFastExact {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreLabelFastExact.class);

    private int[][] aa;
    private int[][] LL;
    private int[]  currentTeam;
    private int i0;
    private boolean prune;
    public double[][]score;

    public ScoreLabelFastExact(int[][] aa,int[][] LL,int[] currentTeam,int i0,boolean prune){
        this.aa = aa;
        this.LL = LL;
        this.currentTeam = currentTeam;
        this.i0 = i0;
        this.prune = prune;
    }

    public double[][] label_fast_exact(){
        int mapsize = aa.length;
        int dn = LL[0].length;
        int []remainTeam = this.setdiff(currentTeam, i0);
        if(remainTeam == null){
            return null;
        }
        for(int i = 0; i < remainTeam.length;i++){
            currentTeam[i] = remainTeam[i];
        }
        currentTeam[currentTeam.length - 1] = i0;
        int [][]W = subMatrix(this.aa,this.currentTeam,this.currentTeam);
        //W0 is the common part
        int n0=currentTeam.length;
        int [][] W0=W;
        for (int j=0;j<W0[0].length;j++){
            W0[n0-1][j]=0;
        }
        for(int i=0;i<W0.length;i++){
            W0[i][n0-1]=0;
        }

        int [][][] L = new int[dn][][];
        for(int i=0;i<dn;i++){
            L[i]=duijiaoMatixx(this.LL,currentTeam,i);
        }

        int [][][] L0 = new int[dn][][];;//ÐÞ¸Ä
        for(int i=0;i<dn;i++){
            int [][] temp=L[i];
            temp[n0-1][n0-1]=0;
            L0[i]=temp;
        }

        int []orignalTeam = new int[mapsize];
        for(int i = 0; i < mapsize;i++){
            orignalTeam[i] = i+1;
        }


        int[] cand = new int[mapsize - currentTeam.length];
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
        double  c = 0.00000001;
        double [][]q = divisionMatrix(ones(n0,1),n0);
        double [][]p = divisionMatrix(ones(n0,1),n0);
        double [][]qx = kron(q,q);
        double [][]px = kron(p,p);
        int[][] temp1 = zeros(n0*n0);
        for(int j = 0; j < dn;j++){
            temp1= sumMatrix(temp1,kron(multiplyMatrix(L[j],W),multiplyMatrix( L0[j],W0)));
        }
        double [][]eyeMatrix = eye(n0*n0);
        double [][]tempMatrix = multiplyMatrix(c,temp1);
        double [][]inversZ = inverseMatrix(subtractMatrix(eyeMatrix,tempMatrix));
        double [][] R= zeros(n0*n0,1);
        for(int i= 0; i< dn;i++){

            R=sumMatrix(R,kron(multiplyMatrix(L[i],p),multiplyMatrix( L0[i],p)));
        }

        double[][] base=multiplyMatrix(multiplyMatrix(transposeMatrix(qx),inversZ),R);
        double[][] l=multiplyMatrix(multiplyMatrix(c,transposeMatrix(qx)),inversZ);
        double[][] r=multiplyMatrix(inversZ,R);
        this.score = zeros(cand.length,2);
        for(int k= 0; k < cand.length;k++){
            double [][] s  = new double[1][n0];
            for(int i1=0;i1<n0-1;i1++){
                s[0][i1]=0;
                s[0][n0-1]=1;
            }

            double [][] Am= subMatrix(this.aa,cand[k],remainTeam);
            double t[][] = new double[1][n0];
            for(int i1=0;i1<n0-1;i1++){

                t[0][i1]=Am[0][i1];
                t[0][n0-1]=0;
            }
            double[][] T=transposeMatrix(t);
            double[][] S=transposeMatrix(s);
            double[][] A;
            A=mergeRowMatrix(T, S);
            double[][] B;
            B=mergeColumnMatrix(s,t);
            double [][] a = new double [n0][1];
            for(int i=0;i<n0-1;i++){
                a[i][0]=0;
                a[n0-1][0]=1;
            }
            double [][][] b = new double [dn][][];
            for(int j=0;j<dn;j++){
                double[][] c1=zeros(1,n0-1);
                double d= LL[cand[k]-1][j];
                b[j]=margeMatrxi(c1, d);
            }

            double [][]A1 = null;
            double [][]B1 = null;
            for(int j = 0; j< dn; j++){
                if(j == 0){
                    A1 = this.kron(L[j], a);
                    B1 = this.kron(this.eye(n0), b[j]);

                }else{
                    A1 = mergeRowMatrix(A1,this.kron(L[j], a));

                    B1 = mergeColumnMatrix(B1,this.kron(this.eye(n0), b[j]));
                }

            }

            double[][] X1= zeros(n0*n0,n0*2);
            double[][] X2= zeros(n0*n0,n0*2);
            for(int j= 0; j< dn;j++){
                X1 = sumMatrix(X1,kron(multiplyMatrix(L[j],W),multiplyMatrix(L0[j],A)));
                X2 = sumMatrix(X2,kron(multiplyMatrix(L[j],W),multiplyMatrix(multiplyMatrix(a,b[j]),A)));
            }
            double [][] Y1=multiplyMatrix(B1,kron(W,W0));
            double [][] Y2=kron(eye(n0),B);
            double [][] Xtemp=mergeRowMatrix(A1, X1);
            double [][] X=mergeRowMatrix(Xtemp, X2);
            double [][] Ytemp=mergeColumnMatrix(Y1, Y2);
            double [][] Y=mergeColumnMatrix(Ytemp, Y2);
            double [][] eyeMatrix1 = eye((dn+4)*n0);
            double [][] tempMatrix1 = multiplyMatrix(multiplyMatrix(c,Y),inversZ);
            double [][] tempMatrix2 = multiplyMatrix(tempMatrix1,X);
            double [][] M =inverseMatrix(subtractMatrix(eyeMatrix1,tempMatrix2));
            double [][] r1 = multiplyMatrix(multiplyMatrix(inversZ,A1),B1);
            double [][] r0 = multiplyMatrix(r1,px);
            double [][] a1=multiplyMatrix(transposeMatrix(qx),r0);
            double [][] a2= sumMatrix(r,r0);
            double [][] a3= multiplyMatrix(multiplyMatrix(l,X),M);
            double [][] a4=multiplyMatrix(multiplyMatrix(a3,Y),a2);
            double [][] sim= sumMatrix(sumMatrix(base,a1),a4);
            this.score[k][0] = sim[0][0];
            this.score[k][1] = cand[k];
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
            LOGGER.warn("出问题了11");
            return null;
        }

    }
    private int[][] subMatrix(int [][]aa, int []currentTeamRow, int []currentTeamColumn){
        int [][]sMatrix = new int[currentTeamRow.length][currentTeamColumn.length];

        sortMatrix(currentTeamRow);
        sortMatrix(currentTeamColumn);

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
    private int[][] duijiaoMatixx(int[][]theL, int[]currentTeam,int j){
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

    private double [][] subMatrix(int [][]aa, int currentTeamRow, int []currentTeamColumn){
        double [][]sMatrix = new double[1][currentTeamColumn.length];

        sortMatrix(currentTeamColumn);

        for(int j = 0; j < currentTeamColumn.length;j++){
            sMatrix[0][j] = aa[currentTeamRow - 1][currentTeamColumn[j] - 1];
        }

        return sMatrix;
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
            LOGGER.warn("出问题了12");
            System.out.println(matrixA.length + "  Ma " + matrixA[0].length);
            System.out.println(matrixB.length + "  Mb " + matrixB[0].length);
            return null;
        }
        return sumM;
    }
    private double[][]sumMatrix(double[][]matrixA, double[][]matrixB){
        double [][]sumM;

        if(matrixA.length == matrixB.length && matrixA[0].length ==matrixB[0].length ){
            sumM = new double[matrixB.length][matrixB[0].length];
            for(int i = 0; i < matrixB.length;i++){
                for(int j = 0; j <matrixB[i].length;j++){
                    sumM[i][j] = matrixA[i][j] +matrixB[i][j];
                }
            }
        }
        else
        {
            LOGGER.warn("出问题了13");
            System.out.println(matrixA.length + "  Ma " + matrixA[0].length);
            System.out.println(matrixB.length + "  Mb " + matrixB[0].length);
            return null;
        }
        return sumM;
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
    private double[][] multiplyMatrix(double c, double [][]B){
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



    public double[][] mergeRowMatrix(double [][]t1, double [][]t2){

        double [][]matrix = new double[t1.length][t1[0].length + t2[0].length];
        if(t1.length != t2.length){
            LOGGER.warn("出问题了14");
            return null;
        }

        for(int i = 0; i < t1.length; i++){
            int column = 0;
            for(int tj = 0; tj < t1[i].length; tj++){
                matrix[i][column] = t1[i][tj];
                column++;
            }
            for(int t2j = 0; t2j < t2[i].length; t2j++){
                matrix[i][column] = t2[i][t2j];
                column++;
            }
        }
        return matrix;
    }

    private double [][]margeMatrxi(double[][]ma, double t){
        double [][]marge = new double[1][ma[0].length + 1];
        for(int i = 0; i < ma[0].length;i++){
            marge[0][i] = ma[0][i];
        }
        marge[0][ma[0].length] = t;
        return marge;
    }

    public double[][] mergeColumnMatrix(double [][]t1, double [][]t2){
        double [][]matrix = new double[t1.length + t2.length][t1[0].length];
        if(t1[0].length != t2[0].length){
            LOGGER.warn("出问题了16");
            return null;
        }
        int row = 0;
        for(int i = 0; i < t1.length; i++){
            for(int tj = 0; tj < t1[0].length; tj++){
                matrix[row][tj] = t1[i][tj];
            }
            row++;
        }

        for(int t2i = 0; t2i < t2.length; t2i++){

            for(int t2j = 0; t2j < t2[0].length; t2j++){
                matrix[row][t2j] = t2[t2i][t2j];

            }
            row++;
        }
        return matrix;
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
            LOGGER.warn("出问题了17");
            return 0;
        }
        else {
            for(int i=0;i<b.length;i++){
                for(int j=0;j<b.length;j++){
                    if(b[i][j]!=0)break;
                    if(j==(b.length-1)&&(b[i][b.length-1]==0)){
                        LOGGER.warn("出问题了19");
                        return 0;
                    }
                }
            }
            for(int i=0;i<b.length;i++){
                for(int j=0;j<b.length;j++){
                    if(b[j][i]!=0)break;
                    if(j==(b.length-1)&&(b[b.length-1][j]==0)){
                        LOGGER.warn("出问题了20");
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
                LOGGER.warn("出问题了21");
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
            LOGGER.warn("出问题了23");
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
            LOGGER.warn("出问题了24");
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
            LOGGER.warn("出问题了25");
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
            LOGGER.warn("出问题了26");
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

	
	

