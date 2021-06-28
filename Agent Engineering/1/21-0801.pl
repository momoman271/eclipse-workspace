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
brother(X,Y) :- parent(P,X), parent(P,Y), male(X), (X)\=(Y).
sister(X,Y) :- parent(P,X), parent(P,Y), female(X), (X)\=(Y).
aunt(X,Y) :- parent(P,Y), sister(X,P); parent(P,Y), brother(B,P), wife(X,B).
uncle(X,Y) :- parent(P,Y), brother(X,P); parent(P,Y), sister(B,P), hasband(X,B).
siblings(X,Y) :- brother(X,Y); sister(X,Y).
nephew(X,Y) :- siblings(S,Y), parent(S,X), male(X).
grandmother(X,Y) :- parent(P,Y), mother(X,P).
grandfather(X,Y) :- parent(P,Y), father(X,P).
grandchild(X,Y) :- grandfather(Y,X); grandmother(Y,X).