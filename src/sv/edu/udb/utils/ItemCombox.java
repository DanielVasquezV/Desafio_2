package sv.edu.udb.utils;

public class ItemCombox {

    private int id;
    private String description;

    public ItemCombox(int id, String description) {
        this.id = id;
        this.description = description;
    }
    public int getId() { return id; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "ItemComboBox{" +
                "id=" + id +
                ", descripcion='" + description + '\'' +
                '}';
    }
}
