##xGRAPH 1.0 Final
Fertigstellung am *17.3.2013*

Berechnung und Darstellung von Teilbereichen der Graphentheorie.

###Programminhalt:

* Adjazenzmatrix
* Graphische Darstellung
* Wegmatrix
* Distanzmatrix
* Exzentrizitäten
* Radius
* Durchmesser
* Zentrum
* Komponenten
* Artikulationen
* Brücken
* Bäume
* Wald

**Adjazenzmatrix:** Matrix zur Eingabe von ungerichteten Kanten, welche zur weiteren Berechnung herangezogen werden. Die Diagonale ist auf 0 gesetzt und somit sind Knoten mit Schlingen nicht möglich.

**Graphische Darstellung:** Mittels der JUNG2-Java-Bibliothek wird der in der Adjazenzmatrix eingegebene Graph visualisiert.

**Wegmatrix:** Enthält als Werte nur 0 oder 1 und besagt, ob es einen Weg von einem beliebigen Knoten x zu einem beliebigen Knoten y gibt.

**Distanzmatrix:** Liefert die Information darüber, in wievielen Schritten ein beliebiger Knoten x zu einem beliebigen Knoten y erreicht werden kann.

**Exzentrizitäten:** Ist der jeweils größte Wert eines Knoten in der Distanzmatrix und besagt, in wievielen Schritten man vom jeweiligen Knoten maximal braucht um zu einem anderen Knoten zu gelangen.

**Radius:** Ist die kleinste Exzentrizität.

**Durchmesser:** Ist die größte Exzentrizität.

**Zentrum:** Sind jene Knoten, welche die kleinste Exzentrizität aufweisen.

**Komponenten:** Eine Komponente ist ein Teilgraph. Diese werden mit Hilfe der Wegmatrix berechnet.

**Artikulationen:** Sind Knoten, wo bei Wegnahme eines solchen Knotens der Graph in mehr Komponenten zerfällt als er vorher Komponenten hatte.

**Brücken:** Sind Kanten, wo bei Wegnahme einer solchen Kante der Graph in mehr Komponenten zerfällt als er vorher Komponenten hatte.

**Bäume:** Ein Graph ist dann ein Baum wenn er um einen Knoten mehr als Kanten hat und der Graph zusammenhängend ist.

**Wald:** Ein Graph ist dann ein Wald, wenn er nicht zusammenhängend ist und die Anzahl der Kanten gleich der Anzahl der Knoten minus der Anzahl der Komponenten ist.