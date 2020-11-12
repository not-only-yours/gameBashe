import java.util.ArrayList;
import java.util.Stack;

public class Tree {
    public static Element mainElement = new Element(15);
    Element curentElement = mainElement;

    public static void createTree(Integer pr,Element q){
        if(pr>3){
            q.left = new Element(pr-1);
            q.center = new Element(pr-2);
            q.right = new Element(pr-3);
            q.left.parent = q;
            q.center.parent = q;
            q.right.parent = q;
            //if(q.parent!=null)
                //System.out.println(q.prise+" " +q.left.prise+" "+q.center.prise+" "+q.right.prise+" "+q.parent.prise);
            createTree(pr-1,q.left);
            createTree(pr-2,q.center);
            createTree(pr-3,q.right);
        }else if(pr==3){
            q.left = new Element(2);
            q.center = new Element(1);
            q.left.left = new Element(1);
            q.left.parent = q;
            q.left.left.parent = q.left;
            q.center.parent = q;
            //System.out.println(q.left.prise+" "+q.center.prise);
        }else if(pr==2){
            q.left = new Element(1);
            q.left.parent = q;
        }
    }
    public static ArrayList<Element> finder = new ArrayList<>();

    public static void createTreeAlphaBeta(Integer pr,Element q){
        Boolean isAdded = false;
        if(!finder.isEmpty())
        for(Element a: finder) {
            if(a.prise.equals(pr)){
                isAdded = true;
            }
        }
        if(!isAdded || finder.isEmpty()){
            finder.add(q);
        }

        if(pr>3){
            q.left = new Element(pr-1);
            q.center = new Element(pr-2);
            q.right = new Element(pr-3);
            q.left.parent = q;
            q.center.parent = q;
            q.right.parent = q;
            //if(q.parent!=null)
            //System.out.println(q.prise+" " +q.left.prise+" "+q.center.prise+" "+q.right.prise+" "+q.parent.prise);

            createTreeAlphaBeta(pr-1,q.left);

            createTreeAlphaBeta(pr-2,q.center);

            createTreeAlphaBeta(pr-3,q.right);

        }else if(pr==3){
            q.left = new Element(2);
            q.center = new Element(1);
            q.left.left = new Element(1);
            q.left.parent = q;
            q.left.left.parent = q.left;
            q.center.parent = q;
            //System.out.println(q.left.prise+" "+q.center.prise);
        }else if(pr==2){
            q.left = new Element(1);
            q.left.parent = q;
        }
    }


public static void dfs(Element input,Boolean Layer){
    Stack<Element> stack= new Stack<>();
    stack.add(input);
    Boolean isAdded;
    Boolean addLayer = Layer;
    do{
        isAdded = false;
            if (stack.peek().left != null || stack.peek().center != null || stack.peek().right != null) {
                if (stack.peek().left.prise == 1 || stack.peek().center.prise == 1 || stack.peek().right.prise == 1) {
                    Boolean changinLayer = false;
                    if (stack.peek().left != null && stack.peek().left.prise == 1) {
                        if (stack.peek().left.prise == 1 && !stack.peek().left.isVisited) {
                            stack.peek().left.isVisited = true;
                            if (addLayer) {
                                stack.peek().coeficientOfTaken += 1;
                            } else {
                                stack.peek().coeficientOfTaken -= 1;
                            }
                        }
                    } else if (stack.peek().center != null && stack.peek().center.prise == 1) {
                        if (stack.peek().center.prise == 1 && !stack.peek().center.isVisited) {
                            stack.peek().center.isVisited = true;
                            if (addLayer) {
                                stack.peek().coeficientOfTaken += 1;
                            } else {
                                stack.peek().coeficientOfTaken -= 1;
                            }
                        }
                    } else if (stack.peek().right != null && stack.peek().right.prise == 1) {
                        if (stack.peek().right.prise == 1 && !stack.peek().right.isVisited) {
                            stack.peek().right.isVisited = true;
                            if (addLayer) {
                                stack.peek().coeficientOfTaken += 1;
                            } else {
                                stack.peek().coeficientOfTaken -= 1;
                            }
                        }
                    }
                }


                if (!stack.peek().left.isVisited && stack.peek().left.prise != 1 && !isAdded) {
                    stack.add(stack.peek().left);
                    if (addLayer) {
                        addLayer = false;
                    } else {
                        addLayer = true;
                    }
                    isAdded = true;
                } else if (stack.peek().center != null && !stack.peek().center.isVisited) {
                    if (!stack.peek().center.isVisited && stack.peek().center.prise != 1 && !isAdded) {
                        stack.add(stack.peek().center);
                        if (addLayer) {
                            addLayer = false;
                        } else {
                            addLayer = true;
                        }
                        isAdded = true;
                    }
                } else if (stack.peek().right != null && !stack.peek().center.isVisited) {
                    if (!stack.peek().right.isVisited && stack.peek().right.prise != 1 && !isAdded) {
                        stack.add(stack.peek().right);
                        if (addLayer) {
                            addLayer = false;
                        } else {
                            addLayer = true;
                        }
                        isAdded = true;
                    }
                }


                if (stack.peek().right != null && !stack.peek().right.isVisited && stack.peek().right.prise > 1) {
                    stack.add(stack.peek().right);
                    if (addLayer) {
                        addLayer = false;
                    } else {
                        addLayer = true;
                    }
                    isAdded = true;
                }

                if (stack.peek().left == null || stack.peek().left.isVisited) {
                    //System.out.println("1st");
                    if (stack.peek().center == null || stack.peek().center.isVisited) {
                        //System.out.println("2nd");
                        if (stack.peek().right == null || stack.peek().right.isVisited) {
                            //System.out.println("3rd");
                            Integer boof = stack.peek().coeficientOfTaken;
                            stack.peek().isVisited = true;
                            stack.pop();
                            if (addLayer) {
                                addLayer = false;
                            } else {
                                addLayer = true;
                            }
                            if(!stack.isEmpty()) {
                                if (addLayer) {
                                    stack.peek().coeficientOfTaken += boof;
                                } else {
                                    stack.peek().coeficientOfTaken -= boof;
                                }
                            }
                        }
                    }
                }
            }
    }while (!stack.isEmpty());
}
}

