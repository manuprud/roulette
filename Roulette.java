/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roulette;

/**
 *
 * @author Prudhomme Emmanuel
 */
public class Roulette {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        byte nbr_j;
        String tmp_nomjoueur;

        System.out.println("Bienvenu au jeu de la Roulette");

        nbr_j = func_nbr();

        //choix du nombre de joueurs (max 3)
        while (nbr_j < 1 || nbr_j > 3) {
            System.out.println("ERREUR: Choisisez un numero valide");
            nbr_j = func_nbr();
        }

        // liste_j -> array contenant nom des joueurs
        String liste_j[] = new String[nbr_j];
        // jetons_j -> array contenant les jetons des joueurs
        int jetons_j[] = {0, 0, 0};
        // jeu_j contient la liste des mises des joueurs
        //format => [liste_des_joueurs] [numeros_de mise (maximum de 37 mises / tour / joueurs)] [type de mise, montant de la mise, numeros1, numeros2, numeros3, numeros4]
        int jeu_j[][][] = new int[3][36][6];
        for (byte i = 0; i < nbr_j; i++) {
            do {
                System.out.print("Nom du joueur n°" + (i + 1) + ":");
                tmp_nomjoueur = Lire.S();
            } while (valid_joueur(tmp_nomjoueur, liste_j));
            liste_j[i] = tmp_nomjoueur;
            jetons_j[i] = 300;
        }
        System.out.println("C'est parti pour le jeux de la roulette en Java \n");
//tant qu'au moins 1 joueur à des jetons
        while (jetons_j[0] > 0 || jetons_j[1] > 0 || jetons_j[2] > 0) {
            System.out.println("\n\n***********************");
            System.out.println("*   Faites vos jeux   *");
            System.out.println("***********************");

            //liste les joueurs
            for (byte i = 0; i < nbr_j; i++) {
                byte choix_tmp = 0;
                byte placement = 0;

                //si le joueur a des jetons
                if (jetons_j[i] > 0) {

                    System.out.println("\n C'est au tour de " + (liste_j[i]) + " avec un total de " + (jetons_j[i]) + " jetons \n");
                    //calcul le nombre de mise par tour : incrementé en fin de boucle
                    int nbr_de_mise = 0;
                    //flag_fin_tour gère la fin de tour du joueur
                    boolean flag_fin_tour = false;
                    //etiquette play: mets fin au tour du joueur s'il le souhaite
                    play: while (flag_fin_tour == false && nbr_de_mise < 36) {

                        //flag controle validité choix placement
                        boolean flag_t_p = false;

                        //tant que le choix du type de placement n'est pas valide
                        while (flag_t_p == false) {
                            show_placement();
                            choix_tmp = Lire.b();
                            switch (choix_tmp) {
                                case 0:
                                    break play;
                                case 1:
                                    placement = 1;
                                    flag_t_p = true;
                                    break;
                                case 2:
                                    placement = 8;
                                    flag_t_p = true;
                                    break;
                                default:
                                    System.out.println("ERREUR: Choisisez un numero valide");
                            }
                        }
                        //réinitialise choix temporaire et flag
                        choix_tmp = 0;
                        flag_t_p = false;
                        //si placement interieur
                        if (placement == 1) {
                            //tant que le choix du type de placement n'est pas valide
                            while (flag_t_p == false) {
                                show_interieur();
                                choix_tmp = Lire.b();
                                switch (choix_tmp) {
                                    case 1:
                                        placement += 0;
                                        flag_t_p = true;
                                        break;
                                    case 2:
                                        placement += 1;
                                        flag_t_p = true;
                                        break;
                                    case 3:
                                        placement += 2;
                                        flag_t_p = true;
                                        break;
                                    case 4:
                                        placement += 3;
                                        flag_t_p = true;
                                        break;
                                    case 5:
                                        placement += 4;
                                        flag_t_p = true;
                                        break;
                                    case 6:
                                        placement += 5;
                                        flag_t_p = true;
                                        break;
                                    case 7:
                                        placement += 6;
                                        flag_t_p = true;
                                        break;
                                    default:
                                        System.out.println("ERREUR: Choisisez un numero valide");
                                }
                            }
                        } //si placement exterieur
                        else if (placement == 8) {
                            //tant que le choix du type de placement n'est pas valide
                            while (flag_t_p == false) {
                                show_exterieur();
                                choix_tmp = Lire.b();
                                switch (choix_tmp) {
                                    case 1:
                                        placement += 0;
                                        flag_t_p = true;
                                        break;
                                    case 2:
                                        placement += 1;
                                        flag_t_p = true;
                                        break;
                                    case 3:
                                        placement += 2;
                                        flag_t_p = true;
                                        break;
                                    case 4:
                                        placement += 3;
                                        flag_t_p = true;
                                        break;
                                    case 5:
                                        placement += 4;
                                        flag_t_p = true;
                                        break;
                                    default:
                                        System.out.println("ERREUR: Choisisez un numero valide");
                                }
                            }
                        } else {
                            System.out.println("ERREUR");
                            System.exit(0);
                        }
                        //affiche le jeu
                        show_table();
                        switch (placement) {
                            case 0:
                                break;
                            case 1:
                                jeu_j[i][nbr_de_mise] = en_plein(jetons_j[i]);
                                //retire les jetons misés
                                jetons_j[i] -= jeu_j[i][nbr_de_mise][1];
                                break;
                            case 2:
                                jeu_j[i][nbr_de_mise] = a_cheval(jetons_j[i]);
                                //retire les jetons misés
                                jetons_j[i] -= jeu_j[i][nbr_de_mise][1];
                                break;
                            case 3:
                                jeu_j[i][nbr_de_mise] = transversale(jetons_j[i]);
                                //retire les jetons misés
                                jetons_j[i] -= jeu_j[i][nbr_de_mise][1];
                                break;
                            case 4:
                                jeu_j[i][nbr_de_mise] = triple(jetons_j[i]);
                                //retire les jetons misés
                                jetons_j[i] -= jeu_j[i][nbr_de_mise][1];
                                break;
                            case 5:
                                jeu_j[i][nbr_de_mise] = carre(jetons_j[i]);
                                //retire les jetons misés
                                jetons_j[i] -= jeu_j[i][nbr_de_mise][1];
                                break;
                            case 6:
                                jeu_j[i][nbr_de_mise] = ligne_haut(jetons_j[i]);
                                //retire les jetons misés
                                jetons_j[i] -= jeu_j[i][nbr_de_mise][1];
                                break;
                            case 7:
                                jeu_j[i][nbr_de_mise] = sixain(jetons_j[i]);
                                //retire les jetons misés
                                jetons_j[i] -= jeu_j[i][nbr_de_mise][1];
                                break;
                            case 8:
                                jeu_j[i][nbr_de_mise] = colonnes(jetons_j[i]);
                                //retire les jetons misés
                                jetons_j[i] -= jeu_j[i][nbr_de_mise][1];
                                break;
                            case 9:
                                jeu_j[i][nbr_de_mise] = douzaines(jetons_j[i]);
                                //retire les jetons misés
                                jetons_j[i] -= jeu_j[i][nbr_de_mise][1];
                                break;
                            case 10:
                                jeu_j[i][nbr_de_mise] = rouge_noir(jetons_j[i]);
                                //retire les jetons misés
                                jetons_j[i] -= jeu_j[i][nbr_de_mise][1];
                                break;
                            case 11:
                                jeu_j[i][nbr_de_mise] = parite(jetons_j[i]);
                                //retire les jetons misés
                                jetons_j[i] -= jeu_j[i][nbr_de_mise][1];
                                break;
                            case 12:
                                jeu_j[i][nbr_de_mise] = moitier(jetons_j[i]);
                                //retire les jetons misés
                                jetons_j[i] -= jeu_j[i][nbr_de_mise][1];
                                break;
                            default:
                                System.out.println("ERREUR");
                                System.exit(0);
                        }
                        //reponse pour la demande d'un nouveau placement de jeton
                        char new_loop;

                        if (jetons_j[i] > 0) {
                            System.out.println("Il vous reste " + jetons_j[i] + "");
                            boolean flag_new_loop = false;

                            while (flag_new_loop == false) {
                                System.out.println("Souhaitez vous placer s'autre jetons y/n");

                                new_loop = Lire.c();
                                switch (new_loop) {
                                    case 'y':
                                        flag_new_loop = true;
                                        nbr_de_mise++;
                                        break;
                                    case 'n':
                                        flag_fin_tour = true;
                                        flag_new_loop = true;
                                        break;
                                    default:
                                        System.out.println("ERREUR de saisie: répondez par y ou par n");
                                }
                            }
                        } else {
                            flag_fin_tour = true;
                        }
                    }
                }
            }
            //je fait tourner le roulette

            byte roulette;
            System.out.println("***********************");
            System.out.println("*   Rien ne va plus   *\n");

            roulette = (byte) (Math.random() * 37);
            System.out.println("* Les jeux sont faits *");
            System.out.println("\n***********************\n*         " + roulette + "         *\n***********************\n");
            //info sur le numero de la bille + return couleur du numeros
            int[] info = info_bille(roulette);

            System.out.println("***********************");
            //calcul des gains
            //liste les joueurs
            for (byte i = 0; i < nbr_j; i++) {
                
                // controle si le joueur à joué ce tour ci
                //test si première type de mise !=0
                if(jeu_j[i][0][0] != 0){

                    
                //nombre de mise sur joueur i
                //recupère taille du tableau non vide.
                int length_jeu = jeu_j[i].length;
                int jeu_empty=0;
                for(int g=0; g<length_jeu; g++){
                    if(jeu_j[i][g][0] != 0){
                        jeu_empty++;
                    }
                }
                
                System.out.println("***********************");
                System.out.println("\n\n Verification des gains de " + liste_j[i] + ": \n");
                for (byte j = 0; j < jeu_empty; j++) {
                    
                    
                    int gain = 0;
                    // 
                    int reinitial[] = {0};
                    switch (jeu_j[i][j][0]) {
                        case 0:
                            break;
                        case 1:
                            gain = calc_en_plein(roulette, jeu_j[i][j]);
                            jetons_j[i] += gain;
                            jeu_j[i][j] = reinitial;
                            break;
                        case 2:
                            gain = calc_a_cheval(roulette, jeu_j[i][j]);
                            jetons_j[i] += gain;
                            jeu_j[i][j] = reinitial;
                            break;
                        case 3:
                            gain = calc_transversale(roulette, jeu_j[i][j]);
                            jetons_j[i] += gain;
                            jeu_j[i][j] = reinitial;
                            break;
                        case 4:
                            gain = calc_triple(roulette, jeu_j[i][j]);
                            jetons_j[i] += gain;
                            jeu_j[i][j] = reinitial;
                            break;
                        case 5:
                            gain = calc_carre(roulette, jeu_j[i][j]);
                            jetons_j[i] += gain;
                            jeu_j[i][j] = reinitial;
                            break;
                        case 6:
                            gain = calc_ligne_haut(roulette, jeu_j[i][j]);
                            jetons_j[i] += gain;
                            jeu_j[i][j] = reinitial;
                            break;
                        case 7:
                            gain = calc_sixain(roulette, jeu_j[i][j]);
                            jetons_j[i] += gain;
                            jeu_j[i][j] = reinitial;
                            break;
                        case 8:
                            gain = calc_colonnes(roulette, jeu_j[i][j]);
                            jetons_j[i] += gain;
                            jeu_j[i][j] = reinitial;
                            break;
                        case 9:
                            gain = calc_douzaines(roulette, jeu_j[i][j]);
                            jetons_j[i] += gain;
                            jeu_j[i][j] = reinitial;
                            break;
                        case 10:
                            gain = calc_rouge_noir(roulette, jeu_j[i][j], info[0]);
                            jetons_j[i] += gain;
                            jeu_j[i][j] = reinitial;
                            break;
                        case 11:
                            gain = calc_parite(roulette, jeu_j[i][j], info[1]);
                            jetons_j[i] += gain;
                            jeu_j[i][j] = reinitial;
                            break;
                        case 12:
                            gain = calc_moitier(roulette, jeu_j[i][j]);
                            jetons_j[i] += gain;
                            jeu_j[i][j] = reinitial;
                            break;
                        default:
                            System.out.println("ERREUR");
                            System.exit(0);
                    }
                }

            }
                }
            //affiche les jetons en fin de tour
            System.out.println("\n* * *   JETONS  * * * *");
            System.out.println("* * * * * * * * * * * *");
            for (int i = 0; i < nbr_j; i++) {
                System.out.println(liste_j[i] + " à " + jetons_j[i]);
            }
            System.out.println("* * * * * * * * * * * *\n");
        }

