public class Card {
    private static int counter = 1;

    private String name;
    private String slideID;
    private double hardness;
    private double gravity;
    private String cleavage;
    private String abundance;
    private String ecoValue;
    private String instruction;

    public Card(String name, double hardness, double gravity, String cleavage, String abundance, String ecoValue) {
        this.name = name;
        String decimalNumber = (counter < 10 ? "0" : "") + counter++;
        this.slideID = "Slide" + decimalNumber;
        this.hardness = hardness;
        this.gravity = gravity;
        this.cleavage = cleavage;
        this.abundance = abundance;
        this.ecoValue = ecoValue;
    }

    public Card(String name, String instruction) {
        this.name = name;
        String decimalNumber = (counter < 10 ? "0" : "") + counter++;
        this.slideID = "Slide" + decimalNumber;
        this.instruction = instruction;
    }

    public String getName() {
        return name;
    }

    public String getSlideID() {
        return slideID;
    }

    public double getHardness() {
        return hardness;
    }

    public double getGravity() {
        return gravity;
    }

    public String getCleavage() {
        return cleavage;
    }

    public String getAbundance() {
        return abundance;
    }

    public String getEcoValue() {
        return ecoValue;
    }

    public String getInstruction() {
        return instruction;
    }
}
