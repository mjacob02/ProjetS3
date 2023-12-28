INSERT INTO "GAMME" (ID, REFERENCE, DESCRIPTION) VALUES
(1,'Gamme 1', 'Description 1'),
(2,'Gamme 2', 'Description 2'),
(3,'Gamme 3', 'Description 3');

INSERT INTO "PRODUIT" (ID, IDGAMME, REFERENCE, DESCRIPTION) VALUES
(1,1, 'Produit 1', 'Description 1'),
(2,1, 'Produit 2', 'Description 2'),
(3,1, 'Produit 3', 'Description 3'),
(4,2, 'Produit 4', 'Description 4'),
(5,2, 'Produit 5', 'Description 5'),
(6,2, 'Produit 6', 'Description 6');