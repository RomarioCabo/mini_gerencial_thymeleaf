package romario.cabo.com.br.mini_gerencial_thymeleaf.domain.enums;

public enum UF {
    AC("AC", "Acre"),
    AL("AL", "Alagoas"),
    AM("AM", "Amazonas"),
    BA("BA", "Bahia"),
    CE("CE", "Ceará"),
    DF("DF", "Distrito Federal"),
    ES("ES", "Espírito Santo"),
    GO("GO", "Goiás"),
    MA("MA", "Maranhão"),
    MT("MT", "Mato Grosso"),
    MS("MS", "Mato Grosso do Sul"),
    MG("MG", "Minas Gerais"),
    PA("PA", "Pará"),
    PB("PB", "Paraíba"),
    PR("PR", "Paraná"),
    PE("PE", "Pernambuco"),
    PI("PI", "Piauí"),
    RJ("RJ", "Rio de Janeiro"),
    RN("RN", "Rio Grande do Norte"),
    RS("RS", "Rio Grande do Sul"),
    RO("RO", "Rondônia"),
    RR("RR", "Roraima"),
    SC("SC", "Santa Catarina"),
    SP("SP", "São Paulo"),
    SE("SE", "Sergipe"),
    TO("TO", "Tocantins");

    private final String initials;
    private final String description;

    UF(String initials, String description) {
        this.initials = initials;
        this.description = description;
    }

    public String getInitials() {
        return initials;
    }

    public String getDescription() {
        return description;
    }

    public static UF toEnum(String initials) {

        if (initials == null) {
            return null;
        }

        for (UF ufEnum : UF.values()) {
            if (initials.equals(ufEnum.getInitials())) {
                return ufEnum;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + initials);
    }
}
