class Card {
    private static int counter = 1;

    private String name;
    private String slideID;
    private double hardness;
    private double gravity;
    private String cleavage;
    private String abundance;
    private String ecoValue;
    private String instruction;

    Card(String name, double hardness, double gravity, String cleavage, String abundance, String ecoValue) {
        this.name = name;
        String decimalNumber = (counter < 10 ? "0" : "") + counter++;
        this.slideID = "Slide" + decimalNumber;
        this.hardness = hardness;
        this.gravity = gravity;
        this.cleavage = cleavage;
        this.abundance = abundance;
        this.ecoValue = ecoValue;
    }

    Card(String name, String instruction) {
        this.name = name;
        String decimalNumber = (counter < 10 ? "0" : "") + counter++;
        this.slideID = "Slide" + decimalNumber;
        this.instruction = instruction;
    }

    String getName() {
        return name;
    }

    String getSlideID() {
        return slideID;
    }

    double getHardness() {
        return hardness;
    }

    double getGravity() {
        return gravity;
    }

    String getCleavage() {
        return cleavage;
    }

    String getAbundance() {
        return abundance;
    }

    String getEcoValue() {
        return ecoValue;
    }

    String getInstruction() {
        return instruction;
    }
}
