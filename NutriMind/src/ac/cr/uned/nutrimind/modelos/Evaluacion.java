
package ac.cr.uned.nutrimind.modelos;


import java.math.BigDecimal;

public class Evaluacion {
    
    private String pacienteId;
    private int nutricionistaId;
    private String fechaEvaluacion; // sería mejor LocalDate
    private BigDecimal peso; // kg
    private BigDecimal altura; // cm
    private String nivelActividadFisica;
    private BigDecimal imc;
    private String categoriaImc;
    private String recomendaciones;

    // Constructor, getters y setters
    public Evaluacion() {}
    public Evaluacion( String pacienteId, int nutricionistaId, String fechaEvaluacion,
                      BigDecimal peso, BigDecimal altura, String nivelActividadFisica,
                      BigDecimal imc, String categoriaImc, String recomendaciones) {
        
        this.pacienteId = pacienteId;
        this.nutricionistaId = nutricionistaId;
        this.fechaEvaluacion = fechaEvaluacion;
        this.peso = peso;
        this.altura = altura;
        this.nivelActividadFisica = nivelActividadFisica;
        this.imc = imc;
        this.categoriaImc = categoriaImc;
        this.recomendaciones = recomendaciones;
    }
    //public int getId() { return id; }
    //public void setId(int id) { this.id = id; }
    public String getPacienteId() { return pacienteId; }
    public void setPacienteId(String pacienteId) { this.pacienteId = pacienteId; }
    public int getNutricionistaId() { return nutricionistaId; }
    public void setNutricionistaId(int nutricionistaId) { this.nutricionistaId = nutricionistaId; }
    public String getFechaEvaluacion() { return fechaEvaluacion; }
    public void setFechaEvaluacion(String fechaEvaluacion) { this.fechaEvaluacion = fechaEvaluacion; }
    public BigDecimal getPeso() { return peso; }
    public void setPeso(BigDecimal peso) { this.peso = peso; }
    public BigDecimal getAltura() { return altura; }
    public void setAltura(BigDecimal altura) { this.altura = altura; }
    public String getNivelActividadFisica() { return nivelActividadFisica; }
    public void setNivelActividadFisica(String nivelActividadFisica) { this.nivelActividadFisica = nivelActividadFisica; }
    public BigDecimal getImc() { return imc; }
    public void setImc(BigDecimal imc) { this.imc = imc; }
    public String getCategoriaImc() { return categoriaImc; }
    public void setCategoriaImc(String categoriaImc) { this.categoriaImc = categoriaImc; }
    public String getRecomendaciones() { return recomendaciones; }
    public void setRecomendaciones(String recomendaciones) { this.recomendaciones = recomendaciones; }
}
