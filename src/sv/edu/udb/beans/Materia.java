package sv.edu.udb.beans;

public class Materia {
    private int idMateria;
    private String materia;

    public Materia(int idMateria, String materia){
        this.idMateria = idMateria;
        this.materia = materia;
    }
    // Métodos Setter
    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    // Métodos Getter
    public int getIdMateria() {
        return idMateria;
    }

    public String getMateria() {
        return materia;
    }
}
