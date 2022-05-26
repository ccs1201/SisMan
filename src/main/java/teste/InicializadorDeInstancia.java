package teste;

public class InicializadorDeInstancia {

    public static void main(String[] args) {

        @SuppressWarnings("unused")
        Instancia instancia = new Instancia();

    }

}

class Instancia {

    // bloco inicializador

    {
        System.out.println("1 = Dentro no inicializador da instancia e Antes da declaração das variáveis!!!"
                + "\n##################################################################################### \n");
    }

    // atributos
    int x = 15;
    int y = 40;
    char c = 'a';

    {
        System.out.println("2 = Dentro do Inicilizador de instancia e Depois de declarar as váriaveis");
        System.out.println("3 = Valor de X: " + x);
        System.out.println("4 = Valor de y: " + y);
        System.out.println("5 = Valor de c: " + c);

    }

    public Instancia() {
        System.out.println("6 = #### Entrou no construtor!!! ####");
    }

    {
        System.out.println("7 = #### Depois do Construtor!!! ####");
    }

}