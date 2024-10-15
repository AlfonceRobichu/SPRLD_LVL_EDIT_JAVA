package src.formes;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Raymath;
import com.raylib.java.raymath.Vector2;

import src.RayClass;

public class Rectr {
    private Vector2 pos;
    private int width;
    private int height;
    private int rot;

    public Rectr(Vector2 pos, int width, int height){
        this.pos = new Vector2(pos.x, pos.y);
        this.width = width;
        this.height = height;
        this.rot = 0;
    }

    public Rectr(Vector2 pos, int width, int height, int rot){
        this.pos = new Vector2(pos.x, pos.y);
        this.width = width;
        this.height = height;
        this.rot = rot;
    }

    private Vector2 rectr_getTRcorner(){
        Vector2 point = new Vector2(width, -height);
        Vector2 res = Raymath.Vector2Rotate(point, rot);
        res.x += pos.x;    
        res.y += pos.y;
        return res;
    }
    
    
    private Vector2 rectr_getTLcorner(){
        Vector2 point = new Vector2(-width, -height);
        Vector2 res = Raymath.Vector2Rotate(point, rot);
        res.x += pos.x;    
        res.y += pos.y;
        return res;
    }
    
    
    
    private Vector2 rectr_getBRcorner(){
        Vector2 point = new Vector2(width, height);
        Vector2 res = Raymath.Vector2Rotate(point, rot);
        res.x += pos.x;    
        res.y += pos.y;
        return res;
    }
    
    private Vector2 rectr_getBLcorner(){
        Vector2 point = new Vector2(-width, height);
        Vector2 res = Raymath.Vector2Rotate(point, rot);
        res.x += pos.x;    
        res.y += pos.y;
        return res;
    }

    public void getAllCorners(Vector2 tab[]){
        tab[0] = rectr_getTLcorner();
        tab[1] = rectr_getTRcorner();
        tab[2] = rectr_getBRcorner();
        tab[3] = rectr_getBLcorner();
    }


    
    private static Vector2 findFurthestPoint(Vector2 direction, Vector2 points[], int points_size){
        Vector2 maxPoint = new Vector2(0.0f, 0.0f);
        float maxDist = -Float.MAX_VALUE;
    
        for(int i = 0; i < points_size; i++){
            float dist = RayClass.Vector2DotProduct(points[i], direction);
            if(dist > maxDist){
                maxDist = dist;
                maxPoint = points[i];
            }
        }
        return maxPoint;
    }
    
    private static Vector2 supportWithPoint(Vector2 colliderA[], Vector2 point, int sizeA, Vector2 direction){
        Vector2 furthestPointA = findFurthestPoint(direction, colliderA, sizeA);
        return  Raymath.Vector2Subtract(furthestPointA, point);
    }



    private static Vector2 tripleProd(Vector2 a, Vector2 b, Vector2 c){
        Vector2 res = new Vector2();
        float ac = RayClass.Vector2DotProduct(a, c);
        float bc = RayClass.Vector2DotProduct(b, c);
        res.x = (b.x * ac) - (a.x  * bc);
        res.y = (b.y * ac) - (a.y  * bc);
        return res;
    }

    private static Vector2 perpendicular (Vector2 v) {return new Vector2( v.y, -v.x );}

    /**
     * utiliser pour verifier si le curseur est dans un rectr
     * @param r1 un rectr
     * @param point
     * @return true si le point est dans le rectr
     */
    public boolean gjk_with_point(Vector2 point){      

        Vector2 a, b, c, d, ao, ab, ac, abperp, acperp;
        Vector2 simplex[] = new Vector2[3];

        int index = 0;

        d = Raymath.Vector2Subtract(point, pos);
        if(d.x == 0.0f && d.y == 0.0f)
            d.x = 1.0f;

        Vector2 r1Corners[] = new Vector2[4];
        getAllCorners(r1Corners);

        a = simplex[0] = supportWithPoint(r1Corners, point, 4, d);

        if(RayClass.Vector2DotProduct(a, d) <= 0) return false;
        d =  Raymath.Vector2Negate(a);

        while(true){                                             
            a = simplex[++index] = supportWithPoint(r1Corners, point, 4, d);

            if(RayClass.Vector2DotProduct(a, d) <= 0) return false;

            ao = Raymath.Vector2Negate(a);

            if(index < 2){      //cas de la ligne
                b = simplex[0];
                ab = Raymath.Vector2Subtract(b, a);
                d = tripleProd(ab, ao, ab);
                if(Raymath.Vector2LengthSqr(d) == 0){
                    d = perpendicular(ab);
                }
                continue;

            }

            //cas du simplex
            b = simplex[1];
            c = simplex[0];
            ab = Raymath.Vector2Subtract(b, a);
            ac = Raymath.Vector2Subtract(c, a);

            acperp = tripleProd(ab, ac, ac);

            if(RayClass.Vector2DotProduct(acperp, ao) >= 0){
                d = acperp;
            }

            else{
                abperp = tripleProd(ac, ab, ab);
                if(RayClass.Vector2DotProduct(abperp, ao) < 0)
                    return true;

                simplex[0] = simplex[1];
                d = abperp;
            }

            simplex[1] = simplex[2];
            --index;
        }
    }


    public void draw(Color color){
        Vector2 coins[] = new Vector2[4];
        getAllCorners(coins);

        RayClass.rlj.shapes.DrawLineV(coins[0], coins[1], color);
        RayClass.rlj.shapes.DrawLineV(coins[1], coins[2], color);
        RayClass.rlj.shapes.DrawLineV(coins[2], coins[3], color);
        RayClass.rlj.shapes.DrawLineV(coins[3], coins[0], color);
    }
    

}
