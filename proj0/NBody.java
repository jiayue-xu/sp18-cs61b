public class NBody {
    static private final String backImageToDraw = "images/starfield.jpg";

    public static double readRadius(String fileName){
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        int n = in.readInt();
        in.readDouble();
        Planet[] allPlanets = new Planet[n];
        for(int i = 0; i < n; i++){
            double px = in.readDouble();
            double py = in.readDouble();
            double vx = in.readDouble();
            double vy = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            allPlanets[i] = new Planet(px, py, vx, vy, m, img);
        }
        return allPlanets;
    }

    public static void main(String[] args) {
        if (args.length == 3) {
            double T = Double.parseDouble(args[0]);
            double dt = Double.parseDouble(args[1]);
            String filename = args[2];
            double t = 0.0;

            double radius = readRadius(filename);
            Planet []allPlanets = readPlanets(filename);

            StdDraw.setScale(-radius, radius);
            StdDraw.enableDoubleBuffering();

            while(t < T){
                double []xForces = new double[allPlanets.length];
                double []yForces = new double[allPlanets.length];
                for(int i = 0; i < allPlanets.length; i++){
                    xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                    yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
                }
                for(int i = 0; i < allPlanets.length; i++){
                    allPlanets[i].update(dt, xForces[i], yForces[i]);
                }
                StdDraw.clear();
                StdDraw.picture(0.0, 0.0, backImageToDraw);
                for(Planet p : allPlanets) p.draw();
                StdDraw.show();
                StdDraw.pause(10);
                t += dt;
            }

            StdOut.printf("%d\n", allPlanets.length);
            StdOut.printf("%.2e\n", radius);
            for (int i = 0; i < allPlanets.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                        allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
            }
        }
    }
}
