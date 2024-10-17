/**
 * La classe JeuMarienbadJvJ implémente une version console du jeu Marienbad
 * (également connu sous le nom de Nim) pour deux joueurs. Le jeu consiste à ce que les joueurs
 * prennent des tours pour enlever des bâtonnets de différents tas, et le joueur forcé de prendre
 * le dernier bâtonnet perd.
 * 
 * Fonctionnalités :
 * - Art ASCII pour le titre du jeu, l'accueil, les paramètres et les écrans de jeu.
 * - Invites interactives pour les noms des joueurs et les actions de jeu.
 * - Génération aléatoire des tas avec un nombre spécifié de lignes.
 * - Fonctionnalité de nettoyage de l'écran de la console.
 * - Option de rejouer ou de quitter après la fin d'une partie.
 * 
 * Méthodes :
 * - jouer(int nbLignes) : Gère la boucle principale du jeu, les tours des joueurs et les conditions de fin de jeu.
 * - afficherTas(int[] tas, int k) : Affiche l'état actuel des tas.
 * - joueur(int[] tas, String nomJoueur, int k) : Gère le tour d'un joueur, y compris le choix d'un tas et le retrait des bâtonnets.
 * - clear() : Nettoie l'écran de la console.
 * - jeuFini(int[] tas) : Vérifie si le jeu est terminé (tous les tas sont vides).
 * - genererTas(int nbLignes) : Génère la répartition initiale des bâtonnets dans les tas.
 * - principal() : Initialise le jeu, demande le nombre de lignes et démarre le jeu.
 * - main(String[] args) : Point d'entrée du programme, crée une instance du jeu et le démarre.
 * 
 * Utilisation :
 * Pour jouer au jeu, exécutez la méthode main. Le jeu demandera les noms des joueurs et le nombre de lignes,
 * puis procédera à la boucle de jeu jusqu'à ce qu'un joueur gagne. Après la fin du jeu, les joueurs peuvent choisir de rejouer ou de quitter.
 */
public class MarienbadJvsJ_AUDEBERT_TOUZEAU {
    //Colors from :
	//https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println#5762502
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
    
    final String ASCII_ART_NAME = ".------..------..------.     .------..------..------..------.\n" +
                                  "|N.--. ||I.--. ||M.--. |.-.  |G.--. ||A.--. ||M.--. ||E.--. |\n" +
                                  "| :(): || (\\/)|| (\\/) ((5)) | :/\\: || (\\/) || (\\/) || (\\/) |\n" +
                                  "| ()() || :\\/: || :\\/: |'-.-.| :\\/: || :\\/: || :\\/: || :\\/: |\n" +
                                  "| '--'N|| '--'I|| '--'M| ((1)) '--'G|| '--'A|| '--'M|| '--'E|\n" +
                                  "`------'`------'`------'  '-'`------'`------'`------'`------'";

    final String ASCII_ART_PLAY = " ____ ____ ____ ____ \n" +
                                  "||P |||L |||A |||Y ||\n" +
                                  "||__|||__|||__|||__||\n" +
                                  "|/__\\|/__\\|/__\\|/__\\|";

    void principal() {
        clear();
        System.out.println(ASCII_ART_NAME);
        System.out.println("Bienvenue sur ce jeu de Marienbad.");
        System.out.println();

        int nbLignes;
        do {
            nbLignes = SimpleInput.getInt("Donnez le nombre de lignes : ");
        } while (nbLignes < 1 || nbLignes > 15);
        jouer(nbLignes);
    }
    
