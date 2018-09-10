INSERT INTO Compte(numero,label,solde) VALUES (1,'compte 1',50.0);
INSERT INTO Compte(numero,label,solde) VALUES (2,'compte 2',80.0);

INSERT INTO Operation(numOp,label,montant,dateOp,compte) VALUES (1,'achat xy',-5,'2018-09-07',1);
INSERT INTO Operation(numOp,label,montant,dateOp,compte) VALUES (2,'achat zzz',-8,'2018-09-01',1);
INSERT INTO Operation(numOp,label,montant,dateOp,compte) VALUES (3,'achat aaaa',-9,'2018-09-05',2);
INSERT INTO Operation(numOp,label,montant,dateOp,compte) VALUES (4,'achat bbbb',-18,'2018-09-02',2);
