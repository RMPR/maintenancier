go :- hypothesis(Probleme), sendSolution(Probleme), nl, undo.

sendSolution(Solution) :- term_to_atom(Solution, S),
		   jpl_call('sma.engine.Engine', handle, [S], _).

/*Hypoth�ses � tester */
hypothesis("changer de chargeur") :- chargeur, !.
hypothesis("changer le circuit d'alimentation") :- alimentation, !.
hypothesis("bien connecter le bouton d'allumage ou le changer") :- boutondallumage, !.
hypothesis("bien connecter le processeur et le ventilateur, les changer si le probl�me persiste") :- processeur, !.
hypothesis("nettoyer le ventilateur et si cela ne fonctionne pas en changer") :- ventilateur, !.
hypothesis("bien r�gler la luminosit� de l'�cran") :- luminosite, !.
hypothesis("changer l'�cran") :- ecran, !.
hypothesis("r�chauffer la carte graphique, la changer si le probl�me persiste") :- cartegraphique, !.
hypothesis("refaire le syst�me d'exploitation, si le probl�me persiste changer de disque dur") :- disquedur, !.
hypothesis("placer correctement le disque dur") :- disquedurmalplace, !.
hypothesis("bien placer les barrettes RAM, les changer si le probl�me persiste") :- ram, !.
hypothesis("nettoyer les barrettes RAM, les changer si le probl�me persiste") :- ram2, !.
hypothesis("v�rifier la nappe de connexion du clavier, ou alors utiliser un clavier externe") :- clavier, !.
hypothesis(".....nous ne parvenons pas � d�tecter votre probl�me"). /*Pas de diagnostic*/

/*R�gles de d�pannage*/
ventilateur :- verify("s'allume"), verify("s'arr�te juste apr�s").
chargeur :- not(verify("s'allume")), verify("s'allume apr�s avoir chang� de chargeur").
processeur :- not(verify("s'allume")), verify("�met 5 bips longs").
luminosite :- verify("s'allume"), verify("a l'�cran tout noir ou qui pr�sente des taches"), verify("donne des images apr�s avoir bien r�gl� la luminosit�").
ecran :- verify("s'allume"), not(verify("s'arr�te juste apr�s")), verify("a l'�cran tout noir ou qui pr�sente des taches"), not(verify("donne des images apr�s avoir bien r�gl� la luminosit�")), verify("donne des images sur un moniteur externe").
ram :- verify("s'allume"), verify("�met 1 bip long suivi d'1 bip court ou alors des bips longs uniquement").
cartegraphique:- (verify("s'allume"), verify("a l'�cran tout noir ou qui pr�sente des taches"), not(verify("donne des images sur un moniteur externe")), not(verify("donne des images claires apr�s avoir nettoy� ou changer les RAM"))) ; (verify("s'allume") , verify("�met 1 bip long suivi de 2 bips courts")).
ram2 :- verify("s'allume"), verify("a l'�cran tout noir ou qui pr�sente des taches"), not(verify("donne des images sur un moniteur externe")), verify("donne des images claires apr�s avoir nettoy� ou changer les RAM").
disquedur :- verify("s'allume"), not(verify("finit le chargement du syst�me d'exploitation(ne tourne pas ind�finiment)")).
disquedurmalplace :- verify("s'allume"), verify("affiche le message d'erreur boot device not found").
clavier :- verify("s'allume"), not(verify("affiche les touches que nous saisissons au clavier")).
alimentation :- not(verify("s'allume")), not(verify("s'allume apr�s avoir bien connect� le bouton d'allumage ou apr�s l'avoir chang�")).
boutondallumage :- not(verify("s'allume")).

/*Comment poser les questions*/
ask(Question) :- term_to_atom(Question, Q),
		   jpl_call('sma.engine.Engine', handle, [Q], _),
           jpl_get('java.lang.System', 'in', In),
           jpl_call('sma.engine.Engine', readT, [], K),
           (  (K==oui ; K==o)
             ->  assert(oui(Question));
			 (K==cancel)
             ->  assert(non(_));
             assert(non(Question)), fail).

:- dynamic oui/1, non/1.

/*Comment v�rifier*/
verify(S) :- (oui(S) -> true ; (non(S) -> fail ; ask(S))).

/* undo all oui/no assertions */
undo :- retract(oui(_)),fail.
undo :- retract(non(_)),fail.
undo.