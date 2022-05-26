package teste;

public class FinalizadorDeInstancia {

    public static void main(String[] args) {

        Finalizador finalizador;

        for (int i = 0; i < 5; i++) {

            finalizador = new Finalizador(i);

            System.out.println("HashCode do Objeto Finalizador dentro do for: " + finalizador.hashCode());

            System.gc();
        }
    }


    @SuppressWarnings("deprecation")
    protected void finalize() {

        System.out.println("Finalizando Sistema");
        try {
            super.finalize();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

class Finalizador {

    int i;
    int x = 10;

    {
        System.out.println("\nValor do X na inilialização do objeto: " + x);
    }

    Finalizador(int i) {
        System.out.println("Entrou no contrutor do Finalizador!!!!");
        this.i = i;
        System.out.println("Somando " + x + " + " + i);
        this.x += i;

    }

    public void finalize() {

        System.out.println("\n############## Garbage Coletctor esta eliminando este objeto " + this.hashCode());
        System.out.println("Valor do X dentro do finalize: " + x + "\n");
    }

}