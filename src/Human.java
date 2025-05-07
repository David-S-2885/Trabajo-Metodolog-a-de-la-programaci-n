public class Human extends Flunky {
    private Loyalty loyalty;

    public enum Loyalty {
        LOW("low"),
        MEDIUM("medium"),
        HIGH("high");

        private String valor;

        Loyalty(String valor) {
            this.valor = valor;
        }

        public String getValue() {
            return valor;
        }

        public static Loyalty fromValue(String valor) {
            for (Loyalty l : Loyalty.values()) {
                if (l.getValue().equalsIgnoreCase(valor)) {
                    return l;
                }
            }
            throw new IllegalArgumentException("Valor de lealtad desconocido: " + valor);
        }
    }


    public String getLoyalty() {
        return loyalty.getValue();
    }

    public void setLoyalty(Loyalty loyalty) {
        this.loyalty = loyalty;
    }

    public Human() {

    }


}
