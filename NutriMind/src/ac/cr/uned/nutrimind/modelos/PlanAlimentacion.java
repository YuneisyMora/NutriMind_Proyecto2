
package ac.cr.uned.nutrimind.modelos;


public class PlanAlimentacion {
    private int id;
    private int pacienteId;
    private int nutricionistaId;
    private String fechaInicio;  // sería mejor LocalDate
    private String fechaFinal;   // nullable
    private String planTexto;
    private String macronutrientes;
    private int comidasDia;
    private String alimentosRecomendados;
    private String observaciones;

    // Constructor, getters y setters
    public PlanAlimentacion() {}
    public PlanAlimentacion(int id, int pacienteId, int nutricionistaId,
                           String fechaInicio, String fechaFinal,
                           String planTexto, String macronutrientes,
                           int comidasDia, String alimentosRecomendados,
                           String observaciones) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.nutricionistaId = nutricionistaId;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.planTexto = planTexto;
        this.macronutrientes = macronutrientes;
        this.comidasDia = comidasDia;
        this.alimentosRecomendados = alimentosRecomendados;
        this.observaciones = observaciones;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPacienteId() { return pacienteId; }
    public void setPacienteId(int pacienteId) { this.pacienteId = pacienteId; }
    public int getNutricionistaId() { return nutricionistaId; }
    public void setNutricionistaId(int nutricionistaId) { this.nutricionistaId = nutricionistaId; }
    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }
    public String getFechaFinal() { return fechaFinal; }
    public void setFechaFinal(String fechaFinal) { this.fechaFinal = fechaFinal; }
    public String getPlanTexto() { return planTexto; }
    public void setPlanTexto(String planTexto) { this.planTexto = planTexto; }
    public String getMacronutrientes() { return macronutrientes; }
    public void setMacronutrientes(String macronutrientes) { this.macronutrientes = macronutrientes; }
    public int getComidasDia() { return comidasDia; }
    public void setComidasDia(int comidasDia) { this.comidasDia = comidasDia; }
    public String getAlimentosRecomendados() { return alimentosRecomendados; }
    public void setAlimentosRecomendados(String alimentosRecomendados) { this.alimentosRecomendados = alimentosRecomendados; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}

