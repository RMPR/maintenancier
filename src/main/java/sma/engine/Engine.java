/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.engine;

import com.github.cschen1205.ess.engine.Clause;
import com.github.cschen1205.ess.engine.EqualsClause;
import com.github.cschen1205.ess.engine.KieRuleInferenceEngine;
import com.github.cschen1205.ess.engine.Rule;
import com.github.cschen1205.ess.engine.RuleInferenceEngine;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import sma.main.controllers.MainController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rufus
 */
public class Engine {

	public String getChargeur() {
		return chargeur;
	}

	public String getAlimentation() {
		return alimentation;
	}

	public String getBouton_allumage() {
		return bouton_allumage;
	}

	public String getProcesseur() {
		return processeur;
	}

	public String getVentilateur() {
		return ventilateur;
	}

	public String getLuminonsite() {
		return luminonsite;
	}

	public String getEcran() {
		return ecran;
	}

	public String getCarte_graphique() {
		return carte_graphique;
	}

	public String getDisque_dur() {
		return disque_dur;
	}

	public String getDisque_dur_mal_place() {
		return disque_dur_mal_place;
	}

	public String getRam() {
		return ram;
	}

	public String getRam2() {
		return ram2;
	}

	public String getClavier() {
		return clavier;
	}

	public String getEchec() {
		return echec;
	}

	public String getOui() {
		return oui;
	}

	public String getNonn() {
		return non;
	}

	public String getQ_allume() {
		return q_allume;
	}

	public String getQ_arret() {
		return q_arret;
	}

	public String getQ_allume2() {
		return q_allume2;
	}

	public String getQ_bips() {
		return q_bips;
	}

	public String getQ_bips2() {
		return q_bips2;
	}

	public String getQ_ecran() {
		return q_ecran;
	}

	public String getQ_ecran2() {
		return q_ecran2;
	}

	public String getQ_ecran3() {
		return q_ecran3;
	}

	public String getQ_carte_graphique() {
		return q_carte_graphique;
	}

	public String getQ_ram() {
		return q_ram;
	}

	public String getQ_disque_dur() {
		return q_disque_dur;
	}

	public String getQ_disque_dur2() {
		return q_disque_dur2;
	}

	public String getQ_clavier() {
		return q_clavier;
	}

	public String getQ_allume3() {
		return q_allume3;
	}

	public int getNbre_max_questions() {
		return nbre_max_questions;
	}
	private String random;
	private Clause conclusion;
	private RuleInferenceEngine rie;
	private final String chargeur = "Remplacer le chargeur";
	private final String alimentation = "Remplacer le boitier d'alimentation";
	private final String bouton_allumage = "Vérifier la nappe d'alimentation";
	private final String processeur = "Vérifier le fonctionnement du ventirad et remplacement de pâte thermique";
	private final String ventilateur = "Dépoussiérer le ventilateur ou le changer";
	private final String luminonsite = "Augmenter la luminonsité de l'écran au maximum";
	private final String ecran = "Remplacer l'écran";
	private final String carte_graphique = "Dépoussiérer le système de refroidissement de la carte graphique ou le changer";
	private final String disque_dur = "Lancer une vérification de disque, si échec, changer de disque dur";
	private final String disque_dur_mal_place = "Déconnecter puis reconnecter le disque dur";
	private final String ram = "Déconnecter puis reconnecter les barrettes mémoire";
	private final String ram2 = "Dépoussiérer les barrettes mémoire régulièrement ou les changer";
	private final String clavier = "Essayer un clavier externe, s'il fonctionne, remplacer le clavier";
	private final String echec = "Nous ne parvenonns pas à détecter le problème, il faut enrichir la base";

	private final String oui = "oui";
	private final String non = "non";

	/* Questions */
	private final String q_allume = "s'allume";
	private final String q_arret = "s'arrête juste après";
	private final String q_allume2 = "s'allume après avoir changé de chargeur";
	private final String q_bips = "émet des bips longs";
	private final String q_bips2 = "émet des bips courts";
	private final String q_ecran = "a l'écran tout nonir";
	private final String q_ecran2 = "donne des images sur un moniteur externe";
	private final String q_ecran3 = "parvient à afficher mais faiblement";
	private final String q_carte_graphique = "donne des images claires après avoir nettoyé les RAM";
	private final String q_ram = "le voyant clignonte fixe";
	private final String q_disque_dur = "démarre le système d'exploitation";
	private final String q_disque_dur2 = "affiche \"Boot Device Not Found\"";
	private final String q_clavier = "affiche les caractères saisis au clavier";
	private final String q_allume3 = "aucune courant ne circule, même lorsque l'ordinateur est sous tension";

