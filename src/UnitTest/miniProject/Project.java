package UnitTest.miniProject;
import elements.*;
import geometries.Polygon;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;
import geometries.*;
import primitives.*;


public class Project {

    @Test
    public void project() {
        Camera camera = new Camera(
                new Point3D(0, 0, 1000),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0))
                .setViewPlaneSize(200, 125)
                .setDistance(800)
                .setNumOfRays(70);

        Scene scene = new Scene("Test Scene");
        setLights(scene);
        setGeometries(scene);

        Render render = new Render() //
                .setCamera(camera) //
                .setMultithreading(3)
                .setRayTracer(new RayTracerBasic(scene).setGlossinessRays(20));

        int frames = 16;
        double angle = 360d / frames;
        double angleRadians = 2 * Math.PI / frames;

        double radius = camera.getP0().subtract(Point3D.ZERO).length();

        for (int i = 0; i < frames; i++) {
            System.out.println("Start frame " + (i + 1));

            camera.rotate(0, angle, 0);
            camera.setP0(
                    Math.sin(angleRadians * (i + 1)) * radius,
                    0,
                    Math.cos(angleRadians * (i + 1)) * radius
            );

            ImageWriter imageWriter = new ImageWriter("project/Project" + (i + 1), 600, 450);
            render.setImageWriter(imageWriter);
            render.renderImage();
            render.writeToImage();
        }
    }


    private void setLights(Scene scene){
        scene.lights.add(
                new SpotLight(
                        new Color(400, 400, 400),
                        new Vector(-0.5, -1, -0.5),
                        new Point3D(-50, 100, 100))
                        .setkL(0.004)
                        .setkQ(0.000006)
        );
        scene.lights.add(new SpotLight(new Color(0,250,350), new Vector(1, 1, -2), new Point3D(-200, 100, 0)).setSpecularN(40) //
                .setkL(0.00000005).setkQ(0.000000005));
     scene.lights.add(new SpotLight(new Color(0,250,350), new Vector(1, 0.5, -2), new Point3D(-200, 50, 0)).setSpecularN(20) //
                .setkL(0.00000005).setkQ(0.000000005));
        scene.lights.add(new SpotLight(new Color(0,250,350), new Vector(1, 1, -2), new Point3D(-200, 55, 0)).setSpecularN(10) //
                .setkL(0.00000005).setkQ(0.000000005));


    }


    private void setGeometries(Scene scene) {
        //triangles
                Point3D h=new Point3D(60,-50,30);
                Point3D g=new Point3D(45,-30,0);
                Point3D a=new Point3D(30,-50,30);
                Point3D b=new Point3D(40,0,15);



        scene.geometries.add(
                //sphere

                new Sphere(new Point3D(80, -28, 0), 22)
                        .setEmission(new Color(30,40,50))
                       .setMaterial(new Material()
                                .setkR(0.8)),

                new Sphere(new Point3D(-45, -45, -5), 5)
                        .setEmission(new Color(0,60,0))
                        .setMaterial(new Material()
                                .setkR(0.8).setkG(0.95)),

                //triangles

                new Triangle(a,g,h)
                        .setEmission(new Color(0, 75, 66))
                        .setMaterial(new Material()
                                .setkD(0.6).setkS(0.4).setkT(0.6)
                                .setnShininess(80)),
                new Triangle(a,b,h)
                        .setEmission(new Color(0, 75, 66))
                        .setMaterial(new Material()
                                .setkD(0.6).setkS(0.4).setkT(0.6)
                                .setnShininess(80)),
                new Triangle(a,b,g)
                        .setEmission(new Color(0, 75, 66))
                        .setMaterial(new Material()
                                .setkD(0.6).setkS(0.4).setkT(0.6)
                                .setnShininess(80)),
                new Triangle(g,b,h)
                        .setEmission(new Color(0, 75, 66))
                        .setMaterial(new Material()
                                .setkD(0.6).setkS(0.4).setkT(0.6)
                           .setnShininess(80)),

                //cylinder
                new Cylinder(new Ray(
                        new Point3D(-80, -45, 0),
                        new Vector(60, 85, 0)),
                        13, 50)
                        .setEmission(new Color(0,100,70))
                        .setMaterial(new Material()
                                .setkD(0.6).setkS(0.4)
                                .setnShininess(50))
                ,
                new Cylinder(new Ray(
                        new Point3D(-70, -61, 0),
                        new Vector(1, 0, 0)),
                        11, 140)
                        .setEmission(new Color(0,51,102))
                        .setMaterial(new Material()
                                  .setkD(0.6).setkS(0.4).setkG(0.9)
                                .setnShininess(50)),


                //square
              //1
                new Polygon(new Point3D(-25,-50,-30),
                        new Point3D(-25,-50,30),
                        new Point3D(15,-50,30),
                        new Point3D(15,-50,-30))
                        .setEmission(new Color(0,75,100))
                        .setMaterial(new Material()
                                .setkD(0.6).setkS(0.4)
                                .setnShininess(50)),
                new Polygon(new Point3D(-25,-25,-30),
                        new Point3D(-25,-25,30),
                        new Point3D(15,-25,30),
                        new Point3D(15,-25,-30))
                        .setEmission(new Color(0,75,100))
                        .setMaterial(new Material()
                                .setkD(0.6).setkS(0.4)
                                .setnShininess(50)),
                new Polygon(new Point3D(-25,-50,-30),
                        new Point3D(-25,-50,30),
                        new Point3D(-25,-25,30),
                        new Point3D(-25,-25,-30))
                        .setEmission(new Color(0,75,100))
                        .setMaterial(new Material()
                                .setkD(0.6).setkS(0.4)
                                .setnShininess(50)),
                new Polygon(new Point3D(15,-50,-30),
                        new Point3D(15,-50,30),
                        new Point3D(15,-25,30),
                        new Point3D(15,-25,-30))
                        .setEmission(new Color(0,75,100))
                        .setMaterial(new Material()
                                .setkD(0.6).setkS(0.4)
                                .setnShininess(50)),
                new Polygon(new Point3D(-25,-50,30),
                        new Point3D(15,-50,30),
                        new Point3D(15,-25,30),
                        new Point3D(-25,-25,30))
                        .setEmission(new Color(0,75,100))
                        .setMaterial(new Material()
                                .setkD(0.6).setkS(0.4)
                                .setnShininess(50)),
                new Polygon(new Point3D(-25,-50,-30),
                        new Point3D(15,-50,-30),
                        new Point3D(15,-25,-30),
                        new Point3D(-25,-25,-30))
                        .setEmission(new Color(0,75,100))
                        .setMaterial(new Material()
                                .setkD(0.6).setkS(0.4)
                                .setnShininess(50)),
                //2
                new Polygon(new Point3D(-15,-25,-20),
                        new Point3D(-15,-25,20),
                        new Point3D(5,-25,20),
                        new Point3D(5,-25,-20))
                        .setEmission(new Color(0,90,100))
                        .setMaterial(new Material()
                                .setkR(0.1).setkD(0.5).setkS(0.5).setkT(0.2)
                                .setnShininess(60)),
                new Polygon(new Point3D(-15,-15,-20),
                        new Point3D(-15,-15,20),
                        new Point3D(5,-15,20),
                        new Point3D(5,-15,-20))
                        .setEmission(new Color(0,90,100))
                        .setMaterial(new Material()
                                .setkR(0.1).setkD(0.5).setkS(0.5).setkT(0.2)
                                .setnShininess(60)),
                new Polygon(new Point3D(-15,-25,-20),
                        new Point3D(-15,-25,20),
                        new Point3D(-15,-15,20),
                        new Point3D(-15,-15,-20))
                        .setEmission(new Color(0,90,100))
                        .setMaterial(new Material()
                                .setkR(0.1).setkD(0.5).setkS(0.5).setkT(0.2)
                                .setnShininess(60)),
                new Polygon(new Point3D(5,-25,-20),
                        new Point3D(5,-25,20),
                        new Point3D(5,-15,20),
                        new Point3D(5,-15,-20))
                        .setEmission(new Color(0,90,100))
                        .setMaterial(new Material()
                                .setkR(0.1).setkD(0.5).setkS(0.5).setkT(0.2)
                                .setnShininess(60)),
                new Polygon(new Point3D(-15,-25,20),
                        new Point3D(5,-25,20),
                        new Point3D(5,-15,20),
                        new Point3D(-15,-15,20))
                        .setEmission(new Color(0,90,100))
                        .setMaterial(new Material()
                                .setkR(0.1).setkD(0.5).setkS(0.5).setkT(0.2)
                                .setnShininess(30)),
                new Polygon(new Point3D(-15,-25,-20),
                        new Point3D(5,-25,-20),
                        new Point3D(5,-15,-20),
                        new Point3D(-15,-15,-20))
                       .setEmission(new Color(0,90,100))
                        .setMaterial(new Material()
                                .setkR(0.1).setkD(0.5).setkS(0.5).setkT(0.2)
                                .setnShininess(60)),


                // surface
                new Polygon(
                        new Point3D(-100, -50, -150),
                        new Point3D(-100, -50, 200),
                        new Point3D(100, -50, 200),
                        new Point3D(100, -50, -150))
                        .setEmission(new Color(102, 153, 153))
                        .setMaterial(new Material()
                                .setkD(0.6).setkS(0.4).setkT(0.2)
                                .setnShininess(50)),
                //front block
                new Polygon(
                        new Point3D(0, -50, 75),
                        new Point3D(0, 30, 75),
                        new Point3D(38, 30, 75),
                        new Point3D(38, -50, 75))
                        .setEmission(new Color(40, 40, 40))
                        .setMaterial(new Material()
                                .setkT(1.0).setkG(0.8)),
                new Polygon(
                        new Point3D(42, -50, 75),
                        new Point3D(42, 30, 75),
                        new Point3D(80 ,30, 75),
                        new Point3D(80, -50, 75))
                        .setEmission(new Color(40, 40, 40))
                        .setMaterial(new Material()
                                .setkT(1.0).setkG(0.8))



        );
    }
    @Test
    public void project2() {
        Camera camera = new Camera(
                new Point3D(0, 0, 1000),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0))
                .setViewPlaneSize(225, 150)
                .setDistance(800)
                .setNumOfRays(10)
                .setFocus(new Point3D(0, 0, 0), 500);

        Scene scene = new Scene("Test Scene");
        scene.lights.add(

                new SpotLight(
                        new Color(500, 500, 500),
                        new Vector(-0.5, -1, -0.5),
                        new Point3D(-50, 100, 100))
                        .setkL(0.004)
                        .setkQ(0.000006));
        scene.geometries.add(
                new Sphere(new Point3D(50, 0, 0), 50)
                        .setEmission(new Color(5, 5, 5))
                        .setMaterial(new Material()
                                .setkR(1.0).setkG(0.8)),
                new Cylinder(new Ray(
                        new Point3D(-90, -35, 0),
                        new Vector(60, 85, 0)),
                        25, 100)
                        .setEmission(new Color(100, 75, 0))
                        .setMaterial(new Material()
                                .setkD(0.6).setkD(0.4)
                                .setnShininess(80)),
                new Polygon(
                        new Point3D(-100, -50, -150),
                        new Point3D(-100, -50, 150),
                        new Point3D(100, -50, 150),
                        new Point3D(100, -50, -150))
                        .setEmission(new Color(40, 40, 40))
                        .setMaterial(new Material()
                                .setkD(0.6).setkS(0.4)
                                .setnShininess(50)),
                new Polygon(
                        new Point3D(-100, -50, -150),
                        new Point3D(-100, 75, -150),
                        new Point3D(100, 75, -150),
                        new Point3D(100, -50, -150))
                        .setEmission(new Color(40, 40, 40))
                        .setMaterial(new Material()
                                .setkD(0.6).setkS(0.4)
                                .setnShininess(50))
        );

        int frames = 16;
        double angle = 360d / frames;
        double angleRadians = 2 * Math.PI / frames;

        double radius = camera.getP0().subtract(Point3D.ZERO).length();

        for (int i = 0; i < frames; i++) {
            System.out.println("Start frame " + (i + 1));

            camera.rotate(0, angle, 0);
            camera.setP0(
                    Math.sin(angleRadians * (i + 1)) * radius,
                    0,
                    Math.cos(angleRadians * (i + 1)) * radius
            );

            Render render = new Render()
                    .setImageWriter(
                            new ImageWriter("project/Project2." + (i + 1), 750, 500))
                    .setCamera(camera)
                    .setMultithreading(5)
                    .setRayTracer(new RayTracerBasic(scene).setGlossinessRays(20));
            render.renderImage();
            render.writeToImage();


        }

    }
}
