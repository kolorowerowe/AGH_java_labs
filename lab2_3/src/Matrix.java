import java.util.Random;


public class Matrix {
    double[]data;
    int rows;
    int cols;

    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }

    Matrix(double[][] d){
        if (d.length==0){
            rows = 0;
            cols = 0;
        }
        else{
            rows = d.length;
            cols=0;
            for(int r=0; r<rows;r++){
                if (d[r].length>cols)
                    cols=d[r].length;
            }

            data = new double[rows*cols];
            for (int r=0;r<rows;r++){
                for (int c=0;c<cols;c++){
                    if (c<d[r].length){
                        data[r*cols+c]=d[r][c];
                    }
                    else {
                        data[r*cols+c]=0;
                    }
                }
            }
        }
    }

    public double[][] asArray(){
        double[][] result = new double[rows][cols];
        for(int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                result[r][c] = data[r*cols+c];
            }
        }
        return result;
    }

    double get(int r, int c){
        return data[r*cols+c];
    }
    void set(int r, int c, double val){
        data[r*cols+c]=val;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        for(int r=0;r<rows;r++){
            buf.append("[");
            for(int c=0; c<cols;c++)
                buf.append(data[r*cols+c]+" ");
            buf.append("]\n");
        }

        return buf.toString();
    }

    void print(){
        System.out.println(this.toString());
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));
        rows=newRows;
        cols=newCols;
    }

    int[] shape(){
        int[] shape={rows,cols};
        return shape;
    }

    Matrix add(Matrix m){
        if((m.shape()[0]!=this.shape()[0]) || (m.shape()[1]!=this.shape()[1]))
            throw new RuntimeException(String.format("these matrix has different shape, so i cannot add them"));
        Matrix result = new Matrix(rows,cols);
        double number;
        for(int r=0;r<rows;r++)
            for(int c=0; c<cols; c++){
                number=this.get(r,c)+m.get(r,c);
                result.set(r,c,number);
            }
        return result;
    }

    Matrix sub(Matrix m){
        if((m.shape()[0]!=this.shape()[0]) || (m.shape()[1]!=this.shape()[1]))
            throw new RuntimeException(String.format("these matrix has different shape, so i cannot add them"));
        Matrix result = new Matrix(rows,cols);
        double number;
        for(int r=0;r<rows;r++)
            for(int c=0; c<cols; c++){
                number=this.get(r,c)-m.get(r,c);
                result.set(r,c,number);
            }
        return result;
    }

    Matrix mul(Matrix m){
        if((m.shape()[0]!=this.shape()[0]) || (m.shape()[1]!=this.shape()[1]))
            throw new RuntimeException(String.format("these matrix has different shape, so i cannot add them"));
        Matrix result = new Matrix(rows,cols);
        double number;
        for(int r=0;r<rows;r++)
            for(int c=0; c<cols; c++){
                number=this.get(r,c)*m.get(r,c);
                result.set(r,c,number);
            }
        return result;
    }
    Matrix div(Matrix m){
        if((m.shape()[0]!=this.shape()[0]) || (m.shape()[1]!=this.shape()[1]))
            throw new RuntimeException(String.format("these matrix has different shape, so i cannot add them"));
        Matrix result = new Matrix(rows,cols);
        double number;
        for(int r=0;r<rows;r++)
            for(int c=0; c<cols; c++){
                if(m.get(r,c)==0){
                    throw new RuntimeException(String.format("can't divide by 0"));
                }
                number=this.get(r,c)/m.get(r,c);
                result.set(r,c,number);
            }
        return result;
    }

    Matrix add(double w){
        Matrix result = new Matrix(rows,cols);
        for(int r=0;r<rows;r++)
            for(int c=0; c<cols; c++){
                result.set(r,c,this.get(r,c)+w);
            }
        return result;
    }

    Matrix sub(double w){
        Matrix result = new Matrix(rows,cols);
        for(int r=0;r<rows;r++)
            for(int c=0; c<cols; c++){
                result.set(r,c,this.get(r,c)-w);
            }
        return result;
    }
    Matrix mul(double w){
        Matrix result = new Matrix(rows,cols);
        for(int r=0;r<rows;r++)
            for(int c=0; c<cols; c++){
                result.set(r,c,this.get(r,c)*w);
            }
        return result;
    }
    Matrix div(double w){
        if (w == 0){
            throw new RuntimeException(String.format("can't divide by 0"));
        }
        Matrix result = new Matrix(rows,cols);
        for(int r=0;r<rows;r++)
            for(int c=0; c<cols; c++){
                result.set(r,c,this.get(r,c)/w);
            }
        return result;
    }
    Matrix dot(Matrix m){
        if(this.cols != m.rows){
            throw new RuntimeException(String.format("can't multiply these matrix because of their shape"));
        }

        Matrix res= new Matrix(this.rows, m.cols);
        for(int r=0;r<this.rows;r++){
            for (int c=0;c<m.cols;c++){
                res.set(r,c,0);
                for (int i=0;i<this.cols;i++){
                    res.data[r*cols+c]+=this.get(r,i)*m.get(i,c);
                }
            }
        }
        return res;
    }

    double frobenius(){
        double result=0;
        for(int r=0;r<rows;r++){
            for(int c=0;c<cols;c++){
                result+=Math.pow((get(r,c)),2);
            }
        }
        return Math.sqrt(result);
    }

    public static Matrix random(int rows, int cols){
        Matrix m = new Matrix(rows,cols);
        Random rd = new Random();
        for (int r=0;r<rows; r++){
            for(int c=0;c<cols;c++){
                m.set(r,c,rd.nextDouble());
            }
        }
        return m;
    }

    public static Matrix eye(int n){
        Matrix m = new Matrix(n,n);
        for (int r=0;r<n; r++){
            for(int c=0;c<n;c++){
                if(r==c)
                    m.set(r,c,1);
                else
                    m.set(r,c,0);
            }
        }
        return m;
    }


    // ----------- KARTKÓWKA ------------------------

    Matrix max(int axis){
        if(axis==1){
            Matrix res = new Matrix (rows, 1);
            double maxinRow;
            for (int r=0;r<rows;r++) {
                maxinRow=get(r,0);
                for (int c = 0; c < cols; c++) {
                    if (get(r,c)>maxinRow){
                        maxinRow = get(r,c);
                    }
                }
                res.set(r,0,maxinRow);
            }
            return res;
        }
        else if (axis == 0){
            Matrix res = new Matrix (1,cols);

            double maxinCol;
            for (int c=0;c<cols;c++) {
                maxinCol=get(0,c);
                for (int r = 0; r < rows; r++) {
                    if (get(r,c)>maxinCol){
                        maxinCol = get(r,c);
                    }
                }
                res.set(0,c,maxinCol);
            }

            return res;

        }
        else{
            Matrix res = new Matrix (1,1);
            double maxInAll;
            maxInAll=get(0,0);
            for (int c=0;c<cols;c++) {
                for (int r = 0; r < rows; r++){
                    if (get(r,c)>maxInAll){
                        maxInAll=get(r,c);
                    }
                }
            }
            res.set(0,0,maxInAll);

            return res;
        }

    }




    public static void main(String[] args) {
        /*
        double[][] d1 = new double[][]{{2, 1, 3}, {-1, 4, 1}};
        Matrix m1 = new Matrix(d1);

        double[][] d2 = new double[][]{{1, 3, 2}, {-2, 0, 1}, {5, -3, 2}};
        Matrix m2 = new Matrix(d2);


        Matrix m3 = Matrix.eye(4);
        m3.print();
        */
        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        m.print();

        Matrix col = m.max(1);
        col.print();

        Matrix row = m.max(0);
        row.print();


        Matrix all= m.max(-1);
        all.print();






    }
}

