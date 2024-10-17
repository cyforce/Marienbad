
/**
 * La classe JeuMarienbad représente une implémentation console du jeu de Marienbad.
 * Elle fournit des méthodes pour afficher des dessins ASCII, gérer les paramètres du jeu,
 * et gérer le déroulement du jeu entre les joueurs et/ou l'IA.
 * 
 * Constantes :
 * - ASCII_ART_NAME : Représentation ASCII du nom du jeu.
 * - ASCII_ART_HOME : Représentation ASCII du menu principal.
 * - ASCII_ART_SETTINGS : Représentation ASCII du menu des paramètres.
 * - ASCII_ART_PLAY : Représentation ASCII du menu de jeu.
 * 
 * Méthodes :
 * - principal() : Fonction principale qui gère le menu.
 * - afficherParametres(int[] param) : Affiche et modifie les paramètres du jeu.
 * - jouer(int[] params) : Démarre le jeu avec les paramètres donnés.
 * - afficherTas(int[] tas, int k) : Affiche les différents tas et leur contenu s'ils ne sont pas vides.
 * - joueur(int[] tas, String nomJoueur, int k) : Gère le tour du joueur.
 * - IA(int[] tas, int difficulte, int ia, int k) : Gère le tour de l'IA.
 * - coupRandom(int[] tas) : Effectue un coup aléatoire pour l'IA.
 * - coupParfait(int[] tas) : Effectue le meilleur coup possible pour l'IA.
 * - jeuFini(int[] tas) : Vérifie si le jeu est terminé.
 * - genererTas(int nbLignes) : Génère la répartition initiale des bâtons en tas.
 * - clear() : Efface l'écran de la console.
 * 
 * @author Célian TOUZEAU
 * @autor Giovani AUDEBERT
 */
class MarienbadJvsO_AUDEBERT_TOUZEAU {
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

    final String ASCII_ART_HOME = " ____ ____ ____ ____ \n" +
            "||H |||O |||M |||E ||\n" +
            "||__|||__|||__|||__||\n" +
            "|/__\\|/__\\|/__\\|/__\\|";

    final String ASCII_ART_SETTINGS = " ____ ____ ____ ____ ____ ____ ____ ____ \n" +
            "||S |||E |||T |||T |||I |||N |||G |||S ||\n" +
            "||__|||__|||__|||__|||__|||__|||__|||__||\n" +
            "|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|";

    final String ASCII_ART_PLAY = " ____ ____ ____ ____ \n" +
            "||P |||L |||A |||Y ||\n" +
            "||__|||__|||__|||__||\n" +
            "|/__\\|/__\\|/__\\|/__\\|";
    
    final String ASCII_ART_TEST = " ____ ____ ____ ____ \n" +
            "||T |||E |||S |||T ||\n" +
            "||__|||__|||__|||__||\n" +
            "|/__\\|/__\\|/__\\|/__\\|";

    
    void principal() {
        boolean exit = false;
        int rep;
        int[] params = { 1, 2, 15 }; // Paramètres du jeu par défaut

        // Boucle du menu principal
        while (!exit) {
            clear();
            System.out.println(ASCII_ART_NAME);
            System.out.println("Bienvenue sur ce jeu de Marienbad.");
            System.out.println();
            
            System.out.println(ASCII_ART_HOME);
            System.out.println("\nQue voulez-vous faire ?");
            System.out.println("1. Jouer");
            System.out.println("2. Paramètres");
            System.out.println("3. Quitter");

            rep = SimpleInput.getInt("Entrez le numéro du menu : ");

            // Gestion des choix utilisateur
            if (rep == 1) {
                jouer(params); // Lancer le jeu
            } else if (rep == 2) {
                System.out.println(ASCII_ART_SETTINGS);
                afficherParametres(params); // Afficher les paramètres
            } else if (rep == 3) {
                System.out.println("Au revoir !");
                exit = true; // Sortir du programme
            } else {
                System.out.println("Choix non valide. Veuillez entrer 1, 2 ou 3.");
            }
        }
    }

