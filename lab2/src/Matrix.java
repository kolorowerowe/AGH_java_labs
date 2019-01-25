
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
        if (d.length=0)
            
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


}