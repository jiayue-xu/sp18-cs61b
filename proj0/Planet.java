import java.math.*;

public class Planet {
    static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p){
        double r = calcDistance(p);
        return G * mass * p.mass / r / r;
    }

    public double calcForceExertedByX(Planet p){
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);

        return f * (p.xxPos - xxPos) / r;
    }

    public double calcForceExertedByY(Planet p){
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        return f * (p.yyPos -  yyPos) / r;
    }

    public double calcNetForceExertedByX(Planet []allPlanets){
        double netForceX = 0.0;
        for(Planet p : allPlanets){
            if(!p.equals(this)){
                netForceX += calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet []allPlanets){
        double netForceY = 0.0;
        for(Planet p : allPlanets){
            if(!p.equals(this)){
                netForceY += calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fx, double fy){
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