    /**
     * Affiche et modifie les paramètres du jeu
     * @param param Tableau d'entiers contenant les paramètres du jeu.
     */
    void afficherParametres(int[] param) {
        boolean parametres = true;
        int rep;
        String nomModeJeu = "";
        int modeJeu = param[0];
        int difficulteIA = param[1];
        int nbLignes = param[2];

        if (modeJeu == 1) {
            nomModeJeu = "Joueur contre IA";
        } else if (modeJeu == 2) {
            nomModeJeu = "IA contre joueur";
        } else if (modeJeu == 3) {
            nomModeJeu = "Joueur contre joueur";
        } else if (modeJeu == 4) {
            nomModeJeu = "IA contre IA";
        }

        while (parametres) {
            clear();
            System.out.println(ASCII_ART_NAME);
            System.out.println("Bienvenue sur ce jeu de Marienbad.");
            System.out.println();
            System.out.println(ASCII_ART_SETTINGS);
            System.out.println();

            System.out.println("Ici vous pouvez modifier les paramètres.");
            
            System.out.println("\nQue voulez-vous changer ?");
            System.out.println("1. Mode de jeu : " + nomModeJeu);
            System.out.println("2. Difficulte IA : " + difficulteIA);
            System.out.println("3. Nombre de lignes : " + nbLignes);
            System.out.println("4. Tests");
            System.out.println("5. Retour au menu principal");

            rep = SimpleInput.getInt("Entrez le numéro du menu : ");

            // Gestion des choix utilisateur
            if (rep == 1) { // Changer le mode de jeu
                clear();
                System.out.println(ASCII_ART_NAME);
                System.out.println("Bienvenue sur ce jeu de Marienbad.");
                System.out.println();
                System.out.println(ASCII_ART_SETTINGS);
                System.out.println("\nvaleur actuelle : " + nomModeJeu);

                System.out.println();
                System.out.println("Choisissez le type de jeu : ");
                System.out.println("1. Joueur contre IA");
                System.out.println("2. IA contre joueur");
                System.out.println("3. Joueur contre joueur");
                System.out.println("4. IA contre IA");

                do {
                    rep = SimpleInput.getInt("Entrez le numéro du mode de jeu : ");
                } while (rep < 1 || rep > 4);

                if (rep == 1) {
                    nomModeJeu = "Joueur contre IA";
                    modeJeu = 1;
                } else if (rep == 2) {
                    nomModeJeu = "IA contre joueur";
                    modeJeu = 2;
                } else if (rep == 3) {
                    nomModeJeu = "Joueur contre joueur";
                    modeJeu = 3;
                } else if (rep == 4) {
                    nomModeJeu = "IA contre IA";
                    modeJeu = 4;
                }
            } else if (rep == 2) {  // Changer la difficulté de l'IA
                clear();
                System.out.println(ASCII_ART_NAME);
                System.out.println("Bienvenue sur ce jeu de Marienbad.");
                System.out.println();
                System.out.println(ASCII_ART_SETTINGS);
                System.out.println("\nvaleur actuelle : " + difficulteIA);

                do {
                    System.out.println();
                    difficulteIA = SimpleInput.getInt("Entrez la difficulte de l'IA (entre 1 et 5) : ");
                } while (difficulteIA < 1 && difficulteIA > 5);
            } else if (rep == 3) {  // Changer le nombre de lignes
                clear();
                System.out.println(ASCII_ART_NAME);
                System.out.println("Bienvenue sur ce jeu de Marienbad.");
                System.out.println();
                System.out.println(ASCII_ART_SETTINGS);
                System.out.println("\nvaleur actuelle : " + nbLignes);

                do {
                    System.out.println();
                    nbLignes = SimpleInput.getInt("Entrez le nombre de lignes (entre 2 et 15) : ");
                } while (nbLignes < 2 && nbLignes > 15);
            } else if (rep == 4) {  // Tests
                boolean test = true;
                clear();
                System.out.println(ASCII_ART_NAME);
                System.out.println("Bienvenue sur ce jeu de Marienbad.");
                System.out.println();
                System.out.println(ASCII_ART_TEST);

                while (test) {
                    System.out.println();
                    System.out.println("Quelle methode tester : ");
                    System.out.println("1. void coupParfait(int[] tas)");
                    System.out.println("2. boolean jeuFini(int[] tas)");
                    System.out.println("3. int[] genererTas(int nbLignes)");
                    System.out.println("4. Retour");

                    do {
                        rep = SimpleInput.getInt("Entrez le numéro du test : ");
                    } while (rep < 1 || rep > 4);

                    if (rep == 1) {
                        testCoupParfait();
                    } else if (rep == 2) {
                        testJeuFini();
                    } else if (rep == 3) {
                        testGenererTas();
                    } else if (rep == 4) {
                        test = false;
                    }
                }
            } else if (rep == 5) {  // Retour au menu principal
                param[0] = modeJeu;
                param[1] = difficulteIA;
                param[2] = nbLignes;
                parametres = false; // Sortir des paramètres
            } else {
                System.out.println("Choix non valide. Veuillez entrer la valeur d'une des propositions.");
            }
        }
    }

