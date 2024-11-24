// Isaac de Oliveira Carvalho (isaac.dev.br) - MAPA ESOFT PROGRAMAÇÃO DE SISTEMAS I - RA: 23027292-5

public class Cliente {
    private int id;
    private String name;
    private String email;
    private String phone;

    // Construtor da Classe Cliente
    public Cliente(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Definindo os Get e Set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Método para exibir informações do cliente
    public void printInfo() {
        System.out.print("#" + id + " | ");
        System.out.print(name + " | ");
        System.out.print(email + " | ");
        System.out.println(phone);
    }
}

