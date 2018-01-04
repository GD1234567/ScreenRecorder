package com.vserdiuk.screenrecorder;

import com.vserdiuk.screenrecorder.recorder.Recorder;

import java.awt.*;
import java.util.Scanner;

/**
 * Created by vserdiuk on 1/4/18.
 */

public class ScreenRecorderApp {
    public static void main(String[] args) throws Exception {
        Recorder recorder = new Recorder();
        GraphicsDevice[] graphicsDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        int graphicsDeviceAmount = graphicsDevices.length;
        GraphicsConfiguration graphicsConfiguration;
        if (graphicsDeviceAmount > 1) {
            System.out.println("The application has detected " + graphicsDeviceAmount + " graphics devices connected to a PC. Please choose a device id you would like to record: ");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Device ID: ");
            int deviceId = scanner.nextInt() - 1;
            graphicsConfiguration = graphicsDevices[deviceId].getDefaultConfiguration();
            recorder.startRecording("/home/vserdiuk/Projects/my-projects/ScreenRecorder/", graphicsConfiguration);
        } else {
            graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
            recorder.startRecording("/home/vserdiuk/Projects/my-projects/ScreenRecorder/", graphicsConfiguration);
        }
        Thread.sleep(30000);
        recorder.stopRecording();
    }
} 