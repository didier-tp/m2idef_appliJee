INSERT INTO Compte(numero,label,solde,typeCompte) VALUES (1,'compte 1',50.0,'CompteCourant');
INSERT INTO Compte(numero,label,solde,typeCompte,tauxInteret) VALUES (2,'compte 2',80.0,'ComptePEL',1.1);
INSERT INTO Compte(numero,label,solde,typeCompte) VALUES (3,'compte 3',150.0,'CompteCourant');
INSERT INTO Compte(numero,label,solde,typeCompte,tauxInteret) VALUES (4,'compte 4',180.0,'ComptePEL',1.0);

INSERT INTO Operation(numOp,label,montant,dateOp,compte) VALUES (1,'achat xy',-5,'2018-09-07',1);
INSERT INTO Operation(numOp,label,montant,dateOp,compte) VALUES (2,'achat zzz',-8,'2018-09-01',1);
INSERT INTO Operation(numOp,label,montant,dateOp,compte) VALUES (3,'achat aaaa',-9,'2018-09-05',3);
INSERT INTO Operation(numOp,label,montant,dateOp,compte) VALUES (4,'achat bbbb',-18,'2018-09-02',3);

INSERT INTO Client(numero,prenom,nom) VALUES (1,'alain','Therieur');
INSERT INTO Client(numero,prenom,nom) VALUES (2,'alex','Therieur');

INSERT INTO Client_Compte(client_id,compte_id) VALUES(1,1);
INSERT INTO Client_Compte(client_id,compte_id) VALUES(1,2);
INSERT INTO Client_Compte(client_id,compte_id) VALUES(2,3);
INSERT INTO Client_Compte(client_id,compte_id) VALUES(2,4);