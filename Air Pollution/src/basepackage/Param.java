package basepackage;
/**
 * Represents parameters objects.
 * Created mainly for the JSON library.
 * Stores name, formula, code and id of parameter
 */
public class Param {
    private String paramName;
    private String paramFormula;
    private String paramCode;
    private int idParam;

    public Param() {
    }

    public String getParamName() {
        return paramName;
    }

    public String getParamFormula() {
        return paramFormula;
    }

    public String getParamCode() {
        return paramCode;
    }

    public int getIdParam() {
        return idParam;
    }
}