        System.out.println("fin de la partie");
        System.exit(0);
    }


    
    // demande le nombre de joueur en debut de partie
    public static byte func_nbr() {
        byte nbr_j;
        System.out.print("Indiquez le nombre de joueur (maximum 3 joueurs)");
        nbr_j = Lire.b();
        return nbr_j;
    }

    // verifie si le nom est deja utilisé
    public static boolean valid_joueur(String tmp_nomjoueur, String[] liste_j) {
        //flag qui valide le nom
        boolean flag_valide_j = false;
        int liste_length = liste_j.length;
        for (int i = 0; i < liste_length; i++) {
            // equals() est la seul fonction de ce programme que l'on n'a pas vue en cours
            // elle me permet de comparer le contenu de 2 objets de type String
            if (tmp_nomjoueur.equals(liste_j[i])) {
                System.out.println("Erreur: nom de joueur deja utilisé");
                flag_valide_j = true;
            }
        }
        return flag_valide_j;
    }

    //affiche la table de jeu
    public static boolean show_table() {
        System.out.println("\n");
        System.out.println("     3  6  9  12 | 15  18  21  24 | 27  30  33  36 | 3eC");
        System.out.println("0    2  5  8  11 | 14  17  20  23 | 26  29  32  35 | 2eC");
        System.out.println("     1  4  7  10 | 13  16  19  22 | 25  28  31  34 | 1eC");
        System.out.println("    1er douzaine    2nd douzaine     3em douzaine");
        return true;
    }

    //affiche le choix de type de placement
    public static boolean show_placement() {
        System.out.println("\n");
        System.out.println("INDIQUEZ LE TYPE DE PLACEMENT SOUHAITE");
        System.out.println("0 Je ne joue pas ce tour ci");
        System.out.println("1 placement interieur");
        System.out.println("2 placement exterieur");
        return true;
    }

    //affiche les choix des placements interieurs
    public static boolean show_interieur() {
        System.out.println("\n");
        System.out.println("PRECISEZ DE PLACEMENT SOUHAITE");
        System.out.println("1 :En Plein"); // choix 1
        System.out.println("2 :A Cheval"); // choix 2
        System.out.println("3 :Transversale Pleine"); // choix 3
        System.out.println("4 :Triple"); // choix 4
        System.out.println("5 :En Carré"); // choix 5
        System.out.println("6 :Ligne du haut"); // choix 6
        System.out.println("7 :Sixain"); // choix 7
        return true;
    }

    //affiche les choix des placements exterieurs
    public static boolean show_exterieur() {
        System.out.println("\n");
        System.out.println("PRECISEZ DE PLACEMENT VOULU");
        System.out.println("1 :Colonne"); // choix 8
        System.out.println("2 :Douzaine"); // choix 9
        System.out.println("3 :Couleur"); // choix 10
        System.out.println("4 :Paire ou Impaire"); // choix 11
        System.out.println("5 :Manque ou Passe"); // choix 12
        return true;
    }

    //affiche les infos concernant le numero tiré
    //je profite de cette function pour recuperer la couleur du numero tiré et la parité dans un tableau
    //info[0]= 0=> vert, 1=> noire, 2=> rouge
    //info[1]= 0=> 0, 1=> paire, 2=> impaire
    public static int[] info_bille(int bille) {
        int[] info = {0, 0};
        //liste places rouges
        byte[] place_rouge = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        //liste places noires
        byte[] place_noir = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
        //affiche la couleur du numero
        for (int i = 0; i < place_rouge.length; i++) {
            if (bille == place_rouge[i]) {
                info[0] = 2;
                System.out.println("*    Couleur rouge    *");
            }
        }
        for (int i = 0; i < place_noir.length; i++) {
            if (bille == place_noir[i]) {
                info[0] = 1;
                System.out.println("*    Couleur noire    *");
            }
        }
        //affiche la douzaine du numero
        if (bille > 0 && bille < 13) {
            System.out.println("*  Première douzaine  *");
        }
        if (bille > 12 && bille < 25) {
            System.out.println("*  Seconde douzaine   *");
        }
        if (bille > 24 && bille < 37) {
            System.out.println("*  Toisième douzaine  *");
        }

        //Affiche la moitier du numero
        if (bille > 0 && bille < 19) {
            System.out.println("*  Première moitier   *");
        }
        if (bille > 18 && bille < 37) {
            System.out.println("*   Seconde moitier   *");
        }

        //Affiche la parité du numero (je pars du fait que 0 ne soit ni paire ni impaire) 
        if (bille % 2 == 0 && bille != 0) {
            System.out.println("*    Numero paire     *");
            info[1] = 1;
        }
        if (bille % 2 != 0 && bille != 0) {
            System.out.println("*   Numero impaire    *");
            info[1] = 2;
        }

        //Affiche la colonne du numero
        if (bille % 3 == 0 && bille != 0) {
            System.out.println("*     Colonne 3       *");
        }
        if ((bille + 1) % 3 == 0 && bille != 0) {
            System.out.println("*     Colonne 2       *");
        }
        if ((bille + 2) % 3 == 0 && bille != 0) {
            System.out.println("*     Colonne 1       *");
        }

        return info;
    }

    //tri à bulle croissant sur tableau jeu
    // jeu[0] renseigne le type de mise et jeu[1] renseigne le montant de la mise => ils ne sont donc pas concerné par le tri
    public static int[] bubble_sort_jeu(int[] jeu) {
        int taille = jeu.length;
        //flag pour verifier qu'une modification à été apportée au tableau
        boolean flag_tri = false;

        while (flag_tri == false) {
            flag_tri = true;
            for (int i = 2; i < taille - 1; i++) {
                if (jeu[i] > jeu[i + 1]) {
                    int tmp;
                    tmp = jeu[i];
                    jeu[i] = jeu[i + 1];
                    jeu[i + 1] = tmp;
                    flag_tri = false;
                }
            }
            //le dernier chiffre du tableau est placé, je peux reduire le tableau
            taille--;
        }

        return jeu;
    }

    //gère les mises sur "en plein"
    public static int[] en_plein(int jetons) {
        byte num = 0;
        byte num_tmp;
        int mise = 0;
        int mise_tmp;
        boolean flag = false;
        //tant que le choix du mumero n'est pas valide
        while (flag == false) {
            System.out.println("Choisissez un numero compris 0 et 36");
            num_tmp = Lire.b();
            if (num_tmp >= 0 && num_tmp <= 36) {
                num = num_tmp;
                flag = true;
            } else {
                System.out.println("Erreur: Numero incorrect");
            }
        }
        //réinitialise flag
        flag = false;
        while (flag == false) {
            System.out.println("Combien souhaitez vous miser?");
            mise_tmp = Lire.i();
            if (mise_tmp > jetons) {
                System.out.println("Erreur: Vous n'avez pas assez de jetons \n Il vous reste " + jetons + " jetons");
            } else if (mise_tmp <= 0) {
                System.out.println("Erreur: mise incorrect");
            } else if (mise_tmp <= jetons) {
                mise = mise_tmp;
                flag = true;
            }
        }
        int retour[] = {1, mise, num};

        return retour;
    }

    //verifie les paris et calcul des gains sur "en plein"
    public static int calc_en_plein(int bille, int[] jeu) {
        if (bille == jeu[2]) {
            int gain = jeu[1] * 36;
            System.out.println("Gain de " + gain + " avec un pari \"en plein\" d'une mise de " + jeu[1] + " sur n°" + jeu[2]);
            return gain;
        } else {
            System.out.println("Aucun gain avec le pari \"en plein\" d'une mise de " + jeu[1] + " sur n°" + jeu[2]);
            return 0;
        }
    }

    //gère les mises sur "a cheval"
    public static int[] a_cheval(int jetons) {
        byte num = 0;
        byte num_tmp;
        byte num_2 = 0;
        byte num_2_tmp;
        int mise = 0;
        int mise_tmp;
        //flag numero valide
        boolean flag = false;
        //tant que le choix du mumero n'est pas valide
        while (flag == false) {
            System.out.println("Choisissez deux numeros \"voisins\" compris 0 et 36");
            System.out.print("Premier numeros: ");
            num_tmp = Lire.b();
            System.out.print("Second numeros: ");
            num_2_tmp = Lire.b();
            //verifie la validité des 2 numeros saisis
            if (num_tmp >= 0 && num_tmp <= 36 && num_2_tmp >= 0 && num_2_tmp <= 36) {
                //si le 1er numero saisi est plus grand que le second
                if (num_tmp > num_2_tmp) {
                    //si numero le plus petit est different de 0
                    if (num_2_tmp != 0) {
                        //verifie que les numeros soit adjacents
                        // x+3 = y ou x+1= y 
                        //on exclu les facteurs de 3 de x pour x+1
                        if (num_2_tmp + 3 == num_tmp || (num_2_tmp + 1 == num_tmp && num_2_tmp % 3 != 0)) {
                            flag = true;
                            num = num_tmp;
                            num_2 = num_2_tmp;
                        } else {
                            System.out.println("Erreur: Vos 2 numeros doivent être adjacents");
                        }
                    } // si le nuemro le plus petit est égal à 0
                    else {
                        //on valide la saisi seulement si le numero le plus grand est egale à 2
                        if (num_tmp == 2) {
                            flag = true;
                            num = num_tmp;
                            num_2 = num_2_tmp;
                        } else {
                            System.out.println("Erreur: Vos 2 numeros doivent être adjacents");
                        }
                    }
                } //si le 1er numero saisi est plus petit que le second
                else if (num_tmp < num_2_tmp) {
                    //si numero le plus petit est different de 0
                    if (num_tmp != 0) {
                        if (num_tmp + 3 == num_2_tmp || (num_tmp + 1 == num_2_tmp && num_tmp % 3 != 0)) {
                            flag = true;
                            num = num_tmp;
                            num_2 = num_2_tmp;
                        } else {
                            System.out.println("Erreur: Vos 2 numeros doivent être adjacents");
                        }
                    } else {
                        //on valide la saisi seulement si le numero le plus grand est egale à 2
                        if (num_2_tmp == 2) {
                            flag = true;
                            num = num_tmp;
                            num_2 = num_2_tmp;
                        } else {
                            System.out.println("Erreur: Vos 2 numeros doivent être adjacents");
                        }
                    }
                } else {
                    System.out.println("Erreur: Vos 2 numeros doivent être adjacents");
                }

            } else {
                System.out.println("Erreur: Numero incorrect, choisisez des numeros compris entre 0 et 36");
            }
        }
        //réinitialise flag
        flag = false;
        while (flag == false) {
            System.out.println("Combien souhaitez vous miser?");
            mise_tmp = Lire.i();
            if (mise_tmp > jetons) {
                System.out.println("Erreur: Vous n'avez pas assez de jetons \n Il vous reste " + jetons + " jetons");
            } else if (mise_tmp <= 0) {
                System.out.println("Erreur: mise incorrect");
            } else if (mise_tmp <= jetons) {
                mise = mise_tmp;
                flag = true;
            }
        }
        int retour[] = {2, mise, num, num_2};
        return retour;
    }

    //verifie les paris et calcul des gains sur "a cheval"
    public static int calc_a_cheval(int bille, int[] jeu) {
        if (bille == jeu[2] || bille == jeu[3]) {
            int gain = jeu[1] * 18;
            System.out.println("Gain de " + gain + " avec un pari \"à cheval\" d'une mise de " + jeu[1] + " entre le n°" + jeu[2] + " et le n°" + jeu[3]);
            return gain;
        } else {
            System.out.println("Aucun gain avec le pari \"à cheval\" d'une mise de " + jeu[1] + " entre le n°" + jeu[2] + " et le n°" + jeu[3]);
            return 0;
        }
    }

    //gère les mises sur "transversale"
    public static int[] transversale(int jetons) {
        byte num = 0;
        byte num_tmp;
        int mise = 0;
        int mise_tmp;
        boolean flag = false;
        //tant que le choix du mumero n'est pas valide
        while (flag == false) {
            System.out.println("indiquez le numero le plus elevé de la ligne choisie");
            num_tmp = Lire.b();
            if (num_tmp >= 0 && num_tmp <= 36 && num_tmp % 3 == 0 && num_tmp != 0) {
                num = num_tmp;
                flag = true;
            } else {
                System.out.println("Erreur: Numero incorrect");
            }
        }
        //réinitialise flag
        flag = false;
        while (flag == false) {
            System.out.println("Combien souhaitez vous miser?");
            mise_tmp = Lire.i();
            if (mise_tmp > jetons) {
                System.out.println("Erreur: Vous n'avez pas assez de jetons \n Il vous reste " + jetons + " jetons");
            } else if (mise_tmp <= 0) {
                System.out.println("Erreur: mise incorrect");
            } else if (mise_tmp <= jetons) {
                mise = mise_tmp;
                flag = true;
            }
        }
        int retour[] = {3, mise, num};

        return retour;
    }

    //verifie les paris et calcul des gains sur "transversale"
    public static int calc_transversale(int bille, int[] jeu) {
        if (bille == jeu[2] || bille == jeu[2] - 1 || bille == jeu[2] - 2) {
            int gain = jeu[1] * 12;
            System.out.println("Gain de " + gain + " avec un pari \"Transversale pleine\" d'une mise de " + jeu[1] + " sur la ligne contenant le n°" + jeu[2]);
            return gain;
        } else {
            System.out.println("Aucun gain avec le pari \"Transversale pleine\" d'une mise de " + jeu[1] + " sur la ligne contenant le n°" + jeu[2]);
            return 0;
        }
    }

    //gère les mises sur "triple"
    public static int[] triple(int jetons) {
        byte num = 0;
        byte num_tmp;
        int mise = 0;
        int mise_tmp;
        boolean flag = false;
        //tant que le choix du mumero n'est pas valide
        while (flag == false) {
            System.out.println("indiquez le numero qui complete le placement 0, 2 et:");
            num_tmp = Lire.b();
            if (num_tmp == 3 || num_tmp == 1) {
                num = num_tmp;
                flag = true;
            } else {
                System.out.println("Erreur: Numero incorrect");
            }
        }
        //réinitialise flag
        flag = false;
        while (flag == false) {
            System.out.println("Combien souhaitez vous miser?");
            mise_tmp = Lire.i();
            if (mise_tmp > jetons) {
                System.out.println("Erreur: Vous n'avez pas assez de jetons \n Il vous reste " + jetons + " jetons");
            } else if (mise_tmp <= 0) {
                System.out.println("Erreur: mise incorrect");
            } else if (mise_tmp <= jetons) {
                mise = mise_tmp;
                flag = true;
            }
        }
        int retour[] = {4, mise, num};

        return retour;
    }

    //verifie les paris et calcul des gains sur "triple"
    public static int calc_triple(int bille, int[] jeu) {
        if (bille == jeu[2] || bille == 0 || bille == 2) {
            int gain = jeu[1] * 12;
            System.out.println("Gain de " + gain + " avec un pari \"triple\" d'une mise de " + jeu[1] + " sur les n°0, 2 et " + jeu[2]);
            return gain;
        } else {
            System.out.println("Aucun gain avec le pari \"triple\" d'une mise de " + jeu[1] + " sur les n°0, 2 et " + jeu[2]);
            return 0;
        }
    }

    //gère les mises sur "carre"
    public static int[] carre(int jetons) {
        byte num_1_tmp;
        byte num_2_tmp;
        byte num_3_tmp;
        byte num_4_tmp;

        int mise_tmp;

        int retour[] = new int[6];
        retour[0] = 5;
        boolean flag = false;
        //tant que le choix du mumero n'est pas valide
        while (flag == false) {
            System.out.println("indiquez les 4 numeros qui constitue votre placement en carré:");
            System.out.print("premier n°: ");
            retour[2] = Lire.b();
            System.out.print("second n°: ");
            retour[3] = Lire.b();
            System.out.print("troisieme n°: ");
            retour[4] = Lire.b();
            System.out.print("quatrieme n°: ");
            retour[5] = Lire.b();
           
            retour = bubble_sort_jeu(retour);

            //recupère les valeurs supposé des numeros voisins à retour[2] pour pouvoir les comparer au valeur saisies
            int retour_3 = retour[3] - 1;
            int retour_4 = retour[4] - 3;
            int retour_5 = retour[5] - 4;
            // on verifie que les extremes de n° choisis sont compris entre 1 et 36 et sont different de 0
            // de plus, on verifie que 4 numero choisis forme un carré sur le jeu : mise1 = mise2-1 = mise3-3 = mise 4 -4
            if (retour[2] != 0 && retour[2] > 0 && retour[2] < 37 && retour[5] > 0 && retour[5] < 37 && retour[2] == retour_3 && retour[2] == retour_4 && retour[2] == retour_5 && retour[2] % 3 != 0 && retour[4] % 3 != 0) {
                flag = true;
            } else {
                System.out.println("Erreur: Numero incorrect");
            }
        }

        //réinitialise flag
        flag = false;
        while (flag == false) {
            System.out.println("Combien souhaitez vous miser?");
            mise_tmp = Lire.i();
            if (mise_tmp > jetons) {
                System.out.println("Erreur: Vous n'avez pas assez de jetons \n Il vous reste " + jetons + " jetons");
            } else if (mise_tmp <= 0) {
                System.out.println("Erreur: mise incorrect");
            } else if (mise_tmp <= jetons) {
                retour[1] = mise_tmp;
                flag = true;
            }
        }

        return retour;
    }

    //verifie les paris et calcul des gains sur "carre"
    public static int calc_carre(int bille, int[] jeu) {
        if (bille == jeu[2] || bille == jeu[3] || bille == jeu[4] || bille == jeu[5]) {
            int gain = jeu[1] * 9;
            System.out.println("Gain de " + gain + " avec un pari \"carre\" d'une mise de " + jeu[1] + " sur les n°" + jeu[2] + ", " + jeu[3] + ", " + jeu[4] + ", " + jeu[5]);
            return gain;
        } else {
            System.out.println("Aucun gain avec le pari \"carre\" d'une mise de " + jeu[1] + " sur les n°" + jeu[2] + ", " + jeu[3] + ", " + jeu[4] + ", " + jeu[5]);
            return 0;
        }
    }

    //gère les mises sur "Ligne du haut"
    public static int[] ligne_haut(int jetons) {
        int mise = 0;
        int mise_tmp;
        boolean flag = false;
        //réinitialise flag
        while (flag == false) {
            System.out.println("Combien souhaitez vous miser?");
            mise_tmp = Lire.i();
            if (mise_tmp > jetons) {
                System.out.println("Erreur: Vous n'avez pas assez de jetons \n Il vous reste " + jetons + " jetons");
            } else if (mise_tmp <= 0) {
                System.out.println("Erreur: mise incorrect");
            } else if (mise_tmp <= jetons) {
                mise = mise_tmp;
                flag = true;
            }
        }
        int retour[] = {6, mise, 0, 1, 2, 3};

        return retour;
    }

    //verifie les paris et calcul des gains sur "Ligne du haut"
    public static int calc_ligne_haut(int bille, int[] jeu) {
        if (bille == 0 || bille == 1 || bille == 2 || bille == 3) {
            int gain = jeu[1] * 9;
            System.out.println("Gain de " + gain + " avec un pari \"Ligne du haut\" d'une mise de " + jeu[1]);
            return gain;
        } else {
            System.out.println("Aucun gain avec le pari \"Ligne du haut\" d'une mise de " + jeu[1]);
            return 0;
        }
    }

    //gère les mises sur "sixain"
    public static int[] sixain(int jetons) {
        byte num = 0;
        byte num_tmp;
        byte num_2 = 0;
        byte num_2_tmp;
        int mise = 0;
        int mise_tmp;
        boolean flag = false;
        //tant que le choix du mumero n'est pas valide
        while (flag == false) {
            System.out.println("indiquez le numero le plus elevé de la première ligne choisie");
            num_tmp = Lire.b();
            System.out.println("indiquez le numero le plus elevé d'un ligne adjacente");
            num_2_tmp = Lire.b();
            if (num_tmp > 0 && num_tmp <= 36 && num_2_tmp > 0 && num_2_tmp <= 36 && num_tmp % 3 == 0 && (num_tmp == num_2_tmp - 3 || num_tmp == num_2_tmp + 3)) {
                num = num_tmp;
                num_2 = num_2_tmp;
                flag = true;
            } else {
                System.out.println("Erreur: Numero incorrect");
            }
        }
        //réinitialise flag
        flag = false;
        while (flag == false) {
            System.out.println("Combien souhaitez vous miser?");
            mise_tmp = Lire.i();
            if (mise_tmp > jetons) {
                System.out.println("Erreur: Vous n'avez pas assez de jetons \n Il vous reste " + jetons + " jetons");
            } else if (mise_tmp <= 0) {
                System.out.println("Erreur: mise incorrect");
            } else if (mise_tmp <= jetons) {
                mise = mise_tmp;
                flag = true;
            }
        }
        int retour[] = {7, mise, num, num_2};

        return retour;
    }

    //verifie les paris et calcul des gains sur "sixain"
    public static int calc_sixain(int bille, int[] jeu) {

        if (bille == jeu[2] || bille == jeu[2] - 1 || bille == jeu[2] - 2 || bille == jeu[3] || bille == jeu[3] - 1 || bille == jeu[3] - 2) {
            int gain = jeu[1] * 6;
            System.out.println("Gain de " + gain + " avec un pari \"Sixain\" d'une mise de " + jeu[1] + " sur les lignes contenants les n°" + jeu[2] + ", " + (jeu[2] - 1) + ", " + (jeu[2] - 2) + " et " + (jeu[3]) + ", " + (jeu[3] - 1) + ", " + (jeu[3] - 2));
            return gain;
        } else {
            System.out.println("Aucun gain avec le pari \"Sixain\" d'une mise de " + jeu[1] + " sur les lignes contenants les n°" + jeu[2] + ", " + (jeu[2] - 1) + ", " + (jeu[2] - 2) + " et " + (jeu[3]) + ", " + (jeu[3] - 1) + ", " + (jeu[3] - 2));
            return 0;
        }
    }

    //gère les mises sur "colonnes"
    public static int[] colonnes(int jetons) {
        byte num = 0;
        byte num_tmp;
        int mise = 0;
        int mise_tmp;
        boolean flag = false;
        //tant que le choix du mumero n'est pas valide
        while (flag == false) {
            System.out.println("indiquez le premier numero de la colonne choisi: 1, 2 ou 3");
            num_tmp = Lire.b();
            if (num_tmp == 1 || num_tmp == 2 || num_tmp == 3) {
                num = num_tmp;
                flag = true;
            } else {
                System.out.println("Erreur: Numero incorrect");
            }
        }
        //réinitialise flag
        flag = false;
        while (flag == false) {
            System.out.println("Combien souhaitez vous miser?");
            mise_tmp = Lire.i();
            if (mise_tmp > jetons) {
                System.out.println("Erreur: Vous n'avez pas assez de jetons \n Il vous reste " + jetons + " jetons");
            } else if (mise_tmp <= 0) {
                System.out.println("Erreur: mise incorrect");
            } else if (mise_tmp <= jetons) {
                mise = mise_tmp;
                flag = true;
            }
        }
        int retour[] = {8, mise, num};

        return retour;
    }

    //verifie les paris et calcul des gains sur "colonnes"
    public static int calc_colonnes(int bille, int[] jeu) {
        int nbr_jeu = jeu[2];
        boolean flag_win = false;
        for (int i = 0; i < 12; i++) {
            if (bille == nbr_jeu) {
                flag_win = true;
            }
            nbr_jeu += 3;
        }
        if (flag_win == true) {
            int gain = jeu[1] * 3;
            System.out.println("Gain de " + gain + " avec un pari \"Colonnes\" d'une mise de " + jeu[1] + " sur la colonne commencant par le n°" + jeu[2]);
            return gain;
        } else {
            System.out.println("Aucun gain avec le pari \"Colonnes\" d'une mise de " + jeu[1] + " sur la colonne commencant par le n°" + jeu[2]);
            return 0;
        }
    }

    //gère les mises sur "douzaines"
    public static int[] douzaines(int jetons) {
        byte num = 0;
        byte num_tmp;
        int mise = 0;
        int mise_tmp;
        boolean flag = false;
        //tant que le choix du mumero n'est pas valide
        while (flag == false) {
            System.out.println("indiquez le premier la douzaine de votre choix: 1, 2 ou 3");
            num_tmp = Lire.b();
            if (num_tmp > 0 && num_tmp < 4) {
                num = num_tmp;
                flag = true;
            } else {
                System.out.println("Erreur: Numero incorrect");
            }

        }
        //réinitialise flag
        flag = false;
        while (flag == false) {
            System.out.println("Combien souhaitez vous miser?");
            mise_tmp = Lire.i();
            if (mise_tmp > jetons) {
                System.out.println("Erreur: Vous n'avez pas assez de jetons \n Il vous reste " + jetons + " jetons");
            } else if (mise_tmp <= 0) {
                System.out.println("Erreur: mise incorrect");
            } else if (mise_tmp <= jetons) {
                mise = mise_tmp;
                flag = true;
            }
        }
        int retour[] = {9, mise, num};

        return retour;
    }

    //verifie les paris et calcul des gains sur "douzaines"
    public static int calc_douzaines(int bille, int[] jeu) {
        int jeton_init;
        boolean flag_win = false;

        switch (jeu[2]) {
            case 1:
                jeton_init = 1;
                break;
            case 2:
                jeton_init = 13;
                break;
            case 3:
                jeton_init = 25;
                break;
            default:
                jeton_init = 100;
        }

        for (int i = 0; i < 12; i++) {
            if (bille == jeton_init) {
                flag_win = true;
            }
            jeton_init++;
        }
        if (flag_win == true) {
            int gain = jeu[1] * 3;
            System.out.println("Gain de " + gain + " avec un pari \"Douzaine\" d'une mise de " + jeu[1] + " sur la douzaine n°" + jeu[2]);
            return gain;
        } else {
            System.out.println("Aucun gain avec le pari \"Douzaine\" d'une mise de " + jeu[1] + " sur la douzaine n°" + jeu[2]);
            return 0;
        }
    }

    //gère les mises sur "Rouge ou Noir"
    public static int[] rouge_noir(int jetons) {
        int num = 0;
        char num_tmp;
        int mise = 0;
        int mise_tmp;
        boolean flag = false;
        //tant que le choix de la couleur n'est pas valide
        while (flag == false) {
            System.out.println("indiquez la couleur choisie: r ou n");
            num_tmp = Lire.c();
            if (num_tmp == 'r' || num_tmp == 'n') {
                switch (num_tmp) {
                    case 'n':
                        num = 1;
                        flag = true;
                        break;
                    case 'r':
                        num = 2;
                        flag = true;
                        break;
                }

            } else {
                System.out.println("Erreur: saisi incorrect, indiquer le premier charactère de la couleur: r ou n");
            }
        }
        //réinitialise flag
        flag = false;
        while (flag == false) {
            System.out.println("Combien souhaitez vous miser?");
            mise_tmp = Lire.i();
            if (mise_tmp > jetons) {
                System.out.println("Erreur: Vous n'avez pas assez de jetons \n Il vous reste " + jetons + " jetons");
            } else if (mise_tmp <= 0) {
                System.out.println("Erreur: mise incorrect");
            } else if (mise_tmp <= jetons) {
                mise = mise_tmp;
                flag = true;
            }
        }
        int retour[] = {10, mise, num};

        return retour;
    }

    //verifie les paris et calcul des gains sur "Rouge ou Noir"
    public static int calc_rouge_noir(int bille, int[] jeu, int info) {

        if (jeu[2] == info) {
            int gain = jeu[1] * 2;
            if (jeu[2] == 1) {
                System.out.println("Gain de " + gain + " avec un pari \"Couleur\" d'une mise de " + jeu[1] + " sur le noir");
                return gain;
            } else {
                System.out.println("Gain de " + gain + " avec un pari \"Couleur\" d'une mise de " + jeu[1] + " sur le rouge");
                return gain;
            }

        } else {
            if (jeu[2] == 1) {
                System.out.println("Aucun gain avec le pari \"Couleur\" d'une mise de " + jeu[1] + " sur le noir");
                return 0;
            } else {
                System.out.println("Aucun gain avec le pari \"Couleur\" d'une mise de " + jeu[1] + " sur le rouge");
                return 0;
            }
        }
    }

    //gère les mises sur "parité"
    public static int[] parite(int jetons) {
        int num = 0;
        char num_tmp;
        int mise = 0;
        int mise_tmp;
        boolean flag = false;
        //tant que le choix de la parité n'est pas valide
        while (flag == false) {
            System.out.println("indiquez la parité choisie: p ou i");
            num_tmp = Lire.c();
            if (num_tmp == 'p' || num_tmp == 'i') {
                switch (num_tmp) {
                    case 'p':
                        num = 1;
                        flag = true;
                        break;
                    case 'i':
                        num = 2;
                        flag = true;
                        break;
                }

            } else {
                System.out.println("Erreur: saisi incorrect, indiquer \"p\" pour paire ou \"i\" pour impaire");
            }
        }
        //réinitialise flag
        flag = false;
        while (flag == false) {
            System.out.println("Combien souhaitez vous miser?");
            mise_tmp = Lire.i();
            if (mise_tmp > jetons) {
                System.out.println("Erreur: Vous n'avez pas assez de jetons \n Il vous reste " + jetons + " jetons");
            } else if (mise_tmp <= 0) {
                System.out.println("Erreur: mise incorrect");
            } else if (mise_tmp <= jetons) {
                mise = mise_tmp;
                flag = true;
            }
        }
        int retour[] = {11, mise, num};

        return retour;
    }

    //verifie les paris et calcul des gains sur "parité"
    public static int calc_parite(int bille, int[] jeu, int info) {

        int parite = jeu[2];
        // parite = 1 => paire
        // parite = 2 => impaire
        if (parite == info) {
            int gain = jeu[1] * 2;
            if (parite == 1) {
                System.out.println("Gain de " + gain + " avec un pari \"Paire / Impaire\" d'une mise de " + jeu[1] + " sur le paire");
                return gain;
            } else {
                System.out.println("Gain de " + gain + " avec un pari \"Paire / Impaire\" d'une mise de " + jeu[1] + " sur l'impaire");
                return gain;
            }

        } else {
            if (parite == 1) {
                System.out.println("Aucun gain avec le pari \"Paire / Impaire\" d'une mise de " + jeu[1] + " sur le paire");
                return 0;
            } else {
                System.out.println("Aucun gain avec le pari \"Paire / Impaire\" d'une mise de " + jeu[1] + " sur l'impaire");
                return 0;
            }
        }
    }

    //gère les mises sur "moitier"
    public static int[] moitier(int jetons) {
        int num = 0;
        char num_tmp;
        int mise = 0;
        int mise_tmp;
        boolean flag = false;
        //tant que le choix de la parité n'est pas valide
        while (flag == false) {
            System.out.println("indiquez la moitier choisie (marque ou passe): m ou p");
            num_tmp = Lire.c();
            if (num_tmp == 'm' || num_tmp == 'p') {
                switch (num_tmp) {
                    case 'm':
                        num = 1;
                        flag = true;
                        break;
                    case 'p':
                        num = 2;
                        flag = true;
                        break;
                }

            } else {
                System.out.println("Erreur: saisi incorrect, indiquer \"m\" pour marque ou \"p\" pour passe");
            }
        }
        //réinitialise flag
        flag = false;
        while (flag == false) {
            System.out.println("Combien souhaitez vous miser?");
            mise_tmp = Lire.i();
            if (mise_tmp > jetons) {
                System.out.println("Erreur: Vous n'avez pas assez de jetons \n Il vous reste " + jetons + " jetons");
            } else if (mise_tmp <= 0) {
                System.out.println("Erreur: mise incorrect");
            } else if (mise_tmp <= jetons) {
                mise = mise_tmp;
                flag = true;
            }
        }
        int retour[] = {12, mise, num};

        return retour;
    }

    //verifie les paris et calcul des gains sur "moitier"
    public static int calc_moitier(int bille, int[] jeu) {
        int gain = 0;

        if (bille > 0 && bille < 19) {
            if (jeu[2] == 1) {
                gain = jeu[1] * 2;
                System.out.println("Gain de " + gain + " avec un pari \"Manque / Passe\" d'une mise de " + jeu[1] + " sur manque");
                return gain;
            } else {
                System.out.println("Aucun gain avec le pari \"Manque / Passe\" d'une mise de " + jeu[1] + " sur passe");
                return gain;
            }
        }
        if (bille > 18 && bille < 37) {
            if (jeu[2] == 2) {
                gain = jeu[1] * 2;
                System.out.println("Gain de " + gain + " avec un pari \"Manque / Passe\" d'une mise de " + jeu[1] + " sur passe");
                return gain;
            } else {
                System.out.println("Aucun gain avec le pari \"Manque / Passe\" d'une mise de " + jeu[1] + " sur manque");
                return gain;
            }
        }
        return gain;
    }
}
