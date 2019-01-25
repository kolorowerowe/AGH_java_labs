import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;


public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long, AdminUnit> IdToAdminUnits = new HashMap<>();
    Map<AdminUnit, Long> AdminUnitsToParent = new HashMap<>();


    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ",");

        while (reader.next()) {

            AdminUnit admin = new AdminUnit();
            admin.set(
                    reader.get("name"),
                    reader.getInt("admin_level"),
                    reader.getDouble("population"),
                    reader.getDouble("area"),
                    reader.getDouble("density"),
                    reader.getDouble("x1"),
                    reader.getDouble("y1"),
                    reader.getDouble("x3"),
                    reader.getDouble("y3"));

            units.add(admin);
            IdToAdminUnits.put(reader.getLong(0), admin);
            AdminUnitsToParent.put(admin, reader.getLong(1) == -1 ? null : reader.getLong(1));
        }
        //ustaw referencje
        for (AdminUnit un : this.units) {
            un.parent = this.IdToAdminUnits.get(this.AdminUnitsToParent.get(un));
        }

        for (AdminUnit un : this.units) {
            if (un.adminLevel != 4) {
                if (this.AdminUnitsToParent.get(un) != null)
                    if (this.IdToAdminUnits.get(this.AdminUnitsToParent.get(un)) != null) {
                        this.IdToAdminUnits.get(this.AdminUnitsToParent.get(un)).children.add(un);
                    }
            }
        }


    }


    void list(PrintStream out) {
        for (AdminUnit un : this.units) {
            out.print(un.toString());
        }
    }

    void list(PrintStream out, int offset, int limit) {
        for (int i = offset; i < offset + limit; i++) {
            out.print(units.get(i).toString());
        }
    }

    AdminUnitList selectByName(String pattern, boolean regex) {
        AdminUnitList ret = new AdminUnitList();
        for (AdminUnit un : this.units) {
            if (regex) {
                if (un.name.matches(pattern)) {
                    ret.units.add(un);
                }
            } else {
                if (un.name.contains(pattern)) {
                    ret.units.add(un);
                }
            }
        }
        return ret;
    }

    private AdminUnitList fixMissingValues() {
        for (AdminUnit un : this.units) {
            if (un.density == -1) {
                un.density = un.parent.density;
            }
            if (un.population == -1) {
                un.population = un.area * un.density;
            }
        }
        return this;
    }

    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance) {
        AdminUnitList ret = new AdminUnitList();
        for (AdminUnit un : this.units) {
            if (unit.adminLevel == un.adminLevel) {
                if (unit.bbox.intersects(un.bbox)) {
                    ret.units.add(un);
                } else if (unit.adminLevel == 8 && unit.bbox.distanceTo(un.bbox) < maxdistance) {
                    ret.units.add(un);
                }
            }

        }
        return ret;
    }

    AdminUnitList sortInplaceByName() {
        Collections.sort(this.units, new NameComparator());
        return this;
    }

    class NameComparator implements Comparator<AdminUnit> {
        @Override
        public int compare(AdminUnit a, AdminUnit b) {
            return b.name.compareTo(a.name);
        }
    }

    static class AreaComparator implements Comparator<AdminUnit> {
        @Override
        public int compare(AdminUnit a, AdminUnit b) {
            if (a.area > b.area)
                return 1;
            else if (a.area == b.area)
                return 0;
            else
                return -1;
        }
    }

    AdminUnitList sortInplaceByArea() {
        Comparator<AdminUnit> por = new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                if (o1.area > o2.area)
                    return 1;
                if (o1.area == o2.area)
                    return 0;
                else
                    return -1;
            }
        };
        units.sort(por);
        return this;
    }

    AdminUnitList sortInplace(Comparator<AdminUnit> cmp) {
        this.units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp) {
        AdminUnitList sorted = new AdminUnitList();
        for (AdminUnit un : units) {
            sorted.units.add(un);
        }
        sorted.sortInplace(cmp);
        return sorted;
    }

    AdminUnitList sortInplaceByPopulation() {
        Collections.sort(units, (u1, u2) -> Double.compare(u1.population, u2.population));
        return this;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred) {
        AdminUnitList wynik = new AdminUnitList();
        for (AdminUnit un : units) {
            if (pred.test(un)) {
                wynik.units.add(un);
            }
        }
        return wynik;
    }


    AdminUnitList filter(Predicate<AdminUnit> pred, int limit) {
        int i = 0;
        AdminUnitList wynik = new AdminUnitList();
        for (AdminUnit un : units) {
            if (pred.test(un) && i < limit) {
                wynik.units.add(un);
                i++;
            } else if (i == limit) {
                break;
            }
        }
        return wynik;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit) {
        int i = 0;
        AdminUnitList wynik = new AdminUnitList();
        for (AdminUnit un : units) {
            if (pred.test(un) && i < limit+offset) {
                if(i>=offset){
                    wynik.units.add(un);
                }
                i++;
            } else if (i == limit+offset) {
                break;
            }

        }
        return wynik;
    }




    public static void main(String args[]) throws IOException {

        AdminUnitList lista = new AdminUnitList();
        lista.read("admin-units.csv");

        AdminUnitList nowa = new AdminUnitList();
        nowa = lista.filter(a->a.name.startsWith("Rop"),0,99).sortInplaceByName();
        for (AdminUnit u:nowa.units){
                u.saveAsHtmlFile();
        }

    }



}