    /**
     * Gère la boucle principale du jeu, les tours des joueurs et les conditions de fin de jeu.
     * @param nbLignes Nombre de lignes de départ pour les tas.
     */
    void jouer(int nbLignes) {
        boolean gameFinished = false;
        int[] tas = genererTas(nbLignes);
        int valLigneMax = tas[tas.length - 1] - 1;
        int rep;
        boolean firstRun = true;
        String nomJ1 = "";
        String nomJ2 = "";

        clear();
        System.out.println(ASCII_ART_NAME);
        System.out.println("Bienvenue sur ce jeu de Marienbad.");
        System.out.println();

        while (!gameFinished) {
            while (firstRun && nomJ1.equals("")) {
                nomJ1 = SimpleInput.getString("Entrez le nom du joueur 1 : ");
            }
            while (firstRun && nomJ2.equals("")) {
                nomJ2 = SimpleInput.getString("Entrez le nom du joueur 2 : ");
            }
            joueur(tas, nomJ1, valLigneMax);
            if (jeuFini(tas)) {
                System.out.println(nomJ1 + " a gagne !");
                gameFinished = true;
            } else {
                joueur(tas, nomJ2, valLigneMax);
                if (jeuFini(tas)) {
                    System.out.println(nomJ2 + " a gagne !");
                    gameFinished = true;
                }
            }
        }

        System.out.println();
        System.out.println("Que voulez-vous faire ?");
        System.out.println("1. Rejouer");
        System.out.println("2. Sortir");

        do {
            rep = SimpleInput.getInt("Entrez le numéro du menu : ");
        } while (rep < 1 || rep > 2);
        
        if (rep == 1) {
            jouer(nbLignes);
        } else if (rep == 2) {
            System.out.println("Merci d'avoir joué !");
            System.out.println("Au revoir !");
        }
    }

    
    /**
     * Affiche les tas de bâtonnets restants dans le jeu de Marienbad.
     *
     * @param tas Un tableau d'entiers représentant les tas de bâtonnets.
     * @param k Un entier représentant l'espacement initial entre les bâtonnets.
     */
    void afficherTas(int[] tas, int k) {
        System.out.println();
        for (int i = 0; i < tas.length; i++) {
            if (tas[i] != 0) {
                System.out.print("Ligne n°" + (i + 1) + " :\t" + tas[i] + "\t");
                for (int c = k; c > 0; c--) {
                    System.out.print(" ");
                }
                if (k % 2 == 0) {
                    k = k - 2;
                } else {
                    k = k - 1;
                }
                for (int j = 0; j < tas[i]; j++) {
                    System.out.print("| ");
                }
                System.out.println();
            }
        }
    }

    /**
     * Gère le tour d'un joueur, y compris le choix d'un tas et le retrait des bâtonnets.
     * @param tas tableau contenant les contenus des tas
     * @param nomJoueur nom du joueur
     * @param k espacement initial entre les bâtonnets
     */
    void joueur(int[] tas, String nomJoueur, int k) {
        int tasChoisi = 0;
        int nbAEnlever = 0;

        System.out.println("\n");
        System.out.println("A " + nomJoueur + " de jouer.");

        afficherTas(tas, k);
        System.out.println();

        do {
            tasChoisi = SimpleInput.getInt("Choisissez un tas parmis ceux ci dessus : ") - 1;
        } while (tasChoisi < 0 || tasChoisi >= tas.length || tas[tasChoisi] == 0);

        do {
            nbAEnlever = SimpleInput.getInt("Nombre de bâtons a enlever du tas n°" + (tasChoisi + 1) + " : ");
        } while (nbAEnlever < 1 || nbAEnlever > tas[tasChoisi]);

        tas[tasChoisi] -= nbAEnlever;
    }

    /**
     * Nettoie l'écran de la console
     */
    void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Vérifie si le jeu est terminé (tous les tas sont vides)
     * @param tas tableau contenant les contenus des tas
     * @return true si le jeu est terminé, false sinon
     */
    boolean jeuFini(int[] tas) {
        boolean fini = true;

        for (int i = 0; i < tas.length; i++) {
            if (tas[i] > 0) {
                fini = false;
            }
        }

        return fini;
    }

    /**
     * Effectue un test de la méthode jeuFini
     * @param tas Tableau contenant les contenus des tas
     * @param result Résultat attendu
     */
    void testCasJeuFini(int[] tas, boolean result){
        // Affichage
        System.out.print("jeuFini(" + tabToString(tas) + ") \t= " + result + "\t : ");
        // Appel
        boolean res = jeuFini(tas);
        // Verification
        if (res == result){
            System.out.println(ANSI_GREEN + "OK" + ANSI_RESET);
        } else {
            System.err.println(ANSI_RED + "ERREUR" + ANSI_RESET);
        }
    }

