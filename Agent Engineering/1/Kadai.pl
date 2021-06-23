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
parent(X,Y) :- father(X,Y); mother(X,Y).
brother(X,Y) :- child(X,F), child(Y,F), male(X).
sister(X,Y) :- child(X,F), child(Y,F), female(X).
aunt(X,Y) :- parent(P,Y), sister(X,P); parent(P,Y), brother(B,P), wife(X,B).
uncle(X,Y) :- parent(P,Y), brother(X,P); parent(P,Y), sister(B,P), hasband(X,B).
nephew(X,Y) :- uncle(Y,X).
grandmother(X,Y) :- parent(P,Y), mother(X,P).
grandfather(X,Y) :- parent(P,Y), father(X,P).
grandchild(X,Y) :- grandfather(Y,X); grandmother(Y,X).