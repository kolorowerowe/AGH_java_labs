

import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void Matrix(){
        int[] tab={1,2};
        Matrix m = new Matrix(tab[0],tab[1]);
        assertEquals(m.rows,tab[0],1e-5);
        assertEquals(m.cols,tab[1],1e-5);

    }

    @Test
    public void asArray() {

    }

    @Test
    public void get(){
    }

    @Test
    public void set() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void print() {
    }

    @Test
    public void reshape() {
    }

    @Test
    public void shape() {
        int[] tab ={3,4};
        Matrix m = new Matrix(tab[0],tab[1]);
        int[] result=m.shape();
        assertEquals(tab[0],result[0],1e-5);
        assertEquals(tab[1],result[1],1e-5);
    }

    @Test
    public void add() {
    }

    @Test
    public void sub() {
        Matrix first= Matrix.random(3,4);
        Matrix result=first.sub(first);
        double err=result.frobenius();
        assertEquals(0, err, 1e-5);
    }

    @Test
    public void mul() {
        Matrix first= Matrix.random(3,4);
        Matrix second=first.mul(-1);
        second=second.add(first);
        double err=second.frobenius();
        assertEquals(0, err, 1e-5);
    }

    @Test
    public void div() {
    }

    @Test
    public void add1() {
        Matrix first= Matrix.random(3,4);
        Matrix second=first.mul(-1);
        second=second.add(first);
        double err=second.frobenius();
        assertEquals(0, err, 1e-5);
    }

    @Test
    public void sub1() {
    }

    @Test
    public void mul1() {

    }

    @Test
    public void div1() {
    }

    @Test
    public void dot() {
    }

    @Test
    public void frobenius() {
    }

    @Test
    public void random() {
    }

    @Test
    public void eye() {
        int n = 4;
        Matrix e = Matrix.eye(n);
        double err= e.frobenius();
        assertEquals(Math.sqrt(n), err, 1e-5);
    }

    @Test
    public void main() {
    }
}