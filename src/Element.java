public class Element {
    Element parent = null;
    Element left = null;
    Element right = null;
    Element center = null;
    Integer prise;
    Integer coeficientOfTaken = 0;
    Boolean isVisited =false;

    Element(Integer p){
        prise = p;
    }
}
