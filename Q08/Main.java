import java.util.Scanner;

class Main {

    public static class No {
        public int elemento;
        public No esq, dir;

        public No(int elemento) {
            this(elemento, null, null);
        }

        public No(int elemento, No esq, No dir) {
            this.elemento = elemento;
            this.esq = esq;
            this.dir = dir;
        }
    }

    public static class ArvoreBinaria {
        private No raiz;

        public ArvoreBinaria() {
            raiz = null;
        }

        public boolean pesquisar(int x) {
            return pesquisar(x, raiz);
        }

        private boolean pesquisar(int x, No i) {
            boolean resp;
            if (i == null) {
                resp = false;

            } else if (x == i.elemento) {
                resp = true;

            } else if (x < i.elemento) {
                resp = pesquisar(x, i.esq);

            } else {
                resp = pesquisar(x, i.dir);
            }
            return resp;
        }

        public void caminharCentral() {
            caminharCentral(raiz);
            System.out.println("");
        }

        private void caminharCentral(No i) {
            if (i != null) {
                caminharCentral(i.esq);
                System.out.print(i.elemento + " ");
                caminharCentral(i.dir);
            }
        }

        public void caminharPre() {
            caminharPre(raiz);
            System.out.println("");
        }

        private void caminharPre(No i) {
            if (i != null) {
                System.out.print(i.elemento + " ");
                caminharPre(i.esq);
                caminharPre(i.dir);
            }
        }

        public void caminharPos() {
            caminharPos(raiz);
            System.out.println("");
        }

        private void caminharPos(No i) {
            if (i != null) {
                caminharPos(i.esq);
                caminharPos(i.dir);
                System.out.print(i.elemento + " ");
            }
        }

        public void inserir(int x) throws Exception {
            raiz = inserir(x, raiz);
        }

        private No inserir(int x, No i) throws Exception {
            if (i == null) {
                i = new No(x);

            } else if (x < i.elemento) {
                i.esq = inserir(x, i.esq);

            } else if (x > i.elemento) {
                i.dir = inserir(x, i.dir);

            } else {
                throw new Exception("Erro ao inserir!");
            }

            return i;
        }

        public void inserirPai(int x) throws Exception {
            if (raiz == null) {
                raiz = new No(x);
            } else if (x < raiz.elemento) {
                inserirPai(x, raiz.esq, raiz);
            } else if (x > raiz.elemento) {
                inserirPai(x, raiz.dir, raiz);
            } else {
                throw new Exception("Erro ao inserirPai!");
            }
        }

        private void inserirPai(int x, No i, No pai) throws Exception {
            if (i == null) {
                if (x < pai.elemento) {
                    pai.esq = new No(x);
                } else {
                    pai.dir = new No(x);
                }
            } else if (x < i.elemento) {
                inserirPai(x, i.esq, i);
            } else if (x > i.elemento) {
                inserirPai(x, i.dir, i);
            } else {
                throw new Exception("Erro ao inserirPai!");
            }
        }

        public void remover(int x) throws Exception {
            raiz = remover(x, raiz);
        }

        private No remover(int x, No i) throws Exception {

            if (i == null) {
                throw new Exception("Erro ao remover!");

            } else if (x < i.elemento) {
                i.esq = remover(x, i.esq);

            } else if (x > i.elemento) {
                i.dir = remover(x, i.dir);

            } else if (i.dir == null) {
                i = i.esq;

            } else if (i.esq == null) {
                i = i.dir;

            } else {
                i.esq = maiorEsq(i, i.esq);
            }

            return i;
        }

        private No maiorEsq(No i, No j) {

            if (j.dir == null) {
                i.elemento = j.elemento;
                j = j.esq;

            } else {
                j.dir = maiorEsq(i, j.dir);
            }
            return j;
        }

        public int getMaior() {
            int resp = -1;

            if (raiz != null) {
                No i;
                for (i = raiz; i.dir != null; i = i.dir)
                    ;
                resp = i.elemento;
            }

            return resp;
        }

        public int getMenor() {
            int resp = -1;

            if (raiz != null) {
                No i;
                for (i = raiz; i.esq != null; i = i.esq)
                    ;
                resp = i.elemento;
            }

            return resp;
        }

        public int getAltura() {
            return getAltura(raiz, 0);
        }

        public int getAltura(No i, int altura) {
            if (i == null) {
                altura--;
            } else {
                int alturaEsq = getAltura(i.esq, altura + 1);
                int alturaDir = getAltura(i.dir, altura + 1);
                altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
            }
            return altura;
        }

        public void remover2(int x) throws Exception {
            if (raiz == null) {
                throw new Exception("Erro ao remover2!");
            } else if (x < raiz.elemento) {
                remover2(x, raiz.esq, raiz);
            } else if (x > raiz.elemento) {
                remover2(x, raiz.dir, raiz);
            } else if (raiz.dir == null) {
                raiz = raiz.esq;
            } else if (raiz.esq == null) {
                raiz = raiz.dir;
            } else {
                raiz.esq = maiorEsq(raiz, raiz.esq);
            }
        }

        private void remover2(int x, No i, No pai) throws Exception {
            if (i == null) {
                throw new Exception("Erro ao remover2!");
            } else if (x < i.elemento) {
                remover2(x, i.esq, i);
            } else if (x > i.elemento) {
                remover2(x, i.dir, i);
            } else if (i.dir == null) {
                pai = i.esq;
            } else if (i.esq == null) {
                pai = i.dir;
            } else {
                i.esq = maiorEsq(i, i.esq);
            }
        }

        public int getRaiz() throws Exception {
            return raiz.elemento;
        }
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int c = Integer.parseInt(sc.nextLine().trim());
        for (int i = 0; i < c; i++) {
            ArvoreBinaria arvore = new ArvoreBinaria();
            String n = sc.nextLine().trim();
            String tmp = sc.nextLine();
            String[] array = tmp.split(" ");

            for (int j = 0; j < Integer.parseInt(n); j++) {
                arvore.inserir(Integer.parseInt(array[j]));
            }
            System.out.println("Case " + (i + 1) + ":");
            System.out.print("Pre.: ");
            arvore.caminharPre();
            System.out.print("In..: ");
            arvore.caminharCentral();
            System.out.print("Post: ");
            arvore.caminharPos();
            System.out.println("");
        }
        sc.close();
    }
}
