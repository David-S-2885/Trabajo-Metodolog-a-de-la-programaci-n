public class Round {

    private int c1Attack;
    private int c2Attack;
    private int c1Defense;
    private int c2Defense;
    private String summaryDefiant;
    private String summaryDefied;

    public Round() {

    }

    private int calculateFinalValue(int i) {
        int j = 0;
        int random = 0;
        for (int k = 0; k<i;k++) {
            random = (int) (Math.random() * 6) + 1;
            if (random == 5 || random == 6) {
                j++;
            }
        }
        return j;
    }

    public void executeRound(Combat c) {
        Character c1 =  c.getC1();
        Character c2 = c.getC2();

        int c1Ap = calculateAttackPotential(c1);
        int c2Ap = calculateAttackPotential(c2);

        int c1Dp = calculateDefensePotential(c1);
        int c2Dp = calculateDefensePotential(c2);

        int c1Attack = calculateFinalValue(c1Ap);
        int c2Attack = calculateFinalValue(c2Ap);

        setC1Attack(c1Attack);
        setC2Attack(c2Attack);

        int c1Defense = calculateFinalValue(c1Dp);
        int c2Defense = calculateFinalValue(c2Dp);

        setC1Defense(c1Defense);
        setC2Defense(c2Defense);

        StringBuilder defiant = new StringBuilder();
        StringBuilder defied = new StringBuilder();

        if (c1Attack >= c2Defense) {
            if (c.getC2Flunky() != 0) {
                c.setC2Flunky(c.getC2Flunky()-1);
                defied.append("Tus esbirros han recibido 1 de daño. ");
                defiant.append("Has inflingido 1 de daño a los esbirros del rival. ");
            }
            else {
                c2.setHealth(c2.getHealth()-1);
                if (c2 instanceof Lycanthrope) {
                    ((Lycanthrope) c2).setRage(Math.min(((Lycanthrope) c2).getRage()+1,3));
                }
                if (c2 instanceof Hunter) {
                    ((Hunter) c2).setWill(Math.max(((Hunter) c2).getWill()-1,0));
                }
                if (c1 instanceof Vampire) {
                    ((Vampire) c1).setBloodPoints(Math.min(((Vampire) c1).getBloodPoints()+4,10));
                }
                defied.append("Has recibido 1 de daño. ");
                defiant.append("Has infligido 1 de daño al rival. ");
            }
        }
        else {
            defied.append("El rival ha fallado su ataque. ");
            defiant.append("Ataque fallido. ");
        }

        if(c2Attack >= c1Defense) {
            if (c.getC1Flunky() != 0) {
                c.setC1Flunky(c.getC1Flunky()-1);
                defied.append("Has inflingido 1 de daño a los esbirros del rival. ");
                defiant.append("Tus esbirros han recibido 1 de daño. ");
            }
            else {
                c1.setHealth(c1.getHealth()-1);
                if (c1 instanceof Lycanthrope) {
                    ((Lycanthrope) c1).setRage(Math.min(((Lycanthrope) c1).getRage()+1,3));
                }
                if (c1 instanceof Hunter) {
                    ((Hunter) c1).setWill(Math.max(((Hunter) c1).getWill()-1,0));
                }
                if (c2 instanceof Vampire) {
                    ((Vampire) c2).setBloodPoints(Math.min(((Vampire) c2).getBloodPoints()+4,10));
                }
                defied.append("Has infligido 1 de daño al rival. ");
                defiant.append("Has recibido 1 de daño. ");
            }
        }
        else {
            defied.append("Ataque fallido. ");
            defiant.append("El rival ha fallado su ataque. ");
        }
        setSummaryDefied(defied.toString());
        setSummaryDefiant(defiant.toString());
    }

    private int calculateAttackPotential(Character c) {
        int i = 0;
        int ae = 0;
        for (Equipment e: c.getActiveEquipment()) {
            ae += e.getAttackModifier();
        }

        if(!c.getWeaknesses().isEmpty()) {
            for(Weakness w: c.getWeaknesses()) {
                i -= w.getValue();
            }
        }

        if(!c.getStrenghts().isEmpty()) {
            for(Strength s: c.getStrenghts()) {
                i += s.getValue();
            }
        }

        if (c instanceof Vampire) {
            Vampire v = (Vampire) c;
            Discipline d = (Discipline) v.getSpecialAbility();
            i = v.getPower() + ae;

            if (v.getBloodPoints() >= d.getBloodCost()) {
                i += d.getAttackValue();
                v.setBloodPoints(v.getBloodPoints()-d.getBloodCost());
            }

            if(v.getBloodPoints() >= 5) {
                i += 2;
            }

        }
        else if (c instanceof Lycanthrope) {
            Lycanthrope l = (Lycanthrope) c;
            Gift g = (Gift) l.getSpecialAbility();

            i = l.getPower() + ae;

            if(l.getRage() > g.getMinmumRage()) {
                i += g.getAttackValue();
            }

            i += l.getRage();
        }
        else {
            Hunter h = (Hunter) c;
            Talent t = (Talent) h.getSpecialAbility();

            i =  h.getPower() + ae + t.getAttackValue() + h.getWill();
        }
        return i;
    }

    private int calculateDefensePotential(Character c) {
        int i = 0;
        int ae = 0;
        for (Equipment e: c.getActiveEquipment()) {
            ae += e.getDefenseModifier();
        }
        if (c instanceof Vampire) {
            Vampire v = (Vampire) c;
            Discipline d = (Discipline) v.getSpecialAbility();
            i = v.getPower() + ae;

            if (v.getBloodPoints() >= d.getBloodCost()) {
                i += d.getDefenseValue();
                v.setBloodPoints(v.getBloodPoints()-d.getBloodCost());
            }

            if(v.getBloodPoints() >= 5) {
                i += 2;
            }
        }
        else if (c instanceof Lycanthrope) {
            Lycanthrope l = (Lycanthrope) c;
            Gift g = (Gift) l.getSpecialAbility();

            i = l.getPower() + ae;

            if(l.getRage() > g.getMinmumRage()) {
                i += g.getDefenseValue();
            }

            i += l.getRage();
        }
        else {
            Hunter h = (Hunter) c;
            Talent t = (Talent) h.getSpecialAbility();

            i =  h.getPower() + ae + t.getDefenseValue() + h.getWill();
        }
        return i;
    }

    public void setC1Attack(int c1Attack) {
        this.c1Attack = c1Attack;
    }

    public void setC2Attack(int c2Attack) {
        this.c2Attack = c2Attack;
    }

    public void setC1Defense(int c1Defense) {
        this.c1Defense = c1Defense;
    }

    public void setC2Defense(int c2Defense) {
        this.c2Defense = c2Defense;
    }

    public int getC1Attack() {
        return c1Attack;
    }

    public int getC2Attack() {
        return c2Attack;
    }

    public int getC1Defense() {
        return c1Defense;
    }

    public int getC2Defense() {
        return c2Defense;
    }

    public String getSummaryDefiant() {
        return summaryDefiant;
    }

    public String getSummaryDefied() {
        return summaryDefied;
    }

    public void setSummaryDefiant(String summaryDefiant) {
        this.summaryDefiant = summaryDefiant;
    }

    public void setSummaryDefied(String summaryDefied) {
        this.summaryDefied = summaryDefied;
    }
}
