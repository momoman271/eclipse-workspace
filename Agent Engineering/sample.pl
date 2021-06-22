male(namihei).
male(masuo).
male(katsuo).
male(tara).
female(fune).
female(sazae).
female(wakame).

child(sazae, namihei).
child(katsuo, namihei).
child(wakame, namihei).
child(tara, masuo).

married(fune, namihei).
married(sazae, masuo).

wife(W,H) :- married(W,H), female(W).
hasband(H,W) :- married(H,W), male(H).
father(F,C) :- child(C,F), male(F).
mother(M,C) :- father(F,C), married(M,F).