    /**
     * Teste la méthode jeuFini
     */
    void testJeuFini(){
        System.out.println();
        System.out.println("*** testJeuFini()");
        // 1. Cas où le jeu est fini
        testCasJeuFini(new int[]{0, 0, 0}, true);

        // 2. Cas où le jeu n'est pas fini
        testCasJeuFini(new int[]{1, 0, 0}, false);

        // 3. Cas avec un seul tas non vide
        testCasJeuFini(new int[]{0, 0, 1}, false);

        // 4. Cas avec un tas vide
        testCasJeuFini(new int[]{0, 1, 0}, false);

        // 5. Cas avec plusieurs tas non vides
        testCasJeuFini(new int[]{1, 1, 1}, false);

        // 6. Cas avec un tas vide et un tas non vide
        testCasJeuFini(new int[]{0, 1, 1}, false);
    }

    /**
     * Génère la répartition initiale des bâtonnets dans les tas.
     * @param nbLignes nombre de lignes de départ pour les tas
     * @return tableau d'entiers représentant les tas de bâtonnets
     */
    int[] genererTas(int nbLignes) {
        int[] lignes = new int[nbLignes];

        lignes[0] = 1;

        for (int i = 1; i < lignes.length; i++) {
            lignes[i] = lignes[i - 1] + 2;
        }

        return lignes;
    }

    /**
     * Effectue un test de la méthode genererTas
     * @param nbLignes Nombre de lignes de départ pour les tas
     * @param result Résultat attendu
     */
    void testCasGenererTas(int nbLignes, int[] result){
        // Affichage
        System.out.print("genererTas(" + nbLignes + ") \t= " + tabToString(result) + "\t : ");
        // Appel
        int[] res = genererTas(nbLignes);
        // Verification
        if (tabEgaux(res, result)){
            System.out.println(ANSI_GREEN + "OK" + ANSI_RESET);
        } else {
            System.err.println(ANSI_RED + "ERREUR" + ANSI_RESET);
        }
    }

    /**
     * Teste la méthode genererTas
     */
    void testGenererTas(){
        System.out.println();
        System.out.println("*** testGenererTas()");
        // 1. Cas avec 2 lignes
        testCasGenererTas(2, new int[]{1, 3});

        // 2. Cas avec 3 lignes
        testCasGenererTas(3, new int[]{1, 3, 5});

        // 3. Cas avec 4 lignes
        testCasGenererTas(4, new int[]{1, 3, 5, 7});

        // 4. Cas avec 5 lignes
        testCasGenererTas(5, new int[]{1, 3, 5, 7, 9});

        // 5. Cas avec 6 lignes
        testCasGenererTas(6, new int[]{1, 3, 5, 7, 9, 11});

        // 6. Cas avec 7 lignes
        testCasGenererTas(7, new int[]{1, 3, 5, 7, 9, 11, 13});

        // 7. Cas avec 8 lignes
        testCasGenererTas(8, new int[]{1, 3, 5, 7, 9, 11, 13, 15});

        // 8. Cas avec 9 lignes
        testCasGenererTas(9, new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17});

        // 9. Cas avec 10 lignes
        testCasGenererTas(10, new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19});

        // 10. Cas avec 11 lignes
        testCasGenererTas(11, new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21});

        // 11. Cas avec 12 lignes
        testCasGenererTas(12, new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23});
    }

    void test(){
        testGenererTas();
        testJeuFini();
    }

    /**
     * Transforme un tableau d'entiers en chaine de caractère
     * @param tab tableau d'entiers
     * @return chaine de caractère représentant le tableau
     */
    String tabToString(int[] tab){
        String res = "{";
        for(int i = 0; i < tab.length; i++){
            res += tab[i];
            if(i != tab.length - 1){
                res += ", ";
            }
        }
        res += "}";
        return res;
    }

    boolean tabEgaux(int[] tab1, int[] tab2){
        boolean res = true;
        if(tab1.length != tab2.length){
            res = false;
        } else {
            for(int i = 0; i < tab1.length; i++){
                if(tab1[i] != tab2[i]){
                    res = false;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MarienbadJvsJ_AUDEBERT_TOUZEAU jeu = new MarienbadJvsJ_AUDEBERT_TOUZEAU();
        jeu.principal();
    }
}
