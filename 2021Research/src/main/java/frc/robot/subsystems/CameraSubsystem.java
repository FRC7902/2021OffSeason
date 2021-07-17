// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.GripPipeline;

public class CameraSubsystem extends SubsystemBase {
  
  private GripPipeline gripPipeline;
  private Thread visionThread;

  private boolean runProcessing = false;
  private double centerX;
  private double centerY;
  private int contoursFound;

  /** Creates a new CameraSubsystem. */
  public CameraSubsystem() {
    enableVisionThread();
  }

  public void enableVisionThread(){

    //Define the GRIP pipeline
    gripPipeline = new GripPipeline();

    //Start the webcam and set its resolution
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
    camera.setResolution(640, 480);

    //Define the camera input and output 
    CvSink cvSink = CameraServer.getInstance().getVideo();
    CvSource outputStream = CameraServer.getInstance().putVideo("Stream", 640, 480);

    //Define a mat (so apparently it can be reused and save space)
    Mat mat = new Mat();

    //turn on processing and set number of contours to 0
    runProcessing = true;
    contoursFound = 0;

    //Create a vision thread to deal with vision processing
    visionThread = new Thread(
      () -> {

        while (!Thread.interrupted()) {

          if(cvSink.grabFrame(mat) == 0){ //If there is no camera input, output an error
            outputStream.notifyError(cvSink.getError());

            continue;
          }

          if(runProcessing){ //if processing is turned on

            //run the image through the pipeline
            gripPipeline.process(mat);

            //access the number of contours and put it on SmartDashboard
            contoursFound = gripPipeline.convexHullsOutput().size();
            SmartDashboard.putString("More vision state", "Saw " + contoursFound + " Contours");

            if(contoursFound > 0){ //if at least one contour exists
              //get a bounding rectangle around the contour
              Rect rect = Imgproc.boundingRect(gripPipeline.convexHullsOutput().get(0)); 

              //draw a bounding rectangle on the image
              Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y+rect.height), new Scalar(0,0,255), 2);

              //calculate the center of the contour
              centerX = rect.x + rect.width/2;
              centerY = rect.y + rect.height/2;

              //put up data on SmartDashboard
              SmartDashboard.putString("Vision state", "Executed overlay!");
              SmartDashboard.putNumber("Center X", centerX);
            }

            //output the image
            outputStream.putFrame(mat);


            }

        }

      }
    );
    //start the vision thread
    visionThread.setDaemon(true);
    visionThread.start();


  }


  //return the centerX value of the contour
  public double getCenterX(){
    return centerX;
  }

  //turn off processing
  public void disableProcessing(){
    runProcessing = false;
  }

  //turn on processing
  public void enableProcessing(){
    runProcessing = true;
  }

  //return the number of contours
  public int getNumContours(){
    return contoursFound;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