	private final int nbre_max_questions = 3;
	List<String> questions = new ArrayList<>();

	private RuleInferenceEngine getInferenceEngine() {
		RuleInferenceEngine rie = new KieRuleInferenceEngine();

		Rule rule = new Rule(chargeur);
		rule.addAntecedent(new EqualsClause(q_allume, oui));
		rule.addAntecedent(new EqualsClause(q_arret, oui));
		rule.setConsequent(new EqualsClause("probleme", chargeur));
		rie.addRule(rule);

		rule = new Rule(alimentation);
		rule.addAntecedent(new EqualsClause(q_allume, oui));
		rule.addAntecedent(new EqualsClause(q_allume2, oui));
		rule.setConsequent(new EqualsClause("probleme", alimentation));
		rie.addRule(rule);

		rule = new Rule(carte_graphique);
		rule.addAntecedent(new EqualsClause(q_allume, oui));
		rule.addAntecedent(new EqualsClause(q_ecran2, oui));
		rule.setConsequent(new EqualsClause("probleme", carte_graphique));
		rie.addRule(rule);

		rule = new Rule(clavier);
		rule.addAntecedent(new EqualsClause(q_allume, oui));
		rule.addAntecedent(new EqualsClause(q_clavier, oui));
		rule.setConsequent(new EqualsClause("probleme", clavier));
		rie.addRule(rule);

		rule = new Rule(disque_dur);
		rule.addAntecedent(new EqualsClause(q_allume, oui));
		rule.addAntecedent(new EqualsClause(q_disque_dur, non));
		rule.setConsequent(new EqualsClause("probleme", disque_dur));
		rie.addRule(rule);

		rule = new Rule(disque_dur_mal_place);
		rule.addAntecedent(new EqualsClause(q_allume, oui));
		rule.addAntecedent(new EqualsClause(q_disque_dur2, oui));
		rule.setConsequent(new EqualsClause("probleme", disque_dur_mal_place));
		rie.addRule(rule);

		rule = new Rule(ecran);
		rule.addAntecedent(new EqualsClause(q_allume, oui));
		rule.addAntecedent(new EqualsClause(q_ecran, oui));
		rule.setConsequent(new EqualsClause("probleme", alimentation));
		rie.addRule(rule);

		rule = new Rule(luminonsite);
		rule.addAntecedent(new EqualsClause(q_allume, oui));
		rule.addAntecedent(new EqualsClause(q_ecran3, oui));
		rule.setConsequent(new EqualsClause("probleme", luminonsite));
		rie.addRule(rule);
		return rie;
	}

	public void getResponse(String reponse) {
		rie.addFact(new EqualsClause(random, oui));
		Vector<Clause> unproved_conditions = new Vector<>();
		conclusion = rie.infer("probleme", unproved_conditions);
		if (conclusion != null) {

		} else {
			Random randomizer = new Random();
			random = questions.get(randomizer.nextInt(questions.size()));
		}


	}

	public String sendResponse() {
		return random;
	}

	public Engine() {
		rie = getInferenceEngine();
		
		questions.add(q_allume2);
		questions.add(q_allume3);
		questions.add(q_arret);
		questions.add(q_bips);
		questions.add(q_bips2);
		questions.add(q_carte_graphique);
		questions.add(q_clavier);
		questions.add(q_disque_dur);
		questions.add(q_disque_dur2);
		questions.add(q_ecran);
		questions.add(q_ecran2);
		questions.add(q_ecran3);
		questions.add(q_ram);
		Random randomizer = new Random();
		random = questions.get(randomizer.nextInt(questions.size()));
	}

	public RuleInferenceEngine chainageArriere() {
		rie.addFact(new EqualsClause(q_allume, oui));

		Vector<Clause> unproved_conditions = new Vector<>();

		return rie;
	}

	public String getRandom() {
		return random;
	}
}