    /**
     * Gère la boucle principale du jeu, les tours des joueurs et les conditions de fin de jeu.
     * @param params Tableau d'entiers contenant les paramètres du jeu sous la forme [mode de jeu, difficulté IA, nombre de lignes].
     */
    void jouer(int[] params) {
        boolean gameFinished = false;
        int[] tas = genererTas(params[2]);
        int valLigneMax = tas[tas.length - 1] - 1;
        int rep;
        boolean firstRun = true;
        String nomJ1 = "";
        String nomJ2 = "";

        clear();
        System.out.println(ASCII_ART_NAME);
        System.out.println("Bienvenue sur ce jeu de Marienbad.");
        System.out.println();

        // Boucle de jeu
        while (!gameFinished) {
            if (params[0] == 1) {
                while (firstRun && nomJ1.equals("")) {
                    nomJ1 = SimpleInput.getString("Entrez le nom du joueur : ");
                }
                joueur(tas, nomJ1, valLigneMax);
                if (jeuFini(tas)) {
                    System.out.println("Bravo, vous avez gagne !");
                    gameFinished = true;
                } else {
                    IA(tas, params[1], -1, valLigneMax);
                    if (jeuFini(tas)) {
                        System.out.println("L'IA a gagne !");
                        gameFinished = true;
                    }
                }
            } else if (params[0] == 2) {
                IA(tas, params[1], -1, valLigneMax);
                if (jeuFini(tas)) {
                    System.out.println("L'IA a gagne !");
                    gameFinished = true;
                } else {
                    while (firstRun && nomJ1.equals("")) {
                        nomJ1 = SimpleInput.getString("Entrez le nom du joueur : ");
                    }
                    joueur(tas, nomJ1, valLigneMax);
                    if (jeuFini(tas)) {
                        System.out.println("Bravo, vous avez gagne !");
                        gameFinished = true;
                    }
                }
            } else if (params[0] == 3) {
                while (firstRun && nomJ1.equals("")) {
                    nomJ1 = SimpleInput.getString("Entrez le nom du joueur : ");
                }
                while (firstRun && nomJ2.equals("")) {
                    nomJ2 = SimpleInput.getString("Entrez le nom du joueur : ");
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
            } else if (params[0] == 4) {
                IA(tas, params[1], 1, valLigneMax);
                if (jeuFini(tas)) {
                    System.out.println("L'IA 1 a gagne !");
                    gameFinished = true;
                } else {
                    IA(tas, params[1], 2, valLigneMax);
                    if (jeuFini(tas)) {
                        System.out.println("L'IA 2 a gagne !");
                        gameFinished = true;
                    }
                }
            }
            firstRun = false;
        }
        System.out.println();
        System.out.println("Que voulez-vous faire ?");
        System.out.println("1. Rejouer");
        System.out.println("2. Retour au menu principal");
        do {
            rep = SimpleInput.getInt("Entrez le numéro du menu : ");
        } while (rep < 1 || rep > 2);
        if (rep == 1) {
            jouer(params);
        }
    }

    /**
     * Affiche les tas et leur contenu
     * @param tas Tableau contenant les tas
     * @param k Espacement initial entre les bâtons
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
     * Gère le tour du joueur
     * @param tas Tableau contenant les contenus des tas
     * @param nomJoueur Nom du joueur
     * @param k Espacement initial entre les bâtons
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
     * Gère le tour de l'IA
     * @param tas Tableau contenant les contenus des tas
     * @param difficulte Difficulté de l'IA
     * @param ia Numéro de l'IA
     * @param k Espacement initial entre les bâtons
     */
    void IA(int[] tas, int difficulte, int ia, int k) {
        System.out.println();
        System.out.println("\n");
        if (ia != -1) {
            System.out.println("A l'IA " + ia + " de jouer.");
        } else {
            System.out.println("Au tour de l'IA de jouer.");
        }

        afficherTas(tas, k);

        if (difficulte == 1) {
            coupRandom(tas);
        } else if (difficulte == 2) {
            if ((Math.random() * 4) + 1 == 2) {
                coupParfait(tas);
            } else {
                coupRandom(tas);
            }
        } else if (difficulte == 3) {
            if ((Math.random() * 2) + 1 == 1) {
                coupRandom(tas);
            } else {
                coupParfait(tas);
            }
        } else if (difficulte == 4) {
            if ((Math.random() * 4) + 1 == 2) {
                coupRandom(tas);
            } else {
                coupParfait(tas);
            }
        } else if (difficulte == 5) {
            coupParfait(tas);
        }
    }

    /**
     * Effectue un coup aléatoire pour l'IA
     * @param tas Tableau contenant les contenus des tas
     */
    void coupRandom(int[] tas) {
        int i;
        int nbAEnlever;

        do {
            i = (int) (Math.random() * tas.length);
        } while (tas[i] == 0);
        nbAEnlever = (int) (Math.random() * tas[i]) + 1;
        System.out.println("\nL'IA enlève " + nbAEnlever + " bâtons du tas n°" + (i + 1));
        tas[i] -= nbAEnlever;
    }

    /**
     * Effectue le meilleur coup possible pour l'IA
     * @param tas Tableau contenant les contenus des tas
     */
    void coupParfait(int[] tas) {
        int xorTot = 0;
        int nbAEnlever;

        // Calcul du Nim Sum par des XOR entre tous les tas
        for (int i = 0; i < tas.length; i++) {
            xorTot ^= tas[i];
        }

        // Si la Nim Sum est à 0, il faut jouer un coup aléatoire car position perdante
        if (xorTot == 0) {
            boolean nbChoisi = false;
            int i = 0;

            while (i < tas.length && !nbChoisi) {
                if (tas[i] > 0) {
                    nbAEnlever = (int) (Math.random() * tas[i]) + 1;
                    System.out.println("L'IA enlève " + nbAEnlever + " bâtons du tas n°" + (i + 1));
                    tas[i] -= nbAEnlever;
                    nbChoisi = true;
                }

                i++;
            }
        } else { // Si la Nim Sum est suppérieure à 0, il faut faire revenir la Nim Sum à 0 pour placer l'adversaire en position perdante
            boolean nbChoisi = false;
            int i = 0;

            while (i < tas.length && !nbChoisi) {
                int xorAvecTas = tas[i] ^ xorTot;

                if (xorAvecTas < tas[i]) {
                    nbAEnlever = tas[i] - xorAvecTas;
                    System.out.println("L'IA enlève " + nbAEnlever + " bâtons du tas n°" + (i + 1));
                    tas[i] -= nbAEnlever;
                    nbChoisi = true;
                }

                i++;
            }
        }
    }

    /**
     * Effectue un test de la méthode coupParfait
     * @param tas Tableau contenant les contenus des tas
     * @param result Résultat attendu
     */
    void testCasCoupParfait(int[] tas, int[] result){
        // Affichage
		System.out.print("coupParfait(" + tabToString(tas) + ") \t= " + tabToString(result) + "\t : ");
		// Appel
		coupParfait(tas);
		// Verification
		if (tabEgaux(tas, result)){
			System.out.println(ANSI_GREEN + "OK" + ANSI_RESET);
		} else {
			System.err.println(ANSI_RED + "ERREUR" + ANSI_RESET);
		}
    }

    /**
     * Teste la méthode coupParfait
     */
    void testCoupParfait(){
        System.out.println();
        System.out.println("*** testCoupParfait()");
        // 1. Cas où la Nim Sum est déjà nulle (Position perdante)
        testCasCoupParfait(new int[]{3, 3, 3}, new int[]{0, 3, 3});

        // 2. Cas simple où un seul tas est non vide
        testCasCoupParfait(new int[]{0, 0, 5}, new int[]{0, 0, 0});

        // 3. Cas classique où l'IA peut forcer la victoire
        testCasCoupParfait(new int[]{1, 2, 3}, new int[]{0, 2, 3});

        // 4. Cas avec plusieurs petits tas
        testCasCoupParfait(new int[]{1, 1, 1, 1}, new int[]{0, 1, 1, 1});

        // 5. Cas avec un nombre croissant de bâtons dans chaque tas
        testCasCoupParfait(new int[]{1, 2, 3, 4, 5}, new int[]{0, 2, 3, 4, 5});

        // 6. Cas avec tous les tas égaux
        testCasCoupParfait(new int[]{2, 2, 2}, new int[]{0, 2, 2});

        // 7. Cas avec un seul bâton dans un tas
        testCasCoupParfait(new int[]{0, 0, 1}, new int[]{0, 0, 0});

        // 8. Cas où l'IA ne peut pas gagner immédiatement
        testCasCoupParfait(new int[]{4, 1, 1}, new int[]{0, 1, 1});
    }

    /**
     * Vérifie si le jeu est terminé
     * @param tas Tableau contenant les contenus des tas
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
     * Génère la répartition initiale des bâtons en tas
     * @param nbLignes Nombre de lignes de départ pour les tas
     */
    int[] genererTas(int nbLignes) {
        int[] lignes = new int[nbLignes];

        lignes[0] = 1;

        // Donner à chaque tas au moins 1 bâton pour éviter les tas à 0
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

    /**
     * Nettoie l'écran de la console
     */
    void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
}
