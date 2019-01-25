import java.util.ArrayList;
import java.util.List;


public class Prod extends Node {
    List<Node> args = new ArrayList<>();

    Prod(){}

    Prod(Node n1){
        args.add(n1);
    }
    Prod(double c){
        args.add(new Constant(c));
    }


    Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }
    Prod(double c, Node n){
        args.add(new Constant(c));
        args.add(n);
     }



    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c){
       args.add(new Constant(c));
        return null;

    }


    @Override
    double evaluate() {
        double result =1;
        for (Node n : args){
            result*=n.evaluate();
        }
        return sign*result;
    }
    int getArgumentsCount(){return args.size();}


    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-");
        for (Node n : args){
            b.append(n.toString());
            b.append("*");

        }
        b.deleteCharAt(b.length()-1);
        return b.toString();
    }


}