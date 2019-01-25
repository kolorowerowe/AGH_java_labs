import java.io.IOException;
import java.util.Comparator;
import java.util.function.Predicate;

public class AdminUnitQuery {
    AdminUnitList src;
    Predicate<AdminUnit> p = a->true;
    Comparator<AdminUnit> cmp;
    int limit = Integer.MAX_VALUE;
    int offset = 0;

    AdminUnitQuery selectFrom(AdminUnitList src){
        this.src=src;
        return this;
    }

    AdminUnitQuery where(Predicate<AdminUnit> pred){
        p=pred;
        return this;
    }

    AdminUnitQuery and(Predicate<AdminUnit> pred){
        p=p.and(pred);
        return this;
    }

    AdminUnitQuery or(Predicate<AdminUnit> pred){
        p=p.or(pred);
        return this;
    }

    AdminUnitQuery sort(Comparator<AdminUnit> cmp){
        this.cmp=cmp;
        return this;
    }

    AdminUnitQuery limit(int limit){
        this.limit=limit;
        return this;
    }

    AdminUnitQuery offset(int offset){
        this.offset=offset;
        return this;
    }

    AdminUnitList execute(){
        AdminUnitList ret = src.filter(p,offset,limit).sort(cmp);
        return ret;
    }

    public static void main(String args[]) throws IOException {

        AdminUnitList list = new AdminUnitList();
        list.read("admin-units.csv");

        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(list)
                .where(a->a.name.startsWith("Cz"))
                .or(a->a.name.startsWith("Sz"))
                .sort((a,b)->a.name.compareTo(b.name))
                .limit(100);
        query.execute().list(System.out);
    }

}


